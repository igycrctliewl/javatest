package com.concretepage.dao;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.concretepage.bean.Company;

@Repository
public class CompanyDao {
	private SimpleJdbcCall simpleJdbcCall;
    @Autowired
    public void setDataSource(DataSource dataSource) {
       this.simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("read_company");
    }
    
    public Company getCompany(int id){
    	SqlParameterSource in = new MapSqlParameterSource()
        .addValue("in_id", id); 
		Map<String,Object> out = simpleJdbcCall.execute(in);
		Company comp = new Company();
		comp.setName((String)out.get("out_name"));
		comp.setLocation((String)out.get("out_location"));
		comp.setNoOfEmp(Integer.parseInt(String.valueOf(out.get("out_no_emp"))));
		return comp;
    }
}



