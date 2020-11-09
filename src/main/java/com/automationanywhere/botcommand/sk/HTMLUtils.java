package com.automationanywhere.botcommand.sk;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.DictionaryValue;
import com.automationanywhere.botcommand.data.impl.StringValue;


import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;

public class HTMLUtils {
	
	
	
	public String HTMLtoXML(Document doc) throws Exception {

		String result = "";
		if (doc != null) {
			OutputSettings xml = doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml); 
			result = doc.html();
		}
		return result;
		
	}
	
	public String HTML(Document doc) throws Exception {

		String result = "";
		if (doc != null) {
			OutputSettings xml = doc.outputSettings().syntax(Document.OutputSettings.Syntax.html); 
			result = doc.html();
		}
		return result;
		
	}
	
	
	public List<HashMap<String, Value>> select(Document doc,String selector) throws Exception {

		List<HashMap<String, Value>> results = new ArrayList<HashMap<String, Value>>();
		if (doc != null) {
			Elements elements = doc.select(selector); 
			for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();
				results.add(getMap(element));

			}

		}
		return results;
		
	}
	
	public List<HashMap<String, Value>> findbyText(Document doc,String text, Boolean regex) throws Exception {

		List<HashMap<String, Value>> results = new ArrayList<HashMap<String, Value>>();
		Elements elements;
		if (doc != null) {
			if (regex) {
				elements = doc.select("*:matchesOwn("+text+")"); 
			}
			else {
				elements = doc.select("*:containsOwn("+text+")"); 
			}
			for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();
				results.add(getMap(element));
				
				
			}

		}
		return results;
		
	}
	
	public List<HashMap<String, Value>> findbyTag(Document doc,String tag) throws Exception {

		List<HashMap<String, Value>> results = new ArrayList<HashMap<String, Value>>();
		if (doc != null) {
			Elements elements = doc.getElementsByTag(tag);
			for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();
				results.add(getMap(element));

			}

		}
		return results;
		
	}
	
	
	public HashMap<String, Value> findbyID(Document doc,String  id) throws Exception {

		HashMap<String, Value> result = new HashMap<String, Value>();
		if (doc != null) {
			Element element = doc.getElementById(id);
			if (element != null) {
				result = getMap(element);
			}
			
		}
		return result;
		
	}
	
	
	public List<HashMap<String, Value>> findbyClass(Document doc,String className) throws Exception {

		List<HashMap<String, Value>> results = new ArrayList<HashMap<String, Value>>();
		if (doc != null) {
			Elements elements = doc.getElementsByClass(className);
			for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();
				results.add(getMap(element));
				
				
			}

		}
		return results;
		
	}
	
	
	public List<HashMap<String, Value>> findbyAttribute(Document doc,String attribute,String value, Boolean regex) throws Exception {

		List<HashMap<String, Value>> results = new ArrayList<HashMap<String, Value>>();
		Elements elements ;
		if (doc != null) {
			if (value != null) {
				if (regex == true) {
					elements = doc.getElementsByAttributeValueMatching(attribute,  value);
				}
				else		
				{
					elements = doc.getElementsByAttributeValue(attribute, value);	
				}
			}
			else {
				elements = doc.getElementsByAttribute(attribute);
			}
			for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();
				results.add(getMap(element));
			}

		}
		return results;
		
	}
	

	
	
	public Document getDoc(String content ,String inputtype , String method) throws Exception {
		Document doc = null;
		if (inputtype.contentEquals("WEB")) {
			Connection connection= Jsoup.connect(content).followRedirects(true);

			switch (method) {
				case "GET" :	doc = connection.get();
								break;
				case "POST" :	doc = connection.post();
								break;	
				default :		doc = null;
			}
		}
		else if (inputtype.contentEquals("FILE")) {
			File input = new File(content);
			doc = Jsoup.parse(input, "UTF-8");
		}
		else if (inputtype.contentEquals("STRING")) {
			doc = Jsoup.parse(content);
		}
	
	
			return doc;
	}
	
	
	public DictionaryValue toDictionaryValue(HashMap<String,Value> map) {
		DictionaryValue dict = new DictionaryValue();	
		dict.set(map);
		return dict;
		
	}
	

	
	private HashMap<String, Value> getMap(Element element) {
		HashMap<String, Value> result = new HashMap<String, Value>();
		result.put("text", new StringValue(element.ownText()));
		result.put("id", new StringValue(element.id()));
		//result.put("classes", new StringValue(element.className()));
		result.put("html", new StringValue(element.outerHtml()));
		result.put("tag", new StringValue(element.tagName().toLowerCase()));
		result.put("value", new StringValue(element.val()));
		Attributes attributes = element.attributes();
		for (Attribute attribute : attributes) {
			result.put(attribute.getKey(), new StringValue(attribute.getValue()));
		}
		return result;
		
	}
	
	
	
		
	
}
