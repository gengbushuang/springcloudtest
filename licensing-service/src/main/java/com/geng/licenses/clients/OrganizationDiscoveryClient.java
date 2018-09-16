package com.geng.licenses.clients;

import com.geng.licenses.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
//Spring DiscoveryClient提供了对Ribbon和Ribbon中缓存的注册服务的最低层次访问。
// 可以查询通过Ribbon注册的所有服务以及这些服务对应的URL
@Component
public class OrganizationDiscoveryClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    public Organization getOrganization(String organizationId){
        RestTemplate restTemplate = new RestTemplate();
        //获取组织服务的所有实例的列表
        List<ServiceInstance> instances = discoveryClient.getInstances("organizationservice");

        if(instances.size()==0){return null;}
        //检索要调用的服务端点
        String serviceUri = String.format("%s/v1/organizations/%s", instances.get(0).getUri().toString(), organizationId);
        //使用标准的Spring REST模板类去调用服务
        ResponseEntity<Organization> restExchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null, Organization.class, organizationId);

        return restExchange.getBody();
    }
}
