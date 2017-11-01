package com.ouyang.test.impl.service;

import com.ouyang.test.to.CustomerOrderRecord;

public interface CustomerOrderRecordService {
    int save(CustomerOrderRecord customerOrderRecord);

    CustomerOrderRecord selectByPrimaryKey(Long id);

    int delete(Long id);

    int update(CustomerOrderRecord customerOrderRecord);
}