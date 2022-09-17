package com.service.zerobnb.web.accommodation.service;

import com.service.zerobnb.util.status.HostStatus;
import com.service.zerobnb.util.type.PopularFacilityServiceType;
import com.service.zerobnb.web.accommodation.domain.Accommodation;
import com.service.zerobnb.web.accommodation.domain.AccommodationImage;
import com.service.zerobnb.web.accommodation.domain.Event;
import com.service.zerobnb.web.accommodation.domain.PopularFacilityService;
import com.service.zerobnb.web.accommodation.dto.AccommodationForHostDto;
import com.service.zerobnb.web.accommodation.model.AccommodationImageInput;
import com.service.zerobnb.web.accommodation.model.AccommodationInput;
import com.service.zerobnb.web.accommodation.model.EventInput;
import com.service.zerobnb.web.accommodation.model.PopularFacilityServiceInput;
import com.service.zerobnb.web.accommodation.repository.AccommodationImageRepository;
import com.service.zerobnb.web.accommodation.repository.AccommodationRepository;
import com.service.zerobnb.web.accommodation.repository.EventRepository;
import com.service.zerobnb.web.accommodation.repository.PopularFacilityServiceRepository;
import com.service.zerobnb.web.error.message.ExceptionMessage;
import com.service.zerobnb.web.error.model.AccommodationException;
import com.service.zerobnb.web.error.model.HostException;
import com.service.zerobnb.web.host.domain.Host;
import com.service.zerobnb.web.host.service.HostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final AccommodationImageRepository accommodationImageRepository;
    private final EventRepository eventRepository;
    private final PopularFacilityServiceRepository popularFacilityServiceRepository;
    private final HostService hostService;

    @Transactional
    public Long registerAccommodation(AccommodationInput accommodationInput) {
        Host host = hostService.findHostById(accommodationInput.getHostId());

        if (!HostStatus.checkIsActive(host.getStatus())) {
            throw new HostException(ExceptionMessage.DISABLED_HOST);
        }

        Accommodation accommodation = Accommodation.from(accommodationInput, hostService.findHostById(accommodationInput.getHostId()));
        saveAccommodation(accommodationInput, accommodation);
        return accommodation.getId();
    }

    @Transactional
    public Long updateAccommodation(AccommodationInput accommodationInput, Long accommodationId) {
        Host host = hostService.findHostById(accommodationInput.getHostId());

        if (!HostStatus.checkIsActive(host.getStatus())) {
            throw new HostException(ExceptionMessage.DISABLED_HOST);
        }

        Accommodation accommodation = findAccommodationById(accommodationId);
        updateAccommodation(accommodationInput, accommodation);
        return accommodation.getId();
    }

    @Transactional
    public Long deleteAccommodation(Long accommodationId) {
        // TODO principal 을 이용한 email 반환 및 해당 호스트 정보 획득, host activate 체크
        Accommodation accommodation = findAccommodationById(accommodationId);
        accommodation.setDelete(true);
        return accommodation.getId();
    }

    private void saveAccommodation(AccommodationInput accommodationInput, Accommodation accommodation) {
        accommodationRepository.save(accommodation);

        accommodationImageRepository.saveAll(accommodationInput.getAccommodationImageInputs().stream()
                .map(accommodationImageInput -> AccommodationImage.from(accommodationImageInput, accommodation))
                .collect(Collectors.toList()));

        eventRepository.saveAll(accommodationInput.getEventInputs().stream()
                .map(eventInput -> Event.from(eventInput, accommodation))
                .collect(Collectors.toList()));

        popularFacilityServiceRepository.saveAll(accommodationInput.getPopularFacilityServiceInputs().stream()
                .map(popularFacilityServiceInput -> PopularFacilityService.from(popularFacilityServiceInput, accommodation))
                .collect(Collectors.toList()));
    }

    private void updateAccommodation(AccommodationInput accommodationInput, Accommodation accommodation) {
        accommodationRepository.save(accommodation);

        List<AccommodationImage> accommodationImages = accommodation.getAccommodationImageList();
        List<AccommodationImageInput> accommodationImageInputs = accommodationInput.getAccommodationImageInputs();

        for (int i = 0; i < accommodationImages.size(); i++) {
            AccommodationImage accommodationImage = accommodationImages.get(i);
            AccommodationImageInput accommodationImageInput = accommodationImageInputs.get(i);
            accommodationImage.setUrl(accommodationImageInput.getUrl());
        }


        List<Event> events = accommodation.getEventList();
        List<EventInput> eventInputs = accommodationInput.getEventInputs();

        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            EventInput eventInput = eventInputs.get(i);
            event.setDescription(eventInput.getDescription());
        }

        List<PopularFacilityService> popularFacilityServices = accommodation.getPopularFacilityServiceList();
        List<PopularFacilityServiceInput> popularFacilityServiceInputs = accommodationInput.getPopularFacilityServiceInputs();

        for (int i = 0; i < popularFacilityServices.size(); i++) {
            PopularFacilityService popularFacilityService = popularFacilityServices.get(i);
            PopularFacilityServiceInput popularFacilityServiceInput = popularFacilityServiceInputs.get(i);
            popularFacilityService.setPopularFacilityServiceType(PopularFacilityServiceType.convert(popularFacilityServiceInput.getPopularFacilityServiceType()));
        }
    }

    public Accommodation findAccommodationById(Long accommodationId) {
        if (!accommodationRepository.existsById(accommodationId)) {
            throw new AccommodationException(ExceptionMessage.NOT_EXIST_ACCOMMODATION);
        }

        Accommodation accommodation = accommodationRepository.findById(accommodationId).get();

        if (accommodation.isDelete()) {
            throw new AccommodationException(ExceptionMessage.ALREADY_DELETE_ACCOMMODATION);
        }
        return accommodationRepository.findById(accommodationId).get();
    }

    public List<AccommodationForHostDto> findAccommodationByHostId(Long hostId) {
        Host host = hostService.findHostById(hostId);

        if (!HostStatus.checkIsActive(host.getStatus())) {
            throw new HostException(ExceptionMessage.DISABLED_HOST);
        }
        return host.getAccommodationList().stream().filter(accommodation -> accommodation.isDelete() != true)
                .map(AccommodationForHostDto::from).collect(Collectors.toList());
    }
}
