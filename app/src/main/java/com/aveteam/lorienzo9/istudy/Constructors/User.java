package com.aveteam.lorienzo9.istudy.Constructors;

/**
 * Created by lorienzo9 on 09/11/17.
 */

public class User {
    String name, group_id, email;
    public User(){
    }
    public User(String name, String group_id, String email){
        this.name = name;
        this.group_id = group_id;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup_id() {
        return group_id;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
