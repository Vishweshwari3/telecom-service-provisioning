package com.telecom.telecom_service_provisioning.model;

import java.math.BigDecimal;

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
@Table(name = "internet_services")
public class InternetService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "service_name", nullable = false, length = 100)
    private String serviceName;

    @Column(name = "description")
    private String description;

    @Column(name = "service_type", length = 50)
    private String serviceType;

    @Column(name = "service_download_speed")
    private Integer serviceDownloadSpeed;

    @Column(name = "service_upload_speed")
    private Integer serviceUploadSpeed;

    @Column(name = "benefits")
    private String benefits;

    @Column(name = "criteria")
    private String criteria;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "monthly_cost", precision = 10, scale = 2)
    private BigDecimal monthlyCost;
}
