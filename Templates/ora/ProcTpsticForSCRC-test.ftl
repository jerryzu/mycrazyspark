<#--
<#list procedurelist as p>
TRUNCATE TABLE ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].sourceTableName!" "};
INSERT INTO  ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].sourceTableName!" "} (N_SEQ, C_CDE, C_NAME, C_SQL, C_MONTH, C_SEASON, C_HALF_YEAR<#if p.dependFieldMappinglist[0].sourceTableName=="WEB_PLY_COUNT_DPT">, C_BULLETIN</#if>)
SELECT N_SEQ, C_CDE, C_NAME, C_SQL, C_MONTH, C_SEASON, C_HALF_YEAR<#if p.dependFieldMappinglist[0].sourceTableName=="WEB_PLY_COUNT_DPT">, C_BULLETIN</#if>
FROM  ${p.dependFieldMappinglist[0].sourceTableOwner!" "}.${p.dependFieldMappinglist[0].sourceFieldRemark!" "};
COMMIT;
</#list>

<#list procedurelist as p>
TRUNCATE TABLE ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "};
</#list>
-->

<#list procedurelist as p>
EXEC "${p.schemaName}"."${p.procDesc}"('1', '20180101', '20181031');  
</#list>
<#list procedurelist as p>
EXEC "${p.schemaName}"."${p.procName}"('1', '000220999999', ' AND C_DPT_CDE LIKE ''99%''', '20180101', '20181031');  
EXEC "${p.schemaName}"."${p.procName}"('1', '000220330000', ' AND C_DPT_CDE LIKE ''99%''', '20180101', '20181031');  
EXEC "${p.schemaName}"."${p.procName}"('1', '000220330400', ' AND C_DPT_CDE LIKE ''9973%''', '20180101', '20181031');  
</#list>


<#list procedurelist as p>
EXEC "${p.schemaName}"."${p.procDesc}"('2', '20180101', '20181031');  
</#list>
<#list procedurelist as p>
EXEC "${p.schemaName}"."${p.procName}"('2', '000220999999', ' AND C_DPT_CDE LIKE ''99%''', '20180101', '20181031');  
EXEC "${p.schemaName}"."${p.procName}"('2', '000220330000', ' AND C_DPT_CDE LIKE ''99%''', '20180101', '20181031');  
EXEC "${p.schemaName}"."${p.procName}"('2', '000220330400', ' AND C_DPT_CDE LIKE ''9973%''', '20180101', '20181031');  
</#list>


<#list procedurelist as p>
EXEC "${p.schemaName}"."${p.procDesc}"('3', '20180101', '20181031');  
</#list>
<#list procedurelist as p>
EXEC "${p.schemaName}"."${p.procName}"('3', '000220999999', ' AND C_DPT_CDE LIKE ''99%''', '20180101', '20181031');  
EXEC "${p.schemaName}"."${p.procName}"('3', '000220330000', ' AND C_DPT_CDE LIKE ''99%''', '20180101', '20181031');  
EXEC "${p.schemaName}"."${p.procName}"('3', '000220330400', ' AND C_DPT_CDE LIKE ''9973%''', '20180101', '20181031');  
</#list>

<#--
WITH VW AS (
<#list procedurelist as p>
--${p.comment!" "} 
SELECT L.N_SEQ, L.C_CDE, L.C_NAME, L.N_NUM L_N_NUM, RA.N_NUM RA_N_NUM, R1.N_NUM R1_N_NUM, R2.N_NUM R2_N_NUM
FROM ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetFieldRemark!" "} L
  FULL JOIN (SELECT * FROM ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "} WHERE C_DPT_CODE = '000220999999')RA
    ON L.N_SEQ = RA.N_SEQ  AND L.C_CDE = RA.C_CDE
  FULL JOIN (SELECT * FROM ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "} WHERE C_DPT_CODE = '000220330000')R1
    ON L.N_SEQ = R1.N_SEQ  AND L.C_CDE = R1.C_CDE
  FULL JOIN (SELECT * FROM ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "} WHERE C_DPT_CODE = '000220330400')R2
    ON L.N_SEQ = R2.N_SEQ  AND L.C_CDE = R2.C_CDE
<#if p_has_next>UNION ALL</#if>
</#list>
)
SELECT *
FROM VW
WHERE  NVL(L_N_NUM,0) <>  NVL(RA_N_NUM,0)	OR NVL(L_N_NUM,0) <> NVL(R1_N_NUM,0)	OR NVL(L_N_NUM,0) <> NVL(R2_N_NUM,0)

-->