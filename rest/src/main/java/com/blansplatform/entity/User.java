package com.blansplatform.entity;

import com.blansplatform.enumeration.Faculty;
import com.blansplatform.enumeration.University;
import com.blansplatform.enumeration.UserStatus;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String username;
    private UserStatus userStatus;
    private String city;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns =
            {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;
    private String email;
    private String password;
    @OneToMany(targetEntity = Task.class, mappedBy = "customer", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Task> customerTasks;
    @OneToMany(targetEntity = Task.class, mappedBy = "executor", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Task> executorTasks;
    @OneToMany(targetEntity = Offer.class, mappedBy = "executor", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Offer> executorOffers;
    @OneToMany(targetEntity = Offer.class, mappedBy = "customer", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Offer> customerOffers;
    @OneToMany(targetEntity = Message.class, mappedBy = "userTo", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Message> inboxMessages;
    @OneToMany(targetEntity = Message.class, mappedBy = "userFrom", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Message> outgoingMessage;
    private Faculty faculty;
    private String webMoneyAccount;
    private String course;
    private University university;
    @LastModifiedDate
    private Date updated;

    public User(String name, String surname, String username, String city, List<Role> roles, String email, String password, List<Task> customerTasks, List<Task> executorTasks,
                List<Offer> executorOffers, List<Offer> customerOffers, List<Message> inboxMessages, List<Message> outgoingMessage, Faculty faculty,
                String webMoneyAccount, String course, University university, UserStatus userStatus, Date updated) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.city = city;
        this.roles = roles;
        this.email = email;
        this.password = password;
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
        this.userStatus = userStatus;
        this.updated = updated;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getCustomerTasks() {
        return customerTasks;
    }

    public void setCustomerTasks(List<Task> customerTasks) {
        this.customerTasks = customerTasks;
    }

    public List<Task> getExecutorTasks() {
        return executorTasks;
    }

    public void setExecutorTasks(List<Task> executorTasks) {
        this.executorTasks = executorTasks;
    }

    public List<Offer> getExecutorOffers() {
        return executorOffers;
    }

    public void setExecutorOffers(List<Offer> executorOffers) {
        this.executorOffers = executorOffers;
    }

    public List<Offer> getCustomerOffers() {
        return customerOffers;
    }

    public void setCustomerOffers(List<Offer> customerOffers) {
        this.customerOffers = customerOffers;
    }

    public List<Message> getInboxMessages() {
        return inboxMessages;
    }

    public void setInboxMessages(List<Message> inboxMessages) {
        this.inboxMessages = inboxMessages;
    }

    public List<Message> getOutgoingMessage() {
        return outgoingMessage;
    }

    public void setOutgoingMessage(List<Message> outgoingMessage) {
        this.outgoingMessage = outgoingMessage;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(username, user.username) &&
                userStatus == user.userStatus &&
                Objects.equals(city, user.city) &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(customerTasks, user.customerTasks) &&
                Objects.equals(executorTasks, user.executorTasks) &&
                Objects.equals(executorOffers, user.executorOffers) &&
                Objects.equals(customerOffers, user.customerOffers) &&
                Objects.equals(inboxMessages, user.inboxMessages) &&
                Objects.equals(outgoingMessage, user.outgoingMessage) &&
                faculty == user.faculty &&
                Objects.equals(webMoneyAccount, user.webMoneyAccount) &&
                Objects.equals(course, user.course) &&
                university == user.university &&
                Objects.equals(updated, user.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, username, userStatus, city, roles, email, password, customerTasks, executorTasks, executorOffers, customerOffers, inboxMessages, outgoingMessage, faculty, webMoneyAccount, course, university, updated);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", userStatus=" + userStatus +
                ", city='" + city + '\'' +
                ", roles=" + roles +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
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
                ", updated=" + updated +
                '}';
    }
}
