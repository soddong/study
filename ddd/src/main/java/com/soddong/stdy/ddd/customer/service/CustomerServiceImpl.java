package com.soddong.stdy.ddd.customer.service;

import com.soddong.stdy.ddd.customer.adapter.in.web.dto.CustomerQuery;
import com.soddong.stdy.ddd.customer.domain.model.Customer;
import com.soddong.stdy.ddd.customer.domain.model.exception.CustomerNotFoundException;
import com.soddong.stdy.ddd.customer.domain.model.vo.PhoneNumber;
import com.soddong.stdy.ddd.customer.port.in.CustomerUseCase;
import com.soddong.stdy.ddd.customer.port.out.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public Customer register(String name, String phone) {
        PhoneNumber phoneNumber = new PhoneNumber(phone);
        return customerRepository.save(new Customer(null, name, phoneNumber));
    }


    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public List<Customer> getAll(CustomerQuery query) {
        return customerRepository.findAll(query);
    }
}