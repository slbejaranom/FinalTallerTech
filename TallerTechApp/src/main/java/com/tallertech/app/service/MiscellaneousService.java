package com.tallertech.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.tallertech.app.entity.Status;

@Service
public class MiscellaneousService {

	@Autowired
	private Environment env;
	
	public Status getStatistics(){
		String source = env.getProperty("spring.datasource.url");
		String password = env.getProperty("spring.datasource.password");
		String user = env.getProperty("spring.datasource.username");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(source,user,password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM LLAMADA");
			Status status = new Status();
			while(rs.next()) {
				status.setLlamadasHechas(Integer.parseInt(rs.getString(1)));
			}
			rs = st.executeQuery("SELECT COUNT(*) FROM LLAMADA WHERE FUE_REPORTADA='1'");
			while(rs.next()) {
				status.setLlamadasReportadas(Integer.parseInt(rs.getString(1)));
			}
			rs = st.executeQuery("SELECT COUNT(*) FROM CLIENTE");
			while(rs.next()) {
				status.setClientesRegistrados(Integer.parseInt(rs.getString(1)));
			}
			return status;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
