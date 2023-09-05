package com.lrtech.lrbackend.mappers;

import com.lrtech.lrbackend.dtoModels.CustomerDTO;
import com.lrtech.lrbackend.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerOrders", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    Customer customerDtoToCustomer(CustomerDTO customerDTO);

    CustomerDTO customerToCustomerDto(Customer customer);
}
