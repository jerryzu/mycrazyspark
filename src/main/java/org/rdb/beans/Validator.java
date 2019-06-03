package org.rdb.beans;

import java.util.Date;
import java.util.List;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLBoolean;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;
import org.rdb.wrapper.SQLTimestamp;

@DBTable(name = "SYS_VALIDATORS")
public class Validator {
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer validatorID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String validatorSchema;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String validatorName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String tableSchema;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String tableName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	@SQLTimestamp
	private Date createtime;
	private String remark;
	private ProcDebug debug;
	
	private List<ValidatorItem> validatorItems;
	private List<ValidatorItem> masterKeyItems;	
	
	public Validator() {
	
	}

	public Integer getValidatorID() {
		return validatorID;
	}

	public void setValidatorID(Integer validatorID) {
		this.validatorID = validatorID;
	}

	public String getValidatorSchema() {
		return validatorSchema;
	}

	public void setValidatorschema(String validatorSchema) {
		this.validatorSchema = validatorSchema;
	}

	public String getValidatorName() {
		return validatorName;
	}

	public void setValidatorname(String validatorName) {
		this.validatorName = validatorName;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ProcDebug getDebug() {
		return debug;
	}

	public void setDebug(ProcDebug debug) {
		this.debug = debug;
	}

	public void setValidatorSchema(String validatorSchema) {
		this.validatorSchema = validatorSchema;
	}

	public void setValidatorName(String validatorName) {
		this.validatorName = validatorName;
	}
	public List<ValidatorItem> getValidatorItems() {
		return validatorItems;
	}

	public void setValidatorItems(List<ValidatorItem> validatorItems) {
		this.validatorItems = validatorItems;
	}
}
