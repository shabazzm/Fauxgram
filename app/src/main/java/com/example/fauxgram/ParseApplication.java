package com.example.fauxgram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    //initalizes parse sdk as soon as application is created
    @Override
    public void onCreate() {
        super.onCreate();

        //Register your Parse Models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("HtVaicHBlsu5ZpJg8taSDjGtiYFwXxGDZpcKlNtJ")
                .clientKey("KEtHDPxhIlyAdimaxLnmRlPmVYlrmttrvaC1TtRu")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
