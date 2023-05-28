package com.banking.bankapi.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Integer id;

    private String iban;

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdated;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
