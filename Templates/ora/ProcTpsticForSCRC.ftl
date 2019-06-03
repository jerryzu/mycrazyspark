<#list procedurelist as p>
CREATE OR REPLACE PROCEDURE "${p.schemaName}"."${p.procName}"(v_mark IN VARCHAR2, v_Report_Code IN VARCHAR2, v_dpt_sql IN VARCHAR2, t_start_tm IN VARCHAR2, t_end_tm IN VARCHAR2) IS
/**
  EXEC "${p.schemaName}"."${p.procName}"('1', '000220999999', ' AND C_DPT_CDE LIKE ''99%''', '${p.debug.startDate?string("yyyyMMdd")}', '${p.debug.endDate?string("yyyyMMdd")}'); 
  v_mark  1:月报；2：季报；3：半年报
  t_start_tm
  t_end_tm
*/
  -- 标准模板定义变量
  v_task_start_date DATE;
  v_task_end_date   DATE;
  v_sql_code        NUMBER;
  v_sql_msg         VARCHAR2(4000) := '';
BEGIN
  SELECT SYSDATE INTO v_task_start_date FROM dual;
  SELECT SYSDATE INTO v_task_end_date   FROM dual;
  --v_sql_msg:='step 0.1:删除${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "}表数据';
  --EXECUTE IMMEDIATE 'truncate table ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "}';

  v_sql_msg:='step 0.1:删除${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "}表,机构:['|| v_Report_Code ||'] 的数据';
  EXECUTE IMMEDIATE 'delete from ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "} where C_DPT_CODE = :1 ' using v_Report_Code;
  COMMIT;

  v_sql_msg := 'step 1.2:插入${p.procName}表中';
  INSERT INTO ${p.dependFieldMappinglist[0].targetTableOwner!" "}.${p.dependFieldMappinglist[0].targetTableName!" "}(
  <#list p.dependFieldMappinglist as FieldProp>
  <#if FieldProp_index != 0>,</#if><#rt>
  ${FieldProp.targetFieldName!" "} --${FieldProp_index+1} ${FieldProp.targetFieldDesc!" "}<#lt>
  </#list>)
  SELECT 
  <#list p.dependFieldMappinglist as FieldProp>	
  <#if FieldProp_index != 0>,</#if><#rt>
  <#if FieldProp.sourceExpression??>${FieldProp.sourceExpression!" "}<#else>${FieldProp.sourceFieldName!" "}</#if> <#t>		
  --${FieldProp_index+1}	${FieldProp.sourceFieldDesc!" "}<#lt>
  </#list>
  FROM ${p.dependFieldMappinglist[0].sourceTableOwner!" "}.${p.dependFieldMappinglist[0].sourceTableName!" "}
  WHERE <#if p.sourceRefSQLList[0].remark??>${p.sourceRefSQLList[0].remark!" "} 
  	and </#if> ((c_month IS NOT NULL and v_mark='1') or (c_season IS NOT NULL and v_mark='2') or (c_half_year IS NOT NULL and v_mark='3'));
  COMMIT;

  v_sql_code := 0;
  v_sql_msg := 'NORMAL,SUCCESSFUL COMPLETION';
  --写任务日志
  SELECT SYSDATE INTO v_task_end_date FROM dual;
  INSERT INTO load_his_log(sys, jobname, start_date, end_date, run_date, sql_code, sql_state)
  VALUES('REPORT', '${p.procName}', v_task_start_date, v_task_end_date
    ,to_char((v_task_end_date-v_task_start_date)*86400), v_sql_code, v_sql_msg);
  COMMIT;
exception
  WHEN others THEN
    v_sql_code := sqlcode;
    v_sql_msg := v_sql_msg || ' ' || ':' || sqlerrm;
    --任务结束时间
    SELECT SYSDATE INTO v_task_end_date FROM dual;
    ROLLBACK;
    INSERT INTO load_his_log(sys, jobname, start_date, end_date, run_date, sql_code, sql_state)
    VALUES('REPORT', '${p.procName}', v_task_start_date, v_task_end_date
      ,to_char((v_task_end_date - v_task_start_date) * 86400), v_sql_code, v_sql_msg);
  COMMIT;
END;
/
</#list>