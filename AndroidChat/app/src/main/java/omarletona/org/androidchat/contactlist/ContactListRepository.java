package omarletona.org.androidchat.contactlist;

/**
 * Created by Omar on 04/07/2016.
 */
public interface ContactListRepository {

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void destroyListener();
    void subscribeToContactListEvent();
    void unsubscribeToContactListEvent();
    void ChangeConnectionStatus(boolean online);
}
