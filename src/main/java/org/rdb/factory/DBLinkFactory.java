package org.rdb.factory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.rdb.beans.DBLink;
import org.rdb.build.DBLinkbuilder;
import org.rdb.core.FreeMarkerExportWay;
import org.rdb.core.FreeMarkerFileType;
import org.rdb.core.SimpleFreeMarkerFactory;

public class DBLinkFactory {
	static Map<String, Object> root = new HashMap<String, Object>();
	static private List<DBLink> dbLinks;

	public static List<DBLink> getDbLinks() {
		return dbLinks;
	}

	public static void main(String[] args) {		
		System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径 
		BuildDbLinks();
	}

	public static void done() {
		dbLinks = DBLinkbuilder.build();
		root.put("dblinklist", dbLinks);
	}

	public static void BuildDbLinks() {
		done();
		SimpleFreeMarkerFactory.setExportInfo(FreeMarkerFileType.ora,
				FreeMarkerExportWay.screen);
		SimpleFreeMarkerFactory.analysisTemplate("dbLink", "DbLink.ftl", root);
	}
}