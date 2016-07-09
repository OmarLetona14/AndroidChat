package omarletona.org.androidchat.contactlist.ui.adapters;

import omarletona.org.androidchat.entities.User;

/**
 * Created by Omar on 04/07/2016.
 */
public interface OnItemClickListener {

    void onItemClick(User user);
    void onItemLongClick(User user);
}
