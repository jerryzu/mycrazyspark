<#list tablelist as t>
--DROP TABLE ${t.schemaName}.${t.tableName} PURGE;
--alter table ${t.schemaName}.${t.tableName} rename to ${t.schemaName}.${t.tableName}_;
CREATE TABLE ${t.schemaName}.${t.tableName}(
  <#list t.fieldlist as Field>
  <#if Field.dataType?upper_case == "DATE" || Field.dataType == "TIMESTAMP"  || Field.dataType == "SMALLINT">${Field.fieldName} ${Field.dataType}<#if Field_has_next>,</#if>
<#elseif (Field.dataType == "VARCHAR" ||Field.dataType == "VARCHAR2" ||Field.dataType == "CHAR")>${Field.fieldName} ${Field.dataType}(${Field.fieldLen!0}<#if (Field.charUsed!"B") == "C"> CHAR</#if>)<#if Field_has_next>,</#if>
<#elseif (Field.dataType == "FLOAT")>${Field.fieldName} ${Field.dataType}<#if Field_has_next>,</#if>
<#elseif (Field.dataType == "BLOB" ||Field.dataType == "CLOB")>${Field.fieldName} ${Field.dataType}<#if Field_has_next>,</#if>
<#elseif Field.dataType == "NUMBER">
<#if (Field.precision_ == 0) || (Field.scale_ == 0)> 
${Field.fieldName} ${Field.dataType}<#if Field_has_next>,</#if>
<#elseif Field.scale_ == 0>			
${Field.fieldName} ${Field.dataType}(${Field.precision_})<#if Field_has_next>,</#if>
<#else>			
${Field.fieldName} ${Field.dataType}(${Field.precision_},${Field.scale_})<#if Field_has_next>,</#if>
</#if>
</#if> 	
</#list>
) <#if t.partition??>
<#if t.partition.partType == "RANGE">PARTITION BY RANGE ("${t.partField}") (
  <#list t.partition.partlist as pl>
    PARTITION "PART_${pl.partDate?string("yyyyMM")}"  VALUES LESS THAN (TO_DATE('${pl.nextDay?string("yyyyMMdd")}', 'YYYYMMDD')),
	</#list>
    PARTITION "PART_DEF"  VALUES LESS THAN (MAXVALUE)
)<#elseif t.partition.partType == "RANGE-DAY">PARTITION BY RANGE ("${t.partField}") (
  <#list t.partition.partlist as pl>
    PARTITION "PART_${pl.partDate?string("yyyyMMdd")}"  VALUES LESS THAN (TO_DATE('${pl.nextDay?string("yyyyMMdd")}', 'YYYYMMDD')),
	</#list>
    PARTITION "PART_DEF"  VALUES LESS THAN (MAXVALUE)
)<#elseif t.partition.partType == "LIST">PARTITION BY LIST ("${t.partField}") (
  <#list t.partition.partlist as pl>
    PARTITION "PART_${pl.partDate?string("yyyyMM")}"  VALUES ('${pl.partDate?string("yyyyMMdd")}'), 	
  </#list>
    PARTITION "PART_DEF"  VALUES (DEFAULT) 
) 
</#if>  
</#if>  
ENABLE ROW MOVEMENT;

COMMENT ON TABLE ${t.schemaName}.${t.tableName} IS '${t.tableDesc!""}';

<#list t.fieldlist as field>
COMMENT ON COLUMN ${t.schemaName}.${t.tableName}.${field.fieldName} IS '${field.fieldDesc!""}';
</#list>

/
</#list>
