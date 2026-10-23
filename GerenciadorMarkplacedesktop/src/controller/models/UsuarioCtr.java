package controller.models;

import api.gerenciadorMarktplaces.LoginApi;
import util.Alertas;
import view.LoginSistema;
import com.google.gson.JsonObject;
import javax.swing.SwingWorker;
import org.json.JSONObject;
import util.Sessao;
import view.Dashboard;
/**
 * Classe responsável fazer a interligação de todas as partes do sistema
 * pertinentes aos usuários
 *
 * @author RodolfoFonseca
 */
public class UsuarioCtr {

    public void LoginSistema(LoginSistema formulario) {
        String email = formulario.tbxEmail.getText().trim();
        String senha = new String(formulario.tbxSenha.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            Alertas.aviso("Preencha o e-mail ou a senha!");
        }

        LoginApi api = new LoginApi();
        Alertas.AlertasModerno(formulario, "Validando suas credenciais...");

        SwingWorker<com.google.gson.JsonObject, Void> worker = new SwingWorker<com.google.gson.JsonObject, Void>() {
            @Override
            protected JsonObject doInBackground() throws Exception {
                return api.login(email, senha);
            }
            
            @Override
            protected void done(){
                try{
                    com.google.gson.JsonObject resposta = get();
                    JSONObject respostaJson = new JSONObject(resposta.toString());
                    
                    boolean retorno = resposta.get("sucesso").getAsBoolean();
                    String mensagem = resposta.get("mensagem").getAsString();
                    
                    Alertas.fechar();
                    
                    if(retorno == true){
                        Sessao.carregar(respostaJson);
                        
                        Alertas.sucesso(mensagem);
                        Dashboard dashboard = new Dashboard();
                        dashboard.setVisible(true);
                        formulario.dispose();
                    }else{
                        Alertas.erro(mensagem);
                    }
                }catch(Exception ex){
                    Alertas.fechar();
                    Throwable causa = ex.getCause();
                    String mensagemErro = causa != null ? causa.getMessage(): ex.getMessage();
                    Alertas.erro(mensagemErro);
                }finally{
                    
                }
            }
        };
        
        worker.execute();
    }
}
