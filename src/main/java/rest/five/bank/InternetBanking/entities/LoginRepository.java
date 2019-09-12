package rest.five.bank.InternetBanking.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.five.bank.InternetBanking.model.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
