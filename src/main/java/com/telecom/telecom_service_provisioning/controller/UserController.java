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
    public Map<String, List<?>> getSubscribedServices(@PathVariable Integer userId) {
        List<InternetServiceAvailed> internetServices = internetServiceAvailedService.getActiveSubscribedServices(userId);
        List<TvServiceAvailed> tvServices = tvServiceAvailedService.getActiveSubscribedServices(userId);

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

        return Map.of(
            "internetServices", simplifiedInternetServices,
            "tvServices", simplifiedTvServices
        );
    }

    @GetMapping("/available_services/{userId}")
    public Map<String, List<?>> getAvailableServicesForUpgradeOrDowngrade(@PathVariable Integer userId) {
 
    	    // Fetch active subscribed internet services for the user
    	    List<InternetServiceAvailed> internetServices = internetServiceAvailedService.getActiveSubscribedServices(userId);
    	    
    	    // Fetch active subscribed TV services for the user
    	    List<TvServiceAvailed> tvServices = tvServiceAvailedService.getActiveSubscribedServices(userId);

    	    // Extract the service types from the active internet services
    	    List<String> currentInternetServiceTypes = internetServices.stream()
    	        .filter(InternetServiceAvailed::getActive) // Only active services
    	        .map(service -> service.getInternetService().getServiceType()) // Get the service type
    	        .distinct()
    	        .collect(Collectors.toList());

    	    // Extract the service types from the active TV services
    	    List<String> currentTvServiceTypes = tvServices.stream()
    	        .filter(TvServiceAvailed::getActive) // Only active services
    	        .map(service -> service.getTvService().getServiceType()) // Get the service type
    	        .distinct()
    	        .collect(Collectors.toList());

    	    // Find available internet services for upgrade/downgrade excluding current service types
    	    List<InternetService> availableInternetServices = currentInternetServiceTypes.stream()
    	        .flatMap(type -> internetServiceUpDownGrade.findAllExceptType(type).stream())
    	        .distinct()
    	        .collect(Collectors.toList());

    	    // Find available TV services for upgrade/downgrade excluding current service types
    	    List<TvService> availableTvServices = currentTvServiceTypes.stream()
    	        .flatMap(type -> tvServiceUpDownGrade.findAllExceptType(type).stream())
    	        .distinct()
    	        .collect(Collectors.toList());

    	    return Map.of(
    	        "internetServices", availableInternetServices,
    	        "tvServices", availableTvServices
    	    );
    	}

    @PostMapping("/deactivate_internet_service")
    public void deactivateInternetService(
            @RequestParam Integer userId,
            @RequestParam Integer serviceId,
            @RequestParam LocalDate startDate) {
        internetServiceAvailedService.deactivateService(userId, serviceId, startDate);
    }

    @PostMapping("/deactivate_tv_service")
    public void deactivateTvService(
            @RequestParam Integer userId,
            @RequestParam Integer serviceId,
            @RequestParam LocalDate startDate) {
        tvServiceAvailedService.deactivateService(userId, serviceId, startDate);
    }
}
