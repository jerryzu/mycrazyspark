<#list validatorlist as p>
 CREATE OR REPLACE PROCEDURE "${p.validatorSchema}"."${p.validatorName}" (ps_batch varchar2, pd_startdate date, pd_enddate date) is
/**
 * 业务: ${p.remark}
 * 名称: ${p.remark} 
 * 开发者: ${p.author!""}
 * DATE: ${p.createtime?string("yyyy-MM-dd HH:mm:ss")}
 * 异常处理: 继续/
 *
 * 运行规则: 每天运行计算本月截止当前时间的数据
 * ================================运行样例===================================
 * EXEC "${p.validatorSchema}"."${p.validatorName}"('${p.debug.batch}','${p.debug.startDate?string("yyyyMMdd")}','${p.debug.endDate?string("yyyyMMdd")}');  --计算截止上月数据
 *              ps_batch       批次号（时间 精确至秒）
 *              pd_startdate   计算开始时间
 *              pd_enddate     计算结束时间
 * 更新机制 全量（2010-01 开始至 上月）
*  创建时间	开发人员	动作	修改原因
 *  ${p.createtime?string("yyyyMMdd")}	${p.author!""}	创建
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
  vn_rowcount number(20) := -1;
  vn_etlRunId number(20) := -1;
  vs_etlDesc varchar2(300);

  vd_startdate date;
  vd_enddate date;
  vs_Startdate varchar2(30);
  vs_Enddate varchar2(30);

  -- 开发人员定义变量
  v_max_error NUMBER :=20;
  v_validator_msg VARCHAR2(4000);
  v_masterkeys VARCHAR2(2000);
  
  CURSOR prod_cursor IS SELECT   
  	<#list p.validatorItems as item>${item.fieldName}<#if item_has_next>,</#if></#list>
  	FROM ${p.tableName!""};
  
  <#list p.validatorItems as item>	
  v${item.fieldName} ${item.tableName}.${item.fieldName}%TYPE;
  </#list>
begin
  vs_prcOwner:='${p.validatorSchema}';
  vs_prcName:='${p.validatorName}';
  vs_etlDesc:='${p.remark}';
  vd_startdate:=pd_startdate;
  vd_enddate:=pd_enddate;
  vs_startdate:=to_char(vd_startdate,'YYYYMMDD');
  vs_enddate:=to_char(vd_enddate,'YYYYMMDD');
  vs_prcParam:=ps_batch||','||vs_startdate||','||vs_enddate;

  -- 模板过程开始的日志记录和ETL状态记录
  vn_logid := TOOL_UTIL.FN_ETL_LOG_INFO(vn_logid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',sysdate,null,0,vs_etlDesc,null);
  vn_etlRunId := TOOL_UTIL.FN_ETL_PRC_START(vs_prcOwner, vs_prcName, vs_prcParam, ps_batch, vd_enddate, vs_etlDesc);

  -- TO DO IT  

  OPEN prod_cursor;
  loop
    fetch prod_cursor INTO <#list p.validatorItems as item>	v${item.fieldName}<#if item_has_next>,</#if></#list>;
    exit WHEN prod_cursor%notfound;
    v_validator_msg := '';    
    v_masterkeys := <#list p.masterKeyItems as item>'${item.fieldName}' || ':' || TO_CHAR(v${item.fieldName}) <#if item_has_next> || ';' || <#else>;</#if> </#list>
    <#list p.validatorItems as item>
    <#if item.maxLength??>--最大长度校验
    IF  length(v${item.fieldName})> ${item.maxLength} THEN
    v_validator_msg := v_validator_msg || '${item.fieldName}  > ${item.maxLength} '  || ';' ;
    END IF;</#if>
    <#if item.minVal??>--取值范围校验-最小值
    IF v${item.fieldName} < ${item.minVal} THEN
    v_validator_msg := v_validator_msg || '${item.fieldName} < ${item.minVal}'  || ';' ;
    END IF;</#if>
    <#if item.maxVal??>--取值范围校验-最大值    
    IF v${item.fieldName} > ${item.maxVal} THEN
    v_validator_msg := v_validator_msg || '${item.fieldName} > ${item.maxVal}'  || ';' ;
    END IF;</#if><#t>
	</#list>
    IF v_validator_msg IS NOT NULL THEN
      v_validator_msg := v_masterkeys || ' 出错内容:' || v_validator_msg;
      dbms_output.put_line(v_validator_msg);
    END IF;
  END loop;
  CLOSE prod_cursor;

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