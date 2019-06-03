package org.rdb.factory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.ProcDebug;
import org.rdb.beans.Validator;
import org.rdb.beans.ValidatorItem;
import org.rdb.build.Validatorbuilder;
import org.rdb.core.FreeMarkerExportWay;
import org.rdb.core.FreeMarkerFileType;
import org.rdb.core.SimpleFreeMarkerFactory;
import org.rdb.dao.ValidatorDao;
import org.rdb.dao.ValidatorItemDao;

public class ValidatorFactory {
	static Map<String, Object> root = new HashMap<String, Object>();
	static private List<Validator> validators;


	public static Object getValidator() {
		return validators;
	}
	
	public static void main(String[] args) {
		BuildValidators();
	}

	public static void done() {
		validators = Validatorbuilder.build();
		root.put("validatorlist", validators);
	}

	public static void BuildValidators() {
		done();
		SimpleFreeMarkerFactory.setExportInfo(FreeMarkerFileType.ora, FreeMarkerExportWay.screen);
		SimpleFreeMarkerFactory.analysisTemplate("validator", "validator.ftl", root);
	}
}
