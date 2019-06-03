<#list dblinklist as s>
DROP DATABASE LINK ${s.dbLinkName};
CREATE DATABASE LINK ${s.dbLinkName}　 CONNECT TO ${s.userName} IDENTIFIED BY ${s.password} USING 
	'(DESCRIPTION = (ADDRESS_LIST =(ADDRESS = (PROTOCOL = TCP)(HOST = ${s.host} )(PORT = ${s.port})))(CONNECT_DATA =(SID = ${s.SID})))';
</#list> 