package com.service.zerobnb.web.common.image.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.zerobnb.web.common.image.service.ImageService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest(controllers = ImageController.class)
@Disabled
class ImageControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ImageService imageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerImages() throws Exception {
        when(imageService.uploadImage(any())).thenReturn("test");
        MockMultipartFile image = new MockMultipartFile("images", "imagefile.jpeg", "image/jpeg", "<<jpeg data>>".getBytes());
        mockMvc.perform(fileUpload("/images").file(image).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}