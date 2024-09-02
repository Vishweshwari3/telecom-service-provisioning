package com.telecom.telecom_service_provisioning.controller;

import com.telecom.telecom_service_provisioning.model.InternetService;
import com.telecom.telecom_service_provisioning.model.InternetServiceAvailed;
import com.telecom.telecom_service_provisioning.model.TvService;
import com.telecom.telecom_service_provisioning.model.TvServiceAvailed;
import com.telecom.telecom_service_provisioning.service.InternetServiceAvailedService;
import com.telecom.telecom_service_provisioning.service.InternetServiceUpDownGrade;
import com.telecom.telecom_service_provisioning.service.TvServiceAvailedService;
import com.telecom.telecom_service_provisioning.service.TvServiceUpDownGrade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private InternetServiceAvailedService internetServiceAvailedService;

    @Autowired
    private TvServiceAvailedService tvServiceAvailedService;

    @Autowired
    private InternetServiceUpDownGrade internetServiceUpDownGrade;

    @Autowired
    private TvServiceUpDownGrade tvServiceUpDownGrade;

    @GetMapping("/subscribed_services/{userId}")
    public ResponseEntity<Map<String, List<?>>> getSubscribedServices(@PathVariable Integer userId) {
        List<InternetServiceAvailed> internetServices = internetServiceAvailedService.getActiveSubscribedServices(userId);
        List<TvServiceAvailed> tvServices = tvServiceAvailedService.getActiveSubscribedServices(userId);

        if (internetServices.isEmpty() && tvServices.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "message", List.of("No subscribed services found for the given user.")
            ));
        }

        List<Map<String, Object>> simplifiedInternetServices = internetServices.stream()
            .filter(InternetServiceAvailed::getActive) // Only active services
            .map(service -> Map.of(
                "id", Map.of(
                    "userId", service.getUserId(),
                    "serviceId", service.getServiceId(),
                    "startDate", service.getStartDate()
                ),
                "endDate", service.getEndDate(),
                "active", service.getActive()
            ))
            .collect(Collectors.toList());

        List<Map<String, Object>> simplifiedTvServices = tvServices.stream()
            .filter(TvServiceAvailed::getActive) // Only active services
            .map(service -> Map.of(
                "id", Map.of(
                    "userId", service.getUserId(),
                    "serviceId", service.getServiceId(),
                    "startDate", service.getStartDate()
                ),
                "endDate", service.getEndDate(),
                "active", service.getActive()
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(Map.of(
            "message", List.of("Subscribed services retrieved successfully."),
            "internetServices", simplifiedInternetServices,
            "tvServices", simplifiedTvServices
        ));
    }

    @GetMapping("/available_services/{userId}")
    public ResponseEntity<Map<String, List<?>>> getAvailableServicesForUpgradeOrDowngrade(@PathVariable Integer userId) {
        List<InternetServiceAvailed> subscribedInternetServices = internetServiceAvailedService.getActiveSubscribedServices(userId);
        List<TvServiceAvailed> subscribedTvServices = tvServiceAvailedService.getActiveSubscribedServices(userId);

        List<InternetService> availableInternetServices = subscribedInternetServices.isEmpty() ?
            internetServiceUpDownGrade.findAll() :
            internetServiceUpDownGrade.findAllExceptType(subscribedInternetServices.get(0).getInternetService().getServiceType());

        List<TvService> availableTvServices = subscribedTvServices.isEmpty() ?
            tvServiceUpDownGrade.findAll() :
            tvServiceUpDownGrade.findAllExceptType(subscribedTvServices.get(0).getTvService().getServiceType());

        if (availableInternetServices.isEmpty() && availableTvServices.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "message", List.of("No available services for upgrade or downgrade.")
            ));
        }

        return ResponseEntity.ok(Map.of(
            "message", List.of("Available services for upgrade or downgrade retrieved successfully."),
            "internetServices", availableInternetServices,
            "tvServices", availableTvServices
        ));
    }

    @PostMapping("/deactivate_internet_service")
    public ResponseEntity<String> deactivateInternetService(
            @RequestParam Integer userId,
            @RequestParam Integer serviceId,
            @RequestParam LocalDate startDate) {
        try {
            internetServiceAvailedService.deactivateService(userId, serviceId, startDate);
            return ResponseEntity.ok("Internet service deactivated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found or already deactivated.");
        }
    }

    @PostMapping("/deactivate_tv_service")
    public ResponseEntity<String> deactivateTvService(
            @RequestParam Integer userId,
            @RequestParam Integer serviceId,
            @RequestParam LocalDate startDate) {
        try {
            tvServiceAvailedService.deactivateService(userId, serviceId, startDate);
            return ResponseEntity.ok("TV service deactivated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found or already deactivated.");
        }
    }
}
