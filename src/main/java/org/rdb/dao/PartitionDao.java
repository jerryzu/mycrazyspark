package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.core.Mapper;
import org.rdb.beans.Field;
import org.rdb.beans.Partition;

public interface PartitionDao  extends Mapper{
	@Select("select * from SYS_PARTITIONS")
	public List<Partition> selectAll();
	
	@Select("select * from SYS_PARTITIONS WHERE PARTTYPE = #{partType}")
	public Partition findByPartType(String partType);	
}
