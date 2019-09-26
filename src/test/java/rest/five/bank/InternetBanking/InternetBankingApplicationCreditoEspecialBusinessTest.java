package rest.five.bank.InternetBanking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import rest.five.bank.InternetBanking.Business.CreditoEspecialBusiness;
import rest.five.bank.InternetBanking.controller.dto.CreditoDTO;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.CreditoEspecialInterface;
import rest.five.bank.InternetBanking.model.Cliente;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.CreditoEspecial;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InternetBankingApplicationCreditoEspecialBusinessTest {
	@InjectMocks
	CreditoEspecialBusiness creditoEspecialBusiness;
	@Mock
	CreditoEspecialInterface mockCreditoEspecialInterface;
	@Mock
	ContaInterface mockContaInterface;


	Cliente cliente1;
	@Before
	public void retornaCliente () {
		cliente1 = new Cliente();
		cliente1.setIdCliente(1l);
		cliente1.setCpfCliente("08444055905");
		cliente1.setNomeCliente("Gabriel");
		cliente1.setPassword("tata123");

	}
	private Conta criaConta(Cliente cliente) {
		Conta conta = new Conta();
		conta.setNumConta(cliente.getIdCliente());
		conta.setSaldoConta((double) 100);
		conta.setFkIdCliente(cliente);
		return conta;
	}

	@Test
	public void TestContaBusinessSave() {
		Conta conta = criaConta(cliente1);

		CreditoEspecial creditoEspecial = new CreditoEspecial();
		creditoEspecial.setValorSaldo(10);
		creditoEspecial.setIdCreditoEspecial(1l);
		creditoEspecial.setFkIdConta(conta);

		CreditoDTO creditoEspecialDTO = new CreditoDTO();
		creditoEspecialDTO.setIdCliente(1l);
		creditoEspecialDTO.setValorSaldo(10);

		when(mockContaInterface.findById(1l)).thenReturn(Optional.of(conta));
		when(mockCreditoEspecialInterface.existsByFkIdConta(any())).thenReturn(false);

		InOrder inOrder = Mockito.inOrder(mockCreditoEspecialInterface);
		creditoEspecialBusiness.save(creditoEspecialDTO);
		inOrder.verify(mockCreditoEspecialInterface,times(1)).save(any());
	}

	@Test
	public void TestContaBusinessSaveAtualiza() {
		Conta conta = criaConta(cliente1);

		CreditoEspecial creditoEspecial = new CreditoEspecial();
		creditoEspecial.setValorSaldo(10);
		creditoEspecial.setIdCreditoEspecial(1l);
		creditoEspecial.setFkIdConta(conta);

		CreditoDTO creditoEspecialDTO = new CreditoDTO();
		creditoEspecialDTO.setIdCliente(1l);
		creditoEspecialDTO.setValorSaldo(10);

		when(mockContaInterface.findById(1l)).thenReturn(Optional.of(conta));
		when(mockCreditoEspecialInterface.existsByFkIdConta(any())).thenReturn(true);
		when(mockCreditoEspecialInterface.findByFkIdConta(any())).thenReturn(creditoEspecial);

		creditoEspecialBusiness.save(creditoEspecialDTO);
	}

	@Test
	public void TestContaBusinessFindCredito() {
		Conta conta = criaConta(cliente1);

		CreditoEspecial creditoEspecial = new CreditoEspecial();
		creditoEspecial.setValorSaldo(10);
		creditoEspecial.setIdCreditoEspecial(1l);
		creditoEspecial.setFkIdConta(conta);

		when(mockCreditoEspecialInterface.findById(1l)).thenReturn(Optional.of(creditoEspecial));
		Assert.assertEquals(creditoEspecial, creditoEspecialBusiness.findCredito(1l));
	}

	@Test
	public void TestContaBusinessUpdateCredito(){
		Conta conta = criaConta(cliente1);

		CreditoEspecial creditoEspecial1 = new CreditoEspecial();
		creditoEspecial1.setValorSaldo(10);
		creditoEspecial1.setIdCreditoEspecial(1l);
		creditoEspecial1.setFkIdConta(conta);

		when(mockContaInterface.findById(1l)).thenReturn(Optional.of(conta));
		when(mockCreditoEspecialInterface.findByFkIdConta(any())).thenReturn(creditoEspecial1);
		when(mockCreditoEspecialInterface.save(any())).thenAnswer((invocation) -> invocation.getArgument(0));
		CreditoEspecial creditoEspecial2 = new CreditoEspecial();
		creditoEspecial2.setValorSaldo(10);
		creditoEspecial2.setIdCreditoEspecial(1l);
		creditoEspecial2.setFkIdConta(conta);

		CreditoEspecial creditoExpected = (CreditoEspecial) creditoEspecialBusiness.updateCredito(creditoEspecial2);
		Assert.assertEquals(20,creditoExpected.getValorSaldo(),0.001);
	}

	@Test
	public void TestContaBusinessUpdateCreditoFalse() {
		Conta conta = criaConta(cliente1);

		CreditoEspecial creditoEspecial1 = new CreditoEspecial();
		creditoEspecial1.setValorSaldo(700);
		creditoEspecial1.setIdCreditoEspecial(1l);
		creditoEspecial1.setFkIdConta(conta);

		when(mockContaInterface.findById(1l)).thenReturn(Optional.of(conta));
		when(mockCreditoEspecialInterface.findByFkIdConta(any())).thenReturn(creditoEspecial1);
		CreditoEspecial creditoEspecial2 = new CreditoEspecial();
		creditoEspecial2.setValorSaldo(10);
		creditoEspecial2.setIdCreditoEspecial(1l);
		creditoEspecial2.setFkIdConta(conta);

		Assert.assertEquals("Atigido o limite do crédito especial", creditoEspecialBusiness.updateCredito(creditoEspecial1));
	}
}
