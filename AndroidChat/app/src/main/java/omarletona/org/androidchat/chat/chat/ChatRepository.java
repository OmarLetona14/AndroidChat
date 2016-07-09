package omarletona.org.androidchat.chat.chat;

/**
 * Created by Omar on 05/07/2016.
 */
public interface ChatRepository {

    void changeConnectionStatus(boolean online);
    void sendMessage(String msg);
    void setRecipient(String recipient);

    void subscribe();
    void unsubscribe();
    void destroyListener();
}
