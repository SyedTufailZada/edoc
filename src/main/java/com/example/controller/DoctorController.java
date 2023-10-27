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
import com.example.model.Doctor;
import com.example.model.Patient;
import com.example.repository.DoctorRepository;
import com.example.service.AppointmentService;
import com.example.service.DoctorService;
import com.example.service.PatientService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorRepository doctorRepository;
		
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	
	
	@ModelAttribute
	private void doctorDetails(Model m, Principal p) {
		String email = p.getName();
		Doctor doctor = doctorRepository.findByEmail(email);
		m.addAttribute("doctor", doctor);
	}
			
	@GetMapping("/home")
	public String viewDoctorHome() {
		return "doctor/doctor_home";
	}
	
	@GetMapping("/profile")
	public String doctorProfile() {
		return "doctor/profile";
	}
	
	@GetMapping("/showDoctorFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value = "id") int id, Model model) {
		Doctor doctor = doctorService.getDoctorById(id);
		model.addAttribute("doctor", doctor);
		return "doctor/update_doctor_detail";
	}
	
	@PostMapping("/saveDoctor")
	public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {
		doctorService.saveDoctor(doctor);
		return "doctor/profile";
	}
	
	//Show Appointments for doctors
	@GetMapping("/appointments")
    public String viewDoctorAppointments(Model model, Authentication authentication, Principal p) {
		String email = p.getName();
		Doctor doctor = doctorRepository.findByEmail(email);
		System.out.println("doctor check "+ doctor);
        List<Appointment> appointments = appointmentService.findByDoctor(doctor);
        model.addAttribute("appointments", appointments);
        return "/doctor/doctor-appointments";
    }
	
	@GetMapping("/appointments/delete/{id}")
	public String deleteAppointment(@PathVariable("id") int id) {
		Appointment appointment = appointmentService.findById(id);
		appointmentService.delete(appointment);
		return "redirect:/doctor/appointments";
	}
	
	@GetMapping("/reschedule/appointments/{id}")
	public String showRescheduleAppointmentForm(@PathVariable("id") int id, Model model) {
	    Appointment appointment = appointmentService.findAppointmentById(id);
	    model.addAttribute("appointment", appointment);
	    return "doctor/reschedule-appointment";
	}
	
	@PostMapping("/appointments/{id}/reschedule")
	public String processRescheduleAppointmentForm(@PathVariable("id") int id,
	        @RequestParam("date") Date date,
	        @RequestParam("time") String time, 
			@RequestParam("description") String description){
	    Appointment appointment = appointmentService.findAppointmentById(id);
	    appointmentService.rescheduleAppointment(appointment, date, time, description);
	    return "redirect:/doctor/appointments";
	}
	
	
	//Confirm Appointment
    @GetMapping("/appointments/confirm/{id}")
	public String confirmAppointmentForm(@PathVariable("id") int id, Model model) {
	    Appointment appointment = appointmentService.findAppointmentById(id);
	    model.addAttribute("appointment", appointment);
	    return "doctor/confirm-appointment";
	}
	
		
	@PostMapping("/appointments/{id}/confirm")
	public String processConfirmAppointmentForm(@PathVariable("id") int id,
		HttpSession session){
	    Appointment appointment = appointmentService.findAppointmentById(id);
	  	session.setAttribute("msg", "Appointment Confirmed Successfully!!");
	    return "redirect:/doctor/appointments";
	}
	
	//Show Patient Detail
	@GetMapping("/showPatientDetail/{id}")
	public String showPatientCompleteDetail(@PathVariable (value = "id") int id, Model model) {
		Patient patient = patientService.getPatientById(id);
		model.addAttribute("patient", patient);
		return "doctor/display_patient_detail";
	}
	
	
	

}
