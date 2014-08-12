CREATE DATABASE `qmanager`;

USE `qmanager`;

CREATE TABLE `instituicao` (
  `id_instituicao` INT NOT NULL AUTO_INCREMENT,
  `nr_cnpj` CHAR(14) NOT NULL,
  `nm_instituicao` VARCHAR(45) NOT NULL,
  `nm_sigla` VARCHAR(10) NOT NULL,
  `vl_orcamento` DOUBLE,
  UNIQUE (`nr_cnpj`),
  PRIMARY KEY (`id_instituicao`)
);

CREATE TABLE `programa_institucional` (
  `id_programa_institucional` INT NOT NULL AUTO_INCREMENT,
  `nm_programa_institucional` VARCHAR(255) NOT NULL,
  `nm_sigla` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id_programa_institucional`)
);

CREATE TABLE `instituicao_has_programa_institucional` (
  `instituicao_id` INT NOT NULL,
  `programa_institucional_id` INT NOT NULL,
  PRIMARY KEY (`instituicao_id`, `programa_institucional_id`),
  CONSTRAINT `fk_instituicao_has_programa_institucional`
    FOREIGN KEY (`instituicao_id`)
    REFERENCES `instituicao` (`id_instituicao`),
  CONSTRAINT `fk_programa_institucional_has_instituicao`
    FOREIGN KEY (`programa_institucional_id`)
    REFERENCES `programa_institucional` (`id_programa_institucional`)
);

CREATE TABLE `edital` (
  `id_edital` INT NOT NULL AUTO_INCREMENT,
  `numero_ano` VARCHAR(8) NOT NULL,
  `dt_inicio_inscricoes` DATE NOT NULL,
  `dt_fim_inscricoes` DATE NOT NULL,
  `dt_relatorio_parcial` DATE NOT NULL,
  `dt_relatorio_final` DATE NOT NULL,
  `nr_vagas` INT NOT NULL,
  `vl_bolsa_discente` DOUBLE NOT NULL,
  `vl_bolsa_docente` DOUBLE NOT NULL,
  `tp_edital` CHAR NOT NULL,
  `programa_institucional_id` INT NOT NULL,
  UNIQUE(`numero_ano`),
  PRIMARY KEY (`id_edital`),
  CONSTRAINT `fk_edital_programa_institucional`
    FOREIGN KEY (`programa_institucional_id`)
    REFERENCES `programa_institucional` (`id_programa_institucional`)
);

CREATE TABLE `projeto` (
  `id_projeto` INT NOT NULL AUTO_INCREMENT,
  `nm_projeto` VARCHAR(255) NOT NULL,
  `dt_inicio_projeto` DATE NOT NULL,
  `dt_fim_projeto` DATE NOT NULL,
  `relatorio_parcial` VARCHAR(255),
  `relatorio_final` VARCHAR(255),
  `nr_processo` INT NOT NULL,
  `tp_projeto` CHAR NOT NULL,
  `edital_id` INT NOT NULL,
  PRIMARY KEY (`id_projeto`),
  CONSTRAINT `fk_projeto_edital`
    FOREIGN KEY (`edital_id`)
    REFERENCES `edital` (`id_edital`)
);

CREATE TABLE `pessoa` (
  `id_pessoa` INT NOT NULL AUTO_INCREMENT,
  `nm_pessoa` VARCHAR(90) NOT NULL,
  `nr_cpf` CHAR(11) NOT NULL,
  `nr_matricula` VARCHAR(20) NOT NULL,
  `nm_endereco` VARCHAR(90) NOT NULL,
  `nm_cep` CHAR(8) NOT NULL,
  `nm_telefone` VARCHAR(12) NOT NULL,
  `nm_email` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`id_pessoa`),
  UNIQUE (`nr_cpf`),
  UNIQUE (`nr_matricula`)
);

CREATE TABLE `participacao` (
  `id_participacao` INT NOT NULL AUTO_INCREMENT,
  `pessoa_id` INT NOT NULL,
  `projeto_id` INT NOT NULL,
  `dt_inicio` DATE NOT NULL,
  `dt_fim` DATE NULL,
  `fl_bolsista` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_participacao`),
  CONSTRAINT `fk_participacao_projeto`
    FOREIGN KEY (`projeto_id`)
    REFERENCES `projeto` (`id_projeto`),
  CONSTRAINT `fk_participacao_pessoa`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `pessoa` (`id_pessoa`)
);

CREATE TABLE `instituicao_bancaria` (
  `id_instituicao_bancaria` INT NOT NULL AUTO_INCREMENT,
  `nm_banco` VARCHAR(45) NOT NULL,
  `nr_agencia` VARCHAR(6) NOT NULL,
  PRIMARY KEY(id_instituicao_bancaria),
  UNIQUE(nr_agencia)
);

CREATE TABLE `dados_bancarios` (
  `pessoa_id` INT NOT NULL,
  `instituicao_bancaria_id` INT NOT NULL,
  `nr_operacao` VARCHAR(3),
  `nr_conta` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`pessoa_id`),
  CONSTRAINT `fk_dados_bancarios_instituicao_bancaria`
    FOREIGN KEY (`instituicao_bancaria_id`)
    REFERENCES `instituicao_bancaria` (`id_instituicao_bancaria`),
  CONSTRAINT `fk_dados_bancarios_pessoa`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `pessoa` (`id_pessoa`)
);

CREATE TABLE `docente` (
  `pessoa_id` INT NOT NULL,
  `nm_titulacao` VARCHAR(45) NOT NULL,
  `nm_cargo` VARCHAR(45) NOT NULL,
  `nm_local_trabalho` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`pessoa_id`),
  CONSTRAINT `fk_docente_pessoa`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `pessoa` (`id_pessoa`)
);

CREATE TABLE `curso` (
  `id_curso` INT NOT NULL AUTO_INCREMENT,
  `nm_curso` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_curso`),
  UNIQUE (`nm_curso`)
);

CREATE TABLE `turma`(
  `id_turma` INT NOT NULL AUTO_INCREMENT,
  `nr_ano` INT NOT NULL,
  `nm_turno` CHAR NOT NULL,
  `curso_id` INT NOT NULL,
  PRIMARY KEY (`id_turma`),
  CONSTRAINT `fk_turma_curso`
    FOREIGN KEY (`curso_id`)
    REFERENCES `curso` (`id_curso`)
);

CREATE TABLE `discente` (
  `pessoa_id` INT NOT NULL,
  `turma_id` INT NOT NULL,
  PRIMARY KEY (`pessoa_id`),
  CONSTRAINT `fk_discente_turma`
    FOREIGN KEY (`turma_id`)
    REFERENCES `turma` (`id_turma`),
  CONSTRAINT `fk_discente_pessoa`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `pessoa` (`id_pessoa`)
);


