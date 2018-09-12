package com.avatech.edi.mdm.bo;

import com.avatech.edi.common.data.List;

/**
 * @author Fancy
 * @date 2018/9/4
 */
public class UserFileds implements IUserFileds {

    private List<IUserFiled> userFileds;

    @Override
    public List<IUserFiled> getUserFileds() {
        return userFileds;
    }

    @Override
    public void setUserFileds(List<IUserFiled> userFileds) {
        this.userFileds = userFileds;
    }
}
