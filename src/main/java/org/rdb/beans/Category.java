package org.rdb.beans;

import java.util.List;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLBoolean;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_CATEGORIES")
public class Category {
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer CatID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String CatName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String CatCaption;
	@SQLBoolean
	private boolean Enabled;
	@SQLString(value = 200, constrains = @Constrains(allowNull = true))
	private String CatURL;
	@SQLString(value = 2000, constrains = @Constrains(allowNull = true))
	private String CatDesc;

	public Integer getCatID() {
		return CatID;
	}

	public void setCatID(Integer dbLinkID) {
		CatID = dbLinkID;
	}

	public String getCatName() {
		return CatName;
	}

	public void setCatName(String dbLinkName) {
		CatName = dbLinkName;
	}

	public String getCatCaption() {
		return CatCaption;
	}

	public void setCatCaption(String catCaption) {
		CatCaption = catCaption;
	}

	public boolean isEnabled() {
		return Enabled;
	}

	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}

	public String getCatURL() {
		return CatURL;
	}

	public void setCatURL(String catURL) {
		CatURL = catURL;
	}

	public String getCatDesc() {
		return CatDesc;
	}

	public void setHost(String catDesc) {
		CatDesc = catDesc;
	}
}
