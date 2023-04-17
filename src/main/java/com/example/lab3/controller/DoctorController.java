package com.example.lab3.controller;


import com.example.lab3.entity.Doctor;
import com.example.lab3.entity.Hospital;
import com.example.lab3.entity.Paciente;
import com.example.lab3.repository.DoctorRepository;
import com.example.lab3.repository.HospitalRepository;
import com.example.lab3.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/Doctor")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping(value = {"","/","/listar"})
    public String listaDoctores(Model model) {
        List<Doctor> doctorList = doctorRepository.findAll();
        for (Doctor d : doctorList){
            d.setNombreHospital(hospitalRepository.findHospitalById(d.getHospitalId()).getNombre());
        }
        model.addAttribute("doctorList", doctorList);
        return "vistaDoctores";


    }
    @GetMapping("/paciente")
    public String searchPacientePorDoctorId(@RequestParam("id") String id,
                                            Model model) {

        int is = Integer.parseInt(id);
        List<Paciente> listaPaciente = pacienteRepository.buscarPacientePorDoctorId(is);
        Doctor doctor = doctorRepository.findDoctorById(is);
        for (Paciente p: listaPaciente){
            p.setNombreDoctor(doctorRepository.findDoctorById(p.getDoctor_id()).getNombre());
            p.setNombreHospital(hospitalRepository.findHospitalById(p.getHospital_id()).getNombre());
        }
        model.addAttribute("listaPaciente", listaPaciente);
        model.addAttribute("doctorbuscado",doctor);

        return "vistaDoctorPaciente";
    }

    @GetMapping("/citas")
    public String searchPacientePorDoctorIdFecha(@RequestParam("id") String id,
                                            Model model) {

        int iss = Integer.parseInt(id);
        List<Paciente> listaPaciente = pacienteRepository.buscarPacientePorDoctorIdFecha(iss);
        Doctor doctor1 = doctorRepository.findDoctorById(iss);
        for (Paciente p: listaPaciente){
            p.setNombreDoctor(doctorRepository.findDoctorById(p.getDoctor_id()).getNombre());
            p.setNombreHospital(hospitalRepository.findHospitalById(p.getHospital_id()).getNombre());
        }
        model.addAttribute("listaPaciente", listaPaciente);
        model.addAttribute("doctorbuscado",doctor1);

        return "vistaDoctorCita";
    }
}
