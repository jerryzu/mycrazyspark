package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.Synonym;

public interface SynonymDao  extends Mapper{
	@Select("select * from SYS_SYNONYMS where enabled = 1 order by synonymID asc")
	public List<Synonym> selectAll();
}
