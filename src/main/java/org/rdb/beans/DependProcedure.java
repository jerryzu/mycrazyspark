package org.rdb.beans;

public class DependProcedure {
	private Integer procID;
	private String schemaName;
	private String procName;
	private Integer procedureID;
	private String procedureName;
	private String dependencyName;
	private String procedureDesc;
	private String objectType;
	private String remark;

	public DependProcedure() {
	}

	public Integer getProcID() {
		return procID;
	}

	public void setProcID(Integer procID) {
		this.procID = procID;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public Integer getProcedureID() {
		return procedureID;
	}

	public void setProcedureID(Integer ProcedureID) {
		this.procedureID = ProcedureID;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String ProcedureName) {
		procedureName = ProcedureName;
	}

	public String getProcedureDesc() {
		return procedureDesc;
	}

	public void setProcedureDesc(String ProcedureDesc) {
		procedureDesc = ProcedureDesc;
	}
	
	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String ObjectType) {
		objectType = ObjectType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String Remark) {
		remark = Remark;
	}

	public String getDependencyName() {
		return dependencyName;
	}

	public void setDependencyName(String dependencyName) {
		this.dependencyName = dependencyName;
	}

}
