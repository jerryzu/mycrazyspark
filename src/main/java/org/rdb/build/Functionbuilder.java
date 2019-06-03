package org.rdb.build;

import java.util.Iterator;
import java.util.List;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.Function;
import org.rdb.beans.Table;
import org.rdb.dao.FunctionDao;

public class Functionbuilder {

	static FunctionDao functionDao;
	static private List<Function> functions;

  public static void init() {
		functionDao = MapperFactory.createMapper(FunctionDao.class,
				DataSourceEnvironment.platform);
	}

	public static List<Function> build() {
		init();
		functions = functionDao.selectAll();
		return functions;
	}
	
	public static List<Function> buildbyTopic(String topicName) {
		init();
		functions = functionDao.selectbyTopic(topicName);
		return functions;
	}
		
	public static List<Function> buildbyName(String functionName) {
		init();
//		functions = FunctionDao.findByName(functionName);
		return functions;
	}

	public static List<Function> buildbyID(Integer[] functionIDs) {
		init();
		functions = functionDao.findByID(functionIDs);// RANGE
		return functions;		
	}
}
