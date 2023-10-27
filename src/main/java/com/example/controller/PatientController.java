package com.example.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Appointment;
import com.example.model.AppointmentNurse;
import com.example.model.AppointmentPhysio;
import com.example.model.Doctor;
import com.example.model.Nurse;
import com.example.model.Patient;
import com.example.model.PatientRecord;
import com.example.model.Physio;
import com.example.repository.PatientRepository;
import com.example.service.AppointmentNurseService;
import com.example.service.AppointmentPhysioService;
import com.example.service.AppointmentService;
import com.example.service.DoctorService;
import com.example.service.NurseService;
import com.example.service.PatientService;
import com.example.service.PhysioService;
import com.example.service.RecordService;

@Controller
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private RecordService recordService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PhysioService physioService;

	@Autowired
	private NurseService nurseService;

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private AppointmentNurseService appointmentNurseService;

	@Autowired
	private AppointmentPhysioService appointmentPhysioService;

	@ModelAttribute
	private void patientDetails(Model m, Principal p) {
		String email = p.getName();
		Patient patient = patientRepository.findByEmail(email);

		m.addAttribute("patient", patient);

	}

	@GetMapping("/home")
	public String viewPatientHome() {
		return "patient/patient_home";
	}

	@GetMapping("/profile")
	public String patientProfile() {
		return "patient/profile";
	}

	@GetMapping("doctor/list")
	public String doctosList(Model model) {
		model.addAttribute("listDoctor", patientService.getAllDoctorDetails());
		return "patient/doctor_list";
	}

	@GetMapping("physiotherapist/list")
	public String physioList(Model model) {
		model.addAttribute("listPhysio", patientService.getAllPhysioDetails());
		return "patient/physio_list";
	}

	@GetMapping("nurse/list")
	public String nurseList(Model model) {
		model.addAttribute("listNurse", patientService.getAllNurseDetails());
		return "patient/nurse_list";
	}

	@GetMapping("/showDoctorForm/{id}")
	public String showDoctorCompleteDetail(@PathVariable(value = "id") int id, Model model) {
		Doctor doctor = doctorService.getDoctorById(id);
		model.addAttribute("doctor", doctor);
		return "patient/display_doctor_detail";
	}

	@GetMapping("/showPhysioForm/{id}")
	public String showPhysioCompleteDetail(@PathVariable(value = "id") int id, Model model) {
		Physio physio = physioService.getPhysioById(id);
		model.addAttribute("physio", physio);
		return "patient/display_physio_detail";
	}

	@GetMapping("/showNurseForm/{id}")
	public String showNurseCompleteDetail(@PathVariable(value = "id") int id, Model model) {
		Nurse nurse = nurseService.getNurseById(id);
		model.addAttribute("nurse", nurse);
		return "patient/display_nurse_detail";
	}

	// patient Appointments with doctors
	@GetMapping("/appointments")
	public String viewAppointments(Model model, Principal p) {
		String email = p.getName();
		Patient patient = patientRepository.findByEmail(email);
		System.out.println("patient check " + patient);
		List<Appointment> appointments = appointmentService.findByPatient(patient);
		model.addAttribute("appointments", appointments);
		return "/patient/appointments-patient";
	}

	@GetMapping("/appointments/delete/{id}")
	public String deleteAppointment(@PathVariable("id") int id) {
		Appointment appointment = appointmentService.findById(id);
		appointmentService.delete(appointment);
		return "redirect:/patient/appointments";
	}

	@GetMapping("/update/appointment/{id}")
	public String updateAppointmentForm(@PathVariable("id") int id, Model model) {
		Appointment appointment = appointmentService.findAppointmentById(id);
		model.addAttribute("appointment", appointment);
		return "patient/appointment-update";
	}

	@PostMapping("/appointment/{id}/update")
	public String processUpdateAppointmentForm(@PathVariable("id") int id, @RequestParam("date") Date date,
			@RequestParam("time") String time, @RequestParam("description") String description, @RequestParam("description") String confirmed) {
		Appointment appointment = appointmentService.findAppointmentById(id);
		appointmentService.rescheduleAppointment(appointment, date, time, description);
		return "redirect:/patient/appointments";
	}

	// Patient appointment with Physio
	@GetMapping("/appointments/physio")
	public String viewAppointmentPhysio(Model model, Principal p) {
		String email = p.getName();
		Patient patient = patientRepository.findByEmail(email);
		System.out.println("patient check " + patient);
		List<AppointmentPhysio> appointments = appointmentPhysioService.findByPatient(patient);
		model.addAttribute("appointments", appointments);
		return "/patient/appointments-physio-patient";
	}

	@GetMapping("/appointments/physio/delete/{id}")
	public String deleteAppointmentPhysio(@PathVariable("id") int id) {
		AppointmentPhysio appointmentPhysio = appointmentPhysioService.findById(id);
		appointmentPhysioService.delete(appointmentPhysio);
		return "redirect:/patient/appointments/physio";
	}

	@GetMapping("/update/appointment/physio/{id}")
	public String updateAppointmentFormPhysio(@PathVariable("id") int id, Model model) {
		AppointmentPhysio appointment = appointmentPhysioService.findAppointmentById(id);
		model.addAttribute("appointment", appointment);
		return "patient/appointment-physio-update";
	}

	@PostMapping("/appointment/{id}/update/physio")
	public String processUpdateAppointmentPhysioForm(@PathVariable("id") int id, @RequestParam("date") Date date,
			@RequestParam("time") String time, @RequestParam("disease") String disease,
			@RequestParam("description") String description) {
		AppointmentPhysio appointmentPhysio = appointmentPhysioService.findAppointmentById(id);
		appointmentPhysioService.rescheduleAppointment(appointmentPhysio, date, time, disease, description);
		return "redirect:/patient/appointments/physio";
	}

	// Patient appointment with Nurse
	@GetMapping("/appointments/nurse")
	public String viewAppointmentNurse(Model model, Principal p) {
		String email = p.getName();
		Patient patient = patientRepository.findByEmail(email);
		System.out.println("patient check " + patient);
		List<AppointmentNurse> appointments = appointmentNurseService.findByPatient(patient);
		model.addAttribute("appointments", appointments);
		return "/patient/appointments-nurse-patient";
	}

	@GetMapping("/appointment/nurse/delete/{id}")
	public String deleteAppointmentNurse(@PathVariable("id") int id) {
		AppointmentNurse appointmentNurse = appointmentNurseService.findById(id);
		appointmentNurseService.delete(appointmentNurse);
		return "redirect:/patient/appointments/nurse";
	}

	@GetMapping("/update/appointment/nurse/{id}")
	public String updateAppointmentFormNurse(@PathVariable("id") int id, Model model) {
		AppointmentNurse appointment = appointmentNurseService.findAppointmentById(id);
		model.addAttribute("appointment", appointment);
		return "patient/appointment-nurse-update";
	}

	@PostMapping("/appointment/{id}/update/nurse")
	public String processUpdateAppointmentNurseForm(@PathVariable("id") int id, @RequestParam("date") Date date,
			@RequestParam("time") String time, @RequestParam("medicalConditions") String medicalConditions,
			@RequestParam("description") String description) {
		AppointmentNurse appointmentNurse = appointmentNurseService.findAppointmentById(id);
		appointmentNurseService.rescheduleAppointment(appointmentNurse, date, time, medicalConditions, description);
		return "redirect:/patient/appointments/nurse";
	}

	// Open Record Form handler
	@GetMapping("/add-record")
	public String viewRecordForm(Model model) {

		model.addAttribute("title", "Add Medical Record");
		model.addAttribute("record", new PatientRecord());

		return "/patient/record_form";
	}

	@PostMapping("/process-record")
	public String processRecord(@ModelAttribute PatientRecord patientRecord, 
			@RequestParam("profileImage") MultipartFile file,
			Principal principal, HttpSession session){

		try {
			
			String email = principal.getName();
			Patient patient = this.patientRepository.findByEmail(email);
			
			//image processing
			if(file.isEmpty()) {
				
				System.out.println("Image is empty");
				
			}else {
				patientRecord.setRecordImg(file.getOriginalFilename());
				File saveFile = new ClassPathResource("static/img").getFile();

				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Image is uploded");
				
			}
			
						
			patient.getRecords().add(patientRecord);
			patientRecord.setPatient(patient);
			this.patientRepository.save(patient);
			
			session.setAttribute("msg", "Your record is stored Successfully");
			System.err.println("Patient record stored successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR" + e.getMessage());
			session.setAttribute("msg", "Some went wrong !! Please Try Again");
		}

		return "/patient/record_form";
	}
	
	@GetMapping("/show-record")
	public String showRecordByPatientId(Model model, Principal p) {
		String email = p.getName();
		Patient patient = patientRepository.findByEmail(email);
		System.out.println("patient check " + patient);
		List<PatientRecord> patientRecords = recordService.findByPatient(patient);
		model.addAttribute("patientRecords", patientRecords);		
		return "/patient/record_show";
	}
	
	@GetMapping("/show-record/delete/{id}")
	public String deleteRecord(@PathVariable("id") int id) {
		PatientRecord patientRecord = recordService.findById(id);
		recordService.delete(patientRecord);
		return "redirect:/patient/show-record";
	}
	
	
//	view health tips
	
	
	@GetMapping("/view/health/tips")
	public String OpenViewHealthTipsPage() {
		return "/patient/view_health_tips";
	}
	
	

}
