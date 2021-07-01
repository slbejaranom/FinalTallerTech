package com.tallertech.app.repository;

import org.springframework.stereotype.Repository;

import com.tallertech.app.entity.EmpresaTelefoniaFija;

import org.springframework.data.jpa.repository.*;


@Repository
public interface EmpresaTelefoniaFijaRepository extends JpaRepository<EmpresaTelefoniaFija,String>{

}
