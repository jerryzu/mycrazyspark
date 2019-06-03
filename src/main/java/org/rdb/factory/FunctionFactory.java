package org.rdb.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.Function;
import org.rdb.beans.Trigger;
import org.rdb.build.Functionbuilder;
import org.rdb.core.FreeMarkerExportWay;
import org.rdb.core.FreeMarkerFileType;
import org.rdb.core.SimpleFreeMarkerFactory;
import org.rdb.dao.FunctionDao;

public class FunctionFactory {
	static Map<String, Object> root = new HashMap<String, Object>();
	static private List<Function> functions;

	public static List<Function> getFunction() {
		return functions;
	}
	
	public static void main(String[] args) {
		BuildFunctions();
	}

	public static void done() {
//		functions = FunctionDao.selectAllN(functions);
//		functions = FunctionDao.selectAllNS(new Integer[]{501,503,601,603});
		functions = Functionbuilder.build();
		root.put("functionlist", functions);
	}

	public static void BuildFunctions() {
		done();
		SimpleFreeMarkerFactory.setExportInfo(FreeMarkerFileType.ora,
				FreeMarkerExportWay.screen);
		SimpleFreeMarkerFactory.analysisTemplate("function", "function.ftl",
				root);
	}
}
