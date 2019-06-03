package org.rdb.build;

import java.util.List;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.Synonym;
import org.rdb.dao.SynonymDao;

public class Synonymbuilder {
	static SynonymDao synonymDao;
	static private List<Synonym> synonyms;

  public static void init() {
		synonymDao = MapperFactory.createMapper(SynonymDao.class,
				DataSourceEnvironment.platform);
	}

	public static List<Synonym> build() {
		init();
		synonyms = synonymDao.selectAll();
		return synonyms;
	}
	public static List<Synonym> buildbyName(String SynonymName) {
		init();
//		synonyms = synonymDao.findByName(SynonymName);
		return synonyms;
	}

	public static List<Synonym> buildbyID(Integer[] SynonymID) {
		init();
//		synonyms = synonymDao.findByID(SynonymID);
		return synonyms;
	}
}
