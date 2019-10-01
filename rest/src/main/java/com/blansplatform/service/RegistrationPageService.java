package com.blansplatform.service;

import com.blansplatform.dto.RegistrationPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationPageService {
    final private RegistrationPage registrationPage;

    @Autowired
    public RegistrationPageService(RegistrationPage registrationPage) {
        this.registrationPage = registrationPage;
    }

    public RegistrationPage getRegistrationPage() {
        return registrationPage;
    }

}
