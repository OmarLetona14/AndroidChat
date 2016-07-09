package omarletona.org.androidchat.contactlist;

/**
 * Created by Omar on 04/07/2016.
 */
public interface ContactListSessionInteractor {

    void signOff();
    String getCurrentUserEmail();
    void ChangeConnectionStatus(boolean online);
}
