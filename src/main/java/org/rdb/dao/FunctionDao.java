package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.Function;

public interface FunctionDao extends Mapper {
	@Select("SELECT FNCID, SCHEMANAME, FNCNAME, FNCDESC, AUTHOR, COMMENT, CREATETIME, ENABLED, LASTCHANGETIME, REMARK, OBJECTTYPE, FNCLAYER, LOGSYNCWAY FROM SYS_FUNCTIONS order by FNCID")
	public List<Function> selectAllQ();

	@SuppressWarnings("unchecked")
	@Select("SELECT FNCID, SCHEMANAME, FNCNAME, FNCDESC, REMARK FROM SYS_FUNCTIONS WHERE  enabled = 1 order by FNCID")
	public List<Function> selectAll();

	@Select("SELECT FNCID, SCHEMANAME, FNCNAME, FNCDESC, REMARK FROM SYS_FUNCTIONS WHERE  topicName =  #{topicName}  AND enabled = 1 order by FNCID")
	public List<Function> selectbyTopic(String topicName);

	@Select({ "<script>", "SELECT FNCID, SCHEMANAME, FNCNAME, FNCDESC, REMARK FROM SYS_FUNCTIONS WHERE FNCID in ",
			"<foreach collection='fncLists' item='fnc' index='index' separator=',' open='(' close=')' >",
			"#{fnc.FncID}", "</foreach>", " order by FNCID", "</script>" })
	public List<Function> selectAllN(@Param(value = "fncLists") List<Function> fncLists);

	@Select({ "<script>", "SELECT FNCID, SCHEMANAME, FNCNAME, FNCDESC, REMARK FROM SYS_FUNCTIONS WHERE FNCID in ",
			"<foreach collection='fncLists' item='fnc' index='index' separator=',' open='(' close=')' >", "#{fnc}",
			"</foreach>", " order by FNCID", "</script>" })
	public List<Function> selectAllNS(@Param(value = "fncLists") Integer[] funcs);

	@SuppressWarnings("unchecked")
	@Select({ "<script>", "SELECT FNCID, SCHEMANAME, FNCNAME, FNCDESC, REMARK FROM SYS_FUNCTIONS WHERE FNCID in ",
		"<foreach collection='fncLists' item='fnc' index='index' separator=',' open='(' close=')' >", "#{fnc}",
		"</foreach>", " order by FNCID", "</script>" })
	public List<Function> findByID(@Param(value = "fncLists") Integer[] funcs);
}
