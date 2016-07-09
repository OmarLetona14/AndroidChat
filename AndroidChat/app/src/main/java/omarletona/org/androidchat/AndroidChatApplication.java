package omarletona.org.androidchat;

import android.app.Application;

import com.firebase.client.Firebase;

import omarletona.org.androidchat.lib.ImageLoader;

/**
 * Created by Omar on 29/06/2016.
 */
public class AndroidChatApplication extends Application {
    private ImageLoader imageLoader;
    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }
    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);

    }
}
