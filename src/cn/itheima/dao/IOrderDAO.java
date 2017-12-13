package cn.itheima.dao;

import java.util.List;

import cn.itheima.domain.Customer;
import cn.itheima.domain.Order;

public interface IOrderDAO {

	List<Order> findByCustomer(Customer customer);

	List<Order> findOrderByCustomerPage(Customer c, int pageNum, int currentCount);

	public int findTotalCount();

}
