package password.controllers;

import java.security.KeyStore.TrustedCertificateEntry;

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
    }

    @RequestMapping("/passwordvalidation")
    public PasswordModel PasswordValidation(@RequestParam(name="senha", defaultValue="") String senha) {
        passwordBusiness = new PasswordBusiness();
        return passwordBusiness.ValidaSenha(senha);
    }
}
