package entity;

import lombok.*;
import model.DiscountType;
import model.DiscountTypeEnum;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
@Getter
public class DiscountTypeEntity {

    @Enumerated(EnumType.STRING)
    DiscountTypeEnum type;

    int value;

    public static DiscountTypeEntity fromDiscountType(DiscountType discountType) {
        if (discountType == null) {
            return null;
        }

        return DiscountTypeEntity.builder()
                .type(discountType.getType())
                .value(discountType.getValue())
                .build();
    }

    public DiscountType toDiscountType() {
        return DiscountType.builder()
                .type(type)
                .value(value)
                .build();
    }
}

