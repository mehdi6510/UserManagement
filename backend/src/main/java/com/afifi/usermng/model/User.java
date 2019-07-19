package com.afifi.usermng.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
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

}
