package org.rdb.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.rdb.beans.Synonym;
import org.rdb.build.Synonymbuilder;
import org.rdb.core.FreeMarkerExportWay;
import org.rdb.core.FreeMarkerFileType;
import org.rdb.core.SimpleFreeMarkerFactory;

public class SynonymFactory {
	static Map<String, Object> root = new HashMap<String, Object>();
	static private List<Synonym> synonyms;

	public static List<Synonym> getSynonym() {
		return synonyms;
	}
	
	public static void main(String[] args) {
		BuildSynonyms();
	}

	public static void done() {
		synonyms = Synonymbuilder.build();
//		Iterator<Synonym> iter = synonyms.iterator();
//		while (iter.hasNext()) {
//			Synonym s = iter.next();
//			System.out.println(s.getGrantOn());
//		}
		root.put("synonymlist", synonyms);
	}

	public static void BuildSynonyms() {
		done();
		SimpleFreeMarkerFactory.setExportInfo(FreeMarkerFileType.ora,
				FreeMarkerExportWay.screen);
		SimpleFreeMarkerFactory.analysisTemplate("synonym", "synonym.ftl",
				root);
	}
}
