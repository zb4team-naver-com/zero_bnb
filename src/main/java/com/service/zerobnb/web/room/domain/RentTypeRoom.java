package com.service.zerobnb.web.room.domain;

import com.service.zerobnb.util.BaseTimeEntity;
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
public class RentTypeRoom extends BaseTimeEntity {
    @Id
    @Column(name = "rent_type_room_id")
    private Long id;

    private int roomCount;

    private long cost;

    private int discount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
