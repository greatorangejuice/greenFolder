/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service.serviceImpl;

import com.blansplatform.dto.RegistrationPage;
import com.blansplatform.service.RegistrationPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationPageServiceImpl implements RegistrationPageService {
    final private RegistrationPage registrationPage;

    @Autowired
    public RegistrationPageServiceImpl(RegistrationPage registrationPage) {
        this.registrationPage = registrationPage;
    }

    @Override
    public RegistrationPage getRegistrationPage() {
        return registrationPage;
    }

}
