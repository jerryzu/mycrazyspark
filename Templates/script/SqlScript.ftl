<#list sqlScriptlist as sql>
${sql.remark!""};
${sql.seq};
${sql.cde};
${sql.name};
${sql.sqltxt};
</#list> 