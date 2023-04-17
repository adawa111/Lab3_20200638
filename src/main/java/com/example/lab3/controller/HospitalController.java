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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/Hospital")
public class HospitalController {
    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping(value = {"", "/", "/listar"})
    public String listarHospital(Model model) {
        List<Hospital> hospitalList = hospitalRepository.findAll();
        model.addAttribute("hospitalList", hospitalList);
        return "vistaHospital";
    }

    @GetMapping("/doctor")
    public String buscarDoctorPorHospital(@RequestParam("id") String id,
                                               Model model) {

        int i = Integer.parseInt(id);
        List<Doctor> listaDoctor = doctorRepository.buscarDoctorPorHospitalId(i);
        Hospital hospital = hospitalRepository.findHospitalById(i);
        model.addAttribute("listaDoctor", listaDoctor);
        model.addAttribute("hospitalbuscado",hospital);
        for (Doctor d : listaDoctor){
            System.out.printf(d.getNombre());
            System.out.println(d.getId());
            System.out.println(d.getEspecialidad());
            System.out.println(d.getHospitalId());
        }


        return "vistaHospitalDoctores";
    }

    @GetMapping("/paciente")
    public String buscarPacientePorHospital(@RequestParam("id") String id,
                                               Model model) {

        int is = Integer.parseInt(id);
        List<Paciente> listaPaciente = pacienteRepository.buscarPacientePorHospitalId(is);
        Hospital hospital = hospitalRepository.findHospitalById(is);
        for (Paciente p: listaPaciente){
            p.setNombreDoctor(doctorRepository.findDoctorById(p.getDoctor_id()).getNombre());
            p.setNombreHospital(hospitalRepository.findHospitalById(p.getHospital_id()).getNombre());
        }
        model.addAttribute("listaPaciente", listaPaciente);
        model.addAttribute("hospitalbuscado",hospital);

        return "vistaHospitalPaciente";
    }


}
