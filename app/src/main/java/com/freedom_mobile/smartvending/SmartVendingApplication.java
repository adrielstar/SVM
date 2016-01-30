package com.freedom_mobile.smartvending;

import android.app.Application;

import com.freedom_mobile.smartvending.model.Account;
import com.freedom_mobile.smartvending.utils.ParseConstants;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class SmartVendingApplication extends Application {
    public static final String APPLICATION_ID =
            "pHxs4ZYRNL740fld8yMkdt5nlZs5HJacKV3tORfN";
    public static final String CLIENT_KEY =
            "poYPC2fUqIEcf1OX85z6dU6YCfqg9zaUm9uS19xS";

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models.
        ParseObject.registerSubclass(Account.class);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);
    }

    public static void updateParseInstallation(ParseUser parseUser) {
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put(ParseConstants.KEY_USER_ID, parseUser.getObjectId());
        installation.saveInBackground();
    }
}