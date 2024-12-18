package com.gadv.medvoll.api.controller;

import com.gadv.medvoll.api.domain.model.consult.ConsultCancelData;
import com.gadv.medvoll.api.domain.model.consult.ConsultDetailData;
import com.gadv.medvoll.api.domain.model.consult.ConsultReservation;
import com.gadv.medvoll.api.domain.model.consult.ConsultReserveData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consults")
@SecurityRequirement(name = "bearer-key")
public class ConsultController {
    @Autowired
    private ConsultReservation consultReservation;

    @PostMapping
    @Transactional
    public ResponseEntity<ConsultDetailData> reserveConsult(@RequestBody @Valid ConsultReserveData consultReserveData){
        var consultDetailData = consultReservation.reserve(consultReserveData);
        return ResponseEntity.ok(consultDetailData);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid ConsultCancelData consultCancelData) {
        consultReservation.cancelConsult(consultCancelData);
        return ResponseEntity.noContent().build();
    }

}
