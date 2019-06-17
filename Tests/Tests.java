import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prova.Boteco;
import prova.Produto;
import prova.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {

    @DisplayName("Valor inicial de ser R$1000")
    @Test
    public void valorInicialdeMilReais(){
        //cenário
        double valorInicial = 1000.00;

        //validação
        Boteco boteco = new Boteco();

        //execução
        assertEquals(valorInicial, boteco.getCaixa());
    }

    @DisplayName("O estoque (quantidade) de um produto não pode ficar/ser negativo. " +
            "Na tentativa de uma entrada negativa, uma ValidationException deverá ser lançada.\n")
    @Test
    public void estoqueNaoPodeSerNegativo(){
        //cenário
        Integer quant = -1;
        Produto produto = new Produto();

        //validação
        Exception exception = assertThrows(ValidationException.class, () -> produto.cadastrar("Birovis", "Cigarréti", 50, 2.50, 3.50, quant));

        //execução
        assertEquals(new ValidationException().getMessage(), exception.getMessage());
    }

    @DisplayName("O valor de venda de um produto não poderá ser menor do que o valor de compra. Neste caso uma " +
            "SecurityException deverá ser lançada, com a mensagem \"" +
            "O valor de venda não deve ser menor do que o valor de compra\"\n ")
    @Test
    public void valorVendaNaoPodeSerMenorQueValorCompra(){
        //cenário
        double valorC = 5.00;
        double valorV = 2.50;
        Produto produto = new Produto();

        //validação
        Exception exception = assertThrows(SecurityException.class, () -> produto.cadastrar("Angeloti", "Papel Higienico", 5, valorC, valorV, 10));

        //execução
        assertEquals("O valor de venda não deve ser menor que o valor de compra", exception.getMessage());

    }

    @DisplayName("O valor de compra de um produto deverá ser maior do que 0. " +
            "Na tentativa de uma entrada negativa, uma ValidationException deverá ser lançada.\n")
    @Test
    public void valorDeCompraDeveraSerMaiorQueZero(){
        //cenário
        double valorC = -5.00;
        Produto produto = new Produto();

        //validação
        Exception exception = assertThrows(SecurityException.class, ()-> produto.cadastrar("Deciao", "Birita de PHP", 20, valorC, 5, 10));

        //execução
        assertEquals(new ValidationException().getMessage(), exception.getMessage());
    }

    @DisplayName("O valor por litro precisa ser testado se está sendo calculado " +
            "corretamente (de acordo com o valor de venda);\n\n")
    @Test
    public void valorLitro(){
        //cenário
        double valorVenda = 5.00;
        Integer volume = 2;
        Produto produto = new Produto();

        //validação
        produto.cadastrar("Mussum", "CAcilds", volume, 2.50, valorVenda, 10);
        double valorLitro = valorVenda/volume;

        //execução
        assertEquals(valorLitro, produto.getValorPorLitro());
    }

      @DisplayName("O nome deverá conter de 3 a 50 caracteres ")
      @Test
      public void nomeDeveSerEntre3e50(){
        //cenário
        String nome = "PHPiJAVA";
        Produto produto = new Produto();

        //validação
        produto.setNome(nome);

        //execução
        assertEquals(nome, produto.getNome());
      }

    @DisplayName("O campo \"nome\" do Produto deverá possuir entre 3 e 50 caracteres, inclusive. " +
            "Caso contrário deverá lançar uma exceção do tipo ValidationException, com a mensagem \"" +
            "Número de caracteres inválido\".\n")
    @Test
    public void nomeNaoDeveSerMaiorQue50() {

        String nome = "LECO LECO LECO LECO LECO LECO LECO LECO LECO LECO LECO LELECO LECO";

        //Cenário
        Produto produto = new Produto();


        //Ação
        Exception ex = assertThrows(ValidationException.class, () -> produto.setNome(nome));


        //Validação
        assertEquals("Número de caracteres inválido", ex.getMessage());

        //correto
        //assertThat(ex.getMessage(), is(equalTo("Número de caractesres inválido")));

    }

    @DisplayName("Metodo que verifica o tamanho do nome menor que 3")
    @Test
    public void nomeDevePossuirMaisDe3Caracteres(){
        //cenário
        String nome = "Bé";
        Produto produto = new Produto();


        //validacao
        Exception ex = assertThrows(ValidationException.class, () -> produto.setNome(nome));

        //execucao
        assertEquals("Número de caracteres inválido", ex.getMessage());

        //correto
        //assertThat(ex.getMessage(), is(equalTo("Número de caractesres inválido")));

    }


}
