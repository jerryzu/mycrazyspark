package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.Dependency;

public interface DependTargetTableDao  extends Mapper{
	@Select("select * from VW_DEPENDTARGETTABLES")
	public List<Dependency> selectAll();
	
	@Select("select * from VW_DEPENDTARGETTABLES WHERE procName = #{procName}")
	public List<Dependency> findByProcedure(String procName);

}
