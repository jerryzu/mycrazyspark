 <#macro DebugBlock procDebug>  
* ================================运行样例===================================
* EXEC PRC_BH_MAIN_DAY('${procDebug.batch}','${procDebug.startDate?string("yyyyMMdd")}','${procDebug.endDate?string("yyyyMMdd")}');  --计算截止上月数据
*              ps_batch       批次号（时间 精确至秒）
*              pd_startdate   计算开始时间
*              pd_enddate     计算结束时间
</#macro>  