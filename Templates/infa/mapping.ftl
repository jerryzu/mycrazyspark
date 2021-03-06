<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE POWERMART SYSTEM "powrmart.dtd">
<POWERMART CREATION_DATE="09/24/2014 17:34:30" REPOSITORY_VERSION="181.90">
<REPOSITORY NAME="QOROS_BITEST" VERSION="181" CODEPAGE="UTF-8" DATABASETYPE="Oracle">
<FOLDER NAME="TEST_ONLY" GROUP="" OWNER="Administrator" SHARED="NOTSHARED" DESCRIPTION="" PERMISSIONS="rwx---r--" UUID="7e87584a-d402-4422-a873-f809bd402d5e">
    <SOURCE BUSINESSNAME ="" DATABASETYPE ="Oracle" DBDNAME ="DCS_DEV" DESCRIPTION ="" NAME ="TT_ETL_DCS_ODS_INFA_PARAMS" OBJECTVERSION ="1" OWNERNAME ="DCS_ODS" VERSIONNUMBER ="1">
        <SOURCEFIELD BUSINESSNAME ="" DATATYPE ="nvarchar2" DESCRIPTION ="" FIELDNUMBER ="1" FIELDPROPERTY ="0" FIELDTYPE ="ELEMITEM" HIDDEN ="NO" KEYTYPE ="NOT A KEY" LENGTH ="0" LEVEL ="0" NAME ="BATCH" NULLABLE ="NULL" OCCURS ="0" OFFSET ="0" PHYSICALLENGTH ="17" PHYSICALOFFSET ="0" PICTURETEXT ="" PRECISION ="17" SCALE ="0" USAGE_FLAGS =""/>
        <SOURCEFIELD BUSINESSNAME ="" DATATYPE ="date" DESCRIPTION ="" FIELDNUMBER ="2" FIELDPROPERTY ="0" FIELDTYPE ="ELEMITEM" HIDDEN ="NO" KEYTYPE ="NOT A KEY" LENGTH ="19" LEVEL ="0" NAME ="STARTDATE" NULLABLE ="NULL" OCCURS ="0" OFFSET ="0" PHYSICALLENGTH ="19" PHYSICALOFFSET ="17" PICTURETEXT ="" PRECISION ="19" SCALE ="0" USAGE_FLAGS =""/>
        <SOURCEFIELD BUSINESSNAME ="" DATATYPE ="date" DESCRIPTION ="" FIELDNUMBER ="3" FIELDPROPERTY ="0" FIELDTYPE ="ELEMITEM" HIDDEN ="NO" KEYTYPE ="NOT A KEY" LENGTH ="19" LEVEL ="0" NAME ="ENDDATE" NULLABLE ="NULL" OCCURS ="0" OFFSET ="19" PHYSICALLENGTH ="19" PHYSICALOFFSET ="36" PICTURETEXT ="" PRECISION ="19" SCALE ="0" USAGE_FLAGS =""/>
    </SOURCE>
    <TARGET BUSINESSNAME ="" CONSTRAINT ="" DATABASETYPE ="Oracle" DESCRIPTION ="" NAME ="TT_ETL_DCS_ODS_INFA_RESULT" OBJECTVERSION ="1" TABLEOPTIONS ="" VERSIONNUMBER ="1">
        <TARGETFIELD BUSINESSNAME ="" DATATYPE ="nvarchar2" DESCRIPTION ="" FIELDNUMBER ="1" KEYTYPE ="NOT A KEY" NAME ="BATCH" NULLABLE ="NULL" PICTURETEXT ="" PRECISION ="17" SCALE ="0"/>
        <TARGETFIELD BUSINESSNAME ="" DATATYPE ="date" DESCRIPTION ="" FIELDNUMBER ="2" KEYTYPE ="NOT A KEY" NAME ="STARTDATE" NULLABLE ="NULL" PICTURETEXT ="" PRECISION ="19" SCALE ="0"/>
        <TARGETFIELD BUSINESSNAME ="" DATATYPE ="date" DESCRIPTION ="" FIELDNUMBER ="3" KEYTYPE ="NOT A KEY" NAME ="ENDDATE" NULLABLE ="NULL" PICTURETEXT ="" PRECISION ="19" SCALE ="0"/>
    </TARGET>
	<#list procedurelist as p>  
	<MAPPING DESCRIPTION ="" ISVALID ="YES" NAME ="m_${p.procName}" OBJECTVERSION ="1" VERSIONNUMBER ="1">
        <TRANSFORMATION DESCRIPTION ="" NAME ="${p.procName}" OBJECTVERSION ="1" REUSABLE ="NO" TYPE ="Stored Procedure" VERSIONNUMBER ="1">
            <TRANSFORMFIELD DATATYPE ="nstring" DEFAULTVALUE ="" DESCRIPTION ="" NAME ="PS_BATCH" PICTURETEXT ="" PORTTYPE ="INPUT/OUTPUT" PRECISION ="4000" SCALE ="0"/>
            <TRANSFORMFIELD DATATYPE ="date/time" DEFAULTVALUE ="" DESCRIPTION ="" NAME ="PD_STARTDATE" PICTURETEXT ="" PORTTYPE ="INPUT/OUTPUT" PRECISION ="29" SCALE ="9"/>
            <TRANSFORMFIELD DATATYPE ="date/time" DEFAULTVALUE ="" DESCRIPTION ="" NAME ="PD_ENDDATE" PICTURETEXT ="" PORTTYPE ="INPUT/OUTPUT" PRECISION ="29" SCALE ="9"/>
            <TABLEATTRIBUTE NAME ="Stored Procedure Name" VALUE ="${p.procName}"/>
            <TABLEATTRIBUTE NAME ="Connection Information" VALUE ="$Target"/>
            <TABLEATTRIBUTE NAME ="Call Text" VALUE =""/>
            <TABLEATTRIBUTE NAME ="Stored Procedure Type" VALUE ="Normal"/>
            <TABLEATTRIBUTE NAME ="Execution Order" VALUE ="1"/>
            <TABLEATTRIBUTE NAME ="Tracing Level" VALUE ="Normal"/>
            <TABLEATTRIBUTE NAME ="Subsecond Precision" VALUE ="6"/>
            <TABLEATTRIBUTE NAME ="Output is Repeatable" VALUE ="Based On Input Order"/>
            <TABLEATTRIBUTE NAME ="Output is Deterministic" VALUE ="NO"/>
        </TRANSFORMATION>
		<TRANSFORMATION DESCRIPTION ="" NAME ="EXP_TT_ETL_DCS_ODS_INFA_PARAMS" OBJECTVERSION ="1" REUSABLE ="NO" TYPE ="Expression" VERSIONNUMBER ="1">
            <TRANSFORMFIELD DATATYPE ="nstring" DEFAULTVALUE ="" DESCRIPTION ="" EXPRESSION ="BATCH" EXPRESSIONTYPE ="GENERAL" NAME ="BATCH" PICTURETEXT ="" PORTTYPE ="INPUT/OUTPUT" PRECISION ="400" SCALE ="0"/>
            <TRANSFORMFIELD DATATYPE ="date/time" DEFAULTVALUE ="" DESCRIPTION ="" NAME ="STARTDATE" PICTURETEXT ="" PORTTYPE ="INPUT" PRECISION ="29" SCALE ="9"/>
            <TRANSFORMFIELD DATATYPE ="date/time" DEFAULTVALUE ="" DESCRIPTION ="" NAME ="ENDDATE" PICTURETEXT ="" PORTTYPE ="INPUT" PRECISION ="29" SCALE ="9"/>
            <TRANSFORMFIELD DATATYPE ="date/time" DEFAULTVALUE ="ERROR(&apos;transformation error&apos;)" DESCRIPTION ="" EXPRESSION ="IIF(($$AUTORUN=&apos;1&apos; OR ISNULL($$AUTORUN)), STARTDATE, TO_DATE($$STARTDATE, &apos;YYYYMMDD&apos;))" EXPRESSIONTYPE ="GENERAL" NAME ="V_STARTDATE" PICTURETEXT ="" PORTTYPE ="OUTPUT" PRECISION ="29" SCALE ="9"/>
            <TRANSFORMFIELD DATATYPE ="date/time" DEFAULTVALUE ="ERROR(&apos;transformation error&apos;)" DESCRIPTION ="" EXPRESSION ="IIF(($$AUTORUN=&apos;1&apos; OR ISNULL($$AUTORUN)), ENDDATE, TO_DATE(CONCAT($$ENDDATE,&apos;235959999&apos;), &apos;YYYYMMDDHH24MISSMS&apos;))" EXPRESSIONTYPE ="GENERAL" NAME ="V_ENDDATE" PICTURETEXT ="" PORTTYPE ="OUTPUT" PRECISION ="29" SCALE ="9"/>
            <TABLEATTRIBUTE NAME ="Tracing Level" VALUE ="Normal"/>
            <METADATAEXTENSION DATATYPE ="STRING" DESCRIPTION ="" DOMAINNAME ="User Defined Metadata Domain" ISCLIENTEDITABLE ="YES" ISCLIENTVISIBLE ="YES" ISREUSABLE ="YES" ISSHAREREAD ="NO" ISSHAREWRITE ="NO" MAXLENGTH ="256" NAME ="��չ��" VALUE ="" VENDORNAME ="INFORMATICA"/>
        </TRANSFORMATION>
        <TRANSFORMATION DESCRIPTION ="" NAME ="SQ_TT_ETL_DCS_ODS_INFA_PARAMS" OBJECTVERSION ="1" REUSABLE ="NO" TYPE ="Source Qualifier" VERSIONNUMBER ="1">
            <TRANSFORMFIELD DATATYPE ="nstring" DEFAULTVALUE ="" DESCRIPTION ="" NAME ="BATCH" PICTURETEXT ="" PORTTYPE ="INPUT/OUTPUT" PRECISION ="17" SCALE ="0"/>
            <TRANSFORMFIELD DATATYPE ="date/time" DEFAULTVALUE ="" DESCRIPTION ="" NAME ="STARTDATE" PICTURETEXT ="" PORTTYPE ="INPUT/OUTPUT" PRECISION ="29" SCALE ="9"/>
            <TRANSFORMFIELD DATATYPE ="date/time" DEFAULTVALUE ="" DESCRIPTION ="" NAME ="ENDDATE" PICTURETEXT ="" PORTTYPE ="INPUT/OUTPUT" PRECISION ="29" SCALE ="9"/>
            <TABLEATTRIBUTE NAME ="Sql Query" VALUE =""/>
            <TABLEATTRIBUTE NAME ="User Defined Join" VALUE =""/>
            <TABLEATTRIBUTE NAME ="Source Filter" VALUE =""/>
            <TABLEATTRIBUTE NAME ="Number Of Sorted Ports" VALUE ="0"/>
            <TABLEATTRIBUTE NAME ="Tracing Level" VALUE ="Normal"/>
            <TABLEATTRIBUTE NAME ="Select Distinct" VALUE ="NO"/>
            <TABLEATTRIBUTE NAME ="Is Partitionable" VALUE ="NO"/>
            <TABLEATTRIBUTE NAME ="Pre SQL" VALUE =""/>
            <TABLEATTRIBUTE NAME ="Post SQL" VALUE =""/>
            <TABLEATTRIBUTE NAME ="Output is deterministic" VALUE ="NO"/>
            <TABLEATTRIBUTE NAME ="Output is repeatable" VALUE ="Never"/>
        </TRANSFORMATION>
        <INSTANCE DESCRIPTION ="" NAME ="TT_ETL_DCS_ODS_INFA_RESULT" TRANSFORMATION_NAME ="TT_ETL_DCS_ODS_INFA_RESULT" TRANSFORMATION_TYPE ="Target Definition" TYPE ="TARGET"/>
        <INSTANCE DESCRIPTION ="" NAME ="${p.procName}" REUSABLE ="NO" TRANSFORMATION_NAME ="${p.procName}" TRANSFORMATION_TYPE ="Stored Procedure" TYPE ="TRANSFORMATION"/>
        <INSTANCE DESCRIPTION ="" NAME ="EXP_TT_ETL_DCS_ODS_INFA_PARAMS" REUSABLE ="NO" TRANSFORMATION_NAME ="EXP_TT_ETL_DCS_ODS_INFA_PARAMS" TRANSFORMATION_TYPE ="Expression" TYPE ="TRANSFORMATION"/>
        <INSTANCE DBDNAME ="DCS_DEV" DESCRIPTION ="" NAME ="TT_ETL_DCS_ODS_INFA_PARAMS" TRANSFORMATION_NAME ="TT_ETL_DCS_ODS_INFA_PARAMS" TRANSFORMATION_TYPE ="Source Definition" TYPE ="SOURCE"/>
        <INSTANCE DESCRIPTION ="" NAME ="SQ_TT_ETL_DCS_ODS_INFA_PARAMS" REUSABLE ="NO" TRANSFORMATION_NAME ="SQ_TT_ETL_DCS_ODS_INFA_PARAMS" TRANSFORMATION_TYPE ="Source Qualifier" TYPE ="TRANSFORMATION">
            <ASSOCIATED_SOURCE_INSTANCE NAME ="TT_ETL_DCS_ODS_INFA_PARAMS"/>
        </INSTANCE>
        <CONNECTOR FROMFIELD ="PS_BATCH" FROMINSTANCE ="${p.procName}" FROMINSTANCETYPE ="Stored Procedure" TOFIELD ="BATCH" TOINSTANCE ="TT_ETL_DCS_ODS_INFA_RESULT" TOINSTANCETYPE ="Target Definition"/>
        <CONNECTOR FROMFIELD ="PD_STARTDATE" FROMINSTANCE ="${p.procName}" FROMINSTANCETYPE ="Stored Procedure" TOFIELD ="STARTDATE" TOINSTANCE ="TT_ETL_DCS_ODS_INFA_RESULT" TOINSTANCETYPE ="Target Definition"/>
        <CONNECTOR FROMFIELD ="PD_ENDDATE" FROMINSTANCE ="${p.procName}" FROMINSTANCETYPE ="Stored Procedure" TOFIELD ="ENDDATE" TOINSTANCE ="TT_ETL_DCS_ODS_INFA_RESULT" TOINSTANCETYPE ="Target Definition"/>
        <CONNECTOR FROMFIELD ="V_ENDDATE" FROMINSTANCE ="EXP_TT_ETL_DCS_ODS_INFA_PARAMS" FROMINSTANCETYPE ="Expression" TOFIELD ="PD_ENDDATE" TOINSTANCE ="${p.procName}" TOINSTANCETYPE ="Stored Procedure"/>
        <CONNECTOR FROMFIELD ="V_STARTDATE" FROMINSTANCE ="EXP_TT_ETL_DCS_ODS_INFA_PARAMS" FROMINSTANCETYPE ="Expression" TOFIELD ="PD_STARTDATE" TOINSTANCE ="${p.procName}" TOINSTANCETYPE ="Stored Procedure"/>
        <CONNECTOR FROMFIELD ="BATCH" FROMINSTANCE ="EXP_TT_ETL_DCS_ODS_INFA_PARAMS" FROMINSTANCETYPE ="Expression" TOFIELD ="PS_BATCH" TOINSTANCE ="${p.procName}" TOINSTANCETYPE ="Stored Procedure"/>
        <CONNECTOR FROMFIELD ="BATCH" FROMINSTANCE ="SQ_TT_ETL_DCS_ODS_INFA_PARAMS" FROMINSTANCETYPE ="Source Qualifier" TOFIELD ="BATCH" TOINSTANCE ="EXP_TT_ETL_DCS_ODS_INFA_PARAMS" TOINSTANCETYPE ="Expression"/>
        <CONNECTOR FROMFIELD ="STARTDATE" FROMINSTANCE ="SQ_TT_ETL_DCS_ODS_INFA_PARAMS" FROMINSTANCETYPE ="Source Qualifier" TOFIELD ="STARTDATE" TOINSTANCE ="EXP_TT_ETL_DCS_ODS_INFA_PARAMS" TOINSTANCETYPE ="Expression"/>
        <CONNECTOR FROMFIELD ="ENDDATE" FROMINSTANCE ="SQ_TT_ETL_DCS_ODS_INFA_PARAMS" FROMINSTANCETYPE ="Source Qualifier" TOFIELD ="ENDDATE" TOINSTANCE ="EXP_TT_ETL_DCS_ODS_INFA_PARAMS" TOINSTANCETYPE ="Expression"/>
        <CONNECTOR FROMFIELD ="BATCH" FROMINSTANCE ="TT_ETL_DCS_ODS_INFA_PARAMS" FROMINSTANCETYPE ="Source Definition" TOFIELD ="BATCH" TOINSTANCE ="SQ_TT_ETL_DCS_ODS_INFA_PARAMS" TOINSTANCETYPE ="Source Qualifier"/>
        <CONNECTOR FROMFIELD ="STARTDATE" FROMINSTANCE ="TT_ETL_DCS_ODS_INFA_PARAMS" FROMINSTANCETYPE ="Source Definition" TOFIELD ="STARTDATE" TOINSTANCE ="SQ_TT_ETL_DCS_ODS_INFA_PARAMS" TOINSTANCETYPE ="Source Qualifier"/>
        <CONNECTOR FROMFIELD ="ENDDATE" FROMINSTANCE ="TT_ETL_DCS_ODS_INFA_PARAMS" FROMINSTANCETYPE ="Source Definition" TOFIELD ="ENDDATE" TOINSTANCE ="SQ_TT_ETL_DCS_ODS_INFA_PARAMS" TOINSTANCETYPE ="Source Qualifier"/>
        <TARGETLOADORDER ORDER ="1" TARGETINSTANCE ="TT_ETL_DCS_ODS_INFA_RESULT"/>
        <MAPPINGVARIABLE DATATYPE ="nstring" DEFAULTVALUE ="" DESCRIPTION ="" ISEXPRESSIONVARIABLE ="NO" ISPARAM ="YES" NAME ="$$AUTORUN" PRECISION ="10" SCALE ="0" USERDEFINED ="YES"/>
        <MAPPINGVARIABLE DATATYPE ="nstring" DEFAULTVALUE ="" DESCRIPTION ="" ISEXPRESSIONVARIABLE ="NO" ISPARAM ="YES" NAME ="$$STARTDATE" PRECISION ="29" SCALE ="0" USERDEFINED ="YES"/>
        <MAPPINGVARIABLE DATATYPE ="nstring" DEFAULTVALUE ="" DESCRIPTION ="" ISEXPRESSIONVARIABLE ="NO" ISPARAM ="YES" NAME ="$$ENDDATE" PRECISION ="29" SCALE ="0" USERDEFINED ="YES"/>
        <ERPINFO/>
    </MAPPING>
	</#list>  
</FOLDER>
</REPOSITORY>
</POWERMART>