package com.telecom.telecom_service_provisioning.repository;

import com.telecom.telecom_service_provisioning.model.InternetServiceAvailed;
import com.telecom.telecom_service_provisioning.model.compositekeyModels.InternetServicesAvailedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InternetServiceAvailedRepository extends JpaRepository<InternetServiceAvailed, InternetServicesAvailedId> {

    @Query("SELECT i FROM InternetServiceAvailed i WHERE i.user.userId = :userId")
    List<InternetServiceAvailed> findByUserId(Integer userId);

    @Override
    @Query("SELECT i FROM InternetServiceAvailed i WHERE i.id = :id")
    Optional<InternetServiceAvailed> findById(InternetServicesAvailedId id);
}
