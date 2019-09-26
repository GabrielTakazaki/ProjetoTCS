package rest.five.bank.InternetBanking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import rest.five.bank.InternetBanking.Business.ContaBusiness;
import rest.five.bank.InternetBanking.Business.TransferenciaBusiness;
import rest.five.bank.InternetBanking.controller.dto.DepositoDTO;
import rest.five.bank.InternetBanking.controller.dto.FixedClienteDTO;
import rest.five.bank.InternetBanking.controller.dto.TransferenciaDTO;
import rest.five.bank.InternetBanking.entities.ClienteInterface;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.CreditoEspecialInterface;
import rest.five.bank.InternetBanking.entities.TransferenciaInterface;
import rest.five.bank.InternetBanking.model.Cliente;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.CreditoEspecial;
import rest.five.bank.InternetBanking.model.Transferencia;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InternetBankingApplicationTransferenciaBusinessTest {
	@InjectMocks
	private TransferenciaBusiness transferenciaBusiness;
	@Mock
	private ContaInterface mockContaInterface;
	@Mock
	private CreditoEspecialInterface mockCreditoEspecialInterface;
	@Mock
	private TransferenciaInterface mockTransferenciaInterface;

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
		conta.setSaldoConta((double) 100);
		conta.setFkIdCliente(cliente);
		return conta;
	}

	@Test
	public void TestContaBusinessAddTrans() {
		TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
		transferenciaDTO.setIdCreditoDTO(1l);
		transferenciaDTO.setIdDebitoDTO(2l);
		transferenciaDTO.setValorTransferenciaDTO(100);

		Conta conta1 = criaConta(cliente1);
		Conta conta2 = criaConta(cliente2);

		CreditoEspecial creditoEspecial = new CreditoEspecial();
		creditoEspecial.setIdCreditoEspecial(1l);
		creditoEspecial.setValorSaldo(100f);
		creditoEspecial.setFkIdConta(conta1);

		Transferencia transferencia = new Transferencia();
		transferencia.setContaCredito(conta1);
		transferencia.setContaDebito(conta2);
		transferencia.setValTransferencia(100);

		when(mockContaInterface.findById(1l)).thenReturn(Optional.of(conta1));
		when(mockContaInterface.findById(2l)).thenReturn(Optional.of(conta2));
		when(mockTransferenciaInterface.save(any())).thenAnswer((invocation) -> invocation.getArgument(0));

		TransferenciaDTO transferenciaDTOAtual = transferenciaBusiness.addTrans(transferenciaDTO);
		Assert.assertEquals(transferenciaDTO.getValorTransferenciaDTO(),transferenciaDTOAtual.getValorTransferenciaDTO(),0.0001);
	}

	@Test
	public void TestContaBusinessListTransf() {
		Conta conta1 = criaConta(cliente1);
		Conta conta2 = criaConta(cliente2);

		Transferencia transferencia1 = new Transferencia();
		transferencia1.setIdTransferencia(1l);
		conta1.setNumConta(1l);
		conta2.setNumConta(2l);
		transferencia1.setContaCredito(conta1);
		transferencia1.setContaDebito(conta2);
		transferencia1.setValTransferencia(100);

		Transferencia transferencia2 = new Transferencia();
		transferencia2.setIdTransferencia(2l);
		transferencia2.setContaCredito(conta2);
		transferencia2.setContaDebito(conta1);
		transferencia2.setValTransferencia(200);

		List<Transferencia> listTransf = Arrays.asList(transferencia1, transferencia2);
		when(mockTransferenciaInterface.findAll()).thenReturn(listTransf);
		List<TransferenciaDTO> listaAtual = transferenciaBusiness.listaTudoComId(1l);

		TransferenciaDTO transferenciaExpected = new TransferenciaDTO();
		transferenciaExpected.setValorTransferenciaDTO(100);
		transferenciaExpected.setIdDebitoDTO(2l);
		transferenciaExpected.setIdCreditoDTO(1l);
		Assert.assertNotNull(listaAtual);
		Assert.assertEquals(listaAtual.get(0).getIdCreditoDTO(),transferenciaExpected.getIdCreditoDTO());
		Assert.assertEquals(listaAtual.get(0).getIdDebitoDTO(),transferenciaExpected.getIdDebitoDTO());
	}
}
