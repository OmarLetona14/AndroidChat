package omarletona.org.androidchat.contactlist;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import omarletona.org.androidchat.contactlist.events.ContactListEvent;
import omarletona.org.androidchat.domain.FirebaseHelper;
import omarletona.org.androidchat.entities.User;
import omarletona.org.androidchat.lib.EventBus;
import omarletona.org.androidchat.lib.GreenRobotEventBus;

/**
 * Created by Omar on 04/07/2016.
 */
public class ContactListRepositoryImpl implements ContactListRepository {
    private FirebaseHelper helper;
    private EventBus eventBus;
    private ChildEventListener contactEventListener;

    public ContactListRepositoryImpl(){
        this.helper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void subscribeToContactListEvent() {
        if (contactEventListener == null) {
            contactEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {
                    handleContact(dataSnapshot, ContactListEvent.onContactAdded);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildKey) {
                    handleContact(dataSnapshot, ContactListEvent.onContactChanged);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleContact(dataSnapshot, ContactListEvent.onContactRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {}
            };
        }

        helper.getMyContactsReference().addChildEventListener(contactEventListener);
    }

    private void handleContact(DataSnapshot dataSnapshot, int type) {
        String email = dataSnapshot.getKey();
        email = email.replace("_",".");
        boolean online = ((Boolean)dataSnapshot.getValue()).booleanValue();
        User user = new User(email, online, null);
        postEvent(type, user);
    }

    @Override
    public void destroyListener() {
        contactEventListener = null;
    }

    @Override
    public void unsubscribeToContactListEvent(){
        if (contactEventListener != null) {
            helper.getMyContactsReference().removeEventListener(contactEventListener);
        }
    }

    @Override
    public void removeContact(String email) {
        String currentUserEmail = helper.getAuthUserEmail();
        helper.getOneContactReference(currentUserEmail, email).removeValue();
        helper.getOneContactReference(email, currentUserEmail).removeValue();
    }

    @Override
    public String getCurrentUserEmail() {
        return helper.getAuthUserEmail();
    }

    @Override
    public void signOff() {
        helper.signOff();
    }

    @Override
    public void ChangeConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }

    private void postEvent(int type, User user) {
        ContactListEvent contactListEvent = new ContactListEvent();
        contactListEvent.setEventType(type);
        contactListEvent.setUser(user);
        eventBus.post(contactListEvent);
    }
}
