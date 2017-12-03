package com.aveteam.lorienzo9.istudy.Constructors;

/**
 * Created by lorienzo9 on 29/09/17.
 */

public class Blog {
    String title, content, user_id, date, type;
    boolean like;
    public Blog(){};
    public Blog(String title, String content, String user, String date, String type){
        this.content = content;
        this.date = date;
        this.user_id = user;
        this.type = type;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
