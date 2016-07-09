package omarletona.org.androidchat.chat.chat.events;

import omarletona.org.androidchat.chat.chat.entities.ChatMessage;

/**
 * Created by Omar on 05/07/2016.
 */
public class ChatEvent {

    private ChatMessage message;

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }
}
