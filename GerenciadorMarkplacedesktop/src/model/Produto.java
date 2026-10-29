package model;
import java.sql.Timestamp;
/**
 * Classe que contém todos os dados do cadastro de produtos
 * @author RodolfoFonseca
 */
public class Produto {
    private int codigoProduto;
    private Empresa empresa;
    private CategoriaProduto categoriaProduto;
    private FotoProduto fotoProduto;
    private String skuProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private String statusProdutoPesquisa;
    private float valorCompra;
    private float valorVenda;
    private boolean statusProduto;
    private boolean tipoProduto;
    private Timestamp dataCadastro;
    private Timestamp dataAlteracao;

    /**
     * @return the codigoProduto
     */
    public int getCodigoProduto() {
        return codigoProduto;
    }

    /**
     * @param codigoProduto the codigoProduto to set
     */
    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
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
     * @return the categoriaProduto
     */
    public CategoriaProduto getCategoriaProduto() {
        return categoriaProduto;
    }

    /**
     * @param categoriaProduto the categoriaProduto to set
     */
    public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    /**
     * @return the skuProduto
     */
    public String getSkuProduto() {
        return skuProduto;
    }

    /**
     * @param skuProduto the skuProduto to set
     */
    public void setSkuProduto(String skuProduto) {
        this.skuProduto = skuProduto;
    }

    /**
     * @return the nomeProduto
     */
    public String getNomeProduto() {
        return nomeProduto;
    }

    /**
     * @param nomeProduto the nomeProduto to set
     */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    /**
     * @return the descricaoProduto
     */
    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    /**
     * @param descricaoProduto the descricaoProduto to set
     */
    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    /**
     * @return the valorCompra
     */
    public float getValorCompra() {
        return valorCompra;
    }

    /**
     * @param valorCompra the valorCompra to set
     */
    public void setValorCompra(float valorCompra) {
        this.valorCompra = valorCompra;
    }

    /**
     * @return the valorVenda
     */
    public float getValorVenda() {
        return valorVenda;
    }

    /**
     * @param valorVenda the valorVenda to set
     */
    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    /**
     * @return the fotoProduto
     */
    public FotoProduto getFotoProduto() {
        return fotoProduto;
    }

    /**
     * @param fotoProduto the fotoProduto to set
     */
    public void setFotoProduto(FotoProduto fotoProduto) {
        this.fotoProduto = fotoProduto;
    }

    /**
     * @return the statusProduto
     */
    public boolean isStatusProduto() {
        return statusProduto;
    }

    /**
     * @param statusProduto the statusProduto to set
     */
    public void setStatusProduto(boolean statusProduto) {
        this.statusProduto = statusProduto;
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
     * @return the dataAlteracao
     */
    public Timestamp getDataAlteracao() {
        return dataAlteracao;
    }

    /**
     * @param dataAlteracao the dataAlteracao to set
     */
    public void setDataAlteracao(Timestamp dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    /**
     * @return the statusProdutoPesquisa
     */
    public String getStatusProdutoPesquisa() {
        return statusProdutoPesquisa;
    }

    /**
     * @param statusProdutoPesquisa the statusProdutoPesquisa to set
     */
    public void setStatusProdutoPesquisa(String statusProdutoPesquisa) {
        this.statusProdutoPesquisa = statusProdutoPesquisa;
    }

    /**
     * @return the tipoProduto
     */
    public boolean isTipoProduto() {
        return tipoProduto;
    }

    /**
     * @param tipoProduto the tipoProduto to set
     */
    public void setTipoProduto(boolean tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
}
