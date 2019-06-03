package org.core;

import org.apache.ibatis.annotations.Param;

public interface Mapper {
	public <T> T selectAll();
	public <T> T  findByID(@Param(value = "jobLists") Integer[] jobs);
}
