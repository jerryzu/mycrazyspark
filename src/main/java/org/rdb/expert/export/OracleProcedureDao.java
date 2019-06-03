package org.rdb.expert.export;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.Procedure;

public interface OracleProcedureDao extends Mapper  {
	
	@Select("SELECT OBJECT_NAME procName FROM USER_PROCEDURES WHERE OBJECT_TYPE = 'PROCEDURE'")
	public List<Procedure> selectToInfa();

}
