package com.example.bankSystemApplication.entity;

import com.example.bankSystemApplication.enums.TransactionTypes;
import com.example.bankSystemApplication.utility.ValidationMessages;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = ValidationMessages.TRANSACTION_NAME)
    private String name;

    private LocalDate date;

    @NotNull(message = ValidationMessages.TRANSACTION_TYPE)
    @Enumerated(EnumType.STRING)
    private TransactionTypes type;

    @NotNull(message = ValidationMessages.AMOUNT)
    @Positive(message = ValidationMessages.AMOUNT_POSITIVE)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @NotNull(message = ValidationMessages.ACCOUNT)
    private Account account;

    private Boolean deleted = false;

}
