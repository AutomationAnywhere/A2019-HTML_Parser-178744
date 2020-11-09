
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
@CommandPkg(label = "Search with Selector", name = "searchwithselector",
        description = "Advanced Search with Selector",
        node_label = "Search with Selector", icon = "pkg.svg", comment = true , text_color = "#308c0a" , background_color =  "#308c0a" ,
        return_type=DataType.LIST, return_sub_type =  DataType.DICTIONARY ,return_label="List of Elements of type Dictionary", return_required=true)

public class SearchwithSelector {
	   
	@Execute
    public ListValue<DictionaryValue> action(@Idx(index = "1", type = AttributeType.TEXT)  @Pkg(label = "HTML"  , default_value_type = DataType.STRING ) @NotEmpty String html,
    						   	@Idx(index = "2", type = AttributeType.TEXT)  @Pkg(label = "Selector"  , default_value_type = DataType.STRING ) @NotEmpty String selector  ) throws Exception
     {
		List<Value> listofresults = new ArrayList<Value>();
		HTMLUtils utils = new HTMLUtils();
		Document doc = utils.getDoc(html, "STRING", "");
		List<HashMap<String, Value>> searchresults = utils.select(doc, selector);
		for (HashMap<String, Value> hashMap : searchresults) {
			DictionaryValue value = utils.toDictionaryValue(hashMap);
			listofresults.add(value);
		}
		
		ListValue<DictionaryValue> finalresult = new ListValue<DictionaryValue>();
		finalresult.set(listofresults);
		return finalresult;

	}

		
	
}