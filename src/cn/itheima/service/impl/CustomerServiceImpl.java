package cn.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itheima.dao.ICustomerDAO;
import cn.itheima.domain.Customer;
import cn.itheima.service.ICustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerDAO customerDao;

	@Override
	public List<Customer> findAllCustomer() {
		return customerDao.findAllCustomer();
	}

	@Override
	public void addCustomer(Customer customer) {
		customerDao.addCustomer(customer);
	}

	@Override
	public void delCustomer(Customer customer) {
		Customer c = customerDao.findCustomerById(customer.getId());
		 if (c != null) {
			customerDao.delCustomer(c);
		 }
	}
}
