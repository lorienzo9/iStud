package com.aveteam.lorienzo9.istudy.Constructors;

/**
 * Created by lorienzo9 on 01/11/17.
 */

public class Chat {
    String user, text;
    public Chat(){}
    public Chat(String user, String text){
        this.user = user;
        this. text = text;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
