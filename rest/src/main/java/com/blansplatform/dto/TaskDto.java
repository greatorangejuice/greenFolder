/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.dto;

import com.blansplatform.enumeration.Faculty;
import com.blansplatform.enumeration.TaskStatus;

public class TaskDto {

    private String secretId;
    private String name;
    private String deadline;
    private String type;
    private String specification;
    private String description;
    private TaskStatus taskStatus;
    private String subject;
    private Faculty faculty;
    private String keywords;
    private String customer;
    private String executor;
    private String course;

    public TaskDto(String secretId, String name, String deadline, String type, String specification,
                   String description, TaskStatus taskStatus, String subject, Faculty faculty,
                   String keywords, String customer, String executor, String course) {
        this.secretId = secretId;
        this.name = name;
        this.deadline = deadline;
        this.type = type;
        this.specification = specification;
        this.description = description;
        this.taskStatus = taskStatus;
        this.subject = subject;
        this.faculty = faculty;
        this.keywords = keywords;
        this.customer = customer;
        this.executor = executor;
        this.course = course;
    }

    public TaskDto() {
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }
}
