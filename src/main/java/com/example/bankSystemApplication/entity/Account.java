package com.example.bankSystemApplication.entity;

import com.example.bankSystemApplication.utility.ValidationMessages;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = ValidationMessages.ACCOUNT_HOLDER_NAME)
    private String accountHolderName;

    @NotNull(message = ValidationMessages.BALANCE)
    @Positive(message = ValidationMessages.BALANCE_POSITIVE)
    private Double balance;

    private Boolean deleted = false;

}
