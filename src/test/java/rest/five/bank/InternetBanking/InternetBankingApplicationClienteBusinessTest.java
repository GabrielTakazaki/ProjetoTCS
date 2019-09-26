package rest.five.bank.InternetBanking;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rest.five.bank.InternetBanking.Business.ClienteBusiness;
import rest.five.bank.InternetBanking.controller.Form.ClienteForm;
import rest.five.bank.InternetBanking.entities.ClienteInterface;
import rest.five.bank.InternetBanking.model.Cliente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InternetBankingApplicationClienteBusinessTest {
	@InjectMocks
	private ClienteBusiness clienteBusiness;
	@Mock
	private ClienteInterface mockClienteInterface;

	public ClienteForm retornaClienteForm () {
		ClienteForm clienteForm = new ClienteForm();
		clienteForm.setCpfCliente("08444055905");
		clienteForm.setNomeCliente("Gabriel");
		clienteForm.setPassword("tata123");
		return clienteForm;
	}

	@Test
	public void TestClienteBusinessSave() {
		ClienteForm clienteForm = retornaClienteForm();
		Cliente cliente = clienteForm.formatar();
		when(mockClienteInterface.save(any(Cliente.class))).thenReturn(cliente);
		Assert.assertEquals(clienteBusiness.save(clienteForm).getCpfCliente(), cliente.getCpfCliente());
	}
	@Test
	public void TestVerificaLoginTrue() {
		ClienteForm clienteForm = retornaClienteForm();
		Cliente cliente = clienteForm.formatar();
		List<Cliente> listCliente = Arrays.asList(cliente);
		when(mockClienteInterface.findAll()).thenReturn(listCliente);
		Assert.assertTrue(clienteBusiness.verifLogin(clienteForm));
	}
	@Test
	public void TestVerificaLoginFalse() {
		ClienteForm clienteForm = retornaClienteForm();
		Cliente cliente = clienteForm.formatar();
		List<Cliente> listCliente = new ArrayList<Cliente>();
		when(mockClienteInterface.findAll()).thenReturn(listCliente);
		Assert.assertFalse(clienteBusiness.verifLogin(clienteForm));
	}
	@Test
	public void TestFindCpf() {
		ClienteForm clienteForm = retornaClienteForm();
		Cliente cliente = clienteForm.formatar();
		when(mockClienteInterface.findByCpfCliente("08444055905")).thenReturn(cliente);
		String nome = clienteBusiness.findCPF("08444055905").getNomeCliente();
		Assert.assertEquals("Gabriel",nome);
	}
}
