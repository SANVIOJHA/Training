package com.library.libmanagement.service;

import com.library.libmanagement.dto.CustomerDTO;
import com.library.libmanagement.entity.Customer;
import com.library.libmanagement.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public Customer addCustomer(CustomerDTO dto) {

        String hashedPwd = passwordEncoder.encode(dto.getPwd());

        Customer customer = Customer.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .pwd(hashedPwd)
                .build();

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long id) {

        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer registerCustomer(CustomerDTO dto) {
        return addCustomer(dto);
    }

    // 🔵 UPDATE CUSTOMER
    public Customer updateCustomer(Long id, CustomerDTO dto) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (dto.getEmail() != null) {
            customer.setEmail(dto.getEmail());
        }

        if (dto.getPwd() != null) {
            String encodedPwd = passwordEncoder.encode(dto.getPwd());
            customer.setPwd(encodedPwd);
        }

        if (dto.getName() != null) {
            customer.setName(dto.getName());
        }

        if (dto.getPhone() != null) {
            customer.setPhone(dto.getPhone());
        }

        if (dto.getAddress() != null) {
            customer.setAddress(dto.getAddress());
        }

        return customerRepository.save(customer);
    }
}