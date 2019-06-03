package org.rdb.beans;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLBoolean;
import org.rdb.wrapper.SQLDouble;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_FIELDS")
public class Field {
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String schemaName;
	@SQLInteger(constrains = @Constrains(unique = true))
	private Integer tableID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String tableName;
	@SQLBoolean
	private boolean IsMasterKey;
	@SQLInteger(constrains = @Constrains(allowNull = true))
	private Integer FieldID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String FieldName;
	@SQLString(value = 1000, constrains = @Constrains(allowNull = true))
	private String FieldDesc;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String DataType;
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer FieldLen;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String CharUsed;
	@SQLDouble(caption = "数值数据的长度")
	private double Scale_;
	@SQLDouble(caption = "数值数据的精度小数位", Precision = 0, name = "Precision_")
	private double Precision_;
	@SQLBoolean
	private boolean Nullable;
	@SQLString(value = 4000, constrains = @Constrains(allowNull = true))
	private String Remark;
	@SQLInteger
	private Integer DeliverTableID;
	@SQLInteger(constrains = @Constrains(allowNull = true))
	private Integer DeliverFieldID;
	@SQLInteger(constrains = @Constrains(allowNull = true))
	private Integer Status;

	public Field() {
	}

	public Field(Integer fieldID, String fieldName, String fieldDesc,
			String dataType, Integer fieldLen, double precision, double scale,
			boolean Nullable, Integer Status, String remark) {
		this.FieldID = fieldID;
		this.FieldName = fieldName;
		this.FieldDesc = fieldDesc;
		this.DataType = dataType;
		this.FieldLen = fieldLen;
		this.Precision_ = precision;
		this.Scale_ = scale;
		this.Nullable = Nullable;
		this.Status = Status;
		this.Remark = remark;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public Integer getTableID() {
		return tableID;
	}

	public void setTableID(Integer tableID) {
		this.tableID = tableID;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public boolean isIsMasterKey() {
		return IsMasterKey;
	}

	public void setIsMasterKey(boolean isMasterKey) {
		IsMasterKey = isMasterKey;
	}

	public Integer getFieldID() {
		return FieldID;
	}

	public void setFieldID(Integer fieldID) {
		this.FieldID = fieldID;
	}

	public String getFieldName() {
		return FieldName;
	}

	public void setFieldName(String fieldName) {
		this.FieldName = fieldName;
	}

	public String getFieldDesc() {
		if (FieldDesc == null || FieldDesc == "") {
			return FieldDesc;
		} else {
			return FieldDesc.replace("'", "''");
		}
	}

	public void setFieldDesc(String fieldDesc) {
		FieldDesc = fieldDesc;
	}

	public String getDataType() {
		return DataType;
	}

	public void setDataType(String dataType) {
		this.DataType = dataType;
	}

	public Integer getFieldLen() {
		return FieldLen;
	}

	public void setFieldLen(Integer fieldLen) {
		this.FieldLen = fieldLen;
	}

	public String getCharUsed() {
		return CharUsed;
	}

	public void setCharUsed(String charUsed) {
		CharUsed = charUsed;
	}

	public double getPrecision_() {
		return Precision_;
	}

	public void setPrecision_(double precision) {
		this.Precision_ = precision;
	}

	public double getScale_() {
		return Scale_;
	}

	public void setScale_(double scale) {
		this.Scale_ = scale;
	}

	public boolean isNullable() {
		return Nullable;
	}

	public void setNullable(boolean nullable) {
		this.Nullable = nullable;
	}

	public Integer getDeliverTableID() {
		return DeliverTableID;
	}

	public void setDeliverTableID(Integer deliverTableID) {
		this.DeliverTableID = deliverTableID;
	}

	
	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		this.Status = status;
	}
	
	
	public Integer getDeliverFieldID() {
		return DeliverFieldID;
	}

	public void setDeliverFieldID(Integer deliverFieldID) {
		this.DeliverFieldID = deliverFieldID;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		this.Remark = remark;
	}

}
