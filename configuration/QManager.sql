CREATE DATABASE `qmanager` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `qmanager`;

CREATE TABLE `tb_instituicao` (
  `id_instituicao` INT NOT NULL AUTO_INCREMENT,
  `nm_cnpj` CHAR(14) NOT NULL,
  `nm_instituicao` VARCHAR(45) NOT NULL,
  `nm_sigla` VARCHAR(10) NOT NULL,
  `vl_orcamento` DOUBLE,
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE (`nm_cnpj`),
  PRIMARY KEY (`id_instituicao`)
);

CREATE TABLE `tb_programa_institucional` (
  `id_programa_institucional` INT NOT NULL AUTO_INCREMENT,
  `nm_programa_institucional` VARCHAR(255) NOT NULL,
  `nm_sigla` VARCHAR(10) NOT NULL,
  `vl_orcamento` DOUBLE NOT NULL,
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_programa_institucional`)
);

CREATE TABLE `tb_instituicao_has_programa_institucional` (
  `instituicao_id` INT NOT NULL,
  `programa_institucional_id` INT NOT NULL,
  PRIMARY KEY (`instituicao_id`, `programa_institucional_id`),
  CONSTRAINT `fk_instituicao_has_programa_institucional`
    FOREIGN KEY (`instituicao_id`)
    REFERENCES `tb_instituicao` (`id_instituicao`),
  CONSTRAINT `fk_programa_institucional_has_instituicao`
    FOREIGN KEY (`programa_institucional_id`)
    REFERENCES `tb_programa_institucional` (`id_programa_institucional`)
);

CREATE TABLE `tb_edital` (
  `id_edital` INT NOT NULL AUTO_INCREMENT,
  `nr_edital` INT(3) NOT NULL,
  `nr_ano` INT(4) NOT NULL,
  `dt_inicio_inscricoes` DATE NOT NULL,
  `dt_fim_inscricoes` DATE NOT NULL,
  `dt_relatorio_parcial` DATE NOT NULL,
  `dt_relatorio_final` DATE NOT NULL,
  `nr_vagas` INT(3) NOT NULL,
  `vl_bolsa_discente` DOUBLE NOT NULL,
  `vl_bolsa_docente` DOUBLE NOT NULL,
  `tp_edital` CHAR NOT NULL,
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `programa_institucional_id` INT NOT NULL,
  PRIMARY KEY (`id_edital`),
  CONSTRAINT `fk_edital_programa_institucional`
    FOREIGN KEY (`programa_institucional_id`)
    REFERENCES `tb_programa_institucional` (`id_programa_institucional`)
);

CREATE TABLE `tb_projeto` (
  `id_projeto` INT NOT NULL AUTO_INCREMENT,
  `nm_projeto` VARCHAR(255) NOT NULL,
  `dt_inicio_projeto` DATE NOT NULL,
  `dt_fim_projeto` DATE NOT NULL,
  `nm_projeto_submetido` VARCHAR(255),
  `nm_relatorio_parcial` VARCHAR(255),
  `nm_relatorio_final` VARCHAR(255),
  `nr_processo` VARCHAR(21) NOT NULL,
  `tp_projeto` CHAR NOT NULL,
  `vl_orcamento` DOUBLE,
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `edital_id` INT NOT NULL,
  PRIMARY KEY (`id_projeto`),
  CONSTRAINT `fk_projeto_edital`
    FOREIGN KEY (`edital_id`)
    REFERENCES `tb_edital` (`id_edital`)
);

CREATE TABLE `tb_usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nm_login` VARCHAR(95),
  `nm_password` VARCHAR(25),
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_usuario`)
);

CREATE TABLE `tb_pessoa` (
  `id_pessoa` INT NOT NULL AUTO_INCREMENT,
  `nm_pessoa` VARCHAR(90) NOT NULL,
  `nr_cpf` CHAR(11) NOT NULL,
  `nr_matricula` VARCHAR(20) NOT NULL,
  `nm_endereco` VARCHAR(90) NOT NULL,
  `nm_cep` CHAR(8) NOT NULL,
  `nm_telefone` VARCHAR(12) NOT NULL,
  `nm_email` VARCHAR(90) NOT NULL,
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id_pessoa`),
  UNIQUE (`nr_cpf`),
  UNIQUE (`nr_matricula`),
  CONSTRAINT `fk_pessoa_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `tb_usuario` (`id_usuario`)
);

CREATE TABLE `tb_participacao` (
  `id_participacao` INT NOT NULL AUTO_INCREMENT,
  `pessoa_id` INT NOT NULL,
  `projeto_id` INT NOT NULL,
  `dt_inicio` DATE NOT NULL,
  `dt_fim` DATE NULL,
  `fl_bolsista` TINYINT(1) NOT NULL,
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_participacao`),
  CONSTRAINT `fk_participacao_projeto`
    FOREIGN KEY (`projeto_id`)
    REFERENCES `tb_projeto` (`id_projeto`),
  CONSTRAINT `fk_participacao_pessoa`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `tb_pessoa` (`id_pessoa`)
);

CREATE TABLE `tb_bolsa` (
  `participacao_id` INT NOT NULL,
  `vl_bolsa` DOUBLE NOT NULL,
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `fk_bolsa_participacao`
    FOREIGN KEY (`participacao_id`)
    REFERENCES `tb_participacao` (`id_participacao`)
);

CREATE TABLE `tb_instituicao_bancaria` (
  `id_instituicao_bancaria` INT NOT NULL AUTO_INCREMENT,
  `nm_banco` VARCHAR(45) NOT NULL,
  `nr_agencia` VARCHAR(6) NOT NULL,
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY(id_instituicao_bancaria),
  UNIQUE(nr_agencia)
);

CREATE TABLE `tb_dados_bancarios` (
  `pessoa_id` INT NOT NULL,
  `instituicao_bancaria_id` INT NOT NULL,
  `nr_operacao` VARCHAR(3),
  `nr_conta` VARCHAR(10) NOT NULL,
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pessoa_id`),
  CONSTRAINT `fk_dados_bancarios_instituicao_bancaria`
    FOREIGN KEY (`instituicao_bancaria_id`)
    REFERENCES `tb_instituicao_bancaria` (`id_instituicao_bancaria`),
  CONSTRAINT `fk_dados_bancarios_pessoa`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `tb_pessoa` (`id_pessoa`)
);

CREATE TABLE `tb_docente` (
  `pessoa_id` INT NOT NULL,
  `nm_titulacao` VARCHAR(45) NOT NULL,
  `nm_cargo` VARCHAR(45) NOT NULL,
  `nm_local_trabalho` VARCHAR(45) NOT NULL,
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pessoa_id`),
  CONSTRAINT `fk_docente_pessoa`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `tb_pessoa` (`id_pessoa`)
);

CREATE TABLE `tb_curso` (
  `id_curso` INT NOT NULL AUTO_INCREMENT,
  `nm_curso` VARCHAR(45) NOT NULL,
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_curso`),
  UNIQUE (`nm_curso`)
);

CREATE TABLE `tb_turma`(
  `id_turma` INT NOT NULL AUTO_INCREMENT,
  `nr_ano` INT(1) NOT NULL,
  `nm_turno` CHAR NOT NULL,
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `curso_id` INT NOT NULL,
  PRIMARY KEY (`id_turma`),
  CONSTRAINT `fk_turma_curso`
    FOREIGN KEY (`curso_id`)
    REFERENCES `tb_curso` (`id_curso`)
);

CREATE TABLE `tb_discente` (
  `pessoa_id` INT NOT NULL,
  `turma_id` INT NOT NULL,
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pessoa_id`),
  CONSTRAINT `fk_discente_turma`
    FOREIGN KEY (`turma_id`)
    REFERENCES `tb_turma` (`id_turma`),
  CONSTRAINT `fk_discente_pessoa`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `tb_pessoa` (`id_pessoa`)
);


