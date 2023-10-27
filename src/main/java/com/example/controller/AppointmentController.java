package com.example.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Appointment;
import com.example.model.Patient;
import com.example.repository.AppointmentRepository;
import com.example.repository.PatientRepository;
import com.example.service.AppointmentService;
import com.example.service.DoctorService;
import com.example.service.PatientService;


@Controller
public class AppointmentController {
	@Autowired
	private PatientRepository patientRepository;
	
	//private DoctorRepository doctorRepository;

	
	
	private final AppointmentService appointmentService;
	private final DoctorService doctorService;
	//private final PatientService patientService;
	
	public AppointmentController(AppointmentService appointmentService, DoctorService doctorService, PatientService patientService) {
	    this.appointmentService = appointmentService;
	    this.doctorService = doctorService;
	   // this.patientService = patientService;
	}
	
	@ModelAttribute
	private void patientDetails(Model m, Principal p) {
		String email = p.getName();
		Patient patient = patientRepository.findByEmail(email);
		m.addAttribute("patient", patient);
				
		System.out.println("patient is "+ patient);
	}
	
	@GetMapping("/appointments/new/{id}")
	public String showAddAppointmentForm(@PathVariable (value = "id") int id,Model model, Principal p) {
	    model.addAttribute("appointment", new Appointment());
	    model.addAttribute("doctors", doctorService.getDoctorById(id));
	    return "/patient/appointment-add";
	}
	
	@PostMapping("/appointments/save")
	public String addAppointment(@ModelAttribute("appointment") Appointment appointment, HttpSession session) {
		System.out.println(appointment);

		Appointment appointment2 = appointmentService.saveAppointment(appointment);
		if (appointment2 != null) {
			System.out.println("Appointment Booked Successfully");
			session.setAttribute("msg", "Your appointment is booked successfully");
		} else {
			System.out.println("Something wrong on server");
			session.setAttribute("msg", "Something wrong on server");
		}

		System.out.println("final check appointment is booked or not");
		return "/patient/appointment-add";
	
	}
	
	

	
	
	
	
}