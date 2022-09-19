package com.service.zerobnb.web.host.service;

import com.service.zerobnb.util.status.HostStatus;
import com.service.zerobnb.web.error.message.ExceptionMessage;
import com.service.zerobnb.web.error.model.HostException;
import com.service.zerobnb.web.guest.domain.Guest;
import com.service.zerobnb.web.guest.service.GuestService;
import com.service.zerobnb.web.host.domain.Host;
import com.service.zerobnb.web.host.model.HostInput;
import com.service.zerobnb.web.host.repository.HostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HostService {
    private final HostRepository hostRepository;
    private final GuestService guestService;

    @Transactional
    public long registerHost(HostInput hostInput) {
        Guest guest = guestService.findGuestByEmail(hostInput.getEmail());

        if (guest.isHost()) {
            throw new HostException(ExceptionMessage.ALREADY_EXIST_HOST);
        }

        return hostRepository.save(Host.from(hostInput, guest)).getId();
    }

    @Transactional
    public long updateHost(HostInput hostInput, Long hostId) {
        Host host = Host.from(hostInput, findHostById(hostId));

        if (!HostStatus.checkIsActive(host.getStatus())) {
            throw new HostException(ExceptionMessage.DISABLED_HOST);
        }
        return hostRepository.save(host).getId();
    }

    @Transactional
    public long disableHost(Long hostId) {
        Host host = findHostById(hostId);

        if (!HostStatus.checkIsActive(host.getStatus())) {
            throw new HostException(ExceptionMessage.DISABLED_HOST);
        }
        host.setStatus(HostStatus.DISABLED);
        return hostRepository.save(host).getId();
    }

    @Transactional
    public long ableHost(Long hostId) {
        Host host = findHostById(hostId);
        host.setStatus(HostStatus.ACTIVE);
        return hostRepository.save(host).getId();
    }

    public Host findHostById(Long hostId) {
        if (!hostRepository.existsById(hostId)) {
            throw new HostException(ExceptionMessage.NOT_EXIST_HOST);
        }

        Host host = hostRepository.findById(hostId).get();
        return host;
    }

    public Host findHostByEmail(String email) {
        Guest guest = guestService.findGuestByEmail(email);

        if (!guest.isHost()) {
            throw new HostException(ExceptionMessage.NOT_EXIST_HOST);
        }

        Host host = guest.getHost();
        return host;
    }
}
