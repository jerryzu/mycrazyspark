<#list tablelist as Table>	
CREATE TABLE ${Table.schemaName}.${Table.tableName}(
<#list Table.fieldlist as Field>
	<#if Field.dataType == "INTEGER">
		${Field.fieldName} ${Field.dataType}<#rt>
	<#elseif Field.dataType == "SMALLINT">
		${Field.fieldName} ${Field.dataType}<#rt> 
	<#elseif (Field.dataType == "DATE" ||Field.dataType == "TIMESTAMP")>
		${Field.fieldName} ${Field.dataType}<#rt>
	<#elseif (Field.dataType == "VARCHAR" ||Field.dataType == "VARCHAR2")>
		${Field.fieldName} ${Field.dataType}(${Field.fieldLen})<#rt>
	<#elseif (Field.dataType == "DECIMAL" ||Field.dataType == "DOUBLE") >
		<#if Field.scale == 0>
		${Field.fieldName} ${Field.dataType}(${Field.precision})<#rt>
		<#else>
		${Field.fieldName} ${Field.dataType}(${Field.precision},${Field.scale})<#rt>
		</#if>
	<#else>
		ERROR
	</#if>	
	<#lt><#if Field_has_next>,</#if>
</#list>
)<#rt>
<#if Table.partition.partType == "RANGE">  
PARTITION BY RANGE ("${Table.partition.partField}") 
	<#list Table.partition.partlist as partval>
	(PARTITION "${Table.partition.prefix}_${partval}"  VALUES LESS THAN (TO_DATE(${partval}, 'SYYYY-MM-DD HH24:MI:SS'))
	</#list>
PARTITION "PART_DEF"  VALUES LESS THAN (MAXVALUE)) 
<#elseif Table.partition.partType == "LIST">
PARTITION BY LIST ("${Table.partition.partField}") 
	<#list Table.partition.partlist as partval>
	(PARTITION "${Table.partition.prefix}_${partval}" VALUES('${partval}')
	</#list>
	PARTITION "${Table.partition.prefix}_DEF" VALUES(DEFAULT)) 
</#if>;

COMMENT ON TABLE ${Table.schemaName}.${Table.tableName}   IS '${Table.remark!" "}';
<#list Table.fieldlist as field>
COMMENT ON COLUMN ${Table.schemaName}.${Table.tableName}.${field.fieldName} IS '${field.remark!" "}';
</#list>

</#list>