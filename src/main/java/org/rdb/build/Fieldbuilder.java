package org.rdb.build;

import java.util.List;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.Field;
import org.rdb.dao.FieldDao;

public class Fieldbuilder {
	static FieldDao fieldDao;
	static List<Field> fields;

	public static void init() {
		fieldDao = MapperFactory.createMapper(FieldDao.class, DataSourceEnvironment.platform);
	}

	public static List<Field> build() {
		init();
		fields = fieldDao.selectAll();
		return fields;
	}

	public static List<Field> buildbyTableID(Integer tableID) {
		init();
		fields = fieldDao.findByTableID(tableID);
		return fields;
	}

	public static List<Field> buildbyName(String fieldName) {
		init();
		// fields = fieldDao.findByName(fieldName);
		return fields;
	}

	public static List<Field> buildbyID(Integer[] fieldID) {
		init();
		// fields = fieldDao.findByID(fieldID);
		return fields;
	}
}
