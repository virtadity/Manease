package com.virtadity.manease.controller;

import com.virtadity.manease.service.SupplyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(SupplyController.class)
@AutoConfigureRestTestClient
public class SupplyControllerTests {

    @Autowired
    private RestTestClient restTestClient;

    @MockitoBean
    private SupplyService supplyService;

    @Test
    void emptySupplies() {
        when(supplyService.all()).thenReturn(List.of());
        restTestClient.get().uri("/supplies/")
                .exchange()
                .expectBody(List.class)
                .isEqualTo(List.of());
    }

}
