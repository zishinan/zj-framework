package com.ouyang.test.impl.service;

import com.ouyang.test.to.CurstomerOrder;

public interface CurstomerOrderService {
    int save(CurstomerOrder curstomerOrder);

    CurstomerOrder selectByPrimaryKey(Long orderId);

    int delete(Long orderId);

    int update(CurstomerOrder curstomerOrder);
}