package Agenda;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {
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

    private void adicionarCompromisso() {
        System.out.println("\n== Novo Compromisso ==");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.println("Data e Hora (formato dd/MM/yyyy HH:mm ou dd/MM/yyyy):");
        String dataHoraStr = scanner.nextLine();
        try {
            DateTimeFormatter formatter;
            LocalDateTime dataHora;

            // Verifica se a entrada contém horas e minutos
            if (dataHoraStr.trim().length() <= 10) {
                // Formato sem hora
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dataHora = LocalDateTime.parse(dataHoraStr + " 00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            } else {
                // Formato com hora
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                dataHora = LocalDateTime.parse(dataHoraStr, formatter);
            }

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
        compromissos.sort((c1, c2) -> c1.getDataHora().compareTo(c2.getDataHora()));
        for (Compromisso c : compromissos) {
            System.out.println(c);
        }
    }

    private void exibirMenuBusca() {
        BuscarCompromisso buscar = new BuscarCompromisso(compromissos, scanner);
        buscar.exibirMenuBusca();
    }
}
