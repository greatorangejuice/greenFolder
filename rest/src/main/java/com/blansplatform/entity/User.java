package com.blansplatform.entity;

import com.blansplatform.enumeration.Faculty;
import com.blansplatform.enumeration.Role;
import com.blansplatform.enumeration.University;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    @OneToMany(targetEntity = Task.class,
                mappedBy = "customer",
                cascade = CascadeType.REMOVE,
                fetch = FetchType.LAZY)
    @JsonManagedReference(value = "task_customer")
    private List<Task> customerTasks;
    @OneToMany(targetEntity = Task.class,
            mappedBy = "executor",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    @JsonManagedReference(value = "task_executor")
    private List<Task> executorTasks;
    @OneToMany(targetEntity = Offer.class,
                mappedBy = "executor",
                cascade = CascadeType.REMOVE,
                fetch = FetchType.LAZY)
    @JsonManagedReference(value = "offers_executor")
    private List<Offer> executorOffers;
    @OneToMany(targetEntity = Offer.class,
            mappedBy = "customer",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    @JsonManagedReference(value = "offers_customer")
    private List<Offer> customerOffers;
    @OneToMany(targetEntity = Message.class,
                mappedBy = "userTo",
                cascade = CascadeType.REMOVE,
                fetch = FetchType.LAZY)
    @JsonManagedReference(value = "inboxMessages_userTo")
    private List<Message> inboxMessages;
    @OneToMany(targetEntity = Message.class,
                mappedBy = "userFrom",
                cascade = CascadeType.REMOVE,
                fetch = FetchType.LAZY)
    @JsonManagedReference(value = "outgoingMessage_userFrom")
    private List<Message> outgoingMessage;
    private Faculty faculty;
    private String webMoneyAccount;
    private String course;
    private University university;


    public User(String name, String email, String password, Role role, List<Task> customerTasks, List<Task> executorTasks, List<Offer> executorOffers, List<Offer> customerOffers, List<Message> inboxMessages,
                List<Message> outgoingMessage, Faculty faculty, String webMoneyAccount, String course, University university) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.customerTasks = customerTasks;
        this.executorTasks = executorTasks;
        this.executorOffers = executorOffers;
        this.customerOffers = customerOffers;
        this.inboxMessages = inboxMessages;
        this.outgoingMessage = outgoingMessage;
        this.faculty = faculty;
        this.webMoneyAccount = webMoneyAccount;
        this.course = course;
        this.university = university;
    }

    public User() {
    }

    public List<Offer> getCustomerOffers() {
        return customerOffers;
    }

    public void setCustomerOffers(List<Offer> customerOffers) {
        this.customerOffers = customerOffers;
    }

    public List<Task> getExecutorTasks() {
        return executorTasks;
    }

    public void setExecutorTasks(List<Task> executorTasks) {
        this.executorTasks = executorTasks;
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

    public List<Message> getOutgoingMessage() {
        return outgoingMessage;
    }

    public void setOutgoingMessage(List<Message> outgoingMessage) {
        this.outgoingMessage = outgoingMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Task> getCustomerTasks() {
        return customerTasks;
    }

    public void setCustomerTasks(List<Task> customerTasks) {
        this.customerTasks = customerTasks;
    }

    public List<Offer> getExecutorOffers() {
        return executorOffers;
    }

    public void setExecutorOffers(List<Offer> executorOffers) {
        this.executorOffers = executorOffers;
    }

    public List<Message> getInboxMessages() {
        return inboxMessages;
    }

    public void setInboxMessages(List<Message> inboxMessages) {
        this.inboxMessages = inboxMessages;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                Objects.equals(customerTasks, user.customerTasks) &&
                Objects.equals(executorTasks, user.executorTasks) &&
                Objects.equals(executorOffers, user.executorOffers) &&
                Objects.equals(customerOffers, user.customerOffers) &&
                Objects.equals(inboxMessages, user.inboxMessages) &&
                Objects.equals(outgoingMessage, user.outgoingMessage) &&
                faculty == user.faculty &&
                Objects.equals(webMoneyAccount, user.webMoneyAccount) &&
                Objects.equals(course, user.course) &&
                university == user.university;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", customerTasks=" + customerTasks +
                ", executorTasks=" + executorTasks +
                ", executorOffers=" + executorOffers +
                ", customerOffers=" + customerOffers +
                ", inboxMessages=" + inboxMessages +
                ", outgoingMessage=" + outgoingMessage +
                ", faculty=" + faculty +
                ", webMoneyAccount='" + webMoneyAccount + '\'' +
                ", course='" + course + '\'' +
                ", university=" + university +
                '}';
    }
}
