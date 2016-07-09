package omarletona.org.androidchat.contactlist;

import omarletona.org.androidchat.contactlist.events.ContactListEvent;

/**
 * Created by Omar on 04/07/2016.
 */
public interface ContactListPresenter {

    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void onEventMainThread(ContactListEvent event);
}
