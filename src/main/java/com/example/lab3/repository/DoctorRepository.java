package com.example.lab3.repository;

import com.example.lab3.entity.Doctor;
import com.example.lab3.entity.Hospital;
import com.example.lab3.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    //@Query(nativeQuery = true,value = "select * from doctor where hospital_id =?1")
    //List<Doctor> findDoctorByHospitalId(int id);

    @Query(nativeQuery = true,value = "select * from doctor where hospital_id =?1")
    List<Doctor> buscarDoctorPorHospitalId(int id);

    @Query(nativeQuery = true,value = "select * from doctor where id =?1")
    Doctor findDoctorById(int id);

}
