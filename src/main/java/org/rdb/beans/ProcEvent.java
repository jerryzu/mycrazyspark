package org.rdb.beans;

import java.util.Date;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLBoolean;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;
import org.rdb.wrapper.SQLTimestamp;

@DBTable(name = "SYS_PROCEVENTS")
public class ProcEvent {
	@SQLInteger
	private Integer procID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String schemaName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String procName;
	@SQLBoolean
	private boolean enabled;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String eventType;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String objectType;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String objectName;
	@SQLString(value = 64, constrains = @Constrains(allowNull = true))
	private String execSQL;
	@SQLTimestamp
	private Date createTime;
	@SQLTimestamp
	private Date lastChangeTime;
	@SQLString(value = 200, constrains = @Constrains(allowNull = true))
	private String remark;

	public ProcEvent() {
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getExecSQL() {
		return execSQL;
	}

	public void setExecSQL(String execSQL) {
		this.execSQL = execSQL;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastChangeTime() {
		return lastChangeTime;
	}

	public void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}