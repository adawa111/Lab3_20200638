package com.example.lab3.controller;

import com.example.lab3.entity.Doctor;
import com.example.lab3.entity.Hospital;
import com.example.lab3.entity.Paciente;
import com.example.lab3.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/Hospital")
public class HospitalController {
    @Autowired
    HospitalRepository hospitalRepository;

    @GetMapping("/listar")
    public String listarHospital(Model model) {
        List<Hospital> hospitalList = hospitalRepository.findAll();
        model.addAttribute("hospitalList", hospitalList);
        return "vistaHospital";
    }

    @PostMapping("/doctor")
    public String buscarDoctorPorHospital(@RequestParam("id") String id,
                                               Model model) {

        List<Doctor> listaDoctor = hospitalRepository.buscarDoctorPorHospital(id);
        Hospital hospital = hospitalRepository.buscarHospitalPorId(id);
        model.addAttribute("listaDoctor", listaDoctor);
        model.addAttribute("hospitalbuscado",hospital.getId());

        return "vistaHospitalDoctor";
    }

    @PostMapping("/paciente")
    public String buscarPacientePorHospital(@RequestParam("id") String id,
                                               Model model) {

        List<Paciente> listaPaciente = hospitalRepository.buscarPacientePorHospital(id);
        Hospital hospital = hospitalRepository.buscarHospitalPorId(id);
        model.addAttribute("listaPaciente", listaPaciente);
        model.addAttribute("hospitalbuscado",hospital.getId());

        return "vistaHospitalPaciente";
    }


}