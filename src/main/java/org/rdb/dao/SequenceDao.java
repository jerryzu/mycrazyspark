package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.Field;
import org.rdb.beans.Sequence;

public interface SequenceDao extends Mapper {
	@Select("SELECT SEQUENCEID, SEQUENCENAME, SCHEMANAME, MINVALUE, MAXVALUE_, INCREMENT_ , START_  FROM SYS_SEQUENCES order by sequenceID")
	public List<Sequence> selectAll();

}
