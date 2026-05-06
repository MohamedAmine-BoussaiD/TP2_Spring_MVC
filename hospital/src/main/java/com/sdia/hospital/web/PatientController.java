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
                        @RequestParam(name="size" ,defaultValue = "5") int size ,
                        @RequestParam(name="keyword" ,defaultValue = "")  String keyword) {

        Page<Patient> pagePatient = patientRepository.findByNomContainsIgnoreCaseOrPrenomContainsIgnoreCase(keyword, keyword, PageRequest.of(page,size));
        model.addAttribute("patientList", pagePatient.getContent());
        model.addAttribute("pages", new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword" , keyword);
        return "patients";
    }

   @GetMapping("/deletePatient")
    public String delete(
            @RequestParam(name="id") Long id ,
            @RequestParam(name="keyword")  String keyword , int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
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

