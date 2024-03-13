package Hotel.GIL.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hotel.GIL.models.UserEntity;
import Hotel.GIL.Repositories.UserEntityRepository;
import Hotel.GIL.Services.UserService;

@Controller
public class UserEntityController {
    

    @Autowired
    private  UserEntityRepository  UserEntityRepository;
    private  UserService  UserEntityService;

         @PostMapping("/create_user")
    // @PreAuthorize("hasAuthority('admin:create')")
    // @RequestMapping(value = "/create_enseignant", method =RequestMethod.POST)
    // @Hidden
    public String  CreatUser(
                                @RequestParam("nom") String userName,
                                @RequestParam("password") String password,
                                @RequestParam("ncni") String ncni,
                                @RequestParam("email") String email,
                                @RequestParam("number") int number)
                                {
      UserEntityService.saveUserEntity(userName, password, ncni,email, number);
        return "redirect:/login";
    }


    @GetMapping("/user")
    public String CreateUsrt()
    {
        return "/user.html";
    }

}
