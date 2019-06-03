  CREATE OR REPLACE PROCEDURE "HUADIBI"."PRC_BH_TDA_SALE_DEPT_DAY" (ps_batch varchar2, pd_startdate date, pd_enddate date) is
/**
 * 业务: 整合层_百货厅房销售数据
 * 名称: 整合层_百货厅房销售数据
 * 开发者: 祖新合
 * DATE: 20120925
 * 异常处理: 继续/
 *
 * 运行规则: 每天运行计算本月截止当前时间的数据
 * ================================运行样例===================================
 * EXEC PRC_BH_TDA_SALE_DEPT_DAY('20100801125959','20100701','20100731');  --计算截止上月数据
 *              ps_batch       批次号（时间 精确至秒）
 *              pd_startdate   计算开始时间
 *              pd_enddate     计算结束时间
 * 更新机制 全量（2010-01 开始至 上月）
 * =================================相关表====================================
 * 依赖过程
 * 数据源
          HUADIBI.TD_BH_CONTRACT_ASSOCIATE_JSKL 基础信息 百货门店联营合同结算扣率信息
          BFBHDD8.TH_BH_GHDWJXCR 交易 百货供货单位进销存日数据
 * 结果表
          HUADIBI.TDA_BH_SALE_DEPT_DAY  交易 百货供货单位进销存日数据
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
  vd_enddateMonth DATE;
  vd_currentMonth DATE;
  vd_currentMonthLast date;  
begin
  vs_prcOwner:='HUADIBI';
  vs_prcName:='PRC_BH_TDA_SALE_DEPT_DAY';
  vs_etlDesc:='整合层_百货厅房销售数据(整合层_百货厅房销售数据)';
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
  vd_enddateMonth := trunc(vd_enddate,'MM');
  vd_currentMonth := trunc(vd_startdate,'MM');
  
  WHILE vd_currentMonth <= vd_enddateMonth LOOP
  BEGIN -- 计算交易 百货供货单位进销存日数据
  -- 删除历史数据TS_BH_SALE_DEPT_DAY的ALL历史数据
  -- TRUNCATE TABLE PART FIELD(D_EVENTDATE) 超过1个月(分区)
    vd_currentMonthLast := last_day(vd_currentMonth);
    if(vd_currentMonthLast > vd_enddate) then vd_currentMonthLast := vd_enddate; end if;
    vd_currentMonthLast := com_util.FN_DATE_DAY_ENDTIME(vd_currentMonthLast);
  
  vn_tmplogid := -1;
  SELECT COUNT(1) INTO vn_tmpcount FROM TDA_BH_SALE_DEPT_DAY WHERE D_RQ BETWEEN vd_currentMonth AND last_day(vd_currentMonth);
  
  vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(-1,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',sysdate,null,0,'TRUNCATE TDA_BH_SALE_DEPT_DAY 的分区'||to_char(vd_currentMonth,'YYYYMM'),null);
  COM_UTIL.PRC_SQL_TRUNCATE_MONTH('HUADIBI','TDA_BH_SALE_DEPT_DAY',vd_currentMonth,vd_currentMonth,'YYYYMM','PART_');
  
  vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(vn_tmplogid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',null,sysdate,vn_tmpcount,'TRUNCATE成功!',1);  
  
  vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(-1,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',SYSDATE,NULL,0,'插入'||to_char(vd_currentMonth,'YYYYMM')||'数据到表TDA_BH_SALE_DEPT_DAY',NULL);
 
  -- 插入数据TDA_BH_SALE_DEPT_DAY的ALL数据
  vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(-1,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',sysdate,null,0,'插入ALL数据到表TDA_BH_SALE_DEPT_DAY',null);
  INSERT /*+ APPEND */ INTO TDA_BH_SALE_DEPT_DAY(
    F_BJZRJE_JJ,  --变价转入金额（进价）
    D_BRAND_HOUSE_CODE,  --厅房业务编码
    D_BRAND_HOUSE_ID,  --厅房ID
    D_BRAND_HOUSE_ID_JH,  --进货部门
    D_BRAND_ID,  --百货厅房品牌ID
    F_BRJE_JJ,  --拨入金额（进价）
    F_BSJE_JJ,  --报损金额（进价）
    D_CATEGORY3_ID,  --百货商品分类
    F_CJFTJE,  --厂家分摊金额
    D_CONTRACT_NO,  --合同号
    D_DM_CATEGORYID,  --集市分类ID
    D_DM_DEPID,  --集市统一部门ID
    D_DM_SHOPID,  --集市统一门店ID
    D_ETL_DATE,  --ETL处理时间
    F_FQJE,  --发券金额
    D_HSFS,  --百货核算方式: [0:经销，1:联营，2:代销，3:租赁]，除经销为0是正确以外，其他仅作参考
    D_HTH_MAIN_DEDUCTION,  --百货结算主扣率
    F_JCJE_HS,  --结存金额含税
    F_JCJE_JJ,  --结存金额（进价）
    F_JHJE_JJ,  --进货金额
    F_JSJE,  --结算金额
    D_JZBJ,  --记账标记
    F_OTHER1,  --指标预留字段
    D_OTHER1,  --维度预留字段
    D_OTHER2,  --维度预留字段2
    F_OTHER2,  --指标预留字段2
    F_QT_ZKFT,  --其它折扣分摊
    D_RQ,  --日期
    D_SHOP_ID,  --门店ID
    F_SJ,  --税金
    F_SJ_13,  --税金13
    F_SJ_17,  --税金17
    F_THJE_JJ,  --退货金额（进价）
    F_TZCB,  --调帐成本
    D_VENDER_ID,  --供应商编码
    F_WRZFQJE,  --未入帐发券金额
    F_WRZXSCB,  --未入账销售成本
    F_WRZXSJE,  --未入帐销售金额
    F_WRZYHJE,  --未入账优惠金额
    F_XSCB,  --销售成本
    F_XSCB_HS,  --销售成本(含税)
    F_XSJE,  --销售金额
    F_XSJE_BHS,  --不含税销售金额
    F_YHJE,  --优惠金额
    F_ZKJE  --折扣金额
  )
  SELECT
    m.BJZRJE_JJ  --变价转入金额（进价）
    ,NVL(l.D_BRAND_HOUSE_CODE, '-1')  --厅房业务编码
    ,NVL(l.BRAND_HOUSE_ID, -1)  --部门代码
    ,m.DEPTID_JH  --进货部门
    ,NVL(l.BRAND_CODE, '-1') --百货厅房品牌ID
    ,m.BRJE_JJ  --拨入金额（进价）
    ,m.BSJE_JJ  --报损金额（进价）
    ,NVL(l.CATEGORY3_CODE, '-1')  --百货商品分类
    ,m.CJFTJE  --厂家分摊金额
    ,m.HTH  --合同号
    ,NVL(l.D_DM_CATEGORY2_ID, -1)  --集市分类ID
    ,NVL(l.D_DM_DEPID, -1)  --集市统一部门ID
    ,NVL(l.D_DM_SHOPID, -1)  --集市统一门店ID
    ,SYSDATE  --ETL处理时间
    ,m.FQJE  --发券金额
    ,m.HSFS  --百货核算方式: [0:经销，1:联营，2:代销，3:租赁]，除经销为0是正确以外，其他仅作参考
    ,d.D_HTH_DEDUCTION  --百货结算扣率代码: [0:主扣率]
    ,m.JCJE_HS  --结存金额含税
    ,m.JCJE_JJ  --结存金额（进价）
    ,m.JHJE_JJ  --进货金额
    ,m.JSJE  --结算金额
    ,m.JZBJ  --记账标记
    ,NULL  --维度预留字段
    ,NULL  --维度预留字段2
    ,NULL  --指标预留字段
    ,NULL  --指标预留字段2
    ,m.QT_ZKFT  --其它折扣分摊
    ,m.RQ  --日期
    ,NVL(l.SHOP_ID, -1)  --门店ID
    ,m.SJ  --税金
    ,m.SJ_13  --税金13
    ,m.SJ_17  --税金17
    ,m.THJE_JJ  --退货金额（进价）
    ,m.TZCB  --调帐成本
    ,m.CODE  --往来单位代码
    ,m.WRZFQJE  --未入帐发券金额
    ,m.WRZXSCB  --未入账销售成本
    ,m.WRZXSJE  --未入帐销售金额
    ,m.WRZYHJE  --未入账优惠金额
    ,m.XSCB  --销售成本
    ,m.XSCB_HS  --销售成本(含税)
    ,m.XSJE  --销售金额
    ,m.XSJE_BHS  --不含税销售金额
    ,M.YHJE  --优惠金额
    ,M.ZKJE  --折扣金额
  FROM TB_BH_GHDWJXCR	 m 
    LEFT JOIN TD_BH_DEPTTREE_MAPPING mp
      ON m.DEPTID = mp.D_DEPT_ID 
    LEFT JOIN TD_BH_SHOP_BRAND_HOUSE	l
      ON mp.D_BRAND_HOUSE_ID = l.BRAND_HOUSE_ID AND mp.D_SHOP_ID = l.SHOP_ID
    LEFT JOIN TD_BH_CONTRACT_ASSOCIATE_JSKL	d
      ON l.SHOP_ID = d.SHOP_ID AND d.CODE = 0
        AND m.HTH = d.CONTRACT_NO 
  WHERE m.RQ BETWEEN vd_currentMonth AND vd_currentMonthLast;

    vn_tmpcount := sql%rowcount;
    vn_rowcount := vn_rowcount+vn_tmpcount;
    commit;
    vn_tmplogid := TOOL_UTIL.FN_ETL_LOG_DEBUG(vn_tmplogid,vs_prcOwner,vs_prcName,vs_prcParam,ps_batch,'exec',null,sysdate,vn_tmpcount,'插入成功!',1);

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