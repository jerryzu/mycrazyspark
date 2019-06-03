<#list synonymlist as s>
 CREATE SYNONYM  ${s.synonymOwner}.${s.synonymName} FOR ${s.tableOwner}.${s.tableName} ;
 GRANT ${s.grantOn} ON ${s.tableOwner}.${s.tableName} TO ${s.synonymOwner} ;
</#list> 