package com.example.lab3.controller;

import com.example.lab3.entity.Doctor;
import com.example.lab3.entity.Hospital;
import com.example.lab3.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/Paciente")
public class PacienteController {


    @Autowired
    HospitalRepository hospitalRepository;

    @GetMapping("")
    public String listarHospital(Model model) {
        List<Hospital> hospitalList = hospitalRepository.findAll();
        model.addAttribute("hospitalList", hospitalList);
        return "vistaHospital";
    }

    @GetMapping("/doctor")
    public String buscarDoctorPorHospital(@RequestParam("id") String id,
                                          Model model) {

        int iii = Integer.parseInt(id);
        //List<Doctor> listaDoctor = hospitalRepository.buscarDoctorPorHospital(iii);
        //Hospital hospital = hospitalRepository.buscarHospitalPorId(iii);
        //model.addAttribute("listaDoctor", listaDoctor);
        //model.addAttribute("hospitalbuscado",hospital.getId());

        return "vistaHospitalDoctor";
}
}
