package com.example.controller;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Appointment;
import com.example.model.AppointmentPhysio;
import com.example.model.Patient;
import com.example.model.Physio;
import com.example.repository.PhysioRepository;
import com.example.service.AppointmentPhysioService;
import com.example.service.AppointmentService;
import com.example.service.PatientService;
import com.example.service.PhysioService;

@Controller
@RequestMapping("/physio")
public class PhysioController {
	
	@Autowired
	private PhysioRepository physioRepository;
	
	@Autowired
	private PhysioService physioService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private AppointmentPhysioService appointmentPhysioService;
	
	@Autowired
	private PatientService patientService;
	
	@ModelAttribute
	private void physioDetails(Model m, Principal p) {
		String email = p.getName();
		Physio physio = physioRepository.findByEmail(email);
		m.addAttribute("physio", physio);
	}

	@GetMapping("/home")
	public String viewPhysioHome() {
		return "physio/physio_home";
	}
	
	@GetMapping("/profile")
	public String doctorProfile() {
		return "physio/profile";
	}
	
	@GetMapping("/showPhysioFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value = "id") int id, Model model) {
		Physio physio = physioService.getPhysioById(id);
		model.addAttribute("physio", physio);
		return "physio/update_physio_detail";
	}
	
	@PostMapping("/savePhysio")
	public String saveDoctor(@ModelAttribute("physio") Physio physio) {
		physioService.savePhysio(physio);
		return "physio/profile";
	}
	
	//Show Appointments for therapist
	@GetMapping("/appointments")
    public String viewDoctorAppointments(Model model, Authentication authentication, Principal p) {
		String email = p.getName();
		Physio physio = physioRepository.findByEmail(email);
		System.out.println("physio check "+ physio);
        List<AppointmentPhysio> appointments = appointmentPhysioService.findByPhysio(physio);
        model.addAttribute("appointments", appointments);
        return "/physio/physio-appointments";
    }
	
	
	@GetMapping("/appointments/delete/{id}")
	public String deleteAppointment(@PathVariable("id") int id) {
		AppointmentPhysio appointment = appointmentPhysioService.findById(id);
		appointmentPhysioService.delete(appointment);
		return "redirect:/physio/appointments";
	}
	
	@GetMapping("/reschedule/appointments/{id}")
	public String showRescheduleAppointmentForm(@PathVariable("id") int id, Model model) {
	    AppointmentPhysio appointment = appointmentPhysioService.findAppointmentById(id);
	    model.addAttribute("appointment", appointment);
	    return "physio/reschedule-appointment";
	}
	
	@PostMapping("/appointments/{id}/reschedule")
	public String processRescheduleAppointmentForm(@PathVariable("id") int id,
	        @RequestParam("date") Date date,
	        @RequestParam("time") String time, 
	        @RequestParam("disease") String disease,
			@RequestParam("description") String description){
	    AppointmentPhysio appointmentPhysio = appointmentPhysioService.findAppointmentById(id);
	    appointmentPhysioService.rescheduleAppointment(appointmentPhysio, date, time,disease, description);
	    return "redirect:/physio/appointments";
	}
	
	//Show Patient Detail
		@GetMapping("/showPatientDetail/{id}")
		public String showPatientCompleteDetail(@PathVariable (value = "id") int id, Model model) {
			Patient patient = patientService.getPatientById(id);
			model.addAttribute("patient", patient);
			return "physio/display_patient_detail";
		}
		
		
		//Confirm Appointment
	    @GetMapping("/appointments/confirm/{id}")
		public String confirmAppointmentForm(@PathVariable("id") int id, Model model) {
		    Appointment appointment = appointmentService.findAppointmentById(id);
		    model.addAttribute("appointment", appointment);
		    return "physio/confirm-appointment";
		}
		
			
		@PostMapping("/appointments/{id}/confirm")
		public String processConfirmAppointmentForm(@PathVariable("id") int id,
			HttpSession session){
		    Appointment appointment = appointmentService.findAppointmentById(id);
		  	session.setAttribute("msg", "Appointment Confirmed Successfully!!");
		    return "redirect:/physio/appointments";
		}
}
