package com.telecom.telecom_service_provisioning.repository;

import com.telecom.telecom_service_provisioning.model.TvService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TvServiceRepository extends JpaRepository<TvService, Integer> {

    @Query("SELECT t FROM TvService t WHERE t.serviceType <> :excludedServiceType")
    List<TvService> findByServiceTypeNot(String excludedServiceType);
}
