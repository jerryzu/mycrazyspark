<#list sequencelist as s>
 CREATE SEQUENCE  ${s.schemaName}.${s.sequenceName} MINVALUE ${s.minValue} MAXVALUE ${s.maxValue!" "} INCREMENT BY ${s.increment_} START WITH ${s.start_} ;
 /*
 #if s.cacheType> CACHE 20 <#/if
 #if !s.cycleType> NOCYCLE <#/if
 */
</#list> 