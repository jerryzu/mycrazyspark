package org.rdb.beans;

import java.util.List;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLBoolean;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_TABLES")

public class Table {
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer tableID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String schemaName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String tableName;
	@SQLString(value = 1000, constrains = @Constrains(allowNull = true))
	private String tableDesc;
	@SQLBoolean
	private boolean Enabled;
	@SQLString(value = 64, constrains = @Constrains(allowNull = true))
	private String BizSys;
	@SQLString(value = 64, constrains = @Constrains(allowNull = true))
	private String TableLayer;
	@SQLString(value = 64, constrains = @Constrains(allowNull = true))
	private String UpdateMethod;
	@SQLString(value = 64, constrains = @Constrains(allowNull = true))
	private String MasterKey;
	@SQLString(value = 64, constrains = @Constrains(allowNull = true))
	private String UpdateField;
	@SQLString(value = 64, constrains = @Constrains(allowNull = true))
	private String Updatefrequency;
	@SQLInteger
	private Integer DeliverID;
	@SQLString(value = 1000, constrains = @Constrains(allowNull = true))
	private String remark;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String PartType;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String PartField;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String distributionKey;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String TopicName;
	
	private List<Field> fieldlist;
	private Partition partition;

	public Table(Partition partition) {
		this.partition = partition;
	}

	public Table() {
	}

	public Table(Integer TableID, String SchemaName, String TableName, String MasterKey,
			String TableDesc, String Remark, String distributionKey, Partition partition) {
		this.tableID = TableID;
		this.schemaName = SchemaName;
		this.tableName = TableName;
		this.MasterKey = MasterKey;
		this.tableDesc = TableDesc;
		this.remark = Remark;
		this.distributionKey = distributionKey;
		this.partition = partition;
	}

	public Integer getTableID() {
		return tableID;
	}

	public void setTableID(Integer TableID) {
		this.tableID = TableID;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String SchemaName) {
		this.schemaName = SchemaName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String TableName) {
		this.tableName = TableName;
	}

	public String getTableDesc() {
		return tableDesc;
	}

	public void setTableDesc(String TableDesc) {
		this.tableDesc = TableDesc;
	}

	public boolean isEnabled() {
		return Enabled;
	}

	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}

	public Integer getDeliverID() {
		return DeliverID;
	}

	public void setDeliverID(Integer deliverID) {
		DeliverID = deliverID;
	}

	public String getBizSys() {
		return BizSys;
	}

	public void setBizSys(String bizSys) {
		BizSys = bizSys;
	}

	public String getTableLayer() {
		return TableLayer;
	}

	public void setTableLayer(String tableLayer) {
		TableLayer = tableLayer;
	}

	public String getUpdateMethod() {
		return UpdateMethod;
	}

	public void setUpdateMethod(String updateMethod) {
		UpdateMethod = updateMethod;
	}

	public String getMasterKey() {
		return MasterKey;
	}

	public void setMasterKey(String masterKey) {
		MasterKey = masterKey;
	}

	public String getUpdateField() {
		return UpdateField;
	}

	public void setUpdateField(String updateField) {
		UpdateField = updateField;
	}

	public String getUpdatefrequency() {
		return Updatefrequency;
	}

	public void setUpdatefrequency(String updatefrequency) {
		Updatefrequency = updatefrequency;
	}

	public Integer getReftableID() {
		return DeliverID;
	}

	public void setReftableID(Integer reftableID) {
		DeliverID = reftableID;
	}

	public String getPartField() {
		return PartField;
	}

	public void setpartField(String partField) {
		this.PartField = partField;
	}

	public String getDistributionKey() {
		return distributionKey;
	}

	public void setdistributionKey(String distributionKey) {
		this.distributionKey = distributionKey;
	}

	
	public String getTopicName() {
		return TopicName;
	}

	public void settopicName(String topicName) {
		this.TopicName = topicName;
	}


	public Partition getPartition() {
		return partition;
	}

	public void setPartition(Partition partition) {
		this.partition = partition;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String Remark) {
		this.remark = Remark;
	}

	public String getPartType() {
		return PartType;
	}

	public void setPartType(String partType) {
		PartType = partType;
	}

	public List<Field> getFieldlist() {
		return fieldlist;
	}

	public void setFieldlist(List<Field> fieldlist) {
		this.fieldlist = fieldlist;
	}
}
