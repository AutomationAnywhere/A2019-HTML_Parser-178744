
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
@CommandPkg(label = "Advanced Search with RegEx", name = "regexsearch",
        description = "RegEx Search by Attribute Value or Text Value",
        node_label = "Advanced Search with RegEx", icon = "pkg.svg", comment = true , text_color = "#308c0a" , background_color =  "#308c0a" ,
        return_type=DataType.LIST, return_sub_type =  DataType.DICTIONARY ,return_label="List of Elements of type Dictionary", return_required=true)

public class AdvancedSearch {
	   
	@Execute
    public ListValue<DictionaryValue> action(@Idx(index = "1", type = AttributeType.TEXT)  @Pkg(label = "HTML"  , default_value_type = DataType.STRING ) @NotEmpty String html,
    						   	@Idx(index = "2", type = AttributeType.TEXT)  @Pkg(label = "RegEx"  , default_value_type = DataType.STRING ) @NotEmpty String query,
    						   	@Idx(index = "3", type = AttributeType.TEXT)  @Pkg(label = "Attribute Name"  , default_value_type = DataType.STRING )  String attrname,
    						   	@Idx(index = "4", type = SELECT, options = {
    										@Idx.Option(index = "4.1", pkg = @Pkg(label = "By Attribute Value", value = "ATTRIBUTE")),
    										@Idx.Option(index = "4.2", pkg = @Pkg(label = "By Text Value", value = "TEXT"))
								}) @Pkg(label = "Query Type", default_value = "TEXT", default_value_type = STRING) @NotEmpty String searchtype   ) throws Exception
     {
		List<Value> listofresults = new ArrayList<Value>();
		attrname = (attrname == null) ? "" : attrname;
		HTMLUtils utils = new HTMLUtils();
		Document doc = utils.getDoc(html, "STRING", "");
		switch (searchtype) {
			case "TEXT" : 	List<HashMap<String, Value>> listtext = utils.findbyText(doc, query, true);
							for (HashMap<String, Value> hashMap : listtext) {
								DictionaryValue value = utils.toDictionaryValue(hashMap);
								listofresults.add(value);
							}
							break;
			case "ATTRIBUTE	" : List<HashMap<String, Value>> listattribute  = utils.findbyAttribute(doc, query,attrname,true);
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