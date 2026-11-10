package main;

import view.JanelaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Executar a interface gráfica na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new JanelaPrincipal().setVisible(true);
        });
    }
}
