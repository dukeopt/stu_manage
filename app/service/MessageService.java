package service;

import play.i18n.Lang;
import play.i18n.Messages;
import play.i18n.MessagesApi;

import javax.inject.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

@Singleton
public class MessageService {
    private final MessagesApi messagesApi;

    @Inject
    public MessageService(MessagesApi messagesApi) {
        this.messagesApi = messagesApi;
    }

    public String at(String key) {
        Collection<Lang> candidates = Collections.singletonList(new Lang(Locale.ENGLISH));
        Messages messages = messagesApi.preferred(candidates);
        return messages.at(key);
    }
}




