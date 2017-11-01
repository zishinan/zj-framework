package com.ouyang.test.impl.service;

import com.ouyang.test.to.SettingCategory;

public interface SettingCategoryService {
    int save(SettingCategory settingCategory);

    SettingCategory selectByPrimaryKey(Long id);

    int delete(Long id);

    int update(SettingCategory settingCategory);
}