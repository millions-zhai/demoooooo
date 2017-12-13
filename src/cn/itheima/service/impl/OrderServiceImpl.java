package cn.itheima.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itheima.dao.ICustomerDAO;
import cn.itheima.dao.IOrderDAO;
import cn.itheima.domain.Customer;
import cn.itheima.domain.Order;
import cn.itheima.domain.PageBean;
import cn.itheima.service.IOrderService;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private ICustomerDAO customerDao;

	@Autowired
	private IOrderDAO orderDao;

	@Override
	public List<Order> findOrderByCustomer(Integer customerId) {
		// 1.根据customerId来查询customer对象
		Customer customer = customerDao.findCustomerById(customerId);

		// 2.根据customer对象来查询订单
		return orderDao.findByCustomer(customer);

	}

	@Override
	public PageBean<Order> findOrderByCustomerPage(Integer customerId, int pageNum, int currentCount) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setCurrentCount(currentCount); // 封装每一页条数
		pageBean.setPageNum(pageNum);// 封装当前页码
		int totalCount = orderDao.findTotalCount();
		totalCount=1000;
		pageBean.setTotalCount(totalCount);

		// 总页数
		int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);

		pageBean.setTotalPage(totalPage);
		
		//查询当前页的所有信息
		Customer c=customerDao.findCustomerById(customerId);
		List<Order> orders=orderDao.findOrderByCustomerPage(c,pageNum,currentCount);
		pageBean.setCurrentContent(orders);

		return pageBean;
	}

}
