package org.rdb.core;

import org.apache.commons.lang3.StringUtils;

import org.apache.ibatis.session.SqlSessionFactory;

public class ObjectUtils {

	public static boolean IsNotNull(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		boolean flag = true;
		if (null == sqlSessionFactory) {
			flag = false;
		} else if (flag = StringUtils
				.isBlank(String.valueOf(sqlSessionFactory))) {
			flag = false;
		}
		return flag;
	}

}
