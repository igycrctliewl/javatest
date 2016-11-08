package com.concretepage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.concretepage.bean.Company;
import com.concretepage.dao.CompanyDao;
public class SpringTest {
	public static void main(String[] args) {
		ApplicationContext  context = new ClassPathXmlApplicationContext("spring.xml");
		CompanyDao companyDao = (CompanyDao)context.getBean("companyDao");
		Company comp = companyDao.getCompany(1);
		System.out.println(comp.getLocation()+" "+comp.getName()+" "+comp.getNoOfEmp());
	}
}   