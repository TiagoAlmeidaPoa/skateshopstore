package com.tiagoalmeida.skateshopstore.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiagoalmeida.skateshopstore.domain.ItemPedido;
import com.tiagoalmeida.skateshopstore.domain.PagamentoComBoleto;
import com.tiagoalmeida.skateshopstore.domain.Pedido;
import com.tiagoalmeida.skateshopstore.domain.enums.EstadoPagamento;
import com.tiagoalmeida.skateshopstore.repository.ItemPedidoRepository;
import com.tiagoalmeida.skateshopstore.repository.PagamentoRepository;
import com.tiagoalmeida.skateshopstore.repository.PedidoRepository;
import com.tiagoalmeida.skateshopstore.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public Pedido find(Integer id) {
		Optional<Pedido> pedido = repository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + ", tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPrice());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}

}
