package Oenskeskyen.Repository;

import Oenskeskyen.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("h2")
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;
/*
    @Test
    void testSaveAndGetUserCustomer(){
        String Fullname = "Kea";
        String mail = "Kea@mail.com";
        String password = "Kea1234";

        repository.saveNewUser(Fullname,mail,password);

        User obj = repository.getUser("Kea@mail.com");

        assertEquals("Kea",obj.getFullName());
    }
    */
}
