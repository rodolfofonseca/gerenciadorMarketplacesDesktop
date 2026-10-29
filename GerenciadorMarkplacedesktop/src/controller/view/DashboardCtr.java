package controller.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.beans.PropertyVetoException;
import model.CategoriaProduto;
import model.Produto;
import static view.Dashboard.desktop;
import view.Dashboard;
import util.Sessao;
import view.cadastro.CategoriaProdutoCad;
import view.cadastro.ProdutoCad;
import view.pesquisa.CategoriaProdutoPesq;
import view.pesquisa.ProdutoPesq;

/**
 * Classe que realiza todas as funções da view dashboard
 *
 * @author RodolfoFonseca
 */
public class DashboardCtr {

    /**
     * Função responsável por colocar o plano de fundo no desktop
     *
     * @param formulario
     */
    private void adicionarPlanoDeFundo(Dashboard formulario) {
        java.net.URL url = getClass().getResource("/imagens/logo/logo.png");

        if (url == null) {
            javax.swing.JOptionPane.showMessageDialog(formulario, "Imagem de fundo não encontrada!");
            return;
        }

        javax.swing.ImageIcon imagem = new javax.swing.ImageIcon(url);

        if (desktop instanceof util.DesktopComFundo) {
            ((util.DesktopComFundo) desktop).setImagemFundo(imagem.getImage());
        } else {
            javax.swing.JOptionPane.showMessageDialog(formulario, "O desktop precisa ser do tipo DesktopComFundo.");
        }
    }

    /**
     * Função responsável por colocar os dados da dashboard
     *
     * @param formulario
     */
    private void colocarDadosDashboard(Dashboard formulario) {
        formulario.lNomeUsuario.setText(Sessao.usuario.getString("nome") + " - ");
    }

    /**
     * Função responsável por iniciar o relógio que mostra a data e hora e fica
     * atualizando em tempo de execução de sistema
     */
    private void iniciarRelogio(Dashboard formulario) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy HH:mm:ss", new java.util.Locale("pt", "BR"));
        Timer timer = new Timer(1000, e -> {
            LocalDateTime agora = LocalDateTime.now();
            formulario.lDataEHora.setText(agora.format(formato));
        });

        timer.start();
    }

    /**
     * Função responsável por iniciar o formulário.
     *
     * @param formulario
     */
    public void iniciarDashboard(Dashboard formulario) {
        this.iniciarRelogio(formulario);
        this.adicionarPlanoDeFundo(formulario);
        this.colocarDadosDashboard(formulario);
    }

    /**
     * Função responsável por abrir a o formulário de pesquisa de categoria
     */
    public void abrirFormularioPesquisaCategoriaProdutos() {
        SwingUtilities.invokeLater(() -> {
            CategoriaProdutoPesq categoriaProduto = new CategoriaProdutoPesq();

            categoriaProduto.setSize(1000, 700);

            int x = (desktop.getWidth() - categoriaProduto.getWidth()) / 2;
            int y = (desktop.getHeight() - categoriaProduto.getHeight()) / 2;

            categoriaProduto.setLocation(x, y);

            desktop.add(categoriaProduto);
            categoriaProduto.setVisible(true);

            desktop.revalidate();
            desktop.repaint();

            try {
                categoriaProduto.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Função repsonsável por abrir o formulário de cadastro de categorias de
     * produto.
     *
     * @param model
     * @param formularioPesquisa
     */
    public void abrirFormularioCadastroCategoriaProdutos(CategoriaProduto model, CategoriaProdutoPesq formularioPesquisa) {
        SwingUtilities.invokeLater(() -> {
            CategoriaProdutoCad categoriaProduto = new CategoriaProdutoCad();

            categoriaProduto.setSize(1000, 700);
            categoriaProduto.formularioPesquisa = formularioPesquisa;

            int x = (desktop.getWidth() - categoriaProduto.getWidth()) / 2;
            int y = (desktop.getHeight() - categoriaProduto.getHeight()) / 2;

            categoriaProduto.setLocation(x, y);

            desktop.add(categoriaProduto);

            if (model != null) {
                categoriaProduto.preencherFormulario(model);
            }

            categoriaProduto.setVisible(true);

            desktop.revalidate();
            desktop.repaint();

            try {
                categoriaProduto.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Função responsável por abrir o formulário de pesquisa de produtos
     */
    public void abrirFormularioPesquisaProduto() {
        SwingUtilities.invokeLater(() -> {
            ProdutoPesq pesquisa = new ProdutoPesq();

            pesquisa.setSize(1000, 700);

            int x = (desktop.getWidth() - pesquisa.getWidth()) / 2;
            int y = (desktop.getHeight() - pesquisa.getHeight()) / 2;
            pesquisa.setLocation(x, y);

            desktop.add(pesquisa);
            pesquisa.setVisible(true);
            desktop.revalidate();
            desktop.repaint();

            try {
                pesquisa.setSelected(true);
            } catch (java.beans.PropertyVetoException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Função responsável por abrir o formulário de cadastro/Alteração de
     * produtos com as informações carregadas no caso de alteração
     *
     * @param model
     * @param formularioPesquisa
     */
    public void abrirFormularioCadastroProduto(Produto model, ProdutoPesq formularioPesquisa) {
        SwingUtilities.invokeLater(() -> {
            ProdutoCad cadastro = new ProdutoCad();
            cadastro.setSize(1000, 700);
            cadastro.formularioPesquisa = formularioPesquisa;
            
            int x = (desktop.getWidth() - cadastro.getWidth()) / 2;
            int y = (desktop.getHeight() - cadastro.getHeight()) / 2;
            cadastro.setLocation(x, y);
            
            desktop.add(cadastro);
            
            if(model != null){
                cadastro.preencherFormulario(model);
            }
            
            cadastro.setVisible(true);
            desktop.revalidate();
            desktop.repaint();
            
            try{
                cadastro.setSelected(true);
            }catch(PropertyVetoException ex){
                ex.printStackTrace();
            }
        });
    }
}
