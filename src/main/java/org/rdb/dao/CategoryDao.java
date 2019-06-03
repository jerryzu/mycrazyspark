package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.Category;
import org.rdb.beans.DBLink;

public interface CategoryDao extends Mapper {
	@Select("select * from SYS_CATEGORIES  order by catid")
	public List<Category> selectAll();
}
