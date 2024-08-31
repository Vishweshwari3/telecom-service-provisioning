package com.telecom.telecom_service_provisioning.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import com.telecom.telecom_service_provisioning.model.compositekeyModels.InternetServicesAvailedId;

@Entity
@Table(name = "internet_services_availed")
public class InternetServiceAvailed implements Serializable {

    @EmbeddedId
    private InternetServicesAvailedId id;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @MapsId("serviceId")
    @JoinColumn(name = "service_id", insertable = false, updatable = false)
    private InternetService internetService;

    // Constructors
    public InternetServiceAvailed() {}

    public InternetServiceAvailed(InternetServicesAvailedId id, LocalDate endDate, Boolean active, User user, InternetService internetService) {
        this.id = id;
        this.endDate = endDate;
        this.active = active;
        this.user = user;
        this.internetService = internetService;
    }

    // Getters and Setters
    public InternetServicesAvailedId getId() {
        return id;
    }

    public void setId(InternetServicesAvailedId id) {
        this.id = id;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public InternetService getInternetService() {
        return internetService;
    }

    public void setInternetService(InternetService internetService) {
        this.internetService = internetService;
    }

    // Methods to expose only necessary fields
    public Integer getUserId() {
        return id.getUserId();
    }

    public Integer getServiceId() {
        return id.getServiceId();
    }

    public LocalDate getStartDate() {
        return id.getStartDate();
    }
}
