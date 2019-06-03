package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.ProcDebug;

public interface DebugDao  extends Mapper{
	@Select("select * from SYS_PROCDEBUG")
	public List<ProcDebug> selectAll();
}
