<#list fieldlist as f>  
--alter table ${f.schemaName}.${f.tableName} drop column ${f.fieldName}; <#rt>

alter table ${f.schemaName}.${f.tableName} ADD(${f.fieldName} <#rt>
<#if f.dataType?upper_case == "DATE" || f.dataType?upper_case == "TIMESTAMP"  || f.dataType?upper_case == "SMALLINT">
${f.dataType}<#rt> 
<#elseif (f.dataType?upper_case == "VARCHAR" ||f.dataType?upper_case == "VARCHAR2" ||f.dataType?upper_case == "CHAR")>
${f.dataType}(${f.fieldLen!0}<#if (f.charUsed!"B") == "C"> CHAR</#if>)<#rt> 
<#elseif (f.dataType?upper_case == "FLOAT")>
${f.dataType}<#rt> 
<#elseif f.dataType?upper_case == "DECIMAL">
<#if (f.precision_ == 0) || (f.scale_ == 0)> 
${f.dataType}<#rt> 
<#elseif f.scale_ == 0>
${f.dataType}(${f.precision_})<#rt>
<#else>
${f.dataType}(${f.precision_},${f.scale_})<#rt> 
</#if>
</#if> comment '${f.remark!""}');	
</#list>
<#list fieldlist as f>
</#list>
