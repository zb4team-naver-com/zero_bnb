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
        return hostRepository.save(Host.from(hostInput, guestService.findGuestByEmail(hostInput.getEmail()))).getId();
    }

    @Transactional
    public long updateHost(HostInput hostInput, Long hostId) {
        Host host = Host.from(hostInput, findHostById(hostId));
        return hostRepository.save(host).getId();
    }

    @Transactional
    public long disableHost(Long hostId) {
        Host host = findHostById(hostId);
        host.setHostStatus(HostStatus.DISABLED);
        return hostRepository.save(host).getId();
    }

    @Transactional
    public long ableHost(Long hostId) {
        Host host = findHostById(hostId);
        host.setHostStatus(HostStatus.ACTIVE);
        return hostRepository.save(host).getId();
    }

    public Host findHostById(Long hostId) {
        if (!hostRepository.existsById(hostId)) {
            throw new HostException(ExceptionMessage.NOT_EXIST_HOST);
        }
        return hostRepository.findById(hostId).get();
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
