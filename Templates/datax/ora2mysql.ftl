<#-- -->
<#list joblist as job>
cat  >> /alidata/edwproject/datax/task/${job.jobName}.json  << EOF
{
    "job": {
        "content": [
            {
                "reader": {
                    "name": "${job.jobReader}", 
                    "parameter": {
                        "column": ["*"],
                        <#--"column": [<#list job.collist as col>${col.readerColumnName}<#if Field_has_next>,</#if></#list>], -->
                        "connection": [
                            {
                                "jdbcUrl": ["${job.readerUrl}"], 
                                "table": ["${job.readertable}"]
                            }
                        ], 
                        "password": "${job.readerPassword}", 
                        "username": "${job.readerUserName}"
                    }
                }, 
                "writer": {
                    "name": "${job.jobWriter}", 
                    "parameter": {
                    	"column": ["*"],
                        <#--"column": [<#list job.collist as col>${col.writerColumnName}<#if Field_has_next>,</#if></#list>], -->
                        "connection": [
                            {
                                "jdbcUrl": "${job.writerUrl}", 
                                "table": ["${job.writertable}"]
                            }
                        ], 
                        "password": "${job.writerPassword}", 
                        "preSql": ["${job.preSql}"], 
                        "username": "${job.writerUserName}",  
                        "session": [],
                        "writeMode": "insert"

                    }
                }
            }
        ], 
        "setting": {
            "speed": {
                "channel": "${job.channel}"
            }
        }
    }
}

EOF

</#list>