package com.ouyang.test.impl.service;

import com.ouyang.test.to.CustomerFaq;

public interface CustomerFaqService {
    int save(CustomerFaq customerFaq);

    CustomerFaq selectByPrimaryKey(Long id);

    int delete(Long id);

    int update(CustomerFaq customerFaq);
}