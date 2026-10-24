package controller.models;

import DAO.CategoriaProdutoDAO;
import controller.view.DashboardCtr;
import java.util.List;
import java.util.concurrent.ExecutionException;
import model.CategoriaProduto;
import model.Empresa;
import util.Alertas;
import view.pesquisa.CategoriaProdutoPesq;
import view.cadastro.CategoriaProdutoCad;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import util.ColorirTabela;
import util.FormatarData;
import util.Sessao;

/**
 * Classe que faz a interligação entre todas as partes do sistema relacionado a
 * categoria do produto
 *
 * @author RodolfoFonseca
 */
public class CategoriaProdutoCtr {

    private CategoriaProduto model;
    private CategoriaProdutoDAO dao;

    public CategoriaProdutoCtr() {
        this.dao = new CategoriaProdutoDAO();
        this.model = new CategoriaProduto();
    }

    /**
     * Função responsável por pesquisar todas as categorias cadastradas no banco
     * de ddos para a empresa que está acessando o sistema.
     *
     * @param formulario
     * @param back
     */
    public void pesquisarCategoriasProdutos(CategoriaProdutoPesq formulario, boolean back) {
        if (back == false) {
            Alertas.AlertasModerno(javax.swing.SwingUtilities.getWindowAncestor(formulario), "Carregando categorias produtos...");
        }

        SwingWorker<List<CategoriaProduto>, Void> worker = new SwingWorker<List<CategoriaProduto>, Void>() {
            @Override
            protected List<CategoriaProduto> doInBackground() throws Exception {
                model.setDescricaoCategoriaProduto(formulario.tbxDescricaoCategoria.getText().toUpperCase());
                String statusPesquisa = formulario.cbxStatusCategoria.getSelectedItem().toString().trim();
                model.setCategoriaProdutoPesquisa(statusPesquisa);

                if ("ATIVO".equals(statusPesquisa)) {
                    model.setStatusCategoriaProduto(true);
                } else if ("INATIVO".equals(statusPesquisa)) {
                    model.setStatusCategoriaProduto(false);
                }
                return dao.listarTodas(model);
            }

            @Override
            protected void done() {
                try {
                    List<CategoriaProduto> categorias = get();
                    DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Código", "Descrição", "Data Cadastro", "Status"});

                    for (CategoriaProduto categoria : categorias) {
                        modelo.addRow(new Object[]{categoria.getCodigoCategoriaProduto(), categoria.getDescricaoCategoriaProduto(), FormatarData.formatarData(categoria.getDataCadastro()), categoria.isStatusCategoriaProduto()});
                    }

                    formulario.tCategoriaProdutos.setModel(modelo);
                    ColorirTabela.colorirLinhaPorBoolean(formulario.tCategoriaProdutos, 3);

                } catch (Exception e) {
                    Throwable causa = e.getCause();
                    String mensagem = causa != null ? causa.getMessage() : e.getMessage();
                    System.err.println("Erro ao pesquisar categorias: " + mensagem);
                    Alertas.erro(mensagem != null ? mensagem : "Não foi possível carregar as categorias.");
                } finally {
                    if (back == false) {
                        Alertas.fechar();
                    }
                }
            }
        };

        worker.execute();
    }

    /**
     * Função reponsável por salvar no banco de dados o cadastro ou alteração.
     *
     * @param formulario
     */
    public void salvarDadosCategoriaProduto(CategoriaProdutoCad formulario) {
        try {
            Empresa empresa = new Empresa();
            empresa.setCodigoEmpresa(Sessao.empresa.getInt("codigo_empresa"));
            this.model.setEmpresa(empresa);

            this.model.setCodigoCategoriaProduto(formulario.codigoCategoria);
            this.model.setDescricaoCategoriaProduto(formulario.tbxDescricao.getText().toUpperCase().trim());

            String status = formulario.cbxStatus.getSelectedItem().toString();
            boolean statusCategoria = false;

            if ("ATIVO".equals(status)) {
                this.model.setStatusCategoriaProduto(true);
            } else {
                this.model.setStatusCategoriaProduto(false);
            }
        } catch (Exception ex) {
            Alertas.erro(ex.getMessage());
            return;
        }

        final boolean alteracao = this.model.getCodigoCategoriaProduto() != 0;
        Alertas.AlertasModerno(javax.swing.SwingUtilities.getWindowAncestor(formulario), alteracao ? "Alterando Categoria Produto..." : "Cadastrando Categoria Produto...");

        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                if (alteracao) {
                    return dao.alterar(model);
                } else {
                    return dao.cadastrar(model);
                }
            }

            @Override
            protected void done() {
                try {
                    boolean retornoOperacao = get();

                    Alertas.fechar();

                    if (retornoOperacao) {
                        if(formulario.formularioPesquisa != null){
                            pesquisarCategoriasProdutos(formulario.formularioPesquisa, true);
                        }
                        
                        Alertas.operacaoSucesso();
                    } else {
                        Alertas.OperacaoErro();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    Alertas.erro("A operação foi interrompida.");
                } catch (java.util.concurrent.ExecutionException e) {
                    Throwable causa = e.getCause();

                    String mensagem = causa != null ? causa.getMessage() : e.getMessage();
                    Alertas.erro(mensagem != null ? mensagem : "Não foi possível salvar a categoria.");
                } finally {
                    Alertas.fechar();
                }
            }
        };

        worker.execute();
    }

    /**
     * Função responsável por pegar a linha selecionada na tabela.
     *
     * @param evento
     * @param formulario
     */
    public void selecionarTabela(java.awt.event.MouseEvent evento, CategoriaProdutoPesq formulario) {
        int linhaSelecionada = formulario.tCategoriaProdutos.getSelectedRow();

        int linhaModelo = formulario.tCategoriaProdutos.convertRowIndexToModel(linhaSelecionada);
        Object valorCodigo = formulario.tCategoriaProdutos.getModel().getValueAt(linhaModelo, 0);

        if (valorCodigo == null) {
            Alertas.erro("Não foi possível identificar a categoria selecionada!");
            return;
        }

        int codigo = Integer.parseInt(valorCodigo.toString());

        abrirEdicao(codigo, formulario);
    }

    /**
     * Função responsável por pesquisar os dados da categoria selecionada pelo
     * usuário e logo em seguida abrir o formulário de cadastro de contas.
     *
     * @param codigoCategoria
     * @param formulario
     */
    public void abrirEdicao(int codigoCategoria, CategoriaProdutoPesq formulario) {
        Alertas.AlertasModerno(javax.swing.SwingUtilities.getWindowAncestor(formulario), "Pesquisando Categoria...");
        SwingWorker<CategoriaProduto, Void> worker = new SwingWorker<CategoriaProduto, Void>() {
            @Override
            protected CategoriaProduto doInBackground() throws Exception {
                return dao.pesquisar(codigoCategoria);
            }

            @Override
            protected void done() {
                try {
                    CategoriaProduto categoriaProduto = get();

                    if (categoriaProduto == null) {
                        Alertas.erro("Categoria produto não encontrada!");
                        return;
                    }

                    DashboardCtr dashboardController = new DashboardCtr();
                    dashboardController.abrirFormularioCadastroCategoriaProdutos(categoriaProduto, formulario);
                } catch (ExecutionException ex) {
                    Throwable causa = ex.getCause();
                    Alertas.erro("Erro ao consultar a conta: " + (causa != null ? causa.getMessage() : ex.getMessage()));
                } catch (InterruptedException ex) {
                    Alertas.erro("Erro ao consultar a conta: " + ex.getMessage());
                } finally {
                    Alertas.fechar();
                }
            }
        };

        worker.execute();
    }

    /**
     * Função responável por colocar os dados no formulário de edição ou
     * cadastro de categoria
     *
     * @param categoriaProduto
     * @param formulario
     */
    public void colocarDados(CategoriaProduto categoriaProduto, CategoriaProdutoCad formulario) {
        formulario.codigoCategoria = categoriaProduto.getCodigoCategoriaProduto();
        formulario.tbxDescricao.setText(categoriaProduto.getDescricaoCategoriaProduto().toUpperCase().trim());
        formulario.tbxDataCadastro.setText(FormatarData.formatarData(categoriaProduto.getDataCadastro()));

        if (categoriaProduto.isStatusCategoriaProduto() == true) {
            formulario.cbxStatus.setSelectedItem("ATIVO");
        } else {
            formulario.cbxStatus.setSelectedItem("INATIVO");
        }
    }
}
