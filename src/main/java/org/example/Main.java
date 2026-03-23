package org.example;

import dao.CategoriaDAO;
import dao.UsuarioDAO;
import entity.Categoria;
import entity.Usuario;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean continuar = true;
        Scanner scanner = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();


        while (continuar) {

            System.out.println("");
            System.out.println("+---------------------+");
            System.out.println("|      WIKI-MAIS      |");
            System.out.println("+---------------------+");
            System.out.println("|     = OPÇÕES: =     |");
            System.out.println("|                     |");
            System.out.println("| 1) Entrar           |");
            System.out.println("| 2) Nova Conta       |");
            System.out.println("| 3) Sair             |");
            System.out.println("+---------------------+");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("+---------------------+");
                    System.out.println("|        ENTRAR       |");
                    System.out.println("+---------------------+");
                    System.out.print("Nome do usuário: ");
                    String nomeUsuario = scanner.nextLine();
                    System.out.print("Senha do usuário: ");
                    String senhaUsuario = scanner.nextLine();

                    Usuario usuario = usuarioDAO.loginUsuario(nomeUsuario, senhaUsuario);
                    if (usuario == null) {
                        System.out.println("");
                        System.out.println("Usuário não encontrado. Tente novamente");
                        System.out.println("ou crie uma nova conta.");
                    } else {
                        System.out.println("+---------------------+");
                        System.out.println("|      WIKI-MAIS      |");
                        System.out.println("+---------------------+");
                        System.out.println("|      = OPÇÕES: =    |");
                        System.out.println("|                     |");
                        System.out.println("| 1) Buscar um        |");
                        System.out.println("|    artigo           |");
                        System.out.println("| 2) Criar um         |");
                        System.out.println("|    novo artigo      |");
                        System.out.println("+---------------------+");
                    }

                    break;
                case 2:
                    System.out.println("+---------------------+");
                    System.out.println("|      NOVA CONTA     |");
                    System.out.println("+---------------------+");
                    System.out.print("Nome do usuário: ");
                    nomeUsuario = scanner.nextLine();
                    System.out.print("Senha do usuário:");
                    senhaUsuario = scanner.nextLine();
                    System.out.println("Email do usuário:");
                    String emailUsuario = scanner.nextLine();
                    break;
                case 3:
                    System.out.println("+---------------------+");
                    System.out.println("|       SAINDO...     |");
                    System.out.println("+---------------------+");
                    scanner.close();
                    return;
            }


        }


    }

}