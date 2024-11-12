package agendaProvisoria;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
 
class Compromisso {
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
 
    public String getTitulo() { return titulo; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getCategoria() { return categoria; }
 
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Título: " + titulo + " | Data/Hora: " + dataHora.format(formatter) + 
               " | Categoria: " + categoria + " | Descrição: " + descricao;
    }
}
 
class Agenda {
    private ArrayList<Compromisso> compromissos;
    private Scanner scanner;
 
    public Agenda() {
        compromissos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
 
    public void executar() {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); 
 
            switch (opcao) {
                case 1:
                    adicionarCompromisso();
                    break;
                case 2:
                    listarCompromissosHoje();
                    break;
                case 3:
                    listarTodosCompromissos();
                    break;
                case 4:
                    exibirMenuBusca();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
 
    private void exibirMenu() {
        LocalDateTime hoje = LocalDateTime.now();
        String dataAtual = hoje.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("\n=== AGENDA PESSOAL ===");
        System.out.println("Data Atual: " + dataAtual);
        System.out.println("1. Adicionar Compromisso");
        System.out.println("2. Compromissos de Hoje");
        System.out.println("3. Todos os Compromissos");
        System.out.println("4. Buscar Outros Compromissos");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
 
    private void exibirMenuBusca() {
        int opcao;
        do {
            System.out.println("\n=== MENU DE BUSCA ===");
            System.out.println("1. Buscar por Título");
            System.out.println("2. Buscar por Data Específica");
            System.out.println("3. Buscar por Mês");
            System.out.println("4. Buscar por Ano");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
 
            opcao = scanner.nextInt();
            scanner.nextLine();
 
            switch (opcao) {
                case 1:
                    buscarPorTitulo();
                    break;
                case 2:
                    buscarPorDataCompleta();
                    break;
                case 3:
                    buscarPorMes();
                    break;
                case 4:
                    buscarPorAno();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
 
    private void adicionarCompromisso() {
        System.out.println("\n== Novo Compromisso ==");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.println("Data e Hora (formato dd/MM/yyyy HH:mm):");
        String dataHoraStr = scanner.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);
            compromissos.add(new Compromisso(titulo, descricao, dataHora, categoria));
            System.out.println("Compromisso adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Formato de data/hora inválido!");
        }
    }
 
    private void listarCompromissosHoje() {
        System.out.println("\n== Compromissos de Hoje ==");
        LocalDateTime hoje = LocalDateTime.now();
        boolean encontrou = false;
        System.out.println("Data: " + hoje.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        for (Compromisso c : compromissos) {
            if (c.getDataHora().toLocalDate().equals(hoje.toLocalDate())) {
                System.out.println(c);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum compromisso para hoje.");
        }
    }
 
    private void listarTodosCompromissos() {
        System.out.println("\n== Todos os Compromissos ==");
        if (compromissos.isEmpty()) {
            System.out.println("Nenhum compromisso cadastrado.");
            return;
        }
        // Ordenar compromissos por data
        compromissos.sort((c1, c2) -> c1.getDataHora().compareTo(c2.getDataHora()));
        for (Compromisso c : compromissos) {
            System.out.println(c);
        }
    }
 
    private void buscarPorTitulo() {
        System.out.println("\n== Buscar por Título ==");
        System.out.print("Digite o título: ");
        String titulo = scanner.nextLine().toLowerCase();
 
        boolean encontrou = false;
        for (Compromisso c : compromissos) {
            if (c.getTitulo().toLowerCase().contains(titulo)) {
                System.out.println(c);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum compromisso encontrado com este título.");
        }
    }
 
    private void buscarPorMes() {
        System.out.println("\n== Buscar por Mês ==");
        System.out.print("Digite o mês (1-12): ");
        int mes = scanner.nextInt();
 
        boolean encontrou = false;
        for (Compromisso c : compromissos) {
            if (c.getDataHora().getMonthValue() == mes) {
                System.out.println(c);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum compromisso encontrado neste mês.");
        }
    }
 
    private void buscarPorAno() {
        System.out.println("\n== Buscar por Ano ==");
        System.out.print("Digite o ano (ex: 2024): ");
        int ano = scanner.nextInt();
 
        boolean encontrou = false;
        for (Compromisso c : compromissos) {
            if (c.getDataHora().getYear() == ano) {
                System.out.println(c);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum compromisso encontrado neste ano.");
        }
    }
 
    private void buscarPorDataCompleta() {
        System.out.println("\n== Buscar por Data Específica ==");
        System.out.println("Digite a data (formato dd/MM/yyyy):");
        String dataStr = scanner.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime dataBusca = LocalDateTime.parse(dataStr + " 00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            boolean encontrou = false;
            for (Compromisso c : compromissos) {
                LocalDateTime dataCompromisso = c.getDataHora();
                if (dataCompromisso.toLocalDate().equals(dataBusca.toLocalDate())) {
                    System.out.println(c);
                    encontrou = true;
                }
            }
            if (!encontrou) {
                System.out.println("Nenhum compromisso encontrado para esta data.");
            }
        } catch (Exception e) {
            System.out.println("Formato de data inválido! Use o formato dd/MM/yyyy");
        }
    }
}
 
public class poo {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        agenda.executar();
    }
}
