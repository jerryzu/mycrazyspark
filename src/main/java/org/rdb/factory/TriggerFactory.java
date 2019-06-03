package org.rdb.factory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.Field;
import org.rdb.beans.Trigger;
import org.rdb.build.Triggerbuilder;
import org.rdb.build.Validatorbuilder;
import org.rdb.core.FreeMarkerExportWay;
import org.rdb.core.FreeMarkerFileType;
import org.rdb.core.SimpleFreeMarkerFactory;
import org.rdb.dao.FieldDao;
import org.rdb.dao.TriggerDao;

public class TriggerFactory {
	static Map<String, Object> root = new HashMap<String, Object>();
	static List<Trigger> triggers;

	public static void main(String[] args) {
		BuildTriggers();
	}

	public static void done() {
		triggers = Triggerbuilder.build();
		root.put("triggerlist", triggers);
	}

	public static void BuildTriggers() {
		done();
		SimpleFreeMarkerFactory.setExportInfo(FreeMarkerFileType.ora,
				FreeMarkerExportWay.screen);
		SimpleFreeMarkerFactory
				.analysisTemplate("trigger", "TrgVerify.ftl", root);
	}

	public static List<Trigger> getTrigger() {
		return triggers;
	}
}
