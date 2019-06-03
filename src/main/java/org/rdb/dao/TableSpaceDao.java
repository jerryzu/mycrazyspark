package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.TableSpace;

public interface TableSpaceDao  extends Mapper{
	@Select("select * from SYS_TABLESPACES order by TableSpaceID asc")
	public List<TableSpace> selectAll();
}
