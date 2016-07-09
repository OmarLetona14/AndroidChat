package omarletona.org.androidchat.contactlist;

import com.firebase.client.FirebaseError;

/**
 * Created by Omar on 05/07/2016.
 */
public class DatabaseError extends FirebaseError{
    public DatabaseError(int code, String message) {
        super(code, message);
    }
}
