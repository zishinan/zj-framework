package com.zj.framework.service.user.to.criteria;


import com.zj.framework.baseto.to.BaseCriteria;
import com.zj.framework.baseto.type.Gender;

import java.util.Date;

public class UserInfoCriteria extends BaseCriteria {
    private String realName;
    private String cardId;
    private Gender gender;
    private Date birthday;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
