<#list triggerlist as t>
CREATE OR REPLACE TRIGGER ${t.triggerOwner}.${t.triggerName}
/**
 * 业务: ${t.triggerDesc!""}
 * 名称: ${t.triggerDesc!""} 
 * 开发者: ${t.author!""}
 * DATE: ${(t.createtime!.now)?string("yyyy-MM-dd HH:mm:ss")}
*/
<#if t.triggerType == "SequencesTrigger">
BEFORE INSERT ON ${t.tableName}
<#elseif t.triggerType == "VerifyTrigger">
AFTER INSERT ON ${t.tableName}
</#if>
FOR EACH ROW
DECLARE
  MSG VARCHAR2(2000);
BEGIN
  <#if t.triggerType == "SequencesTrigger">
  SELECT ${t.sequences!""}.NEXTVAL INTO:NEW.${t.fieldName!""} FROM SYS.DUAL;
  <#elseif t.triggerType == "VerifyTrigger">
  IF :NEW.DW_POLICY_PROP_ID < 0 THEN
	MSG := '新增: ' <#list t.fieldlist as field>
	<#if field.dataType == "DATE" || field.dataType == "TIMESTAMP">
		|| '${field.remark!""}[' || TO_CHAR(:New.${field.fieldName}, 'YYYYMMDD') || ']'
	<#elseif (field.dataType == "VARCHAR" ||field.dataType == "VARCHAR2" ||field.dataType == "CHAR")>
		|| '${field.remark!""}[' || :New.${field.fieldName} || ']' 
	<#elseif (field.dataType == "FLOAT" ||field.dataType == "NUMBER" || field.dataType == "SMALLINT")>
		|| '${field.remark!""}['|| TO_CHAR(:New.${field.fieldName}) || ']'
	</#if> <#if field_has_next>||','<#else> ; </#if><#t>
	</#list>

	INSERT INTO RTDB_DM.TT_TRG_MESSAGE(D_ETL_DATE, SCHEMA_NAME, TABLE_NAME, ERROR_MESSAGE, STATUS)
	VALUES(SYSDATE, '${t.triggerOwner}', '${t.tableName}', MSG, 0);
	END IF;
</#if>
END;
/
</#list>