package org.rdb.expert;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.Field;
import org.rdb.beans.Table;
import org.rdb.dao.FieldDao;
import org.rdb.dao.TableDao;
import org.rdb.expert.export.OracleFieldDao;
import org.rdb.expert.export.OracleTableDao;

public class DBSchemaExport {
	public static void main(String args[]) throws IOException {
		GetTables("DCS_ODS");
//		GetFields("DCS_ODS");
	}

	private static void GetTables(String Schema) {
		OracleTableDao tableDaoFrom = MapperFactory.createMapper(OracleTableDao.class,
				DataSourceEnvironment.development);
		TableDao tableDaoTo = MapperFactory.createMapper(TableDao.class,
				DataSourceEnvironment.platform);
		List<Table> tablesFrom = tableDaoFrom.findBySchema("ACTUARY");
//		List<Table> tablesFrom = tableDaoFrom.selectAll();
		
		Iterator<Table> iterTable = tablesFrom.iterator();
		while (iterTable.hasNext()) {
			Table t = iterTable.next();
			t.setEnabled(false);

			if (t.getRemark() == null) {
				t.setRemark("");
			} else if ("".equals(t.getRemark())) {
				t.setRemark("");
			}
			Logger logger1 = Logger.getLogger("console");
			System.out.println(t.getTableName());
			tableDaoTo.insert(t);
			
		}
	}

	private static void GetFields(String Schema) {
		OracleFieldDao fieldDaoFrom = MapperFactory.createMapper(OracleFieldDao.class,
				DataSourceEnvironment.qa);
		FieldDao fieldDaoTo = MapperFactory.createMapper(FieldDao.class,
				DataSourceEnvironment.test);
		List<Field> fieldsFrom = fieldDaoFrom.findBySchema(Schema);

		Iterator<Field> iterField = fieldsFrom.iterator();
		while (iterField.hasNext()) {
			Field f = iterField.next();
			if (f.getRemark() == null) {
				f.setRemark("");
			} else if ("".equals(f.getRemark())) {
				f.setRemark("");
			}

//			fieldDaoTo.insert(f);
		}
	}
}