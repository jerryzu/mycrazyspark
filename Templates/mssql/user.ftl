<#list uselist as user>
CREATE USER ${user.userName} IDENTIFIED BY ${user.password} 
<#if user.isLock == 0> 
	ACCOUNT UNLOCK 
<#else>
	ACCOUNT LOCK 
</#if>
;

-- QUOTAS

-- ROLES
GRANT RESOURCE TO ${user.userName} ;
GRANT CONNECT TO ${user.userName} ;
GRANT DBA TO ${user.userName} ;
ALTER USER ${user.userName} DEFAULT ROLE RESOURCE,CONNECT,DBA;

-- SYSTEM PRIVILEGES
grant unlimited tablespace to ${user.userName};
</#list>
