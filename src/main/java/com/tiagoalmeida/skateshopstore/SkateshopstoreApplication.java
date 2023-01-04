package com.tiagoalmeida.skateshopstore;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tiagoalmeida.skateshopstore.domain.Categoria;
import com.tiagoalmeida.skateshopstore.domain.Cidade;
import com.tiagoalmeida.skateshopstore.domain.Cliente;
import com.tiagoalmeida.skateshopstore.domain.Endereco;
import com.tiagoalmeida.skateshopstore.domain.Estado;
import com.tiagoalmeida.skateshopstore.domain.ItemPedido;
import com.tiagoalmeida.skateshopstore.domain.Pagamento;
import com.tiagoalmeida.skateshopstore.domain.PagamentoComBoleto;
import com.tiagoalmeida.skateshopstore.domain.PagamentoComCartao;
import com.tiagoalmeida.skateshopstore.domain.Pedido;
import com.tiagoalmeida.skateshopstore.domain.Produto;
import com.tiagoalmeida.skateshopstore.domain.enums.EstadoPagamento;
import com.tiagoalmeida.skateshopstore.domain.enums.TipoCliente;
import com.tiagoalmeida.skateshopstore.repository.CategoriaRepository;
import com.tiagoalmeida.skateshopstore.repository.CidadeRepository;
import com.tiagoalmeida.skateshopstore.repository.ClienteRepository;
import com.tiagoalmeida.skateshopstore.repository.EnderecoRepository;
import com.tiagoalmeida.skateshopstore.repository.EstadoRepository;
import com.tiagoalmeida.skateshopstore.repository.ItemPedidoRepository;
import com.tiagoalmeida.skateshopstore.repository.PagamentoRepository;
import com.tiagoalmeida.skateshopstore.repository.PedidoRepository;
import com.tiagoalmeida.skateshopstore.repository.ProdutoRepository;

@SpringBootApplication
public class SkateshopstoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SkateshopstoreApplication.class, args);
	}
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "calcado");
		Categoria cat2 = new Categoria(null, "camiseta");
		Categoria cat3 = new Categoria(null, "acessorio");
		Categoria cat4 = new Categoria(null, "skate");
		Categoria cat5 = new Categoria(null, "peças");
		Categoria cat6 = new Categoria(null, "bermuda");
		Categoria cat7 = new Categoria(null, "bone");
		
		
		
		Produto p1 = new Produto(null, "nike sb", 150.00);
		Produto p2 = new Produto(null, "bermuda primitive", 130.00);
		Produto p3 = new Produto(null, "camiseta Lakers", 70.00);
		Produto p4 = new Produto(null, "chinelo havaianas", 50.00);
		Produto p5 = new Produto(null, "camiseta nike sb", 100.00);
		Produto p6 = new Produto(null, "tenis DC", 200.00);
		Produto p7 = new Produto(null, "relogio adidas", 300.00);
		Produto p8 = new Produto(null, "bone primitive", 100.00);
		Produto p9 = new Produto(null, "rodas bones peralta", 350.00);
		Produto p10 = new Produto(null, "skate dogtown Cruiser", 250.00);
		Produto p11 = new Produto(null, "truck independente 149", 350.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p4,p6));
		cat2.getProdutos().addAll(Arrays.asList(p3,p5));
		cat3.getProdutos().addAll(Arrays.asList(p7));
		cat4.getProdutos().addAll(Arrays.asList(p10));
		cat5.getProdutos().addAll(Arrays.asList(p9, p11));
		cat6.getProdutos().addAll(Arrays.asList(p2));
		cat7.getProdutos().addAll(Arrays.asList(p8));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat6));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		p4.getCategorias().addAll(Arrays.asList(cat1));
		p5.getCategorias().addAll(Arrays.asList(cat2));
		p6.getCategorias().addAll(Arrays.asList(cat1));
		p7.getCategorias().addAll(Arrays.asList(cat3));
		p8.getCategorias().addAll(Arrays.asList(cat7));
		p9.getCategorias().addAll(Arrays.asList(cat5));
		p10.getCategorias().addAll(Arrays.asList(cat4));
		p11.getCategorias().addAll(Arrays.asList(cat5));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Santa Catarina");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "Xaxim", est2);		
		Cidade c3 = new Cidade(null, "Florianopolis", est2);
		
		est1.getCidade().addAll(Arrays.asList(c1));
		est2.getCidade().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "02212356984", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("33251648","98457693"));
		
		Endereco e1 = new Endereco(null, "rua flores", "154", "casa", "bom jesus", "91420590", cli1, c1);
		Endereco e2 = new Endereco(null, "rua gata", "102", "casa", "boa vista", "4586232", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"),cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"),cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, null, sdf.parse("20/10/2017 00:00"));
		ped1.setPagamento(pagto1);
		ped2.setPagamento(pagto2);
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new  ItemPedido(ped1, p1, 0.00, 1, p1.getPrice());
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, p3.getPrice());
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, p2.getPrice());
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}

}
