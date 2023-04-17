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


    @GetMapping("/guardar1")
    public String actualizarPac1(@RequestParam("id") String id,
                                          @RequestParam("numero_habitacion") String hab,
                                          Model model) {

        int iii = Integer.parseInt(id);
        int i2 = Integer.parseInt(hab);
        pacienteRepository.actualizarHabitacion(i2,iii);
        List<Paciente> listaPaciente = pacienteRepository.findAll();
        for (Paciente p : listaPaciente){
            p.setNombreHospital(hospitalRepository.findHospitalById(p.getHospital_id()).getNombre());
            p.setNombreDoctor(doctorRepository.findDoctorById(p.getDoctor_id()).getNombre());
        }
        model.addAttribute("listaPaciente", listaPaciente);

        return "vistaPaciente";
    }

    @GetMapping("/guardar2")
    public String actualizarPac2(@RequestParam("id") String id,
                                 @RequestParam("doctor") String doc,
                                  Model model) {

        int i3 = Integer.parseInt(id);
        int i4 = Integer.parseInt(doc);
        pacienteRepository.actualizarDoctor(i4,i3);
        List<Paciente> listaPaciente = pacienteRepository.findAll();
        for (Paciente p : listaPaciente){
            p.setNombreHospital(hospitalRepository.findHospitalById(p.getHospital_id()).getNombre());
            p.setNombreDoctor(doctorRepository.findDoctorById(p.getDoctor_id()).getNombre());
        }
        model.addAttribute("listaPaciente", listaPaciente);

        return "vistaPaciente";
    }

    @GetMapping("/derivar")
    public String derivarPaciente(@RequestParam("id") String id,
                                          Model model) {

        int iii = Integer.parseInt(id);

        Optional<Paciente> optPaciente = Optional.ofNullable(pacienteRepository.findPacienteById(iii));

        if (optPaciente.isPresent()) {
            Paciente paciente = optPaciente.get();
            model.addAttribute("paciente", paciente);
            List<Doctor> doctorList = doctorRepository.findAll();
            for (Doctor d : doctorList){
                d.setNombreHospital(hospitalRepository.findHospitalById(d.getHospitalId()).getNombre());
            }
            model.addAttribute("doctorList", doctorList);
            return "vistaDerivarPaciente";
        } else {
            return "redirect:/Paciente";
        }

    }
}
