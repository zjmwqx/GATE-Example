package DStest;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gate.*;
import gate.creole.*;
import gate.creole.annic.Constants;
import gate.creole.annic.Indexer;
import gate.creole.annic.lucene.LuceneIndexer;
import gate.creole.annic.lucene.LuceneSearcher;
import gate.persist.LuceneDataStoreImpl;
import gate.util.*;
import gate.util.persistence.PersistenceManager;
import gate.corpora.RepositioningInfo;

public class GateTest {
	public static void main(String[] args) {
		/*LuceneDataStoreImpl ds = (LuceneDataStoreImpl)
				Factory.*/
		try {
			Gate.init();
			LuceneDataStoreImpl ds = 
					(LuceneDataStoreImpl)Factory.createDataStore(
							"gate.persist.LuceneDataStoreImpl", "file:/home/jiminzhou/data/");
			Indexer indexer = new LuceneIndexer(new URL("file:/home/jiminzhou/dataInd/"));
			Map parameters = new HashMap();
			parameters.put(Constants.INDEX_LOCATION_URL, new URL("file:/home/jiminzhou/dataInd/"));
			
			parameters.put(Constants.BASE_TOKEN_ANNOTATION_TYPE, "Token");
			parameters.put(Constants.CREATE_TOKENS_AUTOMATICALLY, new Boolean(true));
			parameters.put(Constants.INDEX_UNIT_ANNOTATION_TYPE, "Sentence");
			List<String> setsToInclude = new ArrayList<String>();
			setsToInclude.add("Key");
			setsToInclude.add("<null>");
			parameters.put(Constants.ANNOTATION_SETS_NAMES_TO_INCLUDE, setsToInclude);
			parameters.put(Constants.ANNOTATION_SETS_NAMES_TO_EXCLUDE,
					new ArrayList<String>());
			parameters.put(Constants.FEATURES_TO_INCLUDE, new ArrayList<String>());
			parameters.put(Constants.FEATURES_TO_EXCLUDE, new ArrayList<String>());
			ds.setIndexer(indexer, parameters);
			ds.setSearcher(new LuceneSearcher());
			Corpus c = Factory.newCorpus("test corpus");
			ds.adopt(c, null);
			//Searcher searcher = ds.getSearcher();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
