package com.telecom.telecom_service_provisioning.model.compositekeyModels;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class InternetServicesAvailedId implements Serializable {
    private Integer userId;
    private Integer serviceId;
    private LocalDate startDate;

    // Constructors
    public InternetServicesAvailedId() {
    }

    public InternetServicesAvailedId(Integer userId, Integer serviceId, LocalDate startDate) {
        this.userId = userId;
        this.serviceId = serviceId;
        this.startDate = startDate;
    }

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternetServicesAvailedId that = (InternetServicesAvailedId) o;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(serviceId, that.serviceId) &&
               Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, serviceId, startDate);
    }
}
