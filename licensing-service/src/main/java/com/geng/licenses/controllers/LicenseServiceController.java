package com.geng.licenses.controllers;

import com.geng.licenses.model.License;
import com.geng.licenses.servies.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

//告诉Spring Boot这是一个基于REST的服务，它将自动序列化和反序列化服务请求和响应到JSON
@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    @Autowired
    private LicenseService licenseService;

    @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
    public License getLicenses(@PathVariable("organizationId") String organizationId,
                               @PathVariable("licenseId") String licenseId) {

        return new License().withId(licenseId)
                .withOrganizationId(organizationId)
                .withProductName("Test Product Name")
                .withLicenseType("PerSeat");
    }

    @RequestMapping(value = "{licenseId}", method = RequestMethod.PUT)
    public String updateLicense(@PathVariable("licenseId") String licenseId) {
        return String.format("This is the put");
    }

    @RequestMapping(value = "{licenseId}", method = RequestMethod.POST)
    public String saveLicense(@PathVariable("licenseId") String licenseId) {
        return String.format("This is the save");
    }

    @RequestMapping(value = "{licenseId}", method = RequestMethod.DELETE)
    public String deleteLicense(@PathVariable("licenseId") String licenseId) {
        return String.format("This is the delete");
    }
}
