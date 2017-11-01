package com.ouyang.test.impl.mapper;

import com.ouyang.test.to.CustomerOrderRecord;

public interface CustomerOrderRecordMapper {
    int save(CustomerOrderRecord customerOrderRecord);

    CustomerOrderRecord selectByPrimaryKey(Long id);

    int delete(Long id);

    int update(CustomerOrderRecord customerOrderRecord);
}