package org.rdb.expert.export;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.rdb.beans.Table;
import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.SQLBoolean;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

public interface MSSQLTableDao {
	@Select("SELECT TABSCHEMA SCHEMANAME,TABNAME TABLENAME,REMARKS REMARK FROM SYSCAT.TABLES")
	public List<Table> selectAll();

	@Select("SELECT TABSCHEMA SCHEMANAME,TABNAME TABLENAME,REMARKS REMARK FROM SYSCAT.TABLES WHERE TABSCHEMA = #{SCHEMANAME}")
	public List<Table> findBySchema(String schemaName);

	@Select("SELECT TABSCHEMA SCHEMANAME,TABNAME TABLENAME,REMARKS REMARK FROM SYSCAT.TABLES WHERE TABLENAME = #{tableName}")
	public List<Table> findByTableName(String tableName);
}