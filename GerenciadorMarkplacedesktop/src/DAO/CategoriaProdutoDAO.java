package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.CategoriaProduto;
import model.Empresa;
import util.Conexao;
import util.Sessao;

/**
 * Classe que faz a comunição com o banco de dados
 *
 * @author RodolfoFonseca
 */
public class CategoriaProdutoDAO {

    /**
     * Função responsável por pesquisar todas as categorias do produto, de
     * acordo com as opções de filtro selecionado pelo usuário do sistema.
     *
     * @param categoriaProduto
     * @return
     */
    public List<CategoriaProduto> listarTodas(CategoriaProduto categoriaProduto) {
        StringBuilder sql = new StringBuilder("SELECT codigo_categoria_produto, descricao_categoria_produto, status_categoria_produto, data_cadastro FROM categoria_produto WHERE codigo_empresa = ?");
        
        if (!"TODOS".equals(categoriaProduto.getCategoriaProdutoPesquisa())) {
            sql.append(" AND status_categoria_produto = ?");
        }
        
        if (!"".equals(categoriaProduto.getDescricaoCategoriaProduto())) {
            sql.append(" AND descricao_categoria_produto LIKE ?");
        }
        
        sql.append(" ORDER BY descricao_categoria_produto ASC;");
        
        List<CategoriaProduto> categorias = new ArrayList<>();
        
        try (Connection conn = Conexao.conectar(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int parametro = 1;
            
            ps.setInt(parametro++, Sessao.empresa.getInt("codigo_empresa"));
            
            if (!"TODOS".equals(categoriaProduto.getCategoriaProdutoPesquisa())) {
                ps.setBoolean(parametro++, categoriaProduto.isStatusCategoriaProduto());
            }
            
            if (!"".equals(categoriaProduto.getDescricaoCategoriaProduto())) {
                ps.setString(parametro++, "%" + categoriaProduto.getDescricaoCategoriaProduto() + "%");
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CategoriaProduto categoria = new CategoriaProduto();
                    
                    categoria.setCodigoCategoriaProduto(rs.getInt("codigo_categoria_produto"));
                    categoria.setDescricaoCategoriaProduto(rs.getString("descricao_categoria_produto"));
                    categoria.setStatusCategoriaProduto(rs.getBoolean("status_categoria_produto"));
                    categoria.setDataCadastro(rs.getTimestamp("data_cadastro"));
                    
                    categorias.add(categoria);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return categorias;
    }

    /**
     * Função responsável por fazer o cadastro de uma nova categoria de produto
     * no banco de dados
     *
     * @param model
     * @return
     */
    public boolean cadastrar(CategoriaProduto model) {
        String sql = "INSERT INTO categoria_produto(codigo_empresa, descricao_categoria_produto, status_categoria_produto) VALUES(?, ?, ?);";
        
        try (Connection connection = Conexao.conectar(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, model.getEmpresa().getCodigoEmpresa());
            stmt.setString(2, model.getDescricaoCategoriaProduto().toUpperCase().trim());
            stmt.setBoolean(3, model.isStatusCategoriaProduto());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Função responsável por alterar a categoria do produto no banco de dados.
     *
     * @param model
     * @return
     */
    public boolean alterar(CategoriaProduto model) {
        String sql = "UPDATE categoria_produto SET descricao_categoria_produto = ?, status_categoria_produto = ? WHERE codigo_categoria_produto = ?;";
        
        try (Connection connection = Conexao.conectar(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, model.getDescricaoCategoriaProduto().toUpperCase().trim());
            stmt.setBoolean(2, model.isStatusCategoriaProduto());
            stmt.setInt(3, model.getCodigoCategoriaProduto());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Função responsável por pesquisar apenas as informações da categoria
     * selecionada
     *
     * @param id
     * @return
     */
    public CategoriaProduto pesquisar(int id) {
        String sql = "SELECT * FROM categoria_produto WHERE codigo_categoria_produto = ?;";
        
        CategoriaProduto categoriaProduto = new CategoriaProduto();
        try (Connection connection = Conexao.conectar(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    Empresa empresa = new Empresa();
                    empresa.setCodigoEmpresa(rs.getInt("codigo_empresa"));
                    
                    categoriaProduto.setCodigoCategoriaProduto(rs.getInt("codigo_categoria_produto"));
                    categoriaProduto.setEmpresa(empresa);
                    categoriaProduto.setDataCadastro(rs.getTimestamp("data_cadastro"));
                    categoriaProduto.setDescricaoCategoriaProduto(rs.getString("descricao_categoria_produto"));
                    categoriaProduto.setStatusCategoriaProduto(rs.getBoolean("status_categoria_produto"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return categoriaProduto;
        }
    }
}
