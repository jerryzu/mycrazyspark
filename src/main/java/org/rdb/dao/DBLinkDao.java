package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.DBLink;

public interface DBLinkDao extends Mapper {
	@Select("select * from SYS_DBLINKS  order by dblinkid")
	public List<DBLink> selectAll();
}
