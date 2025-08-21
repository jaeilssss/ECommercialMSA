package com.ecommercial.shopping.productservice.product.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_status_history")
@AllArgsConstructor @NoArgsConstructor
@Builder
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class ProductStatusHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_status_history_id")
    private Long id;

    private Long productId;

    @CreatedDate
    private LocalDateTime createdAt;

    private boolean beforeStatus;

    private boolean afterStatus;

}
