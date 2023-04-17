package com.example.lab3.repository;

import com.example.lab3.entity.Doctor;
import com.example.lab3.entity.Hospital;
import com.example.lab3.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Integer> {

    @Query(nativeQuery = true,value = "select * from hospital where id =?1")
    Hospital findHospitalById(int id);


}
