<#list fieldlist as f>  
--ALTER TABLE ${f.schemaName}.${f.tableName} DROP COLUMN ${f.fieldName}; <#rt>

ALTER TABLE ${f.schemaName}.${f.tableName} ADD(${f.fieldName} <#rt>
<#if f.dataType?upper_case == "DATE" || f.dataType == "TIMESTAMP"  || f.dataType == "SMALLINT">
	${f.dataType}<#rt> 
<#elseif (f.dataType == "VARCHAR" ||f.dataType == "VARCHAR2" ||f.dataType == "CHAR")>
	${f.dataType}(${f.fieldLen!0}<#if (f.charUsed!"B") == "C"> CHAR</#if>)<#rt> 
<#elseif (f.dataType == "FLOAT")>
	${f.dataType}<#rt> 
<#elseif f.dataType == "NUMBER">
	<#if (f.precision_ == 0) || (f.scale_ == 0)> 
		${f.dataType}<#rt> 
	<#elseif f.scale_ == 0>
		${f.dataType}(${f.precision_})<#rt>
	<#else>
		${f.dataType}(${f.precision_},${f.scale_})<#rt> 
	</#if>
</#if>); 	
</#list>

<#list fieldlist as f>
COMMENT ON COLUMN ${f.schemaName}.${f.tableName}.${f.fieldName} IS '${f.remark!""}';
</#list>