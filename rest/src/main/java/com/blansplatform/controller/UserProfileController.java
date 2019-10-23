/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.controller;

import com.blansplatform.dto.UserProfileDto;
import com.blansplatform.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody UserProfileDto userProfileDtoById(@PathVariable Long id) {
        return userProfileService.userProfileById(id);
    }

    @GetMapping(path = "/username/{username}")
    public @ResponseBody UserProfileDto userProfileDtoByUsername(@PathVariable String username) {
        return userProfileService.userProfileByUsername(username);
    }





}
