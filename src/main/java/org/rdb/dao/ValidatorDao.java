package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.Validator;

public interface ValidatorDao extends Mapper {
	@Select("select * from SYS_VALIDATORS order by validatorID asc")
	public List<Validator> selectAll();
	
	@Select("select * from SYS_VALIDATORS WHERE SCHEMANAME = #{SCHEMANAME} order by validatorID asc")
	public List<Validator> findBySchema(String schemaName);
}
