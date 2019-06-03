package org.datax.build;

import java.util.Iterator;
import java.util.List;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.datax.beans.Job;
import org.datax.dao.JobDao;
import org.rdb.beans.Table;

public class Jobbuilder {
	static JobDao jobDao;
	static List<Job> jobs;

	public static void init() {
		jobDao = MapperFactory.createMapper(JobDao.class, DataSourceEnvironment.platform);
	}

	public static List<Job> build() {
		init();
		jobs = jobDao.selectAll();
		return jobs;
	}

	public static List<Job> buildbyName(String jobName) {
		init();
		// jobs = jobDao.findByName(jobName);
		return jobs;
	}

	public static List<Job> buildbyID(Integer[] jobID) {
		init();
		// jobs = jobDao.findByID(jobID);
		return jobs;
	}
}
