package org.rdb.schema;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLBoolean;
import org.rdb.wrapper.SQLDate;
import org.rdb.wrapper.SQLDouble;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;
import org.rdb.wrapper.SQLTimestamp;

public class DatabaseBuildMySQL {

	/*
	  org.rdb.beans.Dependency 
	  org.rdb.beans.DependFieldMapping
	  org.rdb.beans.DependProcedure 
	  org.rdb.beans.DependSourceTable
	  org.rdb.beans.DependTargetTable 
	  org.rdb.beans.Field
	  org.rdb.beans.MarkerObject 
	  org.rdb.beans.Partition
	  org.rdb.beans.ProcDebug 
	  org.rdb.beans.Procedure 
	  org.rdb.beans.Sequence
	  org.rdb.beans.Synonym 
	  org.rdb.beans.Table 
	  org.rdb.beans.TableSpace
	  org.rdb.beans.TablespaceFile 
	  org.rdb.beans.User
	  org.rdb.beans.Right
	  org.rdb.beans.DbLink 
	  org.rdb.beans.Validator 
	  org.rdb.beans.Trigger
	  org.datax.beans.Job 
      org.sql.bean.SQLScript
	 */

	public static void main(String[] args) {
		String[] classNames = args;
		try {
			for (String className : classNames) {
				Class<?> cl = Class.forName(className);
				DBTable dbTable = (DBTable) cl.getAnnotation(DBTable.class);
				if (dbTable == null)
					continue;

				String tableName = dbTable.name();
				if (tableName.length() <= 0) {
					tableName = cl.getSimpleName();
				}
				List<String> columnNames = new ArrayList<String>();
				for (Field field : cl.getDeclaredFields()) {
					String columnName = "";
					String comment = "";
					Annotation[] annotations = field.getDeclaredAnnotations();
					if (annotations.length <= 0)
						continue;
					if (annotations[0] instanceof SQLInteger) {
						SQLInteger tmpSqlInt = (SQLInteger) annotations[0];
						columnName = tmpSqlInt.name();
						if (columnName.length() <= 0) {
							columnName = field.getName() + " integer";
						} else {
							columnName += " integer";
						}

						String constrain = getConstraon(tmpSqlInt.constrains());
						columnName += constrain;
					}
					if (annotations[0] instanceof SQLString) {
						SQLString tmpSqlStr = (SQLString) annotations[0];
						columnName = tmpSqlStr.name();
						if (columnName.length() <= 0) {
							columnName = field.getName();
						}
						if (tmpSqlStr.value() > 0) {
							columnName += " varchar(" + tmpSqlStr.value()
									+ ")";
						}
						String constrain = getConstraon(tmpSqlStr.constrains());
						columnName += constrain;
					}
					if (annotations[0] instanceof SQLBoolean) {
						SQLBoolean tmpSqlBoolean = (SQLBoolean) annotations[0];
						columnName = tmpSqlBoolean.name();
						comment = " comment '" + tmpSqlBoolean.caption() + "'";
						
						if (columnName.length() <= 0) {
							columnName = field.getName() + " smallint " + comment;
						}
						String constrain = getConstraon(tmpSqlBoolean
								.constrains());
						columnName += constrain;
					}
					if (annotations[0] instanceof SQLDouble) {
						SQLDouble tmpSqlDouble = (SQLDouble) annotations[0];
						columnName = tmpSqlDouble.name();
						comment = " comment '" + tmpSqlDouble.caption() + "'";
						if (columnName.length() <= 0) {
							columnName = field.getName() + " decimal" + comment;
						} else {
							columnName += " decimal" + comment;
						}
						if (tmpSqlDouble.Precision() > 0) {
							if (tmpSqlDouble.Scale() > 0) {
								columnName += " (" + tmpSqlDouble.Precision()
										+ "," + tmpSqlDouble.Scale() + ")" + comment;
							} else {
								columnName += " (" + tmpSqlDouble.Precision()
										+ ")" + comment;
							}
						} else {

						}
						String constrain = getConstraon(tmpSqlDouble
								.constrains());
						columnName += constrain;
					}
					if (annotations[0] instanceof SQLTimestamp) {
						SQLTimestamp tmpSqlTimestamp = (SQLTimestamp) annotations[0];
						columnName = tmpSqlTimestamp.name();
						if (columnName.length() <= 0) {
							columnName = field.getName() + " timestamp default current_timestamp";
						}
						String constrain = getConstraon(tmpSqlTimestamp
								.constrains());
						columnName += constrain;
					}
					if (annotations[0] instanceof SQLDate) {
						SQLDate tmpSQLDate = (SQLDate) annotations[0];
						columnName = tmpSQLDate.name();
						if (columnName.length() <= 0) {
							columnName = field.getName() + " datetime";
						}
						String constrain = getConstraon(tmpSQLDate
								.constrains());
						columnName += constrain;
					}

					columnNames.add(columnName);
				}
				String sqlCreat;
				sqlCreat = "drop table " + tableName + ";\n";
				sqlCreat += "create table " + tableName + " (\n";
				String createCommond = "";
				for (String str : columnNames) {
					createCommond += "\t" + str + ",\n";
				}
				sqlCreat += createCommond.substring(0,
						createCommond.length() - 2) + " \n); /";
				System.out.println(sqlCreat.toUpperCase());
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private static String getConstraon(Constrains constrain) {
		String rst = "";
		if (!constrain.allowNull()) {
			rst += " not null";
		}
		if (constrain.primaryKey()) {
			// rst += " primarykey ".toUpperCase();
		}
		if (constrain.unique()) {
			// rst += " unique ".toUpperCase();
		}
		return rst;
	}

}