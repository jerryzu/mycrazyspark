<#list procedurelist as p>
 CREATE OR REPLACE PROCEDURE "${p.schemaName}"."${p.procName}" (ps_batch varchar2, pd_startdate date, pd_enddate date) is
/**
 * 业务: ${p.procDesc} 
 * 名称: ${p.procDesc} 
 * 开发者: ${p.author!""} 
 * DATE: ${(p.createtime!.now)?string("yyyy-MM-dd HH:mm:ss")}
 * 异常处理: 继续/
 *
 * 运行规则: 每天运行计算本月截止当前时间的数据
 * ================================运行样例===================================
 * EXEC "${p.schemaName}"."${p.procName}"('${p.debug.batch}','${p.debug.startDate?string("yyyyMMdd")}','${p.debug.endDate?string("yyyyMMdd")}');  --计算截止上月数据
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
  vn_rowcount number(20) := -1;
  vn_etlRunId number(20) := -1;
  vs_etlDesc varchar2(300);

  vd_startdate date;
  vd_enddate date;
  vs_Startdate varchar2(30);
  vs_Enddate varchar2(30);

  -- 开发人员定义变量

  vd_enddateMonth DATE;
  vd_currentMonth DATE;
  vd_currentMonthLast date; 
begin
  vs_prcOwner:='${p.schemaName}';
  vs_prcName:='${p.procName}';
  vs_etlDesc:='${p.procDesc}';
  vd_startdate:=pd_startdate;
  vd_enddate:=pd_enddate;
  vs_startdate:=to_char(vd_startdate,'YYYY-MM-DD hh24:mi:ss');
  vs_enddate:=to_char(vd_enddate,'YYYY-MM-DD hh24:mi:ss');
  vs_prcParam:=ps_batch||','||vs_startdate||','||vs_enddate;

  /*
  SELECT COUNT(1) INTO VN_TMPCOUNT FROM ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "};
  VN_TMPLOGID := TOOL_UTIL.FN_ETL_LOG_DEBUG(-1,VS_PRCOWNER,VS_PRCNAME,VS_PRCPARAM,PS_BATCH,'exec',SYSDATE,NULL,0,'TRUNCATE ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "}', 1);
  COM_UTIL.PRC_SQL_TRUNCATE('${p.dependFieldMappinglist[0].targetTableOwner!" "}','${p.dependFieldMappinglist[0].targetTableName!" "}',NULL,NULL);
  VN_TMPLOGID := TOOL_UTIL.FN_ETL_LOG_DEBUG(VN_TMPLOGID,VS_PRCOWNER,VS_PRCNAME,VS_PRCPARAM,PS_BATCH,'exec',NULL,SYSDATE,VN_TMPCOUNT,'TRUNCATE成功!',1);
  */
  <#list p.procEventlist as procEvent><#if procEvent.eventType?upper_case == "TARGETTABLE-REBUILD_SQL">
  --vs_dm_create_idx := '${procEvent.execSQL!" "}';
  --COM_UTIL.PRC_SQL_DDL(vs_dm_create_idx);
  </#if></#list>

  -- 模板过程开始的日志记录和ETL状态记录
  vn_logid := TOOL_UTIL.FN_ETL_LOG_INFO(vn_logid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',sysdate,null,0,vs_etlDesc,null);
  vn_etlRunId := TOOL_UTIL.FN_ETL_PRC_START(vs_prcOwner, vs_prcName, vs_prcParam, ps_batch, vd_enddate, vs_etlDesc);

  -- TO DO IT
  vd_enddateMonth := trunc(vd_enddate,'MM');
  vd_currentMonth := trunc(vd_startdate,'MM');  
  WHILE vd_currentMonth <= vd_enddateMonth LOOP
  BEGIN 
  -- 删除历史数据
    vd_currentMonthLast := last_day(vd_currentMonth);
    if(vd_currentMonthLast > vd_enddate) then vd_currentMonthLast := vd_enddate; end if;
    vd_currentMonthLast := com_util.FN_DATE_DAY_ENDTIME(vd_currentMonthLast);
  
    vn_tmplogid := -1;
    SELECT COUNT(1) INTO vn_tmpcount FROM TDA_BH_DOWNPAYMENT_DAY WHERE D_EVENTDATE BETWEEN vd_currentMonth AND last_day(vd_currentMonth);
  
    vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(-1,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',sysdate,null,0,'TRUNCATE TDA_BH_DOWNPAYMENT_DAY 的分区'||to_char(vd_currentMonth,'YYYYMM'),null);
    COM_UTIL.PRC_SQL_TRUNCATE_MONTH('HUADIBI','TDA_BH_DOWNPAYMENT_DAY',vd_currentMonth,vd_currentMonth,'YYYYMM','PART_');
    vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(vn_tmplogid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',null,sysdate,vn_tmpcount,'TRUNCATE成功!',1);  
  
  
  -- INSERT INTO
    vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(-1,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',SYSDATE,NULL,0,'插入'||to_char(vd_currentMonth,'YYYYMM')||'数据到表TDA_BH_DOWNPAYMENT_DAY',NULL);

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