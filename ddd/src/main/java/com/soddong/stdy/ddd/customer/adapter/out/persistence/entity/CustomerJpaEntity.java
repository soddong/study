package com.soddong.stdy.ddd.customer.adapter.out.persistence.entity;

import com.soddong.stdy.ddd.customer.domain.model.vo.PhoneNumber;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="customer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CustomerJpaEntity {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Embedded
    private PhoneNumber phone;
}
