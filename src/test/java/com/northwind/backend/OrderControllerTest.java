package com.northwind.backend;


import com.northwind.backend.controller.OrderController;
import com.northwind.backend.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ImportAutoConfiguration(classes = {SecurityConfig.class})
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;
/*    @MockBean
    private OrderRepository orderRepository;*/

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void shouldReturnAdminViewAlternative() throws Exception {
        mockMvc.perform(get("/api/order"))
                .andExpect(status().is(200));
    }
}
