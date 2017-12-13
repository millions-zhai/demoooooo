package cn.itheima.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.itheima.domain.Customer;
import cn.itheima.service.ICustomerService;

@Controller
@Scope("prototype")
@Namespace("/customer")
@ParentPackage("struts-default")
public class CustomerMultiAction extends ActionSupport {

	@Autowired
	private ICustomerService customerService;

	private File cusImg; // 用于接收上传的文件
	private String cusImgFileName; // 用来接收上传文件名称
	private String cusName;
	private String cusPhone;

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public File getCusImg() {
		return cusImg;
	}

	public void setCusImg(File cusImg) {
		this.cusImg = cusImg;
	}

	public String getCusImgFileName() {
		return cusImgFileName;
	}

	public void setCusImgFileName(String cusImgFileName) {
		this.cusImgFileName = cusImgFileName;
	}

	// 添加客户信息
	@Action(value = "addCustomer", results = {
			@Result(name = "success", location = "findAllCustomer", type = "redirectAction"),
			@Result(name = "input", location = "/error.jsp") })
	public String addCustomer() {

		// 处理上传文件，保存到服务器端    /ssh-exam/upload
		String realPath = ServletActionContext.getServletContext().getRealPath("/upload");

		File destFile = new File(realPath, cusImgFileName);
		try {
			FileUtils.copyFile(cusImg, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 完成添加客户操作
		Customer customer = new Customer();
		customer.setCusName(cusName);
		customer.setCusPhone(cusPhone);
		String contextPath = ServletActionContext.getRequest().getContextPath();
		String cusImgsrc = contextPath + "/upload/" + cusImgFileName; // http://localhost:8080 /ssh-exam/upload/1.jpg
		//  /ssh-exam/upload/xxx.jpg
		customer.setCusImgsrc(cusImgsrc);

		customerService.addCustomer(customer);

		return SUCCESS;

	}
}
