package org.example;

import dao.ArtigoDAO;
import dao.CategoriaDAO;
import dao.UsuarioDAO;
import entity.Artigo;
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

    public static void telaNovaConta() {
        System.out.println("+---------------------+");
        System.out.println("|      NOVA CONTA     |");
        System.out.println("+---------------------+");
        System.out.print("Nome do usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Senha do usuário: ");
        String senhaUsuario = scanner.nextLine();
        System.out.print("Email do usuário: ");
        String emailUsuario = scanner.nextLine();

        Usuario usuario = new Usuario(1, nomeUsuario, emailUsuario, senhaUsuario);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.cadastrarUsuario(usuario);
        telaIniciar();

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
        } else if (usuario.getCargo() == 3) {
            System.out.println("");
            System.out.println("Usuário " + usuario.getNome());
            System.out.println("foi banido do WikiMais.");
        } else {
            telaOpcaoArtigos(usuario);
        }
    }

    public static void telaSair() {
        System.out.println("+---------------------+");
        System.out.println("|       SAINDO...     |");
        System.out.println("+---------------------+");
        scanner.close();
    }

    public static void telaOpcaoArtigos(Usuario usuario) {
        System.out.println("+---------------------+");
        System.out.println("|      WIKI-MAIS      |");
        System.out.println("+---------------------+");
        System.out.println("|      = OPÇÕES: =    |");
        System.out.println("|     = BEM-VINDO =   |");
        System.out.println("|                     |");
        System.out.println("| 1) Buscar um        |");
        System.out.println("|    artigo           |");
        System.out.println("| 2) Criar um         |");
        System.out.println("|    novo artigo      |");
        System.out.println("+---------------------+");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                telaBuscarArtigo();
                break;
            case 2:
                telaCriarArtigo(usuario);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                telaOpcaoArtigos(usuario);
                break;
        }

    }

    public static void telaBuscarArtigo() { // ARRUMAR
        System.out.println("+---------------------+");
        System.out.println("|     FAZER BUSCA     |");
        System.out.println("+---------------------+");

        ArtigoDAO artigoDAODAO = new ArtigoDAO();
        List<Artigo> listarArtigo = artigoDAO.listarCategorias();
//        for (Categoria  : listarCategorias) {
//            System.out.println(categ.getId() + ") " + categ.getNome());
//        }

    }

    public static void telaCriarArtigo(Usuario usuario) {
        System.out.println("+---------------------+");
        System.out.println("|     CRIAR ARTIGO    |");
        System.out.println("+---------------------+");

        System.out.print("Nome do Artigo: ");
        String nomeArtigo = scanner.nextLine();
        System.out.println("Deseja criar uma categoria?");
        System.out.println("=== (1) SIM - (2) NÃO ===");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        if (opcao == 1) {
            telaCriarCategoria(usuario);
        } else if (opcao == 2) {

            System.out.println("Selecione a categoria:");

            CategoriaDAO categoriaDAO = new CategoriaDAO();
            List<Categoria> listarCategorias = categoriaDAO.listarCategorias();
            for (Categoria categ : listarCategorias) {
                System.out.println(categ.getId() + ") " + categ.getNome());
            }

            opcao = scanner.nextInt();

            System.out.println("Insira o 'WikiTexto':");
            String wikiTexto = scanner.next();
            scanner.nextLine();

            Artigo artigo = new Artigo(nomeArtigo, wikiTexto);
            ArtigoDAO artigoDAO = new ArtigoDAO();
            artigoDAO.cadastrarArtigo(opcao, artigo, usuario);

        } else {
            System.out.println("Opção inválida. Tente novamente.");
            telaCriarArtigo(usuario);
        }

    }

    public static void telaCriarCategoria(Usuario usuario) {
        System.out.println("+---------------------+");
        System.out.println("|     CRIAR CATEG.    |");
        System.out.println("+---------------------+");

        System.out.print("Nome da Categoria: ");
        String nomeCategoria = scanner.nextLine();

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria categoria = new Categoria(nomeCategoria);
        categoriaDAO.cadastrarCategorias(categoria);
        System.out.println("Categoria criada com sucesso.");
        telaCriarArtigo(usuario);

    }

}