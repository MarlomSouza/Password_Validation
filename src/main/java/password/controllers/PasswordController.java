package password.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import business.PasswordBusiness;
import model.PasswordModel;

@RestController
@RequestMapping("/api")
public class PasswordController {

    private PasswordBusiness passwordBusiness;

    public PasswordController() {
        super();
        passwordBusiness = new PasswordBusiness();
    }

    @RequestMapping("/passwordvalidation")
    public PasswordModel PasswordValidation(@RequestParam(value="senha") String senha) {
        return passwordBusiness.ValidaSenha(senha);
    }
}
