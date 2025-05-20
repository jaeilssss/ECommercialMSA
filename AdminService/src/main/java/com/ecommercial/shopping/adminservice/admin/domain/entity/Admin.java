package com.ecommercial.shopping.adminservice.admin.domain.entity;
import com.ecommercial.shopping.adminservice.admin.domain.vo.Address;
import com.ecommercial.shopping.adminservice.company.domain.entity.Company;
import com.ecommercial.shopping.adminservice.global.enums.AdminRoleEnum;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin")
@Getter
@Setter
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(nullable = false)
    private String password;

    private String name;
    private String birthday;
    private String phoneNumber;
    @Embedded
    private Address address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AdminRoleEnum role;



}
