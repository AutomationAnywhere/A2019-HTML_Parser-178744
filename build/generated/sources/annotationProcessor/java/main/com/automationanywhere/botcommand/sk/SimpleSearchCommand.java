package com.automationanywhere.botcommand.sk;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class SimpleSearchCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(SimpleSearchCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    SimpleSearch command = new SimpleSearch();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("html") && parameters.get("html") != null && parameters.get("html").get() != null) {
      convertedParameters.put("html", parameters.get("html").get());
      if(!(convertedParameters.get("html") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","html", "String", parameters.get("html").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("html") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","html"));
    }

    if(parameters.containsKey("query") && parameters.get("query") != null && parameters.get("query").get() != null) {
      convertedParameters.put("query", parameters.get("query").get());
      if(!(convertedParameters.get("query") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","query", "String", parameters.get("query").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("query") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","query"));
    }

    if(parameters.containsKey("searchtype") && parameters.get("searchtype") != null && parameters.get("searchtype").get() != null) {
      convertedParameters.put("searchtype", parameters.get("searchtype").get());
      if(!(convertedParameters.get("searchtype") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","searchtype", "String", parameters.get("searchtype").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("searchtype") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","searchtype"));
    }
    if(convertedParameters.get("searchtype") != null) {
      switch((String)convertedParameters.get("searchtype")) {
        case "ID" : {

        } break;
        case "TAG" : {

        } break;
        case "TEXT" : {

        } break;
        case "CLASS" : {

        } break;
        case "ATTRIBUTE" : {

        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","searchtype"));
      }
    }

    try {
      Optional<Value> result =  Optional.ofNullable(command.action((String)convertedParameters.get("html"),(String)convertedParameters.get("query"),(String)convertedParameters.get("searchtype")));
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","action"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(),e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }
}
