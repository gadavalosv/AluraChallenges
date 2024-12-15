package com.gadv.medvoll.api.controller;

import com.gadv.medvoll.api.domain.model.consult.ConsultCancelData;
import com.gadv.medvoll.api.domain.model.consult.ConsultDetailData;
import com.gadv.medvoll.api.domain.model.consult.ConsultReservation;
import com.gadv.medvoll.api.domain.model.consult.ConsultReserveData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consults")
public class ConsultController {
    @Autowired
    private ConsultReservation consultReservation;

    @PostMapping
    @Transactional
    public ResponseEntity<ConsultDetailData> reserveConsult(@RequestBody @Valid ConsultReserveData consultReserveData){
        consultReservation.reserve(consultReserveData);
        return ResponseEntity.ok(new ConsultDetailData(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid ConsultCancelData consultCancelData) {
        consultReservation.cancelConsult(consultCancelData);
        return ResponseEntity.noContent().build();
    }

}
