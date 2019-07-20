package com.afifi.usermng.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@Document
public class User {

    @Id
    private String id;
    private String name;
    private String lastName;
    @Indexed(unique = true)
    private String username;
    private String password;
    private String cellPhone;
    @CreatedDate
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private Date creationDate;
    @LastModifiedDate
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private Date updatingDate;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private Date removingDate;
    private char status;
    private char type;
    @Version
    private int version;

    public User() {
    }

    public User(String name, String lastName, String username, String password,
                String cellPhone, char status, char type) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
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

    public Date getUpdatingDate() {
        return updatingDate;
    }

    public void setUpdatingDate(Date updatingDate) {
        this.updatingDate = updatingDate;
    }

    public Date getRemovingDate() {
        return removingDate;
    }

    public void setRemovingDate(Date removingDate) {
        this.removingDate = removingDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return status == user.status &&
                type == user.type &&
                version == user.version &&
                id.equals(user.id) &&
                name.equals(user.name) &&
                lastName.equals(user.lastName) &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                Objects.equals(cellPhone, user.cellPhone) &&
                creationDate.equals(user.creationDate) &&
                Objects.equals(updatingDate, user.updatingDate) &&
                Objects.equals(removingDate, user.removingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, username, password, cellPhone,
                creationDate, updatingDate, removingDate, status, type, version);
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
        sb.append(", updatingDate=").append(updatingDate);
        sb.append(", removingDate=").append(removingDate);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }

}
