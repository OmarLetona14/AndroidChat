package omarletona.org.androidchat.addcontact;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import omarletona.org.androidchat.addcontact.events.AddContactEvent;
import omarletona.org.androidchat.contactlist.DatabaseError;
import omarletona.org.androidchat.domain.FirebaseHelper;
import omarletona.org.androidchat.entities.User;
import omarletona.org.androidchat.lib.EventBus;
import omarletona.org.androidchat.lib.GreenRobotEventBus;

/**
 * Created by Omar on 05/07/2016.
 */
public class AddContactRepositoryImpl implements AddContactRepository {
    private EventBus eventBus;
    private FirebaseHelper helper;


    public AddContactRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void addContact(final String email) {
        final String key = email.replace(".","_");
        Firebase userReference = helper.getUserReference(email);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    boolean online = user.isOnline();
                    Firebase myContactsReference = helper.getMyContactsReference();
                    myContactsReference.child(key).setValue(user.isOnline());

                    String currentUserKey = helper.getAuthUserEmail();
                    currentUserKey = currentUserKey.replace(".","_");
                    Firebase reverseContactsReference = helper.getContactsReference(email);
                    reverseContactsReference.child(currentUserKey).setValue(true);
                    postSuccess();
                } else {
                    postError();
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                postError();
            }
        });
    }

    private void postSuccess() {
        post(true);
    }

    private void postError() {
        post(false);
    }

    private void post(boolean error) {
        AddContactEvent event = new AddContactEvent();
        event.setError(error);
        eventBus.post(event);
    }
}
