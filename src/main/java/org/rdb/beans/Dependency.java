package org.rdb.beans;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLBoolean;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_DEPENDENCIES")
public class Dependency {
	@SQLInteger
	private Integer procID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String schemaName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String procName;
	@SQLInteger
	private Integer DependencyID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String DependencySchemaName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String DependencyLinkName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String DependencyName;
	@SQLString(value = 64, constrains = @Constrains(allowNull = true))
	private String DependencyDesc;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String ObjectType;
	@SQLInteger
	private Integer RankNO;
	@SQLBoolean
	private boolean Enabled;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String DependencyRefType;
	@SQLString(value = 200, constrains = @Constrains(allowNull = true))
	private String Remark;

	public Dependency() {
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

	public Integer getDependencyID() {
		return DependencyID;
	}

	public void setDependencyID(Integer dependencyID) {
		DependencyID = dependencyID;
	}


	public String getDependencySchemaName() {
		return DependencySchemaName;
	}

	public void setDependencySchemaName(String dependencySchemaName) {
		DependencySchemaName = dependencySchemaName;
	}

	public String getDependencyLinkName() {
		return DependencyLinkName;
	}

	public void setDependencyLinkName(String dependencyLinkName) {
		DependencyLinkName = dependencyLinkName;
	}
	
	public String getDependencyName() {
		return DependencyName;
	}

	public void setDependencyName(String dependencyName) {
		DependencyName = dependencyName;
	}

	public String getDependencyDesc() {
		return DependencyDesc;
	}

	public void setDependencyDesc(String dependencyDesc) {
		DependencyDesc = dependencyDesc;
	}

	public String getDependencyRefType() {
		return DependencyRefType;
	}

	public void setDependencyRefType(String dependencyRefType) {
		DependencyRefType = dependencyRefType;
	}

	public String getObjectType() {
		return ObjectType;
	}

	public void setObjectType(String objectType) {
		ObjectType = objectType;
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
