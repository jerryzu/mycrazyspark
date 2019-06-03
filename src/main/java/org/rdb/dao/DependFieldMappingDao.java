package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.core.Mapper;
import org.rdb.beans.DependFieldMapping;
import org.rdb.beans.Field;

public interface DependFieldMappingDao  extends Mapper{
	@Insert("INSERT INTO SYS_FIELDMAPPING(FIELDPROPID, SCHEMANAME, PROCNAME, SOURCETABLEOWNER, "
			+ "SOURCETABLEID, SOURCETABLENAME, SOURCEFIELDID, SOURCEFIELDNAME, SOURCEFIELDDATATYPE, "
			+ "SOURCEFIELDLEN, SOURCEEXPRESSION, SOURCEFIELDDESC, SOURCEFIELDREMARK, "
			+ "TARGETTABLEID, TARGETTABLEOWNER, TARGETTABLENAME, TARGETFIELDID, TARGETFIELDNAME, "
			+ "TARGETFIELDDATATYPE, TARGETFIELDLEN, TARGETEXPRESSION, TARGETFIELDDESC, TARGETFIELDREMARK, FIELDPROPDESC, STATUS)"
			+ " VALUES(#{fieldPropID}, #{schemaName}, #{procName}, #{sourceTableOwner}, "
			+ "#{sourceTableID}, #{sourceTableName}, #{sourceFieldID}, #{sourceFieldName}, "
			+ "#{sourceFieldDataType}, #{sourceFieldLen}, #{sourceExpression}, #{sourceFieldDesc}, #{sourceFieldRemark}, "
			+ "#{targetTableID}, #{targetTableOwner}, #{targetTableName}, #{targetFieldID}, #{targetFieldName}, "
			+ "#{targetFieldDataType}, #{targetFieldLen}, #{targetExpression}, #{targetFieldDesc}, #{targetFieldRemark}, "
			+ "#{fieldPropDesc}, #{status})")
	public int insert(DependFieldMapping map);

	@Update("UPDATE SYS_FIELDMAPPING SET fieldPropID = #{fieldPropID}, schemaName = #{schemaName}, "
			+ "procName = #{procName}, sourceTableOwner = #{sourceTableOwner}, "
			+ "sourceTableID = #{sourceTableID}, sourceTableName = #{sourceTableName}, "
			+ "sourceFieldID = #{sourceFieldID}, sourceFieldName = #{sourceFieldName}, "
			+ "sourceFieldDataType = #{sourceFieldDataType}, sourceFieldLen = #{sourceFieldLen}, "
			+ "sourceExpression = #{sourceExpression}, sourceFieldDesc = #{sourceFieldDesc}, , sourceFieldRemark = #{sourceFieldRemark}, "
			+ "targetTableID = #{targetTableID}, targetTableOwner = #{targetTableOwner}, "
			+ "targetTableName = #{targetTableName}, targetFieldID = #{targetFieldID}, "
			+ "targetFieldName = #{targetFieldName}, targetFieldDataType = #{targetFieldDataType}, "
			+ "targetFieldLen = #{targetFieldLen}, targetExpression = #{targetExpression}, "
			+ "targetFieldDesc = #{targetFieldDesc}, targetFieldRemark = #{targetFieldRemark}, fieldPropDesc = #{fieldPropDesc}, status = #{status}")
	public int update(DependFieldMapping map);

	@Delete("delete from SYS_FIELDMAPPING where map=#{map}")
	public int delete(String map);

	@Select("select * from SYS_FIELDMAPPING order by mapID asc")
	public List<DependFieldMapping> selectAll();
	
	@Select("select * from SYS_FIELDMAPPINGTEMPLATE WHERE TABLELAYER = #{tableLayer} order by SOURCEFIELDID asc")
	public List<DependFieldMapping> findFromTemplate(String tableLayer);	

	@Select("select count(*) c from SYS_FIELDMAPPING")
	public int countAll();

	@Select("select * from SYS_FIELDMAPPING where map=#{map}")
	public DependFieldMapping findByDependFieldMappingName(String map);	
}
