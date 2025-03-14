package hu.replant.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private Long id;
    private String oldId;
    private String name;
    private String taxNumber;
    private String accountingAddress;
    private String web;
    private String logo;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private String owner;
    private BigDecimal discount;
    private String unasId;
    private Boolean monthlyBilling;
    private Instant createdAt;
    private Instant updatedAt;
}
