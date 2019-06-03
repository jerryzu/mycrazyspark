package org.rdb.build;

import java.util.Iterator;
import java.util.List;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.Field;
import org.rdb.beans.Partition;
import org.rdb.beans.Table;
import org.rdb.dao.FieldDao;
import org.rdb.dao.PartitionDao;
import org.rdb.dao.TableDao;

public class Tablebuilder {
	static FieldDao fieldDao;
	static TableDao tableDao;
	static PartitionDao partDao;
	static List<Table> tables;

	public static void GetFields(Table t) {
		List<Field> fields = fieldDao.findByTable(t.getTableName());
		t.setFieldlist(fields);
	}

	public static void GetPartiion(Table t) {
		String PartType = "NONE";
		if (t.getPartType() == null || t.getPartType() == "") {
		} else {
			PartType = t.getPartType();
			Partition part = partDao.findByPartType(PartType);
			t.setPartition(part);	
		}
	}

	public static void init() {
		tableDao = MapperFactory.createMapper(TableDao.class, DataSourceEnvironment.platform);
		fieldDao = MapperFactory.createMapper(FieldDao.class, DataSourceEnvironment.platform);
		partDao = MapperFactory.createMapper(PartitionDao.class, DataSourceEnvironment.platform);
	}

	public static List<Table> build() {
		init();
		tables = tableDao.selectAll();

		Iterator<Table> iter = tables.iterator();
		while (iter.hasNext()) {
			Table table = iter.next();
			GetPartiion(table);
			GetFields(table);
		}
		return tables;
	}


	public static List<Table> buildbyTopic(String topicName) {
		init();
		tables = tableDao.selectbyTopic(topicName);

		Iterator<Table> iter = tables.iterator();
		while (iter.hasNext()) {
			Table table = iter.next();
			GetPartiion(table);
			GetFields(table);
		}
		return tables;
	}


	public static List<Table> findTableTopicLike(String topicName) {
		init();
		tables = tableDao.findTableTopicLike(topicName);

		Iterator<Table> iter = tables.iterator();
		while (iter.hasNext()) {
			Table table = iter.next();
			GetPartiion(table);
			GetFields(table);
		}
		return tables;
	}
	
	public static List<Table> build(String tableName) {
		return build();
	}	
	
	public static List<Table> buildbyName(String tableName) {
		init();
		tables = tableDao.findByName(tableName);// RANGE

		Iterator<Table> iter = tables.iterator();
		while (iter.hasNext()) {
			Table table = iter.next();
			GetPartiion(table);
			GetFields(table);
		}
		return tables;
	}
	
	public static List<Table> buildTableLike(String tableName) {
		init();
		tables = tableDao.findTableLike(tableName);// RANGE

		Iterator<Table> iter = tables.iterator();
		while (iter.hasNext()) {
			Table table = iter.next();
			GetPartiion(table);
			GetFields(table);
		}
		return tables;
	}

	public static List<Table> buildbyID(Integer[] tableIDs) {
		init();
		tables = tableDao.findByID(tableIDs);// RANGE

		Iterator<Table> iter = tables.iterator();
		while (iter.hasNext()) {
			Table table = iter.next();
			GetPartiion(table);
			GetFields(table);
		}
		return tables;
	}
}
