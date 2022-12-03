package com.tiagoalmeida.skateshopstore;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tiagoalmeida.skateshopstore.domain.Categoria;
import com.tiagoalmeida.skateshopstore.domain.Cidade;
import com.tiagoalmeida.skateshopstore.domain.Cliente;
import com.tiagoalmeida.skateshopstore.domain.Endereco;
import com.tiagoalmeida.skateshopstore.domain.Estado;
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

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "calcado");
		Categoria cat2 = new Categoria(null, "roupa");
		
		Produto p1 = new Produto(null, "nike sb", 150.00);
		Produto p2 = new Produto(null, "bermuda primitive", 130.00);
		Produto p3 = new Produto(null, "camiseta Lakers", 70.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1));
		cat2.getProdutos().addAll(Arrays.asList(p2, p3));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat2));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
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
		
	}

}
