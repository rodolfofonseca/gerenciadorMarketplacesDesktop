package model;
import java.sql.Timestamp;
/**
 * Classe responsável por conter todas as informações da foto do produto
 * @author RodolfoFonseca
 */
public class FotoProduto {
    private int codigoFotoProduto;
    private String nome_foto;
    private String fotoProduto;
    private Timestamp dataCadastro;

    /**
     * @return the codigoFotoProduto
     */
    public int getCodigoFotoProduto() {
        return codigoFotoProduto;
    }

    /**
     * @param codigoFotoProduto the codigoFotoProduto to set
     */
    public void setCodigoFotoProduto(int codigoFotoProduto) {
        this.codigoFotoProduto = codigoFotoProduto;
    }

    /**
     * @return the nome_foto
     */
    public String getNome_foto() {
        return nome_foto;
    }

    /**
     * @param nome_foto the nome_foto to set
     */
    public void setNome_foto(String nome_foto) {
        this.nome_foto = nome_foto;
    }

    /**
     * @return the fotoProduto
     */
    public String getFotoProduto() {
        return fotoProduto;
    }

    /**
     * @param fotoProduto the fotoProduto to set
     */
    public void setFotoProduto(String fotoProduto) {
        this.fotoProduto = fotoProduto;
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
}
