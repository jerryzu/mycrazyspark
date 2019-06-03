package org.rdb.build.procedure;

import java.util.Iterator;
import java.util.List;

import org.rdb.beans.ProcDebug;
import org.rdb.beans.Procedure;

public class TaskProcedurebuilder extends Procedurebuilder {
	public static List<Procedure> buildBytopicName(String topicName)  {
		init();
		ProcDebug debug = new ProcDebug();
		procedures = procedureDao.findBytopicName(topicName);
		Iterator<Procedure> iter = procedures.iterator();
		while (iter.hasNext()) {
			Procedure p = iter.next();
			p.setDebug(debug);
			Getdependencies(p);//
			dependencyDao.Getdependencies(p.getProcName());
			GetDependProcedureList(p);//
			dependencyDao.GetDependProcedure(p.getProcName());
		}
		return procedures;
	}
}
