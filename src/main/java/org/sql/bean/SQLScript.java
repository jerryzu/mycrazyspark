package org.sql.bean;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLBoolean;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "RUL_SQLSCRIPT")
public class SQLScript {
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer seq;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String cde;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String name;
	@SQLString(value = 200, constrains = @Constrains(allowNull = true))
	private String sqltxt;
	@SQLBoolean(caption = "是否启用")
	private boolean Enabled;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String Remark;
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getCde() {
		return cde;
	}
	public void setCde(String cde) {
		this.cde = cde;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSqltxt() {
		return sqltxt;
	}
	public void setSqltxt(String sqltxt) {
		this.sqltxt = sqltxt;
	}
	public boolean isEnabled() {
		return Enabled;
	}
	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
}