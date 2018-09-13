package com.avatech.edi.mdm.bo;


/**
 * @author Fancy
 * @date 2018/9/4
 */
public class UserFiled implements IUserFiled {

    private Object uniqueKey;

    @Override
    public Object getUniqueKey() {
        return uniqueKey;
    }

    @Override
    public void setUniqueKey(Object uniqueKey) {
        uniqueKey = uniqueKey;
    }

    private String name;
    @Override
    public String getName() {
        return name;
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    private Object value;

    @Override
    public Object getValue() {
        return value;
    }


    @Override
    public void setValue(Object value) {
        this.value = value;
    }
}
