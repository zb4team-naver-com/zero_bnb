package com.service.zerobnb.web.accommodation.domain;

import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.web.accommodation.model.AccommodationImageInput;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@ToString(exclude = "accommodation")
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationImage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accommodation_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    private String url;

    public static AccommodationImage from(AccommodationImageInput accommodationImageInput, Accommodation accommodation) {
        return AccommodationImage.builder()
                .accommodation(accommodation)
                .url(accommodationImageInput.getUrl())
                .build();
    }
}
