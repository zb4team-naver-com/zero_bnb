package com.service.zerobnb.web.guest.domain;


import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.util.status.UserStatus;
import com.service.zerobnb.web.host.domain.Host;
import com.service.zerobnb.web.reservation.domain.Reservation;
import com.service.zerobnb.web.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "guest", indexes = {@Index(name = "guest_email_index", columnList = "email")})
public class Guest extends BaseTimeEntity {
    @Id
    @Column(name = "guest_id")
    private Long id;

    private String name;

    private String birth;

    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private String profileImage;

    private Long point;

    private boolean isHost;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "guest")
    private Host host;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "guest")
    private List<Review> reviewList;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "guest")
    private List<Reservation> reservationList;
}
