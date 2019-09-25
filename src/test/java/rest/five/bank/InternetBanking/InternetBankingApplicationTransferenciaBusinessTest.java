package rest.five.bank.InternetBanking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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
		conta.setSaldoConta(100);
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
}
