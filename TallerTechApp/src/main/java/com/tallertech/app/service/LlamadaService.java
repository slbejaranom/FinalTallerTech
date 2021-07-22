package com.tallertech.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.tallertech.app.entity.Llamada;
import com.tallertech.app.repository.LlamadaRepository;

@Service
public class LlamadaService {

	@Autowired
	LlamadaRepository llamadaRepository;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	private Environment env;
	
	public List<Llamada> getAllLlamadas(){
		return llamadaRepository.findAll();
	}
	
	public Optional<Llamada> getLlamadaById(long id){
		return llamadaRepository.findById(id);
	}
	
	public Llamada updateLlamada(Llamada llamada) throws Exception{
		if(llamadaRepository.findById(llamada.getIdLlamada()).isPresent()) {
			llamadaRepository.save(llamada);
			return llamadaRepository.findById(llamada.getIdLlamada()).get();
		}else {
			throw new Exception("No hay una llamada con este ID");
		}
	}
	
	public void saveLlamada(Llamada llamada) throws Exception{
		if(llamada != null) {
			llamadaRepository.save(llamada);
		}else {
			throw new Exception("El oobjeto llamada no puede ser nulo");
		}
	}
	
	public List<Llamada> obtenerLlamadasPorConvenio(String nit) {
		String source = env.getProperty("spring.datasource.url");
		String password = env.getProperty("spring.datasource.password");
		String user = env.getProperty("spring.datasource.username");
		ArrayList<Llamada> llamadas = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(source,user,password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM LLAMADA WHERE TELEFONO IN (SELECT TELEFONO FROM CLIENTE_EMPRESA WHERE NIT = '"+nit+"')");
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd"); 
			while(rs.next()) {
				System.out.println(rs.getString(5));
				llamadas.add(new Llamada(Long.parseLong(rs.getString(1)),Long.parseLong(rs.getString(2)), rs.getString(3), rs.getString(4).charAt(0), formato.parse(rs.getString(5).split(" ")[0]), clienteService.findCliente(rs.getString(6)).get()));
			}
			return llamadas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
