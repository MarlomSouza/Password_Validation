package business;

import java.util.regex.Pattern;

import helpers.BooleanHelper;
import helpers.CharHelper;
import helpers.MensagensValidacoes;
import model.PasswordModel;


public class PasswordBusiness{
    private int quantidadeCaracterMinima = 8;
    private int quantidadeCaracteresMaiusculos = 0;
    private int quantidadeCaracteresMinusculo = 0;
    private int quantidadeCaracteresEspecial = 0;
    private int quantidadeCaracteresNumerico = 0;
    private int quantidadeCaracterNumericoOuEspecialNoMeio = 0;
    private int quantidadeCaracteresRepetido = 0;
    private int quantidadeCaracteresSequencial = 0;
    private int quantidadeCaracteresEspecialSequencial= 0;
    private int quantidadeCaracteresNumericoSequencial= 0;
    private int quantidadeCaracterConsecutivoMaiusculo= 0;
    private int quantidadeCaracterConsecutivoMinusculo = 0;
    private int quantidadeCaracterConsecutivoNumerico = 0;
    private int quantidadeRequerimentoAtendido = 0;
    

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
    
        ExisteQuantidadeMinimaDeCaracter();
        ExisteCaracterMaiusculo();
        ExisteCaracterMinusculo();
        ExisteCaracterNumerico();
        ExisteCaracterEspecial();
        ExisteCaracterNumericoOuEspecialNoMeio();
        ExisteCaracterRepetido();
        ExisteCaracterSequencial();
        ExisteCaracterConsecutivo();
        
        CalcularTaxaQuantidadeCaracter();
        CalcularTaxaCaracterMaiusculo();
        CalcularTaxaCaracterMinusculo();
        CalcularTaxaCaracterNumerico();
        CalcularTaxaCaracterEspecial();
        CalcularTaxaCaracterEspecialOuNumericoNoMeio();
        CalcularTodasRequerimentosAtendidos();
        CalcularApenasCaracter();
        CalcularApenasCaracterNumerico();
        CalcularTaxaCaracterRepetido();
        CalcularTaxaCaracterSequencial();
        CalcularTaxaCaracterNumericoSequencial();
        CalcularTaxaCaracterEspecialSequencial();
        CalcularTaxaCaracterMaiusculoConsecutivo();
        CalcularTaxaCaracterMinusculoConsecutivo();
        CalcularTaxaCaracterNumericoConsecutivo();
        return model;
    }
    private void ExisteQuantidadeMinimaDeCaracter(){
        if(model.getSenha().length() < quantidadeCaracterMinima)
            model.setMensagem(MensagensValidacoes.quantidadeMinimaCaracteres);

    }

    private void ExisteCaracterMaiusculo() {
        quantidadeCaracteresMaiusculos = 0;

        for (int i = 0; i < senha.length(); i++) {
            if( padraoMaiusculo.matcher(CharHelper.CharToSring(senha.charAt(i))).matches())
                quantidadeCaracteresMaiusculos++;
        }

        if (quantidadeCaracteresMaiusculos == 0)
            model.setMensagem(MensagensValidacoes.quantidadeMinimaCaracterMaisculo);
    }

    private void ExisteCaracterMinusculo() {
        quantidadeCaracteresMinusculo = 0;

        for (int i = 0; i < senha.length(); i++) {
            if( padraoMinusculo.matcher(CharHelper.CharToSring(senha.charAt(i))).matches())
                quantidadeCaracteresMinusculo++;
        }

        if (quantidadeCaracteresMinusculo == 0)
            model.setMensagem(MensagensValidacoes.quantidadeMinimaCaracterMinusculo);
	}

    private void ExisteCaracterEspecial() {
        quantidadeCaracteresEspecial = 0;

        for (int i = 0; i < senha.length(); i++) {
            if( padraoCaracterEspecial.matcher(CharHelper.CharToSring(senha.charAt(i))).matches())
                quantidadeCaracteresEspecial++;
        }

        if (quantidadeCaracteresEspecial == 0)
            model.setMensagem(MensagensValidacoes.quantidadeMinimaCaracterEspecial);
    }

    private void ExisteCaracterNumerico() {
        quantidadeCaracteresNumerico = 0;

        for (int i = 0; i < senha.length(); i++) {
            if( padraoNumerico.matcher(CharHelper.CharToSring(senha.charAt(i))).matches())
                quantidadeCaracteresNumerico++;
        }

        if (quantidadeCaracteresNumerico == 0)
            model.setMensagem(MensagensValidacoes.quantidadeMinimaCaracterNumerico);
    }

    private void ExisteCaracterNumericoOuEspecialNoMeio(){
        quantidadeCaracterNumericoOuEspecialNoMeio = 0;
        for (int i = 1; i < senha.length()-1; i++) {
            Boolean caracterNumerico = padraoNumerico.matcher(CharHelper.CharToSring(senha.charAt(i)) ).matches();
            Boolean caracterEspecial = padraoCaracterEspecial.matcher(CharHelper.CharToSring(senha.charAt(i)) ).matches();
            if(caracterNumerico||caracterEspecial)
                quantidadeCaracterNumericoOuEspecialNoMeio++;
        }
    }

    private void ExisteCaracterRepetido() {
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

    private void ExisteCaracterSequencial() {
        quantidadeCaracteresNumericoSequencial = 0;
        quantidadeCaracteresEspecialSequencial = 0;
        quantidadeCaracteresSequencial = 0;
        String tempSenha = senha.toUpperCase();


        for (int i = 0; i < senha.length() - 2; i++) {
            if(CharHelper.IsCharSequence(tempSenha.charAt(i), tempSenha.charAt(i + 1), tempSenha.charAt(i + 2))){
                String valorVerificado = ""+ tempSenha.charAt(i) +  tempSenha.charAt(i + 1) +  tempSenha.charAt(i + 2);    
                if(padraoNumerico.matcher(valorVerificado).matches())
                    quantidadeCaracteresNumericoSequencial++;
                else if(padraoCaracterEspecial.matcher(valorVerificado).matches())
                    quantidadeCaracteresEspecialSequencial++;
                else
                    quantidadeCaracteresSequencial++;
            }
        }

        if (quantidadeCaracteresSequencial > 0)
            model.setMensagem(MensagensValidacoes.ExisteCaracterSequencial);
            
        if (quantidadeCaracteresNumericoSequencial > 0)
            model.setMensagem(MensagensValidacoes.ExisteCaracterNumericoSequencial);
        
        if (quantidadeCaracteresEspecialSequencial > 0)
            model.setMensagem(MensagensValidacoes.ExisteCaracterEspecialSequencial);
    }

    private void ExisteCaracterConsecutivo() {
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

    private void CalcularTaxaQuantidadeCaracter(){
        int taxaQuantidadeCaracter = 4;
        model.setForca(model.getSenha().length() * taxaQuantidadeCaracter);
    }

    private void CalcularTaxaCaracterMaiusculo(){
        int taxaMaiusculo = 2;
        if(quantidadeCaracteresMaiusculos > 0)
            model.setForca ((model.getSenha().length() - quantidadeCaracteresMaiusculos) * taxaMaiusculo);
    }

    private void CalcularTaxaCaracterMinusculo(){
        int taxaMinusculo = 2;
        if(quantidadeCaracteresMinusculo > 0)
            model.setForca ((model.getSenha().length() - quantidadeCaracteresMinusculo) * taxaMinusculo);
    }

    private void CalcularTaxaCaracterNumerico(){
        int taxaNumerico = 4;
        model.setForca(quantidadeCaracteresNumerico * taxaNumerico);
    }

    private void CalcularTaxaCaracterEspecial() {
        int taxaNumerico = 6;
        model.setForca(quantidadeCaracteresEspecial * taxaNumerico);
    }

    private void CalcularTaxaCaracterEspecialOuNumericoNoMeio() {
        int taxaCaracterEspecialOuNumericoNoMeio = 2;
        model.setForca(quantidadeCaracterNumericoOuEspecialNoMeio * taxaCaracterEspecialOuNumericoNoMeio);
    }

    private void CalcularTodasRequerimentosAtendidos(){
        quantidadeRequerimentoAtendido =0;
        int taxaRequerimentoAtendido = 2;
        boolean possuiQuantidadeMinimaCaracter = quantidadeCaracterMinima > 0;
        boolean possuiCaracterMaisculos = quantidadeCaracteresMaiusculos > 0;
        boolean possuiCaracterMinusculo = quantidadeCaracteresMinusculo > 0;
        boolean possuiCaracterNumerico = quantidadeCaracteresNumerico > 0;
        boolean possuiCaracterEspecial = quantidadeCaracteresEspecial > 0;

        boolean possuiQuatroRequisitosBasicos = BooleanHelper.MinPositive(3,possuiCaracterMaisculos,possuiCaracterMinusculo, possuiCaracterNumerico,possuiCaracterEspecial);

        if(possuiQuantidadeMinimaCaracter == false || possuiQuatroRequisitosBasicos == false)
            return;

        if(possuiQuantidadeMinimaCaracter)
            quantidadeRequerimentoAtendido++;
        if(possuiCaracterMaisculos)
            quantidadeRequerimentoAtendido++;
        if(possuiCaracterMinusculo)
            quantidadeRequerimentoAtendido++;
        if(possuiCaracterNumerico)
            quantidadeRequerimentoAtendido++;
        if(quantidadeCaracteresEspecial > 0)
            quantidadeRequerimentoAtendido++;
        
        
        if(possuiQuantidadeMinimaCaracter)
        model.setForca(quantidadeRequerimentoAtendido * taxaRequerimentoAtendido) ;

    }

    private void CalcularApenasCaracter(){
        int totalCaracter = quantidadeCaracteresMaiusculos + quantidadeCaracteresMinusculo;
        if( totalCaracter == model.getSenha().length())
            model.setForca(-totalCaracter);

    }


    private void CalcularApenasCaracterNumerico(){
    
        if( quantidadeCaracteresNumerico == model.getSenha().length())
            model.setForca(-quantidadeCaracteresNumerico);
    }

    private void CalcularTaxaCaracterRepetido() {
        model.setForca(-quantidadeCaracteresRepetido);
    }

    private void CalcularTaxaCaracterMaiusculoConsecutivo() {
        int taxaCaracterMaiusculoConsecutico = 2;
        model.setForca(-quantidadeCaracterConsecutivoMaiusculo * taxaCaracterMaiusculoConsecutico);
    }

    private void CalcularTaxaCaracterMinusculoConsecutivo() {
        int taxaCaracterMinusculoConsecutico = 2;
        model.setForca(-quantidadeCaracterConsecutivoMinusculo * taxaCaracterMinusculoConsecutico);
    }

    private void CalcularTaxaCaracterNumericoConsecutivo() {
        int taxaCaracterNumercoConsecutico = 2;
        model.setForca(-quantidadeCaracterConsecutivoNumerico * taxaCaracterNumercoConsecutico);
    }
  
    private void CalcularTaxaCaracterNumericoSequencial() {
        int taxaCaracterNumericaSequencial = 3;
        model.setForca(-quantidadeCaracteresNumericoSequencial * taxaCaracterNumericaSequencial) ;
    }

    private void CalcularTaxaCaracterEspecialSequencial() {
        int taxaCaracterEspecialSequencial = 3;
        model.setForca(-quantidadeCaracteresEspecialSequencial * taxaCaracterEspecialSequencial) ;
    }

    private void CalcularTaxaCaracterSequencial() {
        int taxaCaracterSequencial = 3;
        model.setForca(-quantidadeCaracteresSequencial * taxaCaracterSequencial) ;
    }
}