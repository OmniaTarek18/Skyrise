package com.example.backend.Services;

import com.example.backend.Entities.Account;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.Payment;
import com.example.backend.Entities.User;
import com.example.backend.Enums.Gender;
import com.example.backend.Enums.Role;
import com.example.backend.Repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PaymentServiceTest {

    private User user;
    private Flight flight;

    @BeforeEach
    void setup(){
        user = createUser(createAccount());
        flight = createFlight();
    }

    private User createUser(Account account) {
        User user = User.builder()
                .account(account)
                .gender(Gender.FEMALE)
                .firstName("firstName")
                .lastName("lastName")
                .nationalId("nationalId")
                .dateOfBirth(LocalDate.of(2024, 12, 10))
                .countryCode("CountryCode")
                .phoneNumber("phoneNumber")
                .passportNumber("PassportNumber")
                .passportIssuingCountry("passportIssuingCountry")
                .build();
        return user;
    }

    private Account createAccount() {
        Account account = Account.builder()
                .email("example@gmail.com")
                .password("password")
                .role(Role.USER)
                .build();
        return account;
    }


    private Flight createFlight() {
        LocalDate date = LocalDate.of(2024, 10, 8);

        Flight flight = Flight.builder()
                .isCancel(false)
                .departureDate(date)
                .arrivalDate(date)
                .economyPrice(1000)
                .businessPrice(2000)
                .availableEconomySeats(13)
                .availableBusinessSeats(3)
                .build();
        return flight;
    }

    @Test
    public void testPay() {
        PaymentRepository mockRepository = Mockito.mock(PaymentRepository.class);

        PaymentServices paymentServices = new PaymentServices(mockRepository);

        Payment payment = new Payment();
        payment.setFlightId(1);
        payment.setUserId(1);
        payment.setUser(user);
        payment.setFlight(flight);
        payment.setAmount(1000.03);


        String[] cardNumbers = {"4539 1488 0343 6467", "4539 1488 0343 6469", "1111 2222 3333 4444", "", "gkdewg",
        "1233 1222 22 222", "4539-1488-0343-6467"
        };
        boolean[] valid = {true, false, true, false, false, false, true};
        boolean result = true;

        for (int i = 0;i < cardNumbers.length;i++){
            payment.setCreditCardNumber(cardNumbers[i]);
            try{
                if(paymentServices.pay(payment) && !valid[i]){
                    result = false;
                    break;
                }
            }
            catch (Exception e){
                if(valid[i] || !e.getMessage().equals("Credit Card Number Isn't Valid")){
                    result = false;
                    break;
                }
            }
        }
        assertEquals(true, result);

    }



}
