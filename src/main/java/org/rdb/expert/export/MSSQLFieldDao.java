package org.rdb.expert.export;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.rdb.beans.Field;

public interface MSSQLFieldDao {
	@Select("SELECT TABSCHEMA SCHEMANAME, TABNAME TABLENAME, COLNAME FIELDNAME, COLNO FIELDID"
			+ ", TYPENAME DATATYPE, LENGTH FIELDLEN, LENGTH PRECISION,SCALE "
			+ " FROM SYSCAT.COLUMNS")
	public List<Field> selectAll();

	@Select("SELECT TABSCHEMA SCHEMANAME, TABNAME TABLENAME, COLNAME FIELDNAME, COLNO FIELDID"
			+ ", TYPENAME DATATYPE, LENGTH FIELDLEN, LENGTH PRECISION,SCALE "
			+ " FROM SYSCAT.COLUMNS" + " WHERE TABNAME = #{tableName}")
	public List<Field> findByTable(String tableName);

	@Select("SELECT TABSCHEMA SCHEMANAME, TABNAME TABLENAME, COLNAME FIELDNAME, COLNO FIELDID"
			+ ", TYPENAME DATATYPE, LENGTH FIELDLEN, LENGTH PRECISION,SCALE "
			+ " FROM SYSCAT.COLUMNS" + " WHERE TABSCHEMA = #{SchemaName}")
	public List<Field> findBySchema(String SchemaName);
}
