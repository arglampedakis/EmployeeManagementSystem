package com.cite.employee_managment.service;

import com.cite.employee_managment.model.Attribute;
import com.cite.employee_managment.repo.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public Attribute save(Attribute attribute) {
        return attributeRepository.save(attribute);
    }
}
