package com.ouyang.test.impl.mapper;

import com.ouyang.test.to.CustomerFaq;

public interface CustomerFaqMapper {
    int save(CustomerFaq customerFaq);

    CustomerFaq selectByPrimaryKey(Long id);

    int delete(Long id);

    int update(CustomerFaq customerFaq);
}