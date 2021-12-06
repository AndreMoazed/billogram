package model;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@Builder
public class DiscountType {
    DiscountTypeEnum type;
    
    int value;
}
