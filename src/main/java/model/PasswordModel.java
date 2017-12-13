package model;

import java.util.ArrayList;
import java.util.List;

public class PasswordModel{

    private String senha;
    private List<String> mensagem;
    private int forca;

    public PasswordModel() {
        super();
        mensagem = new ArrayList<String>();
    }

  /**
   * @return the senha
   */
  public String getSenha() {
      return senha;
  }
  /**
   * @param senha the senha to set
   */
  public void setSenha(String senha) {
      this.senha = senha;
  }

  /**
   * @return the forca
   */
  public int getForca() {
      return forca;
  }
  /**
   * @return the mensagem
   */
  public List<String> getMensagem() {
      return mensagem;
  }
  /**
   * @param forca the forca to set
   */
  public void setForca(int forca) {
      this.forca = forca;
  }
  /**
   * @param mensagem the mensagem to set
   */
  public void setMensagem(String mensagem) {
      this.mensagem.add(mensagem);
  }


}