package com.service.zerobnb.web.host.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;

import com.service.zerobnb.util.status.HostStatus;
import com.service.zerobnb.web.error.message.ExceptionMessage;
import com.service.zerobnb.web.error.model.HostException;
import com.service.zerobnb.web.guest.domain.Guest;
import com.service.zerobnb.web.guest.service.AuthService;
import com.service.zerobnb.web.host.domain.Host;
import com.service.zerobnb.web.host.model.HostInput;
import com.service.zerobnb.web.host.repository.HostRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class HostServiceTest {
    @Autowired
    private HostService hostService;

    @MockBean
    private HostRepository hostRepository;

    @MockBean
    private AuthService guestService;

    @Test
    void registerHost() {
        when(guestService.findGuestByEmail(any())).thenReturn(Guest.builder().id(25L).build());
        when(hostRepository.save(any())).thenReturn(Host.builder().status(HostStatus.ACTIVE).id(25L).build());
        assertDoesNotThrow(() -> hostService.registerHost(HostInput.builder().build()));
        verify(guestService, times(1)).findGuestByEmail(any());
        verify(hostRepository, times(1)).save(any());
    }

    @Test
    void updateHost() {
        when(hostRepository.existsById(any())).thenReturn(true);
        when(hostRepository.findById(any())).thenReturn(Optional.of(Host.builder().status(HostStatus.ACTIVE).id(25L).build()));
        when(hostRepository.save(any())).thenReturn(Host.builder().status(HostStatus.ACTIVE).id(25L).build());
        assertDoesNotThrow(() -> hostService.updateHost(HostInput.builder().build(), 25L));
        verify(hostRepository, times(1)).existsById(any());
        verify(hostRepository, times(1)).findById(any());
        verify(hostRepository, times(1)).save(any());

        when(hostRepository.existsById(any())).thenReturn(false);
        when(hostRepository.findById(any())).thenReturn(Optional.of(Host.builder().status(HostStatus.ACTIVE).id(25L).build()));
        when(hostRepository.save(any())).thenReturn(Host.builder().status(HostStatus.ACTIVE).id(25L).build());
        assertEquals(ExceptionMessage.NOT_EXIST_HOST.message(), assertThrows(HostException.class,
                () -> hostService.updateHost(HostInput.builder().build(), 25L)).getMessage());
    }

    @Test
    void disableHost() {
        when(hostRepository.existsById(any())).thenReturn(true);
        when(hostRepository.findById(any())).thenReturn(Optional.of(Host.builder().status(HostStatus.ACTIVE).id(25L).build()));
        when(hostRepository.save(any())).thenReturn(Host.builder().status(HostStatus.ACTIVE).id(25L).build());
        assertDoesNotThrow(() -> hostService.disableHost(25L));
        verify(hostRepository, times(1)).existsById(any());
        verify(hostRepository, times(1)).findById(any());
        verify(hostRepository, times(1)).save(any());

        when(hostRepository.existsById(any())).thenReturn(false);
        when(hostRepository.findById(any())).thenReturn(Optional.of(Host.builder().status(HostStatus.ACTIVE).id(25L).build()));
        when(hostRepository.save(any())).thenReturn(Host.builder().status(HostStatus.ACTIVE).id(25L).build());
        assertEquals(ExceptionMessage.NOT_EXIST_HOST.message(), assertThrows(HostException.class,
                () -> hostService.disableHost(25L)).getMessage());
    }

    @Test
    void ableHost() {
        when(hostRepository.existsById(any())).thenReturn(true);
        when(hostRepository.findById(any())).thenReturn(Optional.of(Host.builder().status(HostStatus.ACTIVE).id(25L).build()));
        when(hostRepository.save(any())).thenReturn(Host.builder().status(HostStatus.ACTIVE).id(25L).build());
        assertDoesNotThrow(() -> hostService.ableHost(25L));
        verify(hostRepository, times(1)).existsById(any());
        verify(hostRepository, times(1)).findById(any());
        verify(hostRepository, times(1)).save(any());

        when(hostRepository.existsById(any())).thenReturn(false);
        when(hostRepository.findById(any())).thenReturn(Optional.of(Host.builder().status(HostStatus.ACTIVE).id(25L).build()));
        when(hostRepository.save(any())).thenReturn(Host.builder().status(HostStatus.ACTIVE).id(25L).build());
        assertEquals(ExceptionMessage.NOT_EXIST_HOST.message(), assertThrows(HostException.class,
                () -> hostService.ableHost(25L)).getMessage());
    }

    @Test
    void findHostById() {
        when(hostRepository.existsById(any())).thenReturn(true);
        when(hostRepository.findById(any())).thenReturn(Optional.of(Host.builder().status(HostStatus.ACTIVE).id(25L).build()));
        assertDoesNotThrow(() -> hostService.findHostById(25L));
        verify(hostRepository, times(1)).existsById(any());
        verify(hostRepository, times(1)).findById(any());

        when(hostRepository.existsById(any())).thenReturn(false);
        when(hostRepository.findById(any())).thenReturn(Optional.of(Host.builder().status(HostStatus.ACTIVE).id(25L).build()));
        assertEquals(ExceptionMessage.NOT_EXIST_HOST.message(), assertThrows(HostException.class,
                () -> hostService.findHostById(25L)).getMessage());
    }

    @Test
    void findHostByEmail() {
        when(guestService.findGuestByEmail(any())).thenReturn(Guest.builder().isHost(true).build());
        assertDoesNotThrow(() -> hostService.findHostByEmail("test@test.com"));
        verify(guestService, times(1)).findGuestByEmail(any());

        when(guestService.findGuestByEmail(any())).thenReturn(Guest.builder().isHost(false).build());
        assertEquals(ExceptionMessage.NOT_EXIST_HOST.message(), assertThrows(HostException.class,
                () -> hostService.findHostByEmail("test@test.com")).getMessage());
    }
}