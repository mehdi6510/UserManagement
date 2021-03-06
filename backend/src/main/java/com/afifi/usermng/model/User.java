package com.afifi.usermng.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username", name = "UK_UM_USERNAME_CONSTRAINT"),
        @UniqueConstraint(columnNames = "email", name = "UK_UM_EMAIL_CONSTRAINT")
})
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Title is mandatory")
    @Size(min = 2, max = 5, message = "Title should have atleast 2 and characters")
    private String title;

    @NotNull(message = "First Name is mandatory")
    @Size(min = 2, max = 50, message = "First Name should have atleast 2 characters")
    private String firstName;

    @NotNull(message = "Last Name is mandatory")
    @Size(min = 2, max = 50, message = "Last Name should have atleast 2 characters")
    private String lastName;

    @NotNull(message = "Username is mandatory")
    @Size(min = 6, max = 25, message = "Username should have atleast 6 characters")
    private String username;

    @NotNull(message = "Password is mandatory")
    @Size(min = 6, message = "Password should have atleast 6 characters")
    private String password;

    @NotNull(message = "Email is mandatory")
    @Size(min = 3, max = 100, message = "Email should have atleast 3 characters")
    @Email(message = "Email has invalid format")
    private String email;

    @Size(max = 11, message = "Cell Phone must be null or have atleast 11 characters")
    private String cellPhone;

    private boolean isAdmin;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    private Date lastModifiedDate;

    public User() {
    }

    public User(Long id, String title, String firstName, String lastName, String username, String password,
                String email, String cellPhone, boolean isAdmin) {
        this(title, firstName, lastName, username, password, email, cellPhone, isAdmin);
        this.id = id;
    }

    public User(String title, String firstName, String lastName, String username, String password,
                String email, String cellPhone, boolean isAdmin) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
        this.cellPhone = cellPhone;
    }

    public User(Long id, String title, String firstName, String lastName, String username, String password, String email,
                String cellPhone, boolean isAdmin, String createdBy, Date createdDate,
                String lastModifiedBy, Date lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.cellPhone = cellPhone;
        this.isAdmin = isAdmin;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .add("email='" + email + "'")
                .add("cellPhone='" + cellPhone + "'")
                .add("isAdmin=" + isAdmin)
                .add("createdBy='" + createdBy + "'")
                .add("createdDate=" + createdDate)
                .add("lastModifiedBy='" + lastModifiedBy + "'")
                .add("lastModifiedDate=" + lastModifiedDate)
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
                Objects.equals(cellPhone, user.cellPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, firstName, lastName, username, password,
                email, isAdmin, cellPhone);
    }
}
