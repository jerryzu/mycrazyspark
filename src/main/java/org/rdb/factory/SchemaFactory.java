package org.rdb.factory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.rdb.beans.DBLink;
import org.rdb.beans.Right;
import org.rdb.beans.TableSpace;
import org.rdb.beans.TablespaceFile;
import org.rdb.beans.User;
import org.rdb.build.DBLinkbuilder;
import org.rdb.build.Fieldbuilder;
import org.rdb.build.Functionbuilder;
import org.rdb.build.Sequencebuilder;
import org.rdb.build.Synonymbuilder;
import org.rdb.build.TableSpacebuilder;
import org.rdb.build.Tablebuilder;
import org.rdb.build.Triggerbuilder;
import org.rdb.build.Userbuilder;
import org.rdb.build.Validatorbuilder;
import org.rdb.build.procedure.Mappingbuilder;
import org.rdb.build.procedure.Procedurebuilder;
import org.rdb.build.procedure.TaskProcedurebuilder;
import org.rdb.core.FreeMarkerExportWay;
import org.rdb.core.FreeMarkerFileType;
import org.rdb.core.SimpleFreeMarkerFactory;
import org.rdb.dao.DBLinkDao;
import org.rdb.dao.ProcEventDao;
import org.rdb.dao.TableSpaceDao;
import org.rdb.dao.UserDao;

public class SchemaFactory {
	static Map<String, Object> root = new HashMap<String, Object>();

	public static void main(String[] args) {
		// root.put("fieldlist", Fieldbuilder.build());
//		BuildSchema();
		BuildSchemaDoc();
	}

	public static void BuildSchema() {
		SimpleFreeMarkerFactory.setExportInfo(FreeMarkerFileType.ora, FreeMarkerExportWay.screen);

		// /*系统初始化*/
		// root.put("uselist", Userbuilder.build());
		// SimpleFreeMarkerFactory.doanalysis("user", "user.ftl", root);
		//
		// root.put("dblinklist", DBLinkbuilder.build());
		// SimpleFreeMarkerFactory.doanalysis("dbLink", "DbLink.ftl", root);
		//
		// root.put("validatorlist", Validatorbuilder.build());
		// SimpleFreeMarkerFactory.doanalysis("validator", "validator.ftl", root);
		//
		// root.put("triggerlist", Triggerbuilder.build());
		// SimpleFreeMarkerFactory.doanalysis("trigger", "TrgVerify.ftl", root);
		//
		// root.put("tablespacelist", TableSpacebuilder.build());
		// SimpleFreeMarkerFactory.doanalysis("tablespace", "tablespace.ftl", root);
		//
		// root.put("sequencelist", Sequencebuilder.build());
		// SimpleFreeMarkerFactory.doanalysis("sequence", "sequence.ftl", root);
		//
		// root.put("synonymlist", Synonymbuilder.build());
		// SimpleFreeMarkerFactory.doanalysis("synonym", "synonym.ftl", root);
		//
		// /*不分机构上报原始表,搭建测试环境,初始化数据库使用*/
		// root.put("tablelist", Tablebuilder.buildbyTopic("AIO"));
		// SimpleFreeMarkerFactory.doanalysis("table", "table.ftl", root);

		// root.put("tablelist", Tablebuilder.buildbyTopic("BIZ-DPT"));
		// SimpleFreeMarkerFactory.doanalysis("table", "table.ftl", root);
		/* 分机构上报更新角本 */
		// root.put("tablelist", Tablebuilder.buildbyTopic("AIO-SRC-EDW_PDM"));
		// SimpleFreeMarkerFactory.doanalysis("table", "table.ftl", root);

		// root.put("tablelist", Tablebuilder.buildbyName("WEB_PLY_COUNT_QUOTA_DPT"));
		// SimpleFreeMarkerFactory.doanalysis("table", "table.ftl", root);

		root.put("functionlist", Functionbuilder.buildbyTopic("DPT"));
		SimpleFreeMarkerFactory.doanalysis("function", "function.ftl", root);

		root.put("tablelist", Tablebuilder.findTableTopicLike("%DPT"));
		SimpleFreeMarkerFactory.doanalysis("table", "table.ftl", root);

		root.put("procedurelist", Mappingbuilder.buildBytopicName("DPT-DF"));
		// root.put("procedurelist",
		// Mappingbuilder.buildByName("P_WEB_PLY_COUNT_QUOTA_DPT"));
		SimpleFreeMarkerFactory.doanalysis("procedure", "ProcTpsticForSCRC.ftl", root);
		// SimpleFreeMarkerFactory.doanalysis("procedure", "ProcTpsticForSCRC-test.ftl",
		// root);

		// root.put("procedurelist", TaskProcedurebuilder.buildBytopicName("DPT-WF"));
		// SimpleFreeMarkerFactory.doanalysis("procedure", "ProcTpsticTask.ftl", root);

		SimpleFreeMarkerFactory.finish();
	}

	public static void BuildSchemaDoc() {
		SimpleFreeMarkerFactory.setExportInfo(FreeMarkerFileType.doc, FreeMarkerExportWay.file);

		// root.put("tablelist", Tablebuilder.findTableTopicLike("%DPT"));
		// SimpleFreeMarkerFactory.doanalysis("table", "table.ftl", root);

		root.put("tablelist", Tablebuilder.findTableTopicLike("%DPT"));
		SimpleFreeMarkerFactory.doanalysis("table", "table-xls.ftl", root);

		// root.put("procedurelist", Procedurebuilder.build());
		// SimpleFreeMarkerFactory.doanalysis("procedure", "procedure.ftl", root);
		
		SimpleFreeMarkerFactory.finish();
	}

}