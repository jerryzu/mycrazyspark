<#list sequencelist as s>
 CREATE SEQUENCE  ${s.schemaName}.${s.sequenceName} MINVALUE ${s.minValue} MAXVALUE ${s.maxValue!" "} INCREMENT ${s.increment_} START ${s.start_} ;
 /*
 #if s.cacheType> CACHE 20 <#/if
 #if !s.cycleType> NOCYCLE <#/if
 */
</#list> 