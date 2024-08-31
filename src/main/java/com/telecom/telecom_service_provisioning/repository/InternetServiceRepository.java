package com.telecom.telecom_service_provisioning.repository;

import com.telecom.telecom_service_provisioning.model.InternetService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternetServiceRepository extends JpaRepository<InternetService, Integer> {

    @Query("SELECT i FROM InternetService i WHERE i.serviceType <> :excludedServiceType")
    List<InternetService> findByServiceTypeNot(String excludedServiceType);
}
