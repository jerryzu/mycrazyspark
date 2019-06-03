<#list taskPackagelist as p>
create or replace PACKAGE pkg_${p.packageName}
/*TransformationModelPackage*/
AS
  <#list p.lkupTablelist as lkup>
  TYPE record_${lkup.alias} IS record (
    <#list lkup.fieldlist as f>
      ${f.columnName}	${f.dataType}<#if f_has_next>,</#if>  
    </#list>      
  );      	
  </#list>      
  <#list p.dlvrTablelist as dlvr>
  TYPE record_${dlvr.alias} IS record (
    <#list dlvr.fieldlist as f>
      ${f.columnName}	${f.dataType}<#if f_has_next>,</#if>
    </#list>      
  );      	
  </#list>

  <#list p.lkupTablelist as lkup>      	     
  TYPE table_${lkup.alias} IS TABLE OF record_${lkup.alias};
  </#list>  
  <#list p.dlvrTablelist as dlvr>      	
  TYPE table_${dlvr.alias} IS TABLE OF record_${dlvr.alias};
  </#list>
  
  function f_Extract(v_BeginDate in date,v_EtlType in varchar2) return integer;
  procedure prc_main(v_BeginDate in varchar2,v_EtlType in varchar2);
  <#list p.targetTablelist as target>
  procedure prc_${target.alias}(v_BeginDate in date,v_EtlType in varchar2); 
  </#list>
END pkg_${p.packageName};
/
create or replace PACKAGE BODY pkg_${p.packageName}
AS
  v_PriCtrlDB varchar2(100) := 'IDP_CTRL';
  --设置任务名称参数  
  v_progname VARCHAR2(100);
  <#list p.lkupTablelist as lkup>  
  tbl_${lkup.alias} table_${lkup.alias} := table_${lkup.alias}();
  </#list>
  <#list p.dlvrTablelist as dlvr>  
  tbl_${dlvr.alias} table_${dlvr.alias} := table_${dlvr.alias}();
  </#list>
  
  <#list p.lkupTablelist as lkup>    
  PROCEDURE prc_lkup_model_init_${lkup.alias} IS
    v_rec_${lkup.alias} record_${lkup.alias};
  BEGIN
    v_progname := 'pkg_${p.packageName}.prc_Qry_model_Init_${lkup.alias}';    
    --初始信息
    tbl_${lkup.alias} := table_${lkup.alias}();
    FOR ${lkup.alias} IN (
      SELECT <#list lkup.fieldlist as f>${f.columnName}<#if f_has_next>, </#if></#list>
      FROM  ${lkup.owner}.${lkup.tableName}
      ORDER BY <#list lkup.orderlist as f1>${f1.columnName}<#if f1_has_next>, </#if></#list>;
    )
    loop  
  	<#list lkup.fieldlist as f> 
        v_rec_${lkup.alias}.${f.columnName} := ${lkup.alias}.${f.columnName};
	</#list>      
        tbl_${lkup.alias}.EXTEND();
        tbl_${lkup.alias}(tbl_${lkup.alias}.count) := v_rec_${lkup.alias};
    END loop;
  END prc_lkup_model_init_${lkup.alias};
   </#list>
    
  <#list p.lkupTablelist as lkup>  
  PROCEDURE prc_qry_model_get_${lkup.alias}(<#list lkup.fieldlist as f><#if f.lookupColumn = 1>v_${f.alias}  ${f.dataType},</#if></#list> v_rec_${lkup.alias} IN out ${p.targetTablelist[0].tableName}%RowType) IS
    v_middle INTEGER;
    v_start INTEGER;
    v_end INTEGER;
  BEGIN
    v_progname := 'pkg_${p.packageName}.prc_Qry_model_Get_${lkup.alias}'; 
    
    v_middle := 0;
    --采用折半算法循环获取收付原因ID值
    v_start := 1;
    v_end := tbl_${lkup.alias}.count;
    loop
        --退出循环
        IF v_start > v_end THEN
            v_middle := 0;
            exit;   
        END IF;      
    
        --获取折半中间值
        v_middle := trunc((v_start + v_end)/2);
        <#list lkup.fieldlist as f>
        <#if f.lookupColumn = 1>          
        IF <#list lkup.fieldlist as f><#if f.lookupColumn = 1>v_${f.alias}</#if></#list> = tbl_${lkup.alias}(v_middle).${f.columnName} THEN           
            --如果满足条件退出循环
            exit;
        ELSE
            --重置循环起始终止ID
            IF <#list lkup.fieldlist as f><#if f.lookupColumn = 1>v_${f.alias}</#if></#list> > tbl_${lkup.alias}(v_middle).${f.columnName} THEN
                v_start := v_middle + 1;
            ELSE
                v_end := v_middle - 1;
            END IF;
        END IF;
        </#if>
        </#list>          
    END loop;    
    --根据获取ID值赋值
    IF v_middle = 0 THEN 
    <#list lkup.fieldlist as f> 
        v_rec_${lkup.alias}.${f.columnName} := NULL;
	</#list>
    ELSE
  	<#list lkup.fieldlist as f> 
        v_rec_${lkup.alias}.${f.columnName} := tbl_${lkup.alias}(v_middle).${f.columnName};
	</#list>
    END IF;
  END prc_qry_model_get_${lkup.alias};
  </#list>
   
  procedure prc_Init is
  begin
    v_progname := 'pkg_${p.packageName}.prc_Init';
    <#list p.lkupTablelist as lkup>
    prc_lkup_model_init_${lkup.alias};
    </#list>
  end prc_Init;
  
  procedure prc_Free is
  begin
    v_progname := 'pkg_${p.packageName}.prc_Free';
    <#list p.lkupTablelist as lkup>
    tbl_${lkup.alias} := table_${lkup.alias}(); 
    </#list>  
    <#list p.dlvrTablelist as dlvr>
    tbl_${dlvr.alias} := table_${dlvr.alias}(); 
    </#list>  
  end prc_Free;      
   
  procedure prc_main(vd_BeginDate in varchar2,vs_EtlType in varchar2) is
    iRet integer;    
    v_BeginTmpDate varchar2(10);
  begin
    v_progname := 'pkg_${p.packageName}.prc_main';
    
    --设置抽取信息
    if vd_BeginDate is null then
       vd_BeginTmpDate := trunc(sysdate)-1;
    else
       vd_BeginTmpDate := to_date(vd_BeginDate,'yyyy-mm-dd');
    end if;    
    
    --记录抽取开始状态
    iRet := f_Extract(vd_BeginTmpDate,vs_EtlType);
    if iRet < 0 then
       goto end_exit;
    end if;

    <<end_exit>>    
    null;
  exception
    when others then
       return;
  end prc_main;
     
  function f_Extract(vd_BeginDate in date,vs_EtlType in varchar2) return integer is
    iRet integer;
  begin
    v_progname := 'pkg_${p.packageName}.f_Extract';
    iRet := 0;
    
    prc_Init;
  	<#list p.targetTablelist as target>       
    prc_Extract_${target.alias}(vd_BeginDate,vs_EtlType); 
    </#list> 
    prc_Free;
    
    return(iRet);
  exception
    when others then
      iRet := -1;
      return(iRet);
  end f_Extract;
  
  <#list p.targetTablelist as target>
  procedure prc_Extract_${target.alias}(vd_BeginDate in date,vs_EtlType in varchar2)  is
    v_sql varchar2(2000);    
    sDtlRow ${target.owner}.${target.tableName}%RowType;
    <#list p.lkupTablelist as lkup>
    <#list lkup.fieldlist as f>
    <#if f.lookupColumn = 1>          
    v_${f.alias}  ${f.dataType};
    </#if>
    </#list> 
    </#list>   
    <#list p.dlvrTablelist as dlvr>   
    cursor deliver_${dlvr.alias}_Cur(vd_BeginDate date) is
      SELECT <#list dlvr.fieldlist as f>${f.columnName}<#if f_has_next>, </#if></#list>
      FROM  ${dlvr.owner}.${dlvr.tableName};
    </#list>  
  begin    
    v_progname := 'pkg_${p.packageName}.prc_Extract_${target.alias}';
    
    --判断抽取类型
    if vs_EtlType = 'E' then
       --全量抽取，全删全插所有数据
       v_sql := 'truncate table ${target.owner}.${target.tableName}';
       execute immediate v_sql;
    else
       --增量删除
       delete from ${target.owner}.${target.tableName} qry
--             created_date> = vd_BeginDate or last_updated_date> = vd_BeginDate
;
        commit;
    end if;
     
    <#list p.dlvrTablelist as dlvr>  
    open deliver_${dlvr.alias}_Cur(v_BeginDate);
    loop
        fetch deliver_${dlvr.alias}_Cur into 
            <#list dlvr.fieldlist as f>sDtlRow.${f.columnName}<#if f_has_next>, </#if></#list>;
        exit when deliver_${dlvr.alias}_Cur%notfound;
        
        
        <#list p.lkupTablelist as lkup>
        <#list lkup.fieldlist as f>
        <#if f.lookupColumn = 1>          
        v_${f.alias} := sDtlRow.${f.columnName};
        </#if>
        </#list>  
        </#list>  
     
  		<#list p.lkupTablelist as lkup>  
        prc_qry_model_get_${lkup.alias}(<#list lkup.fieldlist as f><#if f.lookupColumn = 1>v_${f.alias}</#if></#list>, sDtlRow);
  		</#list>
         
        sDtlRow.D_Etl_date := sysdate;
        insert into ${target.tableName}(
            <#list target.fieldlist as f>${f.columnName}<#if f_has_next>, </#if></#list>
        ) values (
            <#list target.fieldlist as f>sDtlRow.${f.columnName}<#if f_has_next>, </#if></#list>
        );         
    end loop;
    </#list>
    
    close ${target.alias}_Cur;
    commit;
    
    return;
  end prc_${target.alias};  
  </#list>  
     
END pkg_${p.packageName};
</#list>
