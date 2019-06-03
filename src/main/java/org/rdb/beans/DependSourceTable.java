package org.rdb.beans;

import java.util.List;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

public class DependSourceTable {
	private Integer procID;
	private String sourceTableOwner;
	private String procName;
	private Integer sourceTableID;
	private String sourceTableName;
	private String sourceTableDesc;
	private String objectType;
	private String UpdateField;
	private String MasterKey;
	private String BizKey;
	private String remark;
	private List<Field> IncCaptureFieldlist;
	
	public DependSourceTable() {
	}

	public Integer getProcID() {
		return procID;
	}

	public void setProcID(Integer procID) {
		this.procID = procID;
	}

	public String getSourceTableOwner() {
		return sourceTableOwner;
	}

	public void setSourceTableOwner(String sourceTableOwner) {
		this.sourceTableOwner = sourceTableOwner;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public Integer getsourceTableID() {
		return sourceTableID;
	}

	public void setsourceTableID(Integer sourceTableID) {
		this.sourceTableID = sourceTableID;
	}

	public String getsourceTableName() {
		return sourceTableName;
	}

	public void setsourceTableName(String SourceTableName) {
		sourceTableName = SourceTableName;
	}

	public String getsourceTableDesc() {
		return sourceTableDesc;
	}

	public void setsourceTableDesc(String SourceTableDesc) {
		sourceTableDesc = SourceTableDesc;
	}
	
	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String ObjectType) {
		objectType = ObjectType;
	}

	public String getUpdateField() {
		return UpdateField;
	}

	public void setUpdateField(String updateField) {
		UpdateField = updateField;
	}

	public String getMasterKey() {
		return MasterKey;
	}

	public void setMasterKey(String masterKey) {
		MasterKey = masterKey;
	}

	public String getBizKey() {
		return BizKey;
	}

	public void setBizKey(String bizKey) {
		BizKey = bizKey;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String Remark) {
		remark = Remark;
	}
	
	public List<Field> getIncCaptureFieldlist() {
		return IncCaptureFieldlist;
	}

	public void setIncCaptureFieldlist(
			List<Field> incCaptureFieldlist) {
		this.IncCaptureFieldlist = incCaptureFieldlist;
	}
}
