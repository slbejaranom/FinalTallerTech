package com.tallertech.app.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.tallertech.app.entity.EmpresaTelefoniaFija;
import com.tallertech.app.entity.Llamada;
import com.tallertech.app.entity.Status;
import com.tallertech.app.repository.EmpresaTelefoniaFijaRepository;

@Service
public class EmpresaTelefoniaFijaService {

	@Autowired
	EmpresaTelefoniaFijaRepository empresaTelefoniaFijaRepository;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	private Environment env;
	
	public List<EmpresaTelefoniaFija> findAllEmpresas(){
		return empresaTelefoniaFijaRepository.findAll();
	}
	
	public Optional<EmpresaTelefoniaFija> findEmpresaByNit(String nit){
		Optional<EmpresaTelefoniaFija> empresaTelefoniaFija = empresaTelefoniaFijaRepository.findById(nit);
		return empresaTelefoniaFija;
	}
	
	public void saveEmpresa(EmpresaTelefoniaFija empresaTelefoniaFija) throws Exception{
		if(empresaTelefoniaFija != null) {
			empresaTelefoniaFijaRepository.save(empresaTelefoniaFija);
		}
		else {
			throw new Exception("Debe haber un objeto empresa");
		}
	}
	
	public void deleteEmpresa(String nit) throws Exception{
		if(empresaTelefoniaFijaRepository.findById(nit).isPresent()) {
			Optional<EmpresaTelefoniaFija> empresaParaBorrar = empresaTelefoniaFijaRepository.findById(nit);
			empresaParaBorrar.get().setEsta_activo('0');
		}
		else {
			throw new Exception("No se encontró empresa con ese NIT");
		}
	}
	
	public EmpresaTelefoniaFija updateEmpresa(EmpresaTelefoniaFija empresaTelefoniaFija) throws Exception {
		String nit = empresaTelefoniaFija.getNit();
		
		if(empresaTelefoniaFijaRepository.findById(nit).isPresent()) {
			empresaTelefoniaFijaRepository.save(empresaTelefoniaFija);
			return empresaTelefoniaFijaRepository.findById(nit).get();
		}
		else {
			throw new Exception("No se encontró empresa con ese NIT");
		}
	}
	
	public int getNumberOfEmpresas() {
		String source = env.getProperty("spring.datasource.url");
		String password = env.getProperty("spring.datasource.password");
		String user = env.getProperty("spring.datasource.username");
		int cantidad = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(source,user,password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM EMPRESA_TELEFONIA_FIJA");
			
			while(rs.next()) {
				cantidad = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cantidad;
	}
	
	public List<Llamada> llamadasSinReportarParaActualizar(String nit){
		String source = env.getProperty("spring.datasource.url");
		String password = env.getProperty("spring.datasource.password");
		String user = env.getProperty("spring.datasource.username");
		ArrayList<Llamada> llamadas = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(source,user,password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM LLAMADA WHERE TELEFONO IN (SELECT TELEFONO FROM CLIENTE_EMPRESA WHERE NIT = '"+nit+"') AND FUE_REPORTADA='0'");
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd"); 
			while(rs.next()) {
				System.out.println(rs.getString(5));
				llamadas.add(new Llamada(Long.parseLong(rs.getString(1)),Long.parseLong(rs.getString(2)), rs.getString(3), rs.getString(4).charAt(0), formato.parse(rs.getString(5).split(" ")[0]), clienteService.findCliente(rs.getString(6)).get()));
			}			
			CallableStatement stmt = con.prepareCall("call PRO_REPORTAR_LLAMADAS_EMPRESA(?)");
			stmt.setString(1, nit);
			stmt.execute();
			return llamadas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
