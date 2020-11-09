
package com.automationanywhere.botcommand.sk;



import static com.automationanywhere.commandsdk.model.AttributeType.SELECT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import org.jsoup.nodes.Document;


import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.annotations.Execute;


/**
 * @author Stefan Karsten
 *
 */

@BotCommand
@CommandPkg(label = "Convert HTML to XML", name = "htmltoxml",
        description = "Converts HTML data to XML",
        node_label = "Convert HTML to XML", icon = "pkg.svg", comment = true ,  text_color = "#308c0a" , background_color =  "#308c0a" ,
        return_type=STRING, return_label="XML", return_required=true)

public class GetXMLContent {
	   
	@Execute
    public StringValue  action(@Idx(index = "1", type = AttributeType.TEXT)  @Pkg(label = "HTML"  , default_value_type = STRING ) @NotEmpty String source
                                 ) throws Exception
     {

		HTMLUtils utils = new HTMLUtils();
		Document doc = utils.getDoc(source, "STRING","");
		String xmlstring = utils.HTMLtoXML(doc);

		return new StringValue(xmlstring);

	}

		
	
}