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

    private Float auxCre;

    public TransferenciaDTO addTrans(TransferenciaDTO transferenciaDTO) {
        Transferencia transf = new Transferencia();

        Optional<Conta> optCDebito = contaInterface.findById(transferenciaDTO.getIdDebitoDTO());
        Optional<Conta> optCCredito = contaInterface.findById(transferenciaDTO.getIdCreditoDTO());

        transf.setContaDebito(optCDebito.get());
        transf.setContaCredito(optCCredito.get());
        transf.setValTransferencia(transferenciaDTO.getValorTransferenciaDTO());
        transf.setDtTransferencia(LocalDateTime.now());

        float auxVal = transferenciaDTO.getValorTransferenciaDTO();
        calculaConta(optCCredito.get(), optCDebito.get(), transferenciaDTO.getValorTransferenciaDTO(), auxVal, creditoEspecial(transferenciaDTO));
        transferenciaInterface.save(transf);
        return new TransferenciaDTO(transf);
    }

    @Transactional
    private String creditoEspecial(TransferenciaDTO transf) {
        Optional<Conta> conta = contaInterface.findById(transf.getIdCreditoDTO());
        if (conta.get().isExisteEmprestimo()) {
            CreditoEspecial cd = cdInterface.findByFkIdConta(conta.get());

            auxCre = cd.getValorSaldo();

            Float aux2 = transf.getValorTransferenciaDTO();
            transf.setValorTransferenciaDTO(transf.getValorTransferenciaDTO() - cd.getValorSaldo());
            cd.setValorSaldo(cd.getValorSaldo() - aux2);


            if (cd.getValorSaldo() <= 0) {
                conta.get().setEmprDateTime(null);
                conta.get().setExisteEmprestimo(false);
                cd.setFkIdConta(null);
                cdInterface.delete(cd);
                return "Deletado";
            }

            return "Ainda Ativo";
        }

        return "Nao tem";
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
    public void calculaConta(Conta contaCredito, Conta contaDebito, float valorTransferenciaDTO, float valorTransferencia, String b) {
        if (b.equals("Nao tem"))
            contaCredito.setSaldoConta(contaCredito.getSaldoConta() + valorTransferenciaDTO);
        else if (b.equals("Deletado"))
            contaCredito.setSaldoConta((double) (valorTransferenciaDTO - auxCre));
        else if (b.equals("Ainda Ativo"))
            contaCredito.setSaldoConta(contaCredito.getSaldoConta() - valorTransferenciaDTO);
        contaDebito.setSaldoConta(contaDebito.getSaldoConta() - valorTransferencia);
    }
}
