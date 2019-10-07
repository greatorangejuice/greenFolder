/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.dto;

import com.blansplatform.enumeration.Faculty;
import com.blansplatform.enumeration.TaskStatus;

public class TaskDto {

    private Long id;
    private String name;
    private String specification;
    private TaskStatus taskStatus;
    private String subject;
    private Faculty faculty;
    private String keywords;

    public TaskDto(Long id, String name, String specification, TaskStatus taskStatus, String subject, Faculty faculty, String keywords) {
        this.id = id;
        this.name = name;
        this.specification = specification;
        this.taskStatus = taskStatus;
        this.subject = subject;
        this.faculty = faculty;
        this.keywords = keywords;
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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
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

    @Override
    public String toString() {
        return "TaskDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specification='" + specification + '\'' +
                ", taskStatus=" + taskStatus +
                ", subject='" + subject + '\'' +
                ", faculty=" + faculty +
                ", keywords='" + keywords + '\'' +
                '}';
    }
}
