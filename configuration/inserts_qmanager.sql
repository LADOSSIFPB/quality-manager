
--
-- Banco de dados: `qmanager`
--

--
-- Fazendo dump de dados para tabela `tb_tipo_pessoa`
--

INSERT INTO `tb_tipo_pessoa` (`id_tipo_pessoa`, `nm_tipo_pessoa`) VALUES
(1, 'Servidor'),
(2, 'Discente');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_cargo_servidor`
--

INSERT INTO `tb_cargo_servidor` (`id_cargo_servidor`, `nm_cargo_servidor`) VALUES
(1, 'Gestor'),
(2, 'Coordenador'),
(3, 'Professor'),
(4, 'Técnico Administrativo');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_area_tematica_extensao`
--

INSERT INTO `tb_area_tematica_extensao` (`id_area_tematica`, `nm_area_tematica`, `nm_sigla`) VALUES
(1, 'Comunicação', 'COM'),
(2, 'Cultura', 'CUL'),
(3, 'Direitos Humanos', 'DHJ'),
(4, 'Educação', 'EDU'),
(5, 'Meio ambiente', 'MAM'),
(6, 'Saúde', 'SAU'),
(7, 'Tecnologia', 'TEC'),
(8, 'Trabalho', 'TRA');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_atividade_extensao`
--

INSERT INTO `tb_atividade_extensao` (`id_atividade_extensao`, `nm_atividade_extensao`, `nm_sigla`) VALUES
(1, 'Programa de Extensão', 'PRO'),
(2, 'Projeto de Extensão', 'PE'),
(3, 'Curso de Extensão', 'CE'),
(4, 'Evento de Extensao', 'EE'),
(5, 'Prestação de Serviço', 'PS'),
(6, 'Empreendendorismo', 'EP'),
(7, 'Visitas Técnicas', 'VT'),
(8, 'Acompanhamento de Egressos', 'AE');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_local`
--

INSERT INTO `tb_local` (`id_local`, `nm_local`) VALUES
(1, 'Reitoria'),
(2, 'Campus João Pessoa'),
(3, 'Campus Cajazeiras'),
(4, 'Campus Campina Grande'),
(5, 'Campus Sousa'),
(6, 'Campus Cabedelo'),
(7, 'Campus Picuí'),
(8, 'Campus Princesa Isabel'),
(9, 'Campus Patos'),
(10, 'Campus Monteiro'),
(11, 'Centro de Referencia em Pesca e Navegação Marítima');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_instituicao_bancaria`
--

INSERT INTO `tb_instituicao_bancaria` (`id_instituicao_bancaria`, `nm_banco`, `nr_cnpj`) VALUES
(1, 'Caixa Econômica', '360305000104'),
(2, 'Banco do Brasil SA', '00000000000191');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_pessoa`
--

INSERT INTO `tb_pessoa` (`id_pessoa`, `nm_pessoa`, `nr_cpf`, `nr_matricula`, `nm_endereco`, `nm_cep`, `nm_telefone`, `nm_email`, `nm_senha`, `tipo_pessoa_id`, `local_id`) VALUES
(1, 'Márcia Maria Costa Gomes', '12345678910', '12345678901', 'Rua Presidente Tancredo Neves. Bairro: Jardim Sorrilândia', '58015430', '8332083004', 'marcia.gomes@gmail.com', '13934C744DA605867234E02A5E4CC01F37CF9043546456CAA213133D7E213BD3', 1, 4),
(2, 'Eri Jonhson Oliveira da Silva', '12345678921', '20111003145', 'Rua Muniz Barreto de Lima, 92', '58487564', '8399795879', 'erijonhson.os@gmail.com', 'E943BBABCB6A41061EA11CABBE8CF5445202F35C255607795289D89737045EB7', 2, 4),
(3, 'Rhavy Maia Guedes', '09876534523', '32456798', 'Rua Capitão Domingos Cariris', '58432562', '8396432156', 'rhavy.maia@gmail.com', '5D900743AE5F60D4CB174D1E8D919D9A4D442DB17118029B5B8F548D726EB823', 1, 4),
(4, 'Felipe Nascimento Souza', '56781234910', '20111004531', 'Rua Argentina, 34', '58562432', '8396215643', 'felipe_nsousa@hotmail.com', '023FA1ACB18623491AD8376A99498D1E1DAEE4E47F87DFB62ACB2938FB659A60', 2, 4),
(5, 'Elaine Cristina Juvino', '25018495023', '201420042112', 'Rua Holanda, 12', '58102822', '8396324015', 'elaine_cristina@hotmail.com', '5759432840F193E7ACFC85C4D6ECCAB8F5B82817E98DB9DA2C9A47D525034683', 1, 4),
(6, 'Adriana Lemos', '45631901315', '201230012345', 'Rua Ivanildo Terceiro, 12', '58174245', '8391048332', 'adriana_lemos@gmail.com', '4D6C5939E664A7CF5214443DFDC82D0FF6702E0D238D02838AC50F91A2399742', 1, 4),
(7, 'Divanira Ferreira Maia', '84759204958', '201210032731', 'Rua Brasil, 13', '58324562', '8388341023', 'divanira_ferreira@gmail.com', '4618BC625A54D51A268B260B9494DDDE55BE2F0CF9D13C45C959DCCDB03D0883', 1, 4);

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_instituicao_financiadora`
--

INSERT INTO `tb_instituicao_financiadora` (`id_instituicao`, `nr_cnpj`, `nm_instituicao`, `nm_sigla`, `pessoa_id`) VALUES
(1, '10783898000175', 'Instituto Federal de Educação, Ciência e Tecnologia da Paraíba', 'IFPB', 1);

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_recurso_instituicao_financiadora`
--
INSERT INTO `tb_recurso_instituicao_financiadora` (`id_recurso_if`, `instituicao_financiadora_id`, `vl_orcamento`, `dt_validade_inicial`, `dt_validade_final`) VALUES 
('1', '1', '85342.97', '2015-01-05', '2015-12-30');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_programa_institucional`
--

INSERT INTO `tb_programa_institucional` (`id_programa_institucional`, `nm_programa_institucional`, `nm_sigla`, `pessoa_id`, `instituicao_id`) VALUES
(1, 'Programa Institucional de Bolsas de Iniciação Científica para o Ensino Médio', 'PIBIC-EM', 1, 1),
(2, 'Programa Institucional de Bolsas de Extensão', 'PROBEXT', 1, 1);

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_recurso_programa_institucional`
--

INSERT INTO `tb_recurso_programa_institucional` (`id_recurso_pi`, `programa_institucional_id`, `vl_orcamento`, `dt_validade_inicial`, `dt_validade_final`, `recurso_instituicao_financiadora_id`) VALUES 
('1', '1', '3521.68', '2015-07-06', '2015-12-25', '1');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_edital`
--

INSERT INTO `tb_edital` (`id_edital`, `ar_edital`, `nr_edital`, `nm_descricao`, `nr_ano`, `dt_inicio_inscricoes`, `dt_fim_inscricoes`, `dt_relatorio_parcial`, `dt_relatorio_final`, `nr_vagas`, `vl_bolsa_discente`, `vl_bolsa_docente`, `tp_edital`, `programa_institucional_id`, `pessoa_id`) VALUES
(1, '/home/slave4/edital1', 11, 'Este é o 1 edital', 2014, '2014-10-01', '2014-11-30', '2015-03-11', '2015-09-30', 15, 100.0, 500.0, 'P', 1, 1),
(2, '/home/slave4/edital2', 12, 'Este é o 2 edital'2014, '2014-11-01', '2014-12-30', '2015-04-11', '2015-10-30', 20, 200.0, 600.0, 'E', 1, 1);

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_curso`
--

INSERT INTO `tb_curso` (`id_curso`, `nm_curso`, `coordenador_id`, `pessoa_id`) VALUES
(1, 'Técnico em Informática Integrado ao Ensino Médio', 5, 1),
(2, 'Técnico em Mineração Integrado ao Ensino Médio', 6, 1),
(3, 'Técnico em Petróleo e Gás Integrado ao Ensino Médio', 7, 1);

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_turma`
--

INSERT INTO `tb_turma` (`id_turma`, `nr_periodo_letivo`, `nm_turma`, `nm_turno`, `dt_registro`, `curso_id`) VALUES
 (1, 4, 'A', 'M', '2014-10-31 02:27:39', 1),
 (2, 3, 'B' ,'T', '2014-10-31 02:43:09', 2);

--
-- Fazendo dump de dados para tabela `tb_discente`
--

INSERT INTO `tb_discente` (`pessoa_id`, `turma_id`) VALUES
(2, 1),
(4, 1);

-- --------------------------------------------------------

--
-- Extraindo dados da tabela `tb_grau_instrucao`
--

INSERT INTO `tb_titulacao` (`id_titulacao`, `nm_titulacao`) VALUES
(1, 'Graduação'),
(2, 'Especialização'),
(3, 'Mestrado'),
(4, 'Doutorado'),
(5, 'Pós-doutorado');

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

--
-- Fazendo dump de dados para tabela `tb_servidor`
--

INSERT INTO `tb_servidor` (`pessoa_id`, `id_titulacao`, `cargo_servidor_id`, `id_departamento`) VALUES
(1, 3, 1, 14), -- Márcia Gomes --
(5, 3, 2, 19), -- Elaine Cristina --
(6, 3, 2, 17), -- Adriana Lemos --  
(7, 3, 2, 18), -- Divanira Ferreira --
(3, 3, 3, 19); -- Rhavy Maia --

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_dados_bancarios`
--

INSERT INTO `tb_dados_bancarios` (`pessoa_id`, `instituicao_bancaria_id`, `nr_operacao`, `nr_conta`) VALUES
(1, 1, '013', '123456789012345'),
(2, 1, '013', '123456789054321'),
(3, 2, NULL, '905432112345678'),
(4, 1, '013', '345126789054321');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_projeto`
--

INSERT INTO `tb_projeto` (`id_projeto`, `nm_projeto`, `dt_inicio_projeto`, `dt_fim_projeto`, `nr_processo`, `tp_projeto`, `vl_orcamento`, `dt_registro`, `edital_id`, `local_id`) VALUES
(1, 'Uma ferramenta de apoio à gestão de projetos para coordenadores de pesquisa no IFPB', '2013-10-14', '2015-08-28', '1234567890123456780', 'P', 1500, '2013-10-31 02:48:15', 1, 4),
(2, 'Avaliando o comportamento de cargas de trabalho e aplicações SAAS/WEB para realização de estudos de engenharia de infraestrutura de TI', '2013-05-02', '2014-06-03', '2345678901234567801', 'E', 2000, '2013-10-31 02:48:15', 2, 4);

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_tipo_participacao`
--

INSERT INTO `tb_tipo_participacao` (`id_tipo_participacao`, `nm_tipo_participacao`) VALUES
(1, 'Orientador'),
(2, 'Coorientador'),
(3, 'Colaborador'),
(4, 'Orientando');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_participacao`
--

INSERT INTO `tb_participacao` (`id_participacao`, `pessoa_id`, `projeto_id`, `dt_inicio`, `dt_fim`, `vl_bolsa`, `tipo_participacao_id`) VALUES
(1, 2, 1, '2013-10-28', '2015-08-28', 500, 4),
(2, 3, 1, '2013-11-05', NULL, 0.0, 1),
(3, 4, 1, '2013-11-18', NULL, 100, 4),
(4, 5, 2, '2013-12-05', NULL, 0.0, 1);

--
-- Alteração: 23/02/2015
-- Extraindo dados da tabela `tb_campus_institucional`
--

INSERT INTO `tb_campus_institucional` (`id_campus_institucional`, `nm_campus_institucional`) VALUES
(1, 'Cabedelo'),
(2, 'Cajazeiras'),
(3, 'Campina Grande'),
(4, 'Guarabira'),
(5, 'João Pessoa'),
(6, 'Monteiro'),
(7, 'Patos'),
(8, 'Princesa Isabel'),
(9, 'Picuí'),
(10, 'Sousa'),
(11, 'Reitoria');

--
-- Alteração: 30/06/2015
-- Extraindo dados da tabela `tb_tipo_projeto`
--

INSERT INTO `tb_tipo_projeto` (`id_tipo_projeto`, `nm_tipo_projeto`) VALUES
(1, 'PESQUISA'),
(2, 'EXTENSÃO');
