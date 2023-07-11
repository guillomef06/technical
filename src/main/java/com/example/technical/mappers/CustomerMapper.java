package com.example.technical.mappers;

import com.example.technical.models.entities.Customer;
import com.example.technical.models.request.CustomerRequestRemoteObject;
import com.example.technical.models.response.CustomerResponseRemoteObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(config = UnmappedTargetPolicyConfig.class)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "id", ignore = true)
    Customer mapRequestToEntity(CustomerRequestRemoteObject dto);
    CustomerResponseRemoteObject mapEntityToResponse(Customer entity);
}
