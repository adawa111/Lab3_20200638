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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Paciente")
public class PacienteController {


    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping(value = {"","/","/listar"})
    public String listaPacientes(Model model) {
        List<Paciente> listaPaciente = pacienteRepository.findAll();
        for (Paciente p : listaPaciente){
            p.setNombreHospital(hospitalRepository.findHospitalById(p.getHospital_id()).getNombre());
            p.setNombreDoctor(doctorRepository.findDoctorById(p.getDoctor_id()).getNombre());
        }
        model.addAttribute("listaPaciente", listaPaciente);
        return "vistaPaciente";


    }

    @GetMapping("/editar")
    public String editarPaciente(Model model, @RequestParam("id") String id) {
        int iii = Integer.parseInt(id);

        Optional<Paciente> optPaciente = Optional.ofNullable(pacienteRepository.findPacienteById(iii));

        if (optPaciente.isPresent()) {
            Paciente paciente = optPaciente.get();
            model.addAttribute("paciente", paciente);
            return "vistaEditarPaciente";
        } else {
            return "redirect:/Paciente";
        }
    }




    @PostMapping("/guardar1")
    public String actualizarPac1(@RequestParam("id") int id,
                                 @RequestParam("numero_habitacion") int numeroHabitacion) {

        System.out.println("actualizado");
        pacienteRepository.actualizarHabitacion(numeroHabitacion, id);
        System.out.println("actaulizado");

        return "redirect:/Paciente/listar";
    }


    @PostMapping("/guardar2")
    public String actualizarPac2(@RequestParam("id") int id,
                                 @RequestParam("doctor") int doc) {

        System.out.println("actualizado");
        pacienteRepository.actualizarDoctor(doc,id);
        System.out.println("actualizado");

        return "redirect:/Paciente/listar";
    }

    @GetMapping("/derivar")
    public String derivarPaciente(@RequestParam("id") String id,
                                          Model model) {

        int iii = Integer.parseInt(id);

        Optional<Paciente> optPaciente = Optional.ofNullable(pacienteRepository.findPacienteById(iii));

        if (optPaciente.isPresent()) {
            Paciente paciente = optPaciente.get();
            paciente.setNombreDoctor(doctorRepository.findDoctorById(paciente.getDoctor_id()).getNombre());
            model.addAttribute("paciente", paciente);
            List<Doctor> doctorList = doctorRepository.findAll();
            model.addAttribute("doctorList", doctorList);
            return "vistaDerivarPaciente";
        } else {
            return "redirect:/Paciente";
        }

    }
}
