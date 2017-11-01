package com.ouyang.test.impl.mapper;

import com.ouyang.test.to.SettingOrderSeriseExtr;

public interface SettingOrderSeriseExtrMapper {
    int save(SettingOrderSeriseExtr settingOrderSeriseExtr);

    SettingOrderSeriseExtr selectByPrimaryKey(Long id);

    int delete(Long id);

    int update(SettingOrderSeriseExtr settingOrderSeriseExtr);
}