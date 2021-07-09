package classes;

public class Produto {

    private String codigo;
    private String nome;
    private double valor;
    private int quantidadeemEstoque;

    public Produto(){}

    public Produto(String codigo, String nome, double valor, int quantidadeemEstoque){
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.quantidadeemEstoque = quantidadeemEstoque;
    }

    
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
   public int getQuantidadeemEstoque() {
       return quantidadeemEstoque;
    }
      public void setQuantidadeemEstoque(int quantidadeemEstoque) {
        this.quantidadeemEstoque = quantidadeemEstoque;
    }    

    public int removerQuantidadeEstoque(int quantidade) {
        return quantidadeemEstoque = quantidadeemEstoque - quantidade;
    } }