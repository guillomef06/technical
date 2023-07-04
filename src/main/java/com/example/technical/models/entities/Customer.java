package com.example.technical.models.entities;

/* FILE User
AUTHOR Guillaume
PROJECT technical
DATE 29/06/2023 */

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * This is the Customer entity
 * It will be used to store customers into the database
 * <p>
 * Lombok's annotations are used to avoid boilerplate code
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 64)
    private String userName;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    @Column(nullable = false, length = 64)
    private String country;

    @Column(length = 10)
    private String phoneNumber;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Override
    public final boolean equals(Object o) {
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy hibernateProxy ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy hibernateProxy ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Customer customer = (Customer) o;
        return getId() != null && getId().equals(customer.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
