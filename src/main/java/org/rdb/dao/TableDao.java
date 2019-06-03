package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.Table;

public interface TableDao extends Mapper {
//	@Select("select * from sys_tables WHERE TABLEID IS NOT NULL order by tableID asc")
	@Select("select * from sys_tables WHERE enabled = 1 order by tableID asc")
	public List<Table> selectAll();
	
	@Select("select * from sys_tables WHERE topicName = #{topicName} order by tableID asc")
	public List<Table> selectbyTopic(String topicName);
	
	@Select("select * from sys_tables WHERE topicName like #{topicName} order by tableID asc")
	public List<Table> findTableTopicLike(String topicName);
	
	@Select("select t.*, tp.TOPICNAME from sys_tables t INNER JOIN SYS_TOPICS tp ON t.TOPIC = tp.TOPIC WHERE t.status = 1 order by tp.TOPICID, t.tableID asc")
	public List<Table> selectWithTopic();
	
	@Select("select * from sys_tables WHERE TABLELAYER <> 'SYS' order by tableID asc")
	public List<Table> selectUserTable();
	
	@Select({ "<script>", "select * from sys_tables WHERE tableID in ",
			"<foreach collection='tabLists' item='tab' index='index' separator=','  open='(' close=')' >",
			"#{tab}", "</foreach>", " order by tableID asc",
			"</script>"})
	public List<Table> findByID(@Param(value = "tabLists") Integer[] tables);

	@Select("select * from sys_tables WHERE SCHEMANAME = #{SCHEMANAME} order by tableID asc")
	public List<Table> findBySchema(String schemaName);

	@Select("select * from sys_tables WHERE DELIVERBIZSYS = #{deliverBizSys} order by tableID asc")
	public List<Table> findByDeliverBizSys(String deliverBizSys);
	
	@Select("select * from sys_tables WHERE TABLELAYER = #{tableLayer} order by rankno asc")
	public List<Table> findByTableLayer(String tableLayer);
	
	@Select("select * from sys_tables WHERE TABLENAME = #{tableName} order by tableID asc")
	public List<Table> findByName(String tableName);
	
	@Select("select * from sys_tables WHERE TABLENAME like #{tableName} order by tableID asc")
	public List<Table> findTableLike(String tableName);	
	
	@Insert("insert into sys_tables(schemaName, tableID, tableName, remark, enabled, deliverID, tablelayer) "
			+ "values(#{schemaName}, #{tableID}, #{tableName}, #{remark}, #{enabled}, #{deliverID}, #{tableLayer})")
	public int insert(Table table);
}
