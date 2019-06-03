package org.rdb.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.rdb.beans.Table;
import org.rdb.build.Tablebuilder;
import org.rdb.core.FreeMarkerExportWay;
import org.rdb.core.FreeMarkerFileType;
import org.rdb.core.SimpleFreeMarkerFactory;

public class TableFactory {
	static Map<String, Object> root = new HashMap<String, Object>();
	static List<Table> tables;

	public static void main(String[] args) {
		BuildTables();
	}

	public static void done() {
		tables = Tablebuilder.build();
		root.put("tablelist", tables);
	}

	public static void BuildTables() {
//		done();
		SimpleFreeMarkerFactory.setExportInfo(FreeMarkerFileType.mysql, FreeMarkerExportWay.screen, null);

		tables = Tablebuilder.build();
//		root.put("tablelist", tables);
//		SimpleFreeMarkerFactory.doanalysis("table", "table.ftl", root);
//
//		tables = Tablebuilder.buildbyName("LOAD_HIS_LOG");
//		root.put("tablelist", tables);
//		SimpleFreeMarkerFactory.doanalysis("table", "table.ftl", root);
//
//		tables = Tablebuilder.buildbyID(new Integer[]{1,3,2,4});
//		root.put("tablelist", tables);
//		SimpleFreeMarkerFactory.doanalysis("table", "table.ftl", root);

		
		// tables = Tablebuilder.buildbyTopic("AIO");
		root.put("tablelist", tables);
		SimpleFreeMarkerFactory.doanalysis("table", "table.ftl", root);

		SimpleFreeMarkerFactory.finish();		
	}
}
