/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.dto;

import com.blansplatform.entity.Role;
import com.blansplatform.enumeration.Faculty;
import com.blansplatform.enumeration.University;
import com.blansplatform.enumeration.UserStatus;

import java.util.List;

public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private String username;
    private UserStatus userStatus;
    private String city;
    private Faculty faculty;
    private String webMoneyAccount;
    private String course;
    private University university;
    private List<Role> roles;

    public UserDto(Long id, String name, String surname, String username, UserStatus userStatus, String city, Faculty faculty, String webMoneyAccount, String course, University university, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.userStatus = userStatus;
        this.city = city;
        this.faculty = faculty;
        this.webMoneyAccount = webMoneyAccount;
        this.course = course;
        this.university = university;
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getWebMoneyAccount() {
        return webMoneyAccount;
    }

    public void setWebMoneyAccount(String webMoneyAccount) {
        this.webMoneyAccount = webMoneyAccount;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", userStatus=" + userStatus +
                ", city='" + city + '\'' +
                ", faculty=" + faculty +
                ", webMoneyAccount='" + webMoneyAccount + '\'' +
                ", course='" + course + '\'' +
                ", university=" + university +
                '}';
    }
}
