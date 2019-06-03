package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.core.Mapper;
import org.rdb.beans.Trigger;

public interface TriggerDao extends Mapper {
	@Select("select * from SYS_TRIGGERS  order by TriggerID")
	public List<Trigger> selectAll();

	@Select("select * from SYS_TRIGGERS WHERE TriggerName = #{triggerName} order by TriggerID")
	public List<Trigger> findByTrigger(String triggerName);

	@Select("select * from SYS_TRIGGERS WHERE triggerType = #{triggerType} order by TriggerID")
	public List<Trigger> findByVerifyTrigger(String triggerType);
}