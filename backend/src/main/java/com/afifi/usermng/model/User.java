package com.afifi.usermng.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

@Document
public class User {

    @Id
    private String id;
    private String name;
    private String lastName;
    private String username;
    private String password;
    private String cellPhone;
    private Date creationDate;
    private char status;
    private char type;

    public User() {
    }

    public User(String name, String lastName, String username, String password,
                String cellPhone, Date creationDate, char status, char type) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
        this.creationDate = creationDate;
        this.status = status;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return status == user.status &&
                type == user.type &&
                id.equals(user.id) &&
                name.equals(user.name) &&
                lastName.equals(user.lastName) &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                cellPhone.equals(user.cellPhone) &&
                creationDate.equals(user.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, username, password, cellPhone, creationDate, status, type);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", cellPhone='").append(cellPhone).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }

}
