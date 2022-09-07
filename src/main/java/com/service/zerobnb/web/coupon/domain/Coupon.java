package com.service.zerobnb.web.coupon.domain;

import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.util.status.CouponType;
import com.service.zerobnb.web.accommodation.domain.Accommodation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon extends BaseTimeEntity {
    @Id
    @Column(name = "coupon_id")
    private Long id;

    private int count;

    private String name;

    private long discount;

    @Enumerated(EnumType.STRING)
    private CouponType couponType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;
}
