  CREATE OR REPLACE PROCEDURE "HUADIBI"."PRC_BH_TDA_CUSTOMER_NUM" (ps_batch varchar2, pd_startdate date, pd_enddate date) is
/**
 * 业务: 整合层_百货日客流数据
 * 名称: 整合层_百货日客流数据
 * 开发者: 祖新合
 * DATE: 20120925
 * 异常处理: 继续/
 *
 * 运行规则: 每天运行计算本月截止当前时间的数据
 * ================================运行样例===================================
 * EXEC PRC_BH_TDA_CUSTOMER_NUM('20100801125959','20100701','20100731');  --计算截止上月数据
 *              ps_batch       批次号（时间 精确至秒）
 *              pd_startdate   计算开始时间
 *              pd_enddate     计算结束时间
 * 更新机制 全量（2010-01 开始至 上月）
 * =================================相关表====================================
 * 依赖过程
 * 数据源
          BFBHDD8.TH_BH_KL 交易 百货客流量记录
 * 结果表
          HUADIBI.TDA_BH_SALE_CUSTOMER_NUM_DAY  交易 百货客流量记录
 * =================================更新历史====================================
 *  创建时间        开发人员        动作        修改原因
 *  20120925       卢肖          创建
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
  vs_prcOwner:='HUADIBI';
  vs_prcName:='PRC_BH_TDA_CUSTOMER_NUM';
  vs_etlDesc:='整合层_百货日客流数据(整合层_百货日客流数据)';
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
  BEGIN -- 计算交易 百货客流量记录
  -- 删除历史数据TDA_BH_SALE_CUSTOMER_NUM_DAY的ALL历史数据
  -- TRUNCATE TABLE PART FIELD(D_EVENTDATE) 超过1个月(分区)
  vn_tmplogid := -1;
  SELECT COUNT(1) INTO vn_tmpcount FROM TDA_BH_SALE_CUSTOMER_NUM_DAY WHERE D_EVENTDATE BETWEEN vd_startdate AND vd_endLastday;
  vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(-1,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',sysdate,null,0,'TRUNCATE TDA_BH_SALE_CUSTOMER_NUM_DAY 的分区'||vs_startMonth||'-'||vs_endMonth,null);
  COM_UTIL.PRC_SQL_TRUNCATE_MONTH('HUADIBI','TDA_BH_SALE_CUSTOMER_NUM_DAY',vd_startdate,vd_endLastday,'YYYYMM','PART_');
  vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(vn_tmplogid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',null,sysdate,vn_tmpcount,'TRUNCATE成功!',1);

  -- 插入数据TDA_BH_SALE_CUSTOMER_NUM_DAY的ALL数据
  vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(-1,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',sysdate,null,0,'插入ALL数据到表TDA_BH_SALE_CUSTOMER_NUM_DAY',null);

  INSERT /*+ APPEND */ INTO TDA_BH_SALE_CUSTOMER_NUM_DAY(
    D_ETL_DATE  --ETL处理时间
    ,D_EVENTDATE  --记录日期
    ,D_EVENTHOUR  --记录日期时段
    ,D_DM_CATEGORYID  --集市分类ID
    ,D_DM_SHOPID  --集市统一门店ID
    ,D_SHOP_ID  --门店ID
    ,D_OTHER1  --维度预留字段
    ,D_OTHER2  --维度预留字段2
    ,F_CUSTOM_NUM  --客流数量
    ,F_OTHER1  --指标预留字段
    ,F_OTHER2  --指标预留字段2   
  )
  SELECT
    SYSDATE  --ETL处理时间
    ,m.RQ  --记录日期
    ,1
    --,TO_NUMBER(SUBSTR(m.SD,-2))  --记录日期时段
    ,'-9'  ---9
    ,NVL(v.D_DM_SHOPID, -1)  --集市统一门店ID
    ,v.SHOP_ID  --门店代码
    ,NULL  --维度预留字段
    ,NULL  --维度预留字段2
    ,m.INKLL  --客流数量
    ,NULL  --指标预留字段
    ,NULL  --指标预留字段2
  FROM TB_BH_MDKL m
    INNER JOIN TD_BH_SHOP_INFO v
      ON m.SHDM = v.SHOP_ID
  WHERE m.RQ  BETWEEN vs_startdate AND vd_enddate;
      
  vn_tmpcount := sql%rowcount;
  vn_rowcount := vn_rowcount+vn_tmpcount;
  commit;
  vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(vn_tmplogid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',null,sysdate,vn_tmpcount,'插入成功!',1);

  END;