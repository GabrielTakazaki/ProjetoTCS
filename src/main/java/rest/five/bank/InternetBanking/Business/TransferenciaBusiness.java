package rest.five.bank.InternetBanking.Business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.five.bank.InternetBanking.controller.dto.TransferenciaDTO;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.TransferenciaInterface;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.Transferencia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransferenciaBusiness {
    @Autowired
    TransferenciaInterface transferenciaInterface;
    @Autowired
    ContaInterface contaInterface;

    public TransferenciaDTO addTrans(TransferenciaDTO transferenciaDTO) {
        Transferencia transf = new Transferencia();

        Optional<Conta> optCDebito = contaInterface.findById(transferenciaDTO.getIdDebitoDTO());
        Optional<Conta> optCCredito = contaInterface.findById(transferenciaDTO.getIdCreditoDTO());

        transf.setContaDebito(optCDebito.get());
        transf.setContaCredito(optCCredito.get());
        transf.setValTransferencia(transferenciaDTO.getValorTransferenciaDTO());
        transf.setDtTransferencia(LocalDateTime.now());

        calculaConta(optCCredito.get(), optCDebito.get(), transferenciaDTO.getValorTransferenciaDTO());
        transferenciaInterface.save(transf);
        return new TransferenciaDTO(transf);
    }

    public List<Transferencia> listTransf(Long idConta) {
        List<Transferencia> listaTransferencia = new ArrayList<>();
        for (Transferencia transf : transferenciaInterface.findAll()) {
            if (transf.getContaDebito().getNumConta().equals(idConta) || transf.getContaCredito().getNumConta().equals(idConta)) {
                listaTransferencia.add(transf);
            }
        }
        return listaTransferencia;
    }

    public List<TransferenciaDTO> listaTudoComId(Long id) {
        List<TransferenciaDTO> transferenciaDTOList = new ArrayList<>();
        List<Transferencia> transList = transferenciaInterface.findAll();

        transList.forEach((item) -> {
            if (id.equals(item.getContaCredito().getNumConta())) {
                transferenciaDTOList.add(new TransferenciaDTO(item));
            } else if (id.equals(item.getContaDebito().getNumConta())) {
                transferenciaDTOList.add(new TransferenciaDTO(item));
            }
        });
        return transferenciaDTOList;
    }

    public void calculaConta(Conta contaCredito, Conta contaDebito, float valorTransferencia) {
        contaCredito.setSaldoConta(contaCredito.getSaldoConta() + valorTransferencia);
        contaDebito.setSaldoConta(contaDebito.getSaldoConta() - valorTransferencia);
        contaInterface.save(contaCredito);
        contaInterface.save(contaDebito);
    }
}
