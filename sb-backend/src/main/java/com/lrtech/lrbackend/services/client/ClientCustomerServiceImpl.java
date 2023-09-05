package com.lrtech.lrbackend.services.client;

import com.lrtech.lrbackend.dtoModels.CustomerDTO;
import com.lrtech.lrbackend.mappers.CustomerMapper;
import com.lrtech.lrbackend.models.Customer;
import com.lrtech.lrbackend.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClientCustomerServiceImpl implements ClientCustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer saveCustomer(CustomerDTO customer) {
        return customerRepository.save(
                        customerMapper.customerDtoToCustomer(customer));
    }

    @Override
    public Customer createNewCustomer(Map<String, String> customerData) {
        Customer newCustomer = Customer.builder()
                .name(customerData.get("name"))
                .email(customerData.get("email"))
                .build();
        return customerRepository.save(newCustomer);
    }


    @Override
    public Optional<Customer> getCustomerById(UUID id) {
        return Optional.ofNullable(
                customerRepository.findById(id)
                        .orElse(null));
    }

}
