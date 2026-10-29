package controller.models;

/**
 * Classe genérica que faz a comunicação entre as partes
 * @author RodolfoFonseca
 * @param <formularioPesquisa>
 * @param <formularioCadastro>
 * @param <evento>
 * @param <tipoVariavel>
 */
public interface CTRGenerica<formularioPesquisa, formularioCadastro, evento, tipoVariavel> {
    void pesquisarTodos(formularioPesquisa formularioPesquisa);
    void salvarDados(formularioCadastro formularioCadastro);
    void selecionarTabela(evento evento, formularioPesquisa formularioPesquisa);
    void abrirEdicao(tipoVariavel codigo, formularioPesquisa formularioPesquisa);
    void colocarDados(formularioPesquisa formulairoPesquisa, formularioCadastro formularioCadastro);
}
