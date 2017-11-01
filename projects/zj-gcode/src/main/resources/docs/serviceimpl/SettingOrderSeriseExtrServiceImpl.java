package com.ouyang.test.impl.service;

import com.ouyang.test.to.SettingOrderSeriseExtr;

public interface SettingOrderSeriseExtrService {
    int save(SettingOrderSeriseExtr settingOrderSeriseExtr);

    SettingOrderSeriseExtr selectByPrimaryKey(Long id);

    int delete(Long id);

    int update(SettingOrderSeriseExtr settingOrderSeriseExtr);
}