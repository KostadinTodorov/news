package bg.tuvarna.sit.newsblog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority, Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleName name;

    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();


    @Override
    public String getAuthority() {
        return "";
    }

}

