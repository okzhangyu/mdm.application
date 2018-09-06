package com.avatech.edi.mdm.bo;

/**
 * @author Fancy
 * @date 2018/9/4
 */
public interface IUserFiled {

    Object getUniqueKey();

    void setUniqueKey(Object uniqueKey);

    String getName();

    void setName(String name);

    Object getValue();

    void setValue(Object value);
}
