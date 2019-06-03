<#--sqluldr2 user=chj/chj@192.168.8.61:1521/chjdw query="select * from ${t.tableName?lower_case}" field=0x01 escape='\' escf=0x0a esct=n charset=utf8 safe=yes FILE=/app/gpload/data/${t.tableName?lower_case}-->
<#--gpload -f /app/gpload/conf/${t.tableName?lower_case}.ctl -l /app/gpload/log/${t.tableName?lower_case}.log-->

#!/bin/bash
mknod /app/gpload/buffer p
<#list tablelist as t>
echo "---------------${t.tableName?lower_case}----------------------"
sqluldr2 user=chj/chj@192.168.8.61:1521/chjdw query="select /*+ parallel(8)*/ * from ${t.tableName?lower_case}" field=0x01 escape=0x5c charset=utf8 safe=yes FILE=/app/${t.tableName?lower_case}  persql="begin dbms_lock.sleep(2); end;"
gpload -f /app/gpload/conf/${t.tableName?lower_case}.ctl -l /app/gpload/log/${t.tableName?lower_case}.log
echo "---------------${t.tableName?lower_case}----------------------"
</#list>
rm -rf /app/gpload/buffer
