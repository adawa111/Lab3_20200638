package com.example.lab3.repository;
import  java.util.Date;

import com.example.lab3.entity.Hospital;
import com.example.lab3.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Integer> {

    //@Query(nativeQuery = true, value = "select * from paciente where hospital_id =?1")
    //List<Paciente> findPacienteByHospital_id(int id);

    @Query(nativeQuery = true,value = "select * from paciente where id =?1")
    Paciente findPacienteById(int id);

    @Query(nativeQuery = true, value = "select * from paciente where hospital_id =?1")
    List<Paciente> buscarPacientePorHospitalId(int id);

    @Query(nativeQuery = true, value = "select * from paciente where doctor_id =?1 and fecha_cita > CURRENT_DATE")
    List<Paciente> buscarPacientePorDoctorIdFecha(int id);

    @Query(nativeQuery = true, value = "select * from paciente where doctor_id =?1")
    List<Paciente> buscarPacientePorDoctorId(int id);

    @Modifying
    @Query(value = "UPDATE paciente set numero_habitacion =?1 WHERE id =?2", nativeQuery = true)
    void  actualizarHabitacion(int hab, int id);

    @Modifying
    @Query(value = "UPDATE paciente set doctor_id =?1 WHERE id =?2", nativeQuery = true)
    void  actualizarDoctor(int doc, int id);


}
