package org.datax.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.datax.beans.Job;
import org.datax.build.Jobbuilder;
import org.rdb.core.FreeMarkerExportWay;
import org.rdb.core.FreeMarkerFileType;
import org.rdb.core.SimpleFreeMarkerFactory;

public class JobFactory {
	static Map<String, Object> root = new HashMap<String, Object>();
	static private List<Job> jobs;

	public static List<Job> getDbLinks() {
		return jobs;
	}

	public static void main(String[] args) {		
		System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径 
		BuildDbLinks();
	}

	public static void done() {
		jobs = Jobbuilder.build();
		root.put("joblist", jobs);
	}

	public static void BuildDbLinks() {
		done();
		SimpleFreeMarkerFactory.setExportInfo(FreeMarkerFileType.datax,
				FreeMarkerExportWay.screen);
		SimpleFreeMarkerFactory.doanalysis("job", "ora2mysql.ftl", root);
		SimpleFreeMarkerFactory.doanalysis("job", "ora2ora-test.ftl", root);

		SimpleFreeMarkerFactory.finish();
	}
}