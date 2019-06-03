package org.rdb.beans;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_FUNCTIONS")
public class Function {
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer FncID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String FncName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String SchemaName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String FncDesc;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String Remark;
	
	public Function(Integer fncID, String schemaName, String fncName, String fncDesc, String remark) {
		this.FncID = fncID;
		this.SchemaName = schemaName;
		this.FncName = fncName;
		this.FncDesc = fncDesc;
		this.Remark = remark;
	}
	public Function(Integer fncID) {
		this.FncID = fncID;
	}
	
	
	public Integer getFncID() {
		return FncID;
	}

	public void setFncID(Integer fncID) {
		this.FncID = fncID;
	}

	public String getFncName() {
		return FncName;
	}

	public void setFncName(String fncName) {
		this.FncName = fncName;
	}

	public String getSchemaName() {
		return SchemaName;
	}

	public void setSchemaName(String schemaName) {
		this.SchemaName = schemaName;
	}

	public String getFncDesc() {
		return FncDesc;
	}

	public void setFncDesc(String fncDesc) {
		this.FncDesc = fncDesc;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		this.Remark = remark;
	}

}