package org.rdb.beans;

public class DependSourceRefSQL {
	//private Integer refSQLID;
	private String procName;
	private String objectType;
	private String remark;

	public DependSourceRefSQL() {
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
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
}
