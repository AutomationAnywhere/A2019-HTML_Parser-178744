package com.automationanywhere.botcommand.sk;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Semaphore;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import com.automationanywhere.botcommand.data.Value;

public class test {

	public static void main(String[] args) throws Exception {



		// System.out.println("HTML1 : "+html);
	//	HTMLUtils utils = new HTMLUtils();
	//	Document doc = null ;//= utils.getDoc("https://developer.servicenow.com/dev.do", "WEB","POST");
	//	String xml = utils.HTML(doc);
	//	System.out.println("HTML1 : "+xml);
	//	String html2 = utils.simulateBrowser("CHROME", "https://developer.servicenow.com/dev.do/9859302.fls.doubleclick.net/activityi;src=9859302;type=brows0;cat=devel00;ord=721195302723;gtm=2wg7m1;auiddc=505582769.1596017026;u10=https%3A%2F%2Fdeveloper.servicenow.com%2Fdev.do;~oref=https%3A%2F%2Fdeveloper.servicenow.com%2Fdev.do?",true,false,true,true,10);
	//	System.out.println("HTML2 "+html2);
		//Document doc = utils.getDoc(html2, "STRING",null);
		//System.out.println("DOC HTML"+utils.HTML(doc));
//		List<HashMap<String,Value>> elements = utils.select(doc, "div.r > a[href~=(\\W*(bmw)\\W*)]");
//  	List<HashMap<String,Value>> elements = utils.findbyText(doc, "(\\W*(bmw)\\W*)",true);
 //	List<HashMap<String,Value>> elements = utils.findbyText(doc, "bmw",false);
	//
	//	List<HashMap<String,Value>> elements = utils.findbyClass(doc, "r");
	//List<HashMap<String,Value>> elements = utils.findbyAttribute(doc, "href", "https://www.bmw-leipzig.de/",false);
/*
		System.out.println("Size "+ elements.size());
		for (HashMap<String, Value> element : elements) {
			System.out.println("Element HTML : "+element.get("html"));
			for (Entry<String, Value> mapentry : element.entrySet()) {
				
				System.out.println("Key "+mapentry.getKey()+" Value "+mapentry.getValue().toString());
			
			}
		
		}
	*/	
		
	}

}
