package Agenda;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BuscarCompromisso {
    private ArrayList<Compromisso> compromissos;
    private Scanner scanner;

    public BuscarCompromisso(ArrayList<Compromisso> compromissos, Scanner scanner) {
        this.compromissos = compromissos;
        this.scanner = scanner;
    }

    public void exibirMenuBusca() {
        int opcao = -1;
        do {
            System.out.println("\n=== MENU DE BUSCA ===");
            System.out.println("1. Buscar por Título");
            System.out.println("2. Buscar por Data Específica");
            System.out.println("3. Buscar por Mês");
            System.out.println("4. Buscar por Ano");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer
            } else {
                System.out.println("Entrada inválida! Por favor, digite um número.");
                scanner.nextLine(); // Limpa a entrada inválida
                continue;
            }

            switch (opcao) {
                case 1:
                    buscarPorTitulo();
                    break;
                case 2:
                    buscarPorData();
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

    private void buscarPorTitulo() {
        System.out.print("Digite o título: ");
        String titulo = scanner.nextLine().toLowerCase();
        compromissos.stream()
                .filter(c -> c.getTitulo().toLowerCase().contains(titulo))
                .forEach(System.out::println);
    }

    private void buscarPorData() {
        System.out.print("Digite a data (formato dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();
        try {
            LocalDateTime dataBusca = LocalDateTime.parse(dataStr + " 00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            compromissos.stream()
                    .filter(c -> c.getDataHora().toLocalDate().equals(dataBusca.toLocalDate()))
                    .forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Formato de data inválido!");
        }
    }

    private void buscarPorMes() {
        System.out.print("Digite o mês (1-12): ");
        if (scanner.hasNextInt()) {
            int mes = scanner.nextInt();
            compromissos.stream()
                    .filter(c -> c.getDataHora().getMonthValue() == mes)
                    .forEach(System.out::println);
        } else {
            System.out.println("Entrada inválida! Por favor, insira um número entre 1 e 12.");
            scanner.nextLine(); // Limpa a entrada inválida
        }
    }

    private void buscarPorAno() {
        System.out.print("Digite o ano (ex: 2024): ");
        if (scanner.hasNextInt()) {
            int ano = scanner.nextInt();
            compromissos.stream()
                    .filter(c -> c.getDataHora().getYear() == ano)
                    .forEach(System.out::println);
        } else {
            System.out.println("Entrada inválida! Por favor, insira um ano válido.");
            scanner.nextLine(); // Limpa a entrada inválida
        }
    }
}
