package cn.itheima.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.itheima.domain.Order;
import cn.itheima.domain.PageBean;
import cn.itheima.service.IOrderService;

@Controller
@Scope("prototype")
@Namespace("/order")
@ParentPackage("struts-default")
public class OrderAction {

	@Autowired
	private IOrderService orderService;

	@Action("findOrder")
	public void findOrder() {
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		// 1.得到customerId
		Integer customerId = Integer.parseInt((ServletActionContext.getRequest().getParameter("customerId")));

		// 2.得到当前页码
		int pageNum = Integer.parseInt((ServletActionContext.getRequest().getParameter("pageNum")));
		// 3.每页显示条数
		int currentCount = Integer.parseInt((ServletActionContext.getRequest().getParameter("currentCount")));

		// 2.查询客户对应的订单
		// List<Order> orders = orderService.findOrderByCustomer(customerId);

		PageBean<Order> pageBean = orderService.findOrderByCustomerPage(customerId, pageNum, currentCount);

		// 3.将orders转换成json
		// 对json数据进行过滤，并且取消循环引用
		PropertyFilter filter = new PropertyFilter() {

			@Override
			public boolean apply(Object arg0, String fieldName, Object arg2) {
				if ("cusPhone".equalsIgnoreCase(fieldName)) {
					return false;
				}
				if ("id".equalsIgnoreCase(fieldName)) {
					return false;
				}
				if ("orders".equalsIgnoreCase(fieldName)) {
					return false;
				}

				return true;
			}
		};
		String json = JSONArray.toJSONString(pageBean, filter, SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(json);
		// 4.将json响应到浏览器
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
