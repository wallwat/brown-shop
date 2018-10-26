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
    protected String firstName;

    @NotNull
    @Size(max = 60)
    protected String lastName;

    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message="invalid phone number.")
    protected String mobilePhone;

    @NotNull
    @Size(max = 30)
    @Column(name = "username", unique = true)
    protected String username;

    @NotNull
    @Size(max = 30)
    protected String password;
}
