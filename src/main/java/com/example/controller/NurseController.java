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
import com.example.model.AppointmentNurse;
import com.example.model.Nurse;
import com.example.model.Patient;
import com.example.repository.NurseRepository;
import com.example.service.AppointmentNurseService;
import com.example.service.AppointmentService;
import com.example.service.NurseService;
import com.example.service.PatientService;


@Controller
@RequestMapping("/nurse")
public class NurseController {
	
	@Autowired
	private NurseRepository nurseRepository;
	
	@Autowired
	private NurseService nurseService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private AppointmentNurseService appointmentNurseService;
	
	@Autowired
	private PatientService patientService;
	
	
	@ModelAttribute
	private void nurseDetails(Model m, Principal p) {
		String email = p.getName();
		Nurse nurse = nurseRepository.findByEmail(email);
		m.addAttribute("nurse", nurse);
	}

	@GetMapping("/home")
	public String viewNurseHome() {
		return "nurse/nurse_home";
	}
	
	@GetMapping("/profile")
	public String nurseProfile() {
		return "nurse/profile";
	}
	
	@GetMapping("/showNurseFormForUpdate/{id}")
	public String showNurseFormForUpdate(@PathVariable (value = "id") int id, Model model) {
		Nurse nurse = nurseService.getNurseById(id);
		model.addAttribute("nurse", nurse);
		return "nurse/update_nurse_detail";
	}
	
	@PostMapping("/saveNurse")
	public String saveDoctor(@ModelAttribute("nurse") Nurse nurse) {
		nurseService.saveNurse(nurse);
		return "nurse/profile";
	}

	//Show Appointments for Nurses
	@GetMapping("/appointments")
    public String viewNurseAppointments(Model model, Authentication authentication, Principal p) {
		String email = p.getName();
		Nurse nurse = nurseRepository.findByEmail(email);
		System.out.println("nurse check "+ nurse);
        List<AppointmentNurse> appointments = appointmentNurseService.findByNurse(nurse);
        model.addAttribute("appointments", appointments);
        return "nurse/nurse-appointments";
    }
		
	@GetMapping("/appointments/delete/{id}")
	public String deleteAppointment(@PathVariable("id") int id) {
		AppointmentNurse appointment = appointmentNurseService.findById(id);
		appointmentNurseService.delete(appointment);
		return "redirect:/nurse/appointments";
	}
	
	@GetMapping("/reschedule/appointments/{id}")
	public String showRescheduleAppointmentForm(@PathVariable("id") int id, Model model) {
	    AppointmentNurse appointment = appointmentNurseService.findAppointmentById(id);
	    model.addAttribute("appointment", appointment);
	    return "nurse/reschedule-appointment";
	}
	
	@PostMapping("/appointments/{id}/reschedule")
	public String processRescheduleAppointmentForm(@PathVariable("id") int id,
	        @RequestParam("date") Date date,
	        @RequestParam("time") String time,
	        @RequestParam("medicalConditions") String medicalConditions,
			@RequestParam("description") String description){
	    AppointmentNurse appointmentNurse = appointmentNurseService.findAppointmentById(id);
	    appointmentNurseService.rescheduleAppointment(appointmentNurse, date, time, medicalConditions, description);
	    return "redirect:/nurse/appointments";
	}

	
	//Show Patient Detail
		@GetMapping("/showPatientDetail/{id}")
		public String showPatientCompleteDetail(@PathVariable (value = "id") int id, Model model) {
			Patient patient = patientService.getPatientById(id);
			model.addAttribute("patient", patient);
			return "nurse/display_patient_detail";
		}
	
		//Confirm Appointment
	    @GetMapping("/appointments/confirm/{id}")
		public String confirmAppointmentForm(@PathVariable("id") int id, Model model) {
		    Appointment appointment = appointmentService.findAppointmentById(id);
		    model.addAttribute("appointment", appointment);
		    return "nurse/confirm-appointment";
		}
		
			
		@PostMapping("/appointments/{id}/confirm")
		public String processConfirmAppointmentForm(@PathVariable("id") int id,
			HttpSession session){
		    Appointment appointment = appointmentService.findAppointmentById(id);
		  	session.setAttribute("msg", "Appointment Confirmed Successfully!!");
		    return "redirect:/nurse/appointments";
		}
		
		
		
}
