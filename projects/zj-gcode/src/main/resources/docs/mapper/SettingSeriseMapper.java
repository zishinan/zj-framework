package com.ouyang.test.impl.mapper;

import com.ouyang.test.to.SettingSerise;

public interface SettingSeriseMapper {
    int save(SettingSerise settingSerise);

    SettingSerise selectByPrimaryKey(Long id);

    int delete(Long id);

    int update(SettingSerise settingSerise);
}