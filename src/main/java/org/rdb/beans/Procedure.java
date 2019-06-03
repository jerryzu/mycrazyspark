package org.rdb.beans;

import java.util.Date;
import java.util.List;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLBoolean;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;
import org.rdb.wrapper.SQLTimestamp;

@DBTable(name = "SYS_PROCEDURES")
public class Procedure {
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer procID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String schemaName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String procName;
	@SQLString(value = 64, constrains = @Constrains(allowNull = true))
	private String procDesc;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String author;
	@SQLString(value = 200, constrains = @Constrains(allowNull = true))
	private String comment;
	@SQLTimestamp
	private Date createtime;
	@SQLBoolean
	private Boolean Enabled;
	@SQLTimestamp
	private Date lastchangetime;
	@SQLString(value = 64, constrains = @Constrains(allowNull = true))
	private String remark;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String ObjectType;
	@SQLString(value = 24, constrains = @Constrains(allowNull = true))
	private String ProcLayer;
	@SQLString(value = 24, constrains = @Constrains(allowNull = true))
	private String logSyncWay;
	private ProcDebug debug;
	private List<Dependency> dependencylist;
	private List<DependProcedure> dependProcedurelist;
	private List<DependTargetTable> dependTargetTablelist;
	private List<DependSourceTable> dependSourceTablelist;
	private List<DependFieldMapping> dependFieldMappinglist;
	private List<DependFieldMapping> masterKeyMappinglist;
	private List<DependJoinText> dependJoinTextlist;
	private List<ProcEvent> procEventlist;
	private List<DependFieldMapping> groupByFieldlist;
	private List<DependSourceRefSQL> sourceRefSQLList;	
	
	
	public List<DependSourceRefSQL> getSourceRefSQLList() {
		return sourceRefSQLList;
	}

	public void setSourceRefSQLList(List<DependSourceRefSQL> sourceRefSQLList) {
		this.sourceRefSQLList = sourceRefSQLList;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public String getProcDesc() {
		return procDesc;
	}

	public void setProcDesc(String procDesc) {
		this.procDesc = procDesc;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	public Date getLastchangetime() {
		return lastchangetime;
	}

	public void setLastchangetime(Date lastchangetime) {
		this.lastchangetime = lastchangetime;
	}

	public Integer getProcID() {
		return procID;
	}

	public void setProcID(Integer ProcID) {
		this.procID = ProcID;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String SchemaName) {
		this.schemaName = SchemaName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String Remark) {
		remark = Remark;
	}

	public ProcDebug getProcDebug() {
		return debug;
	}

	public void setProcDebug(ProcDebug debug) {
		this.debug = debug;
	}

	public String getObjectType() {
		return ObjectType;
	}

	public void setObjectType(String objectType) {
		ObjectType = objectType;
	}

	public ProcDebug getDebug() {
		return debug;
	}

	public void setDebug(ProcDebug debug) {
		this.debug = debug;
	}

	public String getProcLayer() {
		return ProcLayer;
	}

	public void setProcLayer(String procLayer) {
		ProcLayer = procLayer;
	}

	public String getLogSyncWay() {
		return logSyncWay;
	}

	public void setLogSyncWay(String logSyncWay) {
		this.logSyncWay = logSyncWay;
	}
	
	public List<Dependency> getDependencylist() {
		return dependencylist;
	}

	public void setDependencylist(List<Dependency> dependencylist) {
		this.dependencylist = dependencylist;
	}

	public Boolean getEnabled() {
		return Enabled;
	}

	public void setEnabled(Boolean enabled) {
		Enabled = enabled;
	}

	public List<DependProcedure> getDependProcedurelist() {
		return dependProcedurelist;
	}

	public void setDependProcedurelist(List<DependProcedure> dependProcedurelist) {
		this.dependProcedurelist = dependProcedurelist;
	}

	public List<DependSourceTable> getDependSourceTablelist() {
		return dependSourceTablelist;
	}

	public void setDependSourceTablelist(
			List<DependSourceTable> dependSourceTablelist) {
		this.dependSourceTablelist = dependSourceTablelist;
	}
	
	public List<DependTargetTable> getDependTargetTablelist() {
		return dependTargetTablelist;
	}

	public void setDependTargetTablelist(
			List<DependTargetTable> dependTargetTablelist) {
		this.dependTargetTablelist = dependTargetTablelist;
	}

	public List<DependFieldMapping> getDependFieldMappinglist() {
		return dependFieldMappinglist;
	}

	public List<DependFieldMapping> getGroupByFieldlist() {
		return groupByFieldlist;
	}
	
	public void setDependFieldMappinglist(
			List<DependFieldMapping> dependFieldMappinglist) {
		this.dependFieldMappinglist = dependFieldMappinglist;
	}


	public List<DependFieldMapping> getMasterKeyMappinglist() {
		return masterKeyMappinglist;
	}

	public void setMasterKeyMappinglist(
			List<DependFieldMapping> masterKeyMappinglist) {
		this.masterKeyMappinglist = masterKeyMappinglist;
	}
	
	
	public List<DependJoinText> getDependJoinTextlist() {
		return dependJoinTextlist;
	}

	public void setDependJoinTextlist(List<DependJoinText> dependJoinTextlist) {
		this.dependJoinTextlist = dependJoinTextlist;
	}

	public List<ProcEvent> getProcEventlist() {
		return procEventlist;
	}

	public void setProcEventlist(List<ProcEvent> procEventlist) {
		this.procEventlist = procEventlist;
	}

	public void setGroupByFieldlist(List<DependFieldMapping> groupByFieldlist) {
		this.groupByFieldlist = groupByFieldlist;
		
	}
}
