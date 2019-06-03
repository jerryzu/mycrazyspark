<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<title>user.ftl</title>
</head>
<body>
	<table border="1">
		<tr>
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
			<td>${procedure.createtime?string("yyyy-MM-dd HH:mm:ss")}</td>
			<td>${procedure.remark}</td>
  		</tr>
	</table>
	<table border="1">
		<tr>
			<td>index</td>
			<td>DependencyID</td>
			<td>DependencyName</td>
			<td>DependencyDesc</td>
			<td>ObjectType</td>
			<td>remark</td>
  		</tr>
		<#list procedure.dependencylist as Dependency>
	  	<tr>
			<td>${Dependency_index+1}</td>
			<td>${Dependency.dependencyID}</td>
			<td>${Dependency.dependencyName}</td>
			<td>${Dependency.dependencyDesc}</td>
			<td>${Dependency.objectType}</td>
			<td>${Dependency.remark!" "}</td>
	  	</tr>
		</#list>
	</table><table border="1">
		<tr>
			<td>index</td>
			<td>sourceTableOwner</td>
			<td>sourceTableName</td>
			<td>sourceFieldName</td>
			<td>sourceFieldDataType</td>
			<td>sourceFieldDesc</td>
			<td>targetTableOwner</td>
			<td>targetTableName</td>
			<td>targetFieldName</td>
			<td>targetFieldDataType</td>
			<td>targetFieldDesc</td>
  		</tr>
		<#list procedure.etlDAOFieldProplist as FieldProp>
	  	<tr>
			<td>${FieldProp_index+1}</td>
			<td>${FieldProp.sourceTableOwner!" "}</td>
			<td>${FieldProp.sourceTableName!" "}</td>
			<td>${FieldProp.sourceFieldName!" "}</td>
			<td>${FieldProp.sourceFieldDataType!" "}</td>
			<td>${FieldProp.sourceFieldDesc!" "}</td>
			<td>${FieldProp.targetTableOwner!" "}</td>
			<td>${FieldProp.targetTableName!" "}</td>
			<td>${FieldProp.targetFieldName!" "}</td>
			<td>${FieldProp.targetFieldDataType!" "}</td>
			<td>${FieldProp.targetFieldDesc!" "}</td>
	  	</tr>
		</#list>
 		<td>${procedure.etlDAOFieldProplist[0].sourceTableName!" "}</td>
	</table>
	</#list>
</body>
</html>
