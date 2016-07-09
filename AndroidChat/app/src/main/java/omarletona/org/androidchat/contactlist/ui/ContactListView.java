package omarletona.org.androidchat.contactlist.ui;

import omarletona.org.androidchat.entities.User;

/**
 * Created by Omar on 04/07/2016.
 */
public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
