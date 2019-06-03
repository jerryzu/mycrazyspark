<#list sequencelist as s>
CREATE SEQUENCE ${s.schemaName}.${s.sequenceName} MINVALUE ${s.minValue} MAXVALUE ${s.maxValue} INCREMENT BY ${s.increment} START WITH ${s.start}<#t>
<#--
<#if ((s.cacheType!false) == false)> NO </#if><#t>CACHE 20
<#if ((s.cycleType!false) == false)> NO</#if><#lt> CYCLE
ORDER
</#--><#lt>
;
</#list>
