package entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
@ToString
@Entity(name = "user_discount_codes")
@Table(name = "user_discount_codes", schema = "public")
public class UserDiscountCodesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_discount_code_id")
    int userDiscountCodeId;

    @ManyToOne(targetEntity = UserEntity.class)
    @Column(name = "user_id")
    int userId;

    @ManyToOne(targetEntity =  DiscountCodeEntity.class)
    @Column(name = "discount_code_id")
    int discountCodeId;

    @Column(name = "discount_code_external_id")
    String discountCodeExternalId;

    @Column(name = "claimed")
    boolean claimed;
}
