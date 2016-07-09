package omarletona.org.androidchat.chat.chat;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import butterknife.OnClick;
import omarletona.org.androidchat.R;
import omarletona.org.androidchat.chat.chat.entities.ChatMessage;
import omarletona.org.androidchat.chat.chat.events.ChatEvent;
import omarletona.org.androidchat.contactlist.DatabaseError;
import omarletona.org.androidchat.domain.FirebaseHelper;
import omarletona.org.androidchat.lib.EventBus;
import omarletona.org.androidchat.lib.GreenRobotEventBus;

/**
 * Created by Omar on 06/07/2016.
 */
public class ChatRepositoryImpl implements ChatRepository {
    private String recipient;
    private FirebaseHelper helper;
    private ChildEventListener listener;
    private EventBus eventBus;

    public ChatRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }

    @Override
    public void sendMessage(String msg) {
        ChatMessage chatMessage = new ChatMessage(helper.getAuthUserEmail(), msg);
        Firebase chatsReference = helper.getChatsReference(recipient);
        chatsReference.push().setValue(chatMessage);
    }

    @Override
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public void subscribe() {
        if (listener == null) {
            listener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {
                    ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                    String msgSender = chatMessage.getSender();
                    msgSender = msgSender.replace("_",".");

                    String currentUserEmail = helper.getAuthUserEmail();
                    chatMessage.setSentByMe(msgSender.equals(currentUserEmail));

                    ChatEvent chatEvent = new ChatEvent();
                    chatEvent.setMessage(chatMessage);
                    eventBus.post(chatEvent);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildKey) {}

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {}

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(FirebaseError firebaseError) {}
            };
            helper.getChatsReference(recipient).addChildEventListener(listener);
        }
    }

    @Override
    public void unsubscribe() {
        if (listener != null) {
            helper.getChatsReference(recipient).removeEventListener(listener);
        }
    }

    @Override
    public void destroyListener() {
        listener = null;
    }
}
