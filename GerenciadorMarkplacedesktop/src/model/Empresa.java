package model;

/**
 * @author RodolfoFonseca
 */
public class Empresa {
    private int codigoEmpresa;
    private String nomeEmpresa;
    private String cnpjEmpresa;

    /**
     * @return the codigoEmpresa
     */
    public int getCodigoEmpresa() {
        return codigoEmpresa;
    }

    /**
     * @param codigoEmpresa the codigoEmpresa to set
     */
    public void setCodigoEmpresa(int codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    /**
     * @return the nomeEmpresa
     */
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    /**
     * @param nomeEmpresa the nomeEmpresa to set
     */
    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    /**
     * @return the cnpjEmpresa
     */
    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    /**
     * @param cnpjEmpresa the cnpjEmpresa to set
     */
    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }
    
}
