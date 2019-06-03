package org.rdb.beans;

public class DependJoinText {
	private Integer procID;
	private String schemaName;
	private String procName;
	private String joinText;
	private String remark;

	public DependJoinText() {
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

	public String getJoinText() {
		return joinText;
	}

	public void setJoinText(String JoinText) {
		joinText = JoinText;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String Remark) {
		remark = Remark;
	}
}
