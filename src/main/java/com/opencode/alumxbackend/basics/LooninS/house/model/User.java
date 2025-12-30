import com.opencode.alumxbackend.basics.LooninS.house

import lombok.*
import jakarta.persistence.*

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    private String password;

    private String email;

    private String phone;

    private String address;

    private String city;

}

