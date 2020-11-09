
package com.automationanywhere.botcommand.sk;



import static com.automationanywhere.commandsdk.model.AttributeType.SELECT;
import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.DictionaryValue;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import com.automationanywhere.toolchain.runtime.variable.Variable;
import com.automationanywhere.commandsdk.annotations.Execute;


/**
 * @author Stefan Karsten
 *
 */

@BotCommand
@CommandPkg(label = "Simple Search for Elements", name = "simplesearch",
        description = "Simple Search for Elements by ID, Tag, Text, Class or Attribute",
        node_label = "Simple Search for Elements", icon = "pkg.svg", comment = true , text_color = "#308c0a" , background_color =  "#308c0a" ,
        return_type=DataType.LIST, return_sub_type =  DataType.DICTIONARY ,return_label="List of Elements of type Dictionary", return_required=true)

public class SimpleSearch {
	   
	@Execute
    public ListValue<DictionaryValue> action(@Idx(index = "1", type = AttributeType.TEXT)  @Pkg(label = "HTML"  , default_value_type = DataType.STRING ) @NotEmpty String html,
    						   	@Idx(index = "2", type = AttributeType.TEXT)  @Pkg(label = "Query"  , default_value_type = DataType.STRING ) @NotEmpty String query,
    						   	@Idx(index = "3", type = SELECT, options = {
    										@Idx.Option(index = "3.1", pkg = @Pkg(label = "By ID", value = "ID")),
    										@Idx.Option(index = "3.2", pkg = @Pkg(label = "By Tag", value = "TAG")),
    										@Idx.Option(index = "3.3", pkg = @Pkg(label = "By Text", value = "TEXT")),
    										@Idx.Option(index = "3.4", pkg = @Pkg(label = "By Class", value = "CLASS")),
    										@Idx.Option(index = "3.5", pkg = @Pkg(label = "By Attribute", value = "ATTRIBUTE"))
								}) @Pkg(label = "Query Type", default_value = "TAG", default_value_type = STRING) @NotEmpty String searchtype   ) throws Exception
     {
		List<Value> listofresults = new ArrayList<Value>();
		HTMLUtils utils = new HTMLUtils();
		Document doc = utils.getDoc(html, "STRING", "");
		switch (searchtype) {
			case "ID" : 	HashMap<String, Value> resultid =  utils.findbyID(doc, query);
							if (!resultid.isEmpty()) {
								DictionaryValue value = utils.toDictionaryValue(resultid);
								listofresults.add(value);
							}
							break;
			case "TAG" : 	List<HashMap<String, Value>> listtag = utils.findbyTag(doc, query);
							for (HashMap<String, Value> hashMap : listtag) {
								DictionaryValue value = utils.toDictionaryValue(hashMap);
								listofresults.add(value);
							}
							break;
			case "CLASS" : 	List<HashMap<String, Value>> listclass  = utils.findbyClass(doc, query);
							for (HashMap<String, Value> hashMap : listclass) {
								DictionaryValue value = utils.toDictionaryValue(hashMap);
								listofresults.add(value);
							}
							break;
			case "TEXT" : 	List<HashMap<String, Value>> listtext = utils.findbyText(doc, query, false);
							for (HashMap<String, Value> hashMap : listtext) {
								DictionaryValue value = utils.toDictionaryValue(hashMap);
								listofresults.add(value);
							}
							break;
			case "ATTRIBUTE	" : List<HashMap<String, Value>> listattribute  = utils.findbyAttribute(doc, query,null,false);
								for (HashMap<String, Value> hashMap : listattribute) {
									DictionaryValue value = utils.toDictionaryValue(hashMap);
									listofresults.add(value);
								}
								break;

		}
		
		ListValue<DictionaryValue> finalresult = new ListValue<DictionaryValue>();
		finalresult.set(listofresults);
		return finalresult;

	}

		
	
}