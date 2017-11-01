package com.ouyang.test.impl.mapper;

import com.ouyang.test.to.CurstomerOrderExtr;

public interface CurstomerOrderExtrMapper {
    int save(CurstomerOrderExtr curstomerOrderExtr);

    CurstomerOrderExtr selectByPrimaryKey(Long orderId);

    int delete(Long orderId);

    int update(CurstomerOrderExtr curstomerOrderExtr);
}