package rest.five.bank.InternetBanking.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.Investimento;

import java.util.List;

public interface InvestimentoInterface extends JpaRepository<Investimento, Long> {
    Conta findByConta(Conta contaId);

    List<Investimento> findAllByNomeInvestimento(String nomeInvestimento);

    List<Investimento> findAllByConta(Conta conta);

}
