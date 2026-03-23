package com.virtadity.manease.infrastructure.web.rest_controller.producer;

import com.virtadity.manease.application.port.in.producer.*;
import com.virtadity.manease.infrastructure.web.assembler.ProducerAssembler;
import com.virtadity.manease.infrastructure.web.mapper.ProducerDTOMapper;
import com.virtadity.manease.infrastructure.web.rest_controller.ProducerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProducerController.class)
public class TestProducerControllerDelete {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProducerRegisterInputBoundary producerRegisterInputBoundary;

    @MockitoBean
    private ProducerGetAllInputBoundary producerGetAllInputBoundary;

    @MockitoBean
    private ProducerGetOneInputBoundary producerGetOneInputBoundary;

    @MockitoBean
    private ProducerCorrectInputBoundary producerCorrectInputBoundary;

    @MockitoBean
    private ProducerDeleteInputBoundary producerDeleteInputBoundary;

    @MockitoBean
    private ProducerAssembler producerAssembler;

    @MockitoBean
    private ProducerDTOMapper producerDTOMapper;

    @Test
    void testDelete() throws Exception {
        var producerId = UUID.randomUUID();
        var deletePath = String.format("/producers/%s", producerId);

        mockMvc.perform(delete(deletePath).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
