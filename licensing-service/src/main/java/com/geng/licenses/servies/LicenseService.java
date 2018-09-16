package com.geng.licenses.servies;

import com.geng.licenses.clients.OrganizationDiscoveryClient;
import com.geng.licenses.clients.OrganizationFeignClient;
import com.geng.licenses.clients.OrganizationRestTemplateClient;
import com.geng.licenses.config.ServiceConfig;
import com.geng.licenses.model.License;
import com.geng.licenses.model.Organization;
import com.geng.licenses.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LicenseService {


    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    ServiceConfig config;


    @Autowired
    OrganizationFeignClient organizationFeignClient;

    @Autowired
    OrganizationRestTemplateClient organizationRestClient;

    @Autowired
    OrganizationDiscoveryClient organizationDiscoveryClient;

    private Organization retrieveOrgInfo(String organizationId, String clientType) {
        Organization organization = null;
        switch (clientType) {
            case "feign": //使用Netflix的Feign客户端库来通过Ribbon调用服务
                System.out.println("I am using the feign client");
                organization = organizationFeignClient.getOrganization(organizationId);
                break;
            case "rest": //使用增强的Spring RestTemplate来调用基于Ribbon的服务
                System.out.println("I am using the rest client");
                organization = organizationRestClient.getOrganization(organizationId);
                break;
            case "discovery": //使用DiscoveryClient和标准的Spring RestTemplate类来调用服务
                System.out.println("I am using the discovery client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
                break;
            default:
                organization = organizationRestClient.getOrganization(organizationId);
        }

        return organization;
    }

    public License getLicense(String organizationId, String licenseId) {

        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        return license.withComment(config.getExampleProperty());
    }

    public List<License> getLicensesByOrg(String organizationId) {
        return licenseRepository.findByOrganizationId(organizationId);
    }

    public void saveLicense(License license) {
        license.withId(UUID.randomUUID().toString());
        licenseRepository.save(license);
    }

    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    public void deleteLicense(License license) {
        licenseRepository.delete(license.getLicenseId());
    }

    public License getLicense(String organizationId, String licenseId, String clientType) {
        final License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        Organization org = retrieveOrgInfo(organizationId, clientType);

        return license.withOrganizationName(org.getName())
                .withContactName(org.getContactName())
                .withContactEmail(org.getContactEmail())
                .withContactPhone(org.getContactPhone())
                .withComment(config.getExampleProperty());
    }
}
