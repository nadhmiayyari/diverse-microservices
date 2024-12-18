package com.app;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {

    public void registerCustomer(CustomerRequest customerRequest){
            Customer customer = Customer.builder()
                    .firstName(customerRequest.firstName())
                    .lastName(customerRequest.lastName())
                    .email(customerRequest.email())
                    .build();
            customerRepository.saveAndFlush(customer);

           FraudCheckResponse response =  restTemplate.getForObject("localhost:8081/api/v1/fraud", FraudCheckResponse.class,customer.getId());
           if(response.isFraudulent()){
               throw new IllegalArgumentException("fraudster");
           }
    }
}
