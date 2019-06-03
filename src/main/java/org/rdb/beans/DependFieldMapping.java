package org.rdb.beans;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_FIELDMAPPING")
public class DependFieldMapping {
	@SQLInteger
	private Integer fieldPropID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String schemaName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String procName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String sourceTableOwner;
	@SQLInteger
	private Integer sourceTableID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String sourceTableName;
	@SQLInteger
	private Integer sourceFieldID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String sourceFieldName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String sourceAlias;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String sourceFieldDataType;
	@SQLInteger
	private Integer sourceFieldLen;
	@SQLString(value = 200, constrains = @Constrains(allowNull = true))
	private String sourceExpression;
	@SQLString(value = 64, constrains = @Constrains(allowNull = true))
	private String sourceFieldDesc;
	@SQLString(value = 1000, constrains = @Constrains(allowNull = true))
	private String sourceFieldRemark;
	@SQLInteger
	private Integer targetTableID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String targetTableOwner;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String targetTableName;
	@SQLInteger
	private Integer targetFieldID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String targetFieldName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String targetFieldDataType;
	@SQLInteger
	private Integer targetFieldLen;
	@SQLString(value = 200, constrains = @Constrains(allowNull = true))
	private String targetExpression;
	@SQLString(value = 64, constrains = @Constrains(allowNull = true))
	private String targetFieldDesc;
	@SQLString(value = 1000, constrains = @Constrains(allowNull = true))
	private String targetFieldRemark;
	@SQLString(value = 64, constrains = @Constrains(allowNull = true))
	private String fieldPropDesc;
	@SQLInteger
	private Integer status;
	@SQLString(value = 64, constrains = @Constrains(allowNull = true))
	private String sourceRefType;

	public Integer getFieldPropID() {
		return fieldPropID;
	}

	public void setFieldPropID(Integer fieldPropID) {
		this.fieldPropID = fieldPropID;
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

	public String getSourceTableOwner() {
		return sourceTableOwner;
	}

	public void setSourceTableOwner(String sourceTableOwner) {
		this.sourceTableOwner = sourceTableOwner;
	}

	public Integer getSourceTableID() {
		return sourceTableID;
	}

	public void setSourceTableID(Integer sourceTableID) {
		this.sourceTableID = sourceTableID;
	}

	public String getSourceTableName() {
		return sourceTableName;
	}

	public void setSourceTableName(String sourceTableName) {
		this.sourceTableName = sourceTableName;
	}

	public String getSourceAlias() {
		return sourceAlias;
	}

	public void setSourceAlias(String sourceAlias) {
		this.sourceAlias = sourceAlias;
	}

	public Integer getSourceFieldID() {
		return sourceFieldID;
	}

	public void setSourceFieldID(Integer sourceFieldID) {
		this.sourceFieldID = sourceFieldID;
	}

	public String getSourceFieldName() {
		return sourceFieldName;
	}

	public void setSourceFieldName(String sourceFieldName) {
		this.sourceFieldName = sourceFieldName;
	}

	public String getSourceFieldDataType() {
		return sourceFieldDataType;
	}

	public void setSourceFieldDataType(String sourceFieldDataType) {
		this.sourceFieldDataType = sourceFieldDataType;
	}

	public Integer getSourceFieldLen() {
		return sourceFieldLen;
	}

	public void setSourceFieldLen(Integer sourceFieldLen) {
		this.sourceFieldLen = sourceFieldLen;
	}

	public String getSourceExpression() {
		return sourceExpression;
	}

	public void setSourceExpression(String sourceExpression) {
		this.sourceExpression = sourceExpression;
	}

	public String getSourceFieldDesc() {
		return sourceFieldDesc;
	}

	public void setSourceFieldDesc(String sourceFieldDesc) {
		this.sourceFieldDesc = sourceFieldDesc;
	}

	public String getSourceFieldRemark() {
		return sourceFieldRemark;
	}

	public void setSourceFieldRemark(String sourceFieldRemark) {
		this.sourceFieldRemark = sourceFieldRemark;
	}

	public String getTargetTableOwner() {
		return targetTableOwner;
	}

	public void setTargetTableOwner(String targetTableOwner) {
		this.targetTableOwner = targetTableOwner;
	}

	public Integer getTargetTableID() {
		return targetTableID;
	}

	public void setTargetTableID(Integer targetTableID) {
		this.targetTableID = targetTableID;
	}

	public String getTargetTableName() {
		return targetTableName;
	}

	public void setTargetTableName(String targetTableName) {
		this.targetTableName = targetTableName;
	}

	public Integer getTargetFieldID() {
		return targetFieldID;
	}

	public void setTargetFieldID(Integer targetFieldID) {
		this.targetFieldID = targetFieldID;
	}

	public String getTargetFieldName() {
		return targetFieldName;
	}

	public void setTargetFieldName(String targetFieldName) {
		this.targetFieldName = targetFieldName;
	}

	public String getTargetFieldDataType() {
		return targetFieldDataType;
	}

	public void setTargetFieldDataType(String targetFieldDataType) {
		this.targetFieldDataType = targetFieldDataType;
	}

	public Integer getTargetFieldLen() {
		return targetFieldLen;
	}

	public void setTargetFieldLen(Integer targetFieldLen) {
		this.targetFieldLen = targetFieldLen;
	}

	public String getTargetExpression() {
		return targetExpression;
	}

	public void setTargetExpression(String targetExpression) {
		this.targetExpression = targetExpression;
	}

	public String getTargetFieldDesc() {
		return targetFieldDesc;
	}

	public void setTargetFieldDesc(String targetFieldDesc) {
		this.targetFieldDesc = targetFieldDesc;
	}

	public String getTargetFieldRemark() {
		return targetFieldRemark;
	}

	public void setTargetFieldRemark(String targetFieldRemark) {
		this.targetFieldRemark = targetFieldRemark;
	}

	public String getFieldPropDesc() {
		return fieldPropDesc;
	}

	public void setFieldPropDesc(String fieldPropDesc) {
		this.fieldPropDesc = fieldPropDesc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSourceRefType() {
		return sourceRefType;
	}

	public void setSourceRefType(String sourceRefType) {
		this.sourceRefType = sourceRefType;
	}
}