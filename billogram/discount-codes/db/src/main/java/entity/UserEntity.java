package entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
@ToString
@Entity(name = "users")
@Table(name = "users", schema = "public")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    int userId;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "email")
    String email;

    @Column(name = "phone_number")
    String phoneNumber;
}
