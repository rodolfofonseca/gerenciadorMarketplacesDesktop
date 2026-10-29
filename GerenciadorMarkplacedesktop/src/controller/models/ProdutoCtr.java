package controller.models;
import view.pesquisa.ProdutoPesq;
import view.cadastro.ProdutoCad;
import java.awt.event.MouseEvent;
import DAO.ProdutoDAO;
import model.Produto;
import model.Empresa;
import model.CategoriaProduto;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import util.ColorirTabela;
import util.FormatarData;
import util.Sessao;
import javax.swing.SwingUtilities;
import util.Alertas;
/**
 * Classe que faz a comunicação com todas as partes do sistema.
 * @author RodolfoFonseca
 */
public class ProdutoCtr implements CTRGenerica<ProdutoPesq, ProdutoCad, MouseEvent, Integer>{
    private Produto model;
    private ProdutoDAO dao;
    
    public ProdutoCtr(){
        this.dao = new ProdutoDAO();
        this.model = new Produto();
    }
    
    @Override
    public void pesquisarTodos(ProdutoPesq formularioPesquisa) {
        Alertas.AlertasModerno(SwingUtilities.getWindowAncestor(formularioPesquisa), "Carregando Produtos...");
        SwingWorker<List<Produto>, Void> worker = new SwingWorker<List<Produto>, Void>() {
            @Override
            protected List<Produto> doInBackground() throws Exception {
                model.setSkuProduto(formularioPesquisa.tbxSkuProduto.getText().toUpperCase().trim());
                model.setNomeProduto(formularioPesquisa.tbxNomeProduto.getText().toUpperCase().trim());
                
                String categoria = formularioPesquisa.cbxStatusProduto.getSelectedItem().toString();
                
                if("ATIVO".equals(categoria)){
                    model.setStatusProduto(true);
                }else if("INATIVO".equals(categoria)){
                    model.setStatusProduto(false);
                }
                
                return dao.listar(model);
            }
            
            @Override
            protected void done(){
                try{
                    List<Produto> produtos = get();
                    DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Código", "SKU", "Nome", "Valor Compra", "Valor Venda", "Cadastro", "Alteração"});
                    
                    for(Produto produto:produtos){
                        modelo.addRow(new Object[]{produto.getCodigoProduto(), produto.getSkuProduto(), produto.getValorCompra(), produto.getValorVenda(), FormatarData.formatarData(produto.getDataCadastro()), FormatarData.formatarData(produto.getDataAlteracao())});
                    }
                    
                    formularioPesquisa.tabela.setModel(modelo);
                    ColorirTabela.colorirLinhaPorBoolean(formularioPesquisa.tabela, 0);
                }catch(Exception ex){
                    Throwable causa = ex.getCause();
                    String mensagem = causa != null ? causa.getMessage() : ex.getMessage();
                    System.err.println("Erro ao pesquisar produtos: " + mensagem);
                    Alertas.erro(mensagem != null ? mensagem : "Não foi possível carregar os produtos.");
                }finally{
                    Alertas.fechar();
                }
            }
        };
        
        worker.execute();
    }

    @Override
    public void salvarDados(ProdutoCad formularioCadastro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void selecionarTabela(MouseEvent evento, ProdutoPesq formularioPesquisa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void abrirEdicao(Integer codigo, ProdutoPesq formularioPesquisa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void colocarDados(ProdutoPesq formulairoPesquisa, ProdutoCad formularioCadastro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
