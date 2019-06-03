<#list uselist as user>
-- USER SQL
DROP USER ${user.userName} CASCADE;
CREATE USER ${user.userName} IDENTIFIED BY gemini
DEFAULT TABLESPACE BIZ TEMPORARY TABLESPACE BIZ_TMP
<#if user.isLock == 0> ACCOUNT UNLOCK <#else> ACCOUNT LOCK </#if>;

-- QUOTAS
-- ROLES
GRANT RESOURCE TO ${user.userName} ;
GRANT CONNECT TO ${user.userName} ;
ALTER USER ${user.userName} DEFAULT ROLE RESOURCE,CONNECT;

-- SYSTEM PRIVILEGES
<#list user.userRightlist as userRight>
GRANT ${userRight.rightName} TO ${user.userName};
</#list>

</#list>
