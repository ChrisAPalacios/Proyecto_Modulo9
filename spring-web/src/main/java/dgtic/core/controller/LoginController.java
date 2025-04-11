package dgtic.core.controller;

import dgtic.core.model.Usuario;
import dgtic.core.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("correo", "");
        return "principal/login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String correo,
                                @RequestParam String password,
                                Model model,
                                HttpSession session) {

        Usuario usuario = usuarioService.autenticarUsuario(correo, password);

        if (usuario == null) {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            model.addAttribute("correo", correo);
            return "principal/login";
        }

        session.setAttribute("usuario", usuario);
        if ("ADMINI".equalsIgnoreCase(usuario.getTipoUsuario())) {
            return "redirect:/admin/inicio";  // Vista de administrador
        } else {
            return "redirect:/cliente/inicio";  // Vista de cliente
        }
    }


}
