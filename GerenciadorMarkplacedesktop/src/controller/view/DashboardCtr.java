package controller.view;
import static view.Dashboard.desktop;
import view.Dashboard;

/**
 * Classe que realiza todas as funções da view dashboard
 * @author RodolfoFonseca
 */
public class DashboardCtr {
    /**
     * Função responsável por colocar o plano de fundo no desktop
     * @param formulario 
     */
    private void adicionarPlanoDeFundo(Dashboard formulario){
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
     * Função responsável por iniciar o formulário.
     * @param formulario 
     */
    public void iniciarDashboard(Dashboard formulario){
        this.adicionarPlanoDeFundo(formulario);
    }
}
