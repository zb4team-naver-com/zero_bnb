package com.service.zerobnb.web.host.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.zerobnb.web.host.model.HostInput;
import com.service.zerobnb.web.host.service.HostService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest(controllers = HostController.class)
@Disabled
class HostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private HostService hostService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerHostTest() throws Exception {
        when(hostService.registerHost(any())).thenReturn(200L);

        mockMvc.perform(post("/host/register").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                HostInput.builder()
                                        .businessContact("010-3938-4948")
                                        .companyRegistrationNumber("2019484738383")
                                        .profileImage("https://localhost/test.png")
                                        .email("t1l3k3j@gmail.com")
                                        .build())))
                .andExpect(status().isOk());
        verify(hostService, times(1)).registerHost(any());
    }

    @Test
    void updateHostTest() throws Exception {
        when(hostService.updateHost(any(), any())).thenReturn(200L);

        mockMvc.perform(put("/host/update/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                HostInput.builder()
                                        .businessContact("010-3938-4948")
                                        .companyRegistrationNumber("2019484738383")
                                        .profileImage("https://localhost/test.png")
                                        .email("t1l3k3j@gmail.com")
                                        .build())))
                .andExpect(status().isOk());
        verify(hostService, times(1)).updateHost(any(), any());
    }

    @Test
    void disableHostTest() throws Exception {
        when(hostService.disableHost(any())).thenReturn(200L);
        mockMvc.perform(patch("/host/disable/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        verify(hostService, times(1)).disableHost(any());
    }

    @Test
    void ableHostTest() throws Exception {
        when(hostService.ableHost(any())).thenReturn(200L);
        mockMvc.perform(patch("/host/able/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        verify(hostService, times(1)).ableHost(any());
    }
}