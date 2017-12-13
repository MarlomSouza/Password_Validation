package business;

import Helpers.MensagensValidacoes;
import model.PasswordModel;

public class PasswordBusiness{

    int quantidadeCaracterMinima = 8;
    PasswordModel passaword;

    public PasswordBusiness() {
        super();
        passaword= new PasswordModel();
    }

    public PasswordModel ValidaSenha(String senha){
        passaword.setSenha(senha);
        QuantidadeMinimaCaracteres();
        return passaword;
    }

	private void QuantidadeMinimaCaracteres() {
        if(passaword.getSenha().length() < quantidadeCaracterMinima)
            passaword.setMensagem(MensagensValidacoes.quantidadeMinimaCaracteres);
	}
}