package Oenskeskyen.Repository;


import Oenskeskyen.Model.User;
import Oenskeskyen.Model.Wish;
import Oenskeskyen.Model.WishList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
public class OenskeSkyenRepositoryTest {

    @Autowired
    OenskeSkyenRepository oenskeSkyenRepository;


    @Test
    @DirtiesContext
    void saveNewWishListTest(){
    WishList expectedWishList = new WishList("bday1", "gifts");

    oenskeSkyenRepository.saveNewWishList(expectedWishList.getName(), expectedWishList.getDescription(), 2);

    WishList actualWishList = oenskeSkyenRepository.getWishListById(3);

    assertEquals(expectedWishList.getName(), actualWishList.getName());
    assertEquals(expectedWishList.getDescription(), actualWishList.getDescription());

    }

    @Test
    @DirtiesContext
    void getWishListByIdTest(){
        WishList wishList = new WishList("bob", "1");
        wishList.setWishListId(3);

        oenskeSkyenRepository.saveNewWishList(wishList.getName(), wishList.getDescription(), 2);
        WishList actual = oenskeSkyenRepository.getWishListById(3);

        int expectedValue = actual.getWishListId();
        String expectedName = actual.getName();

        assertEquals(wishList.getWishListId(), expectedValue);
        assertEquals(wishList.getName(), expectedName);
    }
    /*
    @Test
    @DirtiesContext
    void saveWishTest(){
        Wish wish = new Wish("ball", 10, "www.bob.dk");
        WishList wishList = oenskeSkyenRepository.getWishListById(1);
        oenskeSkyenRepository.saveWish(wish.getName(), wish.getPrice(), wish.getUrlLink(), wishList.getWishListId());
        Wish actual = oenskeSkyenRepository.getWishById(4);

        String expectedName = "ball";
        double expectedPrice = 10;
        String expectedURL = "www.bob.dk";

        assertEquals(actual.getName(), expectedName);
        assertEquals(actual.getPrice(), expectedPrice);
        assertEquals(actual.getUrlLink(), expectedURL);
        assertEquals(actual.getWishId(), 4);
    }
    */

//    @Test
//    @DirtiesContext
//    void getAllWishListsTest(){
//        //Arrange
//        List<WishList> expectedList = new ArrayList<>();
//
//        expectedList.add(new WishList("Oskar List", "cake"));
//
//
//        //Act
//        List<WishList> actual = oenskeSkyenRepository.getAllWishLists(1);
//
//
//        //Assert
//        assertEquals(expectedList.get(0).getName(), actual.get(0).getName());
//    }


    @Test
    @DirtiesContext
    void getWishByIdTest() {
        // Arrange
        Wish wish = new Wish("toy car", 19.99, "www.toys.com");
        oenskeSkyenRepository.saveWish(wish.getName(), wish.getPrice(), wish.getUrlLink(), 1);

        // Act
        Wish retrievedWish = oenskeSkyenRepository.getWishById(4);

        // Assert
        assertNotNull(retrievedWish);
        assertEquals(wish.getName(), retrievedWish.getName());
        assertEquals(wish.getPrice(), retrievedWish.getPrice());
        assertEquals(wish.getUrlLink(), retrievedWish.getUrlLink());
    }

    @Test
    @DirtiesContext
    void deleteWishTest() {
        // Arrange
        Wish wish = new Wish("book", 12.50, "www.books.com");
        oenskeSkyenRepository.saveWish(wish.getName(), wish.getPrice(), wish.getUrlLink(), 1);
        Wish savedWish = oenskeSkyenRepository.getWishById(4);
        assertNotNull(savedWish);

        // Act
        oenskeSkyenRepository.deleteWish(savedWish.getWishId());

        // Assert
        Wish deletedWish = oenskeSkyenRepository.getWishById(savedWish.getWishId());
        assertNull(deletedWish);
    }

    @Test
    @DirtiesContext
    void editWishTest() {
        // Arrange
        Wish wish = new Wish("laptop", 999.99, "www.laptops.com");
        oenskeSkyenRepository.saveWish(wish.getName(), wish.getPrice(), wish.getUrlLink(), 1);
        Wish savedWish = oenskeSkyenRepository.getWishById(4);
        assertNotNull(savedWish);

        // Act
        savedWish.setName("gaming laptop");
        savedWish.setPrice(1299.99);
        savedWish.setUrlLink("www.gaminglaptops.com");
        oenskeSkyenRepository.editWish(savedWish.getWishId(), savedWish);

        // Assert
        Wish updatedWish = oenskeSkyenRepository.getWishById(savedWish.getWishId());
        assertEquals("gaming laptop", updatedWish.getName());
        assertEquals(1299.99, updatedWish.getPrice());
        assertEquals("www.gaminglaptops.com", updatedWish.getUrlLink());
    }
//
//    @Test
//    @DirtiesContext
//    void getAllWishesTest() {
//        // Arrange
//        WishList wishList = new WishList("test", "test");
//        oenskeSkyenRepository.saveNewWishList(wishList.getName(), wishList.getDescription(), 2);
//        Wish wish1 = new Wish("table", 50.0, "www.furniture.com");
//        Wish wish2 = new Wish("chair", 25.0, "www.furniture.com");
//        oenskeSkyenRepository.saveWish(wish1.getName(), wish1.getPrice(), wish1.getUrlLink(), 3);
//        oenskeSkyenRepository.saveWish(wish2.getName(), wish2.getPrice(), wish2.getUrlLink(), 3);
//
//        // Act
//        List<Wish> allWishes = oenskeSkyenRepository.getAllWishes(3);
//
//        // Assert
//        assertEquals(2, allWishes.size());
//        assertTrue(allWishes.stream().anyMatch(wish -> wish.getName().equals("table")));
//        assertTrue(allWishes.stream().anyMatch(wish -> wish.getName().equals("chair")));
//    }

//    @Test
//    @DirtiesContext
//    void deleteWishListFromDataTest() {
//        // Arrange
//        oenskeSkyenRepository.saveNewWishList("temp list", "to be deleted", 3);
//        WishList savedWishList = oenskeSkyenRepository.getWishListById(3);
//        assertNotNull(savedWishList);
//
//        // Act
//        oenskeSkyenRepository.deleteWishListFromData(savedWishList.getWishListId());
//
//        // Assert
//        WishList deletedWishList = oenskeSkyenRepository.getWishListById(savedWishList.getWishListId());
//        assertNull(deletedWishList);
//    }

    @Test
    @DirtiesContext
    void getWishIdFromWishlistTest() {
        // Arrange
        Wish wish = new Wish("desk", 100.0, "www.desks.com");
        oenskeSkyenRepository.saveWish(wish.getName(), wish.getPrice(), wish.getUrlLink(), 2);
        Wish savedWish = oenskeSkyenRepository.getWishById(4);

        // Act
        Integer retrievedWishId = oenskeSkyenRepository.getWishIdFromWishlist(2, savedWish.getWishId());

        // Assert
        assertEquals(savedWish.getWishId(), retrievedWishId);
    }
}


