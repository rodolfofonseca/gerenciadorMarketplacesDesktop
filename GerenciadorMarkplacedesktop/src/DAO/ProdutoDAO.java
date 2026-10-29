package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.CategoriaProduto;
import model.Produto;
import util.Conexao;
import util.Sessao;
/**
 * Classe que faz a comunicação com o banco de dados
 * @author RodolfoFonseca
 */
public class ProdutoDAO implements DAOGenerica<Produto, Integer>{

    @Override
    public boolean inserir(Produto objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean alterar(Produto objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Produto bucaPorId(Integer ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Produto> listar(Produto objeto) {
        StringBuilder sql = new StringBuilder("SELECT * from produto WHERE codigo_empresa = ?");
        
        if(!"TODOS".equals(objeto.getStatusProdutoPesquisa())){
            sql.append(" AND status_produto = ?");
        }
        
        if(!"".equals(objeto.getSkuProduto())){
            sql.append(" AND sku_produto LIKE ?");
        }
        
        if(!"".equals(objeto.getNomeProduto())){
            sql.append(" AND nome_produto like ?");
        }
        
        sql.append(" ORDER BY data_cadastro DESC");
       
        List<Produto> produtos = new ArrayList<>();
        
        try(Connection conn = Conexao.conectar(); PreparedStatement ps = conn.prepareStatement(sql.toString())){
           int parametro = 1;
           
           ps.setInt(parametro++, Sessao.empresa.getInt("codigo_empresa"));
           
           if(!"TODOS".equals(objeto.getStatusProdutoPesquisa())){
               ps.setBoolean(parametro++, objeto.isStatusProduto());
           }
           
           if(!"".equals(objeto.getSkuProduto())){
               ps.setString(parametro++, "%"+objeto.getSkuProduto()+"%");
           }
           
           if(!"".equals(objeto.getNomeProduto())){
              ps.setString(parametro++, "%"+objeto.getNomeProduto()+"%");
           }
           
           try(ResultSet rs = ps.executeQuery()){
               while(rs.next()){
                   Produto produto = new Produto();
                   CategoriaProduto categoria = new CategoriaProduto();
                   
                   categoria.setCodigoCategoriaProduto(rs.getInt("codigo_categoria_produto"));
                   produto.setCategoriaProduto(categoria);
                   produto.setCodigoProduto(rs.getInt("codigo_produto"));
                   produto.setSkuProduto(rs.getString("sku_produto"));
                   produto.setNomeProduto(rs.getString("nome_produto"));
                   produto.setDescricaoProduto(rs.getString("descricao_produto"));
                   produto.setValorCompra(rs.getFloat("valor_compra"));
                   produto.setValorVenda(rs.getFloat("valor_venda"));
                   produto.setStatusProduto(rs.getBoolean("status_produto"));
                   produto.setTipoProduto(rs.getBoolean("tipo_produto"));
                   produto.setDataCadastro(rs.getTimestamp("data_cadastro"));
                   produto.setDataAlteracao(rs.getTimestamp("data_atualizacao"));
               }
           }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return produtos;
    }
}