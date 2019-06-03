<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<title>user.ftl</title>
</head>
<body>
	<table border="1"><tr>
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
		<#list fieldlist as Field>
  		<tr>
			<td>${Field_index+1}</td>
			<td>${Field.fieldID}</td>
			<td>${Field.fieldName}</td>
			<td>${Field.dataType}</td>
			<td>${Field.fieldLen}</td>
			<td>${Field.precision}</td>
			<td>${Field.scale}</td>
			<td>${Field.nullable?string("yes", "no")}</td>
			<td>${Field.remark}</td>
  		</tr>
		</#list>
	</table>
</body>
</html>