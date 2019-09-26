package rest.five.bank.InternetBanking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rest.five.bank.InternetBanking.Business.ContaBusiness;
import rest.five.bank.InternetBanking.controller.dto.DepositoDTO;
import rest.five.bank.InternetBanking.entities.ClienteInterface;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.CreditoEspecialInterface;
import rest.five.bank.InternetBanking.model.Cliente;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.CreditoEspecial;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InternetBankingApplicationContaBusinessTest {
	@InjectMocks
	private ContaBusiness contaBusiness;
	@Mock
	private ClienteInterface mockClienteInterface;
	@Mock
	private ContaInterface mockContaInterface;
	@Mock
	private CreditoEspecialInterface mockCreditoEspecialInterface;

	Cliente cliente;
	@Before
	public void retornaCliente () {
		cliente = new Cliente();
		cliente.setIdCliente(1l);
		cliente.setCpfCliente("08444055905");
		cliente.setNomeCliente("Gabriel");
		cliente.setPassword("tata123");
	}

	private Conta criaConta(Cliente cliente) {
		Conta conta = new Conta();
		conta.setSaldoConta(0);
		conta.setFkIdCliente(cliente);
		return conta;
	}
	private CreditoEspecial criaCreditoEspecial(Conta conta) {
		CreditoEspecial creditoEspecial = new CreditoEspecial();
		creditoEspecial.setValorSaldo(10);
		creditoEspecial.setIdCreditoEspecial(1l);
		creditoEspecial.setFkIdConta(conta);
		return creditoEspecial;
	}

    /*@Test
    public void TestContaBusinessAddConta() {
        when(mockClienteInterface.findByCpfCliente(FixedClienteDTO.getCpfCliente())).thenReturn(cliente);
        Conta contaExpected = criaConta(cliente);
        when(mockContaInterface.save(any(Conta.class))).thenReturn(contaExpected);

        Conta contaAtual = contaBusiness.addConta();
        Assert.assertEquals(contaExpected,contaAtual);
    }*/
	@Test
	public void TestContaBusinessAddContaExist() {
		Conta conta = criaConta(cliente);
		when(mockContaInterface.existsByFkIdCliente(any(Cliente.class))).thenReturn(true);
		when(mockContaInterface.findByFkIdCliente(any(Cliente.class))).thenReturn(conta);
		Assert.assertEquals(contaBusiness.addConta(),conta);
	}
	@Test
	public void TestContaBusinessDepositar() {
		DepositoDTO depositoDTO = new DepositoDTO();
		depositoDTO.setIdConta(1l);
		depositoDTO.setValorDeposito(20);
		Conta conta = criaConta(cliente);
		conta.setNumConta(1l);
		CreditoEspecial creditoEspecial = criaCreditoEspecial(conta);
		Optional optionalConta = Optional.of(conta);

		when(mockContaInterface.findById(1l)).thenReturn(optionalConta);
		when(mockCreditoEspecialInterface.existsByFkIdConta(any(Conta.class))).thenReturn(true);
		when(mockCreditoEspecialInterface.findByFkIdConta(any(Conta.class))).thenReturn(creditoEspecial);

		contaBusiness.depositar(depositoDTO);
		InOrder ordemChamadas = inOrder(mockCreditoEspecialInterface);
		ordemChamadas.verify(mockCreditoEspecialInterface, times(1))
				.save(creditoEspecial);
		ordemChamadas.verify(mockCreditoEspecialInterface, times(1))
				.delete(creditoEspecial);
	}
	@Test
	public void TestContaBusinessBuscarContas() {
		Conta conta = criaConta(cliente);
		List<Conta> contaList = Arrays.asList(conta);
		when(mockContaInterface.findAll()).thenReturn(contaList);
		Assert.assertEquals(contaBusiness.buscarContas(),contaList);
	}
	@Test
	public void TestContaBusinessBuscarConta() {
		Conta conta = criaConta(cliente);
		conta.setNumConta(1l);
		when(mockContaInterface.findById(1l)).thenReturn(Optional.of(conta));
		Assert.assertEquals(contaBusiness.buscarConta(1l),conta);
	}

}
