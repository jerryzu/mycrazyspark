package org.rdb.expert.export;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.Table;

public interface OracleTableDao extends Mapper {
	@Select("SELECT M.OWNER SCHEMANAME, M.TABLE_NAME TABLENAME, D.COMMENTS REMARK "
			+ "FROM ALL_TABLES M LEFT JOIN ALL_TAB_COMMENTS D "
			+ "	ON M.OWNER = D.OWNER AND M.TABLE_NAME = D.TABLE_NAME")
	public List<Table> selectAll();

	@Select("SELECT M.OWNER SCHEMANAME, M.TABLE_NAME TABLENAME, D.COMMENTS REMARK "
			+ "FROM ALL_TABLES M LEFT JOIN ALL_TAB_COMMENTS D "
			+ "	ON M.OWNER = D.OWNER AND M.TABLE_NAME = D.TABLE_NAME "
			+ "WHERE M.OWNER = #{SchemaName}")
	public List<Table> findBySchema(String schemaName);

	@Select("SELECT M.OWNER SCHEMANAME, M.TABLE_NAME TABLENAME, D.COMMENTS REMARK "
			+ "FROM ALL_TABLES M LEFT JOIN ALL_TAB_COMMENTS D "
			+ "	ON M.OWNER = D.OWNER AND M.TABLE_NAME = D.TABLE_NAME "
			+ "WHERE M.TABLE_NAME = #{tableName}")
	public List<Table> findByTableName(String tableName);
}
