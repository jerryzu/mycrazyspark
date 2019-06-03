package org.rdb.beans;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

public class DependTargetTable {
	private Integer procID;
	private String targetTableOwner;
	private String procName;
	private Integer targetTableID;
	private String targetTableName;
	private String targetTableDesc;
	private String masterKey;
	private String UpdateField;
	private String objectType;
	private String remark;
	private String deleteType;

	public DependTargetTable() {
	}

	public Integer getProcID() {
		return procID;
	}

	public void setProcID(Integer procID) {
		this.procID = procID;
	}

	public String getTargetTableOwner() {
		return targetTableOwner;
	}

	public void setTargetTableOwner(String targetTableOwner) {
		this.targetTableOwner = targetTableOwner;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public Integer getTargetTableID() {
		return targetTableID;
	}

	public void setTargetTableID(Integer TargetTableID) {
		this.targetTableID = TargetTableID;
	}

	public String getTargetTableName() {
		return targetTableName;
	}

	public void setTargetTableName(String TargetTableName) {
		targetTableName = TargetTableName;
	}

	public String getTargetTableDesc() {
		return targetTableDesc;
	}

	public void setTargetTableDesc(String TargetTableDesc) {
		targetTableDesc = TargetTableDesc;
	}

	public String getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

	public String getUpdateField() {
		return UpdateField;
	}

	public void setUpdateField(String updateField) {
		UpdateField = updateField;
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

	public String getDeleteType() {
		return deleteType;
	}

	public void setDeleteType(String deleteType) {
		this.deleteType = deleteType;
	}

}
