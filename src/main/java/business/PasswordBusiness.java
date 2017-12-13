package business;

import helpers.MensagensValidacoes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.PasswordModel;

public class PasswordBusiness{
    int quantidadeCaracterMinima = 8;

    private PasswordModel passaword;
    private Matcher match;
    private Pattern padrao;

    public PasswordBusiness() {
        super();
        passaword= new PasswordModel();
    }

    public PasswordModel ValidaSenha(String senha){
        passaword.setSenha(senha);
        QuantidadeMinimaCaracteres();
        ExisteLetraMaiscula();

        return passaword;
    }

	private void QuantidadeMinimaCaracteres() {
        if(passaword.getSenha().length() < quantidadeCaracterMinima)
            passaword.setMensagem(MensagensValidacoes.quantidadeMinimaCaracteres);
    }
    
    private void ExisteLetraMaiscula() {
        padrao = Pattern.compile("[A-Z]");
        match = padrao.matcher(passaword.getSenha());
        if(match.matches() == false)
            passaword.setMensagem(MensagensValidacoes.quantidadeMinimaLetraMaiscula);
	}
}