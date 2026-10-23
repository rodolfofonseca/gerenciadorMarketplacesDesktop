package util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicProgressBarUI;

import javax.swing.*;
import java.awt.*;

public class Alertas {

    private static JDialog dialog;
    private static JLabel label;
    private static JLabel porcentagemLabel;
    private static JProgressBar progressBar;

    private Alertas() {
    }

    public static void AlertaComProgresso(Window parent, String mensagem) {
        if (dialog != null && dialog.isVisible()) {
            atualizarMensagem(mensagem);
            return;
        }

        dialog = new JDialog(parent, "Aguarde", Dialog.ModalityType.MODELESS);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setResizable(false);
        label = new JLabel(mensagem, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(60, 60, 60));
        label.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
        progressBar.setForeground(new Color(40, 167, 69));
        progressBar.setBackground(new Color(230, 230, 230));
        progressBar.setOpaque(true);
        progressBar.setUI(new BasicProgressBarUI());
        progressBar.setPreferredSize(new Dimension(300, 22));
        progressBar.setStringPainted(false);

        porcentagemLabel = new JLabel("0%", SwingConstants.CENTER);
        porcentagemLabel.setFont(new Font("Arial", Font.BOLD, 13));
        porcentagemLabel.setForeground(new Color(40, 167, 69));
        porcentagemLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));

        JPanel painelProgresso = new JPanel(new BorderLayout());

        painelProgresso.setBackground(Color.WHITE);
        painelProgresso.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        painelProgresso.add(progressBar, BorderLayout.CENTER);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.WHITE);
        painelPrincipal.add(label, BorderLayout.NORTH);
        painelPrincipal.add(painelProgresso, BorderLayout.CENTER);
        painelPrincipal.add(porcentagemLabel, BorderLayout.SOUTH);

        dialog.setContentPane(painelPrincipal);
        dialog.pack();
        dialog.setMinimumSize(new Dimension(360, 130));
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    /**
     * Função responsável por atualizar o progresso.
     *
     * @param valor
     */
    public static void atualizarProgresso(int valor) {

        final int progresso;

        if (valor < 0) {
            progresso = 0;
        } else if (valor > 100) {
            progresso = 100;
        } else {
            progresso = valor;
        }

        SwingUtilities.invokeLater(() -> {
            if (progressBar != null) {
                progressBar.setValue(progresso);
                if (porcentagemLabel != null) {
                    porcentagemLabel.setText(progresso + "%");
                }

                progressBar.revalidate();
                progressBar.repaint();

                if (porcentagemLabel != null) {
                    porcentagemLabel.repaint();
                }
            }
        });
    }

    /**
     * Função responsável por atualizar a mensagem.
     *
     * @param mensagem
     */
    public static void atualizarMensagem(String mensagem) {
        SwingUtilities.invokeLater(() -> {
            if (label != null) {
                label.setText(mensagem);
                label.revalidate();
                label.repaint();
            }
        });
    }

    /**
     * Função responsável por fechar o dialogÏ=
     */
    public static void fechar() {
        SwingUtilities.invokeLater(() -> {
            if (dialog != null) {
                dialog.dispose();
                dialog = null;
                label = null;
                porcentagemLabel = null;
                progressBar = null;
            }
        });
    }
    
    /**
     * Mensagem: Operação realizada com sucesso!
     * 
     * Apresentada sempre que de sucesso de forma genérica.
     */
    public static void operacaoSucesso(){
        alertaModerno("Sucesso!", "Operação realizada com sucesso!", new Color(40, 167, 69));
    }
    
    /**
     * Mensagem: Falha durante o processo!
     * 
     * Aprensentada sempre que uma operção apresentar erro
     */
    public static void OperacaoErro(){
    alertaModerno("Ops!", "Falha durante o processo!", new Color(220, 53, 69));
    }
    
    public static void sucesso(String mensagem) {
        alertaModerno("Sucesso!", mensagem, new Color(40, 167, 69));
    }

    public static void erro(String mensagem) {
        alertaModerno("Ops!", mensagem, new Color(220, 53, 69));
    }

    public static void aviso(String mensagem) {
        alertaModerno("Atenção!", mensagem, new Color(255, 193, 7));
    }

    private static void alertaModerno(String titulo, String mensagem, Color cor) {
        JDialog dialog = new JDialog((Frame) null, titulo, true);

        dialog.setUndecorated(true);
        dialog.setSize(380, 250);
        dialog.setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createLineBorder(cor, 2));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JLabel icone = new JLabel(titulo.equals("Sucesso!") ? "✓" : titulo.equals("Ops!") ? "✕" : "!", SwingConstants.CENTER);
        icone.setFont(new Font("Arial", Font.BOLD, 42));
        icone.setForeground(cor);
        icone.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 23));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblMensagem = new JLabel("<html><div style='text-align:center;width:290px;'>" + mensagem + "</div></html>");
        lblMensagem.setFont(new Font("Arial", Font.PLAIN, 14));
        lblMensagem.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botao = new JButton("OK");
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(cor);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(100, 36));
        botao.setMaximumSize(new Dimension(100, 36));
        botao.addActionListener(e -> dialog.dispose());

        painel.add(Box.createVerticalStrut(15));
        painel.add(icone);
        painel.add(Box.createVerticalStrut(5));
        painel.add(lblTitulo);
        painel.add(Box.createVerticalStrut(10));
        painel.add(lblMensagem);
        painel.add(Box.createVerticalStrut(18));
        painel.add(botao);
        painel.add(Box.createVerticalStrut(15));

        dialog.setContentPane(painel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
    
    /**
     * Alerta de carregando moderno
     * @param parent
     * @param mensagem 
     */
    public static void AlertasModerno(Window parent, String mensagem) {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(
                () -> AlertasModerno(parent, mensagem)
            );
            return;
        }

        if (dialog != null && dialog.isVisible()) {
            atualizarMensagemModerno(mensagem);
            return;
        }

        dialog = new JDialog(
            parent,
            "Aguarde",
            Dialog.ModalityType.MODELESS
        );

        dialog.setUndecorated(true);
        dialog.setDefaultCloseOperation(
            JDialog.DO_NOTHING_ON_CLOSE
        );
        dialog.setResizable(false);

        // Cartão branco com bordas arredondadas
        JPanel painel = new JPanel(new BorderLayout(0, 12)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(Color.WHITE);
                g2.fillRoundRect(
                    0, 0, getWidth(), getHeight(), 24, 24
                );

                g2.dispose();
                super.paintComponent(g);
            }
        };

        painel.setOpaque(false);
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(235, 237, 240), 1
            ),
            BorderFactory.createEmptyBorder(
                25, 30, 25, 30
            )
        ));

        // Ícone de carregamento
        JLabel icone = new JLabel(
            "\u27F3",
            SwingConstants.CENTER
        );

        icone.setFont(new Font("Arial", Font.PLAIN, 48));
        icone.setForeground(new Color(48, 133, 214));

        // Título
        JLabel titulo = new JLabel(
            "Aguarde...",
            SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(84, 84, 84));

        // Mensagem
        label = new JLabel(
            mensagem,
            SwingConstants.CENTER
        );

        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(110, 110, 110));

        // Barra animada, sem porcentagem fictícia
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setBorderPainted(false);
        progressBar.setOpaque(false);
        progressBar.setForeground(
            new Color(48, 133, 214)
        );
        progressBar.setPreferredSize(
            new Dimension(280, 6)
        );

        JPanel topo = new JPanel(new BorderLayout(0, 5));
        topo.setOpaque(false);
        topo.add(icone, BorderLayout.NORTH);
        topo.add(titulo, BorderLayout.CENTER);

        painel.add(topo, BorderLayout.NORTH);
        painel.add(label, BorderLayout.CENTER);
        painel.add(progressBar, BorderLayout.SOUTH);

        dialog.setContentPane(painel);
        dialog.pack();
        dialog.setSize(360, 205);
        dialog.setLocationRelativeTo(parent);

        dialog.setVisible(true);
        dialog.toFront();
    }

    public static void atualizarMensagemModerno(String mensagem) {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(
                () -> atualizarMensagem(mensagem)
            );
            return;
        }

        if (label != null) {
            label.setText(mensagem);
        }
    }

    public static void atualizarProgressoModerno(int valor) {
        // Não há porcentagem real disponível durante a requisição.
        // A barra permanece animada até o retorno da API.
    }

    public static void fecharModerno() {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(
                Alertas::fecharModerno
            );
            return;
        }

        if (dialog != null) {
            dialog.dispose();
        }

        dialog = null;
        label = null;
        progressBar = null;
    }
}
