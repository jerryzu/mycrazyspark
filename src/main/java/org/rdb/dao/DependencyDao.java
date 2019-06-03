package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.DependFieldMapping;
import org.rdb.beans.DependJoinText;
import org.rdb.beans.DependProcedure;
import org.rdb.beans.DependSourceRefSQL;
import org.rdb.beans.DependSourceTable;
import org.rdb.beans.DependTargetTable;
import org.rdb.beans.Dependency;
import org.rdb.beans.Field;

public interface DependencyDao  extends Mapper{
	@Select("select * from SYS_DEPENDENCIES ORDER BY PROCID ")
	public List<Dependency> selectAll();

	@Select("select * from SYS_DEPENDENCIES WHERE procName = #{procName}")
	public List<Dependency> Getdependencies(String procName);
	
	@Select("select * from SYS_DEPENDENCIE_JOIN_TEXT WHERE procName = #{procName}")
	public List<DependJoinText> GetDependJoinText(String procName);	

	@Select("select * from SYS_FIELDMAPPING WHERE procName = #{procName} and status <> 0 ORDER BY targettableid, targetFIELDid ")
	public List<DependFieldMapping> GetDependFieldMapping(String procName);
	
//	@Select("select * from SYS_FIELDMAPPING WHERE procName = #{procName} and isMasterKey = 1 ORDER BY targettableid, targetFIELDid ")
//	public List<DependFieldMapping> GetMasterKeyMapping(String procName);
	
	@Select("select * from VW_PROFILE_SOURCE WHERE procName = #{procName} and isMasterKey = 1 ORDER BY SOURCETABLEID, SOURCEFIELDID ")
	public List<DependFieldMapping> GetMasterKeyMapping(String procName);
	
	
	@Select("select * from SYS_FIELDMAPPING WHERE procName = #{procName} and SOURCEREFTYPE = 'GROUPBY' ORDER BY targettableid, targetFIELDid ")
	public List<DependFieldMapping> GetGroupByField(String procName);
	
	@Select("select * from SYS_FIELDMAPPING WHERE procName = #{procName} and SOURCEREFTYPE = 'SUM' ORDER BY targettableid, targetFIELDid ")
	public List<DependFieldMapping> GetAggrField(String procName);
	

	@Select("select * from VW_TARGETTABLE WHERE procName = #{procName}")
	public List<DependTargetTable> GetDependTargetTable(String procName);
	
	@Select("select * from SYS_DEPENDENCIES WHERE procName = #{procName} and OBJECTTYPE = 'SOURCEREFSQL'")
	public List<DependSourceRefSQL> GetDependSourceRefSQL(String procName);

	@Select("select * from VW_SOURCETABLE WHERE procName = #{procName}")
	public List<DependSourceTable> GetDependSourceTable(String procName);
	

	@Select("select SCHEMANAME, TABLEID, TABLENAME, FIELDID, FIELDNAME, FIELDDESC from SYS_TABLE_INCCAPTUREFIELDS WHERE TableName = #{tableName} ORDER BY TableID, FieldID ")
	public List<Field> GetSourceTableIncCaptureFields(String tableName);

	@Select("select * from SYS_DEPENDENCIES WHERE procName = #{procName} and objecttype = 'PROCEDURE' ORDER BY RANKNO" )
	public List<DependProcedure> GetDependProcedure(String procName);

	@Insert("INSERT INTO sys_dependencies (PROCID, SCHEMANAME, PROCNAME, "
			+ "DEPENDENCYID, DEPENDENCYNAME, DEPENDENCYDESC, OBJECTTYPE, REMARK)"
			+ "VALUES(#{procID}, #{schemaName}, #{procName}"
			+ ", #{dependencyID}, #{dependencyName}, #{dependencyDesc}, #{objectType}, #{remark})")
	public void insert(Dependency dependency);
}
