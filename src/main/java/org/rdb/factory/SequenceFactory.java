package org.rdb.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.rdb.beans.Sequence;
import org.rdb.build.Sequencebuilder;
import org.rdb.core.FreeMarkerExportWay;
import org.rdb.core.FreeMarkerFileType;
import org.rdb.core.SimpleFreeMarkerFactory;

public class SequenceFactory {
	static Map<String, Object> root = new HashMap<String, Object>();
	static private List<Sequence> sequences;

	public static List<Sequence> getSequence() {
		return sequences;
	}
	
	public static void main(String[] args) {
		BuildSequences();
	}

	public static void done() {
		sequences = Sequencebuilder.build();
		root.put("sequencelist", sequences);
	}

	public static void BuildSequences() {
		done();
		SimpleFreeMarkerFactory.setExportInfo(FreeMarkerFileType.ora,
				FreeMarkerExportWay.screen);
		SimpleFreeMarkerFactory.analysisTemplate("sequence", "sequence.ftl",
				root);
	}
}
