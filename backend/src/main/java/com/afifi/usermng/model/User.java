package com.afifi.usermng.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

@Document
public class User {

    @Id
    private Long id;
    private String nationalNumber;
    private String name;
    private String lastName;
    private String username;
    private String password;
    private String address;
    private String fixedPhone;
    private String cellPhone;
    private Date birthDate;
    private Date registrationDate;
    private int age;
    private int score;
    private char status;
    private char type;

    public User() {
    }

    public User(Long id, String nationalNumber, String name, String lastName, String username, String password, String address, String fixedPhone, String cellPhone, Date birthDate, Date registrationDate, int age, int score, char status, char type) {
        this.id = id;
        this.nationalNumber = nationalNumber;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.address = address;
        this.fixedPhone = fixedPhone;
        this.cellPhone = cellPhone;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.age = age;
        this.score = score;
        this.status = status;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(String nationalNumber) {
        this.nationalNumber = nationalNumber;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFixedPhone() {
        return fixedPhone;
    }

    public void setFixedPhone(String fixedPhone) {
        this.fixedPhone = fixedPhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
        return age == user.age &&
                score == user.score &&
                status == user.status &&
                type == user.type &&
                id.equals(user.id) &&
                nationalNumber.equals(user.nationalNumber) &&
                name.equals(user.name) &&
                lastName.equals(user.lastName) &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                Objects.equals(address, user.address) &&
                Objects.equals(fixedPhone, user.fixedPhone) &&
                Objects.equals(cellPhone, user.cellPhone) &&
                Objects.equals(birthDate, user.birthDate) &&
                registrationDate.equals(user.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nationalNumber, name, lastName, username, password, address, fixedPhone, cellPhone,
                birthDate, registrationDate, age, score, status, type);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", nationalNumber='").append(nationalNumber).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", fixedPhone='").append(fixedPhone).append('\'');
        sb.append(", cellPhone='").append(cellPhone).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", registrationDate=").append(registrationDate);
        sb.append(", age=").append(age);
        sb.append(", score=").append(score);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
