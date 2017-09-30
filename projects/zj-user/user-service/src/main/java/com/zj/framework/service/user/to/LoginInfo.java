package com.zj.framework.service.user.to;

import com.zj.framework.baseto.to.BaseTO;

import java.util.Date;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 登录信息 <br/>
 * @date 2017-09-26 下午 3:12 <br/>
 */
public class LoginInfo extends BaseTO {
    private Long loginInfoId;
    private String userName;
    private String phoneNumber;
    private String email;
    private String password;
    private Date createTime;

    public Long getLoginInfoId() {
        return loginInfoId;
    }

    public void setLoginInfoId(Long loginInfoId) {
        this.loginInfoId = loginInfoId;
    }

    public String getUserName() {
        return userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
