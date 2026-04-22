package com.library.libmanagement.controller;

import com.library.libmanagement.dto.CustomerDTO;
import com.library.libmanagement.entity.Customer;
import com.library.libmanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Customer addCustomer(@RequestBody CustomerDTO dto) {
        return customerService.addCustomer(dto);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "Customer deleted";
    }

    // 🔵 UPDATE CUSTOMER
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id,
                                   @RequestBody CustomerDTO dto) {

        return customerService.updateCustomer(id, dto);
    }
}