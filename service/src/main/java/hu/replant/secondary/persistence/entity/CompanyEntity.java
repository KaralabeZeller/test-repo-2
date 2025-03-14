package hu.replant.secondary.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "company_id_gen", sequenceName = "company_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter
    @Column(name = "old_id")
    private String oldId;

    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Setter
    @Column(name = "tax_number", nullable = false)
    private String taxNumber;

    @Setter
    @Column(name = "accounting_address", nullable = false)
    private String accountingAddress;

    @Setter
    @Column(name = "web")
    private String web;

    @Setter
    @Column(name = "logo")
    private String logo;

    @Setter
    @Column(name = "contact_name")
    private String contactName;

    @Setter
    @Column(name = "contact_email")
    private String contactEmail;

    @Setter
    @Column(name = "contact_phone")
    private String contactPhone;

    @Setter
    @Column(name = "owner")
    private String owner;

    @Setter
    @Column(name = "discount", precision = 10, scale = 2)
    private BigDecimal discount;

    @Setter
    @Column(name = "unas_id")
    private String unasId;

    @Setter
    @Column(name = "monthly_billing")
    private Boolean monthlyBilling;
}
