package com.sdia.hospital.web;

import com.sdia.hospital.entities.Patient;
import com.sdia.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(Model model ,
                        @RequestParam(name = "page" , defaultValue = "0") int page ,
                        @RequestParam(name="size" ,defaultValue = "5") int size) {
        Page<Patient> pagePatient = patientRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("patientList", pagePatient.getContent());
        model.addAttribute("pages", new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return "patients";
    }

   @GetMapping("/deletePatient")
    public String delete(@RequestParam(name="id") Long id){
        patientRepository.deleteById(id);
        return "redirect:/index";
   }
//
//   @GetMapping("/score")
//   public String score(Model model ,
//                       @RequestParam(name="page" , defaultValue = "0") int page ,
//                       @RequestParam(name="size" , defaultValue = "5") int size
//   ){
//        Page<Patient> patientScore = patientRepository.findAllByOrderByScoreAsc(PageRequest.of(page, size));
//        model.addAttribute("patientList",patientScore.getContent());
//        return "patients";
//
//   }


   }

