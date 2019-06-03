package org.datax.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.datax.beans.Job;

public interface JobDao extends Mapper {
	@Select("select * from DATAX_JOBS WHERE enabled = 1 order by jobID asc")
	public List<Job> selectAll();

	@Select("select * from DATAX_JOBS WHERE topicName = #{topicName} and jobID > 0 order by jobID asc")
	public List<Job> selectbyTopic(String topicName);

	@Select("select t.*, tp.TOPICNAME from DATAX_JOBS t INNER JOIN DATAX_TOPICS tp ON t.TOPIC = tp.TOPIC WHERE t.status = 1 order by tp.TOPICID, t.jobID asc")
	public List<Job> selectWithTopic();

	@Select("select * from DATAX_JOBS WHERE JOBLAYER <> 'DATAX' order by jobID asc")
	public List<Job> selectUserJob();

	@Select({ "<script>", "select * from DATAX_JOBS WHERE jobID in ",
			"<foreach collection='jobLists' item='job' index='index' separator=','  open='(' close=')' >", "#{job}",
			"</foreach>", " order by jobID asc", "</script>" })
	public List<Job> findByID(@Param(value = "jobLists") Integer[] jobs);

	@Select("select * from DATAX_JOBS WHERE SCHEMANAME = #{SCHEMANAME} order by jobID asc")
	public List<Job> findBySchema(String schemaName);

	@Select("select * from DATAX_JOBS WHERE DELIVERBIZSYS = #{deliverBizSys} order by jobID asc")
	public List<Job> findByDeliverBizSys(String deliverBizSys);

	@Select("select * from DATAX_JOBS WHERE JOBLAYER = #{jobLayer} order by rankno asc")
	public List<Job> findByJobLayer(String jobLayer);

	@Select("select * from DATAX_JOBS WHERE JOBNAME = #{jobName} order by jobID asc")
	public List<Job> findByName(String jobName);

	@Select("select * from DATAX_JOBS WHERE JOBNAME like #{jobName} order by jobID asc")
	public List<Job> findJobLike(String jobName);

	@Insert("insert into DATAX_JOBS(schemaName, jobID, jobName, remark, enabled, deliverID, joblayer) "
			+ "values(#{schemaName}, #{jobID}, #{jobName}, #{remark}, #{enabled}, #{deliverID}, #{jobLayer})")
	public int insert(Job job);
}
