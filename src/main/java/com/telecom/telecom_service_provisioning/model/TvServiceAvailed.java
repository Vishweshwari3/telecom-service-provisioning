package com.telecom.telecom_service_provisioning.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import com.telecom.telecom_service_provisioning.model.compositekeyModels.TvServicesAvailedId;

@Entity
@Table(name = "tv_services_availed")
public class TvServiceAvailed implements Serializable {

    @EmbeddedId
    private TvServicesAvailedId id;

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
    private TvService tvService;

    // Constructors
    public TvServiceAvailed() {}

    public TvServiceAvailed(TvServicesAvailedId id, LocalDate endDate, Boolean active, User user, TvService tvService) {
        this.id = id;
        this.endDate = endDate;
        this.active = active;
        this.user = user;
        this.tvService = tvService;
    }

    // Getters and Setters
    public TvServicesAvailedId getId() {
        return id;
    }

    public void setId(TvServicesAvailedId id) {
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

    public TvService getTvService() {
        return tvService;
    }

    public void setTvService(TvService tvService) {
        this.tvService = tvService;
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
