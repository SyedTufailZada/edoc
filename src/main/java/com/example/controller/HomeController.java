package com.example.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Doctor;
import com.example.model.Nurse;
import com.example.model.Patient;
import com.example.model.Physio;
import com.example.repository.DoctorRepository;
import com.example.repository.NurseRepository;
import com.example.repository.PatientRepository;
import com.example.repository.PhysioRepository;
import com.example.service.DoctorService;
import com.example.service.NurseService;
import com.example.service.PatientService;
import com.example.service.PhysioService;

@Controller
public class HomeController {
	@Autowired
	private PatientService patientService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private NurseService nurseService;

	@Autowired
	private PhysioService physioService;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private NurseRepository nurseRepository;

	@Autowired
	private PhysioRepository physioRepository;

	@ModelAttribute
	private void patientDetails(Model m, Principal p) {
		if (p != null) {
			String email = p.getName();
			Patient patient = patientRepository.findByEmail(email);
			m.addAttribute("patient", patient);
		}
	}

	@ModelAttribute
	private void doctorDetails(Model m, Principal p) {
		if (p != null) {
			String email = p.getName();
			Doctor doctor = doctorRepository.findByEmail(email);
			m.addAttribute("doctor", doctor);
		}
	}

	@ModelAttribute
	private void nurseDetails(Model m, Principal p) {
		if (p != null) {
			String email = p.getName();
			Nurse nurse = nurseRepository.findByEmail(email);
			m.addAttribute("nurse", nurse);
		}
	}

	@ModelAttribute
	private void physioDetails(Model m, Principal p) {
		if (p != null) {
			String email = p.getName();
			Physio physio = physioRepository.findByEmail(email);
			m.addAttribute("physio", physio);
		}
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@GetMapping("/registerPatient")
	public String registerPatient() {
		return "register_patient";
	}

	@GetMapping("/registerDoctor")
	public String registerDoctor() {
		return "register_doctor";
	}

	@GetMapping("/registerNurse")
	public String registerNurse() {
		return "register_nurse";
	}

	@GetMapping("/registerPhysio")
	public String registerPhysio() {
		return "register_physio";
	}

	@PostMapping("/createPatient")
	public String createPatient(@ModelAttribute Patient patient, @RequestParam MultipartFile img, HttpSession session) {
		System.out.println(patient);
		System.out.println(img.getOriginalFilename());
		try {
			boolean f = patientService.checkEmail(patient.getEmail());

			if (f) {
				System.out.println("email already exist");
			} else {
				// processing and uploading image
				if (img.isEmpty()) {
					System.out.println("Image is empty");
				} else {
					System.out.println("Image is uploding");
					patient.setImageName(img.getOriginalFilename());

					File saveFile = new ClassPathResource("static/img").getFile();

					Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + img.getOriginalFilename());
					Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
					System.out.println("Image is uploded");
				}

				Patient patient2 = patientService.createPatient(patient);
				if (patient2 != null) {
					System.out.println("Register Successfully");
					session.setAttribute("msg", "Register Successfully");
				} else {
					System.out.println("Something wrong on server");
					session.setAttribute("msg", "Something wrong on server");
				}
			}
		} catch (Exception e) {
		}
		return "redirect:/registerPatient";
	}

	@PostMapping("/createDoctor")
	public String createDoctor(@ModelAttribute Doctor doctor, @RequestParam MultipartFile img, HttpSession session) {
		System.out.println("check error " + doctor);
		System.out.println(img.getOriginalFilename());
		try {
			boolean f = doctorService.checkEmail(doctor.getEmail());

			if (f) {
				System.out.println("email already exist");
			} else {
				// processing and uploading image
				if (img.isEmpty()) {
					System.out.println("Image is empty");
				} else {
					System.out.println("Image is uploding");
					doctor.setImageName(img.getOriginalFilename());

					File saveFile = new ClassPathResource("static/img").getFile();

					Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + img.getOriginalFilename());
					Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
					System.out.println("Image is uploded");
				}
				Doctor doctor2 = doctorService.createDoctor(doctor);
				if (doctor2 != null) {
					System.out.println("Register Successfully");
					session.setAttribute("msg", "Register Successfully");
				} else {
					System.out.println("Something wrong on server");
					session.setAttribute("msg", "Something wrong on server");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR" + e.getMessage());
		}
		return "redirect:/registerDoctor";
	}

	@PostMapping("/createNurse")
	public String createNurse(@ModelAttribute Nurse nurse, @RequestParam MultipartFile img, HttpSession session) {
		System.out.println(nurse);
		System.out.println(img.getOriginalFilename());
		try {
			boolean f = nurseService.checkEmail(nurse.getEmail());
			if (f) {
				System.out.println("email already exist");
			} else {
				// processing and uploading image
				if (img.isEmpty()) {
					System.out.println("Image is empty");
				} else {
					System.out.println("Image is uploding");
					nurse.setImageName(img.getOriginalFilename());

					File saveFile = new ClassPathResource("static/img").getFile();

					Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + img.getOriginalFilename());
					Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
					System.out.println("Image is uploded");
				}

				Nurse nurse2 = nurseService.createNurse(nurse);
				if (nurse2 != null) {
					System.out.println("Register Successfully");
					session.setAttribute("msg", "Register Successfully");
				} else {
					System.out.println("Something wrong on server");
					session.setAttribute("msg", "Something wrong on server");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR" + e.getMessage());
		}
		return "redirect:/registerNurse";
	}

	@PostMapping("/createPhysio")
	public String createPhysio(@ModelAttribute Physio physio, @RequestParam MultipartFile img, HttpSession session) {
		System.out.println(physio);
		System.out.println(img.getOriginalFilename());
		try {
			boolean f = physioService.checkEmail(physio.getEmail());
			if (f) {
				System.out.println("email already exist");
			} else {
				// processing and uploading image
				if (img.isEmpty()) {
					System.out.println("Image is empty");
				} else {
					System.out.println("Image is uploding");
					physio.setImageName(img.getOriginalFilename());

					File saveFile = new ClassPathResource("static/img").getFile();

					Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + img.getOriginalFilename());
					Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
					System.out.println("Image is uploded");
				}
				Physio physio2 = physioService.createPhysio(physio);
				if (physio2 != null) {
					System.out.println("Register Successfully");
					session.setAttribute("msg", "Register Successfully");
				} else {
					System.out.println("Something wrong on server");
					session.setAttribute("msg", "Something wrong on server");
				}
			}
		} catch (Exception e) {

		}
		return "redirect:/registerPhysio";
	}
}
