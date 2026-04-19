package com.sdia.hospital.web;

import com.sdia.hospital.entities.Patient;
import com.sdia.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(Model model){
        List<Patient> patientList = patientRepository.findAll();
        model.addAttribute("patientList",patientList);
        return "patients"; // return  " model + vue "
    }

    @GetMapping("/deletePatient")
    public String delete( @RequestParam(name = "id") Long idPatient){
        patientRepository.deleteById(idPatient);
        return "redirect:/index";
    }
}
