
  CREATE OR REPLACE PROCEDURE "HUADIBI"."PRC_BH_TDA_STOCK_GOODS" (ps_batch varchar2, pd_startdate date, pd_enddate date) is
/**
 * 业务: 整合层_百货门店商品库存信息
 * 名称: 整合层_百货门店商品库存信息
 * 开发者: 祖新合
 * DATE: 20120925
 * 异常处理: 继续/
 *
 * 运行规则: 每天运行计算本月截止当前时间的数据
 * ================================运行样例===================================
 * EXEC PRC_BH_TDA_STOCK_GOODS('20100801125959','20100701','20100731');  --计算截止上月数据
 *              ps_batch       批次号（时间 精确至秒）
 *              pd_startdate   计算开始时间
 *              pd_enddate     计算结束时间
 * 更新机制 全量（2010-01 开始至 上月）
 * =================================相关表====================================
 * 依赖过程
          HUADIBI.PRC_BH_TDA_STOCK_BATH
 * 数据源
          BFBHDD8.TH_BH_SPJXCR 库存 百货商品进销存库存表
          BFBHDD8.TH_BH_SPJXCPCR 库存 百货商品进销存批次库存表
 * 结果表
          HUADIBI.TDA_BH_STOCK_GOODS_BATH_MONTH  库存 百货商品进销存批次库存表
 * =================================更新历史====================================
 *  创建时间        开发人员        动作        修改原因
 *  20120925       卢肖          创建
 */
  -- 标准模板定义变量
  vs_prcOwner varchar2(60);
  vs_prcName varchar2(60);
  vs_etlDesc varchar2(300);
  vn_errorcode number;
  vs_errorMsg varchar2(2000);
  vn_etlRunId number(20) := -1;
  vn_logid number(20) := -1;
  vn_tmplogid number(20) := -1;
  vs_prcParam varchar2(100);
  vn_tmpcount number(20) := -1;
  vn_rowcount number(20) := -1;
  
  vd_startdate date;
  vd_startMonth date;
  vd_enddate date;
  vd_endMonth date;
  vd_endLastday date;
  vs_startdate varchar2(20);
  vs_startMonth varchar2(10);
  vs_enddate varchar2(20);
  vs_endMonth varchar2(10);
  vs_endLastday varchar2(20);
  -- 开发人员定义变量
  vd_currentMonth date;
  vd_currentMonthLast date;
  
begin
  vs_prcOwner := 'HUADIBI';
  vs_prcName := 'PRC_BH_TDA_STOCK_GOODS';
  vs_etlDesc := '整合层_百货门店商品库存信息(整合层_百货门店商品库存信息)';
  vd_startdate := trunc(pd_startdate,'MM');
  vd_startMonth := trunc(pd_startdate,'MM');
  vd_enddate := to_date(to_char(pd_enddate,'YYYYMMDD')||' 23:59:59','YYYYMMDD hh24:mi:ss');
  vd_endMonth := trunc(vd_enddate,'MM');
  vd_endLastday := last_day(vd_enddate);
  vs_startdate := to_char(vd_startdate,'YYYYMMDD');
  vs_startMonth := to_char(vd_startdate,'YYYYMM');
  vs_enddate := to_char(vd_enddate,'YYYYMMDD');
  vs_endMonth := to_char(vd_enddate,'YYYYMM');
  vs_endLastday := to_char(vd_endLastday,'YYYYMMDD');
  vs_prcParam := ps_batch||','||vs_startdate||','||vs_enddate;

  -- 模板过程开始的日志记录和ETL状态记录
  vn_logid := TOOL_UTIL.FN_ETL_LOG_INFO(vn_logid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',sysdate,null,0,vs_etlDesc,null);
  vn_etlRunId := TOOL_UTIL.FN_ETL_PRC_START(vs_prcOwner, vs_prcName, vs_prcParam, ps_batch, vd_enddate, vs_etlDesc);
	
  vd_currentMonth := vd_startMonth;
  WHILE vd_currentMonth <= vd_endMonth LOOP
  BEGIN -- 计算库存 百货商品进销存批次库存表
    -- 删除历史数据TDA_BH_STOCK_GOODS_BATH_MONTH的ALL历史数据
    -- TRUNCATE TABLE PART FIELD(D_EVENTMONTH) 超过1个月(分区)
    vd_currentMonthLast := last_day(vd_currentMonth);
    if(vd_currentMonthLast > vd_enddate) then vd_currentMonthLast := vd_enddate; end if;
    vd_currentMonthLast := com_util.FN_DATE_DAY_ENDTIME(vd_currentMonthLast);
    
    vn_tmplogid := -1;
    SELECT COUNT(1) INTO vn_tmpcount FROM TDA_BH_STOCK_GOODS_BATH_MONTH WHERE D_EVENTMONTH = to_char(vd_currentMonth,'YYYYMM');
    vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(-1,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',sysdate,null,0,'TRUNCATE TDA_BH_STOCK_GOODS_BATH_MONTH 的分区'||to_char(vd_currentMonth,'YYYYMM'),null);
    COM_UTIL.PRC_SQL_TRUNCATE_MONTH('HUADIBI','TDA_BH_STOCK_GOODS_BATH_MONTH',vd_currentMonth,vd_currentMonth,'YYYYMM','PART_');
    vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(vn_tmplogid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',null,sysdate,vn_tmpcount,'TRUNCATE成功!',1);

    -- 插入数据TDA_BH_STOCK_GOODS_BATH_MONTH的ALL数据
    vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(-1,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',sysdate,null,0,'插入'||to_char(vd_currentMonth,'YYYYMM')||'数据到表TDA_BH_STOCK_GOODS_BATH_MONTH',null);
    INSERT /*+ APPEND */ INTO TDA_BH_STOCK_GOODS_BATH_MONTH(
      D_BATCH_ID  --库存批次号
      ,D_BRAND_HOUSE_CODE  --厅房业务编码
      ,D_BRAND_HOUSE_ID  --厅房ID
      ,D_CATEGORY3_ID  --百货商品分类
      ,D_DM_CATEGORYID  --集市分类ID
      ,D_DM_DEPID  --集市统一部门ID
      ,D_DM_SHOPID  --集市统一门店ID
      ,D_ETL_DATE  --ETL处理时间
      ,D_EVENTMONTH  --库存日期
      ,D_EVENTDATE  --库存日期
      ,D_GOODS_ID  --系统商品ID
      ,D_HSFS  --核算方式
      ,D_ID  --标识号
      ,F_JCJE  --结存金额
      ,F_JCSL  --结存数量
      ,D_JHDJ  --进货单价
      ,D_OTHER1  --维度预留字段
      ,F_OTHER1  --指标预留字段
      ,D_OTHER2  --维度预留字段2
      ,F_OTHER2  --指标预留字段2
      ,D_SHOP_ID  --门店ID
      ,D_VENDER_ID  --供应商编码
    )
    SELECT  
      d.KCPCH  --库存批次号
      ,NVL(l.D_BRAND_HOUSE_CODE, '-1')  --厅房业务编码
     -- ,m.DEPTID  --部门代码
      ,NVL(l.BRAND_HOUSE_ID, -1) --进货部门(更新201210-29)
      ,NVL(l.CATEGORY3_CODE, -1)  --百货商品分类
      ,NVL(l.D_DM_CATEGORY2_ID, -1)  --集市分类ID
      ,NVL(l.D_DM_DEPID, -1)  --集市统一部门ID
      ,NVL(l.D_DM_SHOPID, -1)  --集市统一门店ID
      ,SYSDATE  --ETL处理时间
      ,TO_CHAR(m.RQ, 'YYYYMM')  --日期
      ,m.RQ
      ,m.SP_ID  --商品码
      ,d.HSFS  --核算方式
      ,D.ID  --标识号
      ,D.JCJE  --结存金额
      ,d.JCSL  --结存数量
      ,d.JHDJ  --进货单价
      ,NULL  --维度预留字段
      ,NULL  --指标预留字段
      ,NULL  --维度预留字段2
      ,NULL  --指标预留字段2
      ,d.MDID  --门店代码
      ,0--m.  --供应商编码
    FROM (SELECT m.MDID, TRUNC(m.RQ,'MM'), MAX(m.RQ) RQ
        FROM TB_BH_SPJXCR M
          --INNER JOIN TB_BH_SPJXCPCR d
            --ON m.ID = d.ID AND m.mdid = d.mdid    
        WHERE m.RQ BETWEEN vd_currentMonth AND vd_currentMonthLast
        GROUP BY m.MDID, TRUNC(m.RQ,'MM')
      ) v
     INNER JOIN TB_BH_SPJXCR m ON m.MDID = v.MDID AND m.RQ = v.RQ  
     --  原测试只有表头没有表身，但速度很慢，资询张恩贵，应是数据未传输造成，现关闭，关闭未测试
     INNER JOIN TB_BH_SPJXCPCR d ON M.ID = D.ID AND M.MDID = D.MDID
      /**********增加的处理是，只算HSFS=0的库存数量，*************/  
     LEFT JOIN TD_BH_DEPTTREE_MAPPING mp
        ON m.DEPTID = mp.D_DEPT_ID AND m.MDID = mp.D_SHOP_ID
     LEFT JOIN TD_BH_SHOP_BRAND_HOUSE  l
        ON mp.D_BRAND_HOUSE_ID = l.BRAND_HOUSE_ID  AND mp.D_SHOP_ID = l.SHOP_ID
     INNER JOIN TD_BH_GOODS_INFO G
        ON m.MDID = g.SHOP_ID AND m.SP_ID = g.GOODS_ID AND g.HSFS = 0 ;  

    vn_tmpcount := sql%rowcount;
    vn_rowcount := vn_rowcount+vn_tmpcount;
    commit;
    vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(vn_tmplogid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',null,sysdate,vn_tmpcount,'插入成功!',1);

    vd_currentMonth := trunc(add_months(vd_currentMonth,1),'MM');
  END;
  END LOOP;