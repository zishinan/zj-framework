package com.ouyang.test.impl.service;

import com.ouyang.test.to.SettingSerise;

public interface SettingSeriseService {
    int save(SettingSerise settingSerise);

    SettingSerise selectByPrimaryKey(Long id);

    int delete(Long id);

    int update(SettingSerise settingSerise);
}