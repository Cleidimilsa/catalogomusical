package controller;

import model.Album;
import model.Artista;
import model.Musica;

import java.util.ArrayList;
import java.util.List;

public class CatalogoController {
    private List<Artista> artistas;
    private List<Album> albuns;
    private List<Musica> musicas;

    public CatalogoController() {
        this.artistas = new ArrayList<>();
        this.albuns = new ArrayList<>();
        this.musicas = new ArrayList<>();
    }

    public void cadastrarArtista(String nome) throws IllegalArgumentException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do artista não pode estar vazio.");
        }
        
        // Evitar duplicados exatos (opcional mas bom)
        for (Artista a : artistas) {
            if (a.getNome().equalsIgnoreCase(nome.trim())) {
                throw new IllegalArgumentException("Já existe um artista cadastrado com este nome.");
            }
        }

        artistas.add(new Artista(nome.trim()));
    }

    public void cadastrarAlbum(String titulo, String anoStr, Artista artista) throws IllegalArgumentException {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título do álbum não pode estar vazio.");
        }
        if (artista == null) {
            throw new IllegalArgumentException("É obrigatório selecionar um artista válido para o álbum.");
        }
        
        int ano;
        try {
            ano = Integer.parseInt(anoStr.trim());
            if (ano < 1900 || ano > 2100) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O ano deve ser um número inteiro válido (ex: 2023).");
        }

        albuns.add(new Album(titulo.trim(), ano, artista));
    }

    public void cadastrarMusica(String titulo, String duracaoStr, Artista artista, Album album) throws IllegalArgumentException {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título da música não pode estar vazio.");
        }
        if (artista == null) {
            throw new IllegalArgumentException("É obrigatório selecionar um artista válido para a música.");
        }
        if (album == null) {
            throw new IllegalArgumentException("É obrigatório selecionar um álbum válido para a música.");
        }
        if (!album.getArtista().equals(artista)) {
            throw new IllegalArgumentException("O álbum selecionado não pertence ao artista escolhido!");
        }

        int duracao;
        try {
            duracao = Integer.parseInt(duracaoStr.trim());
            if (duracao <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("A duração em segundos deve ser um número inteiro positivo.");
        }

        musicas.add(new Musica(titulo.trim(), duracao, artista, album));
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public List<Album> getAlbuns() {
        return albuns;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }
}
