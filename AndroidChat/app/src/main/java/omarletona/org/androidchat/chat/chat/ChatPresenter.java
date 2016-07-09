package omarletona.org.androidchat.chat.chat;

import omarletona.org.androidchat.chat.chat.events.ChatEvent;

/**
 * Created by Omar on 05/07/2016.
 */
public interface ChatPresenter {

    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipient(String recipient);
    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);
}
