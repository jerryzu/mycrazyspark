CREATE TABLE ${t.schemaName}.${t.tableName}(
<#list fieldlist as Field>
	<#if Field.dataType == "INTEGER">
		${Field.fieldName} ${Field.dataType}<#rt>
	<#elseif Field.dataType == "SMALLINT">
		${Field.fieldName} ${Field.dataType}<#rt> 
	<#elseif Field.dataType == "DATE">
		${Field.fieldName} ${Field.dataType}<#rt>
	<#elseif (Field.dataType == "VARCHAR" ||Field.dataType == "VARCHAR2")>
		${Field.fieldName} ${Field.dataType}(${Field.fieldLen})<#rt>
	<#elseif Field.dataType == "NUMBER">
		<#if Field.scale == 0>
			${Field.fieldName} ${Field.dataType}(${Field.precision})<#rt>
		<#else>
			${Field.fieldName} ${Field.dataType}(${Field.precision},${Field.scale})<#rt>
		</#if>
	</#if>	
	<#lt><#if Field_has_next>,</#if>
</#list>
)
<#if t.partition.partType == "RANGE">  
	PARTITION BY RANGE ("${t.partition.partField}") 
	(PARTITION "PART_200712"  VALUES LESS THAN (TO_DATE(' 2008-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'
	PARTITION "PART_DEF"  VALUES LESS THAN (MAXVALUE) ) 
<#elseif t.partition.partType == "LIST">
	PARTITION BY LIST ("${t.partition.partField}") 
	(PARTITION "PART_200801"  VALUES ('200801')
	PARTITION "PART_DEF"  VALUES (DEFAULT) ) 
</#if> 	
;

COMMENT ON TABLE ${t.schemaName}.${t.tableName}   IS '${t.remark}';
<#list fieldlist as field>
COMMENT ON COLUMN ${t.schemaName}.${t.tableName}.${field.fieldName} IS '${field.remark}';
</#list>