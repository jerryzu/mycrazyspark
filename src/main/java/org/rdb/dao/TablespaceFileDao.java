package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.TablespaceFile;

public interface TablespaceFileDao  extends Mapper{
	@Select("select * from SYS_TABLESPACEFILES")
	public List<TablespaceFile> selectAll();
	
    @Select("select * from SYS_TABLESPACEFILES where tableSpace=#{tableSpaceName}")
    public List<TablespaceFile>  findByTablespace(String tableSpaceName);
}
