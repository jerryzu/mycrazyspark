package org.rdb.build;

import java.util.Iterator;
import java.util.List;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.TableSpace;
import org.rdb.beans.TablespaceFile;
import org.rdb.dao.TableSpaceDao;
import org.rdb.dao.TablespaceFileDao;

public class TableSpacebuilder {
	static TableSpaceDao tableSpaceDao;
	static TablespaceFileDao tableSpaceFileDao;
	static private List<TableSpace> tableSpaces;

	public static void GetTablespaceFiles(TableSpace tbspc) {
		List<TablespaceFile> tablespaceFiles = tableSpaceFileDao.findByTablespace(tbspc.getTableSpaceName());
		tbspc.setTablespaceFilelist(tablespaceFiles);
	}

	public static void init() {
		tableSpaceDao = MapperFactory.createMapper(TableSpaceDao.class, DataSourceEnvironment.platform);
		tableSpaceFileDao = MapperFactory.createMapper(TablespaceFileDao.class, DataSourceEnvironment.platform);
	}

	public static List<TableSpace> build() {
		init();
		tableSpaces = tableSpaceDao.selectAll();
		Iterator<TableSpace> iter = tableSpaces.iterator();
		while (iter.hasNext()) {
			TableSpace tbspc = iter.next();
			GetTablespaceFiles(tbspc);
		}
		return tableSpaces;
	}

	public static List<TableSpace> buildbyName(String tableSpaceName) {
		init();
		// tableSpaces = tableSpaceDao.findByName(tableSpaceName);
		return tableSpaces;
	}

	public static List<TableSpace> buildbyID(Integer[] tableSpaceID) {
		init();
		// tableSpaces = tableSpaceDao.findByID(tableSpaceID);
		return tableSpaces;
	}
}
