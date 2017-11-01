package com.aveteam.lorienzo9.istudy.Constructors;

import com.google.firebase.auth.UserInfo;

/**
 * Created by lorienzo9 on 31/10/17.
 */

public class Users {
    String nickName;
    String groupId;
    public Users(){
    }
    public Users (String groupId){
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
