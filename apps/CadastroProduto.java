package apps;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import classes.Produto;
import classes.Venda;

public class CadastroProduto {

    public static void main(String[] args) throws InterruptedException, IOException {

        int opcao;
        List<Produto> produtos = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\n*****MENU*****\n");
            System.out.println("1 - Incluão de Produtos");
            System.out.println("2 - Consulta de Produtos");
            System.out.println("3 – Listagem de Produtos");
            System.out.println("4 - Realizar Venda");
            System.out.println("5 - Relatório Vendas por Período - detalhado");
            System.out.println("0 - Sair");
            System.out.println("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); 

            if(opcao == 1){
                
                Produto produto = new Produto();

                System.out.println("Cadastro de Produtos");

                System.out.print("Código: ");
                produto.setCodigo(in.nextLine());

                System.out.print("Nome: ");
                produto.setNome(in.nextLine());

                System.out.print("Valor: ");
                produto.setValor(in.nextFloat());

                System.out.print("Quantidade em Estoque: ");
                produto.setQuantidadeemEstoque(in.nextInt());
                
               produtos.add(produto);

                System.out.print("Produto cadastrado com sucesso!");
                in.nextLine(); 
                voltarMenu(in);
            
            } else if(opcao == 2){
                 
                 
                if(produtos.size() == 0){
                    System.out.println("Não existe produtos cadastrados para exibir!");
                    voltarMenu(in);
                    continue;
                }

                System.out.println("Informe o código do produto: ");
                String buscarCodigo = in.nextLine();


                for (var produto : produtos) {
                    if(buscarCodigo.equals(produto.getCodigo())){
                        System.out.println("Produto encontrado!");
                        System.out.println("Código: " + produto.getCodigo());
                        System.out.println("Nome: " + produto.getNome());
                    }
                }
                in.nextLine(); 
                voltarMenu(in);

            } else if(opcao == 3){

                if (produtos.size() == 0) {
                        System.out.println("Não existe produtos cadastrados para exibir!");
                        voltarMenu(in);
                        continue;
                    }
                   
                    System.out.println("Produtos Cadastrados:");
                    System.out.println("Listagem de Produtos:");

                    produtos.forEach((produto) -> {
                        System.out.println("Código: " + produto.getCodigo());
                        System.out.println("Nome: " + produto.getNome());
                        System.out.println("Valor Unitário: " + produto.getValor());
                        System.out.println("Quantidade em Estoque: " + produto.getQuantidadeemEstoque());
                    });

                var vlMax = produtos.stream()
                .max((a, b) -> a.getValor() > b.getValor() ? 1 : -1)
                .orElse(null);
                System.out.printf("Maior valor é: %s\n",vlMax.getValor());

                var vlMin = produtos.stream()
                .min((a, b) -> a.getValor() > b.getValor() ? 1 : -1)
                .orElse(null);
                System.out.printf("Menor valor é: %s\n",vlMin.getValor());

                var vlAvg = produtos.stream().collect(Collectors.averagingDouble(prod -> prod.getValor()));
                System.out.printf("Média é de : %s\n", vlAvg);

                    voltarMenu(in);
                    continue;

            } else if(opcao == 4){
                Venda venda = new Venda();
                Produto produtoVenda = new Produto();

                System.out.println("Realizar Venda");
                venda.setDataVenda(LocalDate.now());

                System.out.println("Digite o código do produto: ");
                
                String buscarCodigo = in.nextLine();

                for (var produto : produtos) {
                    if(buscarCodigo.equals(produto.getCodigo())){
                       produtoVenda = produto; 
                    } else{
                        System.out.println("Produto não encontrado!");
                        voltarMenu(in);
                        continue;
                    }
                } 
                
                venda.setProdutoVendido(produtoVenda);
                System.out.println("Digite a quantidade para a venda: ");
                
                int quantidadeDigitada = in.nextInt();
                if(quantidadeDigitada <= produtoVenda.getQuantidadeemEstoque()){
                    venda.setQuantidadeVendida(quantidadeDigitada);
                    produtoVenda.removerQuantidadeEstoque(quantidadeDigitada);
                }
                else{
                    System.out.printf("Produto possui apenas %s !",produtoVenda.getQuantidadeemEstoque());
                    in.nextLine(); 
                    voltarMenu(in);
                    continue;
                }


                vendas.add(venda);
                System.out.println("Venda realizada com sucesso!");          
                

            } else if(opcao == 5){

            }


        } while(opcao != 0);
        System.out.println("Fim do Programa!");


        in.close();
        
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao MENU PRINCIPAL.");
        in.nextLine();

        //Limpa a toda a tela, deixando novamente apenas o menu.
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            System.out.println("\033[H\033[2J");
           
        }
        System.out.flush();
    }
}
    

