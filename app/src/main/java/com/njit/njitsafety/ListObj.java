package com.njit.njitsafety;

/**
 * Created by Anuradha on 27-03-2017.
 */

public class ListObj {

String name,desc,mode;

    public ListObj(String name, String desc, String mode) {
        this.name = name;
        this.desc = desc;
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return name+","+desc+","+mode;
    }
}
