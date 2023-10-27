package com.example.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Doctor;
import com.example.model.Nurse;
import com.example.model.Patient;
import com.example.model.Physio;
import com.example.repository.DoctorRepository;
import com.example.repository.NurseRepository;
import com.example.repository.PatientRepository;
import com.example.repository.PhysioRepository;
import com.example.service.EmailService;


@Controller
public class FotgotController {

	
	@Autowired
	private EmailService emailService;
		
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private NurseRepository nurseRepository;
	
	@Autowired
	private PhysioRepository physioRepository;
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
	Random random = new Random(1000);

	private Object bCryptPasswordEncoder;
	
	//email id form open handler
	@GetMapping("/forgot-password")
	public String openEmailForm() {
		return "forgot_email";
	}
	
//	@GetMapping("/forgot-doctor")
//	public String openEmailFormDoc() {
//		return "forgot_email_form_doctor";
//	}
//	
//	@GetMapping("/forgot-nurse")
//	public String openEmailFormNur() {
//		return "forgot_email_form_nurse";
//	}
//	
//	@GetMapping("/forgot-physio")
//	public String openEmailFormPhy() {
//		return "forgot_email_form_physio";
//	}
	
	@PostMapping("/send-otp")
	public String sendOTPPatientEmail(@RequestParam("email") String email, HttpSession session) {
		
		System.out.println("Email id is :" + email);
	
		//generating otp for 4 digits
		int otp = random.nextInt(999999);
		System.out.println("OTP "+ otp);
		
		//Write code for send OTP to email...
		String subject = "OTP From E-Doc";
		String message = "<div style='border:2px solid rgb(210, 210, 210); padding:20px; background-color:rgb(240, 240, 240)'>"
				+"<h3>"
				+"Your OTP is"
				+"<br>"
				+"" +otp
				+"</h3>"
				+"</div>"; 
		String to = email;
		
		boolean flag = this.emailService.sendEmail(subject, message, to);
		
		if(flag) {
			
			session.setAttribute("myotp", otp);
			session.setAttribute("email", email);
			
			return "verify_otp_patient";
			
		} else {
			
			session.setAttribute("message", "Check your email id !!");
			return "forgot_email";
		}
		
	}
	
//	@PostMapping("/send-otp-doctor-email")
//	public String sendOTPDoctorEmail(@RequestParam("emailDoc") String emailDoc, HttpSession session) {
//		
//		System.out.println("Email id is :" + emailDoc);
//		
//		//generating otp for 4 digits
//		int otp = random.nextInt(8888888);
//		System.out.println("OTP "+ otp);
//		
//		
//		//Write code for send OTP to email...
//		String subject = "OTP From E-Doc";
//		String message = "<div style='border:2px solid rgb(210, 210, 210); padding:20px; background-color:rgb(240, 240, 240)'>"
//				+"<h3>"
//				+"Your OTP is"
//				+"<br>"
//				+"" +otp
//				+"</h3>"
//				+"</div>"; 
//		String to = emailDoc;
//		
//		boolean flag = this.emailService.sendEmail(subject, message, to);
//		
//		if(flag) {
//			
//			session.setAttribute("myotp", otp);
//			session.setAttribute("email", emailDoc);
//			
//			return "verify_otp_doctor";
//			
//		} else {
//			
//			session.setAttribute("message", "Check your email id !!");
//			return "forgot_email_form_doctor";
//		}
//		
//	}
//	
//	@PostMapping("/send-otp-nurse-email")
//	public String sendOTPNurseEmail(@RequestParam("emailNur") String emailNur, HttpSession session) {
//		
//		System.out.println("Email id is :" + emailNur);
//		
//		//generating otp for 4 digits
//		int otp = random.nextInt(7777777);
//		System.out.println("OTP "+ otp);
//		
//		
//		//Write code for send OTP to email...
//		String subject = "OTP From E-Doc";
//		String message = "<div style='border:2px solid rgb(210, 210, 210); padding:20px; background-color:rgb(240, 240, 240)'>"
//				+"<h3>"
//				+"Your OTP is"
//				+"<br>"
//				+"" +otp
//				+"</h3>"
//				+"</div>"; 
//		String to = emailNur;
//		
//		boolean flag = this.emailService.sendEmail(subject, message, to);
//		
//		if(flag) {
//			
//			session.setAttribute("myotp", otp);
//			session.setAttribute("email", emailNur);
//			
//			return "verify_otp_nurse";
//			
//		} else {
//			
//			session.setAttribute("message", "Check your email id !!");
//			return "forgot_email_form_nurse";
//		}
//		
//	}
//	
//	@PostMapping("/send-otp-physio-email")
//	public String sendOTPPhysioEmail(@RequestParam("emailPhy") String emailPhy, HttpSession session) {
//		
//		System.out.println("Email id is :" + emailPhy);
//		
//		//generating otp for 4 digits
//		int otp = random.nextInt(5755757);
//		System.out.println("OTP "+ otp);
//		
//		
//		//Write code for send OTP to email...
//		String subject = "OTP From E-Doc";
//		String message = "<div style='border:2px solid rgb(210, 210, 210); padding:20px; background-color:rgb(240, 240, 240)'>"
//				+"<h3>"
//				+"Your OTP is"
//				+"<br>"
//				+"" +otp
//				+"</h3>"
//				+"</div>"; 
//		String to = emailPhy;
//		
//		boolean flag = this.emailService.sendEmail(subject, message, to);
//		
//		if(flag) {
//			
//			session.setAttribute("myotp", otp);
//			session.setAttribute("email", emailPhy);
//			
//			return "verify_otp_physio";
//			
//		} else {
//			
//			session.setAttribute("message", "Check your email id !!");
//			return "forgot_email_form_physio";
//		}
//		
//	}
	

	
	
	//verify-otp
	@PostMapping("/verify-otp-patient")
	private String veriftyOtpPatient(@RequestParam("otpPat") int otpPat, HttpSession session) {
		
		int myOtp = (int) session.getAttribute("myotp");
		String email = (String) session.getAttribute("email");
		
		if(myOtp == otpPat) {
			//Password change form
			System.out.println("Otp is correct for patient");
			Patient patientDetail = this.patientRepository.findByEmail(email);
			
			if(patientDetail == null) {
				//Send error message
				session.setAttribute("message", "User does not exist with this email!");
				return "forgot_email_form_patient";
				
			} else {
				//Send change password form
				
			}
			
			return "password_change_form_patient";
		} else {
			
			session.setAttribute("message", "You have entered wrong OTP");
			return "verify_otp_patient";
		}
		
	}
	
	//verify-otp
	@PostMapping("/verify-otp-doctor")
	private String veriftyOtpDoctor(@RequestParam("otpDoc") int otpDoc, HttpSession session) {
		
		int myOtp = (int) session.getAttribute("myotp");
		String email = (String) session.getAttribute("email");
		
		if(myOtp == otpDoc) {
			//Password change form
			System.out.println("Otp is correct for doctor");
			Doctor doctorDetail = this.doctorRepository.findByEmail(email);
			
			if(doctorDetail == null) {
				//Send error message
				session.setAttribute("message", "User does not exist with this email!");
				return "forgot_email_form_doctor";
				
			} else {
				//Send change password form
				
			}
			
			return "password_change_form_doctor";
		} else {
			
			session.setAttribute("message", "You have entered wrong OTP");
			return "verify_otp_doctor";
		}
		
	}
	
	//verify-otp
	@PostMapping("/verify-otp-nurse")
	private String veriftyOtpNurse(@RequestParam("otpNur") int otpNur, HttpSession session) {
		
		int myOtp = (int) session.getAttribute("myotp");
		String email = (String) session.getAttribute("email");
		
		if(myOtp == otpNur) {
			//Password change form
			System.out.println("Otp is correct for nurse");
			Nurse nurseDetail = this.nurseRepository.findByEmail(email);
			
			if(nurseDetail == null) {
				//Send error message
				session.setAttribute("message", "User does not exist with this email!");
				return "forgot_email_form_nurse";
				
			} else {
				//Send change password form
				
			}
			
			return "password_change_form_nurse";
		} else {
			
			session.setAttribute("message", "You have entered wrong OTP");
			return "verify_otp_nurse";
		}
		
	}


	//verify-otp
	@PostMapping("/verify-otp-physio")
	private String veriftyOtpPhysio(@RequestParam("otpPhy") int otpPhy, HttpSession session) {
		
		int myOtp = (int) session.getAttribute("myotp");
		String email = (String) session.getAttribute("email");
		
		if(myOtp == otpPhy) {
			//Password change form
			System.out.println("Otp is correct for physio");
			Physio physioDetail = this.physioRepository.findByEmail(email);
			
			if(physioDetail == null) {
				//Send error message
				session.setAttribute("message", "User does not exist with this email!");
				return "forgot_email_form_physio";
				
			} else {
				//Send change password form
				
			}
			
			return "password_change_form_physio";
		} else {
			
			session.setAttribute("message", "You have entered wrong OTP");
			return "verify_otp_physio";
		}
		
	}
	
	
	
	
	
	//Change Password
	@PostMapping("/change-password-patient")
	private String changePasswordPatient(@RequestParam("newpassword") String newpassword, 
			HttpSession session) {
		
		String email = (String) session.getAttribute("email");
		Patient patientDetail = this.patientRepository.findByEmail(email);
		patientDetail.setPassword(bCryptPasswordEncoder().encode(newpassword));
		this.patientRepository.save(patientDetail);
		session.setAttribute("changePass", "Your Passwrod is changed successfully.");
		return "redirect:/login";
	}
	
	@PostMapping("/change-password-doctor")
	private String changePasswordDoctor(@RequestParam("newpassword") String newpassword, 
			HttpSession session) {
		
		String email = (String) session.getAttribute("email");
		Doctor doctorDetail = this.doctorRepository.findByEmail(email);
		doctorDetail.setPassword(((BCryptPasswordEncoder) this.bCryptPasswordEncoder).encode(newpassword));
		this.doctorRepository.save(doctorDetail);
		session.setAttribute("changePass", "Your Passwrod is changed successfully.");
		return "redirect:/doctor/login";
	}

	@PostMapping("/change-password-nurse")
	private String changePasswordNurse(@RequestParam("newpassword") String newpassword, 
			HttpSession session) {
		
		String email = (String) session.getAttribute("email");
		Nurse nurseDetail = this.nurseRepository.findByEmail(email);
		nurseDetail.setPassword(((BCryptPasswordEncoder) this.bCryptPasswordEncoder).encode(newpassword));
		this.nurseRepository.save(nurseDetail);
		session.setAttribute("changePass", "Your Passwrod is changed successfully.");
		return "redirect:/nurse/login";
	}
	
	@PostMapping("/change-password-physio")
	private String changePasswordPhysio(@RequestParam("newpassword") String newpassword, 
			HttpSession session) {
		
		String email = (String) session.getAttribute("email");
		Physio physioDetail = this.physioRepository.findByEmail(email);
		physioDetail.setPassword(((BCryptPasswordEncoder) this.bCryptPasswordEncoder).encode(newpassword));
		this.physioRepository.save(physioDetail);
		session.setAttribute("changePass", "Your Passwrod is changed successfully.");
		return "redirect:/physio/login";
	}
	
}
