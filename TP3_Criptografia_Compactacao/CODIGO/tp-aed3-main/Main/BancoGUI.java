package Main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import CRUD.ContaMODEL;
import CRUD.Crud;
import TP2.Huffman;
import TP3.CasamentoPadrao;

public class BancoGUI {

    public static void printOpcoesMenuPrincipal() {
        cls();
        System.out.println("=== Menu Principal ===");

        System.out.println("0) Fechar Aplicacao");

        System.out.println("1) CRUD");

        System.out.println("2) Algoritmos TP-1 (incompleto)");
        System.out.println("   -Intercalacao Balanceada");
        System.out.println("   -Arvore B+");
        System.out.println("   -Hashing Extendido");

        System.out.println("3) Algoritmos TP-2");
        System.out.println("   -Huffman");
        System.out.println("   -LZW");
        System.out.println("4) Algoritmos TP-3");
        System.out.println("   -Cifra Ceasar");
        System.out.println("   -KMP");

        System.out.println("69) DEV debug comands");

        System.out.println("\n= Digite uma opcao para continuar: ");
    }

    public static void printOpcoesCRUD() {
        cls();
        System.out.println("=== Menu CRUD ===");
        System.out.println("0) Sair");

        System.out.println("1) Create");
        System.out.println("2) Update");
        System.out.println("3) Read");
        System.out.println("4) Delete");
        System.out.println("\n= Digite uma opcao para continuar: ");
    }

    public static void printOpcoesTP1() {
        cls();
        System.out.println("Nao implementado nesse tp");
    }

    public static void printOpcoesTP2() {
        cls();
        System.out.println("=== Menu TP2 ===");
        System.out.println("0) Sair");

        System.out.println("= Huffman =");
        System.out.println("1) Huffman Comprimir");
        System.out.println("2) Huffman Descomprimir");
        // System.out.println("= LZW =");
        // System.out.println("3) LZW Comprimir");
        // System.out.println("4) LZW Descomprimir");
        System.out.println("\n= Digite uma opcao para continuar: ");

    }

    public static void printOpcoesTP3() {
        cls();
        System.out.println("=== Menu TP3 ===");
        System.out.println("0) Sair");

        System.out.println("1) Criptografia (Cifra de Ceasar)");
        System.out.println("2) Casamento de Padroes (KMP)");
        System.out.println("\n= Digite uma opcao para continuar: ");

    }

    public static void printOpcoesDEV() {
        cls();
        System.out.println("=== Menu DEV ===");
        System.out.println("0) Sair");

        System.out.println("1) Print All Registers");
        System.out.println("2) Create Random Users");

    }

    public static void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void Menu() {
        Scanner sc = new Scanner(System.in);
        short opMP;

        do {
            printOpcoesMenuPrincipal();
            opMP = Short.parseShort(sc.nextLine());

            switch (opMP) {

                case 1:
                    printOpcoesCRUD();
                    short opCRUD = Short.parseShort(sc.nextLine());

                    switch (opCRUD) {
                        case 1:
                            guiCreate(sc);
                            break;
                        case 2:
                            guiUpdate(sc);
                            break;
                        case 3:
                            guiRead(sc);
                            break;
                        case 4:
                            guiDelete(sc);
                            break;

                        default:
                            break;
                    }

                    break;

                case 2:
                    printOpcoesTP1();
                    break;

                case 3:
                    printOpcoesTP2();
                    short opTP2 = Short.parseShort(sc.nextLine());

                    switch (opTP2) {
                        case 1:
                            Huffman.comprimirArquivo("banco.dat");
                            break;
                        case 2:

                            Huffman.descomprimirArquivo("Huffman_banco.dat");
                            break;

                        default:
                            break;
                    }
                    break;
                case 4:
                    printOpcoesTP3();
                    short opTP3 = Short.parseShort(sc.nextLine());

                    switch (opTP3) {
                        case 1:
                            System.out.println(
                                    "A senha é salva no arquivo criptografada e descriptografada apos ler o registro");
                            System.out.println(
                                    "A senha é criptografada em : Crud.java na funcao writeRegistro do campo senha");
                            System.out.println(
                                    "A senha é descriptografada em : Crud.java na funcao readRegistro do campo senha");

                            break;
                        case 2:

                            guiCasamentoPadroes(sc);
                            break;

                        default:
                            break;
                    }
                    break;

                case 69:
                    printOpcoesDEV();
                    short opDEV = Short.parseShort(sc.nextLine());

                    switch (opDEV) {
                        case 1:
                            Crud c = new Crud("banco.dat");
                            System.out.println(c.getAllRegistros());
                            break;
                        case 2:
                            System.out.println("Digite o numero de usuario aleatorios que deseja criar");
                            int q = Integer.parseInt(sc.nextLine());

                            for (int i = 0; i < q; i++) {
                                createRandomUser();

                            }

                            break;

                        default:
                            break;
                    }
                    break;
                default:

                    break;
            }
            enterToContinue();
        } while (opMP != 0);

    }

    public static void guiCasamentoPadroes(Scanner sc) {
        System.out.println("Digite o padrao que deseja encontrar no arquivo: ");
        String padrao = sc.nextLine();
        Crud CRUD = new Crud("banco.dat");
        System.out.println(CasamentoPadrao.KMP(padrao, CRUD.arqToSring()));

    }

    public static boolean isFIM(String s) {
        return (!(s.length() == 3 && (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M')));
    }

    public static void guiRead(Scanner sc) {
        System.out.println("Digite o ID que quer buscar:");
        Crud CRUD = new Crud("banco.dat");

        System.out.println(CRUD.read(Integer.parseInt(sc.nextLine()))); // Ler o model no arquivo usando o Crud

    }

    public static void guiDelete(Scanner sc) {
        System.out.println("Digite o ID da conta que você quer apagar:");
        Crud CRUD = new Crud("banco.dat");
        CRUD.delete(Integer.parseInt(sc.nextLine())); // Deletar o model no arquivo usando o Crud
    }

    public static void guiUpdate(Scanner sc) {
        int id;
        String nome, cpf, cidade, usuario, senha;
        float saldo;
        ArrayList<String> emails = new ArrayList<>();

        System.out.println("Digite o id da conta para atualiza-la:");
        id = Integer.parseInt(sc.nextLine());
        System.out.println("Digite o nome: ");
        nome = sc.nextLine();
        System.out.println("Digite o cpf: ");
        cpf = sc.nextLine();
        System.out.println("Digite a cidade: ");
        cidade = sc.nextLine();
        System.out.println("Digite os emails, escreva FIM para parar");
        String entrada;
        do {
            entrada = sc.nextLine();
            emails.add(entrada);
        } while (isFIM(entrada));

        emails.remove(emails.size() - 1);

        System.out.println("Digite o nome de Usuario");
        usuario = sc.nextLine();
        System.out.println("Digite a senha");
        senha = sc.nextLine();
        System.out.println("Digite o saldo:");
        saldo = Float.parseFloat(sc.nextLine());
        Crud CRUD = new Crud("banco.dat");
        CRUD.update(new ContaMODEL(id, nome, emails, usuario, senha, cpf, cidade, 0, saldo, true)); // Modificar o model
                                                                                                    // no arquivo usando
                                                                                                    // o Crud
    }

    public static void guiCreate(Scanner sc) {
        String nome, cpf, cidade, usuario, senha;
        float saldo;
        ArrayList<String> emails = new ArrayList<>();

        System.out.println("Digite o nome: ");
        nome = sc.nextLine();
        System.out.println("Digite o cpf: ");
        cpf = sc.nextLine();
        System.out.println("Digite a cidade: ");
        cidade = sc.nextLine();
        System.out.println("Digite os emails, escreva FIM para parar");
        String entrada;
        do {
            entrada = sc.nextLine();
            emails.add(entrada);
        } while (isFIM(entrada));

        emails.remove(emails.size() - 1);

        System.out.println("Digite o nome de Usuario");
        usuario = sc.nextLine();
        System.out.println("Digite a senha");
        senha = sc.nextLine();
        System.out.println("Digite o saldo:");
        saldo = Float.parseFloat(sc.nextLine());

        Crud CRUD = new Crud("banco.dat");
        CRUD.create(new ContaMODEL(nome, emails, usuario, senha, cpf, cidade, 0, saldo));
    }

    public static void createRandomUser() {
        Random a = new Random();
        int userRID = a.nextInt(100000);
        Crud CRUD = new Crud("banco.dat");
        ArrayList<String> tempArray = new ArrayList<>();
        tempArray.add(userRID + "@gmail.com");
        CRUD.create(new ContaMODEL("DEV-test-" + userRID,
                tempArray,
                "DEV-test-" + userRID,
                userRID + "",
                userRID + "",
                userRID + "city",
                0,
                userRID));
    }

    public static void enterToContinue() {
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void transfererir() {
        Crud CRUD = new Crud("banco.dat");
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o ID da conta que você quer debitar:");
        ContaMODEL aDebitar = CRUD.read(Integer.parseInt(sc.nextLine()));
        System.out.println("Digite o ID da conta que você quer creditar:");
        ContaMODEL aCreditar = CRUD.read(Integer.parseInt(sc.nextLine()));

        System.out.println("Digite o valor da transferencia:");
        float valor = Float.parseFloat(sc.nextLine());

        if (aDebitar.getSaldo() < valor) {
            System.out.println("Erro - sua conta nao possui fundos suficientes");
        } else {
            aDebitar.setSaldo(aDebitar.getSaldo() - valor);
            aDebitar.setTransferencias(aDebitar.getTransferencias() + 1);
            aCreditar.setSaldo(aCreditar.getSaldo() + valor);
            aCreditar.setTransferencias(aCreditar.getTransferencias() + 1);
            CRUD.update(aDebitar);
            CRUD.update(aCreditar);
        }

        sc.close();

    }

    public static void main(String[] args) {
        Menu();
    }

}
