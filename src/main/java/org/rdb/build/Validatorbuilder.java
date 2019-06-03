package org.rdb.build;

import java.util.Iterator;
import java.util.List;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.ProcDebug;
import org.rdb.beans.Validator;
import org.rdb.beans.ValidatorItem;
import org.rdb.dao.ValidatorDao;
import org.rdb.dao.ValidatorItemDao;

public class Validatorbuilder {
	static ValidatorDao validatorDao;
	static ValidatorItemDao validatorItemDao;
	static private List<Validator> validators;

	public static void GetFields(Validator validator) {
		List<ValidatorItem> validatorItems = validatorItemDao.findByTable(validator.getTableName());
		validator.setValidatorItems(validatorItems);
	}

	public static void init() {
		validatorDao = MapperFactory.createMapper(ValidatorDao.class, DataSourceEnvironment.platform);
		validatorItemDao = MapperFactory.createMapper(ValidatorItemDao.class, DataSourceEnvironment.platform);
	}

	public static List<Validator> build() {
		init();
		ProcDebug debug = new ProcDebug();
		validators = validatorDao.selectAll();
		Iterator<Validator> iter = validators.iterator();
		while (iter.hasNext()) {
			Validator validator = iter.next();
			validator.setDebug(debug);
			GetFields(validator);
		}
		return validators;
	}

	public static List<Validator> buildbyName(String validatorName) {
		init();
		// validators = validatorDao.findByrName(ValidatorName);
		return validators;
	}

	public static List<Validator> buildbyID(Integer[] validatorID) {
		init();
		// validators = validatorDao.findByID(ValidatorIDs);
		return validators;
	}
}
