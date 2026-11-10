package model;

public class Musica {
    private String titulo;
    private int duracaoEmSegundos;
    private Artista artista;
    private Album album;

    public Musica(String titulo, int duracaoEmSegundos, Artista artista, Album album) {
        this.titulo = titulo;
        this.duracaoEmSegundos = duracaoEmSegundos;
        this.artista = artista;
        this.album = album;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracaoEmSegundos() {
        return duracaoEmSegundos;
    }

    public void setDuracaoEmSegundos(int duracaoEmSegundos) {
        this.duracaoEmSegundos = duracaoEmSegundos;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String formatarDuracao() {
        int minutos = duracaoEmSegundos / 60;
        int segundos = duracaoEmSegundos % 60;
        return String.format("%d:%02d", minutos, segundos);
    }

    @Override
    public String toString() {
        return titulo + " [" + formatarDuracao() + "] - Álbum: " + album.getTitulo() + " | Artista: " + artista.getNome();
    }
}
