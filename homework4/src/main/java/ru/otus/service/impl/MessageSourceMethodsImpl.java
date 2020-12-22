package ru.otus.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.service.MessageSourceMethods;

import java.util.Locale;

@Component
public class MessageSourceMethodsImpl implements MessageSourceMethods {

    private final static String [] ONE_SPACE_ARRAY = {" "};

    private final MessageSource messageSource;

    public MessageSourceMethodsImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String tag, Locale locale) {
       return messageSource.getMessage(tag, ONE_SPACE_ARRAY, locale);
    }
}
