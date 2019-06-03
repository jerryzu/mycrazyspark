create or replace PROCEDURE            "PRC_DW_MERGE_DELETEBYETL" (ps_batch varchar2, pd_startdate date, pd_enddate date) is
/**
 * 业务: 数据仓库层--整合--根据日志删除记录
 * 名称: 数据仓库层--整合--根据日志删除记录
 * 开发者: 
 * DATE: 2016-03-04 15:11:13
 * 异常处理: 继续/
 *
 * 运行规则: 每天运行计算本月截止当前时间的数据
 * ================================运行样例===================================
 * EXEC "RTDB_DWH"."PRC_DW_MERGE_DELETEBYETL"('Debug-20160304151112','20160304','20160304');  --计算截止上月数据
 *              ps_batch       批次号（时间 精确至秒）
 *              pd_startdate   计算开始时间
 *              pd_enddate     计算结束时间
 * 更新机制 全量（2010-01 开始至 上月）
 * =================================相关表====================================
 * 依赖过程
 * 数据源
 * 结果表
 * =================================更新历史====================================
 *  创建时间	开发人员	动作	修改原因
 *  20160304		创建
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
  --按ROWID排序的Cursor，删除条件是XXX=XXXX，根据实际情况来定。
  <#list procedurelist as p>
  cursor cur_${p.procName?substring(4)} is select  m.ROWID ROWNO, l.OP_TIME  
    from ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "} m
   		inner join ${p.masterKeyMappinglist[0].sourceTableOwner!" "}.${p.masterKeyMappinglist[0].sourceTableName!" "}_ETL l
   		  on <#list p.masterKeyMappinglist as FieldProp>M.${FieldProp.targetFieldName!" "} = l.${FieldProp.sourceFieldName!" "} <#if FieldProp_has_next> and <#else>  AND m.OP_TIME < l.OP_TIME </#if><#rt>
		</#list>
    order by m.rowid;  
  </#list>

  TYPE record_entity IS RECORD(
	ROWNO ROWID,
	OP_TIME DATE
  );

  TYPE entityList IS TABLE OF record_entity;

  entities entityList; 
begin
  vs_prcOwner:='RTDB_DWH';
  vs_prcName:='PRC_DW_MERGE_DELETEBYETL';
  vs_etlDesc:='数据仓库层--整合--根据日志删除记录';
  vd_startdate:=pd_startdate;
  vd_enddate:=pd_enddate;
  vs_startdate:=to_char(vd_startdate,'YYYY-MM-DD hh24:mi:ss');
  vs_enddate:=to_char(vd_enddate,'YYYY-MM-DD hh24:mi:ss');
  vs_prcParam:=ps_batch||','||vs_startdate||','||vs_enddate;

  -- 模板过程开始的日志记录和ETL状态记录
  vn_logid := TOOL_UTIL.FN_ETL_LOG_INFO(vn_logid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',sysdate,null,0,vs_etlDesc,null);
  vn_etlRunId := TOOL_UTIL.FN_ETL_PRC_START(vs_prcOwner, vs_prcName, vs_prcParam, ps_batch, vd_enddate, vs_etlDesc);

  <#list procedurelist as p>
  VN_TMPLOGID := TOOL_UTIL.FN_ETL_LOG_DEBUG(-1,VS_PRCOWNER,VS_PRCNAME,VS_PRCPARAM,PS_BATCH,'exec',SYSDATE,NULL,0,'同步日志删除记录:${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "}',1);
  vn_tmpcount := 0;
  open cur_${p.procName?substring(4)};
  loop     
    fetch   cur_${p.procName?substring(4)} bulk collect into entities  limit 5000;--每次处理5000行，也就是每5000行一提交
    exit when entities.count=0;
    forall i in entities.first..entities.last
    <#if p.logSyncWay == "DELETE">
      delete from ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "}  
      where rowid=entities(i).rowno; 
	<#elseif p.logSyncWay == "SCD">
      update ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "}  set D_DELETE_DATE = entities(i).OP_TIME
      where rowid=entities(i).rowno; 
	</#if>
    vn_tmpcount := vn_tmpcount + sql%rowcount;  
    commit;
  end loop;
  close cur_${p.procName?substring(4)};  

  vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(vn_tmplogid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',null,sysdate,vn_tmpcount,'删除记录操作成功!',1);
  </#list>
       
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