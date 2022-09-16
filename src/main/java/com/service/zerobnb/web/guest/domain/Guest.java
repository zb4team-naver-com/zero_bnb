package com.service.zerobnb.web.guest.domain;


import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.util.status.UserStatus;
import com.service.zerobnb.web.host.domain.Host;
import com.service.zerobnb.web.reservation.domain.Reservation;
import com.service.zerobnb.web.review.domain.Review;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "guest", indexes = {@Index(name = "guest_email_index", columnList = "email")})
public class Guest extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private Long id;

    private String email;

    private String password;

    private String name;

    private String birth;

    private String phone;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private String emailAuthKey;

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

    @Builder
    public Guest(Long id, String email, String password, String name, String birth,
                 String phone, UserStatus status, String emailAuthKey, String profileImage, Long point,
                 boolean isHost) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.status = status;
        this.emailAuthKey = emailAuthKey;
        this.profileImage = profileImage;
        this.point = point;
        this.isHost = isHost;
    }

    public void changeStatus(UserStatus status) {
        this.status = status;
    }
}
