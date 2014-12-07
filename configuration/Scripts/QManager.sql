CREATE DATABASE `qmanager` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `qmanager`;

CREATE TABLE `tb_instituicao` (
  `id_instituicao` INT NOT NULL AUTO_INCREMENT,
  `nr_cnpj` CHAR(14) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nm_instituicao` VARCHAR(45) NOT NULL,
  `nm_sigla` VARCHAR(10) NOT NULL,
  `vl_orcamento` DOUBLE,
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE (`nr_cnpj`),
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
  `programa_institucional_id` INT NOT NULL,
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
  UNIQUE (`nr_cpf`),
  UNIQUE (`nr_matricula`),
  PRIMARY KEY (`id_pessoa`)
);

CREATE TABLE `tb_usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nm_login` VARCHAR(95),
  `nm_password` VARCHAR(25),
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pessoa_id` INT NOT NULL,
  CONSTRAINT `fk_usuario_pessoa`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `tb_pessoa`(`id_pessoa`),
  PRIMARY KEY (`id_usuario`)
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
  `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY(`id_instituicao_bancaria`)
);

CREATE TABLE `tb_dados_bancarios` (
  `pessoa_id` INT NOT NULL,
  `instituicao_bancaria_id` INT NOT NULL,
  `nr_operacao` VARCHAR(3),
  `nr_conta` VARCHAR(15) NOT NULL,
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
  PRIMARY KEY (`pessoa_id`),
  CONSTRAINT `fk_discente_turma`
    FOREIGN KEY (`turma_id`)
    REFERENCES `tb_turma` (`id_turma`),
  CONSTRAINT `fk_discente_pessoa`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `tb_pessoa` (`id_pessoa`)
);

--
-- Alterações de 06/09/2014
-- 

-- ----------------------------------------------------------------------------------
-- Adicionando o atributo `ar_edital` na tabela `tb_edital`
-- ----------------------------------------------------------------------------------
ALTER TABLE `tb_edital`
ADD COLUMN `ar_edital` VARCHAR(255) NOT NULL
AFTER `id_edital`;

-- ----------------------------------------------------------------------------------
-- Alterando os atributos que eram um arquivo e recebiam `nm_...` para `ar_...`
-- ----------------------------------------------------------------------------------
ALTER TABLE `tb_projeto`
CHANGE COLUMN `nm_projeto_submetido` `ar_projeto_submetido` VARCHAR(255) NOT NULL,
CHANGE COLUMN `nm_relatorio_parcial` `ar_relatorio_parcial` VARCHAR(255),
CHANGE COLUMN `nm_relatorio_final` `ar_relatorio_final` VARCHAR(255);

-- -------------------------------------------------------------------------------------------------------------------
-- Deletando o atributo `fl_bolsista` da tabela `tb_participacao`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_participacao`
DROP COLUMN `fl_bolsista`;

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando o atributo `vl_bolsa` com valor padrão 0 na tabela `tb_participacao`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_participacao`
ADD COLUMN `vl_bolsa` DOUBLE NOT NULL DEFAULT 0.00
AFTER `dt_fim`;

-- -------------------------------------------------------------------------------------------------------------------
-- Deletando a tabela bolsa
-- -------------------------------------------------------------------------------------------------------------------
DROP TABLE `tb_bolsa`;

-- -------------------------------------------------------------------------------------------------------------------
-- Renomeando a tabela `tb_instituicao` para `tb_instituicao_financiadora`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_instituicao`
RENAME TO `tb_instituicao_financiadora`;

-- -------------------------------------------------------------------------------------------------------------------
-- Renomeando a tabela `tb_docente` para `tb_instituicao_orientador`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_docente`
RENAME TO `tb_orientador`;

-- -------------------------------------------------------------------------------------------------------------------
-- Deletando a referencia entre `tb_instituicao_has_programa_institucional` e `tb_instituicao`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_instituicao_has_programa_institucional`
DROP FOREIGN KEY fk_instituicao_has_programa_institucional;

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando nova referencia entre `tb_instituicao_has_programa_institucional` e `tb_instituicao_financiadora`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_instituicao_has_programa_institucional`
ADD CONSTRAINT fk_instituicao_has_programa_institucional FOREIGN KEY (instituicao_id) REFERENCES tb_instituicao_financiadora (id_instituicao);

--
-- Alterações de 09/09/2014
-- 

-- -------------------------------------------------------------------------------------------------------------------
-- Alterando o tamanho do campo `tb_intituicao_financiadora`.`nm_instituicao` para 255
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_instituicao_financiadora`
CHANGE COLUMN `nm_instituicao` `nm_instituicao` VARCHAR(255) NOT NULL;

-- -------------------------------------------------------------------------------------------------------------------
-- Alterando o tamanho do campo `tb_pessoa`.`nm_endereco` para 255
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_pessoa`
CHANGE COLUMN `nm_endereco` `nm_endereco` VARCHAR(255) NOT NULL;

--
-- Alterações de 10/09/2014
-- 

-- -------------------------------------------------------------------------------------------------------------------
-- Alterando o tamanho do campo `tb_instituicao_bancaria`.`nm_banco` para 90
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_instituicao_bancaria`
CHANGE COLUMN `nm_banco` `nm_banco` VARCHAR(90) NOT NULL;

-- -------------------------------------------------------------------------------------------------------------------
-- Alterando o nome do campo `tb_turma`.`nr_ano` para `tb_turma`.`nr_perido_letivo`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_turma`
CHANGE COLUMN `nr_ano` `nr_periodo_letivo` INT(2) NOT NULL;

-- -------------------------------------------------------------------------------------------------------------------
-- Alterando o tamanho do campo `tb_curso`.`nm_curso` para 90
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_curso`
CHANGE COLUMN `nm_curso` `nm_curso` VARCHAR(90) NOT NULL;

--
-- Alterações de 16/09/2014
-- 

-- -------------------------------------------------------------------------------------------------------------------
-- Deletando a tabela usuario
-- -------------------------------------------------------------------------------------------------------------------
DROP TABLE `tb_usuario`;

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando o atributo `tb_pessoa.nm_senha`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_pessoa`
ADD COLUMN `nm_senha` VARCHAR(25) NOT NULL
AFTER `nm_email`;

--
-- Alterações de 18/09/2014
-- 

-- -------------------------------------------------------------------------------------------------------------------
-- Deletando a tabela `tb_instituicao_has_programa_institucional`
-- -------------------------------------------------------------------------------------------------------------------
DROP TABLE `tb_instituicao_has_programa_institucional`;

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando o atributo `tb_programa_institucional.instituicao_id`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_programa_institucional`
ADD COLUMN `instituicao_id` INT NOT NULL
AFTER `vl_orcamento`;

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando nova referencia entre `tb_programa_institucional` e `tb_instituicao_financiadora`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_programa_institucional`
ADD CONSTRAINT fk_programa_institucional_instituicao FOREIGN KEY (instituicao_id) REFERENCES tb_instituicao_financiadora (id_instituicao);

--
-- Alterações de 29/09/2014
-- 

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando o atributo `tb_instituicao_financiadora.pessoa_id`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_instituicao_financiadora`
ADD COLUMN `pessoa_id` INT NOT NULL
AFTER `vl_orcamento`;

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando nova referencia entre `tb_instituicao_financiadora` e `tb_pessoa`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_instituicao_financiadora`
ADD CONSTRAINT fk_pessoa_instituicao_financiadora_ FOREIGN KEY (pessoa_id) REFERENCES tb_pessoa (id_pessoa);

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando o atributo `tb_programa_institucional.pessoa_id`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_programa_institucional`
ADD COLUMN `pessoa_id` INT NOT NULL
AFTER `vl_orcamento`;

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando nova referencia entre `tb_programa_institucional` e `tb_pessoa`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_programa_institucional`
ADD CONSTRAINT fk_pessoa_programa_institucional FOREIGN KEY (pessoa_id) REFERENCES tb_pessoa (id_pessoa);

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando o atributo `tb_edital.pessoa_id`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_edital`
ADD COLUMN `pessoa_id` INT NOT NULL
AFTER `programa_institucional_id`;

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando nova referencia entre `tb_edital` e `tb_pessoa`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_edital`
ADD CONSTRAINT fk_pessoa_edital FOREIGN KEY (pessoa_id) REFERENCES tb_pessoa (id_pessoa);

--
-- Alterações de 02/10/2014
-- 

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando a tabela `tb_tipo_pessoa`
-- -------------------------------------------------------------------------------------------------------------------
CREATE TABLE `tb_tipo_pessoa` (
  `id_tipo_pessoa` INT NOT NULL AUTO_INCREMENT,
  `nm_tipo` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id_tipo_pessoa`),
  UNIQUE (`nm_tipo`)
);

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando a referência `tb_pessoa.tipo_pessoa_id`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_pessoa`
ADD COLUMN `tipo_pessoa_id` INT NOT NULL
AFTER `nm_senha`;

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando nova referencia entre `tb_pessoa` e `tb_tipo_pessoa`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_pessoa`
ADD CONSTRAINT fk_pessoa_tipo_pessoa FOREIGN KEY (tipo_pessoa_id) REFERENCES tb_tipo_pessoa (id_tipo_pessoa);

--
-- Alterações de 15/10/2014
-- 

-- -------------------------------------------------------------------------------------------------------------------
-- Alterando o campo `tb_pessoa`.`nm_email` para ser único
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_pessoa`
ADD UNIQUE (`nm_email`);

--
-- Alterações de 22/10/2014
-- 

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando o campo `tb_instituicao_bancaria`.`nr_cnpj`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_instituicao_bancaria`
ADD COLUMN `nr_cnpj` CHAR(14) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
AFTER `nm_banco`;


--
-- Alterações de 11/11/2014
-- 

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando a coluna `tb_curso.pessoa_id`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_curso`
ADD COLUMN `pessoa_id` INT NOT NULL
AFTER `nm_curso`;

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando nova referencia entre `tb_pessoa` e `tb_tipo_pessoa`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_curso`
ADD CONSTRAINT fk_curso_pessoa FOREIGN KEY (pessoa_id) REFERENCES tb_pessoa (id_pessoa);


--
-- Alterações de 11/11/2014
-- 

-- -------------------------------------------------------------------------------------------------------------------
-- Apagando a referência `tb_curso.pessoa_id`
-- -------------------------------------------------------------------------------------------------------------------
ALTER TABLE `tb_curso`
DROP FOREIGN KEY fk_curso_pessoa;

ALTER TABLE `tb_curso`
DROP COLUMN `pessoa_id`;

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando a coluna `tb_curso.pessoa_id`
-- -------------------------------------------------------------------------------------------------------------------

ALTER TABLE `tb_curso`
ADD COLUMN `pessoa_id` INT
AFTER `nm_curso`;

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando a referência `tb_curso.pessoa_id`
-- -------------------------------------------------------------------------------------------------------------------

ALTER TABLE `tb_curso`
ADD CONSTRAINT fk_curso_pessoa FOREIGN KEY (pessoa_id) REFERENCES tb_pessoa (id_pessoa);


--
-- Alterações de 19/11/2014
-- 

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando a coluna `tb_curso.nm_turma`
-- -------------------------------------------------------------------------------------------------------------------

ALTER TABLE `tb_turma`
ADD COLUMN `nm_turma` CHAR(1) NOT NULL
AFTER `nr_periodo_letivo`;

-- -------------------------------------------------------------------------------------------------------------------
-- Redimensionando o campo nm_senha da tb_pessoa para suportar criptografia.
-- -------------------------------------------------------------------------------------------------------------------

ALTER TABLE  tb_pessoa  CHANGE  nm_senha   nm_senha  VARCHAR(255) NOT NULL;
-- Usuário do gestor do sistema com senha criptografada.
UPDATE  qmanager.tb_pessoa SET nm_senha = '13934C744DA605867234E02A5E4CC01F37CF9043546456CAA213133D7E213BD3' 
WHERE tb_pessoa.id_pessoa = 1;