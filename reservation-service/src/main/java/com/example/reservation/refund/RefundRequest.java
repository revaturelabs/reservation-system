package com.example.reservation.refund;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RefundRequest {


        private String chargeId;
        private int Amount;
        private LocalDateTime paymentDate;





}