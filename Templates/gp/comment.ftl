<#list tablelist as t>
COMMENT ON TABLE ${t.schemaName}.${t.tableName} IS '${t.remark!""}';
<#list t.fieldlist as field>
COMMENT ON COLUMN ${t.schemaName}.${t.tableName}.${field.fieldName} IS '${field.remark!""}';
</#list>

</#list>
