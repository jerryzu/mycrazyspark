<#list procedurelist as p>
 CREATE OR REPLACE PROCEDURE ${p.schemaName}.${p.procName} (ps_batch varchar2, pd_startdate date, pd_enddate date) is
/**
 * 业务: ${p.procDesc} 
 * 名称: ${p.procDesc} 
 * 开发者: ${p.author!""} 
 * DATE: ${(p.createtime!.now)?string("yyyy-MM-dd HH:mm:ss")}
 * 异常处理: 继续/
 *
 * 运行规则: 每天运行计算本月截止当前时间的数据
 * ================================运行样例===================================
 * EXEC ${p.schemaName}.${p.procName}('${p.debug.batch}','${p.debug.startDate?string("yyyyMMdd")}','${p.debug.endDate?string("yyyyMMdd")}');  --计算截止上月数据
 *              ps_batch       批次号（时间 精确至秒）
 *              pd_startdate   计算开始时间
 *              pd_enddate     计算结束时间
 * 更新机制 全量（2010-01 开始至 上月） 
 * =================================相关表====================================
 * 依赖过程
 * 数据源  <#list p.dependencylist as depend><#if depend.objectType == "SOURCETABLE"> ${depend.dependencyName!" "} </#if> </#list>
 * 结果表  <#list p.dependencylist as depend><#if depend.objectType == "TARGETTABLE"> ${depend.dependencyName!" "} </#if> </#list>
 * =================================更新历史====================================
 *  创建时间	开发人员	动作	修改原因
 *  ${(p.createtime!.now)?string("yyyyMMdd")}	${p.author!""}	创建
 */
  -- 标准模板定义变量
  vn_errorcode number;
  vs_errorMsg varchar2(2000);
  vn_logid number(20) := -1;
  vn_tmplogid number(20) := -1;
  vs_prcOwner varchar2(60);
  vs_prcName varchar2(60);
  vs_prcParam varchar2(100);
  vn_tmpcount number(20) := -1;
  vn_rowcount number(20) := 0;
  vn_etlRunId number(20) := -1;
  vs_etlDesc varchar2(300);
  vd_startdate  DATE;
  vd_startMonth DATE;
  vd_enddate    DATE;
  vd_endMonth   DATE;
  vd_endLastday DATE;
  vs_startdate  VARCHAR2(20);
  vs_startMonth VARCHAR2(10);
  vs_enddate    VARCHAR2(20);
  vs_endMonth   VARCHAR2(10);
  vs_endLastday VARCHAR2(20);
  -- 开发人员定义变量
  VD_CURRENTMONTH     DATE;
  VD_CURRENTMONTHLAST DATE;
  VD_CURRENTDAY       DATE;
begin
  vs_prcOwner:='${p.schemaName}';
  vs_prcName:='${p.procName}';
  vs_etlDesc:='${p.procDesc}';
  
  vd_startdate  := TRUNC(pd_startdate);
  vd_startMonth := TRUNC(pd_startdate,'MM');
  vd_enddate    := OM_UTIL.FN_SMARTENDDATE(pd_enddate);
  vd_endMonth   := TRUNC(vd_enddate,'MM');
  vd_endLastday := last_day(vd_enddate);
  vs_startdate  := to_char(vd_startdate,'YYYYMMDDHH24MISS');

  vs_startMonth := TO_CHAR(vd_startdate,'YYYYMM');
  vs_enddate    := to_char(vd_enddate,'YYYYMMDDHH24MISS');

  vs_endMonth   := TO_CHAR(vd_enddate,'YYYYMM');
  vs_endLastday := TO_CHAR(vd_endLastday,'YYYYMMDD');  

  vs_prcParam:=ps_batch||','||vs_startdate||','||vs_enddate;
  
  -- 模板过程开始的日志记录和ETL状态记录
  vn_logid := TOOL_UTIL.FN_ETL_LOG_INFO(vn_logid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',sysdate,null,0,vs_etlDesc,null);
  vn_etlRunId := TOOL_UTIL.FN_ETL_PRC_START(vs_prcOwner, vs_prcName, vs_prcParam, ps_batch, vd_enddate, vs_etlDesc);

  IF OM_UTIL.FN_IS_INITDATA = 1 THEN
    vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(-1,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',sysdate,null,0,'清除${p.dependTargetTablelist[0].targetTableOwner!" "}.${p.dependTargetTablelist[0].targetTableName!" "}表',null);
    SELECT COUNT(1) INTO VN_TMPCOUNT FROM ${p.dependTargetTablelist[0].targetTableOwner!" "}.${p.dependTargetTablelist[0].targetTableName!" "};
    COM_UTIL.PRC_SQL_TRUNCATE('${p.dependTargetTablelist[0].targetTableOwner!" "}','${p.dependTargetTablelist[0].targetTableName!" "}',NULL,NULL);   
    vn_rowcount := vn_rowcount+vn_tmpcount;
    VN_TMPLOGID := TOOL_UTIL.FN_ETL_LOG_DEBUG(VN_TMPLOGID,VS_PRCOWNER,VS_PRCNAME,VS_PRCPARAM,PS_BATCH,'exec',NULL,SYSDATE,VN_TMPCOUNT,'TRUNCATE成功!',1);
  END IF;

  -- TO DO IT  
  VN_TMPLOGID := TOOL_UTIL.FN_ETL_LOG_DEBUG(-1,VS_PRCOWNER,VS_PRCNAME,VS_PRCPARAM,PS_BATCH,'exec',SYSDATE,NULL,0,'插入 数据到表RTDB_DM.TDM_EVT_FACT_CTRL_DSHBRD',1);
  
  vd_currentMonth       := vd_startMonth;
  WHILE vd_currentMonth <= vd_endMonth
  LOOP
  begin
    VD_CURRENTMONTH := OM_UTIL.FN_CURRENTMONTHSTART(vd_currentMonth);
    VD_CURRENTMONTHLAST := OM_UTIL.FN_CURRENTMONTHEND(vd_currentMonth, vd_enddate);
    
    --CLEAR REDUNDANT DATA
    IF OM_UTIL.FN_IS_INITDATA = 0 THEN
      SELECT COUNT(1) INTO vn_tmpcount FROM ${p.dependTargetTablelist[0].targetTableOwner!" "}.${p.dependTargetTablelist[0].targetTableName!" "} t WHERE t.D_EVENTDATE BETWEEN VD_CURRENTMONTH AND VD_CURRENTMONTHLAST;
      vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(-1,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',sysdate,null,0,'TRUNCATE ${p.dependTargetTablelist[0].targetTableOwner!" "}.${p.dependTargetTablelist[0].targetTableName!" "}的分区'||TO_CHAR(VD_CURRENTMONTH, 'YYYYMM'),null);
      COM_UTIL.PRC_SQL_TRUNCATE_MONTH('${p.dependTargetTablelist[0].targetTableOwner!" "}','${p.dependTargetTablelist[0].targetTableName!" "}',VD_CURRENTMONTH,VD_CURRENTMONTHLAST,'YYYYMM','PART_');
      vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(vn_tmplogid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',null,sysdate,vn_tmpcount,'TRUNCATE成功!',1);
--      OTHER METHOD
--      VN_TMPLOGID := TOOL_UTIL.FN_ETL_LOG_DEBUG(-1,VS_PRCOWNER,VS_PRCNAME,VS_PRCPARAM,PS_BATCH,'exec',SYSDATE,NULL,0,'重导清除上次导入重复数据:${p.dependTargetTablelist[0].targetTableOwner!" "}.${p.dependTargetTablelist[0].targetTableName!" "}',1);
--      DELETE ${p.dependTargetTablelist[0].targetTableOwner!" "}.${p.dependTargetTablelist[0].targetTableName!" "} t WHERE t.D_EVENTDATE BETWEEN VD_CURRENTMONTH AND VD_CURRENTMONTHLAST;
--      vn_tmpcount := sql%rowcount;
--      vn_rowcount := vn_rowcount+vn_tmpcount;
--      COMMIT;
--      vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(vn_tmplogid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',null,sysdate,vn_tmpcount,'清除操作成功!',1);
    END IF;

  -- TO DO IT    
	INSERT INTO ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "} 
    (
	<#list p.dependFieldMappinglist as FieldProp>
	<#if FieldProp_index != 0>,</#if><#rt>
    ${FieldProp.targetFieldName!" "} --${FieldProp_index+1} ${FieldProp.targetFieldDesc!" "}<#lt>
    </#list>)
    SELECT
	<#list p.dependFieldMappinglist as FieldProp><#rt>
	<#if FieldProp_index != 0>,</#if><#rt>	
		<#if  FieldProp.sourceRefType??><#if  FieldProp.sourceRefType= "SUM">${FieldProp.sourceRefType!" "}(</#if></#if><#t>
	<#if FieldProp.sourceExpression??>${FieldProp.sourceExpression!" "}<#else>${FieldProp.sourceAlias!" "}.${FieldProp.sourceFieldName!" "}</#if><#t>		
	<#if  FieldProp.sourceRefType??><#if  FieldProp.sourceRefType= "SUM">)</#if></#if><#t>
	--${FieldProp_index+1}	${FieldProp.sourceFieldDesc!" "}<#lt>
	</#list>  
  <#list p.dependSourceTablelist as sourceTable>
  FROM ${sourceTable.sourceTableOwner!" "}.${sourceTable.sourceTableName!" "} s
  WHERE<#list sourceTable.incCaptureFieldlist as incCaptureField> ${incCaptureField.fieldName!""} BETWEEN VD_CURRENTMONTH AND VD_CURRENTMONTHLAST</#list>
  </#list> 
  GROUP BY <#list p.groupByFieldlist as FieldProp><#if FieldProp_index != 0>,</#if><#t>
	<#if FieldProp.sourceExpression??>${FieldProp.sourceExpression!" "}<#else>${FieldProp.sourceAlias!" "}.${FieldProp.sourceFieldName!" "}</#if><#lt>
  </#list>;
      	
    vn_tmpcount := sql%rowcount;
    vn_rowcount := vn_rowcount+vn_tmpcount;
    COMMIT;
    vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(vn_tmplogid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',null,sysdate,vn_tmpcount,'插入操作成功!',1);
    vd_currentMonth := trunc(add_months(vd_currentMonth,1),'MM');
  END;
  END LOOP;
  -- 模板过程结束的日志记录和ETL状态记录
  vn_logid := TOOL_UTIL.FN_ETL_LOG_INFO(vn_logid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',null,sysdate,vn_rowcount,'正常结束!',1);
  vn_etlRunId := TOOL_UTIL.FN_ETL_PRC_END(vn_etlRunId, vs_prcName, vd_enddate, 1, '正常结束!');

exception
  when others then
  vn_errorcode := SQLCODE;
  vs_errorMsg := vs_etlDesc||chr(10)||'SQLCODE:'||vn_errorcode||chr(10)||'SQLERRM:'||substr(SQLERRM,1,600);
  -- 模板过程异常的日志记录和ETL状态记录
  vn_logid := TOOL_UTIL.FN_ETL_LOG_ERROR(-1,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exception',null,sysdate,0,vs_errorMsg,null);
  vn_etlRunId := TOOL_UTIL.FN_ETL_PRC_END(vn_etlRunId, vs_prcName, vd_enddate, 0, vs_errorMsg);
  commit;
end;
/
</#list>