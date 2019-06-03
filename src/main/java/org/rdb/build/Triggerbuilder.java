package org.rdb.build;

import java.util.Iterator;
import java.util.List;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.Field;
import org.rdb.beans.Trigger;
import org.rdb.dao.FieldDao;
import org.rdb.dao.TriggerDao;

public class Triggerbuilder {
	static FieldDao fieldDao;
	static TriggerDao triggerDao;
	static List<Trigger> triggers;
	static List<Field> fields;

	private static void GetFields(Trigger t) {
		fields = fieldDao.findByTable(t.getTableName());
		t.setFieldlist(fields);
	}

	public static void init() {
		triggerDao = MapperFactory.createMapper(TriggerDao.class, DataSourceEnvironment.platform);
		fieldDao = MapperFactory.createMapper(FieldDao.class, DataSourceEnvironment.platform);
	}

	public static List<Trigger> build() {
		init();
		triggers = triggerDao.selectAll();
		Iterator<Trigger> iter = triggers.iterator();
		while (iter.hasNext()) {
			Trigger trigger = iter.next();
			GetFields(trigger);
		}
		return triggers;
	}

	public static List<Trigger> buildbyName(String triggerName) {
		init();
		// triggers = triggerDao.findByDBLinkName(triggerName);
		return triggers;
	}

	public static List<Trigger> buildbyID(Integer[] triggerID) {
		init();
		// triggers = triggerDao.findByDBLinkIDs(triggerID);
		return triggers;
	}
}
