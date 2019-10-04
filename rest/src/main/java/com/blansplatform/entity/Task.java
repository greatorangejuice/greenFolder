package com.blansplatform.entity;

import com.blansplatform.enumeration.Faculty;
import com.blansplatform.enumeration.TaskStatus;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specification;
    private TaskStatus taskStatus;
    private String subject;
    private Faculty faculty;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    private User customer;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "executorId")
    private User executor;
    @OneToMany(targetEntity = Offer.class, mappedBy = "task")
    private List<Offer> offers;
    private String keywords;

    public Task(String name, String specification, TaskStatus taskStatus, String subject, Faculty faculty, User customer, User executor, List<Offer> offers, String keywords) {
        this.name = name;
        this.specification = specification;
        this.taskStatus = taskStatus;
        this.subject = subject;
        this.faculty = faculty;
        this.customer = customer;
        this.executor = executor;
        this.offers = offers;
        this.keywords = keywords;
    }

    public Task() {
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
        return id.equals(task.id) &&
                name.equals(task.name) &&
                specification.equals(task.specification) &&
                taskStatus == task.taskStatus &&
                subject.equals(task.subject) &&
                faculty == task.faculty &&
                customer.equals(task.customer) &&
                executor.equals(task.executor) &&
                offers.equals(task.offers) &&
                keywords.equals(task.keywords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specification='" + specification + '\'' +
                ", taskStatus=" + taskStatus +
                ", subject='" + subject + '\'' +
                ", faculty=" + faculty +
                ", customer=" + customer +
                ", executor=" + executor +
                ", offers=" + offers +
                ", keywords='" + keywords + '\'' +
                '}';
    }
}
