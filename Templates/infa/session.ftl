<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE POWERMART SYSTEM "powrmart.dtd">
<POWERMART CREATION_DATE="09/23/2014 20:09:12" REPOSITORY_VERSION="181.90">
<REPOSITORY NAME="QOROS_BITEST" VERSION="181" CODEPAGE="UTF-8" DATABASETYPE="Oracle">
<FOLDER NAME="TEST_ONLY" GROUP="" OWNER="Administrator" SHARED="NOTSHARED" DESCRIPTION="" PERMISSIONS="rwx---r--">
    <CONFIG DESCRIPTION ="Default session configuration object" ISDEFAULT ="YES" NAME ="default_session_config" VERSIONNUMBER ="1">
        <ATTRIBUTE NAME ="Advanced" VALUE =""/>
        <ATTRIBUTE NAME ="Constraint based load ordering" VALUE ="NO"/>
        <ATTRIBUTE NAME ="Cache LOOKUP() function" VALUE ="YES"/>
        <ATTRIBUTE NAME ="Default buffer block size" VALUE ="Auto"/>
        <ATTRIBUTE NAME ="Line Sequential buffer length" VALUE ="1024"/>
        <ATTRIBUTE NAME ="Maximum Memory Allowed For Auto Memory Attributes" VALUE ="512MB"/>
        <ATTRIBUTE NAME ="Maximum Percentage of Total Memory Allowed For Auto Memory Attributes" VALUE ="5"/>
        <ATTRIBUTE NAME ="Additional Concurrent Pipelines for Lookup Cache Creation" VALUE ="Auto"/>
        <ATTRIBUTE NAME ="Custom Properties" VALUE =""/>
        <ATTRIBUTE NAME ="Pre-build lookup cache" VALUE ="Auto"/>
        <ATTRIBUTE NAME ="Optimization Level" VALUE ="Medium"/>
        <ATTRIBUTE NAME ="DateTime Format String" VALUE ="MM/DD/YYYY HH24:MI:SS.US"/>
        <ATTRIBUTE NAME ="Pre 85 Timestamp Compatibility" VALUE ="NO"/>
        <ATTRIBUTE NAME ="Log Options" VALUE ="0"/>
        <ATTRIBUTE NAME ="Save session log by" VALUE ="Session runs"/>
        <ATTRIBUTE NAME ="Save session log for these runs" VALUE ="0"/>
        <ATTRIBUTE NAME ="Session Log File Max Size" VALUE ="0"/>
        <ATTRIBUTE NAME ="Session Log File Max Time Period" VALUE ="0"/>
        <ATTRIBUTE NAME ="Maximum Partial Session Log Files" VALUE ="1"/>
        <ATTRIBUTE NAME ="Writer Commit Statistics Log Frequency" VALUE ="1"/>
        <ATTRIBUTE NAME ="Writer Commit Statistics Log Interval" VALUE ="0"/>
        <ATTRIBUTE NAME ="Error handling" VALUE =""/>
        <ATTRIBUTE NAME ="Stop on errors" VALUE ="0"/>
        <ATTRIBUTE NAME ="Override tracing" VALUE ="None"/>
        <ATTRIBUTE NAME ="On Stored Procedure error" VALUE ="Stop"/>
        <ATTRIBUTE NAME ="On Pre-session command task error" VALUE ="Stop"/>
        <ATTRIBUTE NAME ="On Pre-Post SQL error" VALUE ="Stop"/>
        <ATTRIBUTE NAME ="Enable Recovery" VALUE ="NO"/>
        <ATTRIBUTE NAME ="Error Log Type" VALUE ="None"/>
        <ATTRIBUTE NAME ="Error Log Table Name Prefix" VALUE =""/>
        <ATTRIBUTE NAME ="Error Log File Name" VALUE ="PMError.log"/>
        <ATTRIBUTE NAME ="Log Source Row Data" VALUE ="NO"/>
        <ATTRIBUTE NAME ="Data Column Delimiter" VALUE ="|"/>
        <ATTRIBUTE NAME ="Partitioning Options" VALUE =""/>
        <ATTRIBUTE NAME ="Dynamic Partitioning" VALUE ="Disabled"/>
        <ATTRIBUTE NAME ="Number of Partitions" VALUE ="1"/>
        <ATTRIBUTE NAME ="Multiplication Factor" VALUE ="Auto"/>
        <ATTRIBUTE NAME ="Session on Grid" VALUE =""/>
        <ATTRIBUTE NAME ="Is Enabled" VALUE ="NO"/>
    </CONFIG>
	<#list procedurelist as p>  
    <SESSION DESCRIPTION ="" ISVALID ="YES" MAPPINGNAME ="m_${p.procName}" NAME ="s_m_${p.procName}" REUSABLE ="YES" SORTORDER ="Binary" VERSIONNUMBER ="1">
        <SESSTRANSFORMATIONINST ISREPARTITIONPOINT ="YES" PARTITIONTYPE ="PASS THROUGH" PIPELINE ="1" SINSTANCENAME ="TT_ETL_DCS_ODS_INFA_RESULT" STAGE ="1" TRANSFORMATIONNAME ="TT_ETL_DCS_ODS_INFA_RESULT" TRANSFORMATIONTYPE ="Target Definition"/>
        <SESSTRANSFORMATIONINST ISREPARTITIONPOINT ="NO" PIPELINE ="1" SINSTANCENAME ="${p.procName}" STAGE ="2" TRANSFORMATIONNAME ="${p.procName}" TRANSFORMATIONTYPE ="Stored Procedure"/>
        <SESSTRANSFORMATIONINST ISREPARTITIONPOINT ="NO" PIPELINE ="1" SINSTANCENAME ="EXP_TT_ETL_DCS_ODS_INFA_PARAMS" STAGE ="2" TRANSFORMATIONNAME ="EXP_TT_ETL_DCS_ODS_INFA_PARAMS" TRANSFORMATIONTYPE ="Expression"/>
        <SESSTRANSFORMATIONINST ISREPARTITIONPOINT ="NO" PIPELINE ="0" SINSTANCENAME ="TT_ETL_DCS_ODS_INFA_PARAMS" STAGE ="0" TRANSFORMATIONNAME ="TT_ETL_DCS_ODS_INFA_PARAMS" TRANSFORMATIONTYPE ="Source Definition"/>
        <SESSTRANSFORMATIONINST ISREPARTITIONPOINT ="YES" PARTITIONTYPE ="PASS THROUGH" PIPELINE ="1" SINSTANCENAME ="SQ_TT_ETL_DCS_ODS_INFA_PARAMS" STAGE ="2" TRANSFORMATIONNAME ="SQ_TT_ETL_DCS_ODS_INFA_PARAMS" TRANSFORMATIONTYPE ="Source Qualifier"/>
        <CONFIGREFERENCE REFOBJECTNAME ="default_session_config" TYPE ="Session config"/>
        <SESSIONEXTENSION NAME ="Relational Writer" SINSTANCENAME ="TT_ETL_DCS_ODS_INFA_RESULT" SUBTYPE ="Relational Writer" TRANSFORMATIONTYPE ="Target Definition" TYPE ="WRITER">
            <CONNECTIONREFERENCE CNXREFNAME ="DB Connection" CONNECTIONNAME ="DCS_ODS" CONNECTIONNUMBER ="1" CONNECTIONSUBTYPE ="Oracle" CONNECTIONTYPE ="Relational" VARIABLE =""/>
            <ATTRIBUTE NAME ="Target load type" VALUE ="Bulk"/>
            <ATTRIBUTE NAME ="Insert" VALUE ="YES"/>
            <ATTRIBUTE NAME ="Update as Update" VALUE ="YES"/>
            <ATTRIBUTE NAME ="Update as Insert" VALUE ="NO"/>
            <ATTRIBUTE NAME ="Update else Insert" VALUE ="NO"/>
            <ATTRIBUTE NAME ="Delete" VALUE ="YES"/>
            <ATTRIBUTE NAME ="Truncate target table option" VALUE ="NO"/>
            <ATTRIBUTE NAME ="Reject file directory" VALUE ="$PMBadFileDir&#x5c;"/>
            <ATTRIBUTE NAME ="Reject filename" VALUE ="tt_etl_dcs_ods_infa_result1.bad"/>
        </SESSIONEXTENSION>
        <SESSIONEXTENSION DSQINSTNAME ="SQ_TT_ETL_DCS_ODS_INFA_PARAMS" DSQINSTTYPE ="Source Qualifier" NAME ="Relational Reader" SINSTANCENAME ="TT_ETL_DCS_ODS_INFA_PARAMS" SUBTYPE ="Relational Reader" TRANSFORMATIONTYPE ="Source Definition" TYPE ="READER"/>
        <SESSIONEXTENSION NAME ="Relational Reader" SINSTANCENAME ="SQ_TT_ETL_DCS_ODS_INFA_PARAMS" SUBTYPE ="Relational Reader" TRANSFORMATIONTYPE ="Source Qualifier" TYPE ="READER">
            <CONNECTIONREFERENCE CNXREFNAME ="DB Connection" CONNECTIONNAME ="DCS_ODS" CONNECTIONNUMBER ="1" CONNECTIONSUBTYPE ="Oracle" CONNECTIONTYPE ="Relational" VARIABLE =""/>
        </SESSIONEXTENSION>
        <ATTRIBUTE NAME ="General Options" VALUE =""/>
        <ATTRIBUTE NAME ="Write Backward Compatible Session Log File" VALUE ="NO"/>
        <ATTRIBUTE NAME ="Session Log File Name" VALUE ="s_m_${p.procName}.log"/>
        <ATTRIBUTE NAME ="Session Log File directory" VALUE ="$PMSessionLogDir&#x5c;"/>
        <ATTRIBUTE NAME ="Parameter Filename" VALUE ="$PMRootDir&#x5c;DCSODSParam&#x5c;DCSODS_PARAMS.txt"/>
        <ATTRIBUTE NAME ="Enable Test Load" VALUE ="NO"/>
        <ATTRIBUTE NAME ="$Source connection value" VALUE ="Relational:DCS_ODS"/>
        <ATTRIBUTE NAME ="$Target connection value" VALUE ="Relational:DCS_ODS"/>
        <ATTRIBUTE NAME ="Treat source rows as" VALUE ="Insert"/>
        <ATTRIBUTE NAME ="Commit Type" VALUE ="Target"/>
        <ATTRIBUTE NAME ="Commit Interval" VALUE ="10000"/>
        <ATTRIBUTE NAME ="Commit On End Of File" VALUE ="YES"/>
        <ATTRIBUTE NAME ="Rollback Transactions on Errors" VALUE ="NO"/>
        <ATTRIBUTE NAME ="Recovery Strategy" VALUE ="Fail task and continue workflow"/>
        <ATTRIBUTE NAME ="Java Classpath" VALUE =""/>
        <ATTRIBUTE NAME ="Performance" VALUE =""/>
        <ATTRIBUTE NAME ="DTM buffer size" VALUE ="Auto"/>
        <ATTRIBUTE NAME ="Collect performance data" VALUE ="NO"/>
        <ATTRIBUTE NAME ="Write performance data to repository" VALUE ="NO"/>
        <ATTRIBUTE NAME ="Incremental Aggregation" VALUE ="NO"/>
        <ATTRIBUTE NAME ="Enable high precision" VALUE ="NO"/>
        <ATTRIBUTE NAME ="Session retry on deadlock" VALUE ="NO"/>
        <ATTRIBUTE NAME ="Pushdown Optimization" VALUE ="None"/>
        <ATTRIBUTE NAME ="Allow Temporary View for Pushdown" VALUE ="NO"/>
        <ATTRIBUTE NAME ="Allow Temporary Sequence for Pushdown" VALUE ="NO"/>
        <ATTRIBUTE NAME ="Allow Pushdown for User Incompatible Connections" VALUE ="NO"/>
    </SESSION>
	</#list>
</FOLDER>
</REPOSITORY>
</POWERMART>