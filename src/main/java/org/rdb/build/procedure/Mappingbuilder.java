package org.rdb.build.procedure;

import java.util.Iterator;
import java.util.List;

import org.rdb.beans.DependFieldMapping;
import org.rdb.beans.ProcDebug;
import org.rdb.beans.Procedure;

public class Mappingbuilder extends Procedurebuilder {
	public static List<Procedure> buildBytopicName(String topicName) {
		init();
		ProcDebug debug = new ProcDebug();
		procedures = procedureDao.findBytopicName(topicName);
		
		Iterator<Procedure> iter = procedures.iterator();
		while (iter.hasNext()) {
			Procedure p = iter.next();
			p.setDebug(debug);
			Getdependencies(p);//
			GetDependProcedureList(p);//
			GetDependFieldMapping(p);
			GetSourceRefSQLList(p);
		}
		return procedures;
	}
	public static List<Procedure> buildByName(String procName) {
		init();
		ProcDebug debug = new ProcDebug();
		procedures = procedureDao.findByProcedureName(procName);
		
		Iterator<Procedure> iter = procedures.iterator();
		while (iter.hasNext()) {
			Procedure p = iter.next();
			p.setDebug(debug);
			Getdependencies(p);//
			GetDependProcedureList(p);//
			GetDependFieldMapping(p);
			GetSourceRefSQLList(p);
		}
		return procedures;
	}
}
