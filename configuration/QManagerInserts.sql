USE `qmanager`;

--
-- Alterações de 07/10/2014
-- 

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando tipos de pessoa existentes até o momento em `tb_tipo_pessoa`
-- -------------------------------------------------------------------------------------------------------------------

INSERT INTO `tb_tipo_pessoa`(`id_tipo_pessoa`, `nm_tipo`) VALUES (1, 'COORDENADOR');

INSERT INTO `tb_tipo_pessoa`(`id_tipo_pessoa`, `nm_tipo`) VALUES (2, 'ORIENTADOR');

INSERT INTO `tb_tipo_pessoa`(`id_tipo_pessoa`, `nm_tipo`) VALUES (3, 'DISCENTE');

--
-- Alterações de 21/10/2014
-- 

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando GESTOR como um tipo de pessoa em `tb_tipo_pessoa`
-- -------------------------------------------------------------------------------------------------------------------

INSERT INTO `tb_tipo_pessoa`(`id_tipo_pessoa`, `nm_tipo`) VALUES (4, 'GESTOR');


--
-- Alterações de 21/10/2014
-- 

-- -------------------------------------------------------------------------------------------------------------------
-- Adicionando Caixa Econômica como um tipo de instituição bancária em `tb_instituicao_bancaria`
-- -------------------------------------------------------------------------------------------------------------------

INSERT INTO `tb_instituicao_bancaria`(`nm_banco`, `nr_cnpj`) VALUES ("Caixa Econômica", 00360305000104);