package org.rdb.build;

import java.util.List;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.DBLink;
import org.rdb.dao.DBLinkDao;

public class DBLinkbuilder {
	static DBLinkDao dbLinkDao;
	static List<DBLink> dbLinks;

	
	public static void init() {
		dbLinkDao = MapperFactory.createMapper(DBLinkDao.class, DataSourceEnvironment.platform);
	}

	public static List<DBLink> build() {
		init();
		dbLinks = dbLinkDao.selectAll();
		return dbLinks;
	}

	public static List<DBLink> buildbyName(String dbLinkName) {
		init();
//		dbLinks = dbLinkDao.findByName(dbLinkName);
		return dbLinks;
	}

	public static List<DBLink> buildbyID(Integer[] dbLinkIDs) {
		init();
		dbLinks = dbLinkDao.findByID(dbLinkIDs);
		return dbLinks;
	}
}
