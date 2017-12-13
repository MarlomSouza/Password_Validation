package business;

import helpers.MensagensValidacoes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.PasswordModel;

public class PasswordBusiness{
    int quantidadeCaracterMinima = 8;
    private int quantidadeCaracteresMaisculos = 0;
    private int quantidadeCaracteresMinuscula = 0;

    private PasswordModel passaword;

    public PasswordBusiness() {
        super();
    }

    public PasswordModel ValidaSenha(String senha){
        passaword= new PasswordModel();
        passaword.setSenha(senha);
        if(passaword.getSenha().length() < quantidadeCaracterMinima)
            passaword.setMensagem(MensagensValidacoes.quantidadeMinimaCaracteres);
        else{
            ExisteLetraMaiscula();
            ExisteLetraMinuscula();
        }
        return passaword;
    }

    private void ExisteLetraMaiscula() {
        Matcher match;
        Pattern padrao;
        padrao = Pattern.compile("[A-Z]");
        match = padrao.matcher(passaword.getSenha());
        while(match.matches()){
            quantidadeCaracteresMaisculos++;
        }

        if (quantidadeCaracteresMaisculos == 0) {
            passaword.setMensagem(MensagensValidacoes.quantidadeMinimaLetraMaiscula);
        }
    }
    
    private void ExisteLetraMinuscula() {
        Matcher match;
        Pattern padrao;
        padrao = Pattern.compile("[a-z]");
        match = padrao.matcher(passaword.getSenha());
        while(match.matches())
            quantidadeCaracteresMinuscula++;

        if (quantidadeCaracteresMinuscula == 0) {
            passaword.setMensagem(MensagensValidacoes.quantidadeMinimaLetraMinuscula);
        }
	}
}