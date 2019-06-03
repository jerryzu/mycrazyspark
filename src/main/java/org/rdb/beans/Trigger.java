package org.rdb.beans;

import java.util.List;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_TRIGGERS")
public class Trigger {
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer triggerId;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String triggerOwner;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String triggerName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String tableOwner;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String tableName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String sequences;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String fieldName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String triggerDDL;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String triggerType; //SequencesTrigger VerifyTrigger

	private List<Field> fieldlist;

	public Integer getTriggerId() {
		return triggerId;
	}

	public String getTriggerOwner() {
		return triggerOwner;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public String getTableOwner() {
		return tableOwner;
	}

	public String getTableName() {
		return tableName;
	}

	public String getSequences() {
		return sequences;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getTriggerDDL() {
		return triggerDDL;
	}

	public String getTriggerType() {
		return triggerType;
	}

	public void setTriggerId(Integer triggerId) {
		this.triggerId = triggerId;
	}

	public void setTriggerOwner(String triggerOwner) {
		this.triggerOwner = triggerOwner;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public void setTableOwner(String tableOwner) {
		this.tableOwner = tableOwner;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setSequences(String sequences) {
		this.sequences = sequences;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setTriggerDDL(String triggerDDL) {
		this.triggerDDL = triggerDDL;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	public List<Field> getFieldlist() {
		return fieldlist;
	}

	public void setFieldlist(List<Field> fieldlist) {
		this.fieldlist = fieldlist;
	}
}
