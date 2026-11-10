package view;

import controller.CatalogoController;
import model.Album;
import model.Artista;
import model.Musica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JanelaPrincipal extends JFrame {
    private CatalogoController controller;

    // Componentes de Artista
    private JTextField txtNomeArtista;
    private JTextArea areaListagem;

    // Componentes de Álbum
    private JTextField txtTituloAlbum;
    private JTextField txtAnoAlbum;
    private JComboBox<Artista> cmbArtistaAlbum;

    // Componentes de Música
    private JTextField txtTituloMusica;
    private JTextField txtDuracaoMusica;
    private JComboBox<Artista> cmbArtistaMusica;
    private JComboBox<Album> cmbAlbumMusica;

    public JanelaPrincipal() {
        this.controller = new CatalogoController();

        setTitle("Catálogo Musical - MVC Swing");
        setSize(750, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Cadastrar Artista", criarPainelArtista());
        tabbedPane.addTab("Cadastrar Álbum", criarPainelAlbum());
        tabbedPane.addTab("Cadastrar Música", criarPainelMusica());
        tabbedPane.addTab("Listar Registros", criarPainelListagem());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel criarPainelArtista() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nome do Artista:"), gbc);

        txtNomeArtista = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(txtNomeArtista, gbc);

        JButton btnCadastrar = new JButton("Cadastrar Artista");
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(btnCadastrar, gbc);

        btnCadastrar.addActionListener((ActionEvent e) -> {
            try {
                controller.cadastrarArtista(txtNomeArtista.getText());
                JOptionPane.showMessageDialog(this, "Artista cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                txtNomeArtista.setText("");
                atualizarCombos();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel criarPainelAlbum() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Título do Álbum:"), gbc);
        txtTituloAlbum = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(txtTituloAlbum, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Ano de Lançamento:"), gbc);
        txtAnoAlbum = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtAnoAlbum, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Artista:"), gbc);
        cmbArtistaAlbum = new JComboBox<>();
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(cmbArtistaAlbum, gbc);

        JButton btnCadastrar = new JButton("Cadastrar Álbum");
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(btnCadastrar, gbc);

        btnCadastrar.addActionListener((ActionEvent e) -> {
            try {
                Artista artistaSel = (Artista) cmbArtistaAlbum.getSelectedItem();
                controller.cadastrarAlbum(txtTituloAlbum.getText(), txtAnoAlbum.getText(), artistaSel);
                JOptionPane.showMessageDialog(this, "Álbum cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                txtTituloAlbum.setText("");
                txtAnoAlbum.setText("");
                atualizarCombos();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel criarPainelMusica() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Título da Música:"), gbc);
        txtTituloMusica = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(txtTituloMusica, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Duração (segundos):"), gbc);
        txtDuracaoMusica = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtDuracaoMusica, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Artista:"), gbc);
        cmbArtistaMusica = new JComboBox<>();
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(cmbArtistaMusica, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Álbum:"), gbc);
        cmbAlbumMusica = new JComboBox<>();
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(cmbAlbumMusica, gbc);

        // Listener para atualizar álbuns quando o artista mudar
        cmbArtistaMusica.addActionListener(e -> {
            Artista artistaSel = (Artista) cmbArtistaMusica.getSelectedItem();
            cmbAlbumMusica.removeAllItems();
            if (artistaSel != null) {
                for (Album alb : controller.getAlbuns()) {
                    if (alb.getArtista().equals(artistaSel)) {
                        cmbAlbumMusica.addItem(alb);
                    }
                }
            }
        });

        JButton btnCadastrar = new JButton("Cadastrar Música");
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(btnCadastrar, gbc);

        btnCadastrar.addActionListener((ActionEvent e) -> {
            try {
                Artista artistaSel = (Artista) cmbArtistaMusica.getSelectedItem();
                Album albumSel = (Album) cmbAlbumMusica.getSelectedItem();
                controller.cadastrarMusica(txtTituloMusica.getText(), txtDuracaoMusica.getText(), artistaSel, albumSel);
                JOptionPane.showMessageDialog(this, "Música cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                txtTituloMusica.setText("");
                txtDuracaoMusica.setText("");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel criarPainelListagem() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        areaListagem = new JTextArea();
        areaListagem.setEditable(false);
        panel.add(new JScrollPane(areaListagem), BorderLayout.CENTER);

        JButton btnListar = new JButton("Atualizar Listagem");
        panel.add(btnListar, BorderLayout.SOUTH);

        btnListar.addActionListener(e -> atualizarListagem());

        return panel;
    }

    private void atualizarCombos() {
        // Atualiza Artistas no combo de álbum
        cmbArtistaAlbum.removeAllItems();
        for (Artista a : controller.getArtistas()) {
            cmbArtistaAlbum.addItem(a);
        }

        // Atualiza Artistas no combo de música
        cmbArtistaMusica.removeAllItems();
        for (Artista a : controller.getArtistas()) {
            cmbArtistaMusica.addItem(a);
        }
    }

    private void atualizarListagem() {
        StringBuilder sb = new StringBuilder();

        sb.append("=== ARTISTAS CADASTRADOS ===\n");
        if (controller.getArtistas().isEmpty()) {
            sb.append("(Nenhum artista cadastrado)\n");
        } else {
            for (Artista a : controller.getArtistas()) {
                sb.append("- ").append(a.getNome()).append("\n");
            }
        }

        sb.append("\n=== ÁLBUNS CADASTRADOS ===\n");
        if (controller.getAlbuns().isEmpty()) {
            sb.append("(Nenhum álbum cadastrado)\n");
        } else {
            for (Album al : controller.getAlbuns()) {
                sb.append("- ").append(al.getTitulo()).append(" (").append(al.getAno()).append(") | Artista: ").append(al.getArtista().getNome()).append("\n");
            }
        }

        sb.append("\n=== MÚSICAS CADASTRADAS ===\n");
        if (controller.getMusicas().isEmpty()) {
            sb.append("(Nenhuma música cadastrada)\n");
        } else {
            for (Musica m : controller.getMusicas()) {
                sb.append("- ").append(m.getTitulo()).append(" [Duração: ").append(m.formatarDuracao()).append("] | Álbum: ").append(m.getAlbum().getTitulo()).append(" | Artista: ").append(m.getArtista().getNome()).append("\n");
            }
        }

        areaListagem.setText(sb.toString());
    }
}
