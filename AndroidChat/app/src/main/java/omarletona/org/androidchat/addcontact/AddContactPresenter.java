package omarletona.org.androidchat.addcontact;

import omarletona.org.androidchat.addcontact.events.AddContactEvent;

/**
 * Created by Omar on 05/07/2016.
 */
public interface AddContactPresenter {

    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
