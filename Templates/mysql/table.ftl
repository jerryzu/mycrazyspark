<#list tablelist as t>
drop table ${t.schemaName}.${t.tableName};
create table ${t.schemaName}.${t.tableName}(
  <#list t.fieldlist as Field>
<#if Field.dataType?upper_case == "datetime" ||Field.dataType == "longtext" || Field.dataType == "timestamp"  || Field.dataType == "smallint">${Field.fieldName} ${Field.dataType} comment  '${Field.fieldDesc!""}' <#if Field_has_next>,</#if>
<#elseif (Field.dataType == "varchar" ||Field.dataType == "CHAR")>${Field.fieldName} ${Field.dataType}(${Field.fieldLen!0}<#if (Field.charUsed!"B") == "C"> CHAR</#if>)   comment  '${Field.fieldDesc!""}' <#if Field_has_next>,</#if>
<#elseif (Field.dataType == "float")>${Field.fieldName} ${Field.dataType}   comment  '${Field.fieldDesc!""}' <#if Field_has_next>,</#if>
<#elseif Field.dataType == "numeric">
<#if (Field.precision_ == 0) || (Field.scale_ == 0)> 
${Field.fieldName} ${Field.dataType}<#if Field_has_next>,</#if>
<#elseif Field.scale_ == 0>			
${Field.fieldName} ${Field.dataType}(${Field.precision_})  comment  '${Field.fieldDesc!""}' <#if Field_has_next>,</#if>
<#else>			
${Field.fieldName} ${Field.dataType}(${Field.precision_},${Field.scale_})   comment  '${Field.fieldDesc!""}' <#if Field_has_next>,</#if>
</#if>
<#else>
${Field.fieldName}  ${Field.dataType}(${Field.fieldLen!0})   comment  '${Field.fieldDesc!""}' <#if Field_has_next>,</#if> 
</#if> 	
</#list>
) <#if t.partition??>
<#if t.partition.partType == "range">partition by range ("${t.partField}") (
  <#list t.partition.partlist as pl>
    partition "PART_${pl.partDate?string("yyyyMM")}"  values less than (to_date('${pl.nextDay?string("yyyyMMdd")}', 'YYYYMMDD')),
	</#list>
    partition "part_def"  values less than (maxvalue)
)<#elseif t.partition.partType == "range-day">partition by range ("${t.partField}") (
  <#list t.partition.partlist as pl>
    partition "part_${pl.partDate?string("yyyyMMdd")}"  values less than (to_date('${pl.nextDay?string("yyyyMMdd")}', 'YYYYMMDD')),
	</#list>
    partition "part_def"  values less than (maxvalue)
)<#elseif t.partition.partType == "list">partition by list ("${t.partField}") (
  <#list t.partition.partlist as pl>
    partition "part_${pl.partDate?string("yyyyMM")}"  values ('${pl.partDate?string("yyyyMMdd")}'), 	
  </#list>
    partition "part_def"  values (default) 
) 
</#if>  
</#if> comment='${t.tableDesc!""}';


</#list>
