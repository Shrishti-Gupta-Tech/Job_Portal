package com.jobportal.demo.job_portal.service;

import com.jobportal.demo.job_portal.dto.LoginDTO;
import com.jobportal.demo.job_portal.dto.ResponseDTO;
import com.jobportal.demo.job_portal.dto.UserDTO;
import com.jobportal.demo.job_portal.entity.OTP;
import com.jobportal.demo.job_portal.entity.User;
import com.jobportal.demo.job_portal.exception.JobPortalException;
import com.jobportal.demo.job_portal.repository.OTPRepository;
import com.jobportal.demo.job_portal.repository.UserRepository;
import com.jobportal.demo.job_portal.utility.Data;
import com.jobportal.demo.job_portal.utility.Utility;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Utility utility;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private ProfileService profileService;


    @Override
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException {
        Optional<User> optional=userRepository.findByEmail(userDTO.getEmail());
        if(optional.isPresent()) throw new JobPortalException("USER_FOUND");
        if(userDTO.getProfileID()==null) userDTO.setProfileID(profileService.createProfile(userDTO.getEmail()));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userDTO.toEntity();
        userRepository.save(user);
        return user.toDTO();
    }
    @Override
    public UserDTO createUser(UserDTO userDTO) throws JobPortalException {
        Optional<User> optional = userRepository.findByEmail(userDTO.getEmail());

        if (optional.isEmpty()) {
            throw new JobPortalException("USER_NOT_FOUND");
        }

        User user = optional.get();

        user.setUserName(userDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setRole(userDTO.getRole());
        user.setJobTitle(userDTO.getJobTitle());
        user.setCompany(userDTO.getCompany());
        user.setCompanyLocation(userDTO.getCompanyLocation());
        user.setAbout(userDTO.getAbout());
        user.setSkills(userDTO.getSkills());
        user.setCertificates(userDTO.getCertificates());
        user.setExperiences(userDTO.getExperiences());

        // Don't change ID or profileID if not provided
        if (userDTO.getProfileID() != null) {
            user.setProfileID(userDTO.getProfileID());
        }

        userRepository.save(user);

        return user.toDTO();
    }

    @Override
    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException {
        User user=userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new JobPortalException("USER_NOT_FOUND"));
        if(!passwordEncoder.matches(loginDTO.getPassword(),user.getPassword())) throw new JobPortalException("INVALID_CREDENTIALS");
        return user.toDTO();
    }

    @Override
    @Async
    public void sendOtp(String email) throws Exception {
        User user = userRepository.findByEmail(email).orElseThrow(()->new JobPortalException("USER_NOT_FOUND"));
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Your OTP code");
        String genOTP = utility.generateRandomOTP();
        OTP otp=new OTP(email,genOTP, LocalDateTime.now());
        otpRepository.save(otp);
        mimeMessageHelper.setText(Data.getMessageBody(genOTP,user.getUserName()),true);
        mailSender.send(mimeMessage);
    }

    @Override
    public Boolean verifyOtp(String email, String otp) throws JobPortalException {
        OTP otpEntity = otpRepository.findByEmail(email).orElseThrow(()->new JobPortalException("OTP_NOT_FOUND"));
        if(!otpEntity.getOtpCode().equals(otp)) throw new JobPortalException("INVALID_OTP");
        return true;
    }

    @Override
    public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException {
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new JobPortalException("USER_NOT_FOUND"));
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        userRepository.save(user);
        return new ResponseDTO("Password changed successfully.");
    }

    @Scheduled(fixedRate = 600000)
    @Transactional
    public void deleteExpiredOtps() {
        LocalDateTime expirationTime = LocalDateTime.now().minusMinutes(10);
        List<OTP>expiredOTPs=otpRepository.findByOtpCreationTimeBefore(expirationTime);
        if(!expiredOTPs.isEmpty()){
            otpRepository.deleteAll(expiredOTPs);
        }
        System.out.println("Removed: "+expiredOTPs.size()+" expiredOTPs");
    }
}

