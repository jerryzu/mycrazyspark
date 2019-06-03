package org.sql.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.sql.bean.SQLScript;

public interface SQLScriptDao extends Mapper {
	@Select("select * from RUL_SQLSCRIPT WHERE enabled = 1 order by rulID asc")
	public List<SQLScript> selectAll();
//	public <T> T selectAll();
	
//	@Select("select * from RUL_SQLSCRIPT WHERE topicName = #{topicName} and rulID > 0 order by rulID asc")
//	public List<SQLScript> selectbyTopic(String topicName);
//
//	@Select("select t.*, tp.TOPICNAME from RUL_SQLSCRIPT t INNER JOIN DATAX_TOPICS tp ON t.TOPIC = tp.TOPIC WHERE t.status = 1 order by tp.TOPICID, t.rulID asc")
//	public List<SQLScript> selectWithTopic();
//
//	@Select("select * from RUL_SQLSCRIPT WHERE JOBLAYER <> 'DATAX' order by rulID asc")
//	public List<SQLScript> selectUserSQLScript();
//
//	@Select({ "<script>", "select * from RUL_SQLSCRIPT WHERE rulID in ",
//			"<foreach collection='jobLists' item='job' index='index' separator=','  open='(' close=')' >", "#{job}",
//			"</foreach>", " order by rulID asc", "</script>" })
//	public List<SQLScript> findByID(@Param(value = "jobLists") Integer[] jobs);
//
//	@Select("select * from RUL_SQLSCRIPT WHERE SCHEMANAME = #{SCHEMANAME} order by rulID asc")
//	public List<SQLScript> findBySchema(String schemaName);
//
//	@Select("select * from RUL_SQLSCRIPT WHERE DELIVERBIZSYS = #{deliverBizSys} order by rulID asc")
//	public List<SQLScript> findByDeliverBizSys(String deliverBizSys);
//
//	@Select("select * from RUL_SQLSCRIPT WHERE JOBLAYER = #{jobLayer} order by rankno asc")
//	public List<SQLScript> findBySQLScriptLayer(String jobLayer);
//
//	@Select("select * from RUL_SQLSCRIPT WHERE JOBNAME = #{jobName} order by rulID asc")
//	public List<SQLScript> findByName(String jobName);
//
//	@Select("select * from RUL_SQLSCRIPT WHERE JOBNAME like #{jobName} order by rulID asc")
//	public List<SQLScript> findSQLScriptLike(String jobName);
}
