package com.service.zerobnb.web.room.service;

import com.service.zerobnb.web.accommodation.domain.Accommodation;
import com.service.zerobnb.web.accommodation.service.AccommodationService;
import com.service.zerobnb.web.error.message.ExceptionMessage;
import com.service.zerobnb.web.error.model.RoomException;
import com.service.zerobnb.web.room.domain.Room;
import com.service.zerobnb.web.room.domain.RoomImage;
import com.service.zerobnb.web.room.dto.RoomDto;
import com.service.zerobnb.web.room.model.RoomImageInput;
import com.service.zerobnb.web.room.model.RoomInput;
import com.service.zerobnb.web.room.repository.RoomImageRepository;
import com.service.zerobnb.web.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomService {
    private final RoomRepository roomRepository;
    private final AccommodationService accommodationService;

    private final RoomImageRepository roomImageRepository;

    public List<RoomDto> findRoomsByAccommodationId(Long accommodationId) {
        return accommodationService.findAccommodationById(accommodationId).getRoomList().stream().map(RoomDto::from).collect(Collectors.toList());
    }

    public Room findRoomById(Long roomId) {
        if (!roomRepository.existsById(roomId)) {
            throw new RoomException(ExceptionMessage.NOT_EXIST_ROOM);
        }

        Room room = roomRepository.findById(roomId).get();

        if(room.isDelete()) {
            throw new RoomException(ExceptionMessage.ALREADY_DELETE_ROOM);
        }
        return room;
    }

    @Transactional
    public Long registerRoom(RoomInput roomInput) {
        Accommodation accommodation = accommodationService.findAccommodationById(roomInput.getAccommodationId());
        Room room = Room.from(roomInput, accommodation);
        roomImageRepository.saveAll(roomInput.getRoomImages().stream().map(roomImageInput -> RoomImage.from(roomImageInput, room)).collect(Collectors.toList()));
        return roomRepository.save(room).getId();
    }

    @Transactional
    public Long updateRoom(RoomInput roomInput, Long roomId) {
        Room room = updateRoom(findRoomById(roomId), roomInput);
        return room.getId();
    }

    @Transactional
    public Long deleteRoom(Long roomId) {
        Room room = findRoomById(roomId);
        room.setDelete(true);
        return room.getId();
    }

    private Room updateRoom(Room room, RoomInput roomInput) {
        room = Room.updateByRoomInput(room, roomInput);

        List<RoomImage> roomImages = room.getRoomImageList();
        List<RoomImageInput> roomImageInputs = roomInput.getRoomImages();

        for (int i = 0; i < roomImageInputs.size(); i++) {
            RoomImage roomImage = roomImages.get(i);
            RoomImageInput roomImageInput = roomImageInputs.get(i);
            roomImage.setUrl(roomImageInput.getUrl());
        }
        return room;
    }
}
