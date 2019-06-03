<#-- -->
export JAVA_HOME=/alidata/java/jdk1.8.0_121
export PATH=$JAVA_HOME:$PATH
export JAVA_HOME=/alidata/java/jdk1.8.0_121           
export CLASSPATH=.:$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/dt.jar 
export PATH=$JAVA_HOME/bin:$PATH
export DATAX_HOME=/alidata/edwproject/datax
cd ${r'${DATAX_HOME}'}/bin
<#list joblist as job>
nohup python datax.py /alidata/edwproject/datax/task/${job.jobName}.json >/alidata/edwproject/datax/task/log/${job.jobName}.log 2>&1 &
tail -f /alidata/edwproject/datax/task/log/${job.jobName}.log
</#list>

create or replace view RUL_SQLSCRIPT
as
<#list joblist as job>
SELECT '${job.jsonFileName}' remark, 1 as rulid, N_SEQ seq, C_CDE cde, C_NAME name, 1 enabled, C_SQL as sqltxt
FROM ${job.jsonFileName}
where c_sql is not null
<#if job_has_next>union all</#if>
</#list>
