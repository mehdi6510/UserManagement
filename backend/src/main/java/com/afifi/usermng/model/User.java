package com.afifi.usermng.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String username;
    private String password;
    private String cellPhone;
    private String email;
    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date creationDate;
    @LastModifiedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date updatingDate;

    public User() {
    }

    public User(String firstName, String lastName, String username, String password, String cellPhone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdatingDate() {
        return updatingDate;
    }

    public void setUpdatingDate(Date updatingDate) {
        this.updatingDate = updatingDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                Objects.equals(cellPhone, user.cellPhone) &&
                Objects.equals(email, user.email) &&
                creationDate.equals(user.creationDate) &&
                Objects.equals(updatingDate, user.updatingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, username, password,
                cellPhone, email, creationDate, updatingDate);
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .add("cellPhone='" + cellPhone + "'")
                .add("email='" + email + "'")
                .add("creationDate=" + creationDate)
                .add("updatingDate=" + updatingDate)
                .toString();
    }
}
