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

public final class SearchwithSelectorCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(SearchwithSelectorCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    SearchwithSelector command = new SearchwithSelector();
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

    if(parameters.containsKey("selector") && parameters.get("selector") != null && parameters.get("selector").get() != null) {
      convertedParameters.put("selector", parameters.get("selector").get());
      if(!(convertedParameters.get("selector") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","selector", "String", parameters.get("selector").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("selector") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","selector"));
    }

    try {
      Optional<Value> result =  Optional.ofNullable(command.action((String)convertedParameters.get("html"),(String)convertedParameters.get("selector")));
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
