package business;

import helpers.CharHelper;
import helpers.MensagensValidacoes;
import java.util.regex.Pattern;

import ch.qos.logback.core.joran.conditional.ElseAction;
import model.PasswordModel;


public class PasswordBusiness{
    private int quantidadeCaracterMinima = 8;
    private int quantidadeCaracteresMaisculos = 0;
    private int quantidadeCaracteresMinuscula = 0;
    private int quantidadeCaracteresEspecial = 0;
    private int quantidadeCaracteresNumerico = 0;
    private int quantidadeCaracteresRepetido = 0;
    private int quantidadeCaracteresSequencial = 0;
    private int quantidadeCaracteresESpecialSequencial;
    private int quantidadeCaracteresNumericoSequencial;
    private int quantidadeCaracterConsecutivoMaiusculo;
    private int quantidadeCaracterConsecutivoMinusculo = 0;
    private int quantidadeCaracterConsecutivoNumerico = 0;
    

    Pattern padraoMaiusculo = Pattern.compile("[A-Z]*");
    Pattern padraoMinusculo = Pattern.compile("[a-z]*");
    Pattern padraoNumerico= Pattern.compile("[0-9]+");
    Pattern padraoCaracterEspecial = Pattern.compile("[\\\\\\\\!\\\"#$%&()*+,./:;<=>?@\\\\[\\\\]^_{|}~]+");

    private String senha;
    private PasswordModel model;
	
    public PasswordBusiness() {
        super();
    }

    public PasswordModel ValidaSenha(String senha){
        model = new PasswordModel();
        this.senha = senha;
        model.setSenha(senha);
    
        if(model.getSenha().length() < quantidadeCaracterMinima)
            model.setMensagem(MensagensValidacoes.quantidadeMinimaCaracteres);
        else{
            ExisteCaracterMaiusculo();
            ExisteCaracterMinusculo();
            ExisteCaracterEspecial();
            ExisteCaracterNumerico();
            ExisteCaracterRepetido();
            ExisteCaracterSequencial();
            ExisteCaracterConsecutivo();

        }
        
        return model;
    }

    public void ExisteCaracterMaiusculo() {
        quantidadeCaracteresMaisculos = 0;

        for (int i = 0; i < senha.length(); i++) {
            if( padraoMaiusculo.matcher(CharHelper.CharToSring(senha.charAt(i))).matches())
                quantidadeCaracteresMaisculos++;
        }

        if (quantidadeCaracteresMaisculos == 0)
            model.setMensagem(MensagensValidacoes.quantidadeMinimaCaracterMaisculo);
    }

    public void ExisteCaracterMinusculo() {
        quantidadeCaracteresMinuscula = 0;

        for (int i = 0; i < senha.length(); i++) {
            if( padraoMinusculo.matcher(CharHelper.CharToSring(senha.charAt(i))).matches())
                quantidadeCaracteresMinuscula++;
        }

        if (quantidadeCaracteresMinuscula == 0)
            model.setMensagem(MensagensValidacoes.quantidadeMinimaCaracterMinusculo);
	}

    public void ExisteCaracterEspecial() {
        quantidadeCaracteresEspecial = 0;

        for (int i = 0; i < senha.length(); i++) {
            if( padraoCaracterEspecial.matcher(CharHelper.CharToSring(senha.charAt(i))).matches())
                quantidadeCaracteresEspecial++;
        }

        if (quantidadeCaracteresEspecial == 0)
            model.setMensagem(MensagensValidacoes.quantidadeMinimaCaracterEspecial);
    }

    public void ExisteCaracterNumerico() {
        quantidadeCaracteresNumerico = 0;

        for (int i = 0; i < senha.length(); i++) {
            if( padraoNumerico.matcher(CharHelper.CharToSring(senha.charAt(i))).matches())
                quantidadeCaracteresNumerico++;
        }

        if (quantidadeCaracteresNumerico == 0)
            model.setMensagem(MensagensValidacoes.quantidadeMinimaCaracterNumerico);
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
            model.setMensagem(MensagensValidacoes.ExisteCaracterRepetido);
    }

    public void ExisteCaracterSequencial() {
        quantidadeCaracteresNumericoSequencial = 0;
        quantidadeCaracteresESpecialSequencial = 0;
        quantidadeCaracteresSequencial = 0;
        String tempSenha = senha.toUpperCase();


        for (int i = 0; i < senha.length() - 2; i++) {
            if(CharHelper.IsCharSequence(tempSenha.charAt(i), tempSenha.charAt(i + 1), tempSenha.charAt(i + 2))){
                String valorVerificado = ""+ tempSenha.charAt(i) +  tempSenha.charAt(i + 1) +  tempSenha.charAt(i + 2);    
                if(padraoNumerico.matcher(valorVerificado).matches())
                    quantidadeCaracteresNumericoSequencial++;
                else if(padraoCaracterEspecial.matcher(valorVerificado).matches())
                    quantidadeCaracteresESpecialSequencial++;
                else
                    quantidadeCaracteresSequencial++;
            }
        }

        if (quantidadeCaracteresSequencial > 0)
            model.setMensagem(MensagensValidacoes.ExisteCaracterSequencial);
            
        if (quantidadeCaracteresNumericoSequencial > 0)
            model.setMensagem(MensagensValidacoes.ExisteCaracterNumericoSequencial);
        
        if (quantidadeCaracteresESpecialSequencial > 0)
            model.setMensagem(MensagensValidacoes.ExisteCaracterEspecialSequencial);
    }

    public void ExisteCaracterConsecutivo() {
        quantidadeCaracterConsecutivoMaiusculo = 0;
        quantidadeCaracterConsecutivoMinusculo = 0;
        quantidadeCaracterConsecutivoNumerico = 0;

        for (int i = 0; i < senha.length()-1; i++) {
            String valorVerificado = ""+ senha.charAt(i) +  senha.charAt(i + 1);

            if(padraoMaiusculo.matcher(valorVerificado).matches())
                quantidadeCaracterConsecutivoMaiusculo++;

            if(padraoMinusculo.matcher(valorVerificado).matches())
                quantidadeCaracterConsecutivoMinusculo++;

            if( padraoNumerico.matcher(valorVerificado).matches())
                quantidadeCaracterConsecutivoNumerico++;
        }

        if(quantidadeCaracterConsecutivoMaiusculo > 0)
            model.setMensagem(MensagensValidacoes.ExisteCaracterMaiusculoConsecutivos);
        if(quantidadeCaracterConsecutivoMinusculo > 0)
            model.setMensagem(MensagensValidacoes.ExisteCaracterMinusculosConsecutivos);
        if(quantidadeCaracterConsecutivoNumerico > 0)
            model.setMensagem(MensagensValidacoes.ExisteCaracterNumericosConsecutivos);

    }

    public void CalcularTaxaCaracterMaiusculo(){
        int taxaMaiusculo = 2;
        model.setForca ((model.getSenha().length() - quantidadeCaracteresMaisculos) * taxaMaiusculo);
    }

    public void CalcularTaxaCaracterMinusculo(){
        int taxaMinusculo = 2;
        model.setForca ((model.getSenha().length() - quantidadeCaracteresMinuscula) * taxaMinusculo);
    }

    public void CalcularTaxaCaracterNumerico(){
        int taxaNumerico = 4;
        model.setForca(quantidadeCaracteresNumerico * taxaNumerico);
    }

    public void CalcularTaxaCaracterEspecial() {
        int taxaNumerico = 6;
        model.setForca(quantidadeCaracteresEspecial * taxaNumerico);
    }

    public void CalcularTaxaCaracterRepetido() {
        model.setForca(-quantidadeCaracteresRepetido);
    }

    public void CalcularTaxaCaracterMaiusculoConsecutivo() {
        model.setForca(-quantidadeCaracterConsecutivoMaiusculo);
    }

    public void CalcularTaxaCaracterMinusculoConsecutivo() {
        model.setForca(-quantidadeCaracterConsecutivoMinusculo);
    }

    public void CalcularTaxaCaracterNumericoConsecutivo() {
        model.setForca(-quantidadeCaracterConsecutivoNumerico);
    }
  
    public void CalcularTaxaCaracterNumericoSequencial() {
        int taxaCaracterNumericaSequencial = 3;
        model.setForca(-quantidadeCaracteresNumericoSequencial * taxaCaracterNumericaSequencial) ;
    }

    public void CalcularTaxaCaracterEspecialSequencial() {
        int taxaCaracterEspecialSequencial = 3;
        model.setForca(-quantidadeCaracteresESpecialSequencial * taxaCaracterEspecialSequencial) ;
    }

    public void CalcularTaxaCaracterSequencial() {
        int taxaCaracterSequencial = 3;
        model.setForca(-quantidadeCaracteresESpecialSequencial * taxaCaracterSequencial) ;
    }
}