package com.telecom.telecom_service_provisioning.repository;

import com.telecom.telecom_service_provisioning.model.TvServiceAvailed;
import com.telecom.telecom_service_provisioning.model.compositekeyModels.TvServicesAvailedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TvServiceAvailedRepository extends JpaRepository<TvServiceAvailed, TvServicesAvailedId> {

    @Query("SELECT t FROM TvServiceAvailed t WHERE t.user.userId = :userId")
    List<TvServiceAvailed> findByUserId(Integer userId);

    @Override
    @Query("SELECT t FROM TvServiceAvailed t WHERE t.id = :id")
    Optional<TvServiceAvailed> findById(TvServicesAvailedId id);
}
