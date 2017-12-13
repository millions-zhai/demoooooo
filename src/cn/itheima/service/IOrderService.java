package cn.itheima.service;

import java.util.List;

import cn.itheima.domain.Order;
import cn.itheima.domain.PageBean;

public interface IOrderService {

	List<Order> findOrderByCustomer(Integer customerId);

	PageBean<Order> findOrderByCustomerPage(Integer customerId, int pageNum, int currentCount);

}
