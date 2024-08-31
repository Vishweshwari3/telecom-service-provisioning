package com.telecom.telecom_service_provisioning.service;

import com.telecom.telecom_service_provisioning.model.InternetServiceAvailed;
import com.telecom.telecom_service_provisioning.model.compositekeyModels.InternetServicesAvailedId;
import com.telecom.telecom_service_provisioning.repository.InternetServiceAvailedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InternetServiceAvailedService {

    @Autowired
    private InternetServiceAvailedRepository internetServiceAvailedRepository;

    public List<InternetServiceAvailed> getActiveSubscribedServices(Integer userId) {
        List<InternetServiceAvailed> allServices = internetServiceAvailedRepository.findByUserId(userId);
        return allServices.stream()
                .filter(InternetServiceAvailed::getActive)
                .collect(Collectors.toList());
    }

    public void deactivateService(Integer userId, Integer serviceId, LocalDate startDate) {
        InternetServicesAvailedId id = new InternetServicesAvailedId(userId, serviceId, startDate);
        Optional<InternetServiceAvailed> serviceAvailed = internetServiceAvailedRepository.findById(id);
        if (serviceAvailed.isPresent()) {
            InternetServiceAvailed serviceToUpdate = serviceAvailed.get();
            serviceToUpdate.setActive(false);
            internetServiceAvailedRepository.save(serviceToUpdate);
        }
    }
}
