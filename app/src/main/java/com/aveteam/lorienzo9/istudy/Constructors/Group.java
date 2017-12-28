package com.aveteam.lorienzo9.istudy.Constructors;

/**
 * Created by lorienzo9 on 19/12/17.
 */

public class Group {
    String creator_id, name, validate_code;
    public Group(){
    }
    public Group(String creator_id, String name, String validate_code){
        this.creator_id = creator_id;
        this.name = name;
        this.validate_code = validate_code;
    }

    public String getName() {
        return name;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public String getValidate_code() {
        return validate_code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public void setValidate_code(String validate_code) {
        this.validate_code = validate_code;
    }
}
