package com.quantityapp.quantity.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Quantity Service ka User — sirf email store karta hai.
 * Password/OAuth yahan nahi hai — woh Auth Service ka kaam hai.
 * JWT se email extract hoti hai aur yahan user dhundha/create hota hai.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;
}
