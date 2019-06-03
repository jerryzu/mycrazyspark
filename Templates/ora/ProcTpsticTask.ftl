<#list procedurelist as p>
CREATE OR REPLACE PROCEDURE "${p.schemaName}"."${p.procName}"(v_mark IN VARCHAR2, t_start_tm IN VARCHAR2, t_end_tm IN VARCHAR2) IS
/**
  EXEC "${p.schemaName}"."${p.procName}"('1','${p.debug.startDate?string("yyyyMMdd")}','${p.debug.endDate?string("yyyyMMdd")}'); 
  v_mark  1:月报；2：季报；3：半年报
  t_start_tm
  t_end_tm
*/
  -- 标准模板定义变量
  v_task_start_date DATE;
  v_task_end_date   DATE;
  v_dpt_sql         VARCHAR2(4000) := '';
  v_dpt_code		VARCHAR2(200) := '';
  v_sql_code        INT;
  v_sql_msg         VARCHAR2(4000) := '';
  cursor cur_dpt is select c_dpt_code, c_dpt_sql from sys_map_report_dpt;
BEGIN
  SELECT SYSDATE INTO v_task_start_date FROM dual;
  SELECT SYSDATE INTO v_task_end_date   FROM dual;

  v_sql_msg := 'step 1.1:执行调度过程${p.procName}';
 
  for currec in cur_dpt loop
    v_dpt_code := currec.c_dpt_code;
    v_dpt_sql := currec.c_dpt_sql;
    <#list p.dependProcedurelist as d>
      -- ${d.remark!""}-${d.remark!""}
      ${d.dependencyName}(v_mark, v_dpt_sql, t_start_tm, t_end_tm);
    </#list>
  end loop;
  
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