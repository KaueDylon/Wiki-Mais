package org.example;

import dao.ArtigoDAO;
import dao.CategoriaDAO;
import dao.UsuarioDAO;
import entity.Categoria;
import entity.Usuario;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public static void main(String[] args) {

        telaIniciar();

    }

    public static void telaIniciar() {
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
                telaEntrar();
                break;

            case 2:
                telaNovaConta();
                break;
            case 3:
                telaSair();
        }
    }

    public static void telaNovaConta(){
        System.out.println("+---------------------+");
        System.out.println("|      NOVA CONTA     |");
        System.out.println("+---------------------+");
        System.out.print("Nome do usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Senha do usuário:");
        String senhaUsuario = scanner.nextLine();
        System.out.println("Email do usuário:");
        String emailUsuario = scanner.nextLine();
    }

    public static void telaEntrar() {
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
            telaIniciar();
        } else {
            telaOpcaoArtigos();
        }
    }

    public static void telaSair(){
        System.out.println("+---------------------+");
        System.out.println("|       SAINDO...     |");
        System.out.println("+---------------------+");
        scanner.close();
        return;
    }

    public static void telaOpcaoArtigos(){
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

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao){
            case 1:
                telaBuscarArtigo();
                break;
            case 2:
                telaCriarArtigo();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                telaOpcaoArtigos();
                break;
        }

    }

    public static void telaBuscarArtigo(){
        System.out.println("+---------------------+");
        System.out.println("|     FAZER BUSCA     |");
        System.out.println("+---------------------+");
    }

    public static void telaCriarArtigo(){
        System.out.println("+---------------------+");
        System.out.println("|    CRIAR ARTIGO     |");
        System.out.println("+---------------------+");

        System.out.print("Nome do Artigo: ");
        String nomeUsuario = scanner.nextLine();
        System.out.println("Selecione a categoria:");

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> listaCategorias = new ArrayList<>();
        List<Categoria> listarCategorias = categoriaDAO.listarCategorias();
                for(Categoria categ : listarCategorias){
            System.out.println(categ.getId()+") "+categ.getNome());
        }






    }

}