package com.service.zerobnb.web.room.domain;

import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.web.room.model.RoomImageInput;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@ToString(exclude = "room")
@NoArgsConstructor
@AllArgsConstructor
public class RoomImage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_image_id")
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public static RoomImage from(RoomImageInput roomImageInput, Room room) {
        return RoomImage.builder()
                .url(roomImageInput.getUrl())
                .room(room)
                .build();
    }
}
