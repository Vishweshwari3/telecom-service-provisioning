package com.telecom.telecom_service_provisioning.service;

import com.telecom.telecom_service_provisioning.model.InternetService;
import com.telecom.telecom_service_provisioning.repository.InternetServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternetServiceUpDownGrade {

    @Autowired
    private InternetServiceRepository internetServiceRepository;

    public List<InternetService> findAllExceptType(String excludedServiceType) {
        return internetServiceRepository.findByServiceTypeNot(excludedServiceType);
    }

    public List<InternetService> findAll() {
        return internetServiceRepository.findAll();
    }
}
