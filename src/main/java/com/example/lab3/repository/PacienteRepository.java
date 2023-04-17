package com.example.lab3.repository;
import  java.util.Date;
import com.example.lab3.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Integer> {

    //@Query(nativeQuery = true, value = "select * from paciente where hospital_id =?1")
    //List<Paciente> findPacienteByHospital_id(int id);

    @Query(nativeQuery = true, value = "select * from paciente where hospital_id =?1")
    List<Paciente> buscarPacientePorHospitalId(int id);

    @Query(nativeQuery = true, value = "select * from paciente where doctor_id =?1 and fecha_cita > CURRENT_DATE")
    List<Paciente> buscarPacientePorDoctorIdFecha(int id);

    @Query(nativeQuery = true, value = "select * from paciente where doctor_id =?1")
    List<Paciente> buscarPacientePorDoctorId(int id);
}
