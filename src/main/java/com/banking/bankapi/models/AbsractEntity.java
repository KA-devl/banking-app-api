package com.banking.bankapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass //Information is inherited by other classes, there is no table for this class, it is used to avoid code duplication
public class AbsractEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdated;
}
