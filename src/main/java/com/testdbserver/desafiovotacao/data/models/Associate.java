package com.testdbserver.desafiovotacao.data.models;

import com.testdbserver.desafiovotacao.data.enums.AssociateStatusEnum;
import com.testdbserver.desafiovotacao.data.enums.AssociateRolesEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="associate")
public class Associate implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="fullname")
    private String fullname;

    @Column(name="cpf", nullable = false)
    private String cpf;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AssociateStatusEnum status;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private AssociateRolesEnum role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status.equals(AssociateStatusEnum.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status.equals(AssociateStatusEnum.ACTIVE) ;
    }
}
