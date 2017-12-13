package cn.itheima.service;

import java.util.List;

import cn.itheima.domain.Customer;

public interface ICustomerService {

	List<Customer> findAllCustomer();

	void addCustomer(Customer customer);

	void delCustomer(Customer customer);

}
