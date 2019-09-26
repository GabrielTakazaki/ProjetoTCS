package rest.five.bank.InternetBanking;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import rest.five.bank.InternetBanking.Business.InvestimentoBusiness;
import rest.five.bank.InternetBanking.controller.dto.InvestimentoDTO;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.CreditoEspecialInterface;
import rest.five.bank.InternetBanking.entities.InvestimentoInterface;
import rest.five.bank.InternetBanking.model.Cliente;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.CreditoEspecial;
import rest.five.bank.InternetBanking.model.Investimento;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InternetBankingApplicationInvestimentoBusinessTest {
	@InjectMocks
	private InvestimentoBusiness investimentoBusiness;
	@Mock
	private ContaInterface mockContaInterface;
	@Mock
	private InvestimentoInterface mockInvestimentoInterface;
	@Mock
	private CreditoEspecialInterface mockCreditoEspecial;

	Cliente cliente1,cliente2;
	@Before
	public void retornaCliente () {
		cliente1 = new Cliente();
		cliente1.setIdCliente(1l);
		cliente1.setCpfCliente("08444055905");
		cliente1.setNomeCliente("Gabriel");
		cliente1.setPassword("tata123");

		cliente2 = new Cliente();
		cliente2.setIdCliente(2l);
		cliente2.setCpfCliente("81297094069");
		cliente2.setNomeCliente("Takazaki");
		cliente2.setPassword("tata456");

	}

	private Conta criaConta(Cliente cliente) {
		Conta conta = new Conta();
		conta.setNumConta(cliente.getIdCliente());
        conta.setSaldoConta(110d);
		conta.setFkIdCliente(cliente);
		return conta;
	}

	private InvestimentoDTO criaInvestimento (String tipo) {
		InvestimentoDTO investimentoDTO = new InvestimentoDTO();
		investimentoDTO.setIdInvestimento(1l);
		investimentoDTO.setConta(1l);
		investimentoDTO.setNomeInvestimento(tipo);
		investimentoDTO.setSaldo(110);
		return investimentoDTO;
	}

	@Test
	public void TestContaBusinessAddInv() {
		Conta conta = criaConta(cliente1);

		InvestimentoDTO investimentoDTO = criaInvestimento("POUPANCA");
		Investimento investimento = investimentoDTO.retornaInvestimento(conta);
		List<Investimento> investimentoList = Arrays.asList(investimento);

		//when(mockInvestimentoInterface.findAll()).thenReturn(investimentoList);
		when(mockContaInterface.findById(any())).thenReturn(Optional.of(conta));

		Object obj = investimentoBusiness.addInv(investimentoDTO);
		Assert.assertEquals(null, obj);
	}
	@Test
	public void TestContaBusinessAddInvSaldoNulo() {
		Conta conta = criaConta(cliente1);

		InvestimentoDTO investimentoDTO = criaInvestimento("POUPANCA");
		when(mockContaInterface.findById(any())).thenReturn(Optional.of(conta));

		Object obj = investimentoBusiness.addInv(investimentoDTO);
		Assert.assertEquals("Seu saldo é insuficiente", obj);
	}

	@Test
	public void TestContaBusinessTiraDinheiro() {
		Conta conta = criaConta(cliente1);
		conta.setExisteEmprestimo(true);

		InvestimentoDTO investimentoDTO = criaInvestimento("POUPANCA");

		CreditoEspecial creditoEspecial = new CreditoEspecial();
		creditoEspecial.setValorSaldo(10);
		creditoEspecial.setIdCreditoEspecial(1l);
		creditoEspecial.setFkIdConta(conta);

		Investimento investimento = investimentoDTO.retornaInvestimento(conta);

		when(mockContaInterface.findById(any())).thenReturn(Optional.of(conta));
		when(mockInvestimentoInterface.findById(1l)).thenReturn(Optional.of(investimento));
		when(mockCreditoEspecial.findByFkIdConta(conta)).thenReturn(creditoEspecial);

		Object obj = investimentoBusiness.tiraDinheiro(investimentoDTO);
		Assert.assertEquals("Investimento retirando com sucesso", obj);
	}
	@Test
	public void TestContaBusinessRetornaInvestimentos() {
		Conta conta = criaConta(cliente1);
		conta.setExisteEmprestimo(true);

		InvestimentoDTO investimentoDTO = criaInvestimento("POUPANCA");

		CreditoEspecial creditoEspecial = new CreditoEspecial();
		creditoEspecial.setValorSaldo(10);
		creditoEspecial.setIdCreditoEspecial(1l);
		creditoEspecial.setFkIdConta(conta);

		Investimento investimento = investimentoDTO.retornaInvestimento(conta);

		List<Investimento> investimentoList = Arrays.asList(investimento);

		when(mockContaInterface.findById(any())).thenReturn(Optional.of(conta));
		when(mockInvestimentoInterface.findAllByConta(conta)).thenReturn(investimentoList);
		List<InvestimentoDTO> investimentoDTOExpected = investimentoBusiness.retornaInvestimentos(1l);
		Assert.assertEquals(investimentoDTO.getSaldo(), investimentoDTOExpected.get(0).getSaldo(),0.001);
	}

	@Test
	public void TestContaBusinessrealizaInv() {
		Conta conta = criaConta(cliente1);
		conta.setExisteEmprestimo(true);

		InvestimentoDTO investimentoDTO1 = criaInvestimento("POUPANCA");
		InvestimentoDTO investimentoDTO2 = criaInvestimento("IPCA");
		InvestimentoDTO investimentoDTO3 = criaInvestimento("CDI");

		Investimento investimento1 = investimentoDTO1.retornaInvestimento(conta);
		Investimento investimento2 = investimentoDTO2.retornaInvestimento(conta);
		Investimento investimento3 = investimentoDTO3.retornaInvestimento(conta);

		List<Investimento> investimentoList = Arrays.asList(investimento1, investimento2, investimento3);
		when(mockInvestimentoInterface.findAll()).thenReturn(investimentoList);
        //investimentoBusiness.realizaInv();

		InOrder inOrder = Mockito.inOrder(mockInvestimentoInterface);
		inOrder.verify(mockInvestimentoInterface, times(1)).save(investimento1);
		inOrder.verify(mockInvestimentoInterface, times(1)).save(investimento2);
		inOrder.verify(mockInvestimentoInterface, times(1)).save(investimento3);

	}
}
