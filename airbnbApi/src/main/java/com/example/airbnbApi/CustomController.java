package com.example.airbnbApi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomController {

    private final CustomerRepository customerRepository;

    public CustomController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> list(){
        return customerRepository.findAll();
    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer(
                request.name,
                request.email,
                request.age
        );
        customerRepository.save(customer);

    }

    record  NewCustomerRequest(
            String name,
            String email,
            Integer age
    ){

    }


}
