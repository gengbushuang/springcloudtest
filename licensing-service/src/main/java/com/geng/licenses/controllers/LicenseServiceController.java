package com.geng.licenses.controllers;

import com.geng.licenses.config.ServiceConfig;
import com.geng.licenses.model.License;
import com.geng.licenses.servies.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//告诉Spring Boot这是一个基于REST的服务，它将自动序列化和反序列化服务请求和响应到JSON
@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    @Autowired
    private LicenseService licenseService;

    @Autowired
    private ServiceConfig serviceConfig;


    public List<License> getLicenses(@PathVariable("organizationId") String organizationId) {
        return licenseService.getLicensesByOrg(organizationId);
    }

    @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
    public License getLicenses(@PathVariable("organizationId") String organizationId,
                               @PathVariable("licenseId") String licenseId) {

        return licenseService.getLicense(organizationId, licenseId);
    }

    @RequestMapping(value = "{licenseId}", method = RequestMethod.PUT)
    public String updateLicense(@PathVariable("licenseId") String licenseId) {
        return String.format("This is the put");
    }

    @RequestMapping(value = "{licenseId}", method = RequestMethod.POST)
    public void saveLicense(@RequestBody License license) {
        licenseService.saveLicense(license);
    }

    @RequestMapping(value = "{licenseId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteLicense(@PathVariable("licenseId") String licenseId) {
        return String.format("This is the delete");
    }
    //clientType确定Spring REST要使用的客户端类型
    @RequestMapping(value = "/{licenseId}/{clientType}",method = RequestMethod.GET)
    public License getLicensesWithClient(@PathVariable("organizationId") String organizationId,
                                         @PathVariable("licenseId") String licenseId,
                                         @PathVariable("clientType") String clientType){
        return licenseService.getLicense(organizationId,licenseId,clientType);
    }
}
