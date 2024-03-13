package Hotel.GIL.Services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Hotel.GIL.models.UserEntity;
import Hotel.GIL.Repositories.UserEntityRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;

import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.Optional;
import lombok.Data;

@Service
public class UserService {

    
    @Autowired
    private UserEntityRepository UserEntityRepository;



 
    
        public UserEntity saveUserEntity(String username, String password, String ncni,String mail,int number)
    {
        UserEntity pers = new UserEntity();
    
      pers.setEmail(mail);
      pers.setUserName(username);
      pers.setPassword(password);
      pers.setNcni(ncni);
      pers.setNumber(number);
        UserEntityRepository.save(pers);
        return pers;
        }



     public void deleteUserEntity (int id){
        try {
            UserEntityRepository.deleteById(id);
        } catch (Exception e){

        }
    }

       public void updateinformation(String username, String password, String nom, String ncni,int number)
       {
           UserEntity pers = new UserEntity();
       
         
         pers.setUserName(username);
         pers.setPassword(password);
         pers.setNcni(ncni);
         pers.setEmail(ncni);
         pers.setNumber(number);
           UserEntityRepository.save(pers);
       
           }


public UserEntity SearchUserEntity(int id)
    {
        UserEntity pers = new UserEntity();
        pers = UserEntityRepository.findById(id).get();
        return pers;
    }


}
