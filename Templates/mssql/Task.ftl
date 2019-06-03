CREATE OR REPLACE PROCEDURE "${p.schemaName}"."${p.procName}" (ps_batch varchar2, pd_startdate date, pd_enddate date) is
/**
 * 业务: ${p.procDesc}
 * 名称: ${p.procDesc} 
 * 开发者: ${p.author}
 * DATE: ${p.createtime?string("yyyy-MM-dd HH:mm:ss")}
 * 异常处理: 继续/
 *
 * 运行规则: 每天运行计算本月截止当前时间的数据
 * ================================运行样例===================================
 * EXEC PRC_BH_MAIN_DAY('${debug.batch}','${debug.startDate?string("yyyyMMdd")}','${debug.endDate?string("yyyyMMdd")}');  --计算截止上月数据
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
 *  ${p.createtime?string("yyyyMMdd")}	${p.author}	创建
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
  vd_endLastday date;
  vs_startdate varchar2(20);
  vs_enddate varchar2(20);
  vs_startMonth varchar2(10);
  vs_endMonth varchar2(10);
  vs_endLastday varchar2(20);
  -- 开发人员定义变量

begin
  vs_prcOwner:='${p.schemaName}';
  vs_prcName:='${p.procName}';
  vs_etlDesc:='${p.procDesc}';
  vd_startdate:=trunc(pd_startdate,'MM');
  vd_enddate:=to_date(to_char(pd_enddate,'YYYYMMDD')||' 23:59:59','YYYYMMDD hh24:mi:ss');
  vd_endLastday:=last_day(vd_enddate);
  vs_startdate:=to_char(vd_startdate,'YYYYMMDD');
  vs_enddate:=to_char(vd_enddate,'YYYYMMDD');
  vs_startMonth:=to_char(vd_startdate,'YYYYMM');
  vs_endMonth:=to_char(vd_enddate,'YYYYMM');
  vs_endLastday:=to_char(vd_endLastday,'YYYYMMDD');
  vs_prcParam:=ps_batch||','||vs_startdate||','||vs_enddate;

  -- 模板过程开始的日志记录和ETL状态记录
  vn_logid := TOOL_UTIL.FN_ETL_LOG_INFO(vn_logid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',sysdate,null,0,vs_etlDesc,null);
  vn_etlRunId := TOOL_UTIL.FN_ETL_PRC_START(vs_prcOwner, vs_prcName, vs_prcParam, ps_batch, vd_enddate, vs_etlDesc);

  -- TO DO IT
  -- 维度同步_百货总调度 维度同步_百货总调度
  PRC_BH_DIM_MAIN_DAY(ps_batch, pd_startdate, pd_enddate);
  -- 基础层_百货总调度 基础层_百货总调度
  PRC_BH_TB_MAIN_DAY(ps_batch, pd_startdate, pd_enddate);
  -- 整合层_百货总调度 整合层_百货总调度
  PRC_BH_TDA_MAIN_DAY(ps_batch, pd_startdate, pd_enddate);
  -- 轻量汇总层_百货总调度 轻量汇总层_百货总调度
  PRC_BH_TS_MAIN_DAY(ps_batch, pd_startdate, pd_enddate);
  -- 汇总层_百货总调度 汇总层_百货总调度
  PRC_BH_TF_MAIN_DAY(ps_batch, pd_startdate, pd_enddate);

  --临时调用统计量信息
  PRC_SYS_CHECK_RECCOUNTS(ps_batch, pd_startdate, pd_enddate, 1, 'BH');

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