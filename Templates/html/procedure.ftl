<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<title>user.ftl</title>
</head>
<body>
	<table border="1"><tr>
			<td>index</td>
			<td>schemaName</td>
			<td>procID</td>
			<td>procName</td>
			<td>procDesc</td>
			<td>author</td>
			<td>comment</td>
			<td>createtime</td>
			<td>remark</td>
  		</tr>
		<#list procedurelist as procedure>
  		<tr>
			<td>${procedure_index+1}</td>
			<td>${procedure.schemaName}</td>
			<td>${procedure.procID}</td>
			<td>${procedure.procName}</td>
			<td>${procedure.procDesc}</td>
			<td>${procedure.author}</td>
			<td>${procedure.comment}</td>
			<td>{procedure.createtime?string("yes", "no")}</td>
			<td>${procedure.remark}</td>
  		</tr>
		</#list>
	</table>
</body>
</html>