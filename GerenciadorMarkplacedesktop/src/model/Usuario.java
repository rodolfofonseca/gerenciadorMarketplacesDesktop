package model;

/**
 * @author RodolfoFonseca
 */
public class Usuario {
   private int codigoUsuario;
   private Empresa empresa;
   private String nomeUsuario;
   private String emailUsuario;
   private String funcaoUsuario;
   private String senhaUsuario;
   private boolean statusUsuario;
   private String avatarUsuario;

    /**
     * @return the codigoUsuario
     */
    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    /**
     * @param codigoUsuario the codigoUsuario to set
     */
    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    /**
     * @return the empresa
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the nomeUsuario
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * @param nomeUsuario the nomeUsuario to set
     */
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    /**
     * @return the emailUsuario
     */
    public String getEmailUsuario() {
        return emailUsuario;
    }

    /**
     * @param emailUsuario the emailUsuario to set
     */
    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    /**
     * @return the funcaoUsuario
     */
    public String getFuncaoUsuario() {
        return funcaoUsuario;
    }

    /**
     * @param funcaoUsuario the funcaoUsuario to set
     */
    public void setFuncaoUsuario(String funcaoUsuario) {
        this.funcaoUsuario = funcaoUsuario;
    }

    /**
     * @return the senhaUsuario
     */
    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    /**
     * @param senhaUsuario the senhaUsuario to set
     */
    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    /**
     * @return the statusUsuario
     */
    public boolean isStatusUsuario() {
        return statusUsuario;
    }

    /**
     * @param statusUsuario the statusUsuario to set
     */
    public void setStatusUsuario(boolean statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    /**
     * @return the avatarUsuario
     */
    public String getAvatarUsuario() {
        return avatarUsuario;
    }

    /**
     * @param avatarUsuario the avatarUsuario to set
     */
    public void setAvatarUsuario(String avatarUsuario) {
        this.avatarUsuario = avatarUsuario;
    }
}
