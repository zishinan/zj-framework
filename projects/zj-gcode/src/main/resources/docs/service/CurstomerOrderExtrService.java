package com.ouyang.test.impl.service;

import com.ouyang.test.to.CurstomerOrderExtr;

public interface CurstomerOrderExtrService {
    int save(CurstomerOrderExtr curstomerOrderExtr);

    CurstomerOrderExtr selectByPrimaryKey(Long orderId);

    int delete(Long orderId);

    int update(CurstomerOrderExtr curstomerOrderExtr);
}