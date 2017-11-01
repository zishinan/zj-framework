package com.ouyang.test.impl.mapper;

import com.ouyang.test.to.SettingCategory;

public interface SettingCategoryMapper {
    int save(SettingCategory settingCategory);

    SettingCategory selectByPrimaryKey(Long id);

    int delete(Long id);

    int update(SettingCategory settingCategory);
}