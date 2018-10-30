package com.example.brownshop.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 60)
    private String firstName;

    @NotNull
    @Size(max = 60)
    private String lastName;

    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message="invalid phone number.")
    private String mobilePhone;

    @NotNull
    @Size(max = 30)
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Size(max = 30)
    private String password;
}
