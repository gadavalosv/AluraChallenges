package com.gadv.medvoll.api.controller;

import com.gadv.medvoll.api.domain.model.consult.ConsultDetailData;
import com.gadv.medvoll.api.domain.model.consult.ConsultReservation;
import com.gadv.medvoll.api.domain.model.consult.ConsultReserveData;
import com.gadv.medvoll.api.domain.model.doctor.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") //test can't access to .env file so this is needed
@AutoConfigureJsonTesters
class ConsultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<ConsultReserveData> consultReserveDataJacksonTester;

    @Autowired
    private JacksonTester<ConsultDetailData> consultDetailDataJacksonTester;

    @MockBean //this two lines are to prevent for this class to try to use the Database when testing reserving consults
    private ConsultReservation consultReservation;

    @Test
    @DisplayName("Debería devolver http 400 cuando la request no tenga datos")
    @WithMockUser //to fake login
    void reserveConsult_scene1() throws Exception {
        var response = mockMvc.perform(post("/consults"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Debería devolver http 200 cuando la request reciba un json valido")
    @WithMockUser
    void reserveConsult_scene2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGIA;
        var consultDetailData = new ConsultDetailData(null, 2l, 2l, date);
        var consultReserveData = new ConsultReserveData(2l, 4l, date, specialty);
        when(consultReservation.reserve(any(ConsultReserveData.class))).thenReturn(consultDetailData);
        var response = mockMvc.perform(post("/consults")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(consultReserveDataJacksonTester.write(
                                consultReserveData
                        ).getJson()
                        )
                )
                .andReturn().getResponse();
        var expectedJson = consultDetailDataJacksonTester.write(
                consultDetailData
        ).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}