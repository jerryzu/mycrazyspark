package org.sql.builder;

import java.util.List;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.sql.bean.SQLScript;
import org.sql.dao.SQLScriptDao;

public class SQLScriptbuilder {
	static SQLScriptDao sqlScriptDao;
	static List<SQLScript> sqlScripts;

	public static void init() {
		sqlScriptDao = MapperFactory.createMapper(SQLScriptDao.class, DataSourceEnvironment.txbs);
	}

	public static List<SQLScript> build() {
		init();
		sqlScripts = sqlScriptDao.selectAll();
		return sqlScripts;
	}

	public static List<SQLScript> buildbyName(String dbLinkName) {
		init();
		return sqlScripts;
	}

	public static List<SQLScript> buildbyID(Integer[] dbLinkID) {
		init();
		return sqlScripts;
	}

}
