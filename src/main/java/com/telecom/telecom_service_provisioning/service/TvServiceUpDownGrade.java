package com.telecom.telecom_service_provisioning.service;

import com.telecom.telecom_service_provisioning.model.TvService;
import com.telecom.telecom_service_provisioning.repository.TvServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvServiceUpDownGrade {

    @Autowired
    private TvServiceRepository tvServiceRepository;

    public List<TvService> findAllExceptType(String excludedServiceType) {
        return tvServiceRepository.findByServiceTypeNot(excludedServiceType);
    }

    public List<TvService> findAll() {
        return tvServiceRepository.findAll();
    }
}
