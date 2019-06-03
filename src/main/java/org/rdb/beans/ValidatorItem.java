package org.rdb.beans;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLBoolean;
import org.rdb.wrapper.SQLDouble;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_VALIDATORITEM")
public class ValidatorItem {
	@SQLInteger(constrains = @Constrains(unique = true))
	private Integer validatorID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String schemaName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String tableName;
	@SQLInteger(constrains = @Constrains(allowNull = true))
	private Integer fieldID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String fieldName;	
	@SQLBoolean
	private boolean isMasterKey;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String dataType;
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer maxLength;
	@SQLBoolean
	private boolean notNull;
	@SQLBoolean
	private boolean notBlank;
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer maxVal;
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer minVal;
	
	@SQLString(value = 1000, constrains = @Constrains(allowNull = true))
	private String valueRange;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String valueList;
	@SQLString(value = 4000, constrains = @Constrains(allowNull = true))
	private String Remark;

	public ValidatorItem() {
	}

	public Integer getValidatorID() {
		return validatorID;
	}

	public void setValidatorID(Integer validatorID) {
		this.validatorID = validatorID;
	}
	
	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getFieldID() {
		return fieldID;
	}

	public void setFieldID(Integer fieldID) {
		this.fieldID = fieldID;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public boolean isIsMasterKey() {
		return isMasterKey;
	}

	public void setIsMasterKey(boolean isMasterKey) {
		this.isMasterKey = isMasterKey;
	}
	
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public boolean isNotBlank() {
		return notBlank;
	}

	public void setNotBlank(boolean notBlank) {
		this.notBlank = notBlank;
	}

	public Integer getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(Integer maxVal) {
		this.maxVal = maxVal;
	}

	public Integer getMinVal() {
		return minVal;
	}

	public void setMinVal(Integer minVal) {
		this.minVal = minVal;
	}

	public String getValueRange() {
		return valueRange;
	}

	public void setValueRange(String valueRange) {
		this.valueRange = valueRange;
	}

	public String getValueList() {
		return valueList;
	}

	public void setValueList(String valueList) {
		this.valueList = valueList;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		this.Remark = remark;
	}

}
