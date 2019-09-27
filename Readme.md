# Projeto com base no internet banking utilizando Spring Boot Starter

Utiliza-se do JPA para a comunicação com o banco de dados H2-DataBase

## Package Model:

	Cliente:
    idCliente, nome, cpf, password
	Conta:
    numConta, saldo, fkIdCliente, existeEmprestimo, emprDateTime
	Investimento:
    idInvestimento, conta, nomeInvestiment, saldo, dtInicio
	Transferencia:
    idTransferencia, contaDebito, contaCredito, valTransferencia, dtTransferencia
	CreditoEspecial:
    idCreditoEspecial, fkIdConta, valorSaldo, dtCredito, valorCredito
	

## Inciar Spring

Para iniciar o ProjetoTCS-Spring é necessário dar Run no InternetBankingApplication

Para poder utilizar do ProjetoTCS-Spring é necessario inicializar o projeto spring e ter o 
```https://github.com/tcslearn2019/ProjetoTCS-Angular```

Para utilizar o Angular é necessário ter o nodeJS
```https://nodejs.org/en/```

E então executar os comandos para instalar os node modules
```npm install```

E então para iniciar
```ng serve```

## H2 Database

Para visualizar o H2 Database com as tabelas da Model é necessário ir para:
http://localhost:8080/h2-console 

Alterar o JDBC URL para:
jdbc:h2:mem:testdb

User Name:sa
Password:password
