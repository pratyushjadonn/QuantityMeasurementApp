package QuantityMeasurmentApp.entity;

import jakarta.persistence.*;
import lombok.*;

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

    // FIX: nullable = true — OAuth user ka name kabhi kabhi null aata hai
    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    // FIX: @Builder.Default — builder use karte waqt provider null nahi rahega
    @Builder.Default
    private AuthProvider provider = AuthProvider.LOCAL;
}