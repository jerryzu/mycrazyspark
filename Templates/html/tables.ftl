<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<title>user.ftl</title>
</head>
<body>
	<table border="1"><tr>
			<td>index</td>
			<td>tableID</td>
			<td>tableName</td>
			<td>remark</td>
			<td>partType</td>
			<td>partField</td>
  		</tr>
		<#list tablelist as Table>
  		<tr>
			<td>${Table_index+1}</td>
			<td>${Table.tableID}</td>
			<td>${Table.tableName}</td>
			<td>${Table.remark!" "}</td>
			<td>${Table.partition.partType}</td>
			<td>${Table.partition.partField}</td>
  		</tr>
		</#list>
	</table>
</body>
</html>