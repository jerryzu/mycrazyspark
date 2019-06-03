package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.core.Mapper;
import org.rdb.beans.DependFieldMapping;
import org.rdb.beans.ValidatorItem;

public interface ValidatorItemDao  extends Mapper{
	@Select("select * from SYS_VALIDATORITEM  order by ValidatorID, itemID")
	public List<ValidatorItem> selectAll();

	@Select("select * from SYS_VALIDATORITEM WHERE tableName = #{tableName} order by ValidatorID, itemID")
	public List<ValidatorItem> findByTable(String tableName);

	@Select("select * from SYS_VALIDATORITEM WHERE tableName = #{tableName} and isMasterKey = 1 order by ValidatorID, itemID")
	public List<ValidatorItem> GetmasterKeyItems(String tableName);
}