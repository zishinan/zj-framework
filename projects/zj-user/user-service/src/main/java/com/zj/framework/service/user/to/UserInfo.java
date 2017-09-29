package com.zj.framework.service.user.to;

import com.zj.framework.baseto.type.Gender;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 用户信息 <br/>
 * @date 2017-09-26 下午 3:12 <br/>
 */
public class UserInfo implements Serializable {
	private Long loginInfoId;
	private String realName;
	private String cardId;
	private Gender gender;
	private Date birthday;
	private String imgUrl;

	public Long getLoginInfoId() {
		return loginInfoId;
	}

	public void setLoginInfoId(Long loginInfoId) {
		this.loginInfoId = loginInfoId;
	}

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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
