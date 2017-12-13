package cn.itheima.dao;

import java.util.List;

import cn.itheima.domain.Customer;

public interface ICustomerDAO {

	List<Customer> findAllCustomer();

	void addCustomer(Customer customer);

	void delCustomer(Customer customer);

	Customer findCustomerById(Integer id);

	

}
