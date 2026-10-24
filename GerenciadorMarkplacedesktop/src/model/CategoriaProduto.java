package model;
import java.sql.Timestamp;
/**
 * Classe que contém todas as informações da categoria do produto
 * @author RodolfoFonseca
 */
public class CategoriaProduto {
    private int codigoCategoriaProduto;
    private Empresa empresa;
    private String descricaoCategoriaProduto;
    private boolean statusCategoriaProduto;
    private Timestamp dataCadastro;
    private String categoriaProdutoPesquisa;

    /**
     * @return the codigoCategoriaProduto
     */
    public int getCodigoCategoriaProduto() {
        return codigoCategoriaProduto;
    }

    /**
     * @param codigoCategoriaProduto the codigoCategoriaProduto to set
     */
    public void setCodigoCategoriaProduto(int codigoCategoriaProduto) {
        this.codigoCategoriaProduto = codigoCategoriaProduto;
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
     * @return the descricaoCategoriaProduto
     */
    public String getDescricaoCategoriaProduto() {
        return descricaoCategoriaProduto;
    }

    /**
     * @param descricaoCategoriaProduto the descricaoCategoriaProduto to set
     */
    public void setDescricaoCategoriaProduto(String descricaoCategoriaProduto) {
        this.descricaoCategoriaProduto = descricaoCategoriaProduto;
    }

    /**
     * @return the statusCategoriaProduto
     */
    public boolean isStatusCategoriaProduto() {
        return statusCategoriaProduto;
    }

    /**
     * @param statusCategoriaProduto the statusCategoriaProduto to set
     */
    public void setStatusCategoriaProduto(boolean statusCategoriaProduto) {
        this.statusCategoriaProduto = statusCategoriaProduto;
    }

    /**
     * @return the dataCadastro
     */
    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return the categoriaProdutoPesquisa
     */
    public String getCategoriaProdutoPesquisa() {
        return categoriaProdutoPesquisa;
    }

    /**
     * @param categoriaProdutoPesquisa the categoriaProdutoPesquisa to set
     */
    public void setCategoriaProdutoPesquisa(String categoriaProdutoPesquisa) {
        this.categoriaProdutoPesquisa = categoriaProdutoPesquisa;
    }
}
