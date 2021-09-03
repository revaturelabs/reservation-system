package com.example.reservation.refund;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;

import com.stripe.exception.*;
import com.stripe.model.Refund;


@Service
public class RefundService {



        @Value("${STRIPE_SECRET_KEY}")
    String secretKey;


    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }


    public Refund refund( String paymentIntent,int amount)throws StripeException,AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException   {

        Map<String,Object> refundParams=new HashMap<>();
        refundParams.put("charge", paymentIntent);

        refundParams.put("amount", amount*100);

        Refund refund=Refund.create(refundParams);


        return refund;



    }
}
