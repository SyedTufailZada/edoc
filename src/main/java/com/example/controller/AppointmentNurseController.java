package com.example.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.AppointmentNurse;
import com.example.model.Patient;
import com.example.repository.PatientRepository;
import com.example.service.AppointmentNurseService;
import com.example.service.NurseService;
import com.example.service.PatientService;


@Controller
public class AppointmentNurseController {
	@Autowired
	private PatientRepository patientRepository;
	
	
	private final AppointmentNurseService appointmentNurseService;
	private final NurseService nurseService;
	//private final PatientService patientService;
	
	public AppointmentNurseController(AppointmentNurseService appointmentNurseService, NurseService nurseService, PatientService patientService) {
	    this.appointmentNurseService = appointmentNurseService;
	    this.nurseService = nurseService;
	   // this.patientService = patientService;
	}
	
	@ModelAttribute
	private void patientDetails(Model m, Principal p) {
		String email = p.getName();
		Patient patient = patientRepository.findByEmail(email);
		m.addAttribute("patient", patient);
				
		System.out.println("patient is "+ patient);
	}
	
	@GetMapping("/appointment/nurse/new/{id}")
	public String showAddAppointmentForm(@PathVariable (value = "id") int id,Model model, Principal p) {
	    model.addAttribute("appointmentNurse", new AppointmentNurse());
	    model.addAttribute("nurses", nurseService.getNurseById(id));
	    return "/patient/appointment-nurse-add";
	}
	
	@PostMapping("/appointment/nurse/save")
	public String addAppointment(@ModelAttribute("appointmentNurse") AppointmentNurse appointmentNurse, HttpSession session) {
		System.out.println(appointmentNurse);

		AppointmentNurse appointment2 = appointmentNurseService.saveAppointment(appointmentNurse);
		if (appointment2 != null) {
			System.out.println("Appointment Booked Successfully");
			session.setAttribute("msg", "Your appointment is booked successfully");
		} else {
			System.out.println("Something wrong on server");
			session.setAttribute("msg", "Something wrong on server");
		}

		System.out.println("final check appointment is booked or not");
		return "/patient/appointment-nurse-add";
	}
}