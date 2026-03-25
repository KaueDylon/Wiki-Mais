package org.example;

import dao.ArtigoDAO;
import dao.CargoDAO;
import dao.CategoriaDAO;
import dao.UsuarioDAO;
import entity.Artigo;
import entity.Categoria;
import entity.Usuario;

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
        System.out.println("| 3) Editar um        |");
        System.out.println("|    artigo           |");
        if (usuario.getId() == 2) {
            System.out.println("| 4) ADMIN            |");
        }
        System.out.println("+---------------------+");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                telaBuscarArtigo(usuario);
                break;
            case 2:
                telaCriarArtigo(usuario);
                break;
            case 3:
                telaEditarArtigo(usuario);
                break;
            case 4:
                if (usuario.getId() != 2) {
                    telaOpcaoArtigos(usuario);
                    break;
                }
                telaAdmin(usuario);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                telaOpcaoArtigos(usuario);
                break;
        }

    }

    public static void telaBuscarArtigo(Usuario usuario) { // misericordia
        System.out.println("+---------------------+");
        System.out.println("|     FAZER BUSCA     |");
        System.out.println("+---------------------+");

        ArtigoDAO artigoDAO = new ArtigoDAO();
        List<Artigo> listaArtigo = artigoDAO.listarArtigo();
        for (Artigo art : listaArtigo) {
            System.out.println("");
            System.out.println("(" + art.getId() + ") | Artigo: " + art.getNome());
            System.out.println("Criado em " + art.getCriacao() + " por " + art.getNomeUsuario());
            System.out.println("-".repeat(24));
        }

        System.out.println("Digite o ID do artigo que deseja:");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        Artigo artigo = artigoDAO.listarArtigoID(opcao);
        System.out.println("");
        System.out.println("Artigo: " + artigo.getNome());
        System.out.println(artigo.getWikitexto());
        System.out.println("-".repeat(24));

        System.out.println("Você deseja:");
        System.out.println("=== (1) INICIO - (2) ARTIGOS ===");
        System.out.println(" === (QUALQUER TECLA) SAIR ===");
        opcao = scanner.nextInt();
        scanner.nextLine();
        if (opcao == 1) {
            telaOpcaoArtigos(usuario);
        } else if (opcao == 2) {
            telaBuscarArtigo(usuario);
        } else {
            telaSair();
        }

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

    public static void telaAdmin(Usuario usuario) {

        System.out.println("+---------------------+");
        System.out.println("|    ADMINISTRAÇÃO    |");
        System.out.println("+---------------------+");
        System.out.println("|     = OPÇÕES: =     |");
        System.out.println("|                     |");
        System.out.println("| 1) Deletar um       |");
        System.out.println("|    artigo           |");
        System.out.println("| 2) Banir/Desbanir   |");
        System.out.println("|    um usuário       |");
        System.out.println("| 3) Sair             |");
        System.out.println("+---------------------+");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                telaDeletarArtigo(usuario);
                break;
            case 2:
                telaBanimentosUsuario(usuario);
                break;
            case 3:
                telaSair();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                telaAdmin(usuario);
                break;
        }
    }

    public static void telaBanimentosUsuario(Usuario usuario) {
        System.out.println("+---------------------+");
        System.out.println("|      BANIMENTOS     |");
        System.out.println("+---------------------+");

        System.out.println("Deseja banir ou desbanir?");
        System.out.println("=== (1) BANIR - (2) DESBANIR ===");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            System.out.println("Insira o ID do usuário ");
            System.out.print("que será banido: ");

            int id = scanner.nextInt();
            scanner.nextLine();

            CargoDAO cargoDAO = new CargoDAO();
            cargoDAO.banirUsuario(id);
            System.out.println("Banimento concluído.");
            telaAdmin(usuario);

        } else if (opcao == 2) {
            System.out.println("Insira o ID do usuário ");
            System.out.print("que será desbanido: ");

            int id = scanner.nextInt();
            scanner.nextLine();

            CargoDAO cargoDAO = new CargoDAO();
            cargoDAO.desbanirUsuario(id);
            System.out.println("Desbanimento concluído.");
            telaAdmin(usuario);
        } else {
            System.out.println("Opção inválida.");
            telaBanimentosUsuario(usuario);
        }

    }

    public static void telaDeletarArtigo(Usuario usuario) {
        System.out.println("+---------------------+");
        System.out.println("|     DEL. ARTIGOS    |");
        System.out.println("+---------------------+");

        System.out.println("Insira o ID do artigo ");
        System.out.print("que será deletado: ");

        int id = scanner.nextInt();
        scanner.nextLine();

        ArtigoDAO artigoDAO = new ArtigoDAO();
        artigoDAO.deletarArtigo(id);
        System.out.println("Artigo deletado com sucesso.");
        telaAdmin(usuario);

    }

    public static void telaEditarArtigo(Usuario usuario){
        System.out.println("+---------------------+");
        System.out.println("|     EDT. ARTIGOS    |");
        System.out.println("+---------------------+");

        System.out.println("Insira o ID do artigo ");
        System.out.print("que será editado: ");

        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Escreva o novo WikiTexto ");
        System.out.print("que deste artigo: ");

        String wikiTexto = scanner.next();
        scanner.nextLine();

        ArtigoDAO artigoDAO = new ArtigoDAO();
        artigoDAO.editarArtigo(id,wikiTexto);
        System.out.println("Artigo editado com sucesso.");
        telaBuscarArtigo(usuario);
    }

}