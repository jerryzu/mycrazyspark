<#list procedurelist as p>

CREATE PROCEDURE "${p.schemaName}"."${p.procName}" @ps_batch VARCHAR, @pd_startdate DATETIME, @pd_enddate DATETIME,	@pd_datatime DATETIME
/**
* 业务: ${p.procDesc}
* 名称: ${p.procDesc} 
* 开发者: ${p.author!" "}
* DATE: ${p.createtime?string("yyyy-MM-dd HH:mm:ss")}
* 异常处理: 继续/
*
* 运行规则: 每天运行计算本月截止当前时间的数据
* ================================运行样例===================================
DECLARE	@return_value int,  @vs_batch VARCHAR, @vd_startdate DATETIME, @vd_enddate DATETIME, @vd_datatime DATETIME

SELECT 	@vs_batch = CONVERT(varchar, getdate(), 120 ), @vd_startdate =  DATEADD(YEAR,  -5, GETDATE()), @vd_enddate = GETDATE(), @vd_datatime = GETDATE()

EXEC	@return_value = ${p.schemaName}.${p.procName} @vs_batch, @vd_startdate, @vd_enddate, @vd_datatime

SELECT 'Return Value' = @return_value
* 更新机制 全量（2010-01 开始至 上月）
* =================================相关表====================================
* 依赖过程
* 数据源  <#list p.dependencylist as depend><#if depend.objectType == "SOURCETABLE"> ${depend.dependencyName!" "} </#if> </#list>
* 结果表  <#list p.dependencylist as depend><#if depend.objectType == "TARGETTABLE"> ${depend.dependencyName!" "} </#if> </#list>
* =================================更新历史====================================
*  创建时间	开发人员	动作	修改原因
*  ${p.createtime?string("yyyyMMdd")}	${p.author}	创建
**/
AS

BEGIN
	-- 标准模板定义变量
	DECLARE @v_log_etl_program_type VARCHAR(50)
	DECLARE @v_log_etl_program_name VARCHAR(100)
	DECLARE @v_log_db_level VARCHAR(10)
	DECLARE @v_log_source_table VARCHAR(200)
	DECLARE @v_log_dest_table VARCHAR(100)
	DECLARE @v_log_data_time DATETIME
	DECLARE @v_log_start_time DATETIME
	DECLARE @v_log_end_time DATETIME
	DECLARE @v_log_status VARCHAR(10)
	DECLARE @v_log_err_code VARCHAR(10)
	DECLARE @v_log_err_desc VARCHAR(200)
	DECLARE @v_log_DelRecord INT
	DECLARE @v_log_InsRecord INT

	SELECT @v_log_etl_program_type = 'PRC'
	SELECT @v_log_etl_program_name = '${p.schemaName}.${p.procName}'
	SELECT @v_log_db_level = 'EDW'
	SELECT @v_log_source_table = '<#list p.dependencylist as depend><#if depend.objectType == "SOURCETABLE"> ${depend.dependencyName!" "} </#if> </#list>'
	SELECT @v_log_dest_table = '<#list p.dependencylist as depend><#if depend.objectType == "TARGETTABLE"> ${depend.dependencyName!" "} </#if> </#list>'
	SELECT @v_log_data_time = @pd_datatime
	SELECT @v_log_start_time = GETDATE()

	BEGIN TRY	
		BEGIN TRAN
			--
			DELETE FROM ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "} 
			<#if (p.dependTargetTablelist[0].updateField)??> 
			WHERE ${p.dependTargetTablelist[0].updateField!" "}=@pd_datatime
			</#if>
			SELECT @v_log_DelRecord= @@ROWCOUNT
			-- TO DO IT  

			INSERT INTO ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "}(
			<#list p.dependFieldMappinglist as FieldProp>
			<#if FieldProp_index != 0>,</#if><#rt>
			${FieldProp.targetFieldName!" "} --${FieldProp_index+1} ${FieldProp.targetFieldDesc!" "}<#lt>
			</#list>)
			SELECT
			<#list p.dependFieldMappinglist as FieldProp>
			<#if FieldProp_index != 0>,</#if><#rt>
			<#if FieldProp.sourceExpression??>${FieldProp.sourceExpression!" "}<#else>${FieldProp.sourceFieldName!" "}</#if> <#t>		
			--${FieldProp_index+1}	${FieldProp.sourceFieldDesc!" "}<#lt>
			</#list>
			FROM ${p.dependFieldMappinglist[0].sourceTableOwner!" "}.${p.dependFieldMappinglist[0].sourceTableName!" "} s
			<#if (p.dependTargetTablelist[0].updateField)??> 
			--WHERE ${p.dependSourceTablelist[0].updateField!" "} = CONVERT(VARCHAR(10),@pd_datatime,112)
			</#if>
			;

			SELECT @v_log_end_time = GETDATE(), @v_log_status = 'SUCCEED', @v_log_err_code = '', @v_log_err_desc = '',
				@v_log_InsRecord= @@ROWCOUNT

		COMMIT TRAN
	END TRY
	BEGIN CATCH
		SET @v_log_end_time = GETDATE()
		SET @v_log_status = 'FAILED'
		SET @v_log_err_code = ERROR_NUMBER()
		SET @v_log_err_desc = ERROR_MESSAGE()

		IF @@TRANCOUNT > 0 --判断有无事务
		BEGIN
			ROLLBACK TRAN	--回滚事务
		END 		
	END CATCH

	INSERT INTO [TS_MAN_ETL_LOG] 
	VALUES (@v_log_etl_program_type, @v_log_etl_program_name, @v_log_db_level, @v_log_source_table, @v_log_dest_table
		,@v_log_data_time, @v_log_start_time,	@v_log_end_time,	@v_log_status,	@v_log_err_code,	@v_log_err_desc)	
END
GO
</#list>