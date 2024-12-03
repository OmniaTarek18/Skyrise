    package com.example.backend.User;


    import com.example.backend.Account.Account;
    import com.example.backend.Account.AccountRepository;
    import com.example.backend.Account.AccountServices;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.beans.Transient;
    import java.util.Date;

    @Service
    public class UserServices {

        private final UserRepository userRepository ;
        @Autowired
        public UserServices(UserRepository userRepository) {
            this.userRepository = userRepository ;
        }


        public User createUser(Account account , String countryCode, String phoneNumber, String nationalId, Date dateOfBirth,
                               String firstName, String lastName, boolean gender, String passportNumber,
                               String passportIssuingCountry) {
            return  new User(account , countryCode, phoneNumber, nationalId, dateOfBirth,
                    firstName, lastName, gender, passportNumber, passportIssuingCountry);
        }

        public boolean addUser(User user){
            try{
                this.userRepository.save(user);
                return true ;
            }
            catch (Exception e){
               return false;
            }
        }

    }
