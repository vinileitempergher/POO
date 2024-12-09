package Agenda;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Compromisso {
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private String categoria;

    public Compromisso(String titulo, String descricao, LocalDateTime dataHora, String categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Título: " + titulo + " | Data/Hora: " + dataHora.format(formatter) +
                " | Categoria: " + categoria + " | Descrição: " + descricao;
    }
}
