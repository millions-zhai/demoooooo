package cn.itheima.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.itheima.dao.IOrderDAO;
import cn.itheima.domain.Customer;
import cn.itheima.domain.Order;

@Repository
public class OrderDAOImpl extends HibernateDaoSupport implements IOrderDAO {

	@Autowired
	public void setSupperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public List<Order> findByCustomer(Customer customer) {
		return (List<Order>) this.getHibernateTemplate().find("from Order o where o.customer=?", customer);
	}

	// 查询所有的订单，根据客户
	@Override
	public List<Order> findOrderByCustomerPage(Customer c, int pageNum, int currentCount) {
		DetachedCriteria dc = DetachedCriteria.forClass(Order.class);
		dc.add(Restrictions.eq("customer", c));
		return (List<Order>) this.getHibernateTemplate().findByCriteria(dc, (pageNum - 1) * currentCount, currentCount);
	}

	// 获取总条数
	@Override
	public int findTotalCount() {
		return ((Long) this.getHibernateTemplate().find("select count(*) from Order").iterator().next()).intValue();
	}

}
