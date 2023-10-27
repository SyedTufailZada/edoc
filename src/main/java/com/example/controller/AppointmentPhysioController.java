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

import com.example.model.AppointmentPhysio;
import com.example.model.Patient;
import com.example.repository.PatientRepository;
import com.example.service.AppointmentPhysioService;
import com.example.service.PatientService;
import com.example.service.PhysioService;


@Controller
public class AppointmentPhysioController {
	@Autowired
	private PatientRepository patientRepository;
	
	
	private final AppointmentPhysioService appointmentPhysioService;
	private final PhysioService physioService;
	
	public AppointmentPhysioController(AppointmentPhysioService appointmentPhysioService, PhysioService physioService, PatientService patientService) {
	    this.appointmentPhysioService = appointmentPhysioService;
	    this.physioService = physioService;
	}
	
	@ModelAttribute
	private void patientDetails(Model m, Principal p) {
		String email = p.getName();
		Patient patient = patientRepository.findByEmail(email);
		m.addAttribute("patient", patient);
				
		System.out.println("patient is "+ patient);
	}
	
	@GetMapping("/appointment/physio/new/{id}")
	public String showAddAppointmentForm(@PathVariable (value = "id") int id,Model model, Principal p) {
	    model.addAttribute("appointmentPhysio", new AppointmentPhysio());
	    model.addAttribute("physios", physioService.getPhysioById(id));
	    return "/patient/appointment-physio-add";
	}
	
	@PostMapping("/appointment/physio/save")
	public String addAppointment(@ModelAttribute("appointmentPhysio") AppointmentPhysio appointmentPhysio, HttpSession session) {
		System.out.println(appointmentPhysio);

		AppointmentPhysio appointment2 = appointmentPhysioService.saveAppointment(appointmentPhysio);
		if (appointment2 != null) {
			System.out.println("Appointment Booked Successfully");
			session.setAttribute("msg", "Your appointment is booked successfully");
		} else {
			System.out.println("Something wrong on server");
			session.setAttribute("msg", "Something wrong on server");
		}

		System.out.println("final check appointment is booked or not");
		return "/patient/appointment-physio-add";
	}
}