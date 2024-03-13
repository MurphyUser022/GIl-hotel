package Hotel.GIL.Controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @GetMapping("/access-denied")
    public String accessDenied(Model model,
                                   HttpServletRequest request, RedirectAttributes attributes ) {

                       // Obtenez l'URL de référence (la page précédente)
        String referer = request.getHeader("referer");

        // Ajoutez un attribut pour conserver l'URL de référence lors de la redirection
        attributes.addFlashAttribute("referer", referer);

        // Redirigez l'utilisateur vers la page précédente

        model.addAttribute("retour", referer);
        return "access-denied.html";
    }

      @GetMapping("/error-404")
    public String handle404Error() {
        // Gérez ici la redirection vers votre page d'erreur 404 personnalisée
        return "error-404.html";
    }

   
}