package com.example.backend.Services;

import com.example.backend.Entities.Payment;
import com.example.backend.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServices {

    private final PaymentRepository paymentRepository ;


    @Autowired
    public PaymentServices(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public boolean pay(Payment payment) throws Exception {

        if(!validCreditCardNumber( payment.getCreditCardNumber())){
            throw new IllegalArgumentException("Credit Card Number Isn't Valid");
        }

        try {
            this.paymentRepository.save(payment);
            return true;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private boolean validCreditCardNumber(String creditCardNumber){
        creditCardNumber = creditCardNumber.replaceAll("[^\\d]", "");

        if (creditCardNumber.length() < 13 || creditCardNumber.length() > 19) {
            return false;
        }

        int sum = 0;
        boolean shouldDouble = false;

        for (int i = creditCardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(creditCardNumber.charAt(i));

            if (shouldDouble) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            shouldDouble = !shouldDouble;
        }
        return sum % 10 == 0;
    }
}
