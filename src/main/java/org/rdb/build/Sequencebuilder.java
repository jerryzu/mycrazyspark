package org.rdb.build;

import java.util.List;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.Sequence;
import org.rdb.dao.SequenceDao;

public class Sequencebuilder {
	static SequenceDao sequenceDao;
	static private List<Sequence> sequences;

  public static void init() {
		sequenceDao = MapperFactory.createMapper(SequenceDao.class,
				DataSourceEnvironment.platform);
	}

	public static List<Sequence> build() {
		init();
		sequences = sequenceDao.selectAll();
		return sequences;
	}
	public static List<Sequence> buildbyName(String sequenceName) {
		init();
//		sequences = SequenceDao.findByName(sequenceName);
		return sequences;
	}

	public static List<Sequence> buildbyID(Integer[] sequenceID) {
		init();
//		sequences = SequenceDao.findByID(sequenceID);
		return sequences;
	}
}
