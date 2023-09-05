package com.lrtech.lrbackend.services.client;

import com.lrtech.lrbackend.dtoModels.CustomerDTO;
import com.lrtech.lrbackend.models.Customer;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface ClientCustomerService {

    Customer saveCustomer(Customer customer);

    Customer saveCustomer(CustomerDTO customer);

    Customer createNewCustomer(Map<String, String> customerData);

    Optional<Customer> getCustomerById(UUID id);



}
