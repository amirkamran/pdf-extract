package pdfextract;

import java.util.ArrayList;
import java.util.List;

import cz.vutbr.fit.knot.NNetLanguageIdentifierWrapper;
import cz.vutbr.fit.knot.NNetLanguageIdentifierWrapper.Result;

/**
 * @author MickeyVI
 */
public class DetectLanguage {

    NNetLanguageIdentifierWrapper identifier;
    Result[] result;

	String defaultLang = "";
    int numLangs = 5;

    public DetectLanguage() {
    	identifier = new NNetLanguageIdentifierWrapper(0, 1000);
    }
    
	public List<LanguageResult> find(String text) {

    	List<LanguageResult> resultlist = new ArrayList<LanguageResult>();
    	
        result = identifier.findTopNMostFreqLangs(text, numLangs);
        if (result != null && result.length > 0) {
        	for (Result prop : result) {
    			if (prop.proportion > 0) {
                	resultlist.add(new LanguageResult(prop.language, prop.proportion * 100));
    			}
        	}
        }
        
        return resultlist;
    }
    
    public class LanguageResult {
    	public String language = "";
    	public float percent = 0;
    	
    	public LanguageResult(String language_, float percent_) {
    		language = language_;
    		percent = percent_;
    	}
    }
}
