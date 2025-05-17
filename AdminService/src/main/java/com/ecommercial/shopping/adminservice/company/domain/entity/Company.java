package com.ecommercial.shopping.adminservice.company.domain.entity;

import com.ecommercial.shopping.adminservice.admin.domain.vo.Address;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Company {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @Column(nullable = false)
    private String companyName;

    private String ceoName;
    private String phoneNumber;
    private String businessNumber;
    @Embedded
    private Address address;
}
