
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
@CommandPkg(label = "Get HTML Content", name = "htmlcontent",
        description = "Get HTML text from a Web site or File",
        node_label = "Get HTML Content", icon = "pkg.svg", comment = true ,  text_color = "#308c0a" , background_color =  "#308c0a" ,
        return_type=STRING, return_label="HTML", return_required=true)

public class GetHTMLContent {
	   
	@Execute
    public StringValue  action(@Idx(index = "1", type = AttributeType.TEXT)  @Pkg(label = "HTML Source"  , default_value_type = STRING ) @NotEmpty String source,
    						   @Idx(index = "2", type = SELECT, options = {
    										@Idx.Option(index = "2.1", pkg = @Pkg(label = "URL", value = "WEB")),
    										@Idx.Option(index = "2.2", pkg = @Pkg(label = "File", value = "FILE"))
								}) @Pkg(label = "Source Type", default_value = "WEB", default_value_type = STRING) @NotEmpty String sourcetype
                                 ) throws Exception
     {

		HTMLUtils utils = new HTMLUtils();
		Document doc = utils.getDoc(source, sourcetype, "GET");
		String htmlstring = utils.HTML(doc);

		return new StringValue(htmlstring);

	}

		
	
}