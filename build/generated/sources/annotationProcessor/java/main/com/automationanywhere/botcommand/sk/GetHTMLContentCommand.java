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

public final class GetHTMLContentCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(GetHTMLContentCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    GetHTMLContent command = new GetHTMLContent();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("source") && parameters.get("source") != null && parameters.get("source").get() != null) {
      convertedParameters.put("source", parameters.get("source").get());
      if(!(convertedParameters.get("source") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","source", "String", parameters.get("source").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("source") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","source"));
    }

    if(parameters.containsKey("sourcetype") && parameters.get("sourcetype") != null && parameters.get("sourcetype").get() != null) {
      convertedParameters.put("sourcetype", parameters.get("sourcetype").get());
      if(!(convertedParameters.get("sourcetype") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","sourcetype", "String", parameters.get("sourcetype").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("sourcetype") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","sourcetype"));
    }
    if(convertedParameters.get("sourcetype") != null) {
      switch((String)convertedParameters.get("sourcetype")) {
        case "WEB" : {

        } break;
        case "FILE" : {

        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","sourcetype"));
      }
    }

    try {
      Optional<Value> result =  Optional.ofNullable(command.action((String)convertedParameters.get("source"),(String)convertedParameters.get("sourcetype")));
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
