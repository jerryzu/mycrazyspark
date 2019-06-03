package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.ProcEvent;

public interface ProcEventDao  extends Mapper{
	@Select("select * from SYS_PROCEVENTS ORDER BY PROCID ")
	public List<ProcEvent> selectAll();

	@Select("select * from SYS_PROCEVENTS WHERE procName = #{procName} AND OBJECTTYPE = 'TARGETTABLE'")
	public List<ProcEvent> findByProcedure(String procName);
}
