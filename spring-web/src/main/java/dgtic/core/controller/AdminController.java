// AdminController.java
package dgtic.core.controller;

import dgtic.core.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/inicio")
    public String inicioAdmin(HttpSession session, Model model) {
        Usuario admin = (Usuario) session.getAttribute("usuario");

        if (admin == null || !"ADMINI".equals(admin.getTipoUsuario())) {
            return "redirect:/login";
        }

        model.addAttribute("titulo", "Panel de Administración");
        model.addAttribute("admin", admin);
        return "admin/inicio_admin";
    }
}
