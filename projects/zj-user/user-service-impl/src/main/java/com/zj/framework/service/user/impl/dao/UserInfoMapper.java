package com.zj.framework.service.user.impl.dao;

import com.zj.framework.service.user.to.UserInfo;

public interface UserInfoMapper {
    int save(UserInfo userInfo);

    UserInfo selectByPrimaryKey(Integer loginInfoId);

    int delete(Integer loginInfoId);

    int update(UserInfo iosChannel);
}
