package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.MarkerObject;

public interface MarkerObjectDao  extends Mapper{
	@Select("select * from SYS_MARKEROBJECT order by markerObjectID asc")
	public List<MarkerObject> selectAll();
}
