package com.ouyang.test.impl.mapper;

import com.ouyang.test.to.CurstomerOrder;

public interface CurstomerOrderMapper {
    int save(CurstomerOrder curstomerOrder);

    CurstomerOrder selectByPrimaryKey(Long orderId);

    int delete(Long orderId);

    int update(CurstomerOrder curstomerOrder);
}