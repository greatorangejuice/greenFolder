package com.blansplatform.entity;

import com.blansplatform.enumeration.Faculty;
import com.blansplatform.enumeration.TaskStatus;
import com.blansplatform.enumeration.University;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deadline;
    private String type;
    private String secretId;
    private String name;
    private String description;
    private String specification;
    private TaskStatus taskStatus;
    private String subject;
    private University university;
    private Faculty faculty;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private User customer;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "executorId")
    private User executor;
    @OneToMany(targetEntity = Offer.class, mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Offer> offers;
    private String keywords;
    private String course;

    public Task(String deadline, String type, String secretId, String name, String description,
                String specification, TaskStatus taskStatus, String subject, Faculty faculty,
                User customer, User executor, List<Offer> offers, String keywords, String course, University university) {
        this.deadline = deadline;
        this.type = type;
        this.secretId = secretId;
        this.name = name;
        this.description = description;
        this.specification = specification;
        this.taskStatus = taskStatus;
        this.subject = subject;
        this.faculty = faculty;
        this.customer = customer;
        this.executor = executor;
        this.offers = offers;
        this.keywords = keywords;
        this.course = course;
        this.university = university;
    }

    public Task(String deadline, String type, String secretId, String name, String description,
                String specification, TaskStatus taskStatus, String subject, Faculty faculty, String keywords, String course, University university) {
        this.deadline = deadline;
        this.type = type;
        this.secretId = secretId;
        this.name = name;
        this.description = description;
        this.specification = specification;
        this.taskStatus = taskStatus;
        this.subject = subject;
        this.faculty = faculty;
        this.keywords = keywords;
        this.course = course;
        this.university = university;
    }

    public Task() {
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
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

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(deadline, task.deadline) &&
                Objects.equals(type, task.type) &&
                Objects.equals(secretId, task.secretId) &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(specification, task.specification) &&
                taskStatus == task.taskStatus &&
                Objects.equals(subject, task.subject) &&
                university == task.university &&
                faculty == task.faculty &&
                Objects.equals(customer, task.customer) &&
                Objects.equals(executor, task.executor) &&
                Objects.equals(offers, task.offers) &&
                Objects.equals(keywords, task.keywords) &&
                Objects.equals(course, task.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deadline, type, secretId, name, description, specification, taskStatus, subject, university, faculty, customer, executor, offers, keywords, course);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", deadline='" + deadline + '\'' +
                ", type='" + type + '\'' +
                ", secretId='" + secretId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", specification='" + specification + '\'' +
                ", taskStatus=" + taskStatus +
                ", subject='" + subject + '\'' +
                ", university=" + university +
                ", faculty=" + faculty +
                ", customer=" + customer +
                ", executor=" + executor +
                ", offers=" + offers +
                ", keywords='" + keywords + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
