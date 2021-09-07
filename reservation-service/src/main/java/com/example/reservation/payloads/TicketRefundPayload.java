package com.example.reservation.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketRefundPayload {
    double amount;
}
