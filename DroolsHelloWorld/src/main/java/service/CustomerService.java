package service;

import java.util.ArrayList;
import java.util.List;

import bankAccount.Customer;

public class CustomerService {

	 public List<Customer> getListCustomer() {
	        List<Customer> result = new ArrayList<Customer>();
	        result.add(new Customer("H�ron", "Nicolas", "Fr"));
	        result.add(new Customer("H�ron", "James", "GB"));
	        result.add(new Customer("H�ron", "Nicolas", "GB"));
	        return result;
	    }

}
