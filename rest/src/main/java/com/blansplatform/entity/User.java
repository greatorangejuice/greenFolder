package com.blansplatform.entity;

import com.blansplatform.enumeration.Faculty;
import com.blansplatform.enumeration.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
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
    @JsonIgnore
    private List<Task> tasks;
    @OneToMany(targetEntity = Offer.class,
                mappedBy = "executor",
                cascade = CascadeType.REMOVE,
                fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Offer> offers;
    @OneToMany(targetEntity = Message.class,
                mappedBy = "userTo",
                cascade = CascadeType.REMOVE,
                fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Message> inboxMessages;
    @OneToMany(targetEntity = Message.class,
                mappedBy = "userFrom",
                cascade = CascadeType.REMOVE,
                fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Message> outgoingMessage;
    private Faculty faculty;
    private String webMoneyAccount;

    public User(String name, String email, Role role, ArrayList<Task> tasks, ArrayList<Offer> offers, ArrayList<Message> inboxMessages,
                ArrayList<Message> outgoingMessage ,Faculty faculty, String webMoneyAccount, String password) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.tasks = tasks;
        this.offers = offers;
        this.inboxMessages = inboxMessages;
        this.outgoingMessage = outgoingMessage;
        this.faculty = faculty;
        this.webMoneyAccount = webMoneyAccount;
        this.password = password;
    }

    public User(Long id, String name, String email, String password, Role role, ArrayList<Task> tasks, ArrayList<Offer> offers, ArrayList<Message> inboxMessages,
                ArrayList<Message> outgoingMessage , Faculty faculty, String webMoneyAccount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.tasks = tasks;
        this.offers = offers;
        this.inboxMessages = inboxMessages;
        this.outgoingMessage = outgoingMessage;
        this.faculty = faculty;
        this.webMoneyAccount = webMoneyAccount;
    }

    public User() {
    }

    public List<Message> getOutgoingMessage() {
        return outgoingMessage;
    }

    public void setOutgoingMessage(ArrayList<Message> outgoingMessage) {
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
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
        return id.equals(user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                Objects.equals(tasks, user.tasks) &&
                Objects.equals(offers, user.offers) &&
                Objects.equals(inboxMessages, user.inboxMessages) &&
                Objects.equals(outgoingMessage, user.outgoingMessage) &&
                faculty == user.faculty &&
                Objects.equals(webMoneyAccount, user.webMoneyAccount);
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
                ", role=" + role +
                ", tasks=" + tasks +
                ", offers=" + offers +
                ", inboxMessages=" + inboxMessages +
                ", outgoingMessage=" + outgoingMessage +
                ", faculty=" + faculty +
                ", webMoneyAccount='" + webMoneyAccount + '\'' +
                '}';
    }
}
