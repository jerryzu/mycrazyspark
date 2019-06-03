package org.rdb.build.procedure;

import java.util.Iterator;
import java.util.List;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.DependFieldMapping;
import org.rdb.beans.DependJoinText;
import org.rdb.beans.DependProcedure;
import org.rdb.beans.DependSourceRefSQL;
import org.rdb.beans.DependSourceTable;
import org.rdb.beans.DependTargetTable;
import org.rdb.beans.Dependency;
import org.rdb.beans.Field;
import org.rdb.beans.ProcDebug;
import org.rdb.beans.ProcEvent;
import org.rdb.beans.Procedure;
import org.rdb.beans.Table;
import org.rdb.dao.DependencyDao;
import org.rdb.dao.ProcEventDao;
import org.rdb.dao.ProcedureDao;

public class Procedurebuilder {
	static ProcedureDao procedureDao;
	static DependencyDao dependencyDao;
	static ProcEventDao procEventDao;
	static List<Procedure> procedures;

	public static void init() {
		dependencyDao = MapperFactory.createMapper(DependencyDao.class, DataSourceEnvironment.platform);
		procedureDao = MapperFactory.createMapper(ProcedureDao.class, DataSourceEnvironment.platform);
		procEventDao = MapperFactory.createMapper(ProcEventDao.class, DataSourceEnvironment.platform);
	}

	public static List<Procedure> build() {
		init();
		ProcDebug debug = new ProcDebug();
		procedures = procedureDao.selectAll();
		Iterator<Procedure> iter = procedures.iterator();
		while (iter.hasNext()) {
			Procedure p = iter.next();
			p.setDebug(debug);
			Getdependencies(p);
			// GetDependJoinText(p);
			// GetSourceTableList(p);
			// GetTargetTableList(p);
			GetDependFieldMapping(p);
			// GetMasterKeyMapping(p);
			// GetGroupByField(p);
			GetDependProcedureList(p);
			// GetprocEvents(p);
			GetSourceRefSQLList(p);
		}
		return procedures;
	}

	public static List<Procedure> buildbyName(String procedureName) {
		init();
		// procedures = procedureDao.findByName(procedureName);
		return procedures;
	}

	public static List<Procedure> buildbyID(Integer[] procedureIDs) {
		init();
		ProcDebug debug = new ProcDebug();
		procedures = procedureDao.findByID(procedureIDs);// RANGE

		Iterator<Procedure> iter = procedures.iterator();
		while (iter.hasNext()) {
			Procedure p = iter.next();
			p.setDebug(debug);
			Getdependencies(p);
			// GetDependJoinText(p);
			// GetSourceTableList(p);
			// GetTargetTableList(p);
			GetDependFieldMapping(p);
			// GetMasterKeyMapping(p);
			// GetGroupByField(p);
			GetDependProcedureList(p);
			// GetprocEvents(p);
			GetSourceRefSQLList(p);
		}
		return procedures;
	}

	public static void GetprocEvents(Procedure p) {
		List<ProcEvent> procEvents = procEventDao.findByProcedure(p.getProcName());
		p.setProcEventlist(procEvents);
	}

	public static void Getdependencies(Procedure p) {
		List<Dependency> dependencys = dependencyDao.Getdependencies(p.getProcName());
		p.setDependencylist(dependencys);
	}

	public static void GetDependJoinText(Procedure p) {
		List<DependJoinText> dependJoinTexts = dependencyDao.GetDependJoinText(p.getProcName());
		p.setDependJoinTextlist(dependJoinTexts);
	}

	public static void GetDependFieldMapping(Procedure p) {
		List<DependFieldMapping> fieldlist = dependencyDao.GetDependFieldMapping(p.getProcName());
		p.setDependFieldMappinglist(fieldlist);
	}

	public static void GetMasterKeyMapping(Procedure p) {
		List<DependFieldMapping> fieldlist = dependencyDao.GetMasterKeyMapping(p.getProcName());
		p.setMasterKeyMappinglist(fieldlist);
	}

	public static void GetAggrField(Procedure p) {
		List<DependFieldMapping> fieldlist = dependencyDao.GetAggrField(p.getProcName());
		p.setMasterKeyMappinglist(fieldlist);
	}

	public static void GetGroupByField(Procedure p) {
		List<DependFieldMapping> groupByFieldlist = dependencyDao.GetGroupByField(p.getProcName());
		p.setGroupByFieldlist(groupByFieldlist);
	}

	public static void GetIncCaptureFields(DependSourceTable sourceTable) {
		List<Field> fields = dependencyDao.GetSourceTableIncCaptureFields(sourceTable.getsourceTableName());
		sourceTable.setIncCaptureFieldlist(fields);
	}

	public static void GetSourceTableList(Procedure p) {
		List<DependSourceTable> sourceTablelist = dependencyDao.GetDependSourceTable(p.getProcName());
		Iterator<DependSourceTable> iter = sourceTablelist.iterator();
		while (iter.hasNext()) {
			DependSourceTable sourceTable = iter.next();
			GetIncCaptureFields(sourceTable);
		}
		p.setDependSourceTablelist(sourceTablelist);
	}

	public static void GetTargetTableList(Procedure p) {
		List<DependTargetTable> targetTablelist = dependencyDao.GetDependTargetTable(p.getProcName());
		p.setDependTargetTablelist(targetTablelist);
	}

	public static void GetSourceRefSQLList(Procedure p) {
		List<DependSourceRefSQL> targetTablelist = dependencyDao.GetDependSourceRefSQL(p.getProcName());
		p.setSourceRefSQLList(targetTablelist);
	}

	public static void GetDependProcedureList(Procedure p) {
		List<DependProcedure> dependProcedurelist = dependencyDao.GetDependProcedure(p.getProcName());
		p.setDependProcedurelist(dependProcedurelist);
	}

}
