package org.rdb.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.rdb.beans.Field;
import org.rdb.build.Fieldbuilder;
import org.rdb.core.FreeMarkerExportWay;
import org.rdb.core.FreeMarkerFileType;
import org.rdb.core.SimpleFreeMarkerFactory;

public class FieldFactory {
	static Map<String, Object> root = new HashMap<String, Object>();
	static List<Field> fields;

	public static List<Field> getField() {
		return fields;
	}

	public static void main(String[] args) {
		BuildFields();
	}

	public static void done() {
		fields = Fieldbuilder.buildbyTableID(20700);
		root.put("fieldlist", fields);
	}

	public static void BuildFields() {
		done();
		SimpleFreeMarkerFactory.setExportInfo(FreeMarkerFileType.mysql, FreeMarkerExportWay.screen);

		SimpleFreeMarkerFactory.analysisTemplate("field", "column.ftl", root);
	}
}
