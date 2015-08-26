CREATE DATABASE `qmanager` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `qmanager`;

--
-- Estrutura da tabela `tb_area`
--

CREATE TABLE IF NOT EXISTS `tb_area` (
  `id_area` int(11) NOT NULL AUTO_INCREMENT,
  `grande_area_id` int(11) NOT NULL COMMENT 'Código da grande área.',
  `cd_area` varchar(11) NOT NULL,
  `nm_area` varchar(100) NOT NULL,
  PRIMARY KEY (`id_area`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=82 ;

--
-- Extraindo dados da tabela `tb_area`
--

INSERT INTO `tb_area` (`id_area`, `grande_area_id`, `cd_area`, `nm_area`) VALUES
(1, 1, '10303022', 'ENGENHARIA DE SOFTWARE'),
(2, 1, '10100008', 'MATEMÁTICA'),
(3, 1, '10200002', 'PROBABILIDADE E ESTATÍSTICA'),
(4, 1, '10300007', 'CIÊNCIA DA COMPUTAÇÃO'),
(5, 1, '10400001', 'ASTRONOMIA'),
(6, 1, '10500006', 'FÍSICA'),
(7, 1, '10600000', 'QUÍMICA'),
(8, 1, '10700005', 'GEOCIÊNCIAS'),
(9, 2, '20100000', 'BIOLOGIA GERAL'),
(10, 2, '20200005', 'GENÉTICA'),
(11, 2, '20600003', 'MORFOLOGIA'),
(12, 2, '20700008', 'FISIOLOGIA'),
(13, 2, '20800002', 'BIOQUÍMICA'),
(14, 2, '20900007', 'BIOFÍSICA'),
(15, 2, '21000000', 'FARMACOLOGIA'),
(16, 2, '21100004', 'IMUNOLOGIA'),
(17, 2, '21200009', 'MICROBIOLOGIA'),
(18, 2, '21300003', 'PARASITOLOGIA'),
(19, 2, '20500009', 'ECOLOGIA'),
(20, 2, '10800000', 'OCEANOGRAFIA'),
(21, 2, '20300000', 'BOTÂNICA'),
(22, 2, '20400004', 'ZOOLOGIA'),
(23, 3, '30100003', 'ENGENHARIA CIVIL'),
(24, 3, '30700000', 'ENGENHARIA SANITÁRIA'),
(25, 3, '31000002', 'ENGENHARIA DE TRANSPORTES'),
(26, 3, '30200008', 'ENGENHARIA DE MINAS'),
(27, 3, '30300002', 'ENGENHARIA DE MATERIAIS E METALÚRGICA'),
(28, 3, '30600006', 'ENGENHARIA QUÍMICA'),
(29, 3, '30900000', 'ENGENHARIA NUCLEAR'),
(30, 3, '30500001', 'ENGENHARIA MECÂNICA'),
(31, 3, '30800005', 'ENGENHARIA DE PRODUÇÃO'),
(32, 3, '31100007', 'ENGENHARIA NAVAL E OCEÂNICA'),
(33, 3, '31200001', 'ENGENHARIA AEROESPACIAL'),
(34, 3, '30400007', 'ENGENHARIA ELÉTRICA'),
(35, 3, '31300006', 'ENGENHARIA BIOMÉDICA'),
(36, 4, '40100006', 'MEDICINA'),
(37, 4, '40500004', 'NUTRIÇÃO'),
(38, 4, '40200000', 'ODONTOLOGIA'),
(39, 4, '40300005', 'FARMÁCIA'),
(40, 4, '40400000', 'ENFERMAGEM'),
(41, 4, '40600009', 'SAÚDE COLETIVA'),
(42, 4, '40900002', 'EDUCAÇÃO FÍSICA'),
(43, 4, '40700003', 'FONOAUDIOLOGIA'),
(44, 4, '40800008', 'FISIOTERAPIA E TERAPIA OCUPACIONAL'),
(45, 5, '50100009', 'AGRONOMIA'),
(46, 5, '50200003', 'RECURSOS FLORESTAIS E ENGENHARIA FLORESTAL'),
(47, 5, '50300008', 'ENGENHARIA AGRÍCOLA'),
(48, 5, '50400002', 'ZOOTECNIA'),
(49, 5, '50600001', 'RECURSOS PESQUEIROS E ENGENHARIA DE PESCA'),
(50, 5, '50500007', 'MEDICINA VETERINÁRIA'),
(51, 5, '50700006', 'CIÊNCIA E TECNOLOGIA DE ALIMENTOS'),
(52, 6, '60100001', 'DIREITO'),
(53, 6, '60200006', 'ADMINISTRAÇÃO'),
(54, 6, '61300004', 'TURISMO'),
(55, 6, '60300000', 'ECONOMIA'),
(56, 6, '60400005', 'ARQUITETURA E URBANISMO'),
(57, 6, '61200000', 'DESENHO INDUSTRIAL'),
(58, 6, '60500000', 'PLANEJAMENTO URBANO E REGIONAL'),
(59, 6, '60600004', 'DEMOGRAFIA'),
(60, 6, '60700009', 'CIÊNCIA DA INFORMAÇÃO'),
(61, 6, '60800003', 'MUSEOLOGIA'),
(62, 6, '60900008', 'COMUNICAÇÃO'),
(63, 6, '61000000', 'SERVIÇO SOCIAL'),
(64, 7, '70100004', 'FILOSOFIA'),
(65, 7, '71000003', 'TEOLOGIA'),
(66, 7, '70200009', 'SOCIOLOGIA'),
(67, 7, '70300003', 'ANTROPOLOGIA'),
(68, 7, '70400008', 'ARQUEOLOGIA'),
(69, 7, '70500002', 'HISTÓRIA'),
(70, 7, '70600007', 'GEOGRAFIA'),
(71, 7, '70700001', 'PSICOLOGIA'),
(72, 7, '70800006', 'EDUCAÇÃO'),
(73, 7, '70900000', 'CIÊNCIA POLÍTICA'),
(74, 8, '80100007', 'LINGUÍSTICA '),
(75, 8, '80200001', 'LETRAS'),
(76, 8, '80300006', 'ARTES'),
(77, 9, '90100000', 'INTERDISCIPLINAR'),
(78, 9, '90200000', 'ENSINO'),
(79, 9, '90300009', 'MATERIAIS'),
(80, 9, '90400003', 'BIOTECNOLOGIA'),
(81, 9, '90500008', 'CIÊNCIAS AMBIENTAIS');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_area_tematica_extensao`
--

CREATE TABLE IF NOT EXISTS `tb_area_tematica_extensao` (
  `id_area_tematica` int(11) NOT NULL AUTO_INCREMENT,
  `nm_area_tematica` varchar(45) NOT NULL,
  `nm_sigla` varchar(25) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_area_tematica`),
  UNIQUE KEY `nm_area_tematica` (`nm_area_tematica`),
  UNIQUE KEY `nm_sigla` (`nm_sigla`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Extraindo dados da tabela `tb_area_tematica_extensao`
--

INSERT INTO `tb_area_tematica_extensao` (`id_area_tematica`, `nm_area_tematica`, `nm_sigla`, `dt_registro`) VALUES
(1, 'Comunicação', 'COM', '2014-12-26 14:38:25'),
(2, 'Cultura', 'CUL', '2014-12-26 14:38:25'),
(3, 'Direitos Humanos', 'DHJ', '2014-12-26 14:38:25'),
(4, 'Educação', 'EDU', '2014-12-26 14:38:25'),
(5, 'Meio ambiente', 'MAM', '2014-12-26 14:38:25'),
(6, 'Saúde', 'SAU', '2014-12-26 14:38:25'),
(7, 'Tecnologia', 'TEC', '2014-12-26 14:38:25'),
(8, 'Trabalho', 'TRA', '2014-12-26 14:38:25');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_arquivo`
--

CREATE TABLE IF NOT EXISTS `tb_arquivo` (
  `id_arquivo` int(11) NOT NULL AUTO_INCREMENT,
  `nm_real_arquivo` varchar(255) NOT NULL,
  `nm_sistema_arquivo` varchar(255) NOT NULL,
  `nm_extensao_arquivo` varchar(10) NOT NULL,
  `tp_arquivo` int(11) NOT NULL COMMENT 'Tipos: projeto inicial, projeto corrigido, relatório parcial',
  `pessoa_id` int(11) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_arquivo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_arquivo_edital`
--

CREATE TABLE IF NOT EXISTS `tb_arquivo_edital` (
  `id_arquivo_edital` int(11) NOT NULL AUTO_INCREMENT,
  `edital_id` int(11) NOT NULL COMMENT 'Código do Edital',
  `arquivo_id` int(11) NOT NULL COMMENT 'Código do Arquivo',
  `tp_arquivo_edital` int(11) NOT NULL COMMENT 'Tipo de arquivo de edital: edital, retificação',
  PRIMARY KEY (`id_arquivo_edital`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_arquivo_participacao`
--

CREATE TABLE IF NOT EXISTS `tb_arquivo_participacao` (
  `id_arquivo_participacao` int(11) NOT NULL AUTO_INCREMENT,
  `participacao_id` int(11) NOT NULL COMMENT 'Código da Participação',
  `arquivo_id` int(11) NOT NULL COMMENT 'Código do Arquivo',
  `tp_arquivo_participacao` int(11) NOT NULL COMMENT 'Tipo do aquivo de participação: termo de voluntariado, termo de vínculo empregatício, plano individual de trabalho.',
  PRIMARY KEY (`id_arquivo_participacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_arquivo_projeto`
--

CREATE TABLE IF NOT EXISTS `tb_arquivo_projeto` (
  `id_arquivo_projeto` int(11) NOT NULL AUTO_INCREMENT,
  `projeto_id` int(11) NOT NULL COMMENT 'Código do Projeto',
  `arquivo_id` int(11) NOT NULL COMMENT 'Código do Arquivo',
  `tp_arquivo_projeto` int(11) NOT NULL COMMENT 'Tipo do arquivo do Projeto: projeto identificado, projeto não identificado',
  PRIMARY KEY (`id_arquivo_projeto`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_atividade_extensao`
--

CREATE TABLE IF NOT EXISTS `tb_atividade_extensao` (
  `id_atividade_extensao` int(11) NOT NULL AUTO_INCREMENT,
  `nm_atividade_extensao` varchar(45) NOT NULL,
  `nm_sigla` varchar(25) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_atividade_extensao`),
  UNIQUE KEY `nm_atividade_extensao` (`nm_atividade_extensao`),
  UNIQUE KEY `nm_sigla` (`nm_sigla`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Extraindo dados da tabela `tb_atividade_extensao`
--

INSERT INTO `tb_atividade_extensao` (`id_atividade_extensao`, `nm_atividade_extensao`, `nm_sigla`, `dt_registro`) VALUES
(1, 'Programa de Extensão', 'PRO', '2014-12-26 14:38:25'),
(2, 'Projeto de Extensão', 'PE', '2014-12-26 14:38:25'),
(3, 'Curso de Extensão', 'CE', '2014-12-26 14:38:25'),
(4, 'Evento de Extensao', 'EE', '2014-12-26 14:38:25'),
(5, 'Prestação de Serviço', 'PS', '2014-12-26 14:38:25'),
(6, 'Empreendendorismo', 'EP', '2014-12-26 14:38:25'),
(7, 'Visitas Técnicas', 'VT', '2014-12-26 14:38:25'),
(8, 'Acompanhamento de Egressos', 'AE', '2014-12-26 14:38:25');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_campus_institucional`
--

CREATE TABLE IF NOT EXISTS `tb_campus_institucional` (
  `id_campus_institucional` int(11) NOT NULL AUTO_INCREMENT,
  `nm_campus_institucional` varchar(45) NOT NULL,
  `id_cidade` int(11) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_campus_institucional`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Extraindo dados da tabela `tb_campus_institucional`
--

INSERT INTO `tb_campus_institucional` (`id_campus_institucional`, `nm_campus_institucional`, `id_cidade`, `dt_registro`) VALUES
(1, 'Cabedelo', 0, '2015-03-03 21:08:40'),
(2, 'Cajazeiras', 0, '2015-03-03 21:08:40'),
(3, 'Campina Grande', 0, '2015-03-03 21:08:40'),
(4, 'Guarabira', 0, '2015-03-03 21:08:40'),
(5, 'João Pessoa', 0, '2015-03-03 21:08:40'),
(6, 'Monteiro', 0, '2015-03-03 21:08:40'),
(7, 'Patos', 0, '2015-03-03 21:08:40'),
(8, 'Princesa Isabel', 0, '2015-03-03 21:08:40'),
(9, 'Picuí', 0, '2015-03-03 21:08:40'),
(10, 'Sousa', 0, '2015-03-03 21:08:40'),
(11, 'Reitoria', 0, '2015-03-03 21:13:33');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_cargo_servidor`
--

CREATE TABLE IF NOT EXISTS `tb_cargo_servidor` (
  `id_cargo_servidor` int(11) NOT NULL AUTO_INCREMENT,
  `nm_cargo_servidor` varchar(25) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_cargo_servidor`),
  UNIQUE KEY `nm_cargo_servidor` (`nm_cargo_servidor`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `tb_cargo_servidor`
--

INSERT INTO `tb_cargo_servidor` (`id_cargo_servidor`, `nm_cargo_servidor`, `dt_registro`) VALUES
(1, 'Gestor', '2014-12-26 14:38:25'),
(2, 'Coordenador', '2014-12-26 14:38:25'),
(3, 'Professor', '2014-12-26 14:38:25'),
(4, 'Técnico Administrativo', '2014-12-26 14:38:25');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_curso`
--

CREATE TABLE IF NOT EXISTS `tb_curso` (
  `id_curso` int(11) NOT NULL AUTO_INCREMENT,
  `nm_curso` varchar(90) NOT NULL,
  `coordenador_id` int(11) NOT NULL,
  `pessoa_id` int(11) DEFAULT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_curso`),
  UNIQUE KEY `nm_curso` (`nm_curso`),
  KEY `fk_curso_pessoa` (`pessoa_id`),
  KEY `fk_curso_coordenador` (`coordenador_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `tb_curso`
--

INSERT INTO `tb_curso` (`id_curso`, `nm_curso`, `coordenador_id`, `pessoa_id`, `dt_registro`) VALUES
(1, 'Técnico em Informática Integrado ao Ensino Médio', 3, 1, '2014-12-26 14:38:26'),
(2, 'Técnico em Mineração Integrado ao Ensino Médio', 1, 1, '2014-12-26 14:38:26');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_dados_bancarios`
--

CREATE TABLE IF NOT EXISTS `tb_dados_bancarios` (
  `pessoa_id` int(11) NOT NULL,
  `instituicao_bancaria_id` int(11) NOT NULL,
  `nr_operacao` varchar(3) DEFAULT NULL,
  `nr_conta` varchar(15) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pessoa_id`),
  KEY `fk_dados_bancarios_instituicao_bancaria` (`instituicao_bancaria_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tb_dados_bancarios`
--

INSERT INTO `tb_dados_bancarios` (`pessoa_id`, `instituicao_bancaria_id`, `nr_operacao`, `nr_conta`, `dt_registro`) VALUES
(1, 1, '013', '123456789012345', '2014-12-26 14:38:26'),
(2, 1, '013', '123456789054321', '2014-12-26 14:38:26'),
(3, 2, NULL, '905432112345678', '2014-12-26 14:38:26'),
(4, 1, '013', '345126789054321', '2014-12-26 14:38:26');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_departamento`
--

CREATE TABLE IF NOT EXISTS `tb_departamento` (
  `id_departamento` int(11) NOT NULL AUTO_INCREMENT,
  `nm_departamento` varchar(150) NOT NULL,
  PRIMARY KEY (`id_departamento`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=33 ;

--
-- Extraindo dados da tabela `tb_departamento`
--

INSERT INTO `tb_departamento` (`id_departamento`, `nm_departamento`) VALUES
(1, 'Direção Geral'),
(2, 'Secretaria da Direção Geral'),
(3, 'Chefia de Gabinete'),
(4, 'Assessoria de Comunicação'),
(5, 'Diretoria de Administração'),
(6, 'Diretoria de Ensino'),
(7, 'Coordenação de Estágios'),
(8, 'Coordenação de Controle Acadêmico'),
(9, 'Coordenação Pedagógica'),
(10, 'Coordenação de Assistência ao Estudante'),
(11, 'Coordenação de Tecnologia da Informação'),
(12, 'Coordenação dos cursos Técnicos'),
(13, 'Coordenação de Formação Geral e Projetos Especiais'),
(14, 'Coordenação de Pesquisa e Extensão'),
(15, 'Coordenação do Proeja'),
(16, 'Coordenação do NAPNE'),
(17, 'Coordenação dos cursos técnicos em Mineração'),
(18, 'Coordenação dos cursos técnicos em Petróleo e Gás'),
(19, 'Coordenação dos cursos técnicos em Informática'),
(20, 'Coordenação dos Cursos Superiores'),
(21, 'Coordenação do Curso Superior de Tecnologia em Construção de Edifícios'),
(22, 'Coordenação do Curso Superior de Tecnologia em Telemática'),
(23, 'Coordenação do Curso Superior de Licenciatura em Matemática'),
(24, 'Coordenação do Curso Superior de Licenciatura em Física'),
(25, 'Coordenação dos Cursos à Distância - EaD'),
(26, 'Coordenação de Manutenção, Segurança e Transportes'),
(27, 'Coordenação de Gestão de Pessoas'),
(28, 'Coordenação de Compras'),
(29, 'Coordenação de Almoxarifado e Patrimônio'),
(30, 'Coordenação de Execução Orçamentária e Financeira'),
(31, 'Biblioteca'),
(32, 'Gabinete Médico - Odontológico');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_discente`
--

CREATE TABLE IF NOT EXISTS `tb_discente` (
  `pessoa_id` int(11) NOT NULL,
  `turma_id` int(11) NOT NULL,
  PRIMARY KEY (`pessoa_id`),
  KEY `fk_discente_turma` (`turma_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tb_discente`
--

INSERT INTO `tb_discente` (`pessoa_id`, `turma_id`) VALUES
(2, 1),
(4, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_edital`
--

CREATE TABLE IF NOT EXISTS `tb_edital` (
  `id_edital` int(11) NOT NULL AUTO_INCREMENT,
  `nr_edital` int(3) NOT NULL,
  `nr_ano` int(4) NOT NULL,
  `nm_numero_ano` varchar(8) NOT NULL,
  `nm_titulo` varchar(255) NOT NULL,
  `nm_descricao` varchar(255) NOT NULL,
  `dt_inicio_inscricoes` date NOT NULL,
  `dt_fim_inscricoes` date NOT NULL,
  `dt_inicio_avaliacao` date NOT NULL COMMENT 'Data inicial da avaliação',
  `dt_fim_avaliacao` date NOT NULL COMMENT 'Data final da avaliação',
  `dt_resultado_preliminar` date NOT NULL COMMENT 'Data de divulgação do resultado preliminar',
  `dt_receber_recursos` date NOT NULL COMMENT 'Data de submissão dos recursos',
  `dt_resultado_final` date NOT NULL COMMENT 'Data de publicação do resultado final',
  `dt_inicio_atividades` date NOT NULL COMMENT 'Data do início das atividades do projeto',
  `dt_relatorio_parcial` date NOT NULL,
  `dt_relatorio_final` date NOT NULL,
  `nr_projetos_aprovados` int(11) NOT NULL COMMENT 'Quantidade de projetos aprovados',
  `nr_vagas_discentes_bolsistas` int(11) NOT NULL COMMENT 'Quantidade de bolsas para Discentes Bolsistas',
  `nr_vagas_voluntarios` int(11) NOT NULL COMMENT 'Quantidade de bolsas para Voluntários',
  `vl_bolsa_discente` double NOT NULL,
  `nr_vagas_docentes_bolsistas` int(11) NOT NULL,
  `vl_bolsa_docente` double NOT NULL,
  `programa_institucional_id` int(11) NOT NULL,
  `pessoa_id` int(11) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_edital`),
  UNIQUE KEY `nm_numero_ano` (`nm_numero_ano`),
  KEY `fk_edital_programa_institucional` (`programa_institucional_id`),
  KEY `fk_pessoa_edital` (`pessoa_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `tb_edital`
--

INSERT INTO `tb_edital` (`id_edital`, `nr_edital`, `nr_ano`, `nm_numero_ano`, `nm_titulo`, `nm_descricao`, `dt_inicio_inscricoes`, `dt_fim_inscricoes`, `dt_inicio_avaliacao`, `dt_fim_avaliacao`, `dt_resultado_preliminar`, `dt_receber_recursos`, `dt_resultado_final`, `dt_inicio_atividades`, `dt_relatorio_parcial`, `dt_relatorio_final`, `nr_projetos_aprovados`, `nr_vagas_discentes_bolsistas`, `nr_vagas_voluntarios`, `vl_bolsa_discente`, `nr_vagas_docentes_bolsistas`, `vl_bolsa_docente`, `programa_institucional_id`, `pessoa_id`, `dt_registro`) VALUES
(1, 11, 2014, '11/2014', 'Edital 1', 'Descrição Edital 1 ', '2014-10-01', '2014-11-30', '2014-12-01', '2014-12-10', '2014-12-11', '2014-12-12', '2014-12-13', '2015-01-02', '2015-03-11', '2015-09-30', 0, 0, 0, 100, 0, 500, 1, 1, '2014-12-26 14:38:26'),
(2, 10, 2015, '10/2015', 'null', 'Edital 10/2015', '2015-07-08', '2015-07-09', '2015-07-10', '2015-07-11', '2015-07-12', '2015-07-13', '2015-07-14', '2015-07-15', '2015-07-16', '2015-07-17', 0, 0, 0, 100, 0, 0, 1, 1, '2015-07-08 12:39:33');

--
-- Acionadores `tb_edital`
--
DROP TRIGGER IF EXISTS `tgr_edital_sequencia`;
DELIMITER //
CREATE TRIGGER `tgr_edital_sequencia` AFTER INSERT ON `tb_edital`
 FOR EACH ROW BEGIN
  DECLARE result INT UNSIGNED DEFAULT 0;
  SET result = (SELECT COUNT(*) FROM tb_sequencia_nr_edital 
                WHERE new.nr_ano = nr_ano);
  IF result = 1 THEN
    UPDATE tb_sequencia_nr_edital SET nr_sequencia_edital = new.nr_edital
    WHERE new.nr_ano = nr_ano;
  ELSE
    INSERT INTO tb_sequencia_nr_edital 
    VALUES (new.nr_ano, new.nr_edital);
  END IF;
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_grande_area`
--

CREATE TABLE IF NOT EXISTS `tb_grande_area` (
  `id_grande_area` int(11) NOT NULL AUTO_INCREMENT,
  `cd_grande_area` varchar(11) NOT NULL,
  `nm_grande_area` varchar(100) NOT NULL,
  PRIMARY KEY (`id_grande_area`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Extraindo dados da tabela `tb_grande_area`
--

INSERT INTO `tb_grande_area` (`id_grande_area`, `cd_grande_area`, `nm_grande_area`) VALUES
(1, '10000003', 'CIÊNCIAS EXATAS E DA TERRA'),
(2, '20000006', 'CIÊNCIAS BIOLÓGICAS'),
(3, '30000009', 'ENGENHARIAS'),
(4, '40000001', 'CIÊNCIAS DA SAÚDE'),
(5, '50000004', 'CIÊNCIAS AGRÁRIAS'),
(6, '60000007', 'CIÊNCIAS SOCIAIS APLICADAS'),
(7, '70000000', 'CIÊNCIAS HUMANAS'),
(8, '80000002', 'LINGUÍSTICA, LETRAS E ARTES'),
(9, '90000005', 'MULTIDISCIPLINAR');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_instituicao_bancaria`
--

CREATE TABLE IF NOT EXISTS `tb_instituicao_bancaria` (
  `id_instituicao_bancaria` int(11) NOT NULL AUTO_INCREMENT,
  `nm_banco` varchar(90) NOT NULL,
  `nr_cnpj` char(14) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_instituicao_bancaria`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `tb_instituicao_bancaria`
--

INSERT INTO `tb_instituicao_bancaria` (`id_instituicao_bancaria`, `nm_banco`, `nr_cnpj`, `dt_registro`) VALUES
(1, 'Caixa Econômica', '360305000104', '2014-12-26 14:38:26'),
(2, 'Banco do Brasil SA', '00000000000191', '2014-12-26 14:38:26');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_instituicao_financiadora`
--

CREATE TABLE IF NOT EXISTS `tb_instituicao_financiadora` (
  `id_instituicao` int(11) NOT NULL AUTO_INCREMENT,
  `nr_cnpj` char(14) NOT NULL,
  `nm_instituicao` varchar(255) NOT NULL,
  `nm_sigla` varchar(10) NOT NULL,
  `pessoa_id` int(11) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_instituicao`),
  UNIQUE KEY `nr_cnpj` (`nr_cnpj`),
  KEY `fk_pessoa_instituicao_financiadora_` (`pessoa_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Extraindo dados da tabela `tb_instituicao_financiadora`
--

INSERT INTO `tb_instituicao_financiadora` (`id_instituicao`, `nr_cnpj`, `nm_instituicao`, `nm_sigla`, `pessoa_id`, `dt_registro`) VALUES
(1, '10783898000175', 'Instituto Federal de Educação, Ciência e Tecnologia da Paraíba', 'IFPB', 1, '2014-12-26 14:38:26'),
(2, '55555555555555', 'Rede Nacional de Pesquisa', 'RNP', 1, '2015-02-16 14:14:51'),
(3, '77777777777777', 'Coordenação de Aperfeiçoamento de Pessoal de Nível Superior', 'Capes', 1, '2015-03-04 20:29:37'),
(4, '66666666666666', 'Conselho Nacional de Desenvolvimento Científico e Tecnológico', 'CNPQ', 1, '2015-03-04 20:31:35'),
(5, '44444444444444', 'Fundação de Amparo à Pesquisa do Estado de São Paulo', 'FAPESP', 1, '2015-03-06 14:42:50'),
(6, '23483924738247', 'Universidade Federal de Campina Grande', 'UFCG', 1, '2015-03-21 00:20:54'),
(7, '55555666666666', 'Fundação de Amparo à Ciência e Tecnologia de Pernambuco', 'FACEPE', 1, '2015-03-27 13:03:14');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_linha_programatica_extensao`
--

CREATE TABLE IF NOT EXISTS `tb_linha_programatica_extensao` (
  `id_linha_programatica` int(11) NOT NULL AUTO_INCREMENT,
  `nm_linha_programatica` varchar(90) NOT NULL,
  `nm_definicao` varchar(500) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_linha_programatica`),
  UNIQUE KEY `nm_linha_programatica` (`nm_linha_programatica`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_local`
-- Remover estrutura da tabela local.

CREATE TABLE IF NOT EXISTS `tb_local` (
  `id_local` int(11) NOT NULL AUTO_INCREMENT,
  `nm_local` varchar(60) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_local`),
  UNIQUE KEY `nm_local` (`nm_local`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Extraindo dados da tabela `tb_local`
--

INSERT INTO `tb_local` (`id_local`, `nm_local`, `dt_registro`) VALUES
(1, 'Reitoria', '2014-12-26 14:38:26'),
(2, 'Campus João Pessoa', '2014-12-26 14:38:26'),
(3, 'Campus Cajazeiras', '2014-12-26 14:38:26'),
(4, 'Campus Campina Grande', '2014-12-26 14:38:26'),
(5, 'Campus Sousa', '2014-12-26 14:38:26'),
(6, 'Campus Cabedelo', '2014-12-26 14:38:26'),
(7, 'Campus Picuí', '2014-12-26 14:38:26'),
(8, 'Campus Princesa Isabel', '2014-12-26 14:38:26'),
(9, 'Campus Patos', '2014-12-26 14:38:26'),
(10, 'Campus Monteiro', '2014-12-26 14:38:26'),
(11, 'Centro de Referencia em Pesca e Navegação Marítima', '2014-12-26 14:38:26');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_participacao`
--

CREATE TABLE IF NOT EXISTS `tb_participacao` (
  `id_participacao` int(11) NOT NULL AUTO_INCREMENT,
  `pessoa_id` int(11) NOT NULL,
  `projeto_id` int(11) NOT NULL,
  `dt_inicio` date NOT NULL,
  `dt_fim` date DEFAULT NULL,
  `tipo_participacao_id` int(11) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_participacao`),
  KEY `fk_participacao_projeto` (`projeto_id`),
  KEY `fk_participacao_pessoa` (`pessoa_id`),
  KEY `fk_participacao_tipo_participacao` (`tipo_participacao_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=150 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_pessoa`
--

CREATE TABLE IF NOT EXISTS `tb_pessoa` (
  `id_pessoa` int(11) NOT NULL AUTO_INCREMENT,
  `nm_pessoa` varchar(90) NOT NULL,
  `nr_cpf` char(11) NOT NULL,
  `nr_matricula` varchar(20) NOT NULL,
  `nm_endereco` varchar(255) NOT NULL,
  `nm_cep` char(8) NOT NULL,
  `nm_telefone` varchar(12) NOT NULL,
  `nm_email` varchar(90) NOT NULL,
  `nm_url_lattes` varchar(255) NOT NULL,
  `nm_senha` varchar(255) NOT NULL,
  `tipo_pessoa_id` int(11) NOT NULL,
  `local_id` int(11) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_pessoa`),
  UNIQUE KEY `nr_cpf` (`nr_cpf`),
  UNIQUE KEY `nr_matricula` (`nr_matricula`),
  UNIQUE KEY `nm_email` (`nm_email`),
  KEY `fk_pessoa_tipo_pessoa` (`tipo_pessoa_id`),
  KEY `fk_pessoa_local` (`local_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `tb_pessoa`
--

INSERT INTO `tb_pessoa` (`id_pessoa`, `nm_pessoa`, `nr_cpf`, `nr_matricula`, `nm_endereco`, `nm_cep`, `nm_telefone`, `nm_email`, `nm_url_lattes`, `nm_senha`, `tipo_pessoa_id`, `local_id`, `dt_registro`) VALUES
(1, 'Márcia Maria Costa Gomes', '12345678910', '12345678901', 'Rua Presidente Tancredo Neves. Bairro: Jardim Sorrilândia', '58015430', '8332083004', 'marcia.gomes@gmail.com', '', '13934C744DA605867234E02A5E4CC01F37CF9043546456CAA213133D7E213BD3', 1, 4, '2014-12-26 14:38:26'),
(2, 'Eri Jonhson Oliveira da Silva', '12345678921', '20111003145', 'Rua Muniz Barreto de Lima, 92', '58487564', '8399795879', 'erijonhson.os@gmail.com', '', 'E943BBABCB6A41061EA11CABBE8CF5445202F35C255607795289D89737045EB7', 2, 4, '2014-12-26 14:38:26'),
(3, 'Rhavy Maia Guedes', '09876534523', '32456798', 'Rua Capitão Domingos Cariris', '58432562', '8396432156', 'rhavy.maia@gmail.com', '', '13934C744DA605867234E02A5E4CC01F37CF9043546456CAA213133D7E213BD3', 1, 4, '2014-12-26 14:38:26'),
(4, 'Felipe Nascimento Souza', '56781234910', '20111004531', 'Rua Argentina, 34', '58562432', '8396215643', 'felipe_nsousa@hotmail.com', '', '023FA1ACB18623491AD8376A99498D1E1DAEE4E47F87DFB62ACB2938FB659A60', 2, 4, '2014-12-26 14:38:26');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_pessoa_habilitada`
--

CREATE TABLE IF NOT EXISTS `tb_pessoa_habilitada` (
  `id_pessoa_habilitada` int(11) NOT NULL AUTO_INCREMENT,
  `nm_pessoa_habilitada` varchar(90) NOT NULL,
  `nr_siape` int(11) NOT NULL,
  `id_campus_institucional` int(11) NOT NULL,
  `id_departamento` int(11) NOT NULL,
  `id_cargo_servidor` int(11) NOT NULL,
  `id_titulacao` int(11) NOT NULL,
  `nm_email` varchar(90) DEFAULT NULL,
  `fl_habilitada` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_pessoa_habilitada`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=191 ;

--
-- Extraindo dados da tabela `tb_pessoa_habilitada`
--

INSERT INTO `tb_pessoa_habilitada` (`id_pessoa_habilitada`, `nm_pessoa_habilitada`, `nr_siape`, `id_campus_institucional`, `id_departamento`, `id_cargo_servidor`, `id_titulacao`, `nm_email`, `fl_habilitada`) VALUES
(1, 'adeilma carneiro bastos', 0, 3, 17, 3, 3, '', 0),
(2, 'adenilson targino de araujo junior', 0, 3, 13, 3, 3, '', 0),
(3, 'aderdilania iane barbosa de azevedo', 101010, 3, 13, 3, 3, 'ianeazevedo.bio@gmail.com', 0),
(4, 'adriana araujo costeira de andrade', 101011, 3, 13, 3, 3, 'adrianacosteira@ifpb.edu.br', 1),
(5, 'adriana lemos porto', 101012, 3, 17, 3, 3, 'adrianalemosporto@hotmail.com', 0),
(6, 'adriana sales barros', 101013, 3, 13, 3, 3, '', 1),
(7, 'aglailson gladson cabral de oliveira', 101014, 3, 13, 3, 1, 'aglailson.oliveira@ifb.edu.br', 1),
(8, 'agnes campello araujo braz', 101015, 3, 12, 3, 1, 'agnescampello@gmail.com', 1),
(9, 'alan carlos monteiro junior', 101016, 3, 13, 3, 3, 'alanmonteirojr@gmail.com', 1),
(10, 'alexandre sales vasconcelos', 101017, 3, 19, 3, 3, 'alexandreufcg@gmail.com', 1),
(11, 'alionalia sharlon maciel batista ramos lopes', 0, 3, 13, 3, 3, '', 0),
(12, 'allan patrick de lucena costa', 0, 3, 13, 3, 3, '', 0),
(13, 'amanda christinne nascimento marques', 0, 3, 13, 3, 1, '', 0),
(14, 'ana claudia mousinho ferreira', 0, 3, 17, 3, 3, '', 0),
(15, 'ana cristina alves de oliveira', 0, 3, 19, 3, 3, 'cristina.alves@gmail.com', 0),
(16, 'ana paula sousa silva', 0, 3, 13, 3, 3, '', 0),
(17, 'anderson fabiano batista ferreira da costa', 0, 3, 19, 3, 4, 'anderson@ifpb.edu.br', 0),
(18, 'angelo marcio formiga de almeida', 0, 3, 22, 3, 1, '', 0),
(19, 'anna giovanna rocha bezerra', 0, 3, 13, 3, 3, 'annagiovanna@gmail.com', 0),
(20, 'annaxsuel araujo de lima', 0, 3, 23, 3, 1, 'annaxgt@yahoo.com.br', 0),
(21, 'anne karine de queiroz', 0, 3, 13, 3, 3, 'anne.midias@gmail.com', 0),
(22, 'antonio jesus souza melo neto', 0, 3, 13, 3, 1, 'antoniojesuscg@hotmail.com', 0),
(23, 'antônio leite de andrade', 0, 3, 17, 3, 1, 'antonioleite@consulmina.com', 0),
(24, 'aridan lira leite', 0, 3, 19, 3, 1, '', 0),
(25, 'arllington rodrigues ferreira da costa', 0, 3, 19, 3, 1, '', 0),
(26, 'augusto csar dias de araujo', 0, 3, 13, 3, 3, 'acdaraujo@gmail.com', 0),
(27, 'bruno de brito leite', 0, 3, 19, 3, 3, 'brunodebrito@gmail.com', 0),
(28, 'bruno formiga guimarães', 0, 3, 19, 3, 3, 'bruno.formiga.guimaraes@hotmail.com', 0),
(29, 'bruno jacome cavalcanti', 101113, 3, 19, 3, 1, 'brjcweb@yahoo.com.br', 1),
(30, 'carlo reillen lima martins', 101112, 3, 12, 3, 1, 'reillen@gmail.com', 0),
(31, 'carlos alex souza da silva', 101114, 3, 13, 3, 4, 'calex@fisica.ufce.br', 1),
(32, 'carlos david de carvalho lobao', 0, 3, 13, 3, 3, 'davidlobaoifpb@gmail.com', 0),
(33, 'carlos renato paz', 101115, 3, 13, 3, 2, 'renatopaz@hotmail.com', 1),
(34, 'celso de araujo', 0, 3, 13, 3, 2, 'celso.araujo@ifpb.edu.br', 0),
(35, 'cesar rocha vasconcelos', 0, 3, 19, 3, 3, 'cesarrocha@ifpb.edu.br', 0),
(36, 'cicero da silva pereira', 0, 3, 13, 3, 3, 'cspmat@gmail.com', 0),
(37, 'clarice oliveira da rocha', 0, 3, 12, 3, 3, 'clariceoliveirarocha@gmail.com', 0),
(38, 'cláudio sebastião vasconcelos da cunha cavalcanti', 0, 3, 19, 3, 1, 'claudiosvcc@gmail.com', 0),
(39, 'cristiane vieira do nascimento', 0, 3, 13, 3, 3, 'crisvn@gmail.com', 0),
(40, 'cíntia de sousa bezerra', 0, 3, 12, 3, 4, 'cintiasbezerra@gmail.com', 0),
(41, 'daniella dias cavalcante da silva', 0, 3, 22, 3, 4, 'daniella.silva@ifpb.edu.br', 0),
(42, 'danielly vieira de lucena', 0, 3, 18, 3, 3, 'daniellymateriais@yahoo.com.br', 0),
(43, 'danilo rangel arruda leite', 0, 3, 19, 3, 2, 'danilorangelleite@live.com', 0),
(44, 'david candeia medeiros maia', 0, 3, 22, 3, 3, 'davcandeia@gmail.com', 0),
(45, 'debora janaina ribeiro e silva', 0, 3, 23, 3, 3, 'debora_jr@yahoo.com.br', 0),
(46, 'denis barros barbosa', 0, 3, 13, 3, 4, 'denis.fisica@yahoo.com.br', 0),
(47, 'diego fernandes sales', 0, 3, 13, 3, 1, '', 0),
(48, 'divanira ferreira maia', 0, 3, 13, 3, 4, 'divaniram@yahoo.com.br', 0),
(49, 'djalma toscano de oliveira neto', 0, 3, 13, 3, 3, '', 0),
(50, 'douglas antonio bezerra ramos', 0, 3, 13, 3, 2, 'dabramos@yahoo.com.br', 0),
(51, 'dwight rodrigues soares', 0, 3, 17, 3, 5, '', 0),
(52, 'dbora cristina santos', 0, 3, 13, 3, 2, 'debyncris@hotmail.com', 0),
(53, 'edilane rodrigues bento moreira', 0, 3, 13, 3, 4, 'edilane_rbento@hotmail.com', 0),
(54, 'edison fernando da silva lima', 0, 3, 13, 3, 2, 'proffernandolima@gmail.com', 0),
(55, 'edmilson dantas da silva filho', 0, 3, 13, 3, 4, 'edmsegundo@hotmail.com', 0),
(56, 'edmundo dantas filho', 0, 3, 13, 3, 3, 'edmundofisica@hotmail.com', 0),
(57, 'elaine cristina juvino de araujo', 0, 3, 19, 3, 3, 'elaine@gmail.com', 0),
(58, 'elias antonio freire', 0, 3, 19, 3, 3, 'eafreire@ibest.com.br', 0),
(59, 'ellis regina ferreira dos santos', 0, 3, 13, 3, 4, 'ellisrf@yahoo.com.br', 0),
(60, 'emerson de souza lima', 0, 3, 19, 3, 2, 'emersondesouzalima@gmail.com', 0),
(61, 'eriverton da silva rodrigues', 0, 3, 13, 3, 2, 'erivertonr@hotmail.com', 0),
(62, 'ewerton romulo silva castro', 0, 3, 13, 3, 3, 'ewerton.castro@ifpb.edu.br', 0),
(63, 'fabiana bezerra marinho', 0, 3, 13, 3, 3, 'fabiana_mbr@yahoo.com.br', 0),
(64, 'fabio silveira martins de oliveira', 0, 3, 21, 3, 3, 'professorfabiosilveira@gmail.com', 0),
(65, 'fernanda maria almeida floriano', 0, 3, 13, 3, 2, 'fer_uk@yahoo.co.uk', 0),
(66, 'francicleide gonçalves de sousa', 0, 3, 13, 3, 3, 'francicleide.sousa@ifpb.edu.br', 0),
(67, 'francilda araujo inacio', 0, 3, 13, 3, 4, 'araujo.francilda@gmail.com', 0),
(68, 'francisco dantas nobre neto', 0, 3, 13, 3, 3, '', 0),
(69, 'francisco de assis da silveira gonzaga', 0, 3, 17, 3, 3, 'franciscoagonzaga@hotmail.com', 0),
(70, 'francisco de assis souza', 0, 3, 19, 3, 3, 'franciscosouza.ifpb@gamil.com', 0),
(71, 'francisco henrique duarte filho', 0, 3, 13, 3, 1, 'henrique.bj@ibest.com.br', 0),
(72, 'francisco jucivônio félix de sousa', 0, 3, 13, 3, 2, 'juc.fe@uol.com.br', 0),
(73, 'frankslale fabian diniz de andrade meira', 0, 3, 21, 3, 4, 'frank_meira@yahoo.com.br', 0),
(74, 'geane vidal de negreiros lima', 0, 3, 12, 3, 1, 'gvnegreiros@gmail.com', 0),
(75, 'george sobral silveira', 0, 3, 13, 3, 4, 'georgesilveira@gmail.com', 0),
(76, 'georgina karla de freitas serres', 0, 3, 19, 3, 4, 'geomaciel@gmail.com', 0),
(77, 'geraldo da mota dantas', 0, 3, 13, 3, 3, '', 0),
(78, 'germana correia de oliveira', 0, 3, 13, 3, 3, 'germanacorreia@gmail.com', 0),
(79, 'germana silva de oliveira', 0, 3, 13, 3, 3, 'germanasom@hotmail.com', 0),
(80, 'gilmara de melo ferreira', 0, 3, 13, 3, 1, '', 0),
(81, 'gilmara gomes meira', 0, 3, 13, 3, 3, 'gilmarameira@yahoo.com.br', 0),
(82, 'gisele caldas de araujo cunha', 0, 3, 17, 3, 2, '', 0),
(83, 'glayds richeles araujo veiga', 0, 3, 13, 3, 1, 'glaydshistoria@hotmail.com', 0),
(84, 'helder gustavo pequeno dos reis', 0, 3, 13, 3, 2, 'professorhelder@ig.com.br', 0),
(85, 'henrique do nascimento cunha', 0, 3, 13, 3, 3, 'herinque.cunha@ifpb.edu.br', 0),
(86, 'herllange chaves de brito', 0, 3, 13, 3, 1, 'herllange@ig.com.br', 0),
(87, 'iana daya cavalcante facundo passos', 0, 3, 22, 3, 3, 'daya@terra.com.br', 0),
(88, 'ianna maria sodre ferreira de sousa', 0, 3, 19, 3, 3, 'ianna@ifpb.edu.br', 0),
(89, 'igor barbosa da costa', 0, 3, 13, 3, 3, 'igor.costa@ifpb.edu.br', 0),
(90, 'iliana de oliveira guimarães', 0, 3, 18, 3, 3, 'ilianaguimaraes@hotmail.com', 0),
(91, 'iremar alves madureira', 0, 3, 13, 3, 1, 'iremar@bol.com.br', 0),
(92, 'isa fernandes de souza', 0, 3, 13, 3, 3, 'souza-isa@hotmail.com', 0),
(93, 'ivanise souto maior', 0, 3, 17, 3, 1, 'ismaior@ig.com.br', 0),
(94, 'jair stefanini pereira de ataide', 0, 3, 19, 3, 1, '', 0),
(95, 'jairo carlos de oliveira quintans', 0, 3, 13, 3, 2, 'jairocarlosq@hotmail.com', 0),
(96, 'jamylle rebouças ouverney king', 0, 3, 17, 3, 3, 'katze@terra.com.br', 0),
(97, 'jayson dagoberto dos santos carneiro', 0, 3, 13, 3, 1, 'jaysonguimaraes@hotmail.com', 0),
(98, 'jean luis gomes de medeiros', 0, 3, 13, 3, 3, 'jeandemedeiros@gmail.com', 0),
(99, 'jeronimo silva rocha', 0, 3, 19, 3, 4, 'jeronimo.rocha@ifpb.edu.br', 0),
(100, 'joab dos santos silva', 0, 3, 13, 3, 3, 'joab_professor@hotmail.com', 0),
(101, 'joao edson rufino', 0, 3, 12, 3, 3, 'joaorufinojoao@ig.com.br', 0),
(102, 'joão galdino de lucena neto', 101019, 3, 13, 3, 1, '', 0),
(103, 'joão paulo da silva', 0, 3, 13, 3, 3, 'jps.silva@yahoo.com.br', 0),
(104, 'jonathas jerônimo barbosa', 0, 3, 13, 3, 1, 'jonathasbarbosa@gmail.com', 0),
(105, 'jorge luis de gois gonçalves', 0, 3, 17, 3, 4, 'jlggoncalves@yahoo.com.br', 0),
(106, 'jose adeildo de lima filho', 0, 3, 13, 3, 3, 'adeildobiologia@gmail.com', 0),
(107, 'jose alves do nascimento neto', 0, 3, 19, 3, 4, 'josealvesnneto@gmail.com', 0),
(108, 'jose antonio candido borges da silva', 0, 3, 19, 3, 3, 'candido@ee.ufcg.edu.br', 0),
(109, 'jose de arimateia almeida e silva', 0, 3, 21, 3, 1, 'arimateia.allmeida@gmail.com', 0),
(110, 'jose do nascimento junior', 0, 3, 12, 3, 2, 'jjrbol@yahoo.com.br', 0),
(111, 'jose gilson de lucena gomes', 0, 3, 19, 3, 3, 'gilson.lucena@oi.com.br', 0),
(112, 'jose thiago holanda de alcantara cabral', 0, 3, 19, 3, 3, 'jtholanda@gmail.com', 0),
(113, 'josikleio da costa silva', 0, 3, 13, 3, 3, 'josikleio@gmail.com', 0),
(114, 'josé luiz cavalcante', 0, 3, 13, 3, 1, 'luiz-x@hotmail.com', 0),
(115, 'joyce kelly barros henrique', 0, 3, 13, 3, 1, 'joycekellybarros@yahoo.com.br', 0),
(116, 'katia davi brito', 0, 3, 13, 3, 4, 'katiadout@hotmail.com', 0),
(117, 'katyusco de farias santos', 0, 3, 19, 3, 3, 'katyusco@gmail.com', 0),
(118, 'kennedy flavio meira de lucena', 0, 3, 13, 3, 4, 'kennedyflavio@yahoo.com.br', 0),
(119, 'kleber da fonseca furtado', 0, 3, 17, 3, 1, 'kleberffurtado@uol.com.br', 0),
(120, 'lucyana sobral de souza', 0, 3, 13, 3, 3, 'lsobralsouza@bol.com.br', 0),
(121, 'lugero batista melo', 0, 3, 13, 3, 1, '', 0),
(122, 'luis havelange soares', 0, 3, 13, 3, 3, 'luis.soares@ifpb.edu.br', 0),
(123, 'magna celi tavares bispo', 0, 3, 22, 3, 3, 'magnabispo@gmail.com', 0),
(124, 'manoel mendes de aragao neto', 0, 3, 13, 3, 1, 'aragaomanoel@yahoo.com.br', 0),
(125, 'marcelo jos siqueira coutinho almeida', 0, 3, 22, 3, 3, 'marcelo@coinfo.cefetpb.edu.br', 0),
(126, 'marcelo portela sousa', 0, 3, 12, 3, 4, 'marporsou@gmail.com', 0),
(127, 'marcelo rodrigues do nascimento', 0, 3, 17, 3, 4, 'marceloquimica@gmail.com', 0),
(128, 'marcia de albuquerque pereira', 0, 3, 13, 3, 1, 'marseaa@hotmail.com', 0),
(129, 'marcia gardenia lustosa pires', 0, 3, 13, 3, 4, 'gardenialustosa@yahoo.com.br', 0),
(130, 'marcia maria costa gomes', 0, 3, 13, 3, 3, 'mmarciagomes@gmail.com', 0),
(131, 'marcia rafaela arnold', 0, 3, 13, 3, 1, '', 0),
(132, 'marcilio diniz da silva', 0, 3, 13, 3, 3, 'marciliodiniz.s@gmail.com', 0),
(133, 'marco tullio lima duarte', 0, 3, 13, 3, 3, 'duartetullio@hotmail.com', 0),
(134, 'marconi jose da camara pires', 0, 3, 13, 3, 3, 'marconi.pires@ifpb.edu.br / mjcpires@yahoo.com.br', 0),
(135, 'marcos mesquita da silva', 0, 3, 18, 3, 1, 'marcos_m_silva@yahoo.com.br', 0),
(136, 'marcos severino de lima', 0, 3, 21, 3, 1, 'marcosufcg@oi.com.br', 0),
(137, 'marcos vinicius c. m. de andrade', 0, 3, 19, 3, 2, 'marcosvcma@ifpb.edu.br', 0),
(138, 'maria angélica ramos da silva', 0, 3, 13, 3, 4, 'angel-jp@hotmail.com', 0),
(139, 'maria auxiliadora de brito lira dal monte', 0, 3, 17, 3, 4, 'britolira@hotmail.com', 0),
(140, 'maria célia ribeiro da silva', 0, 3, 13, 3, 3, 'celia.ribeiro@ifpb.edu.br', 0),
(141, 'maria claudia rodrigues brandao', 0, 3, 12, 3, 1, 'claudiabrandao.quimica@gmail.com', 0),
(142, 'maria do socorro marreiro de sousa', 0, 3, 13, 3, 1, '', 0),
(143, 'maria juliana leopoldino vilar', 0, 3, 13, 3, 1, 'julianalspb@yahoo.com.br', 0),
(144, 'marta clia f. bezerra', 0, 3, 19, 3, 4, 'martaceliafeitosa@yahoo.com.br', 0),
(145, 'mary delane gomes da costa', 0, 3, 17, 3, 1, '', 0),
(146, 'mary karlla araujo guimaraes', 0, 3, 19, 3, 3, 'karllacg@gmail.com', 0),
(147, 'mauricio rodrigues pereira', 0, 3, 17, 3, 3, 'fmimauriciorodriguespereira@gmail.com', 0),
(148, 'maxwell aragão marques nogueira', 0, 3, 13, 3, 1, 'rx_maxwell@yahoo.com.br', 0),
(149, 'mellyne palmeira medeiros', 0, 3, 21, 3, 3, 'melpalmeira@hotmail.com', 0),
(150, 'michell leonard duarte de lima tolentino', 0, 3, 13, 3, 1, 'michelltolentino@gmail.com', 0),
(151, 'michelle dayse marques de lima', 0, 3, 12, 3, 3, 'michelledml@gmail.com', 0),
(152, 'moacy pereira da silva', 0, 3, 13, 3, 3, 'moacy.silva@ifpb.edu.br', 0),
(153, 'newmark heiner da cunha carvalho', 0, 3, 19, 3, 3, 'newmark@fiepb.org.br', 0),
(154, 'neyr muniz barreto', 0, 3, 13, 3, 2, 'neyrmb@hotmail.com', 0),
(155, 'orlando batista de almeida', 0, 3, 13, 3, 4, 'proforlandoba@yahoo.com.br', 0),
(156, 'paulo de tarso firmino junior', 0, 3, 13, 3, 3, 'paulodt@gmail.com', 0),
(157, 'paulo jose carneiro perfeito', 0, 3, 13, 3, 3, 'pjperfeito@yahoo.com.br', 0),
(158, 'paulo ribeiro lins junior', 0, 3, 19, 3, 3, 'paulo.ribeiro.lins.jr@gmail.com', 0),
(159, 'pedro paulo de andrade silva', 0, 3, 13, 3, 2, 'pedropaulopb@yahoo.com.br', 0),
(160, 'petrônio carlos bezerra', 0, 3, 19, 3, 3, 'petroniocg@ifpb.edu.br', 0),
(161, 'rachel freire torrez de souza', 0, 3, 13, 3, 1, 'racheltorrez@yahoo.com.br', 0),
(162, 'ramide augusto sales dantas', 0, 3, 22, 3, 1, 'ramide@gmail.com', 0),
(163, 'ranieri de araujo pereira', 0, 3, 17, 3, 1, 'ranieri.engminas@gmail.com', 0),
(164, 'raphael alexander rosa romero', 0, 3, 17, 3, 1, '', 0),
(165, 'rhavy maia guedes', 1752152, 3, 19, 3, 3, 'rhavyron@gmail.com', 1),
(166, 'rodrigo rodrigues da silva', 0, 3, 13, 3, 1, 'rrfisica@hotmail.com', 0),
(167, 'romulo alexandre silva', 0, 3, 23, 3, 3, 'romulo_celia@hotmail.com', 0),
(168, 'romulo sousa torres', 0, 3, 22, 3, 3, 'rstorresk@gmail.com', 0),
(169, 'ronaebson de carvalho ferreira', 0, 3, 13, 3, 3, 'ronaebson@uol.com.br', 0),
(170, 'ronaldo araujo alves', 0, 3, 22, 3, 3, 'araujo.ronaldo@gmail.com', 0),
(171, 'ronnie elder da cunha', 0, 3, 17, 3, 2, 'ronnie.cunha@gmail.com', 0),
(172, 'ronnylson cesar de oliveira fonseca', 0, 3, 13, 3, 2, 'ronnylsoncesar@gmail.com', 0),
(173, 'rosa lucia vieira souza', 0, 3, 12, 3, 1, 'rosa.lucia@uol.com.br', 0),
(174, 'sabrina alves de freitas', 0, 3, 13, 3, 3, 'sabrinaaf@gmail.com', 0),
(175, 'salomão pereira de almeida', 0, 3, 13, 3, 3, 'salomaomatematica@yahoo.com.br', 0),
(176, 'samuel alves da silva', 0, 3, 19, 3, 3, '', 0),
(177, 'saskia lavyne barbosa da silva', 0, 3, 13, 3, 3, 'slavyne@yahoo.com.br', 0),
(178, 'sibria maria souto dos santos farias', 0, 3, 13, 3, 3, 'siberia.santos@gmail.com', 0),
(179, 'stenio farias davila lins', 0, 3, 13, 3, 1, 'steniopb@yahoo.com.br', 0),
(180, 'stephanie ingrid souza barboza', 0, 3, 13, 3, 3, 'stephanieisb@gmail.com', 0),
(181, 'sulen silva figueiredo', 0, 3, 21, 3, 3, 'suelensfigueiredo@gmail.com', 0),
(182, 'tarcisio oliveira de moraes júnior', 0, 3, 13, 3, 3, 'tarcisiocz@gmail.com', 0),
(183, 'thalita cunha motta', 0, 3, 20, 3, 3, 'thalitacmotta@gmail.com', 0),
(184, 'thassio nóbrega gomes', 0, 3, 18, 3, 1, 'thassiong@gmail.com', 0),
(185, 'tulio cesar soares dos santos andre', 0, 3, 17, 3, 4, 'tcssandre@hotmail.com', 0),
(186, 'uelpis luiz tenorio da silva', 0, 3, 13, 3, 1, 'uelpisluiz@yahoo.com.br', 0),
(187, 'vanlex gomes galdino', 0, 3, 13, 3, 2, 'vanlexgaldino@bol.com.br', 0),
(188, 'victor moiss de araújo medeiros', 0, 3, 13, 3, 3, 'victor.medeiros@ifpb.edu.br', 0),
(189, 'wandenberg bismarck colaço lima', 0, 3, 17, 3, 3, 'wandercg@oi.com.br', 0),
(190, 'yuri saladino souto maior nunes', 0, 3, 12, 3, 3, 'yurisaladino@yahoo.com.br', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_programa_institucional`
--

CREATE TABLE IF NOT EXISTS `tb_programa_institucional` (
  `id_programa_institucional` int(11) NOT NULL AUTO_INCREMENT,
  `nm_programa_institucional` varchar(255) NOT NULL,
  `nm_sigla` varchar(32) NOT NULL,
  `tipo_programa_institucional_id` int(11) NOT NULL,
  `pessoa_id` int(11) NOT NULL,
  `instituicao_id` int(11) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_programa_institucional`),
  KEY `fk_programa_institucional_instituicao` (`instituicao_id`),
  KEY `fk_pessoa_programa_institucional` (`pessoa_id`),
  KEY `fk_programa_institucional_tipo_programa_institucional` (`tipo_programa_institucional_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `tb_programa_institucional`
--

INSERT INTO `tb_programa_institucional` (`id_programa_institucional`, `nm_programa_institucional`, `nm_sigla`, `tipo_programa_institucional_id`, `pessoa_id`, `instituicao_id`, `dt_registro`) VALUES
(1, 'Programa Institucional de Bolsas de Iniciação Científica para o Ensino Médio', 'PIBIC-EM', 1, 1, 1, '2014-12-26 14:38:26'),
(2, 'Programa Institucional de Bolsas de Extensão', 'PROBEXT', 2, 1, 1, '2014-12-26 14:38:26'),
(3, 'Programa Institucional de Bolsas de Iniciação em Desenvolvimento Tecnológico e Inovação', 'PIBITI', 1, 1, 3, '2015-07-14 20:24:29');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_projeto`
--

CREATE TABLE IF NOT EXISTS `tb_projeto` (
  `id_projeto` int(11) NOT NULL AUTO_INCREMENT,
  `nm_projeto` varchar(255) NOT NULL,
  `nm_resumo` varchar(300) NOT NULL,
  `dt_inicio_projeto` date NOT NULL,
  `dt_fim_projeto` date NOT NULL,
  `nr_processo` varchar(21) DEFAULT NULL,
  `vl_orcamento` double DEFAULT NULL,
  `edital_id` int(11) NOT NULL,
  `local_id` int(11) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_projeto`),
  UNIQUE KEY `nm_projeto` (`nm_projeto`),
  KEY `fk_projeto_edital` (`edital_id`),
  KEY `fk_projeto_local` (`local_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_recurso_instituicao_financiadora`
--

CREATE TABLE IF NOT EXISTS `tb_recurso_instituicao_financiadora` (
  `id_recurso_if` int(11) NOT NULL AUTO_INCREMENT,
  `instituicao_financiadora_id` int(11) NOT NULL,
  `vl_orcamento` double NOT NULL,
  `dt_validade_inicial` date NOT NULL,
  `dt_validade_final` date NOT NULL,
  `fl_recurso_valido` tinyint(1) NOT NULL DEFAULT '1',
  `pessoa_id` int(11) NOT NULL COMMENT 'Márcia está como padrão, pois, sem isso, essa modificação resulta em erro',
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_recurso_if`),
  KEY `fk_recurso_instituicao_financiadora` (`instituicao_financiadora_id`),
  KEY `fk_recurso_if_pessoa` (`pessoa_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `tb_recurso_instituicao_financiadora`
--

INSERT INTO `tb_recurso_instituicao_financiadora` (`id_recurso_if`, `instituicao_financiadora_id`, `vl_orcamento`, `dt_validade_inicial`, `dt_validade_final`, `fl_recurso_valido`, `pessoa_id`, `dt_registro`) VALUES
(1, 1, 1000, '2015-01-05', '2015-12-30', 1, 1, '2015-03-13 02:24:44'),
(2, 1, 1000, '2015-04-23', '2015-04-30', 1, 1, '2015-04-23 12:49:58'),
(3, 3, 1000, '2015-07-14', '2015-07-31', 1, 1, '2015-07-14 20:49:10');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_recurso_programa_institucional`
--

CREATE TABLE IF NOT EXISTS `tb_recurso_programa_institucional` (
  `id_recurso_pi` int(11) NOT NULL AUTO_INCREMENT,
  `programa_institucional_id` int(11) NOT NULL,
  `vl_orcamento` double NOT NULL,
  `dt_validade_inicial` date NOT NULL,
  `dt_validade_final` date NOT NULL,
  `fl_recurso_valido` tinyint(1) NOT NULL DEFAULT '1',
  `recurso_instituicao_financiadora_id` int(11) NOT NULL,
  `pessoa_id` int(11) NOT NULL DEFAULT '1' COMMENT 'Márcia está como padrão, pois, sem isso, essa modificação resulta em erro',
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_recurso_pi`),
  KEY `fk_recurso_programa_institucional` (`programa_institucional_id`),
  KEY `fk_recurso_pi_if` (`recurso_instituicao_financiadora_id`),
  KEY `fk_recurso_pi_pessoa` (`pessoa_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `tb_recurso_programa_institucional`
--

INSERT INTO `tb_recurso_programa_institucional` (`id_recurso_pi`, `programa_institucional_id`, `vl_orcamento`, `dt_validade_inicial`, `dt_validade_final`, `fl_recurso_valido`, `recurso_instituicao_financiadora_id`, `pessoa_id`, `dt_registro`) VALUES
(1, 1, 500, '2015-07-06', '2015-12-25', 1, 1, 1, '2015-03-26 23:35:46');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_sequencia_nr_edital`
--

CREATE TABLE IF NOT EXISTS `tb_sequencia_nr_edital` (
  `nr_ano` smallint(4) unsigned NOT NULL,
  `nr_sequencia_edital` smallint(4) unsigned NOT NULL,
  PRIMARY KEY (`nr_ano`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Reter valor do campo número da última inserção de Edital.';

--
-- Extraindo dados da tabela `tb_sequencia_nr_edital`
--

INSERT INTO `tb_sequencia_nr_edital` (`nr_ano`, `nr_sequencia_edital`) VALUES
(2014, 12),
(2015, 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_servidor`
--

CREATE TABLE IF NOT EXISTS `tb_servidor` (
  `pessoa_id` int(11) NOT NULL,
  `id_titulacao` int(11) NOT NULL,
  `id_departamento` int(11) NOT NULL,
  `cargo_servidor_id` int(11) NOT NULL,
  PRIMARY KEY (`pessoa_id`),
  KEY `fk_servidor_cargo_servidor` (`cargo_servidor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tb_servidor`
--

INSERT INTO `tb_servidor` (`pessoa_id`, `id_titulacao`, `id_departamento`, `cargo_servidor_id`) VALUES
(1, 3, 6, 1),
(3, 3, 6, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_sub_area`
--

CREATE TABLE IF NOT EXISTS `tb_sub_area` (
  `id_sub_area` int(11) NOT NULL AUTO_INCREMENT,
  `area_id` int(11) NOT NULL,
  `cd_sub_area` varchar(11) NOT NULL,
  `nm_sub_area` varchar(100) NOT NULL,
  PRIMARY KEY (`id_sub_area`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1214 ;

--
-- Extraindo dados da tabela `tb_sub_area`
--

INSERT INTO `tb_sub_area` (`id_sub_area`, `area_id`, `cd_sub_area`, `nm_sub_area`) VALUES
(1, 1, '10101004', 'ALGEBRA'),
(2, 1, '10101012', 'CONJUNTOS'),
(3, 1, '10101020', 'LÓGICA MATEMÁTICA'),
(4, 1, '10101039', 'TEORIA DOS NÚMEROS'),
(5, 1, '10101047', 'GRUPO DE ÁLGEBRA NÃO-COMUTATIVA'),
(6, 1, '10101055', 'ÁLGEBRA COMUTATIVA'),
(7, 1, '10101063', 'GEOMETRIA ALGÉBRICA'),
(8, 1, '10102000', 'ANÁLISE'),
(9, 1, '10102019', 'ANÁLISE COMPLEXA'),
(10, 1, '10102027', 'ANÁLISE FUNCIONAL'),
(11, 1, '10102035', 'ANÁLISE FUNCIONAL NÃO-LINEAR'),
(12, 1, '10102043', 'EQUAÇÕES DIFERENCIAIS ORDINÁRIAS'),
(13, 1, '10102051', 'EQUAÇÕES DIFERENCIAIS PARCIAIS'),
(14, 1, '10102060', 'EQUAÇÕES DIFERENCIAIS FUNCIONAIS'),
(15, 1, '10103007', 'GEOMETRIA E TOPOLOGIA'),
(16, 1, '10103015', 'GEOMETRIA DIFERÊNCIAL'),
(17, 1, '10103023', 'TOPOLOGIA ALGÉBRICA'),
(18, 1, '10103031', 'TOPOLOGIA DAS VARIEDADES'),
(19, 1, '10103040', 'SISTEMAS DINÂMICOS'),
(20, 1, '10103058', 'TEORIA DAS SINGULARIDADES E TEORIA DAS CATÁSTROFES'),
(21, 1, '10103066', 'TEORIA DAS FOLHEAÇÕES'),
(22, 1, '10104003', 'MATEMÁTICA APLICADA'),
(23, 1, '10104011', 'FÍSICA MATEMÁTICA'),
(24, 1, '10104020', 'ANÁLISE NUMÉRICA'),
(25, 1, '10104038', 'MATEMÁTICA DISCRETA E COMBINATÓRIA'),
(26, 2, '10201017', 'TEORIA GERAL E FUNDAMENTOS DA PROBABILIDADE'),
(27, 2, '10201025', 'TEORIA GERAL E PROCESSOS ESTOCÁSTICOS'),
(28, 2, '10201033', 'TEOREMAS DE LIMITE'),
(29, 2, '10201041', 'PROCESSOS MARKOVIANOS'),
(30, 2, '10201050', 'ANÁLISE ESTOCÁSTICA'),
(31, 2, '10201068', 'PROCESSOS ESTOCÁSTICOS ESPECIAIS'),
(32, 2, '10202005', 'ESTATÍSTICA'),
(33, 2, '10202013', 'FUNDAMENTOS DA ESTATÍSTICA'),
(34, 2, '10202021', 'INFERÊNCIA PARAMÉTRICA'),
(35, 2, '10202030', 'INFERÊNCIA NÃO-PARAMÉTRICA'),
(36, 2, '10202048', 'INFERÊNCIA EM PROCESSOS ESTOCÁSTICOS'),
(37, 2, '10202056', 'ANÁLISE MULTIVARIADA'),
(38, 2, '10202064', 'REGRESSÃO E CORRELAÇÃO'),
(39, 2, '10202072', 'PLANEJAMENTO DE EXPERIMENTOS'),
(40, 2, '10202080', 'ANÁLISE DE DADOS'),
(41, 2, '10203001', 'PROBABILIDADE E ESTATÍSTICA APLICADAS'),
(42, 3, '10301003', 'TEORIA DA COMPUTAÇÃO'),
(43, 3, '10301011', 'COMPUTABILIDADE E MODELOS DE COMPUTAÇÃO'),
(44, 3, '10301020', 'LINGUAGEM FORMAIS E AUTÔMATOS'),
(45, 3, '10301038', 'ANÁLISE DE ALGORÍTMOS E COMPLEXIDADE DE COMPUTAÇÃO'),
(46, 3, '10301046', 'LÓGICAS E SEMÂNTICA DE PROGRAMAS'),
(47, 3, '10302000', 'MATEMÁTICA DA COMPUTAÇÃO'),
(48, 3, '10302018', 'MATEMÁTICA SIMBÓLICA'),
(49, 3, '10302026', 'MODELOS ANALÍTICOS E DE SIMULAÇÃO'),
(50, 3, '10303006', 'METODOLOGIA E TÉCNICAS DA COMPUTAÇÃO'),
(51, 3, '10303014', 'LINGUAGENS DE PROGRAMAÇÃO'),
(52, 3, '10303022', 'ENGENHARIA DE SOFTWARE'),
(53, 3, '10303030', 'BANCO DE DADOS'),
(54, 3, '10303049', 'SISTEMAS DE INFORMAÇÃO'),
(55, 3, '10303057', 'PROCESSAMENTO GRÁFICO (GRAPHICS)'),
(56, 3, '10304002', 'SISTEMA DE COMPUTAÇÃO'),
(57, 3, '10304010', 'HARDWARE'),
(58, 3, '10304029', 'ARQUITETURA DE SISTEMAS DE COMPUTAÇÃO'),
(59, 3, '10304037', 'SOFTWARE BÁSICO'),
(60, 3, '10304045', 'TELEINFORMÁTICA'),
(61, 4, '10401008', 'ASTRONOMIA DE POSIÇÃO E MECÂNICA CELESTE'),
(62, 4, '10401016', 'ASTRONOMIA FUNDAMENTAL'),
(63, 4, '10401024', 'ASTRONOMIA DINÂMICA'),
(64, 4, '10402004', 'ASTROFÍSICA ESTELAR'),
(65, 4, '10403000', 'ASTROFÍSICA DO MEIO INTERESTELAR'),
(66, 4, '10403019', 'MEIO INTERESTELAR'),
(67, 4, '10403027', 'NEBULOSA'),
(68, 4, '10404007', 'ASTROFÍSICA EXTRAGALÁTICA'),
(69, 4, '10404015', 'GALÁXIAS'),
(70, 4, '10404023', 'AGLOMERADOS DE GALÁXIAS'),
(71, 4, '10404031', 'QUASARES'),
(72, 4, '10404040', 'COSMOLOGIA'),
(73, 4, '10405003', 'ASTROFÍSICA DO SISTEMA SOLAR'),
(74, 4, '10405011', 'FÍSICA SOLAR'),
(75, 4, '10405020', 'MOVIMENTO DA TERRA'),
(76, 4, '10405038', 'SISTEMA PLANETÁRIO'),
(77, 4, '10406000', 'INSTRUMENTAÇÃO ASTRONÔMICA'),
(78, 4, '10406018', 'ASTRONOMIA ÓTICA'),
(79, 4, '10406026', 'RADIOASTRONOMIA'),
(80, 4, '10406034', 'ASTRONOMIA ESPACIAL'),
(81, 4, '10406042', 'PROCESSAMENTO DE DADOS ASTRONÔMICOS'),
(82, 5, '10501002', 'FÍSICA GERAL'),
(83, 5, '10501010', 'MÉTODOS MATEMÁTICOS DA FÍSICA'),
(84, 5, '10501029', 'FÍSICA CLÁSSICA E FÍSICA QUÂNTICA; MECÂNICA E CAMPOS'),
(85, 5, '10501037', 'RELATIVIDADE E GRAVITAÇÃO'),
(86, 5, '10501045', 'FÍSICA ESTATÍSTICA E TERMODINÂMICA'),
(87, 5, '10501053', 'METROLOGIA, TECN. GER. DE LAB. E SIST. DE INSTRUMENTAÇÃO'),
(88, 5, '10501061', 'INSTRUMENTAÇÃO ESPECÍFICA DE USO GERAL EM FÍSICA'),
(89, 5, '10502009', 'ÁREAS CLÁSSICAS DE FENOMENOLOGIA E SUAS APLICAÇÕES'),
(90, 5, '10502017', 'ELETRICIDADE E MAGNETISMO; CAMPOS E PARTÍCULAS CARREGADAS'),
(91, 5, '10502025', 'ÓTICA'),
(92, 5, '10502033', 'ACÚSTICA'),
(93, 5, '10502041', 'TRANSFERÊNCIA DE CALOR; PROCESSOS TÉRMICOS E TERMODINÂMICOS'),
(94, 5, '10502050', 'MECÂNICA, ELASTICIDADE E REOLOGIA'),
(95, 5, '10502068', 'DINÂMICA DOS FLUIDOS'),
(96, 5, '10503005', 'FÍSICA DAS PARTÍCULAS ELEMENTARES E CAMPOS'),
(97, 5, '10503013', 'TEORIA GERAL DE PARTÍCULAS E CAMPOS'),
(98, 5, '10503021', 'TEOR.ESP.E MOD.DE INTERAÇÃO; SIST.DE PARTÍCULAS; R.CÓSMICOS'),
(99, 5, '10503030', 'REAÇÕES ESPECÍFICAS E FENOMIOLOGIA DE PARTÍCULAS'),
(100, 5, '10503048', 'PROPRIEDADES DE PARTÍCULAS ESPECÍFICAS E RESSONÂNCIAS'),
(101, 5, '10504001', 'FÍSICA NUCLEAR'),
(102, 5, '10504010', 'ESTRUTURA NUCLEAR'),
(103, 5, '10504028', 'DESINTEGRAÇÃO NUCLEAR E RADIOATIVIDADE'),
(104, 5, '10504036', 'REAÇÕES NUCLEARES E ESPALHAMENTO GERAL'),
(105, 5, '10504044', 'REAÇÕES NUCLEARES E ESPALHAMENTO (REAÇÕES ESPECÍFICAS)'),
(106, 5, '10504052', 'PROPRIEDADES DE NÚCLEOS ESPECÍFICOS'),
(107, 5, '10504060', 'MET.EXPER.E INSTRUMENT.PARA PART.ELEMENT.E FÍSICA NUCLEAR'),
(108, 5, '10505008', 'FÍSICA ATÔMICA E MOLECULAR'),
(109, 5, '10505016', 'ESTRUTURA ELETRÔNICA DE ÁTOMOS E MOLÉCULAS; TEORIA'),
(110, 5, '10505024', 'ESPECTROS ATÔMICOS E INTEGRAÇÃO DE FÓTONS'),
(111, 5, '10505032', 'ESPECTROS MOLECUL. E INTERAÇÕES DE FÓTONS COM MOLÉCULAS'),
(112, 5, '10505040', 'PROCESSOS DE COLISÃO E INTERAÇÕES DE ÁTOMOS E MOLÉCULAS'),
(113, 5, '10505059', 'INF.SOB.ATOM.E MOL.OBIT.EXPERIMENTALMENTE; INST.E TÉCNICAS'),
(114, 5, '10505067', 'ESTUDOS DE ÁTOMOS E MOLÉCULAS ESPECIAIS'),
(115, 5, '10506004', 'FÍSICA DOS FLÚIDOS, FÍSICA DE PLASMAS E DESCARGAS ELÉTRICAS'),
(116, 5, '10506012', 'CINÉTICA E TEOR.DE TRANSP.DE FLÚIDOS; PROPRIED.FIS.DE GASES'),
(117, 5, '10506020', 'FÍSICA DE PLASMAS E DESCARGAS ELÉTRICAS'),
(118, 5, '10507000', 'FÍSICA DA MATÉRIA CONDENSADA'),
(119, 5, '10507019', 'ESTRUTURA DE LÍQUIDOS E SÓLIDOS; CRISTALOGRAFIA'),
(120, 5, '10507027', 'PROPRIEDADES MECÂNICAS E ACÚSTICAS DA MATÉRIA CONDENSADA'),
(121, 5, '10507035', 'DINÂMICA DA REDE E ESTATÍSTICA DE CRISTAIS'),
(122, 5, '10507043', 'EQUAÇÃO DE ESTADO, EQUILIB. DE FASES E TRANSIÇÕES DE FASES'),
(123, 5, '10507051', 'PROPRIEDADES TÉRMICAS DA MATÉRIA CONDENSADA'),
(124, 5, '10507060', 'PROPRIEDADES DE TRANSP.DE MATÉRIA COND. (NÃO ELETRÔNICAS)'),
(125, 5, '10507078', 'CAMPOS QUÂNTICOS E SÓLIDOS, HÉLIO, LÍQUIDO, SÓLIDO'),
(126, 5, '10507086', 'SUPERFÍCIES E INTERFACES; PELÍCULAS E FILAMENTOS'),
(127, 5, '10507094', 'ESTADOS ELETRÔNICOS'),
(128, 5, '10507108', 'TRANSP.ELETR.E PROPR.ELET.DE SUPERFÍCIES; INTERF.E PELÍCULAS'),
(129, 5, '10507116', 'ESTRUT.ELETR.E PROPR.ELET.DE SUPERFÍCIES; INTERF.E PELÍCULAS'),
(130, 5, '10507124', 'SUPERCONDUTIVIDADE'),
(131, 5, '10507132', 'MATERIAIS MAGNÉTICOS E PROPRIEDADES MAGNÉTICAS'),
(132, 5, '10507140', 'RESS.MAGN. REL.MAT.COND.; EFEIT.MOSBAUER; CORR.ANG.PERTUBADA'),
(133, 5, '10507159', 'MATERIAIS DIELÉTRICOS E PROPRIEDADES DIELÉTRICAS'),
(134, 5, '10507167', 'PROP.OTIC.E ESPEC.MATR.COND.; OUTRAS INTER.MAT.COM RAD.PART.'),
(135, 5, '10507175', 'EMISSÃO ELETRON.E IÔNICA POR LIQ.E SÓLIDOS; FENOM.DE IMPACTO'),
(136, 6, '10601007', 'QUÍMICA ORGÂNICA'),
(137, 6, '10601015', 'ESTRUTURA, CONFORMAÇÃO E ESTEREOQUÍMICA'),
(138, 6, '10601023', 'SÍNTESE ORGÂNICA'),
(139, 6, '10601031', 'FÍSICO-QUÍMICA ORGÂNICA'),
(140, 6, '10601040', 'FOTOQUÍMICA ORGÂNICA'),
(141, 6, '10601058', 'QUÍMICA DOS PRODUTOS NATURAIS'),
(142, 6, '10601066', 'EVOLUÇÃO, SISTEMÁTICA E ECOLOGIA QUÍMICA'),
(143, 6, '10601074', 'POLÍMEROS E COLÓIDES'),
(144, 6, '10602003', 'QUÍMICA INORGÂNICA'),
(145, 6, '10602011', 'CAMPOS DE COORDENAÇÃO'),
(146, 6, '10602020', 'NÃO-METAIS E SEUS COMPOSTOS'),
(147, 6, '10602038', 'COMPOSTOS ORGANO-METÁLICOS'),
(148, 6, '10602046', 'DETERMINAÇÃO DE ESTRUTURAS DE COMPOSTOS INORGÂNICOS'),
(149, 6, '10602054', 'FOTO-QUÍMICA INORGÂNICA'),
(150, 6, '10602062', 'FÍSICO QUÍMICA INORGÂNICA'),
(151, 6, '10602070', 'QUÍMICA BIO-INORGÂNICA'),
(152, 6, '10603000', 'FÍSICO-QUÍMICA'),
(153, 6, '10603018', 'CINÉTICA QUÍMICA E CATALISE'),
(154, 6, '10603026', 'ELETROQUÍMICA'),
(155, 6, '10603034', 'ESPECTROSCOPIA'),
(156, 6, '10603042', 'QUÍMICA DE INTERFACES'),
(157, 6, '10603050', 'QUÍMICA DO ESTADO CONDENSADO'),
(158, 6, '10603069', 'QUÍMICA NÚCLEAR E RADIOQUÍMICA'),
(159, 6, '10603077', 'QUÍMICA TEÓRICA'),
(160, 6, '10603085', 'TERMODINÂMICA QUÍMICA'),
(161, 6, '10604006', 'QUÍMICA ANALÍTICA'),
(162, 6, '10604014', 'SEPARAÇÃO'),
(163, 6, '10604022', 'MÉTODOS ÓTICOS DE ANÁLISE'),
(164, 6, '10604030', 'ELETROANALÍTICA'),
(165, 6, '10604049', 'GRAVIMETRIA'),
(166, 6, '10604057', 'TITIMETRIA'),
(167, 6, '10604065', 'INSTRUMENTAÇÃO ANALÍTICA'),
(168, 6, '10604073', 'ANÁLISE DE TRAÇOS E QUÍMICA AMBIENTAL'),
(169, 7, '10701001', 'GEOLOGIA'),
(170, 7, '10701010', 'MINERALOGIA'),
(171, 7, '10701028', 'PETROLOGIA'),
(172, 7, '10701036', 'GEOQUÍMICA'),
(173, 7, '10701044', 'GEOLOGIA REGIONAL'),
(174, 7, '10701052', 'GEOTECTÔNICA'),
(175, 7, '10701060', 'GEOCRONOLOGIA'),
(176, 7, '10701079', 'CARTOGRAFIA GEOLÓGICA'),
(177, 7, '10701087', 'METALOGENIA'),
(178, 7, '10701095', 'HIDROGEOLOGIA'),
(179, 7, '10701109', 'PROSPECÇÃO MINERAL'),
(180, 7, '10701117', 'SEDIMENTOLOGIA'),
(181, 7, '10701125', 'PALEONTOLOGIA ESTRATIGRÁFICA'),
(182, 7, '10701133', 'ESTRATIGRAFIA'),
(183, 7, '10701141', 'GEOLOGIA AMBIENTAL'),
(184, 7, '10702008', 'GEOFÍSICA'),
(185, 7, '10702016', 'GEOMAGNETISMO'),
(186, 7, '10702024', 'SISMOLOGIA'),
(187, 7, '10702032', 'GEOTERMIA E FLUXO TÉRMICO'),
(188, 7, '10702040', 'PROPRIEDADES FÍSICAS DAS ROCHAS'),
(189, 7, '10702059', 'GEOFÍSICA NUCLEAR'),
(190, 7, '10702067', 'SENSORIAMENTO REMOTO'),
(191, 7, '10702075', 'AERONOMIA'),
(192, 7, '10702083', 'DESENVOLVIMENTO DE INSTRUMENTAÇÃO GEOFÍSICA'),
(193, 7, '10702091', 'GEOFÍSICA APLICADA'),
(194, 7, '10702105', 'GRAVIMETRIA'),
(195, 7, '10703004', 'METEOROLOGIA'),
(196, 7, '10703012', 'METEOROLOGIA DINÂMICA'),
(197, 7, '10703020', 'METEOROLOGIA SINÓTICA'),
(198, 7, '10703039', 'METEOROLOGIA FÍSICA'),
(199, 7, '10703047', 'QUÍMICA DA ATMOSFERA'),
(200, 7, '10703055', 'INSTRUMENTAÇÃO METEOROLÓGICA'),
(201, 7, '10703063', 'CLIMATOLOGIA'),
(202, 7, '10703071', 'MICROMETEOROLOGIA'),
(203, 7, '10703080', 'SENSORIAMENTO REMOTO DA ATMOSFERA'),
(204, 7, '10703098', 'METEOROLOGIA APLICADA'),
(205, 7, '10704000', 'GEODÉSIA'),
(206, 7, '10704019', 'GEODÉSIA FÍSICA'),
(207, 7, '10704027', 'GEODÉSIA GEOMÉTRICA'),
(208, 7, '10704035', 'GEODÉSIA CELESTE'),
(209, 7, '10704043', 'FOTOGRAMETRIA'),
(210, 7, '10704051', 'CARTOGRAFIA BÁSICA'),
(211, 7, '10705007', 'GEOGRAFIA FÍSICA'),
(212, 7, '10705015', 'GEOMORFOLOGIA'),
(213, 7, '10705023', 'CLIMATOLOGIA GEOGRÁFICA'),
(214, 7, '10705031', 'PEDOLOGIA'),
(215, 7, '10705040', 'HIDROGEOGRAFIA'),
(216, 7, '10705058', 'GEOECOLOGIA'),
(217, 7, '10705066', 'FOTOGEOGRAFIA (FÍSICO-ECOLÓGICA)'),
(218, 7, '10705074', 'GEOCARTOGRAFIA'),
(219, 7, '10802002', 'OCEANOGRAFIA FÍSICA'),
(220, 7, '10802010', 'VARIÁVEIS FÍSICAS DA ÁGUA DO MAR'),
(221, 7, '10802029', 'MOVIMENTO DA ÁGUA DO MAR'),
(222, 7, '10802037', 'ORIGEM DAS MASSAS DE ÁGUA'),
(223, 7, '10802045', 'INTERAÇÃO DO OCEANO COM O LEITO DO MAR'),
(224, 7, '10802053', 'INTERAÇÃO DO OCEANO COM A ATMOSFERA'),
(225, 7, '10803009', 'OCEANOGRAFIA QUÍMICA'),
(226, 7, '10803017', 'PROPRIEDADES QUÍMICAS DA ÁGUA DO MAR'),
(227, 7, '10803025', 'INTER.QUÍM.-BIOL./GEOL.DAS SUBST. QUIM.DA ÁGUA DO MAR'),
(228, 7, '10804005', 'OCEANOGRAFIA GEOLÓGICA'),
(229, 7, '10804013', 'GEOMORFOLOGIA SUBMARINA'),
(230, 7, '10804021', 'SEDIMENTOLOGIA MARINHA'),
(231, 7, '10804030', 'GEOFÍSICA MARINHA'),
(232, 7, '10804048', 'GEOQUÍMICA MARINHA'),
(233, 9, '20201001', 'GENÉTICA QUANTITATIVA'),
(234, 9, '20202008', 'GENÉTICA MOLECULAR E DE MICROORGANISMOS'),
(235, 9, '20203004', 'GENÉTICA VEGETAL'),
(236, 9, '20204000', 'GENÉTICA ANIMAL'),
(237, 9, '20205007', 'GENÉTICA HUMANA E MÉDICA'),
(238, 9, '20206003', 'MUTAGENESE'),
(239, 10, '20601000', 'CITOLOGIA E BIOLOGIA CELULAR'),
(240, 10, '20602006', 'EMBRIOLOGIA'),
(241, 10, '20603002', 'HISTOLOGIA'),
(242, 10, '20604009', 'ANATOMIA'),
(243, 10, '20604017', 'ANATOMIA HUMANA'),
(244, 10, '20604025', 'ANATOMIA ANIMAL'),
(245, 11, '20701004', 'FISIOLOGIA GERAL'),
(246, 11, '20702000', 'FISIOLOGIA DOS ÓRGÃOS E SISTEMAS'),
(247, 11, '20702019', 'NEUROFISIOLOGIA'),
(248, 11, '20702027', 'FISIOLOGIA CARDIOVASCULAR'),
(249, 11, '20702035', 'FISIOLOGIA DA RESPIRAÇÃO'),
(250, 11, '20702043', 'FISIOLOGIA RENAL'),
(251, 11, '20702051', 'FISIOLOGIA ENDÓCRINA'),
(252, 11, '20702060', 'FISIOLOGIA DA DIGESTÃO'),
(253, 11, '20702078', 'CINESIOLOGIA'),
(254, 11, '20703007', 'FISIOLOGIA DO ESFORÇO'),
(255, 11, '20704003', 'FISIOLOGIA COMPARADA'),
(256, 12, '20801009', 'QUÍMICA DE MACROMOLÉCULAS'),
(257, 12, '20801017', 'PROTEÍNAS'),
(258, 12, '20801025', 'LIPÍDEOS'),
(259, 12, '20801033', 'GLICÍDEOS'),
(260, 12, '20802005', 'BIOQUÍMICA DOS MICROORGANISMOS'),
(261, 12, '20803001', 'METABOLISMO E BIOENERGÉTICA'),
(262, 12, '20804008', 'BIOLOGIA MOLECULAR'),
(263, 12, '20805004', 'ENZIMOLOGIA'),
(264, 13, '20901003', 'BIOFÍSICA MOLECULAR'),
(265, 13, '20902000', 'BIOFÍSICA CELULAR'),
(266, 13, '20903006', 'BIOFÍSICA DE PROCESSOS E SISTEMAS'),
(267, 13, '20904002', 'RADIOLOGIA E FOTOBIOLOGIA'),
(268, 14, '21001006', 'FARMACOLOGIA GERAL'),
(269, 14, '21001014', 'FARMACOCINÉTICA'),
(270, 14, '21001022', 'BIODISPONIBILIDADE'),
(271, 14, '21002002', 'FARMACOLOGIA AUTONÔMICA'),
(272, 14, '21003009', 'NEUROPSICOFARMACOLOGIA'),
(273, 14, '21004005', 'FARMACOLOGIA CARDIORENAL'),
(274, 14, '21005001', 'FARMACOLOGIA BIOQUIMICA E MOLECULAR'),
(275, 14, '21006008', 'ETNOFARMACOLOGIA'),
(276, 14, '21007004', 'TOXICOLOGIA'),
(277, 14, '21008000', 'FARMACOLOGIA CLÍNICA'),
(278, 15, '21101000', 'IMUNOQUÍMICA'),
(279, 15, '21102007', 'IMUNOLOGIA CELULAR'),
(280, 15, '21103003', 'IMUNOGENÉTICA'),
(281, 15, '21104000', 'IMUNOLOGIA APLICADA'),
(282, 16, '21201005', 'BIOLOGIA E FISIOLOGIA DOS MICROORGANISMOS'),
(283, 16, '21201013', 'VIROLOGIA'),
(284, 16, '21201021', 'BACTEROLOGIA'),
(285, 16, '21201030', 'MICOLOGIA'),
(286, 16, '21202001', 'MICROBIOLOGIA APLICADA'),
(287, 16, '21202010', 'MICROBIOLOGIA MÉDICA'),
(288, 16, '21202028', 'MICROBIOLOGIA INDUSTRIAL E DE FERMENTAÇÃO'),
(289, 17, '21301000', 'PROTOZOOLOGIA DE PARASITOS'),
(290, 17, '21301018', 'PROTOZOOLOGIA PARASITÁRIA HUMANA'),
(291, 17, '21301026', 'PROTOZOOLOGIA PARASITÁRIA ANIMAL'),
(292, 17, '21302006', 'HELMINTOLOGIA DE PARASITOS'),
(293, 17, '21302014', 'HELMINTOLOGIA HUMANA'),
(294, 17, '21302022', 'HELMINTOLOGIA ANIMAL'),
(295, 17, '21303002', 'ENTOMOLOGIA E MALACOLOGIA DE PARASITOS E VETORES'),
(296, 18, '20501005', 'ECOLOGIA TEÓRICA'),
(297, 18, '20502001', 'ECOLOGIA DE ECOSSISTEMAS'),
(298, 18, '20503008', 'ECOLOGIA APLICADA'),
(299, 19, '10801006', 'OCEANOGRAFIA BIOLÓGICA'),
(300, 19, '10801014', 'INTER.ENTRE OS ORGAN.MARINHOS E OS PARÂMETROS AMBIENTAIS'),
(301, 20, '20301006', 'PALEOBOTÂNICA'),
(302, 20, '20302002', 'MORFOLOGIA VEGETAL'),
(303, 20, '20302010', 'MORFOLOGIA EXTERNA'),
(304, 20, '20302029', 'CITOLOGIA VEGETAL'),
(305, 20, '20302037', 'ANATOMIA VEGETAL'),
(306, 20, '20302045', 'PALINOLOGIA'),
(307, 20, '20303009', 'FISIOLOGIA VEGETAL'),
(308, 20, '20303017', 'NUTRIÇÃO E CRESCIMENTO VEGETAL'),
(309, 20, '20303025', 'REPRODUÇÃO VEGETAL'),
(310, 20, '20303033', 'ECOFISIOLOGIA VEGETAL'),
(311, 20, '20304005', 'TAXONOMIA VEGETAL'),
(312, 20, '20304013', 'TAXONOMIA DE CRIPTÓGAMOS'),
(313, 20, '20304021', 'TAXONOMIA DE FANEROGAMOS'),
(314, 20, '20305001', 'FITOGEOGRAFIA'),
(315, 20, '20306008', 'BOTÂNICA APLICADA'),
(316, 21, '20401000', 'PALEOZOOLOGIA'),
(317, 21, '20402007', 'MORFOLOGIA DOS GRUPOS RECENTES'),
(318, 21, '20403003', 'FISIOLOGIA DOS GRUPOS RECENTES'),
(319, 21, '20404000', 'COMPORTAMENTO ANIMAL'),
(320, 21, '20405006', 'TAXONOMIA DOS GRUPOS RECENTES'),
(321, 21, '20406002', 'ZOOLOGIA APLICADA'),
(322, 21, '20406010', 'CONSERVAÇÃO DAS ESPÉCIES ANIMAIS'),
(323, 21, '20406029', 'UTILIZAÇÃO DOS ANIMAIS'),
(324, 21, '20406037', 'CONTROLE POPULACIONAL DE ANIMAIS'),
(325, 22, '30101000', 'CONSTRUÇÃO CIVIL'),
(326, 22, '30101018', 'MATERIAIS E COMPONENTES DE CONSTRUÇÃO'),
(327, 22, '30101026', 'PROCESSOS CONSTRUTIVOS'),
(328, 22, '30101034', 'INSTALAÇÕES PREDIAIS'),
(329, 22, '30102006', 'ESTRUTURAS'),
(330, 22, '30102014', 'ESTRUTURAS DE CONCRETO'),
(331, 22, '30102022', 'ESTRUTURAS DE MADEIRAS'),
(332, 22, '30102030', 'ESTRUTURAS METÁLICAS'),
(333, 22, '30102049', 'MECÂNICA DAS ESTRUTURAS'),
(334, 22, '30103002', 'GEOTÉCNICA'),
(335, 22, '30103010', 'FUNDAÇÕES E ESCAVAÇÕES'),
(336, 22, '30103029', 'MECÂNICAS DAS ROCHAS'),
(337, 22, '30103037', 'MECÂNICA DOS SOLOS'),
(338, 22, '30103045', 'OBRAS DE TERRA E ENROCAMENTO'),
(339, 22, '30103053', 'PAVIMENTOS'),
(340, 22, '30104009', 'ENGENHARIA HIDRÁULICA'),
(341, 22, '30104017', 'HIDRÁULICA'),
(342, 22, '30104025', 'HIDROLOGIA'),
(343, 22, '30105005', 'INFRA-ESTRUTURA DE TRANSPORTES'),
(344, 22, '30105013', 'AEROPORTOS; PROJETO E CONSTRUÇÃO'),
(345, 22, '30105021', 'FERROVIAS; PROJETOS E CONSTRUÇÃO'),
(346, 22, '30105030', 'PORTOS E VIAS NAVEGÁVEIS; PROJETO E CONSTRUÇÃO'),
(347, 22, '30105048', 'RODOVIAS; PROJETO E CONSTRUÇÃO'),
(348, 23, '30701007', 'RECURSOS HÍDRICOS'),
(349, 23, '30701015', 'PLANEJAMENTO INTEGRADO DOS RECURSOS HÍDRICOS'),
(350, 23, '30701023', 'TECNOLOGIA E PROBLEMAS SANITÁRIOS DE IRRIGAÇÃO'),
(351, 23, '30701031', 'ÁGUAS SUBTERRÂNEAS E POÇOS PROFUNDOS'),
(352, 23, '30701040', 'CONTROLE DE ENCHENTES E DE BARRAGENS'),
(353, 23, '30701058', 'SEDIMENTOLOGIA'),
(354, 23, '30702003', 'TRATAMENTO DE ÁGUAS DE ABASTECIMENTO E RESIDUÁRIAS'),
(355, 23, '30702011', 'QUÍMICA SANITÁRIA'),
(356, 23, '30702020', 'PROCESSOS SIMPLIFICADOS DE TRATAMENTO DE ÁGUAS'),
(357, 23, '30702038', 'TÉCNICAS CONVENCIONAIS DE TRATAMENTO DE ÁGUAS'),
(358, 23, '30702046', 'TÉCNICAS AVANÇADAS DE TRATAMENTO DE ÁGUAS'),
(359, 23, '30702054', 'ESTUDOS E CARACTERIZAÇÃO DE EFLUENTES INDUSTRIAIS'),
(360, 23, '30702062', 'LAY OUT DE PROCESSOS INDUSTRIAIS'),
(361, 23, '30702070', 'RESÍDUOS RADIOATIVOS'),
(362, 23, '30702078', 'TÉCNICAS CONVENCIONAIS DE TRATAMENTO DE ÁGUAS'),
(363, 23, '30703000', 'SANEAMENTO BÁSICO'),
(364, 23, '30703018', 'TÉCNICAS DE ABASTECIMENTO DA ÁGUA'),
(365, 23, '30703026', 'DRENAGEM DE ÁGUAS RESIDUÁRIAS'),
(366, 23, '30703034', 'DRENAGEM URBANA DE ÁGUAS PLUVIAIS'),
(367, 23, '30703042', 'RESÍDUOS SÓLIDOS, DOMÉSTICOS E INDUSTRIAIS'),
(368, 23, '30703050', 'LIMPEZA PÚBLICA'),
(369, 23, '30703069', 'INSTALAÇÕES HIDRÁULICO-SANITÁRIAS'),
(370, 23, '30704006', 'SANEAMENTO AMBIENTAL'),
(371, 23, '30704014', 'ECOLOGIA APLICADA À ENGENHARIA SANITÁRIA'),
(372, 23, '30704022', 'MICROBIOLOGIA APLICADA E ENGENHARIA SANITÁRIA'),
(373, 23, '30704030', 'PARASITOLOGIA APLICADA À ENGENHARIA SANITÁRIA'),
(374, 23, '30704049', 'QUALIDADE DO AR, DAS ÁGUAS E DO SOLO'),
(375, 23, '30704057', 'CONTROLE DA POLUIÇÃO'),
(376, 23, '30704065', 'LEGISLAÇÃO AMBIENTAL'),
(377, 24, '31001009', 'PLANEJAMENTO DE TRANSPORTES'),
(378, 24, '31001017', 'PLANEJAMENTO E ORGANIZAÇÃO DO SISTEMA DE TRANSPORTE'),
(379, 24, '31001025', 'ECONOMIA DOS TRANSPORTES'),
(380, 24, '31002005', 'VEÍCULOS E EQUIPAMENTOS DE CONTROLE'),
(381, 24, '31002013', 'VIAS DE TRANSPORTE'),
(382, 24, '31002021', 'VEÍCULOS DE TRANSPORTES'),
(383, 24, '31002030', 'ESTAÇÃO DE TRANSPORTE'),
(384, 24, '31002048', 'EQUIPAMENTOS AUXILIARES E CONTROLES'),
(385, 24, '31003001', 'OPERAÇÕES DE TRANSPORTES'),
(386, 24, '31003010', 'ENGENHARIA DE TRÁFEGO'),
(387, 24, '31003028', 'CAPACIDADE DE VIAS DE TRANSPORTE'),
(388, 24, '31003036', 'OPERAÇÃO DE SISTEMAS DE TRANSPORTE'),
(389, 25, '30201004', 'PESQUISA MINERAL'),
(390, 25, '30201012', 'CARACTERIZAÇÃO DO MINÉRIO'),
(391, 25, '30201020', 'DIMENSIONAMENTO DE JAZIDAS'),
(392, 25, '30202000', 'LAVRA'),
(393, 25, '30202019', 'LAVRA A CÉU ABERTO'),
(394, 25, '30202027', 'LAVRA DE MINA SUBTERRÂNEA'),
(395, 25, '30202035', 'EQUIPAMENTOS DE LAVRA'),
(396, 25, '30203007', 'TRATAMENTO DE MINÉRIOS'),
(397, 25, '30203015', 'MÉTODOS DE CONCENTRAÇÃO E ENRIQUECIMENTOS DE MINÉRIOS'),
(398, 25, '30203023', 'EQUIPAMENTOS DE BENEFICIAMENTO DE MINÉRIOS'),
(399, 26, '30301009', 'INSTALAÇÕES E EQUIPAMENTOS METALÚRGICOS'),
(400, 26, '30301017', 'INSTALAÇÕES METALÚRGICAS'),
(401, 26, '30301025', 'EQUIPAMENTOS METALÚRGICOS'),
(402, 26, '30302005', 'METALURGIA EXTRATIVA'),
(403, 26, '30302013', 'AGLOMERAÇÃO'),
(404, 26, '30302021', 'ELETROMETALURGIA'),
(405, 26, '30302030', 'HIDROMETALURGIA'),
(406, 26, '30302048', 'PIROMETALURGIA'),
(407, 26, '30302056', 'TRATAMENTO DE MINÉRIOS'),
(408, 26, '30303001', 'METALURGIA DE TRANSFORMAÇÃO'),
(409, 26, '30303010', 'CONFORMAÇÃO MECÂNICA'),
(410, 26, '30303028', 'FUNDIÇÃO'),
(411, 26, '30303036', 'METALURGIA DE PÓ'),
(412, 26, '30303044', 'RECOBRIMENTOS'),
(413, 26, '30303052', 'SOLDAGEM'),
(414, 26, '30303060', 'TRATAMENTO TÉRMICO, MECÂNICOS E QUÍMICOS'),
(415, 26, '30303079', 'USINAGEM'),
(416, 26, '30304008', 'METALURGIA FÍSICA'),
(417, 26, '30304016', 'ESTRUTURA DOS METAIS E LIGAS'),
(418, 26, '30304024', 'PROPRIEDADES FÍSICAS DOS METAIS E LIGAS'),
(419, 26, '30304032', 'PROPRIEDADES MECÂNICAS DOS METAIS E LIGAS'),
(420, 26, '30304040', 'TRANSFORMAÇÃO DE FASES'),
(421, 26, '30304059', 'CORROSÃO'),
(422, 26, '30305004', 'MATERIAIS NÃO-METÁLICOS'),
(423, 26, '30305012', 'EXTRAÇÃO E TRANSFORMAÇÃO DE MATERIAIS'),
(424, 26, '30305020', 'CERÂMICOS'),
(425, 26, '30305039', 'MATERIAIS CONJUGADOS NÃO-METÁLICOS'),
(426, 26, '30305047', 'POLÍMEROS, APLICAÇÕES'),
(427, 27, '30601002', 'PROCESSOS INDUSTRIAIS DE ENGENHARIA QUÍMICA'),
(428, 27, '30601010', 'PROCESSOS BIOQUÍMICOS'),
(429, 27, '30601029', 'PROCESSOS ORGÂNICOS'),
(430, 27, '30601037', 'PROCESSOS INORGÂNICOS'),
(431, 27, '30602009', 'OPERAÇÕES INDUSTRIAIS E EQUIPAMENTOS PARA ENG. QUÍMICA'),
(432, 27, '30602017', 'REATORES QUÍMICOS'),
(433, 27, '30602025', 'OPERAÇÕES CARACTERÍSTICAS DE PROCESSOS BIOQUÍMICOS'),
(434, 27, '30602033', 'OPERAÇÕES DE SEPARAÇÃO E MISTURA'),
(435, 27, '30603005', 'TECNOLOGIA QUÍMICA'),
(436, 27, '30603013', 'BALANÇOS GLOBAIS DE MATÉRIA E ENERGIA'),
(437, 27, '30603021', 'ÁGUA'),
(438, 27, '30603030', 'ÁLCOOL'),
(439, 27, '30603048', 'ALIMENTOS'),
(440, 27, '30603056', 'BORRACHAS'),
(441, 27, '30603064', 'CARVÃO'),
(442, 27, '30603072', 'CERÂMICA'),
(443, 27, '30603080', 'CIMENTO'),
(444, 27, '30603099', 'COURO'),
(445, 27, '30603102', 'DETERGENTES'),
(446, 27, '30603110', 'FERTILIZANTES'),
(447, 27, '30603129', 'MEDICAMENTOS'),
(448, 27, '30603137', 'METAIS NÃO-FERROSOS'),
(449, 27, '30603145', 'ÓLEOS'),
(450, 27, '30603153', 'PAPEL E CELULOSE'),
(451, 27, '30603161', 'PETRÓLEO E PETROQUÍMICA'),
(452, 27, '30603170', 'POLÍMEROS'),
(453, 27, '30603188', 'PRODUTOS NATURAIS'),
(454, 27, '30603196', 'TÉXTEIS'),
(455, 27, '30603200', 'TRATAMENTOS E APROVEITAMENTOS DE REJEITOS'),
(456, 27, '30603218', 'XISTO'),
(457, 28, '30901006', 'APLICAÇÕES DE RADIOISÓTOPOS'),
(458, 28, '30901014', 'PRODUÇÃO DE RADIOISÓPOTOS'),
(459, 28, '30901022', 'APLICAÇÕES INDUSTRIAIS DE RADIOISÓPOTOS'),
(460, 28, '30901030', 'INSTRUMENTAÇÃO PARA MEDIDA E CONTROLE DE RADIAÇÃO'),
(461, 28, '30902002', 'FUSÃO CONTROLADA'),
(462, 28, '30902010', 'PROCESSOS INDUSTRIAIS DA FUSÃO CONTROLADA'),
(463, 28, '30902029', 'PROBLEMAS TECNOLÓGICOS DA FUSÃO CONTROLADA'),
(464, 28, '30903009', 'COMBUSTÍVEL NÚCLEAR'),
(465, 28, '30903017', 'EXTRAÇÃO DE COMBUSTÍVEL NÚCLEAR'),
(466, 28, '30903025', 'CONVERSÃO, ENRIQUECIMENTO E FABRICAÇÃO DE COMBUST. NÚCLEAR'),
(467, 28, '30903033', 'REPROCESSAMENTO DO COMBUSTÍVEL NÚCLEAR'),
(468, 28, '30903041', 'REJEITOS DE COMBUSTÍVEL NÚCLEAR'),
(469, 28, '30904005', 'TECNOLOGIA DOS REATORES'),
(470, 28, '30904013', 'NÚCLEO DO REATOR'),
(471, 28, '30904021', 'MATERIAIS NUCLEARES E BLINDAGEM DE REATORES'),
(472, 28, '30904030', 'TRANSFERÊNCIA DE CALOR EM REATORES'),
(473, 28, '30904048', 'GERAÇÃO E INTEGRAÇÃO COM SISTEMAS ELÉTRICOS EM REATORES'),
(474, 28, '30904056', 'INSTRUMENTAÇÃO PARA OPERAÇÃO E CONTROLE DE REATORES'),
(475, 28, '30904064', 'SEGURANÇA, LOCALIZAÇÃO E LICENCIAMENTO DE REATORES'),
(476, 28, '30904072', 'ASPECTOS ECONÔMICOS DE REATORES'),
(477, 29, '30501008', 'FENÔMENOS DE TRANSPORTES'),
(478, 29, '30501016', 'TRANSFERÊNCIA DE CALOR'),
(479, 29, '30501024', 'MECÂNICA DOS FLUÍDOS'),
(480, 29, '30501032', 'DINÂMICA DOS GASES'),
(481, 29, '30501040', 'PRINCÍPIOS VARIACIONAIS E MÉTODOS NUMÉRICOS'),
(482, 29, '30502004', 'ENGENHARIA TÉRMICA'),
(483, 29, '30502012', 'TERMODINÂMICA'),
(484, 29, '30502020', 'CONTROLE AMBIENTAL'),
(485, 29, '30502039', 'APROVEITAMENTO DA ENERGIA'),
(486, 29, '30503000', 'MECÂNICA DOS SÓLIDOS'),
(487, 29, '30503019', 'MECÂNICA DOS CORPOS SÓLIDOS, ELÁSTICOS E PLÁSTICOS'),
(488, 29, '30503027', 'DINÂMICA DOS CORPOS RÍGIDOS, ELÁSTICOS E PLÁSTICOS'),
(489, 29, '30503035', 'ANÁLISE DE TENSÕES'),
(490, 29, '30503043', 'TERMOELASTICIDADE'),
(491, 29, '30504007', 'PROJETOS DE MÁQUINAS'),
(492, 29, '30504015', 'TEORIA DOS MECANISMOS'),
(493, 29, '30504023', 'ESTÁTICA E DINÂMICA APLICADA'),
(494, 29, '30504031', 'ELEMENTOS DE MÁQUINAS'),
(495, 29, '30504040', 'FUNDAMENTOS GERAIS DE PROJETOS DAS MÁQUINAS'),
(496, 29, '30504058', 'MÁQUINAS, MOTORES E EQUIPAMENTOS'),
(497, 29, '30504066', 'MÉTODOS DE SÍNTESE E OTIMIZAÇÃO APLICADOS AO PROJ. MECÂNICO'),
(498, 29, '30504074', 'CONTROLE DE SISTEMAS MECÂNICOS'),
(499, 29, '30504082', 'APROVEITAMENTO DE ENERGIA'),
(500, 29, '30505003', 'PROCESSOS DE FABRICAÇÃO'),
(501, 29, '30505011', 'MATRIZES E FERRAMENTAS'),
(502, 29, '30505020', 'MÁQUINAS DE USINAGEM E CONFORMAÇÃO'),
(503, 29, '30505038', 'CONTROLE NUMÉRICO'),
(504, 29, '30505046', 'ROBOTIZAÇÃO'),
(505, 29, '30505054', 'PROCESSOS DE FABRICAÇÃO, SELEÇÃO ECONÔMICA'),
(506, 30, '30801001', 'GERÊNCIA DE PRODUÇÃO'),
(507, 30, '30801010', 'PLANEJAMENTO DE INSTALAÇOES INDUSTRIAIS'),
(508, 30, '30801028', 'PLANEJAMENTO, PROJETO E CONTROLE DE SIST. DE PRODUÇÃO'),
(509, 30, '30801036', 'HIGIENE E SEGURANÇA DO TRABALHO'),
(510, 30, '30801044', 'SUPRIMENTOS'),
(511, 30, '30801052', 'GARANTIA DE CONTROLE DE QUALIDADE'),
(512, 30, '30802008', 'PESQUISA OPERACIONAL'),
(513, 30, '30802016', 'PROCESSOS ESTOCÁSTICOS E TEORIAS DAS FILAS'),
(514, 30, '30802024', 'PROGRAMAÇÃO LINEAR, NÃO-LINEAR, MISTA E DINÂMICA'),
(515, 30, '30802032', 'SÉRIES TEMPORAIS'),
(516, 30, '30802040', 'TEORIA DOS GRAFOS'),
(517, 30, '30802059', 'TEORIA DOS JOGOS'),
(518, 30, '30803004', 'ENGENHARIA DO PRODUTO'),
(519, 30, '30803012', 'ERGONOMIA'),
(520, 30, '30803020', 'METODOLOGIA DE PROJETO DO PRODUTO'),
(521, 30, '30803039', 'PROCESSOS DE TRABALHO'),
(522, 30, '30803047', 'GERÊNCIA DO PROJETO E DO PRODUTO'),
(523, 30, '30803055', 'DESENVOLVIMENTO DE PRODUTO'),
(524, 30, '30804000', 'ENGENHARIA ECONÔMICA'),
(525, 30, '30804019', 'ESTUDO DE MERCADO'),
(526, 30, '30804027', 'LOCALIZAÇÃO INDUSTRIAL'),
(527, 30, '30804035', 'ANÁLISE DE CUSTOS'),
(528, 30, '30804043', 'ECONOMIA DE TECNOLOGIA'),
(529, 30, '30804051', 'VIDA ECONÔMICA DOS EQUIPAMENTOS'),
(530, 30, '30804060', 'AVALIAÇÃO DE PROJETOS'),
(531, 31, '31101003', 'HIDRODINÂMICA DE NAVIOS E SISTEMAS OCEÂNICOS'),
(532, 31, '31101011', 'RESISTÊNCIA HIDRODINÂMICA'),
(533, 31, '31101020', 'PROPULSÃO DE NAVIOS'),
(534, 31, '31102000', 'ESTRUTURAS NAVAIS E OCEÂNICAS'),
(535, 31, '31102018', 'ANÁLISE TEÓRICA E EXPERIMENTAL DE ESTRUTURA'),
(536, 31, '31102026', 'DINÂMICA ESTRUTURAL NAVAL E OCEÂNICA'),
(537, 31, '31102034', 'SÍNTESE ESTRUTURAL NAVAL E OCEÂNICA'),
(538, 31, '31103006', 'MÁQUINAS MARÍTIMAS'),
(539, 31, '31103014', 'ANÁLISE DE SISTEMAS PROPULSORES'),
(540, 31, '31103022', 'CONTROLE E AUTOMAÇÃO DE SISTEMAS PROPULSORES'),
(541, 31, '31103030', 'EQUIPAMENTOS AUXILIARES DO SISTEMA PROPULSIVO'),
(542, 31, '31103049', 'MOTOR DE PROPULSÃO'),
(543, 31, '31104002', 'PROJETOS DE NAVIOS E DE SISTEMAS OCEÂNICOS'),
(544, 31, '31104010', 'PROJETOS DE NAVIOS'),
(545, 31, '31104029', 'PROJETOS DE SISTEMAS OCEÂNICOS FIXOS E SEMI-FIXOS'),
(546, 31, '31104037', 'PROJETOS DE EMBARCAÇÕES NÃO-CONVENCIONAIS'),
(547, 31, '31105009', 'TECNOLOGIA DE CONSTRUÇÃO NAVAL E DE SISTEMAS OCEÂNICOS'),
(548, 31, '31105017', 'MÉTODOS DE FABRICAÇÃO DE NAVIOS E SISTEMAS OCEÂNICOS'),
(549, 31, '31105025', 'SOLDAGEM DE ESTRUTURAS NAVAIS E OCEÂNICOS'),
(550, 31, '31105033', 'CUSTOS DE CONSTRUÇÃO NAVAL'),
(551, 31, '31105041', 'NORMATIZAÇÃO E CERTIFICAÇÃO DE QUALIDADE DE NAVIOS'),
(552, 32, '31201008', 'AERODINÂMICA'),
(553, 32, '31201016', 'AERODINÂMICA DE AERONAVES ESPACIAIS'),
(554, 32, '31201024', 'AERODINÂMICA DOS PROCESSOS GEOFÍSICOS E INTERPLANETÁRIOS'),
(555, 32, '31202004', 'DINÂMICA DE VÔO'),
(556, 32, '31202012', 'TRAJETÓRIAS E ÓRBITAS'),
(557, 32, '31202020', 'ESTABILIDADE E CONTROLE'),
(558, 32, '31203000', 'ESTRUTURAS AEROESPACIAIS'),
(559, 32, '31203019', 'AEROELASTICIDADE'),
(560, 32, '31203027', 'FADIGA'),
(561, 32, '31203035', 'PROJETOS DE ESTRUTURAS AEROESPACIAIS'),
(562, 32, '31204007', 'MATERIAIS E PROCESSOS P/ENGENHARIA AERON. E AEROESPACIAL'),
(563, 32, '31205003', 'PROPULSÃO AEROESPACIAL'),
(564, 32, '31205011', 'COMBUSTÃO E ESCOAMENTO COM REAÇÕES QUÍMICAS'),
(565, 32, '31205020', 'PROPULSÃO DE FOGUTES'),
(566, 32, '31205038', 'MÁQUINAS DE FLUXO'),
(567, 32, '31205046', 'MOTORES ALTERNATIVOS'),
(568, 32, '31206000', 'SISTEMAS AEROESPACIAIS'),
(569, 32, '31206018', 'AVIÕES'),
(570, 32, '31206026', 'FOGUETES'),
(571, 32, '31206034', 'HELICÓPTEROS'),
(572, 32, '31206042', 'HOVERCRAFT'),
(573, 32, '31206050', 'SATÉLITES E OUTROS DISPOSITIVOS AEROESPACIAIS'),
(574, 32, '31206069', 'NORMATIZAÇÃO E CERT. DE QUAL. DE AERONAVES E COMPONENTES'),
(575, 32, '31206077', 'MANUTENÇÃO DE SISTEMAS AEROESPACIAIS'),
(576, 33, '30401003', 'MATERIAIS ELÉTRICOS'),
(577, 33, '30401011', 'MATERIAIS CONDUTORES'),
(578, 33, '30401020', 'MATERIAIS E COMPONENTES SEMICONDUTORES'),
(579, 33, '30401038', 'MATERIAIS E DISPOSITIVOS SUPERCONDUTORES'),
(580, 33, '30401046', 'MATERIAIS DIELÉTRICOS, PIESOELÉTRICOS E FERROELÉTRICOS'),
(581, 33, '30401054', 'MAT. E COMP. ELETROÓTICOS E MAGNET., MAT. FOTOELÉTRICOS'),
(582, 33, '30401062', 'MATERIAIS E DISPOSITIVOS MAGNÉTICOS'),
(583, 33, '30402000', 'MEDIDAS ELÉTRICAS, MAGNÉTICAS E ELETRÔNICAS; INSTRUMENTAÇÃO'),
(584, 33, '30402018', 'MEDIDAS ELÉTRICAS'),
(585, 33, '30402026', 'MEDIDAS MAGNÉTICAS'),
(586, 33, '30402034', 'INSTRUMENTAÇÃO ELETROMECÂNICA'),
(587, 33, '30402042', 'INSTRUMENTAÇÃO ELETRÔNICA'),
(588, 33, '30402050', 'SISTEMAS ELETRÔNICOS DE MEDIDAS E DE CONTROLE'),
(589, 33, '30403006', 'CIRCUITOS ELÉTRICOS, MAGNÉTICOS E ELETRÔNICOS'),
(590, 33, '30403014', 'TEORIA GERAL DOS CIRCUITOS ELÉTRICOS'),
(591, 33, '30403022', 'CIRCUITOS LINEARES E NÃO LINEARES'),
(592, 33, '30403030', 'CIRCUITOS ELETRÔNICOS'),
(593, 33, '30403049', 'CIRCUITOS MAGNÉTICOS, MAGNÉTISMO, ELETROMAGNÉTISMO'),
(594, 33, '30404002', 'SISTEMAS ELÉTRICOS DE POTÊNCIA'),
(595, 33, '30404010', 'GERAÇÃO DE ENERGIA ELÉTRICA'),
(596, 33, '30404029', 'TRANSMISSÃO DA ENERGIA ELET., DISTRIB. DA ENERGIA ELÉTRICA'),
(597, 33, '30404037', 'CONVERSÃO E RETIFICAÇÃO DA ENERGIA ELÉTRICA'),
(598, 33, '30404045', 'MEDIÇÃO, CONTROLE, CORREÇÃO E PROTEÇÃO DE SIST. ELET. E POT.'),
(599, 33, '30404053', 'MÁQUINAS ELÉTRICAS E DISPOSITIVOS DE POTÊNCIA'),
(600, 33, '30404061', 'INSTALAÇÕES ELÉTRICAS PREDIAIS E INDUSTRIAIS'),
(601, 33, '30405009', 'ELETRÔNICA INDUSTRIAL, SISTEMAS E CONTROLES ELETRÔNICOS'),
(602, 33, '30405017', 'ELETRÔNICA INDUSTRIAL'),
(603, 33, '30405025', 'AUTOMAÇÃO ELETRÔNICA DE PROCESSOS ELÉTRICOS E INDUSTRIAIS'),
(604, 33, '30405033', 'CONTROLE DE PROCESSOS ELETRÔNICOS, RETROALIMENTAÇÃO'),
(605, 33, '30406005', 'TELECOMUNICAÇÕES'),
(606, 33, '30406013', 'TEORIA ELETROMAG., MICROONDAS, PROPAGAÇÃO DE ONDAS, ANTENAS'),
(607, 33, '30406021', 'RADIONAVEGAÇÃO E RADIOASTRONOMIA'),
(608, 33, '30406030', 'SISTEMAS DE TELECOMUNICAÇÕES'),
(609, 34, '31301002', 'BIOENGENHARIA'),
(610, 34, '31301010', 'PROCESSAMENTO DE SINAIS BIOLÓGICOS'),
(611, 34, '31301029', 'MODELAGEM DE FENÔMENOS BIOLÓGICOS'),
(612, 34, '31301037', 'MODELAGEM DE SISTEMAS BIOLÓGICOS'),
(613, 34, '31302009', 'ENGENHARIA MÉDICA'),
(614, 34, '31302017', 'BIOMATERIAIS E MATERIAIS BIOCOMPATÍVEIS'),
(615, 34, '31302025', 'TRANSDUTORES PARA APLICAÇÕES BIOMÉDICAS'),
(616, 34, '31302033', 'INSTRUMENTAÇÃO ODONTOLÓGICA E MÉDICO-HOSPITALAR'),
(617, 34, '31302041', 'TECNOLOGIA DE PRÓTESES'),
(618, 35, '40101002', 'CLÍNICA MÉDICA'),
(619, 35, '40101010', 'ANGIOLOGIA'),
(620, 35, '40101029', 'DERMATOLOGIA'),
(621, 35, '40101045', 'CANCEROLOGIA'),
(622, 35, '40101061', 'ENDOCRINOLOGIA'),
(623, 35, '40101100', 'CARDIOLOGIA'),
(624, 35, '40101118', 'GASTROENTEROLOGIA'),
(625, 35, '40101126', 'PNEUMOLOGIA'),
(626, 35, '40101134', 'NEFROLOGIA'),
(627, 35, '40101169', 'FISIATRIA'),
(628, 35, '40107000', 'MEDICINA LEGAL E DEONTOLOGIA'),
(629, 35, '40101037', 'ALERGOLOGIA E IMUNOLOGIA CLÍNICA'),
(630, 35, '40101053', 'HEMATOLOGIA'),
(631, 35, '40101070', 'NEUROLOGIA'),
(632, 35, '40101088', 'PEDIATRIA'),
(633, 35, '40101096', 'DOENÇAS INFECCIOSAS E PARASITÁRIAS'),
(634, 35, '40101142', 'REUMATOLOGIA'),
(635, 35, '40103005', 'SAÚDE MATERNO-INFANTIL'),
(636, 35, '40104001', 'PSIQUIATRIA'),
(637, 35, '40105008', 'ANATOMIA PATOLÓGICA E PATOLOGIA CLÍNICA'),
(638, 35, '40106004', 'RADIOLOGIA MÉDICA'),
(639, 35, '40101150', 'GINECOLOGIA E OBSTETRÍCIA'),
(640, 35, '40101177', 'OFTALMOLOGIA'),
(641, 35, '40101186', 'ORTOPEDIA'),
(642, 35, '40102009', 'CIRURGIA'),
(643, 35, '40102017', 'CIRURGIA PLÁSTICA E RESTAURADORA'),
(644, 35, '40102025', 'CIRURGIA OTORRINOLARINGOLOGIA'),
(645, 35, '40102033', 'CIRURGIA OFTALMOLÓGICA'),
(646, 35, '40102041', 'CIRURGIA CARDIOVASCULAR'),
(647, 35, '40102050', 'CIRURGIA TORÁXICA'),
(648, 35, '40102068', 'CIRURGIA GASTROENTEROLOGICA'),
(649, 35, '40102076', 'CIRURGIA PEDIÁTRICA'),
(650, 35, '40102084', 'NEUROCIRURGIA'),
(651, 35, '40102092', 'CIRURGIA UROLÓGICA'),
(652, 35, '40102106', 'CIRURGIA PROCTOLÓGICA'),
(653, 35, '40102114', 'CIRURGIA ORTOPÉDICA'),
(654, 35, '40102122', 'CIRURGIA TRAUMATOLÓGICA'),
(655, 35, '40102130', 'ANESTESIOLOGIA'),
(656, 35, '40102149', 'CIRURGIA EXPERIMENTAL'),
(657, 36, '40501000', 'BIOQUÍMICA DA NUTRIÇÃO'),
(658, 36, '40502007', 'DIETÉTICA'),
(659, 36, '40503003', 'ANÁLISE NUTRICIONAL DE POPULAÇÃO'),
(660, 36, '40504000', 'DESNUTRIÇÃO E DESENVOLVIMENTO FISIOLÓGICO'),
(661, 37, '40201007', 'CLÍNICA ODONTOLÓGICA'),
(662, 37, '40202003', 'CIRURGIA BUCO-MAXILO-FACIAL'),
(663, 37, '40203000', 'ORTODONTIA'),
(664, 37, '40204006', 'ODONTOPEDIATRIA'),
(665, 37, '40205002', 'PERIODONTIA'),
(666, 37, '40206009', 'ENDODONTIA'),
(667, 37, '40207005', 'RADIOLOGIA ODONTOLÓGICA'),
(668, 37, '40208001', 'ODONTOLOGIA SOCIAL E PREVENTIVA'),
(669, 37, '40209008', 'MATERIAIS ODONTOLÓGICOS'),
(670, 38, '40301001', 'FARMACOTECNIA'),
(671, 38, '40302008', 'FARMACOGNOSIA'),
(672, 38, '40303004', 'ANÁLISE TOXICOLÓGICA'),
(673, 38, '40304000', 'ANÁLISE E CONTROLE DE MEDICAMENTOS'),
(674, 38, '40305007', 'BROMATOLOGIA'),
(675, 39, '40401006', 'ENFERMAGEM MÉDICO-CIRÚRGICA'),
(676, 39, '40402002', 'ENFERMAGEM OBSTÉTRICA'),
(677, 39, '40403009', 'ENFERMAGEM PEDIÁTRICA'),
(678, 39, '40404005', 'ENFERMAGEM PSIQUIÁTRICA'),
(679, 39, '40405001', 'ENFERMAGEM DE DOENÇAS CONTAGIOSAS'),
(680, 39, '40406008', 'ENFERMAGEM DE SAÚDE PÚBLICA'),
(681, 40, '40601005', 'EPIDEMIOLOGIA'),
(682, 40, '40602001', 'SAÚDE PÚBLICA'),
(683, 40, '40603008', 'MEDICINA PREVENTIVA'),
(684, 44, '50101005', 'CIÊNCIA DO SOLO'),
(685, 44, '50101013', 'GÊNESE, MORFOLOGIA E CLASSIFICAÇÃO DOS SOLOS'),
(686, 44, '50101021', 'FÍSICA DO SOLO'),
(687, 44, '50101030', 'QUÍMICA DO SOLO'),
(688, 44, '50101048', 'MICROBIOLOGIA E BIOQUÍMICA DO SOLO'),
(689, 44, '50101056', 'FERTILIDADE DO SOLO E ADUBAÇÃO'),
(690, 44, '50101064', 'MANEJO E CONSERVAÇÃO DO SOLO'),
(691, 44, '50102001', 'FITOSSANIDADE'),
(692, 44, '50102010', 'FITOPATOLOGIA'),
(693, 44, '50102028', 'ENTOMOLOGIA AGRÍCOLA'),
(694, 44, '50102036', 'PARASITOLOGIA AGRÍCOLA'),
(695, 44, '50102044', 'MICROBIOLOGIA AGRÍCOLA'),
(696, 44, '50102052', 'DEFESA FITOSSANITÁRIA'),
(697, 44, '50103008', 'FITOTECNIA'),
(698, 44, '50103016', 'MANEJO E TRATOS CULTURAIS'),
(699, 44, '50103024', 'MECANIZAÇÃO AGRÍCOLA'),
(700, 44, '50103032', 'PRODUÇÃO E BENEFICIAMENTO DE SEMENTES'),
(701, 44, '50103040', 'PRODUÇÃO DE MUDAS'),
(702, 44, '50103059', 'MELHORAMENTO VEGETAL'),
(703, 44, '50103067', 'FISIOLOGIA DE PLANTAS CULTIVADAS'),
(704, 44, '50103075', 'MATOLOGIA'),
(705, 44, '50104004', 'FLORICULTURA, PARQUES E JARDINS'),
(706, 44, '50104012', 'FLORICULTURA'),
(707, 44, '50104020', 'PARQUES E JARDINS'),
(708, 44, '50104039', 'ARBORIZAÇÃO DE VIAS PÚBLICAS'),
(709, 44, '50105000', 'AGROMETEROLOGIA'),
(710, 44, '50106007', 'EXTENSÃO RURAL'),
(711, 45, '50201000', 'SILVICULTURA'),
(712, 45, '50201018', 'DENDROLOGIA'),
(713, 45, '50201026', 'FLORESTAMENTO E REFLORESTAMENTO'),
(714, 45, '50201034', 'GENÉTICA E MELHORAMENTO FLORESTAL'),
(715, 45, '50201042', 'SEMENTES FLORESTAIS'),
(716, 45, '50201050', 'NUTRIÇÃO FLORESTAL'),
(717, 45, '50201069', 'FISIOLOGIA FLORESTAL'),
(718, 45, '50201077', 'SOLOS FLORESTAIS'),
(719, 45, '50201085', 'PROTEÇÃO FLORESTAL'),
(720, 45, '50202006', 'MANEJO FLORESTAL'),
(721, 45, '50202014', 'ECONOMIA FLORESTAL'),
(722, 45, '50202022', 'POLÍTICA E LEGISLAÇÃO FLORESTAL'),
(723, 45, '50202030', 'ADMINISTRAÇÃO FLORESTAL'),
(724, 45, '50202049', 'DENDROMETRIA E INVENTÁRIO FLORESTAL'),
(725, 45, '50202057', 'FOTOINTERPRETAÇÃO FLORESTAL'),
(726, 45, '50202065', 'ORDENAMENTO FLORESTAL'),
(727, 45, '50203002', 'TÉCNICAS E OPERAÇÕES FLORESTAIS'),
(728, 45, '50203010', 'EXPLORAÇÃO FLORESTAL'),
(729, 45, '50203029', 'MECANIZAÇÃO FLORESTAL'),
(730, 45, '50204009', 'TECNOLOGIA E UTILIZAÇÃO DE PRODUTOS FLORESTAIS'),
(731, 45, '50204017', 'ANATOMIA E IDENTIFICAÇÃO DE PRODUTOS FLORESTAIS'),
(732, 45, '50204025', 'PROPRIEDADES FISICO-MECÂNICAS DA MADEIRA'),
(733, 45, '50204033', 'RELAÇÕES ÁGUA-MADEIRA E SECAGEM'),
(734, 45, '50204041', 'TRATAMENTO DA MADEIRA'),
(735, 45, '50204050', 'PROCESSAMENTO MECÂNICO DA MADEIRA'),
(736, 45, '50204068', 'QUÍMICA DA MADEIRA'),
(737, 45, '50204076', 'RESINAS DE MADEIRAS'),
(738, 45, '50204084', 'TECNOLOGIA DE CELULOSE E PAPEL'),
(739, 45, '50204092', 'TECNOLOGIA DE CHAPAS'),
(740, 45, '50205005', 'CONSERVAÇÃO DA NATUREZA'),
(741, 45, '50205013', 'HIDROLOGIA FLORESTAL'),
(742, 45, '50205021', 'CONSERVAÇÃO DE ÁREAS SILVESTRES'),
(743, 45, '50205030', 'CONSERVAÇÃO DAS BACIAS HIDROGRÁFICAS'),
(744, 45, '50205048', 'RECUPERAÇÃO DE ÁREAS DEGRADADAS'),
(745, 45, '50206001', 'ENERGIA DE BIOMASSA FLORESTAL'),
(746, 46, '50301004', 'MÁQUINAS E IMPLEMENTOS AGRÍCOLAS'),
(747, 46, '50302000', 'ENGENHARIA DE ÁGUA E SOLO'),
(748, 46, '50302019', 'IRRIGAÇÃO E DRENAGEM'),
(749, 46, '50302027', 'CONSERVAÇÃO DE SOLO E ÁGUA'),
(750, 46, '50303007', 'ENGENHARIA DE PROCESSAMENTO DE PRODUTOS AGRÍCOLAS'),
(751, 46, '50303015', 'PRÉ-PROCESSAMENTO DE PRODUTOS AGRÍCOLAS'),
(752, 46, '50303023', 'ARMAZENAMENTO DE PRODUTOS AGRÍCOLAS'),
(753, 46, '50303031', 'TRANSFERÊNCIA DE PRODUTOS AGRÍCOLAS'),
(754, 46, '50304003', 'CONSTRUÇÕES RURAIS E AMBIÊNCIA'),
(755, 46, '50304011', 'ASSENTAMENTO RURAL'),
(756, 46, '50304020', 'ENGENHARIA DE CONSTRUÇÕES RURAIS'),
(757, 46, '50304038', 'SANEAMENTO RURAL'),
(758, 46, '50305000', 'ENERGIZAÇÃO RURAL'),
(759, 47, '50401009', 'ECOLOGIA DOS ANIMAIS DOMÉSTICOS E ETOLOGIA'),
(760, 47, '50402005', 'GENÉTICA E MELHORAMENTO DOS ANIMAIS DOMÉSTICOS'),
(761, 47, '50403001', 'NUTRIÇÃO E ALIMENTAÇÃO ANIMAL'),
(762, 47, '50403010', 'EXIGÊNCIAS NUTRICIONAIS DOS ANIMAIS'),
(763, 47, '50403028', 'AVALIAÇÃO DE ALIMENTOS PARA ANIMAIS'),
(764, 47, '50403036', 'CONSERVAÇÃO DE ALIMENTOS PARA ANIMAIS'),
(765, 47, '50404008', 'PASTAGEM E FORRAGICULTURA'),
(766, 47, '50404016', 'AVALIAÇÃO, PRODUÇÃO E CONSERVAÇÃO DE FORRAGENS'),
(767, 47, '50404024', 'MANEJO E CONSERVAÇÃO DE PASTAGENS'),
(768, 47, '50404032', 'FISIOLOGIA DE PLANTAS FORRAGEIRAS'),
(769, 47, '50404040', 'MELHORAMENTO DE PLANTAS FORRAGEIRAS E PRODUÇÃO DE SEMENTES'),
(770, 47, '50404059', 'TOXICOLOGIA E PLANTAS TÓXICAS'),
(771, 47, '50405004', 'PRODUÇÃO ANIMAL'),
(772, 47, '50405012', 'CRIAÇÃO DE ANIMAIS'),
(773, 47, '50405020', 'MANEJO DE ANIMAIS'),
(774, 47, '50405039', 'INSTALAÇÕES PARA PRODUÇÃO ANIMAL'),
(775, 48, '50601008', 'RECURSOS PESQUEIROS MARINHOS'),
(776, 48, '50601016', 'FATORES ABIÓTICOS DO MAR'),
(777, 48, '50601024', 'AVALIAÇÃO DE ESTOQUE PESQUEIROS MARINHOS'),
(778, 48, '50601032', 'EXPLORAÇÃO PESQUEIRA MARINHA'),
(779, 48, '50601040', 'MANEJO E CONSERVAÇÃO DE RECURSOS PESQUEIROS MARINHOS'),
(780, 48, '50602004', 'RECURSOS PESQUEIROS DE ÁGUAS INTERIORES'),
(781, 48, '50602012', 'FATORES ABIÓTICOS DE ÁGUAS INTERIORES'),
(782, 48, '50602020', 'AVALIAÇÃO DE ESTOQUES PESQUEIROS DE ÁGUAS INTERIORES'),
(783, 48, '50602039', 'EXPLORAÇÃO PESQUEIRA DE ÁGUAS INTERIORES'),
(784, 48, '50602047', 'MANEJO E CONSERV. DE RECURSOS PESQUEIROS DE ÁGUAS INFERIORES'),
(785, 48, '50603000', 'AQUICULTURA'),
(786, 48, '50603019', 'MARICULTURA'),
(787, 48, '50603027', 'CARCINOCULTURA'),
(788, 48, '50603035', 'OSTREICULTURA'),
(789, 48, '50603043', 'PISCICULTURA'),
(790, 48, '50604007', 'ENGENHARIA DE PESCA'),
(791, 49, '50501003', 'CLÍNICA E CIRÚRGIA ANIMAL'),
(792, 49, '50501011', 'ANESTESIOLOGIA ANIMAL'),
(793, 49, '50501020', 'TÉCNICA CIRÚRGICA ANIMAL'),
(794, 49, '50501038', 'RADIOLOGIA DE ANIMAIS'),
(795, 49, '50501046', 'FARMACOLOGIA E TERAPÉUTICA ANIMAL'),
(796, 49, '50501054', 'OBSTETRÍCIA ANIMAL'),
(797, 49, '50501062', 'CLÍNICA VETERINÁRIA'),
(798, 49, '50501070', 'CLÍNICA CIRÚRGICA ANIMAL'),
(799, 49, '50501089', 'TOXICOLOGIA ANIMAL'),
(800, 49, '50502000', 'MEDICINA VETERINÁRIA PREVENTIVA'),
(801, 49, '50502018', 'EPIDEMIOLOGIA ANIMAL'),
(802, 49, '50502026', 'SANEAMENTO APLICADO À SAÚDE DO HOMEM'),
(803, 49, '50502034', 'DOENÇAS INFECCIOSAS DE ANIMAIS'),
(804, 49, '50502042', 'DOENÇAS PARASITÁRIAS DE ANIMAIS'),
(805, 49, '50502050', 'SAÚDE ANIMAL (PROGRAMAS SANITÁRIOS)'),
(806, 49, '50503006', 'PATOLOGIA ANIMAL'),
(807, 49, '50503014', 'PATOLOGIA AVIÁRIA'),
(808, 49, '50503022', 'ANATOMIA PATOLÓGICA ANIMAL'),
(809, 49, '50503030', 'PATOLOGIA CLÍNICA ANIMAL'),
(810, 49, '50504002', 'REPRODUÇÃO ANIMAL'),
(811, 49, '50504010', 'GINECOLOGIA E ANDROLOGIA ANIMAL'),
(812, 49, '50504029', 'INSEMINAÇÃO ARTIFICIAL ANIMAL'),
(813, 49, '50504037', 'FISIOPATOLOGIA DA REPRODUÇÃO ANIMAL'),
(814, 49, '50505009', 'INSPEÇÃO DE PRODUTOS DE ORIGEM ANIMAL'),
(815, 50, '50701002', 'CIÊNCIA DE ALIMENTOS'),
(816, 50, '50701010', 'VALOR NUTRITIVO DE ALIMENTOS'),
(817, 50, '50701029', 'QUÍMICA, FÍSICA, FÍSICO-QUÍM. BIOQ. DOS ALI. MAT. PRIMAS ALI'),
(818, 50, '50701037', 'MICROBIOLOGIA DE ALIMENTOS'),
(819, 50, '50701045', 'FISIOLOGIA PÓS-COLHEITA'),
(820, 50, '50701053', 'TOXICIDADE E RESÍDUOS DE PESTICIDAS EM ALIMENTOS'),
(821, 50, '50701061', 'AVALIAÇÃO E CONTROLE DE QUALIDADE DE ALIMENTOS'),
(822, 50, '50701070', 'PADRÕES, LEGISLAÇÃO E FISCALIZAÇÃO DE ALIMENTOS'),
(823, 50, '50702009', 'TECNOLOGIA DE ALIMENTOS'),
(824, 50, '50702017', 'TECNOLOGIA DE PRODUTOS DE ORIGEM ANIMAL'),
(825, 50, '50702025', 'TECNOLOGIA DE PRODUTOS DE ORIGEM VEGETAL'),
(826, 50, '50702033', 'TECNOLOGIA DAS BEBIDAS'),
(827, 50, '50702041', 'TECNOLOGIA DE ALIMENTOS DIETÉTICOS E NUTRICIONAIS'),
(828, 50, '50702050', 'APROVEITAMENTO DE SUBPRODUTOS'),
(829, 50, '50702068', 'EMBALAGENS DE PRODUTOS ALIMENTARES'),
(830, 50, '50703005', 'ENGENHARIA DE ALIMENTOS'),
(831, 50, '50703013', 'INSTALAÇÕES INDUSTRIAIS DE PRODUÇÃO DE ALIMENTOS'),
(832, 50, '50703021', 'ARMAZENAMENTO DE ALIMENTOS'),
(833, 51, '60101008', 'TEORIA DO DIREITO'),
(834, 51, '60101016', 'TEORIA GERAL DO DIREITO'),
(835, 51, '60101024', 'TEORIA GERAL DO PROCESSO'),
(836, 51, '60101032', 'TEORIA DO ESTADO'),
(837, 51, '60101040', 'HISTÓRIA DO DIREITO'),
(838, 51, '60101059', 'FILOSOFIA DO DIREITO'),
(839, 51, '60101067', 'LÓGICA JURÍDICA'),
(840, 51, '60101075', 'SOCIOLOGIA JURÍDICA'),
(841, 51, '60101083', 'ANTROPOLOGIA JURÍDICA'),
(842, 51, '60102004', 'DIREITO PÚBLICO'),
(843, 51, '60102012', 'DIREITO TRIBUTÁRIO'),
(844, 51, '60102020', 'DIREITO PENAL'),
(845, 51, '60102039', 'DIREITO PROCESSUAL PENAL'),
(846, 51, '60102047', 'DIREITO PROCESSUAL CIVIL'),
(847, 51, '60102055', 'DIREITO CONSTITUCIONAL'),
(848, 51, '60102063', 'DIREITO ADMINISTRATIVO'),
(849, 51, '60102071', 'DIREITO INTERNACIONAL PÚBLICO'),
(850, 51, '60103000', 'DIREITO PRIVADO'),
(851, 51, '60103019', 'DIREITO CIVIL'),
(852, 51, '60103027', 'DIREITO COMERCIAL'),
(853, 51, '60103035', 'DIREITO DO TRABALHO'),
(854, 51, '60103043', 'DIREITO INTERNACIONAL PRIVADO'),
(855, 51, '60104007', 'DIREITOS ESPECIAIS'),
(856, 52, '60201002', 'ADMINISTRAÇÃO DE EMPRESAS'),
(857, 52, '60201010', 'ADMINISTRAÇÃO DE PRODUÇÃO'),
(858, 52, '60201029', 'ADMINISTRAÇÃO FINANCEIRA'),
(859, 52, '60201037', 'MERCADOLOGIA'),
(860, 52, '60201045', 'NEGÓCIOS INTERNACIONAIS'),
(861, 52, '60201053', 'ADMINISTRAÇÃO DE RECURSOS HUMANOS'),
(862, 52, '60202009', 'ADMINISTRAÇÃO PÚBLICA'),
(863, 52, '60202017', 'CONTABILIDADE E FINANÇAS PÚBLICAS'),
(864, 52, '60202025', 'ORGANIZAÇÕES PÚBLICAS'),
(865, 52, '60202033', 'POLÍTICA E PLANEJAMENTO GOVERNAMENTAIS'),
(866, 52, '60202041', 'ADMINISTRAÇÃO DE PESSOAL'),
(867, 52, '60203005', 'ADMINISTRAÇÃO DE SETORES ESPECÍFICOS'),
(868, 52, '60204001', 'CIÊNCIAS CONTÁBEIS'),
(869, 54, '60301007', 'TEORIA ECONÔMICA'),
(870, 54, '60301015', 'ECONOMIA GERAL'),
(871, 54, '60301023', 'TEORIA GERAL DA ECONOMIA'),
(872, 54, '60301031', 'HISTÓRIA DO PENSAMENTO ECONÔMICO'),
(873, 54, '60301040', 'HISTÓRIA ECONÔMICA'),
(874, 54, '60301058', 'SISTEMAS ECONÔMICOS'),
(875, 54, '60302003', 'MÉTODOS QUANTITATIVOS EM ECONOMIA'),
(876, 54, '60302011', 'MÉTODOS E MODELOS MATEMÁT., ECONOMÉTRICOS E ESTATÍSTICOS'),
(877, 54, '60302020', 'ESTATÍSTICA SÓCIO-ECONÔMICA'),
(878, 54, '60302038', 'CONTABILIDADE NACIONAL'),
(879, 54, '60302046', 'ECONOMIA MATEMÁTICA'),
(880, 54, '60303000', 'ECONOMIA MONETÁRIA E FISCAL'),
(881, 54, '60303018', 'TEORIA MONETÁRIA E FINANCEIRA'),
(882, 54, '60303026', 'INSTITUIÇÕES MONETÁRIAS E FINANCEIRAS DO BRASIL'),
(883, 54, '60303034', 'FINANÇAS PÚBLICAS INTERNAS'),
(884, 54, '60303042', 'POLÍTICA FISCAL DO BRASIL'),
(885, 54, '60304006', 'CRESCIMENTO, FLUTUAÇÕES E PLANEJAMENTO ECONÔMICO'),
(886, 54, '60304014', 'CRESCIMENTO E DESENVOLVIMENTO ECONÔMICO'),
(887, 54, '60304022', 'TEORIA E POLÍTICA DE PLANEJAMENTO ECONÔMICO'),
(888, 54, '60304030', 'FLUTAÇÕES CICLÍCAS E PROJEÇÕES ECONÔMICAS'),
(889, 54, '60304049', 'INFLAÇÃO'),
(890, 54, '60305002', 'ECONOMIA INTERNACIONAL'),
(891, 54, '60305010', 'TEORIA DO COMÉRCIO INTERNACIONAL'),
(892, 54, '60305029', 'RELAÇÕES DO COMÉRCIO; POLÍT. COMERCIAL; INTEGRAÇÃO ECONÔMICA'),
(893, 54, '60305037', 'BALANÇO DE PAGAMENTO; FINANÇAS INTERNACIONAIS'),
(894, 54, '60305045', 'INVESTIMENTOS INTERNACIONAIS E AJUDA EXTERNA'),
(895, 54, '60306009', 'ECONOMIA DOS RECURSOS HUMANOS'),
(896, 54, '60306017', 'TREIN. E ALOCAÇÃO DE MÃO-DE-OBRA;OFERTA MÃO-DE-OBRA F. TRAB.'),
(897, 54, '60306025', 'MERCADO DE TRABALHO; POLÍTICA DO GOVERNO'),
(898, 54, '60306033', 'SINDICATOS, DISSÍDIOS COLET., RELAÇÕES DE EMPREGO(EMP./EMP)'),
(899, 54, '60306041', 'CAPITAL HUMANO'),
(900, 54, '60306050', 'DEMOGRAFIA ECONÔMICA'),
(901, 54, '60307005', 'ECONOMIA INDUSTRIAL'),
(902, 54, '60307013', 'ORGANIZAÇÃO INDUSTRIAL E ESTUDOS INDUSTRIAIS'),
(903, 54, '60307021', 'MUDANÇA TECNOLÓGICA'),
(904, 54, '60308001', 'ECONOMIA DO BEM-ESTAR SOCIAL'),
(905, 54, '60308010', 'ECONOMIA DOS PROGRAMAS DE BEM-ESTAR SOCIAL'),
(906, 54, '60308028', 'ECONOMIA DO CONSUMIDOR'),
(907, 54, '60309008', 'ECONOMIA REGIONAL E URBANA'),
(908, 54, '60309016', 'ECONOMIA REGIONAL'),
(909, 54, '60309024', 'ECONOMIA URBANA'),
(910, 54, '60309032', 'RENDA E TRIBUTAÇÃO'),
(911, 54, '60310006', 'ECONOMIAS AGRÁRIA E DOS RECURSOS NATURAIS'),
(912, 54, '60310014', 'ECONOMIA AGRÁRIA'),
(913, 54, '60310022', 'ECONOMIA DOS RECURSOS NATURAIS'),
(914, 55, '60401001', 'FUNDAMENTOS DE ARQUITETURA E URBANISMO'),
(915, 55, '60401010', 'HISTÓRIA DA ARQUITETURA E URBANISMO'),
(916, 55, '60401028', 'TEORIA DA ARQUITETURA'),
(917, 55, '60401036', 'HISTÓRIA DO URBANISMO'),
(918, 55, '60401044', 'TEORIA DO URBANISMO'),
(919, 55, '60402008', 'PROJETO DE ARQUITETURA E URBANISMO'),
(920, 55, '60402016', 'PLANEJAMENTO E PROJETOS DA EDIFICAÇÃO'),
(921, 55, '60402024', 'PLANEJAMENTO E PROJETO DO ESPAÇO URBANO'),
(922, 55, '60402032', 'PLANEJAMENTO E PROJETO DO EQUIPAMENTO'),
(923, 55, '60403004', 'TECNOLOGIA DE ARQUITETURA E URBANISMO'),
(924, 55, '60403012', 'ADEQUAÇÃO AMBIENTAL'),
(925, 55, '60404000', 'PAISAGISMO'),
(926, 55, '60404019', 'DESENVOLVIMENTO HISTÓRICO DO PAISAGISMO'),
(927, 55, '60404027', 'CONCEITUAÇÃO DE PAISAGISMO E METODOLOGIA DO PAISAGISMO'),
(928, 55, '60404035', 'ESTUDOS DE ORGANIZAÇÃO DO ESPAÇO EXTERIOR'),
(929, 55, '60404043', 'PROJETOS DE ESPAÇOS LIVRES URBANOS'),
(930, 57, '60501006', 'FUNDAMENTOS DO PLANEJAMENTO URBANO E REGIONAL'),
(931, 57, '60501014', 'TEORIA DO PLANEJAMENTO URBANO E REGIONAL'),
(932, 57, '60501022', 'TEORIA DA URBANIZAÇÃO'),
(933, 57, '60501030', 'POLÍTICA URBANA'),
(934, 57, '60501049', 'HISTÓRIA URBANA'),
(935, 57, '60502002', 'MÉTODOS E TÉCNICAS DO PLANEJAMENTO URBANO E REGIONAL'),
(936, 57, '60502010', 'INFORMAÇÃO, CADASTRO E MAPEAMENTO'),
(937, 57, '60502029', 'TÉCNICA DE PREVISÃO URBANA E REGIONAL'),
(938, 57, '60502037', 'TÉCNICAS DE ANÁLISE E AVALIAÇÃO URBANA E REGIONAL'),
(939, 57, '60502045', 'TÉCNICAS DE PLANEJAMENTO E PROJETO URBANOS E REGIONAIS'),
(940, 57, '60503009', 'SERVIÇOS URBANOS E REGIONAIS'),
(941, 57, '60503017', 'ADMINISTRAÇÃO MUNICIPAL E URBANA'),
(942, 57, '60503025', 'ESTUDOS DA HABITAÇÃO'),
(943, 57, '60503033', 'ASPECTOS SOCIAIS DO PLANEJAMENTO URBANO E REGIONAL'),
(944, 57, '60503041', 'ASPECTOS ECONÔMICOS DO PLANEJAMENTO URBANO E REGIONAL'),
(945, 57, '60503050', 'ASPECTOS FÍSICO-AMBIENTAIS DO PLANEJ. URBANO E REGIONAL'),
(946, 57, '60503068', 'SERVIÇOS COMUNITÁRIOS'),
(947, 57, '60503076', 'INFRA-ESTRUTURAS URBANAS E REGIONAIS'),
(948, 57, '60503084', 'TRANSPORTE E TRÁFEGO URBANO E REGIONAL'),
(949, 57, '60503092', 'LEGISLAÇÃO URBANA E REGIONAL'),
(950, 58, '60601000', 'DISTRIBUIÇÃO ESPACIAL'),
(951, 58, '60601019', 'DISTRIBUIÇÃO ESPACIAL GERAL'),
(952, 58, '60601027', 'DISTRIBUIÇÃO ESPACIAL URBANA'),
(953, 58, '60601035', 'DISTRIBUIÇÃO ESPACIAL RURAL');
INSERT INTO `tb_sub_area` (`id_sub_area`, `area_id`, `cd_sub_area`, `nm_sub_area`) VALUES
(954, 58, '60602007', 'TENDÊNCIA POPULACIONAL'),
(955, 58, '60602015', 'TENDÊNCIAS PASSADAS'),
(956, 58, '60602023', 'TAXAS E ESTIMATIVAS CORRENTES'),
(957, 58, '60602031', 'PROJEÇÕES'),
(958, 58, '60603003', 'COMPONENTES DA DINÂMICA DEMOGRÁFICA'),
(959, 58, '60603011', 'FECUNDIDADE'),
(960, 58, '60603020', 'MORTALIDADE'),
(961, 58, '60603038', 'MIGRAÇÃO'),
(962, 58, '60604000', 'NUPCIALIDADE E FAMÍLIA'),
(963, 58, '60604018', 'CASAMENTO E DIVÓRCIO'),
(964, 58, '60604026', 'FAMÍLIA E REPRODUÇÃO'),
(965, 58, '60605006', 'DEMOGRAFIA HISTÓRICA'),
(966, 58, '60605014', 'DISTRIBUIÇÃO ESPACIAL'),
(967, 58, '60605022', 'NATALIDADE, MORTALIDADE, MIGRAÇÃO'),
(968, 58, '60605049', 'MÉTODOS E TÉCNICAS DE DEMOGRAFIA HISTÓRICA'),
(969, 58, '60606002', 'POLÍTICA PÚBLICA E POPULAÇÃO'),
(970, 58, '60606010', 'POLÍTICA POPULACIONAL'),
(971, 58, '60606029', 'POLÍTICAS DE REDISTRIBUIÇÃO DE POPULAÇÃO'),
(972, 58, '60606037', 'POLÍTICAS DE PLANEJAMENTO FAMILIAR'),
(973, 58, '60607009', 'FONTES DE DADOS DEMOGRÁFICOS'),
(974, 59, '60701005', 'TEORIA DA INFORMAÇÃO'),
(975, 59, '60701013', 'TEORIA GERAL DA INFORMAÇÃO'),
(976, 59, '60701021', 'PROCESSOS DA COMUNICAÇÃO'),
(977, 59, '60701030', 'REPRESENTAÇÃO DA INFORMAÇÃO'),
(978, 59, '60702001', 'BIBLIOTECONOMIA'),
(979, 59, '60702010', 'TEORIA DA CLASSIFICAÇÃO'),
(980, 59, '60702028', 'MÉTODOS QUANTITATIVOS, BIBLIOMETRIA'),
(981, 59, '60702036', 'TÉCNICAS DE RECUPERAÇÃO DE INFORMAÇÃO'),
(982, 59, '60702044', 'PROCESSOS DE DISSEMINAÇÃO DA INFORMAÇÃO'),
(983, 59, '60703008', 'ARQUIVOLOGIA'),
(984, 59, '60703016', 'ORGANIZAÇÃO DE ARQUIVOS'),
(985, 61, '60901004', 'TEORIA DA COMUNICAÇÃO'),
(986, 61, '60902000', 'JORNALISMO E EDITORAÇÃO'),
(987, 61, '60902019', 'TEORIA E ÉTICA DO JORNALISMO'),
(988, 61, '60902027', 'ORGANIZAÇÃO EDITORIAL DE JORNAIS'),
(989, 61, '60902035', 'ORGANIZAÇÃO COMERCIAL DE JORNAIS'),
(990, 61, '60902043', 'JORNALISMO ESPECIALIZADO (COMUNITÁRIO, RURAL, EMP. CIENTIF.)'),
(991, 61, '60903007', 'RÁDIO E TELEVISÃO'),
(992, 61, '60903015', 'RADIODIFUSÃO'),
(993, 61, '60903023', 'VIDEODIFUSÃO'),
(994, 61, '60904003', 'RELAÇÕES PÚBLICAS E PROPAGANDA'),
(995, 61, '60905000', 'COMUNICAÇÃO VISUAL'),
(996, 61, '61201006', 'PROGRAMAÇÃO VISUAL'),
(997, 61, '61202002', 'DESENHO DE PRODUTO'),
(998, 62, '61000000', 'SERVIÇO SOCIAL'),
(999, 62, '61001007', 'FUNDAMENTOS DO SERVIÇO SOCIAL'),
(1000, 62, '61002003', 'SERVIÇO SOCIAL APLICADO'),
(1001, 62, '61002011', 'SERVIÇO SOCIAL DO TRABALHO'),
(1002, 62, '61002020', 'SERVIÇO SOCIAL DA EDUCAÇÃO'),
(1003, 62, '61002038', 'SERVIÇO SOCIAL DO MENOR'),
(1004, 62, '61002046', 'SERVIÇO SOCIAL DA SAÚDE'),
(1005, 62, '61002054', 'SERVIÇO SOCIAL DA HABITAÇÃO'),
(1006, 62, '61100005', 'ECONOMIA DOMÉSTICA'),
(1007, 63, '70101000', 'HISTÓRIA DA FILOSOFIA'),
(1008, 63, '70102007', 'METAFÍSICA'),
(1009, 63, '70103003', 'LÓGICA'),
(1010, 63, '70104000', 'ÉTICA'),
(1011, 63, '70105006', 'EPISTEMOLOGIA'),
(1012, 63, '70106002', 'FILOSOFIA BRASILEIRA'),
(1013, 64, '71001000', 'HISTÓRIA DA TEOLOGIA'),
(1014, 64, '71002006', 'TEOLOGIA MORAL'),
(1015, 64, '71003002', 'TEOLOGIA SISTEMÁTICA'),
(1016, 64, '71004009', 'TEOLOGIA PASTORAL'),
(1017, 65, '70201005', 'FUNDAMENTOS DA SOCIOLOGIA'),
(1018, 65, '70201013', 'TEORIA SOCIOLÓGICA'),
(1019, 65, '70201021', 'HISTÓRIA DA SOCIOLOGIA'),
(1020, 65, '70202001', 'SOCIOLOGIA DO CONHECIMENTO'),
(1021, 65, '70203008', 'SOCIOLOGIA DO DESENVOLVIMENTO'),
(1022, 65, '70204004', 'SOCIOLOGIA URBANA'),
(1023, 65, '70205000', 'SOCIOLOGIA RURAL'),
(1024, 65, '70206007', 'SOCIOLOGIA DA SAÚDE'),
(1025, 65, '70207003', 'OUTRAS SOCIOLOGIAS ESPECÍFICAS'),
(1026, 66, '70301000', 'TEORIA ANTROPOLÓGICA'),
(1027, 66, '70302006', 'ETNOLOGIA INDÍGENA'),
(1028, 66, '70303002', 'ANTROPOLOGIA URBANA'),
(1029, 66, '70304009', 'ANTROPOLOGIA RURAL'),
(1030, 66, '70305005', 'ANTROPOLOGIA DAS POPULAÇÕES AFRO-BRASILEIRAS'),
(1031, 67, '70401004', 'TEORIA E MÉTODO EM ARQUEOLOGIA'),
(1032, 67, '70402000', 'ARQUEOLOGIA PRÉ-HISTÓRICA'),
(1033, 67, '70403007', 'ARQUEOLOGIA HISTÓRICA'),
(1034, 68, '70501009', 'TEORIA E FILOSOFIA DA HISTÓRIA'),
(1035, 68, '70502005', 'HISTÓRIA ANTIGA E MEDIEVAL'),
(1036, 68, '70503001', 'HISTÓRIA MODERNA E CONTEMPORÂNEA'),
(1037, 68, '70504008', 'HISTÓRIA DA AMÉRICA'),
(1038, 68, '70504016', 'HISTÓRIA DOS ESTADOS UNIDOS'),
(1039, 68, '70504024', 'HISTÓRIA LATINO-AMERICANA'),
(1040, 68, '70505004', 'HISTÓRIA DO BRASIL'),
(1041, 68, '70505012', 'HISTÓRIA DO BRASIL COLÔNIA'),
(1042, 68, '70505020', 'HISTÓRIA DO BRASIL IMPÉRIO'),
(1043, 68, '70505039', 'HISTÓRIA DO BRASIL REPÚBLICA'),
(1044, 68, '70505047', 'HISTÓRIA REGIONAL DO BRASIL'),
(1045, 68, '70506000', 'HISTÓRIA DAS CIÊNCIAS'),
(1046, 69, '70601003', 'GEOGRAFIA HUMANA'),
(1047, 69, '70601011', 'GEOGRAFIA DA POPULAÇÃO'),
(1048, 69, '70601020', 'GEOGRAFIA AGRÁRIA'),
(1049, 69, '70601038', 'GEOGRAFIA URBANA'),
(1050, 69, '70601046', 'GEOGRAFIA ECONÔMICA'),
(1051, 69, '70601054', 'GEOGRAFIA POLÍTICA'),
(1052, 69, '70602000', 'GEOGRAFIA REGIONAL'),
(1053, 69, '70602018', 'TEORIA DO DESENVOLVIMENTO REGIONAL'),
(1054, 69, '70602026', 'REGIONALIZAÇÃO'),
(1055, 69, '70602034', 'ANÁLISE REGIONAL'),
(1056, 70, '70701008', 'FUNDAMENTOS E MEDIDAS DA PSICOLOGIA'),
(1057, 70, '70701016', 'HISTÓRIA, TEORIAS E SISTEMAS EM PSICOLOGIA'),
(1058, 70, '70701024', 'METODOLOGIA, INSTRUMENTAÇÃO E EQUIPAMENTO EM PSICOLOGIA'),
(1059, 70, '70701032', 'CONSTRUÇÃO E VALIDADE DE TESTES, ESC. E O. MEDIDAS PSICOLÓG.'),
(1060, 70, '70701040', 'TÉCN. DE PROCES. ESTÁT., MATEMÁTICO E COMPUT. EM PSICOLOGIA'),
(1061, 70, '70702004', 'PSICOLOGIA EXPERIMENTAL'),
(1062, 70, '70702012', 'PROCESSOS PERCEPTUAIS E MOTORES'),
(1063, 70, '70702020', 'PROCESSOS DE APRENDIZAGEM, MEMÓRIA E MOTIVAÇÃO'),
(1064, 70, '70702039', 'PROCESSOS COGNITIVOS E ATENCIONAIS'),
(1065, 70, '70702047', 'ESTADOS SUBJETIVOS E EMOÇÃO'),
(1066, 70, '70703000', 'PSICOLOGIA FISIOLÓGICA'),
(1067, 70, '70703019', 'NEUROLOGIA, ELETROFISIOLOGIA E COMPORTAMENTO'),
(1068, 70, '70703027', 'PROCESSOS PSICO-FISIOLÓGICOS'),
(1069, 70, '70703035', 'ESTIMULAÇÃO ELÉTRICA E COM DROGAS; COMPORTAMENTO'),
(1070, 70, '70703043', 'PSICOBIOLOGIA'),
(1071, 70, '70704007', 'PSICOLOGIA COMPARATIVA'),
(1072, 70, '70704015', 'ESTUDOS NATURALÍSTICOS DO COMPORTAMENTO ANIMAL'),
(1073, 70, '70704023', 'MECANISMOS INSTINTIVOS E PROCESSOS SOCIAIS EM ANIMAIS'),
(1074, 70, '70705003', 'PSICOLOGIA SOCIAL'),
(1075, 70, '70705011', 'RELAÇÕES INTERPESSOAIS'),
(1076, 70, '70705020', 'PROCESSOS GRUPAIS E DE COMUNICAÇÃO'),
(1077, 70, '70705038', 'PAPEIS E ESTRUTURAS SOCIAIS; INDIVÍDUO'),
(1078, 70, '70706000', 'PSICOLOGIA COGNITIVA'),
(1079, 70, '70707006', 'PSICOLOGIA DO DESENVOLVIMENTO HUMANO'),
(1080, 70, '70707014', 'PROCESSOS PERCEPTUAIS E COGNITIVOS; DESENVOLVIMENTO'),
(1081, 70, '70707022', 'DESENVOLVIMENTO SOCIAL E DA PERSONALIDADE'),
(1082, 70, '70708002', 'PSICOLOGIA DO ENSINO E DA APRENDIZAGEM'),
(1083, 70, '70708010', 'PLANEJAMENTO INSTITUCIONAL'),
(1084, 70, '70708029', 'PROGRAMAÇÃO DE CONDIÇÕES DE ENSINO'),
(1085, 70, '70708037', 'TREINAMENTO DE PESSOAL'),
(1086, 70, '70708045', 'APRENDIZAGEM E DESEMPENHO ACADÊMICOS'),
(1087, 70, '70708053', 'ENSINO E APRENDIZAGEM NA SALA DE AULA'),
(1088, 70, '70709009', 'PSICOLOGIA DO TRABALHO E ORGANIZACIONAL'),
(1089, 70, '70709017', 'ANÁLISE INSTITUCIONAL'),
(1090, 70, '70709025', 'RECRUTAMENTO E SELEÇÃO DE PESSOAL'),
(1091, 70, '70709033', 'TREINAMENTO E AVALIAÇÃO'),
(1092, 70, '70709041', 'FATORES HUMANOS NO TRABALHO'),
(1093, 70, '70709050', 'PLANEJAMENTO AMBIENTAL E COMPORTAMENTO HUMANO'),
(1094, 70, '70710007', 'TRATAMENTO E PREVENÇÃO PSICOLÓGICA'),
(1095, 70, '70710015', 'INTERVENÇÃO TERAPÊUTICA'),
(1096, 70, '70710023', 'PROGRAMAS DE ATENDIMENTO COMUNITÁRIO'),
(1097, 70, '70710031', 'TREINAMENTO E REABILITAÇÃO'),
(1098, 70, '70710040', 'DESVIOS DA CONDUTA'),
(1099, 70, '70710058', 'DISTÚRBIOS DA LINGUAGEM'),
(1100, 70, '70710066', 'DISTÚRBIOS PSICOSSOMÁTICOS'),
(1101, 71, '70801002', 'FUNDAMENTOS DA EDUCAÇÃO'),
(1102, 71, '70801010', 'FILOSOFIA DA EDUCAÇÃO'),
(1103, 71, '70801029', 'HISTÓRIA DA EDUCAÇÃO'),
(1104, 71, '70801037', 'SOCIOLOGIA DA EDUCAÇÃO'),
(1105, 71, '70801045', 'ANTROPOLOGIA EDUCACIONAL'),
(1106, 71, '70801053', 'ECONOMIA DA EDUCAÇÃO'),
(1107, 71, '70801061', 'PSICOLOGIA EDUCACIONAL'),
(1108, 71, '70802009', 'ADMINISTRAÇÃO EDUCACIONAL'),
(1109, 71, '70802017', 'ADMINISTRAÇÃO DE SISTEMAS EDUCACIONAIS'),
(1110, 71, '70802025', 'ADMINISTRAÇÃO DE UNIDADES EDUCATIVAS'),
(1111, 71, '70803005', 'PLANEJAMENTO E AVALIAÇÃO EDUCACIONAL'),
(1112, 71, '70803013', 'POLÍTICA EDUCACIONAL'),
(1113, 71, '70803021', 'PLANEJAMENTO EDUCACIONAL'),
(1114, 71, '70803030', 'AVAL. DE SISTEMAS, INST. PLANOS E PROGRAMAS EDUCACIONAIS'),
(1115, 71, '70804001', 'ENSINO-APRENDIZAGEM'),
(1116, 71, '70804010', 'TEORIAS DA INSTRUÇÃO'),
(1117, 71, '70804028', 'MÉTODOS E TÉCNICAS DE ENSINO'),
(1118, 71, '70804036', 'TECNOLOGIA EDUCACIONAL'),
(1119, 71, '70804044', 'AVALIAÇÃO DA APRENDIZAGEM'),
(1120, 71, '70805008', 'CURRÍCULO'),
(1121, 71, '70805016', 'TEORIA GERAL DE PLANEJAMENTO E DESENV. CURRICULAR'),
(1122, 71, '70805024', 'CURRÍCULOS ESPECÍFICOS PARA NÍVEIS E TIPOS DE EDUCAÇÃO'),
(1123, 71, '70806004', 'ORIENTAÇÃO E ACONSELHAMENTO'),
(1124, 71, '70806012', 'ORIENTAÇÃO EDUCACIONAL'),
(1125, 71, '70806020', 'ORIENTAÇÃO VOCACIONAL'),
(1126, 71, '70807000', 'TÓPICOS ESPECÍFICOS DE EDUCAÇÃO'),
(1127, 71, '70807019', 'EDUCAÇÃO DE ADULTOS'),
(1128, 71, '70807027', 'EDUCAÇÃO PERMANENTE'),
(1129, 71, '70807035', 'EDUCAÇÃO RURAL'),
(1130, 71, '70807043', 'EDUCAÇÃO EM PERIFERIAS URBANAS'),
(1131, 71, '70807051', 'EDUCAÇÃO ESPECIAL'),
(1132, 71, '70807060', 'EDUCAÇÃO PRÉ-ESCOLAR'),
(1133, 71, '70807078', 'ENSINO PROFISSIONALIZANTE'),
(1134, 72, '70901007', 'TEORIA POLÍTICA'),
(1135, 72, '70901015', 'TEORIA POLÍTICA CLÁSSICA'),
(1136, 72, '70901023', 'TEORIA POLÍTICA MEDIEVAL'),
(1137, 72, '70901031', 'TEORIA POLÍTICA MODERNA'),
(1138, 72, '70901040', 'TEORIA POLÍTICA CONTEMPORÂNEA'),
(1139, 72, '70902003', 'ESTADO E GOVERNO'),
(1140, 72, '70902011', 'ESTRUTURA E TRANSFORMAÇÃO DO ESTADO'),
(1141, 72, '70902020', 'SISTEMAS GOVERNAMENTAIS COMPARADOS'),
(1142, 72, '70902038', 'RELAÇÕES INTERGOVERNAMENTAIS'),
(1143, 72, '70902046', 'ESTUDOS DO PODER LOCAL'),
(1144, 72, '70902054', 'INSTITUIÇÕES GOVERNAMENTAIS ESPECÍFICAS'),
(1145, 72, '70903000', 'COMPORTAMENTO POLÍTICO'),
(1146, 72, '70903018', 'ESTUDOS ELEITORAIS E PARTIDOS POLÍTICOS'),
(1147, 72, '70903026', 'ATITUDE E IDEOLOGIAS POLÍTICAS'),
(1148, 72, '70903034', 'CONFLITOS E COALIZÕES POLÍTICAS'),
(1149, 72, '70903042', 'COMPORTAMENTO LEGISLATIVO'),
(1150, 72, '70903050', 'CLASSES SOCIAIS E GRUPOS DE INTERESSE'),
(1151, 72, '70904006', 'POLÍTICAS PÚBLICAS'),
(1152, 72, '70904014', 'ANÁLISE DO PROCESSO DECISÓRIO'),
(1153, 72, '70904022', 'ANÁLISE INSTITUCIONAL'),
(1154, 72, '70904030', 'TÉCNICAS DE ANTECIPAÇÃO'),
(1155, 72, '70905002', 'POLÍTICA INTERNACIONAL'),
(1156, 72, '70905010', 'POLÍTICA EXTERNA DO BRASIL'),
(1157, 72, '70905029', 'ORGANIZAÇÕES INTERNACIONAIS'),
(1158, 72, '70905037', 'INTEGRAÇÃO INTERNACIONAL, CONFLITO, GUERRA E PAZ'),
(1159, 72, '70905045', 'RELAÇÕES INTERNACIONAIS, BILATERAIS E MULTILATERAIS'),
(1160, 73, '80101003', 'TEORIA E ANÁLISE LINGUÍSTICA'),
(1161, 73, '80102000', 'FISIOLOGIA DA LINGUAGEM'),
(1162, 73, '80103006', 'LINGÜÍSTICA HISTÓRICA'),
(1163, 73, '80104002', 'SOCIOLINGUÍSTICA E DIALETOLOGIA'),
(1164, 73, '80105009', 'PSICOLINGUÍSTICA'),
(1165, 73, '80106005', 'LINGUÍSTICA APLICADA'),
(1166, 74, '80201008', 'LÍNGUA PORTUGUESA'),
(1167, 74, '80202004', 'LÍNGUAS ESTRANGEIRAS MODERNAS'),
(1168, 74, '80203000', 'LÍNGUAS CLÁSSICAS'),
(1169, 74, '80204007', 'LÍNGUAS INDÍGENAS'),
(1170, 74, '80205003', 'TEORIA LITERARIA'),
(1171, 74, '80206000', 'LITERATURA BRASILEIRA'),
(1172, 74, '80207006', 'OUTRAS LITERATURAS VERNÁCULAS'),
(1173, 74, '80208002', 'LITERATURAS ESTRANGEIRAS MODERNAS'),
(1174, 74, '80209009', 'LITERATURAS CLÁSSICAS'),
(1175, 74, '80210007', 'LITERATURA COMPARADA'),
(1176, 75, '80301002', 'FUNDAMENTOS E CRÍTICA DAS ARTES'),
(1177, 75, '80301010', 'TEORIA DA ARTE'),
(1178, 75, '80301029', 'HISTÓRIA DA ARTE'),
(1179, 75, '80301037', 'CRÍTICA DA ARTE'),
(1180, 75, '80302009', 'ARTES PLÁSTICAS'),
(1181, 75, '80302017', 'PINTURA'),
(1182, 75, '80302025', 'DESENHO'),
(1183, 75, '80302033', 'GRAVURA'),
(1184, 75, '80302041', 'ESCULTURA'),
(1185, 75, '80302050', 'CERÂMICA'),
(1186, 75, '80302068', 'TECELAGEM'),
(1187, 75, '80303005', 'MÚSICA'),
(1188, 75, '80303013', 'REGÊNCIA'),
(1189, 75, '80303021', 'INSTRUMENTAÇÃO MUSICAL'),
(1190, 75, '80303030', 'COMPOSIÇÃO MUSICAL'),
(1191, 75, '80303048', 'CANTO'),
(1192, 75, '80304001', 'DANÇA'),
(1193, 75, '80304010', 'EXECUÇÃO DA DANÇA'),
(1194, 75, '80304028', 'COREOGRAFIA'),
(1195, 75, '80305008', 'TEATRO'),
(1196, 75, '80305016', 'DRAMATURGIA'),
(1197, 75, '80305024', 'DIREÇÃO TEATRAL'),
(1198, 75, '80305032', 'CENOGRAFIA'),
(1199, 75, '80305040', 'INTERPRETAÇÃO TEATRAL'),
(1200, 75, '80306004', 'ÓPERA'),
(1201, 75, '80307000', 'FOTOGRAFIA'),
(1202, 75, '80308007', 'CINEMA'),
(1203, 75, '80308015', 'ADMINISTRAÇÃO E PRODUÇÃO DE FILMES'),
(1204, 75, '80308023', 'ROTEIRO E DIREÇÃO CINEMATOGRÁFICOS'),
(1205, 75, '80308031', 'TÉCNICAS DE REGISTROS E PROCESSAMENTO DE FILMES'),
(1206, 75, '80308040', 'INTERPRETAÇÃO CINEMATOGRÁFICA'),
(1207, 75, '80309003', 'ARTES DO VÍDEO'),
(1208, 75, '80310001', 'EDUCAÇÃO ARTÍSTICA'),
(1209, 76, '90191000', 'MEIO AMBIENTE E AGRÁRIAS'),
(1210, 76, '90192000', 'SOCIAIS E HUMANIDADES'),
(1211, 76, '90193000', 'ENGENHARIA/TECNOLOGIA/GESTÃO'),
(1212, 76, '90194000', 'SAÚDE E BIOLÓGICAS'),
(1213, 77, '90201000', 'ENSINO DE CIÊNCIAS E MATEMÁTICA');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_tipo_participacao`
--

CREATE TABLE IF NOT EXISTS `tb_tipo_participacao` (
  `id_tipo_participacao` int(11) NOT NULL AUTO_INCREMENT,
  `nm_tipo_participacao` varchar(25) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_tipo_participacao`),
  UNIQUE KEY `nm_tipo_participacao` (`nm_tipo_participacao`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `tb_tipo_participacao`
--

INSERT INTO `tb_tipo_participacao` (`id_tipo_participacao`, `nm_tipo_participacao`, `dt_registro`) VALUES
(1, 'Orientador', '2014-12-26 14:38:26'),
(2, 'Coorientador', '2014-12-26 14:38:26'),
(3, 'Colaborador', '2014-12-26 14:38:26'),
(4, 'Orientando', '2014-12-26 14:38:26');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_tipo_pessoa`
--

CREATE TABLE IF NOT EXISTS `tb_tipo_pessoa` (
  `id_tipo_pessoa` int(11) NOT NULL AUTO_INCREMENT,
  `nm_tipo_pessoa` varchar(25) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_tipo_pessoa`),
  UNIQUE KEY `nm_tipo_pessoa` (`nm_tipo_pessoa`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `tb_tipo_pessoa`
--

INSERT INTO `tb_tipo_pessoa` (`id_tipo_pessoa`, `nm_tipo_pessoa`, `dt_registro`) VALUES
(1, 'Servidor', '2014-12-26 14:38:25'),
(2, 'Discente', '2014-12-26 14:38:25');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_tipo_programa_institucional`
--

CREATE TABLE IF NOT EXISTS `tb_tipo_programa_institucional` (
  `id_tipo_programa_institucional` int(11) NOT NULL AUTO_INCREMENT,
  `nm_tipo_programa_institucional` varchar(45) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_tipo_programa_institucional`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `tb_tipo_programa_institucional`
--

INSERT INTO `tb_tipo_programa_institucional` (`id_tipo_programa_institucional`, `nm_tipo_programa_institucional`, `dt_registro`) VALUES
(1, 'Pesquisa', '2015-07-14 00:26:45'),
(2, 'Extensão', '2015-07-14 00:26:45');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_titulacao`
--

CREATE TABLE IF NOT EXISTS `tb_titulacao` (
  `id_titulacao` int(11) NOT NULL AUTO_INCREMENT,
  `nm_titulacao` varchar(50) NOT NULL,
  PRIMARY KEY (`id_titulacao`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Extraindo dados da tabela `tb_titulacao`
--

INSERT INTO `tb_titulacao` (`id_titulacao`, `nm_titulacao`) VALUES
(1, 'Graduação'),
(2, 'Especialização'),
(3, 'Mestrado'),
(4, 'Doutorado'),
(5, 'Pós-doutorado');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_turma`
--

CREATE TABLE IF NOT EXISTS `tb_turma` (
  `id_turma` int(11) NOT NULL AUTO_INCREMENT,
  `nr_periodo_letivo` int(2) NOT NULL,
  `nm_turma` char(1) NOT NULL,
  `nm_turno` char(1) NOT NULL,
  `curso_id` int(11) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_turma`),
  KEY `fk_turma_curso` (`curso_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `tb_turma`
--

INSERT INTO `tb_turma` (`id_turma`, `nr_periodo_letivo`, `nm_turma`, `nm_turno`, `curso_id`, `dt_registro`) VALUES
(1, 4, 'A', 'M', 1, '2014-10-31 05:27:39'),
(2, 3, 'B', 'T', 2, '2014-10-31 05:43:09');

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `tb_curso`
--
ALTER TABLE `tb_curso`
  ADD CONSTRAINT `fk_curso_coordenador` FOREIGN KEY (`coordenador_id`) REFERENCES `tb_pessoa` (`id_pessoa`),
  ADD CONSTRAINT `fk_curso_pessoa` FOREIGN KEY (`pessoa_id`) REFERENCES `tb_pessoa` (`id_pessoa`);

--
-- Limitadores para a tabela `tb_dados_bancarios`
--
ALTER TABLE `tb_dados_bancarios`
  ADD CONSTRAINT `fk_dados_bancarios_instituicao_bancaria` FOREIGN KEY (`instituicao_bancaria_id`) REFERENCES `tb_instituicao_bancaria` (`id_instituicao_bancaria`),
  ADD CONSTRAINT `fk_dados_bancarios_pessoa` FOREIGN KEY (`pessoa_id`) REFERENCES `tb_pessoa` (`id_pessoa`);

--
-- Limitadores para a tabela `tb_discente`
--
ALTER TABLE `tb_discente`
  ADD CONSTRAINT `fk_discente_pessoa` FOREIGN KEY (`pessoa_id`) REFERENCES `tb_pessoa` (`id_pessoa`),
  ADD CONSTRAINT `fk_discente_turma` FOREIGN KEY (`turma_id`) REFERENCES `tb_turma` (`id_turma`);

--
-- Limitadores para a tabela `tb_edital`
--
ALTER TABLE `tb_edital`
  ADD CONSTRAINT `fk_edital_programa_institucional` FOREIGN KEY (`programa_institucional_id`) REFERENCES `tb_programa_institucional` (`id_programa_institucional`),
  ADD CONSTRAINT `fk_pessoa_edital` FOREIGN KEY (`pessoa_id`) REFERENCES `tb_pessoa` (`id_pessoa`);

--
-- Limitadores para a tabela `tb_instituicao_financiadora`
--
ALTER TABLE `tb_instituicao_financiadora`
  ADD CONSTRAINT `fk_pessoa_instituicao_financiadora_` FOREIGN KEY (`pessoa_id`) REFERENCES `tb_pessoa` (`id_pessoa`);

--
-- Limitadores para a tabela `tb_participacao`
--
ALTER TABLE `tb_participacao`
  ADD CONSTRAINT `fk_participacao_pessoa` FOREIGN KEY (`pessoa_id`) REFERENCES `tb_pessoa` (`id_pessoa`),
  ADD CONSTRAINT `fk_participacao_projeto` FOREIGN KEY (`projeto_id`) REFERENCES `tb_projeto` (`id_projeto`),
  ADD CONSTRAINT `fk_participacao_tipo_participacao` FOREIGN KEY (`tipo_participacao_id`) REFERENCES `tb_tipo_participacao` (`id_tipo_participacao`);

--
-- Limitadores para a tabela `tb_pessoa`
--
ALTER TABLE `tb_pessoa`
  ADD CONSTRAINT `fk_pessoa_local` FOREIGN KEY (`local_id`) REFERENCES `tb_local` (`id_local`),
  ADD CONSTRAINT `fk_pessoa_tipo_pessoa` FOREIGN KEY (`tipo_pessoa_id`) REFERENCES `tb_tipo_pessoa` (`id_tipo_pessoa`);

--
-- Limitadores para a tabela `tb_programa_institucional`
--
ALTER TABLE `tb_programa_institucional`
  ADD CONSTRAINT `fk_pessoa_programa_institucional` FOREIGN KEY (`pessoa_id`) REFERENCES `tb_pessoa` (`id_pessoa`),
  ADD CONSTRAINT `fk_programa_institucional_instituicao` FOREIGN KEY (`instituicao_id`) REFERENCES `tb_instituicao_financiadora` (`id_instituicao`),
  ADD CONSTRAINT `fk_programa_institucional_tipo_programa_institucional` FOREIGN KEY (`tipo_programa_institucional_id`) REFERENCES `tb_tipo_programa_institucional` (`id_tipo_programa_institucional`);

--
-- Limitadores para a tabela `tb_projeto`
--
ALTER TABLE `tb_projeto`
  ADD CONSTRAINT `fk_projeto_edital` FOREIGN KEY (`edital_id`) REFERENCES `tb_edital` (`id_edital`),
  ADD CONSTRAINT `fk_projeto_local` FOREIGN KEY (`local_id`) REFERENCES `tb_local` (`id_local`);

--
-- Limitadores para a tabela `tb_recurso_instituicao_financiadora`
--
ALTER TABLE `tb_recurso_instituicao_financiadora`
  ADD CONSTRAINT `fk_recurso_if_pessoa` FOREIGN KEY (`pessoa_id`) REFERENCES `tb_pessoa` (`id_pessoa`),
  ADD CONSTRAINT `fk_recurso_instituicao_financiadora` FOREIGN KEY (`instituicao_financiadora_id`) REFERENCES `tb_instituicao_financiadora` (`id_instituicao`);

--
-- Limitadores para a tabela `tb_recurso_programa_institucional`
--
ALTER TABLE `tb_recurso_programa_institucional`
  ADD CONSTRAINT `fk_recurso_pi_if` FOREIGN KEY (`recurso_instituicao_financiadora_id`) REFERENCES `tb_recurso_instituicao_financiadora` (`id_recurso_if`),
  ADD CONSTRAINT `fk_recurso_pi_pessoa` FOREIGN KEY (`pessoa_id`) REFERENCES `tb_pessoa` (`id_pessoa`),
  ADD CONSTRAINT `fk_recurso_programa_institucional` FOREIGN KEY (`programa_institucional_id`) REFERENCES `tb_programa_institucional` (`id_programa_institucional`);

--
-- Limitadores para a tabela `tb_servidor`
--
ALTER TABLE `tb_servidor`
  ADD CONSTRAINT `fk_docente_pessoa` FOREIGN KEY (`pessoa_id`) REFERENCES `tb_pessoa` (`id_pessoa`),
  ADD CONSTRAINT `fk_servidor_cargo_servidor` FOREIGN KEY (`cargo_servidor_id`) REFERENCES `tb_cargo_servidor` (`id_cargo_servidor`);

--
-- Limitadores para a tabela `tb_turma`
--
ALTER TABLE `tb_turma`
  ADD CONSTRAINT `fk_turma_curso` FOREIGN KEY (`curso_id`) REFERENCES `tb_curso` (`id_curso`);

-- 
-- Alteração: 03/08/2015
--

--
-- Estrutura da tabela `tb_chat`
--

CREATE TABLE IF NOT EXISTS `tb_chat` (
  `id_chat` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nm_nome` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_chat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_chat_line`
--

CREATE TABLE IF NOT EXISTS `tb_chat_line` (
  `id_chat_line` int(11) NOT NULL AUTO_INCREMENT,
  `chat_id` int(11) unsigned NOT NULL,
  `pessoa_id` int(11) NOT NULL,
  `nm_mensagem` TEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_chat_line`),
  KEY `chat_id` (`chat_id`),
  KEY `pessoa_id` (`pessoa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_chat_line_read`
--

CREATE TABLE IF NOT EXISTS `tb_chat_line_read` (
  `chat_line_id` int(11) NOT NULL,
  `pessoa_id` int(11) NOT NULL,
  `fl_visualizado` tinyint(4) NOT NULL,
  `dt_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `chat_line_id` (`chat_line_id`),
  KEY `pessoa_id` (`pessoa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_chat_pessoas`
--

CREATE TABLE IF NOT EXISTS `tb_chat_pessoas` (
  `chat_id` int(10) unsigned NOT NULL,
  `pessoa_id` int(10) NOT NULL,
  KEY `chat_id` (`chat_id`),
  KEY `pessoa_id` (`pessoa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `tb_chat_line`
--
ALTER TABLE `tb_chat_line`
  ADD CONSTRAINT `fk_chat_line_chat` FOREIGN KEY (`chat_id`) REFERENCES `tb_chat` (`id_chat`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_chat_line_pessoa` FOREIGN KEY (`pessoa_id`) REFERENCES `tb_pessoa` (`id_pessoa`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `tb_chat_pessoas`
--
ALTER TABLE `tb_chat_pessoas`
  ADD CONSTRAINT `fk_chat_pessoas_pessoa` FOREIGN KEY (`pessoa_id`) REFERENCES `tb_pessoa` (`id_pessoa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_chat_pessoas_chat` FOREIGN KEY (`chat_id`) REFERENCES `tb_chat` (`id_chat`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `tb_chat_line_read`
--
ALTER TABLE `tb_chat_line_read`
  ADD CONSTRAINT `fk_chat_line_read_pessoa` FOREIGN KEY (`pessoa_id`) REFERENCES `tb_pessoa` (`id_pessoa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_chat_line_read_chat` FOREIGN KEY (`chat_line_id`) REFERENCES `tb_chat_line` (`id_chat_line`) ON DELETE NO ACTION ON UPDATE NO ACTION;

-- 
-- Alteração: 17/08/2015
--

--
-- Adicionando referência de Area ao Projeto
--
ALTER TABLE `tb_projeto` 
  ADD `area_id` INT NOT NULL AFTER `local_id` ,
  ADD INDEX ( `area_id` ) ;

ALTER TABLE `tb_projeto` 
ADD CONSTRAINT `fk_projeto_area` 
  FOREIGN KEY ( `area_id` ) 
  REFERENCES `qmanager`.`tb_area` (`id_area`) 
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT ;

--
-- Adicionando referência de Grande Area ao Projeto
--
ALTER TABLE `tb_projeto` 
  ADD `grande_area_id` INT NOT NULL AFTER `local_id` ,
  ADD INDEX ( `grande_area_id` ) ;

ALTER TABLE `tb_projeto` 
  ADD CONSTRAINT `fk_projeto_grande_area` 
    FOREIGN KEY ( `grande_area_id` ) 
    REFERENCES `qmanager`.`tb_grande_area` (`id_grande_area`) 
      ON DELETE RESTRICT 
      ON UPDATE RESTRICT ;

--
-- Adicionando referência entre cadastrador e Projeto
--
ALTER TABLE `tb_projeto` 
  ADD `cadastrador_id` INT NOT NULL AFTER `area_id` ,
  ADD INDEX ( `cadastrador_id` ) ;

ALTER TABLE `tb_projeto` 
  ADD CONSTRAINT `fk_projeto_cadastrador` 
    FOREIGN KEY ( `cadastrador_id` ) 
    REFERENCES `qmanager`.`tb_pessoa` (`id_pessoa`) 
      ON DELETE RESTRICT 
      ON UPDATE RESTRICT ;

-- 
-- Alteração: 26/08/2015
--

--
-- Adicionando flag para bolsista na Participacao.
--
ALTER TABLE  `tb_participacao` 
  ADD  `fl_bolsista` TINYINT NOT NULL AFTER  `dt_fim` ;

