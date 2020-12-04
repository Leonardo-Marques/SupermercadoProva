package Main;

import Utils.Inputs;

import java.util.ArrayList;

public class Pedido {

    private static ArrayList<Item> listaDeItens = new ArrayList();
    private static double valorTotalDoPedido = 0;
    private static ArrayList<Double> troco = new ArrayList<>();

    public static void calculaValorTotal() {
        double subTotal = 0;
        for (Item item : listaDeItens) {
            subTotal += item.getValorDoItem();
        }
        valorTotalDoPedido = subTotal;
    }

    public static boolean adicionaItemNaLista(Produto produto, int quantidade) {
        for (Item item : listaDeItens) {
            if (item.getProduto().getNome().equalsIgnoreCase(produto.getNome())) {
                Estoque.darBaixaEmEstoque(item.getProduto().getId(), quantidade);
                item.setQuantidade(item.getQuantidade() + quantidade);
                item.defineValorTotal();
                System.out.println("Foi adicionada a quantidade ao item já existente.");
                return false;
            }
        }
        listaDeItens.add(new Item(produto, quantidade));
        Estoque.darBaixaEmEstoque(produto.getId(), quantidade);
        System.out.println("Foi adicionado o produto na lista de compras.");
        return true;
    }

    public static void imprimePedido() {
        System.out.println("                              NOTA FISCAL");
        System.out.printf("ID       |NOME            |PRECO UN           |QUANTIDADE   |PRECO ITEM \n");
        for (Item item : listaDeItens) {
            System.out.printf("%-8d | %-14s | R$%-15.2f | %-10d  | R$%.2f\n"
                    , item.getProduto().getId(), item.getProduto().getNome(), item.getProduto().getPreco(), item.getQuantidade(), item.getValorDoItem());
        }
        imprimeValorTotal();
    }

    private static void imprimeValorTotal() {
        System.out.println();
        System.out.printf("Total: R$%.2f", valorTotalDoPedido);
        System.out.println("________________________________________________________________________");
        System.out.println();
        System.out.println();
    }

    public static void adicionaItem(){
        String nome = recebeNomeDoTeclado();
        int quantidade = recebeQuantidadeDoTeclado();
        Produto produto = Estoque.encontraProduto(nome);
        if(produto!= null && produto.getQuantidadeEmEstoque() > quantidade){
            adicionaItemNaLista(produto,quantidade);
            calculaValorTotal();
        } else {
            System.out.println("Produto nao encontrado ou sem estoque disponivel");
        }

    }

    public static boolean calculaTroco(){
        System.out.println("Digite o valor do pagamento");
        System.out.println("valor pedido: "+ getValorTotalDoPedido());
        double valor = Inputs.inputDouble();
        if (valor < getValorTotalDoPedido()){
            System.out.println("Dinheiro insuficiente para finalizar a transação");
            return true;
        }
        System.out.println("troco total: " + (valor - getValorTotalDoPedido()));
        System.out.println("menor troco: " + (menorTroco(valor - getValorTotalDoPedido())));
        return false;
    }
    public static ArrayList<Double> menorTroco(double totalTroco){
        totalTroco = Math.round(totalTroco * 100.0)/100.0;
        //Notas possiveis = 100,50,20,10,5,2,1,0.5,0.25,0.1,0.05 e 0.01;
        Double[] notas ={100.0,50.0,20.0,10.0,5.0,2.0,1.0,0.5,0.25,0.1,0.05,0.01};
        for (Double n: notas) {
            if (n <= totalTroco){
                troco.add(n);
                totalTroco-=n;
            }
        }
        if (totalTroco <= 0) {
            return troco;
        }else
        return menorTroco(totalTroco);
    }

    public static String recebeNomeDoTeclado(){
        System.out.print("Digite o nome: ");
        return Inputs.inputString();
    }


    public static int recebeQuantidadeDoTeclado(){
        System.out.print("Digite a quantidade: ");
        return Inputs.inputInt();
    }

    public void limparCarrinho() {
        listaDeItens.clear();
    }

    public static ArrayList<Item> getListaDeItens() {
        return listaDeItens;
    }

    public void setListaDeItens(ArrayList<Item> listaDeItens) {
        Pedido.listaDeItens = listaDeItens;
    }

    public static double getValorTotalDoPedido() {
        return valorTotalDoPedido;
    }

    public void setValorTotalDoPedido(double valorTotalDoPedido) {
        Pedido.valorTotalDoPedido = valorTotalDoPedido;
    }
}
