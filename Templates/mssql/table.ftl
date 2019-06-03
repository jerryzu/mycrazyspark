<#list tablelist as Table>	
DROP TABLE ${Table.schemaName}.${Table.tableName}
GO

CREATE TABLE ${Table.schemaName}.${Table.tableName}(
<#list Table.fieldlist as Field>
	<#switch Field.dataType?upper_case>
	<#case "INT">	
		${Field.fieldName}  ${Field.dataType}<#rt>
		<#break>
	<#case "SMALLINT">	
		${Field.fieldName}  ${Field.dataType}<#rt>
		<#break>
	<#case "MONEY">	
		${Field.fieldName}  ${Field.dataType}<#rt>
		<#break>
	<#case "DATE">	
		${Field.fieldName} ${Field.dataType}<#rt>
		<#break>
	<#case "TIMESTAMP">	
		${Field.fieldName} ${Field.dataType}<#rt>
		<#break>
	<#case "DATETIME">	
		${Field.fieldName} ${Field.dataType}<#rt>
		<#break>
	<#case "VARCHAR">	
		${Field.fieldName}  ${Field.dataType}(${Field.fieldLen})<#rt>
		<#break>
	<#case "VARCHAR2">	
		${Field.fieldName}  ${Field.dataType}(${Field.fieldLen})<#rt>
		<#break>
	<#case "DECIMAL">	
		<#if Field.scale == 0>
		${Field.fieldName} ${Field.dataType}(${Field.precision})<#rt>
		<#else>
		${Field.fieldName} ${Field.dataType}(${Field.precision},${Field.scale})<#rt>
		</#if>
		<#break>
	<#case "DOUBLE">	
		<#if Field.scale == 0>
		${Field.fieldName} ${Field.dataType}(${Field.precision})<#rt>
		<#else>
		${Field.fieldName} ${Field.dataType}(${Field.precision},${Field.scale})<#rt>
		</#if>
		<#break>
	<#default>
		This will be processed if it is neither
	</#switch>
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
</#if>

GO

EXECUTE sp_addextendedproperty N'MS_Description', N'${Table.remark!" "}', N'SCHEMA', N'${Table.schemaName}', N'TABLE', N'${Table.tableName}', NULL, NULL
GO
<#list Table.fieldlist as Field>

EXECUTE sp_addextendedproperty N'MS_Description', N'${Field.remark!" "}', N'SCHEMA', N'${Table.schemaName}', N'TABLE', N'${Table.tableName}', N'COLUMN', N'${Field.fieldName}'
GO
</#list>

</#list>