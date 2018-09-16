package com.geng.organization.repository;

import com.geng.organization.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization,String> {

    public Organization findById(String organizationId);
}
