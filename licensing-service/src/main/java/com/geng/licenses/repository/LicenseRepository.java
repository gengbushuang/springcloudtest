package com.geng.licenses.repository;

import com.geng.licenses.model.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//告诉Spring Boot这是一个JPA存储库类
@Repository
public interface LicenseRepository extends CrudRepository<License, String> {//定义正在扩展Spring CrudRepository

    public List<License> findByOrganizationId(String organizationId);

    public License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);
}
