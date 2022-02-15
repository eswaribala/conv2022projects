package com.virtusa.customerapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.customerapi.models.Address;
import com.virtusa.customerapi.models.Customer;
import com.virtusa.customerapi.repositories.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
	private CustomerRepository customerRepo;
    
    //insert
    public Customer addCustomer(Customer customer) {
    	
    	if((customer.getAddessList()!=null)&&(customer.getAddessList().size()>0)) {
    		for(Address address:customer.getAddessList()) {
    			address.setCustomer(customer);
    		}
    		
    	}
    	return this.customerRepo.save(customer);
    }
    
    //select all
    public List<Customer> getAllCustomers(){
    	return this.customerRepo.findAll();
    }
    
    //select by id
    public Customer getCustomerById(long customerId) {
    	return this.customerRepo.findById(customerId).orElse(null);
    }
    
    //delete query
    
    public boolean deleteCustomerById(long customerId) {
    	boolean status=false;
    	this.customerRepo.deleteById(customerId);
    	if(this.getCustomerById(customerId)==null)
    		status=true;
    	return status;
    	
    }
    
}
