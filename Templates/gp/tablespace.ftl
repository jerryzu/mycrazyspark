<#list tablespacelist as tablespace>
CREATE ${tablespace.fileType!''} TABLESPACE ${tablespace.tableSpaceName}  
    DATAFILE 
    	<#list tablespace.tablespaceFilelist as tablespaceFile>
    		<#if tablespaceFile.sizeUnit == "G">  
        '${tablespaceFile.datafileName}' SIZE ${tablespaceFile.fileSize * 1024 * 1024 * 1024}<#if tablespaceFile_has_next>,</#if>
        <#elseif tablespaceFile.sizeUnit == "M">
        '${tablespaceFile.datafileName}' SIZE ${tablespaceFile.fileSize * 1024 * 1024}<#if tablespaceFile_has_next>,</#if>
        <#elseif tablespaceFile.sizeUnit == "K">
        '${tablespaceFile.datafileName}' SIZE ${tablespaceFile.fileSize * 1024}<#if tablespaceFile_has_next>,</#if>
        <#else>
        '${tablespaceFile.datafileName}' SIZE ${tablespaceFile.fileSize}<#if tablespaceFile_has_next>,</#if>
        </#if>
        </#list> 
    <#if tablespace.logging == 0> 
    	NOLOGGING 
	<#else>
    	LOGGING
	</#if>    
    DEFAULT NOCOMPRESS 
    ONLINE 
    EXTENT MANAGEMENT LOCAL;
</#list>