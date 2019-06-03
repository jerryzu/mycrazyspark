package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.Procedure;
import org.rdb.beans.Table;

public interface ProcedureDao extends Mapper {
	@Select("select * from SYS_PROCEDURES  WHERE enabled = 1  order by procID asc")
	public List<Procedure> selectAll();
		
	@Select("select * from SYS_PROCEDURES where procID = #{procID}")
	public List<Procedure> findByProcID(Integer procID);

	@Select({ "<script>", "select * from SYS_PROCEDURES WHERE procID in ",
		"<foreach collection='procLists' item='proc' index='index' separator=','  open='(' close=')' >",
		"#{proc}", "</foreach>", " order by procID asc",
		"</script>"})
	public List<Procedure> findByID(@Param(value = "procLists") Integer[] procedures);
	
	@Select("select * from SYS_PROCEDURES where procName = #{procedureName}")
	public List<Procedure> findByProcedureName(String procedureName);

	@Select("select * from SYS_PROCEDURES where topicName = #{topicName}")
	public List<Procedure> findBytopicName(String topicName);
	
	@Select("select * from SYS_PROCEDURES where procName like #{procedureNameLike}")
	public List<Procedure> findByProcedureNameLike(String procedureNameLike);

	@Select("select * from SYS_PROCEDURES where objecttype = #{objecttype} and STATUS = 1 order by PROCID")
	public List<Procedure> findByObjectType(String objectType);		

	@Select("select * from SYS_PROCEDURES where procName not like '%_ETL'")
	public List<Procedure> findOrgTable();

	@Select("select * from SYS_PROCEDURES where procName like '%_ETL'")
	public List<Procedure> findETLTable();
	
	@Select("select * from SYS_PROCEDURES where PROCLAYER = #{procLayer} and STATUS = 1")
	public List<Procedure> findByProcLayer(String procLayer);
	
	@Select("select * from SYS_PROCEDURES where objecttype = 'DATAMERGE-SYNC' and STATUS = 1 order by PROCID")
	public List<Procedure> findBySyncLog();	

	@Insert("INSERT INTO SYS_PROCEDURES(PROCID, SCHEMANAME, PROCNAME, PROCDESC, AUTHOR, COMMENT"
			+ ", CREATETIME, ENABLED, LASTCHANGETIME, REMARK, OBJECTTYPE, PROCLAYER)"
			+ "VALUES(#{procID}, #{schemaName}, #{procName}, #{procDesc}, #{author}, #{comment}"
			+ ", #{createtime}, #{enabled}, #{lastchangetime}, #{remark}, #{objectType}, #{procLayer})")
	public void insert(Procedure proc);
}
