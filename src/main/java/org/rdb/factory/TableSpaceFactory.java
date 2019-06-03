package org.rdb.factory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.TableSpace;
import org.rdb.beans.TablespaceFile;
import org.rdb.beans.Trigger;
import org.rdb.build.TableSpacebuilder;
import org.rdb.core.FreeMarkerExportWay;
import org.rdb.core.FreeMarkerFileType;
import org.rdb.core.SimpleFreeMarkerFactory;
import org.rdb.dao.TableDao;
import org.rdb.dao.TableSpaceDao;
import org.rdb.dao.TablespaceFileDao;

public class TableSpaceFactory {
	static Map<String, Object> root = new HashMap<String, Object>();
	static private List<TableSpace> tableSpaces;

	public static List<TableSpace> getTableSpace() {
		return tableSpaces;
	}
	
	public static void main(String[] args) throws Exception {
		BuildTableSpaces();
	}
	
	public static void done() {		
		tableSpaces = TableSpacebuilder.build();
		root.put("tablespacelist", tableSpaces);
	}

	public static void BuildTableSpaces() {
		done();
		SimpleFreeMarkerFactory.setExportInfo(FreeMarkerFileType.ora,
				FreeMarkerExportWay.screen );
		SimpleFreeMarkerFactory.analysisTemplate("tablespace", "tablespace.ftl", root);
	}
}
