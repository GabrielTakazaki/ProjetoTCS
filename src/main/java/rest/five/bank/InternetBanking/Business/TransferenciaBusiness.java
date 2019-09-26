package rest.five.bank.InternetBanking.Business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.five.bank.InternetBanking.controller.dto.TransferenciaDTO;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.CreditoEspecialInterface;
import rest.five.bank.InternetBanking.entities.TransferenciaInterface;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.CreditoEspecial;
import rest.five.bank.InternetBanking.model.Transferencia;

import javax.transaction.Transactional;
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
    @Autowired
    CreditoEspecialInterface cdInterface;

    public TransferenciaDTO addTrans(TransferenciaDTO transferenciaDTO) {
        Transferencia transf = new Transferencia();

        Optional<Conta> optCDebito = contaInterface.findById(transferenciaDTO.getIdDebitoDTO());
        Optional<Conta> optCCredito = contaInterface.findById(transferenciaDTO.getIdCreditoDTO());

        transf.setContaDebito(optCDebito.get());
        transf.setContaCredito(optCCredito.get());
        transf.setValTransferencia(transferenciaDTO.getValorTransferenciaDTO());
        transf.setDtTransferencia(LocalDateTime.now());

        float auxVal = transferenciaDTO.getValorTransferenciaDTO();

        transferenciaDTO = creditoEspecial(transferenciaDTO);

        calculaConta(optCCredito.get(), optCDebito.get(), transferenciaDTO.getValorTransferenciaDTO(), auxVal);
        transferenciaInterface.save(transf);
        return new TransferenciaDTO(transf);
    }

    @Transactional
    private TransferenciaDTO creditoEspecial(TransferenciaDTO transf) {
        Optional<Conta> conta = contaInterface.findById(transf.getIdCreditoDTO());
        if (conta.get().isExisteEmprestimo()) {
            CreditoEspecial cd = cdInterface.findByFkIdConta(conta.get());

            transf.setValorTransferenciaDTO(transf.getValorTransferenciaDTO() - cd.getValorSaldo());
            cd.setValorSaldo(cd.getValorSaldo() - transf.getValorTransferenciaDTO());
            conta.get().setSaldoConta(cd.getValorSaldo());

            if (cd.getValorSaldo() <= 0) {
                conta.get().setEmprDateTime(null);
                conta.get().setExisteEmprestimo(false);
                cd.setFkIdConta(null);
                cdInterface.delete(cd);
            }

            return transf;
        }

        return transf;
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

    @Transactional
    public void calculaConta(Conta contaCredito, Conta contaDebito, float valorTransferenciaDTO, float valorTransferencia) {
        contaCredito.setSaldoConta(contaCredito.getSaldoConta() + valorTransferenciaDTO);
        contaDebito.setSaldoConta(contaDebito.getSaldoConta() - valorTransferencia);
    }
}
