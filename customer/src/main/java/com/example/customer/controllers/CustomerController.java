package com.example.customer.controllers;

import com.example.customer.models.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {
    private List<Customer> customerList = new ArrayList<>();

    @PostMapping("/customer")
    public Object addCustomer(@RequestBody Customer newCustomer) {
        for (Customer item : customerList) {
            if (newCustomer.getId().equals(item.getId())){
                return "Da ton tai id";
            }
        }
        customerList.add(newCustomer);
        return newCustomer;
    }

    @GetMapping("/customer")
    public List<Customer> getAll() {
        return customerList;
    }

    @GetMapping("/customer/{id}")
    public Object findById(@PathVariable Long id) {
        int count = 0;
        Customer customer = new Customer();
        for (Customer item : customerList) {
            if (item.getId().equals(id)) {
                customer = item;
                count ++;
            }
        }
        if (count!=0){
            return customer;
        }
        return "Khong tim thay Id";
    }

    @PutMapping("/customer/{id}")
    public List<Customer> replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
        int count = 0;
        for (Customer item : customerList) {
            if (item.getId().equals(id)) {
                item.setName(newCustomer.getName());
                item.setEmail(newCustomer.getEmail());
                count++;
            }
        }
        if (count == 0) {
            Customer customer = new Customer(id, newCustomer.getName(), newCustomer.getEmail());
            addCustomer(customer);
        }
        return customerList;
    }

    @DeleteMapping("/customer/{id}")
    List<Customer> deleteCustomer(@PathVariable Long id) {
        customerList.removeIf(item -> item.getId().equals(id));
        return customerList;
    }
}
