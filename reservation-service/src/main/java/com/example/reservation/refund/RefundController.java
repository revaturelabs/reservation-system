package com.example.reservation.refund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.java.Log;
import com.example.reservation.refund.RefundService;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.StripeException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Refund;


@Log
@Controller
public class RefundController {

        @Autowired
        RefundService refundService;


        @GetMapping("/refund/{paymentintent}/{amount}")
        public String initiateRefund(@PathVariable String paymentintent,@PathVariable int amount, Model model,RefundRequest refundRequest)
         throws StripeException,AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        refundRequest.setChargeId(paymentintent);
        //refundRequest.setPaymentDate(LocalDateTime(567))
        Refund fund=refundService.refund(paymentintent,amount);
        model.addAttribute("id", fund.getId());
        model.addAttribute("process", fund.getObject());

        model.addAttribute("status", fund.getStatus());
        model.addAttribute("currency", fund.getCurrency());
        model.addAttribute("chargeId", fund.getCharge());
        model.addAttribute("amount",fund.getAmount());

        return "result";


        }

        @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }


}