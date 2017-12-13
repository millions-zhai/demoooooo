package cn.itheima.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itheima.domain.Customer;
import cn.itheima.service.ICustomerService;

@Controller
@Scope("prototype")
@Namespace("/customer")
@ParentPackage("struts-default")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	private Customer customer = new Customer();

	@Override
	public Customer getModel() {
		return customer;
	}

	@Autowired
	private ICustomerService customerService;

	// 删除客户操作
	@Action(value = "delCustomer", results = {
			@Result(name = "success", location = "findAllCustomer", type = "redirectAction") })
	public String delCustomer() {

		// 得到customerid
		// ServletActionContext.getRequest().getParameter("id");
		customerService.delCustomer(customer);
		
		return SUCCESS;

	}

	// 查询所有客户信息
	@Action(value = "findAllCustomer", results = { @Result(name = "success", location = "/customerList-page.jsp") })
	public String findAllCustomer() {
		// 调用service完成查询所有客户信息操作
		List<Customer> cs = customerService.findAllCustomer();

		// 将cs存储到valueStack中--手动
		ActionContext.getContext().getValueStack().set("cs", cs);

		return SUCCESS;
	}

	
}
