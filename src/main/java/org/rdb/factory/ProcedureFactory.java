package org.rdb.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.rdb.beans.Procedure;
import org.rdb.build.procedure.Procedurebuilder;
import org.rdb.build.procedure.TaskProcedurebuilder;
import org.rdb.core.FreeMarkerExportWay;
import org.rdb.core.FreeMarkerFileType;
import org.rdb.core.SimpleFreeMarkerFactory;

public class ProcedureFactory {
	static Map<String, Object> root = new HashMap<String, Object>();
	static List<Procedure> procedures;

	public static List<Procedure> getProcedure() {
		return procedures;
	}

	public static void main(String[] args) throws Exception {
		BuildProcedures();
	}

	public static void BuildProcedures() {
		// SimpleFreeMarkerFactory.setExportInfo(FreeMarkerFileType.ora,
		// FreeMarkerExportWay.screen);

		SimpleFreeMarkerFactory.setExportInfo(FreeMarkerFileType.ora, FreeMarkerExportWay.screen);

		root.put("procedurelist", TaskProcedurebuilder.build());
		SimpleFreeMarkerFactory.doanalysis("procedure", "ProcTpsticTask.ftl", root);
		// SimpleFreeMarkerFactory.doanalysis("field", "column.ftl", root);

		SimpleFreeMarkerFactory.finish();

		// doc
		// SimpleFreeMarkerFactory.analysisTemplate("procedure", "procedure.ftl",
		// root);

		// SimpleFreeMarkerFactory.analysisTemplate("procedure",
		// "ProcJoin.ftl", root);
		//

		// OMFact--OM DataMart
		// SimpleFreeMarkerFactory.analysisTemplate("procedure",
		// "ProcOMFactDay.ftl",
		// root);

		// OMFact--OM Dim
		// SimpleFreeMarkerFactory.analysisTemplate("procedure", "ProcDim.ftl",
		// root);

		// Dim--SCD
		// SimpleFreeMarkerFactory.analysisTemplate("procedure",
		// "ProcDimSCD.ftl",
		// root);

		// OMMerge--OM DataWareHouse
		// SimpleFreeMarkerFactory.analysisTemplate("procedure",
		// "ProcOMMerge.ftl",
		// root);

		// OMMerge--OM DataWareHouse
		// SimpleFreeMarkerFactory.analysisTemplate("procedure",
		// "ProcOMMerge-test.ftl",
		// root);

		// ProcFactCode.ftl
		// SimpleFreeMarkerFactory.analysisTemplate("procedure",
		// "ProcFactCode.ftl", root);

		// ProcBigData
		// SimpleFreeMarkerFactory.analysisTemplate("procedure",
		// "ProcBigData.ftl", root);
		// SimpleFreeMarkerFactory.analysisTemplate("procedure",
		// "ProcBigData(RowType).ftl", root);

		// Task
		// SimpleFreeMarkerFactory.analysisTemplate("procedure",
		// "ProcTpsticForSCRC.ftl", root);

		// DBLink
		// SimpleFreeMarkerFactory.analysisTemplate("procedure",
		// "ProcDBLink.ftl",
		// root);

		// MergeSQL
		// SimpleFreeMarkerFactory.analysisTemplate("procedure",
		// "ProcOMMerge.ftl", root);

		// Dimension
		// SimpleFreeMarkerFactory.analysisTemplate("procedure",
		// "DimProcedure.ftl", root);

		// Task
		// SimpleFreeMarkerFactory.analysisTemplate("procedure",
		// "procedure.ftl",
		// root);
	}
}