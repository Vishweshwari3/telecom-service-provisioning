package com.telecom.telecom_service_provisioning.model;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pending_requests")
public class PendingRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requestid")
    private Integer requestId;

    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "service_type", length = 20)
    private String serviceType;

    @Column(name = "request_status", length = 15)
    private String requestStatus;

    @Column(name = "remarks", length = 100)
    private String remarks;

    @Column(name = "active")
    private Boolean active;
}
