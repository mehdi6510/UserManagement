package com.afifi.usermng.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Document(collection = "user")
public class User {

    @Id
    private String id;

    @NotNull(message = "Title is mandatory")
    @Size(min = 2, message = "Title should have atleast 2 characters")
    private String title;

    @NotNull(message = "First Name is mandatory")
    @Size(min = 2, message = "First Name should have atleast 2 characters")
    private String firstName;

    @NotNull(message = "Last Name is mandatory")
    @Size(min = 2, message = "Last Name should have atleast 2 characters")
    private String lastName;

    @Indexed(unique = true)
    @NotNull(message = "Username is mandatory")
    @Size(min = 6, message = "Username should have atleast 6 characters")
    private String username;

    @NotNull(message = "Password is mandatory")
    @Size(min = 6, message = "Password should have atleast 6 characters")
    private String password;

    @Indexed(unique = true)
    @NotNull(message = "Email is mandatory")
    @Size(min = 3, message = "Email should have atleast 3 characters")
    @Email
    private String email;

    private boolean isAdmin;

    @Size(max = 11, message = "Cell Phone must be null or have atleast 11 characters")
    private String cellPhone;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date creationDate;

    @LastModifiedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date updatingDate;

    public User() {
    }

    public User(String id,
                @NotNull(message = "Title is mandatory") @Size(min = 2, message = "Title should have atleast 2 characters") String title,
                @NotNull(message = "First Name is mandatory") @Size(min = 2, message = "First Name should have atleast 2 characters") String firstName,
                @NotNull(message = "Last Name is mandatory") @Size(min = 2, message = "Last Name should have atleast 2 characters") String lastName,
                @NotNull(message = "Username is mandatory") @Size(min = 6, message = "Username should have atleast 6 characters") String username,
                @NotNull(message = "Password is mandatory") @Size(min = 6, message = "Password should have atleast 6 characters") String password,
                @NotNull(message = "Email is mandatory") @Size(min = 3, message = "Email should have atleast 3 characters") @Email String email,
                boolean isAdmin,
                @Size(max = 11, message = "Cell Phone must be null or have atleast 11 characters") String cellPhone) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
        this.cellPhone = cellPhone;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("title='" + title + "'")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .add("email='" + email + "'")
                .add("isAdmin=" + isAdmin)
                .add("cellPhone='" + cellPhone + "'")
                .add("creationDate=" + creationDate)
                .add("updatingDate=" + updatingDate)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isAdmin == user.isAdmin &&
                id.equals(user.id) &&
                title.equals(user.title) &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                email.equals(user.email) &&
                Objects.equals(cellPhone, user.cellPhone) &&
                creationDate.equals(user.creationDate) &&
                updatingDate.equals(user.updatingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, firstName, lastName, username, password,
                email, isAdmin, cellPhone, creationDate, updatingDate);
    }
}
