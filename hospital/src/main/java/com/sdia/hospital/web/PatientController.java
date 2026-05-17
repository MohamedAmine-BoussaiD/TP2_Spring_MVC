package com.sdia.hospital.web;

import com.sdia.hospital.entities.Patient;
import com.sdia.hospital.repository.PatientRepository;
//import jakarta.validation.Valid;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(Model model ,
                        @RequestParam(name = "page" , defaultValue = "0") int page ,
                        @RequestParam(name="size" ,defaultValue = "5") int size ,
                        @RequestParam(name="keyword" ,defaultValue = "")  String keyword) {

        Page<Patient> pagePatient = patientRepository.findByNomContainsIgnoreCaseOrPrenomContainsIgnoreCase(keyword, keyword, PageRequest.of(page,size));
        model.addAttribute("patientList", pagePatient.getContent());
        model.addAttribute("pages", new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword" , keyword);
        return "patients";
    }

   @GetMapping("/admin/deletePatient")
    public String delete(
            @RequestParam(name="id") Long id ,
            @RequestParam(name="keyword")  String keyword , int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
   }

   @GetMapping("/admin/formPatients")
    public String formPatient(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatients";
   }

   @PostMapping("/admin/savePatients")
    public String savePatient(Model model, @Valid Patient patient , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "formPatients";
        }
        patientRepository.save(patient);
        return "redirect:/index";
   }

   @GetMapping("/admin/editPatient")
    public String editPatient(Model model  , Long id){
        Patient patient = patientRepository.findById(id).orElse(null);
        model.addAttribute("patient",patient);
        return "editPatients";
   }

   @GetMapping("/")
public String home(){
        return "redirect:/index";
   }






   }

