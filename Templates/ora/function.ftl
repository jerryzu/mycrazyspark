<#list functionlist as f>
<#if (f.fncID <= 600)>
CREATE OR REPLACE FUNCTION ${f.schemaName}.${f.fncName}(v_c_code VARCHAR2, t_start_tm VARCHAR2, t_end_tm VARCHAR2)
<#else>
CREATE OR REPLACE FUNCTION ${f.schemaName}.${f.fncName}(v_c_code VARCHAR2, v_c_dpt_code VARCHAR2, t_start_tm VARCHAR2, t_end_tm VARCHAR2)
</#if>
  RETURN NUMBER AS
  v_csql VARCHAR2(4000);
  v_number NUMBER;
BEGIN
  v_number:=0;
  SELECT A.c_sql INTO v_csql FROM ${f.remark!" "} A WHERE A.c_cde=v_c_code;
  IF(v_csql IS NULL ) THEN
    v_number:=0;
  elsif(v_csql IS NOT NULL ) THEN<#if (f.fncID >= 600)>
    v_csql :=REPLACE(v_csql,'V_DPT',v_c_dpt_code);
    <#else>
    v_csql :=REPLACE(v_csql,'V_DPT',' ');
    </#if>
    v_csql :=REPLACE(v_csql,'V_S',t_start_tm);
    v_csql :=REPLACE(v_csql,'V_E',t_end_tm);
    EXECUTE IMMEDIATE  v_csql INTO v_number;
  END IF ;
  v_number:=nvl(v_number,0);
  RETURN v_number;
  /*exception
    when others then
      return 0;*/
END;
/
</#list>

