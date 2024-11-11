package Oenskeskyen.Service;

import Oenskeskyen.Model.User;
import Oenskeskyen.Model.Wish;
import Oenskeskyen.Model.WishList;
import Oenskeskyen.Repository.OenskeSkyenRepository;
import Oenskeskyen.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository repository){
        this.userRepository = repository;
    }




    /// **************************** Add and modify database functions ******************** ///
    public User getUser(String mail){
        return userRepository.getUser(mail);
    }

    public void saveUserCostumer(User obj){
        userRepository.saveNewUser(obj.getFullName(), obj.getMail(), obj.getPassWord());
    }

    public void saveWishList(WishList obj, int userID){
        userRepository.saveNewWishList(obj.getName(), obj.getDescription(), userID);
    }

    public void saveWish(Wish obj, int wishListID){
        userRepository.saveWish(obj.getName(), obj.getPrice(), obj.getUrlLink(), wishListID);
    }

    public List<WishList> getAllWishLists(int userID){
        return userRepository.getAllWishLists(userID);
    }

    public WishList getWishListById(int wishListID){
        return userRepository.getWishListById(wishListID);
    }

    public List<Wish> getAllWishes(int wishListID){
        return userRepository.getAllWishes(wishListID);
    }

    public void deleteUser(int id){
        userRepository.deleteUserFromData(id);
    }
}
