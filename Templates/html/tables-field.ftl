<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<title>user.ftl</title>
</head>
<body>
	<#list tablelist as Table>
	<table border="1">
		<tr>
			<td>index</td>
			<td>tableID</td>
			<td>tableName</td>
			<td>remark</td>
			<td>partType</td>
			<td>partField</td>
  		</tr>
  		<tr>
			<td>${Table_index+1}</td>
			<td>${Table.tableID}</td>
			<td>${Table.tableName}</td>
			<td>${Table.remark!" "}</td>
			<td>${Table.partition.partType}</td>
			<td>${Table.partition.partField}</td>
  		</tr>
	</table>
		<table border="1">
		<tr>
			<td>index</td>
			<td>fieldID</td>
			<td>fieldName</td>
			<td>dataType</td>
			<td>fieldLen</td>
			<td>precision</td>
			<td>scale</td>
			<td>Nullable</td>
			<td>remark</td>
  		</tr>
		<#list Table.fieldlist as Field>
	  	<tr>
			<td>${Field_index+1}</td>
			<td>${Field.fieldID}</td>
			<td>${Field.fieldName}</td>
			<td>${Field.dataType}</td>
			<td>${Field.fieldLen}</td>
			<td>${Field.precision}</td>
			<td>${Field.scale}</td>
			<td>${Field.nullable?string("yes", "no")}</td>
			<td>${Field.remark!" "}</td>
	  	</tr>
		</#list>
		</table>
	</#list>
</body>
</html>