package com.telecom.telecom_service_provisioning.service;

import com.telecom.telecom_service_provisioning.model.TvServiceAvailed;
import com.telecom.telecom_service_provisioning.model.compositekeyModels.TvServicesAvailedId;
import com.telecom.telecom_service_provisioning.repository.TvServiceAvailedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TvServiceAvailedService {

    @Autowired
    private TvServiceAvailedRepository tvServiceAvailedRepository;

    public List<TvServiceAvailed> getActiveSubscribedServices(Integer userId) {
        List<TvServiceAvailed> allServices = tvServiceAvailedRepository.findByUserId(userId);
        return allServices.stream()
                .filter(TvServiceAvailed::getActive)
                .collect(Collectors.toList());
    }

    public void deactivateService(Integer userId, Integer serviceId, LocalDate startDate) {
        TvServicesAvailedId id = new TvServicesAvailedId(userId, serviceId, startDate);
        Optional<TvServiceAvailed> serviceAvailed = tvServiceAvailedRepository.findById(id);
        if (serviceAvailed.isPresent()) {
            TvServiceAvailed serviceToUpdate = serviceAvailed.get();
            serviceToUpdate.setActive(false);
            tvServiceAvailedRepository.save(serviceToUpdate);
        }
    }
}
