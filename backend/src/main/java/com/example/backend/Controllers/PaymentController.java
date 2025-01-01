package com.example.backend.Controllers;

import com.example.backend.Entities.Payment;
import com.example.backend.Services.PaymentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.HTML;


@RestController
@RequiredArgsConstructor
@RequestMapping("payment")
public class PaymentController {


    private final PaymentServices paymentServices;


    @PostMapping
    public ResponseEntity<String> pay(@RequestBody Payment payment) throws Exception {
        try {
            this.paymentServices.pay(payment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
