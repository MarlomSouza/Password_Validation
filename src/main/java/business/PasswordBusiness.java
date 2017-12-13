package business;

import helpers.CharHelper;
import helpers.MensagensValidacoes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.PasswordModel;
public class PasswordBusiness{
    int quantidadeCaracterMinima = 8;
    private int quantidadeCaracteresMaisculos = 0;
    private int quantidadeCaracteresMinuscula = 0;
    private int quantidadeCaracteresEspecial = 0;
    private int quantidadeCaracteresNumerico = 0;
    private int quantidadeCaracteresRepetido = 0;
    private int quantidadeCaracteresSequencial = 0;
    private int quantidadeCaractereConsecutivoMaiusculo;
    private int quantidadeCaractereConsecutivoMinusculo = 0;
    private int quantidadeCaractereConsecutivoNumerico = 0;

    Pattern padraoMaisculo = Pattern.compile("[A-Z]*");
    Pattern padraoMinusculo = Pattern.compile("[a-z]*");
    Pattern padraoNumerico= Pattern.compile("[0-9]+");
    Pattern padraoCaracterEspecial = Pattern.compile("[\\\\\\\\!\\\"#$%&()*+,./:;<=>?@\\\\[\\\\]^_{|}~]+");

    private String senha;


    private PasswordModel passaword;



    public PasswordBusiness() {
        super();
    }

    public PasswordModel ValidaSenha(String senha){
        passaword= new PasswordModel();
        passaword.setSenha(senha);
        this.senha = senha;
        /*
        if(passaword.getSenha().length() < quantidadeCaracterMinima)
            passaword.setMensagem(MensagensValidacoes.quantidadeMinimaCaracteres);
        else{
            ExisteLetraMaiscula();
            ExisteLetraMinuscula();
            ExisteCaracterEspecial();
            ExisteCaracterNumerico();
            ExisteCaracterRepetido();
        }
        */
        return passaword;
    }

    public void ExisteLetraMaiscula() {
        quantidadeCaracteresMaisculos = 0;

        for (int i = 0; i < senha.length(); i++) {
            if( padraoMaisculo.matcher(CharHelper.CharToSring(senha.charAt(i))).matches())
                quantidadeCaracteresMaisculos++;
        }

        if (quantidadeCaracteresMaisculos == 0)
            passaword.setMensagem(MensagensValidacoes.quantidadeMinimaLetraMaiscula);
    }

    public void ExisteLetraMinuscula() {
        quantidadeCaracteresMinuscula = 0;

        for (int i = 0; i < senha.length(); i++) {
            if( padraoMinusculo.matcher(CharHelper.CharToSring(senha.charAt(i))).matches())
                quantidadeCaracteresMinuscula++;
        }

        if (quantidadeCaracteresMinuscula == 0)
            passaword.setMensagem(MensagensValidacoes.quantidadeMinimaLetraMinuscula);
	}

    public void ExisteCaracterEspecial() {
        quantidadeCaracteresEspecial = 0;

        for (int i = 0; i < senha.length(); i++) {
            if( padraoCaracterEspecial.matcher(CharHelper.CharToSring(senha.charAt(i))).matches())
                quantidadeCaracteresEspecial++;
        }

        if (quantidadeCaracteresEspecial == 0)
            passaword.setMensagem(MensagensValidacoes.quantidadeMinimaCaracterEspecial);
    }

    public void ExisteCaracterNumerico() {
        quantidadeCaracteresNumerico = 0;

        for (int i = 0; i < senha.length(); i++) {
            if( padraoNumerico.matcher(CharHelper.CharToSring(senha.charAt(i))).matches())
                quantidadeCaracteresNumerico++;
        }

        if (quantidadeCaracteresNumerico == 0)
            passaword.setMensagem(MensagensValidacoes.quantidadeMinimaCaracterNumerico);
    }

    public void ExisteCaracterRepetido() {
       quantidadeCaracteresRepetido = 0;

        for (int i = 0; i < senha.length(); i++) {
            for (int j = i; j < senha.length(); j++) {
                if(i == j)
                    continue;

                if(senha.charAt(i) == senha.charAt(j)) {
                    quantidadeCaracteresRepetido++;
                }
            }
        }
        if(quantidadeCaracteresRepetido > 0)
            passaword.setMensagem(MensagensValidacoes.ExisteCaracterRepetido);
    }

    public void ExisteCaracterSequencial() {
        quantidadeCaracteresSequencial = 0;

        for (int i = 0; i < senha.length() - 1; i++) {

          if(CharHelper.IsCharSequence(senha.charAt(i), senha.charAt(i + 1)))
              quantidadeCaracteresSequencial++;
        }

        if (quantidadeCaracteresSequencial > 0)
            passaword.setMensagem(MensagensValidacoes.ExisteCaracterSequencial);
    }

    public void ExisteCaracterConsecutivo() {
        boolean minusculoConsecutivo = false;
        boolean maiusculoConsecutivo = false;
        boolean numeroConsecutivo = false;
        quantidadeCaractereConsecutivoMaiusculo = 0;
        quantidadeCaractereConsecutivoMinusculo = 0;
        quantidadeCaractereConsecutivoNumerico = 0;


        for (int i = 0; i < senha.length()-1; i++) {
            String valorVerificado = ""+ senha.charAt(i) +  senha.charAt(i+1);

            Matcher matchMaisculo = padraoMaisculo.matcher(valorVerificado) ;
            Matcher matchMinusculo = padraoMinusculo.matcher(valorVerificado) ;
            Matcher matchNumerico = padraoNumerico.matcher(valorVerificado) ;


            if(matchMaisculo.matches())
                quantidadeCaractereConsecutivoMaiusculo++;

            if(matchMinusculo.matches())
                quantidadeCaractereConsecutivoMinusculo++;

            if(matchNumerico.matches())
                quantidadeCaractereConsecutivoNumerico++;
        }

        if(quantidadeCaractereConsecutivoMaiusculo > 0)
            passaword.setMensagem(MensagensValidacoes.ExisteCaracterMaisculoConsecutivos);
        if(quantidadeCaractereConsecutivoMinusculo > 0)
            passaword.setMensagem(MensagensValidacoes.ExisteCaracterMinusculosConsecutivos);
        if(quantidadeCaractereConsecutivoNumerico > 0)
            passaword.setMensagem(MensagensValidacoes.ExisteCaracterNumericosConsecutivos);

    }

    public void CalcularTaxaCaracterMaiusculo(){
        int taxaMaiusculo = 2;
        passaword.setForca ((passaword.getSenha().length() - quantidadeCaracteresMaisculos) * taxaMaiusculo);
    }

    public void CalcularTaxaCaracterMinusculo(){
        int taxaMinusculo = 2;
        passaword.setForca ((passaword.getSenha().length() - quantidadeCaracteresMinuscula) * taxaMinusculo);
    }

    public void CalcularTaxaCaracterNumerico(){
        int taxaNumerico = 4;
        passaword.setForca(quantidadeCaracteresNumerico * taxaNumerico);
    }

    public void CalcularTaxaCaracterEspecial() {
        int taxaNumerico = 6;
        passaword.setForca(quantidadeCaracteresEspecial * taxaNumerico);
    }

    public void CalcularTaxaCaracterRepetido() {
        passaword.setForca(-quantidadeCaracteresRepetido);
    }

    public void CalcularTaxaCaracterMaiusculoConsecutivo() {
        passaword.setForca(-quantidadeCaractereConsecutivoMaiusculo);
    }

    public void CalcularTaxaCaracterMinusculoConsecutivo() {
        passaword.setForca(-quantidadeCaractereConsecutivoMinusculo);
    }

    public void CalcularTaxaCaracterNumericoConsecutivo() {
        passaword.setForca(-quantidadeCaractereConsecutivoNumerico);
    }
}