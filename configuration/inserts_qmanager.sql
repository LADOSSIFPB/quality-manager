-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: 31/10/2014 às 00:52
-- Versão do servidor: 5.5.40-0ubuntu0.14.04.1
-- Versão do PHP: 5.5.9-1ubuntu4.4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

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

INSERT INTO `tb_instituicao_financiadora` (`id_instituicao`, `nr_cnpj`, `nm_instituicao`, `nm_sigla`, `vl_orcamento`, `pessoa_id`) VALUES
(1, '10783898000175', 'Instituto Federal de Educação, Ciência e Tecnologia da Paraíba', 'IFPB', 1000000, 1);

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_programa_institucional`
--

INSERT INTO `tb_programa_institucional` (`id_programa_institucional`, `nm_programa_institucional`, `nm_sigla`, `vl_orcamento`, `pessoa_id`, `instituicao_id`) VALUES
(1, 'Programa Institucional de Bolsas de Iniciação Científica para o Ensino Médio', 'PIBIC-EM', 10000, 1, 1),
(2, 'Programa Institucional de Bolsas de Extensão', 'PROBEXT', 9000, 1, 1);

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_edital`
--

INSERT INTO `tb_edital` (`id_edital`, `ar_edital`, `nr_edital`, `nr_ano`, `dt_inicio_inscricoes`, `dt_fim_inscricoes`, `dt_relatorio_parcial`, `dt_relatorio_final`, `nr_vagas`, `vl_bolsa_discente`, `vl_bolsa_docente`, `tp_edital`, `programa_institucional_id`, `pessoa_id`) VALUES
(1, '/home/slave4/edital1', 11, 2014, '2014-10-01', '2014-11-30', '2015-03-11', '2015-09-30', 15, 100.0, 500.0, 'P', 1, 1),
(2, '/home/slave4/edital2', 12, 2014, '2014-11-01', '2014-12-30', '2015-04-11', '2015-10-30', 20, 200.0, 600.0, 'E', 1, 1);

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
-- Fazendo dump de dados para tabela `tb_servidor`
--

INSERT INTO `tb_servidor` (`pessoa_id`, `nm_titulacao`, `cargo_servidor_id`) VALUES
(1, 'Mestre', 1),
(5, 'Mestre', 2),
(6, 'Mestre', 2),
(7, 'Mestre', 2),
(3, 'Mestre', 3);

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

INSERT INTO `tb_projeto` (`id_projeto`, `nm_projeto`, `dt_inicio_projeto`, `dt_fim_projeto`, `ar_projeto_submetido`, `ar_relatorio_parcial`, `ar_relatorio_final`, `nr_processo`, `tp_projeto`, `vl_orcamento`, `dt_registro`, `edital_id`, `local_id`) VALUES
(1, 'Uma ferramenta de apoio à gestão de projetos para coordenadores de pesquisa no IFPB', '2013-10-14', '2015-08-28', '/opt/slave4/projeto1/arquivo', '/opt/slave4/projeto1/parcial', '/opt/slave4/projeto1/final', '1234567890123456780', 'P', 1500, '2013-10-31 02:48:15', 1, 4),
(2, 'Avaliando o comportamento de cargas de trabalho e aplicações SAAS/WEB para realização de estudos de engenharia de infraestrutura de TI', '2013-05-02', '2014-06-03', '/opt/slave4/projeto2/arquivo', '/opt/slave4/projeto2/parcial', '/opt/slave4/projeto2/final', '2345678901234567801', 'E', 2000, '2013-10-31 02:48:15', 2, 4);

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
-- Extraindo dados da tabela `tb_grau_instrucao`
--

INSERT INTO `tb_titulacao` (`id_titulacao`, `nm_titulacao`) VALUES
(1, 'Graduação'),
(2, 'Especialização'),
(3, 'Mestrado'),
(4, 'Doutorado'),
(5, 'Pós-doutorado');


--
-- Extraindo dados da tabela `tb_pessoa_habilitada`
--

INSERT INTO `tb_pessoa_habilitada` (`id_pessoa_habilitada`, `nm_pessoa_habilitada`, `nr_siape`, 
`id_campus_institucional`, `id_departamento`, `id_titulacao`, `nm_email`) 
VALUES
(1, 'adeilma carneiro bastos', 0, 3, 17, 3, ''),
(2, 'adenilson targino de araujo junior', 0, 3, 13, 3, ''),
(3, 'aderdilania iane barbosa de azevedo', 0, 3, 13, 3, 'ianeazevedo.bio@gmail.com'),
(4, 'adriana araujo costeira de andrade', 0, 3, 13, 3, 'adrianacosteira@ifpb.edu.br'),
(5, 'adriana lemos porto', 0, 3, 17, 3, 'adrianalemosporto@hotmail.com'),
(6, 'adriana sales barros', 0, 3, 13, 3, ''),
(7, 'aglailson gladson cabral de oliveira', 0, 3, 13, 1, 'aglailson.oliveira@ifb.edu.br'),
(8, 'agnes campello araujo braz', 0, 3, 12, 1, 'agnescampello@gmail.com'),
(9, 'alan carlos monteiro junior', 0, 3, 13, 3, 'alanmonteirojr@gmail.com'),
(10, 'alexandre sales vasconcelos', 0, 3, 19, 3, 'alexandreufcg@gmail.com'),
(11, 'alionalia sharlon maciel batista ramos lopes', 0, 3, 13, 3, ''),
(12, 'allan patrick de lucena costa', 0, 3, 13, 3, ''),
(13, 'amanda christinne nascimento marques', 0, 3, 13, 1, ''),
(14, 'ana claudia mousinho ferreira', 0, 3, 17, 3, ''),
(15, 'ana cristina alves de oliveira', 0, 3, 19, 3, 'cristina.alves@gmail.com'),
(16, 'ana paula sousa silva', 0, 3, 13, 3, ''),
(17, 'anderson fabiano batista ferreira da costa', 0, 3, 19, 4, 'anderson@ifpb.edu.br'),
(18, 'angelo marcio formiga de almeida', 0, 3, 22, 1, ''),
(19, 'anna giovanna rocha bezerra', 0, 3, 13, 3, 'annagiovanna@gmail.com'),
(20, 'annaxsuel araujo de lima', 0, 3, 23, 1, 'annaxgt@yahoo.com.br'),
(21, 'anne karine de queiroz', 0, 3, 13, 3, 'anne.midias@gmail.com'),
(22, 'antonio jesus souza melo neto', 0, 3, 13, 1, 'antoniojesuscg@hotmail.com'),
(23, 'antônio leite de andrade', 0, 3, 17, 1, 'antonioleite@consulmina.com'),
(24, 'aridan lira leite', 0, 3, 19, 1, ''),
(25, 'arllington rodrigues ferreira da costa', 0, 3, 19, 1, ''),
(26, 'augusto csar dias de araujo', 0, 3, 13, 3, 'acdaraujo@gmail.com'),
(27, 'bruno de brito leite', 0, 3, 19, 3, 'brunodebrito@gmail.com'),
(28, 'bruno formiga guimarães', 0, 3, 19, 3, 'bruno.formiga.guimaraes@hotmail.com'),
(29, 'bruno jacome cavalcanti', 0, 3, 19, 1, 'brjcweb@yahoo.com.br'),
(30, 'carlo reillen lima martins', 0, 3, 12, 1, 'reillen@gmail.com'),
(31, 'carlos alex souza da silva', 0, 3, 13, 4, 'calex@fisica.ufce.br'),
(32, 'carlos david de carvalho lobao', 0, 3, 13, 3, 'davidlobaoifpb@gmail.com'),
(33, 'carlos renato paz', 0, 3, 13, 2, 'renatopaz@hotmail.com'),
(34, 'celso de araujo', 0, 3, 13, 2, 'celso.araujo@ifpb.edu.br'),
(35, 'cesar rocha vasconcelos', 0, 3, 19, 3, 'cesarrocha@ifpb.edu.br'),
(36, 'cicero da silva pereira', 0, 3, 13, 3, 'cspmat@gmail.com'),
(37, 'clarice oliveira da rocha', 0, 3, 12, 3, 'clariceoliveirarocha@gmail.com'),
(38, 'cláudio sebastião vasconcelos da cunha cavalcanti', 0, 3, 19, 1, 'claudiosvcc@gmail.com'),
(39, 'cristiane vieira do nascimento', 0, 3, 13, 3, 'crisvn@gmail.com'),
(40, 'cíntia de sousa bezerra', 0, 3, 12, 4, 'cintiasbezerra@gmail.com'),
(41, 'daniella dias cavalcante da silva', 0, 3, 22, 4, 'daniella.silva@ifpb.edu.br'),
(42, 'danielly vieira de lucena', 0, 3, 18, 3, 'daniellymateriais@yahoo.com.br'),
(43, 'danilo rangel arruda leite', 0, 3, 19, 2, 'danilorangelleite@live.com'),
(44, 'david candeia medeiros maia', 0, 3, 22, 3, 'davcandeia@gmail.com'),
(45, 'debora janaina ribeiro e silva', 0, 3, 23, 3, 'debora_jr@yahoo.com.br'),
(46, 'denis barros barbosa', 0, 3, 13, 4, 'denis.fisica@yahoo.com.br'),
(47, 'diego fernandes sales', 0, 3, 13, 1, ''),
(48, 'divanira ferreira maia', 0, 3, 13, 4, 'divaniram@yahoo.com.br'),
(49, 'djalma toscano de oliveira neto', 0, 3, 13, 3, ''),
(50, 'douglas antonio bezerra ramos', 0, 3, 13, 2, 'dabramos@yahoo.com.br'),
(51, 'dwight rodrigues soares', 0, 3, 17, 5, ''),
(52, 'débora cristina santos', 0, 3, 13, 2, 'debyncris@hotmail.com'),
(53, 'edilane rodrigues bento moreira', 0, 3, 13, 4, 'edilane_rbento@hotmail.com'),
(54, 'edison fernando da silva lima', 0, 3, 13, 2, 'proffernandolima@gmail.com'),
(55, 'edmilson dantas da silva filho', 0, 3, 13, 4, 'edmsegundo@hotmail.com'),
(56, 'edmundo dantas filho', 0, 3, 13, 3, 'edmundofisica@hotmail.com'),
(57, 'elaine cristina juvino de araujo', 0, 3, 19, 3, 'elaine@gmail.com'),
(58, 'elias antonio freire', 0, 3, 19, 3, 'eafreire@ibest.com.br'),
(59, 'ellis regina ferreira dos santos', 0, 3, 13, 4, 'ellisrf@yahoo.com.br'),
(60, 'emerson de souza lima', 0, 3, 19, 2, 'emersondesouzalima@gmail.com'),
(61, 'eriverton da silva rodrigues', 0, 3, 13, 2, 'erivertonr@hotmail.com'),
(62, 'ewerton romulo silva castro', 0, 3, 13, 3, 'ewerton.castro@ifpb.edu.br'),
(63, 'fabiana bezerra marinho', 0, 3, 13, 3, 'fabiana_mbr@yahoo.com.br'),
(64, 'fabio silveira martins de oliveira', 0, 3, 21, 3, 'professorfabiosilveira@gmail.com'),
(65, 'fernanda maria almeida floriano', 0, 3, 13, 2, 'fer_uk@yahoo.co.uk'),
(66, 'francicleide gonçalves de sousa', 0, 3, 13, 3, 'francicleide.sousa@ifpb.edu.br'),
(67, 'francilda araujo inacio', 0, 3, 13, 4, 'araujo.francilda@gmail.com'),
(68, 'francisco dantas nobre neto', 0, 3, 13, 3, ''),
(69, 'francisco de assis da silveira gonzaga', 0, 3, 17, 3, 'franciscoagonzaga@hotmail.com'),
(70, 'francisco de assis souza', 0, 3, 19, 3, 'franciscosouza.ifpb@gamil.com'),
(71, 'francisco henrique duarte filho', 0, 3, 13, 1, 'henrique.bj@ibest.com.br'),
(72, 'francisco jucivônio félix de sousa', 0, 3, 13, 2, 'juc.fe@uol.com.br'),
(73, 'frankslale fabian diniz de andrade meira', 0, 3, 21, 4, 'frank_meira@yahoo.com.br'),
(74, 'geane vidal de negreiros lima', 0, 3, 12, 1, 'gvnegreiros@gmail.com'),
(75, 'george sobral silveira', 0, 3, 13, 4, 'georgesilveira@gmail.com'),
(76, 'georgina karla de freitas serres', 0, 3, 19, 4, 'geomaciel@gmail.com'),
(77, 'geraldo da mota dantas', 0, 3, 13, 3, ''),
(78, 'germana correia de oliveira', 0, 3, 13, 3, 'germanacorreia@gmail.com'),
(79, 'germana silva de oliveira', 0, 3, 13, 3, 'germanasom@hotmail.com'),
(80, 'gilmara de melo ferreira', 0, 3, 13, 1, ''),
(81, 'gilmara gomes meira', 0, 3, 13, 3, 'gilmarameira@yahoo.com.br'),
(82, 'gisele caldas de araujo cunha', 0, 3, 17, 2, ''),
(83, 'glayds richeles araujo veiga', 0, 3, 13, 1, 'glaydshistoria@hotmail.com'),
(84, 'helder gustavo pequeno dos reis', 0, 3, 13, 2, 'professorhelder@ig.com.br'),
(85, 'henrique do nascimento cunha', 0, 3, 13, 3, 'herinque.cunha@ifpb.edu.br'),
(86, 'herllange chaves de brito', 0, 3, 13, 1, 'herllange@ig.com.br'),
(87, 'iana daya cavalcante facundo passos', 0, 3, 22, 3, 'daya@terra.com.br'),
(88, 'ianna maria sodre ferreira de sousa', 0, 3, 19, 3, 'ianna@ifpb.edu.br'),
(89, 'igor barbosa da costa', 0, 3, 13, 3, 'igor.costa@ifpb.edu.br'),
(90, 'iliana de oliveira guimarães', 0, 3, 18, 3, 'ilianaguimaraes@hotmail.com'),
(91, 'iremar alves madureira', 0, 3, 13, 1, 'iremar@bol.com.br'),
(92, 'isa fernandes de souza', 0, 3, 13, 3, 'souza-isa@hotmail.com'),
(93, 'ivanise souto maior', 0, 3, 17, 1, 'ismaior@ig.com.br'),
(94, 'jair stefanini pereira de ataide', 0, 3, 19, 1, ''),
(95, 'jairo carlos de oliveira quintans', 0, 3, 13, 2, 'jairocarlosq@hotmail.com'),
(96, 'jamylle rebouças ouverney king', 0, 3, 17, 3, 'katze@terra.com.br'),
(97, 'jayson dagoberto dos santos carneiro', 0, 3, 13, 1, 'jaysonguimaraes@hotmail.com'),
(98, 'jean luis gomes de medeiros', 0, 3, 13, 3, 'jeandemedeiros@gmail.com'),
(99, 'jeronimo silva rocha', 0, 3, 19, 4, 'jeronimo.rocha@ifpb.edu.br'),
(100, 'joab dos santos silva', 0, 3, 13, 3, 'joab_professor@hotmail.com'),
(101, 'joao edson rufino', 0, 3, 12, 3, 'joaorufinojoao@ig.com.br'),
(102, 'joão galdino de lucena neto', 0, 3, 13, 1, ''),
(103, 'joão paulo da silva', 0, 3, 13, 3, 'jps.silva@yahoo.com.br'),
(104, 'jonathas jerônimo barbosa', 0, 3, 13, 1, 'jonathasbarbosa@gmail.com'),
(105, 'jorge luis de gois gonçalves', 0, 3, 17, 4, 'jlggoncalves@yahoo.com.br'),
(106, 'jose adeildo de lima filho', 0, 3, 13, 3, 'adeildobiologia@gmail.com'),
(107, 'jose alves do nascimento neto', 0, 3, 19, 4, 'josealvesnneto@gmail.com'),
(108, 'jose antonio candido borges da silva', 0, 3, 19, 3, 'candido@ee.ufcg.edu.br'),
(109, 'jose de arimateia almeida e silva', 0, 3, 21, 1, 'arimateia.allmeida@gmail.com'),
(110, 'jose do nascimento junior', 0, 3, 12, 2, 'jjrbol@yahoo.com.br'),
(111, 'jose gilson de lucena gomes', 0, 3, 19, 3, 'gilson.lucena@oi.com.br'),
(112, 'jose thiago holanda de alcantara cabral', 0, 3, 19, 3, 'jtholanda@gmail.com'),
(113, 'josikleio da costa silva', 0, 3, 13, 3, 'josikleio@gmail.com'),
(114, 'josé luiz cavalcante', 0, 3, 13, 1, 'luiz-x@hotmail.com'),
(115, 'joyce kelly barros henrique', 0, 3, 13, 1, 'joycekellybarros@yahoo.com.br'),
(116, 'katia davi brito', 0, 3, 13, 4, 'katiadout@hotmail.com'),
(117, 'katyusco de farias santos', 0, 3, 19, 3, 'katyusco@gmail.com'),
(118, 'kennedy flavio meira de lucena', 0, 3, 13, 4, 'kennedyflavio@yahoo.com.br'),
(119, 'kleber da fonseca furtado', 0, 3, 17, 1, 'kleberffurtado@uol.com.br'),
(120, 'lucyana sobral de souza', 0, 3, 13, 3, 'lsobralsouza@bol.com.br'),
(121, 'lugero batista melo', 0, 3, 13, 1, ''),
(122, 'luis havelange soares', 0, 3, 13, 3, 'luis.soares@ifpb.edu.br'),
(123, 'magna celi tavares bispo', 0, 3, 22, 3, 'magnabispo@gmail.com'),
(124, 'manoel mendes de aragao neto', 0, 3, 13, 1, 'aragaomanoel@yahoo.com.br'),
(125, 'marcelo jos siqueira coutinho almeida', 0, 3, 22, 3, 'marcelo@coinfo.cefetpb.edu.br'),
(126, 'marcelo portela sousa', 0, 3, 12, 4, 'marporsou@gmail.com'),
(127, 'marcelo rodrigues do nascimento', 0, 3, 17, 4, 'marceloquimica@gmail.com'),
(128, 'marcia de albuquerque pereira', 0, 3, 13, 1, 'marseaa@hotmail.com'),
(129, 'marcia gardenia lustosa pires', 0, 3, 13, 4, 'gardenialustosa@yahoo.com.br'),
(130, 'marcia maria costa gomes', 0, 3, 13, 3, 'mmarciagomes@gmail.com'),
(131, 'marcia rafaela arnold', 0, 3, 13, 1, ''),
(132, 'marcilio diniz da silva', 0, 3, 13, 3, 'marciliodiniz.s@gmail.com'),
(133, 'marco tullio lima duarte', 0, 3, 13, 3, 'duartetullio@hotmail.com'),
(134, 'marconi jose da camara pires', 0, 3, 13, 3, 'marconi.pires@ifpb.edu.br / mjcpires@yahoo.com.br'),
(135, 'marcos mesquita da silva', 0, 3, 18, 1, 'marcos_m_silva@yahoo.com.br'),
(136, 'marcos severino de lima', 0, 3, 21, 1, 'marcosufcg@oi.com.br'),
(137, 'marcos vinicius c. m. de andrade', 0, 3, 19, 2, 'marcosvcma@ifpb.edu.br'),
(138, 'maria angélica ramos da silva', 0, 3, 13, 4, 'angel-jp@hotmail.com'),
(139, 'maria auxiliadora de brito lira dal monte', 0, 3, 17, 4, 'britolira@hotmail.com'),
(140, 'maria célia ribeiro da silva', 0, 3, 13, 3, 'celia.ribeiro@ifpb.edu.br'),
(141, 'maria claudia rodrigues brandao', 0, 3, 12, 1, 'claudiabrandao.quimica@gmail.com'),
(142, 'maria do socorro marreiro de sousa', 0, 3, 13, 1, ''),
(143, 'maria juliana leopoldino vilar', 0, 3, 13, 1, 'julianalspb@yahoo.com.br'),
(144, 'marta clia f. bezerra', 0, 3, 19, 4, 'martaceliafeitosa@yahoo.com.br'),
(145, 'mary delane gomes da costa', 0, 3, 17, 1, ''),
(146, 'mary karlla araujo guimaraes', 0, 3, 19, 3, 'karllacg@gmail.com'),
(147, 'mauricio rodrigues pereira', 0, 3, 17, 3, 'fmimauriciorodriguespereira@gmail.com'),
(148, 'maxwell aragão marques nogueira', 0, 3, 13, 1, 'rx_maxwell@yahoo.com.br'),
(149, 'mellyne palmeira medeiros', 0, 3, 21, 3, 'melpalmeira@hotmail.com'),
(150, 'michell leonard duarte de lima tolentino', 0, 3, 13, 1, 'michelltolentino@gmail.com'),
(151, 'michelle dayse marques de lima', 0, 3, 12, 3, 'michelledml@gmail.com'),
(152, 'moacy pereira da silva', 0, 3, 13, 3, 'moacy.silva@ifpb.edu.br'),
(153, 'newmark heiner da cunha carvalho', 0, 3, 19, 3, 'newmark@fiepb.org.br'),
(154, 'neyr muniz barreto', 0, 3, 13, 2, 'neyrmb@hotmail.com'),
(155, 'orlando batista de almeida', 0, 3, 13, 4, 'proforlandoba@yahoo.com.br'),
(156, 'paulo de tarso firmino junior', 0, 3, 13, 3, 'paulodt@gmail.com'),
(157, 'paulo jose carneiro perfeito', 0, 3, 13, 3, 'pjperfeito@yahoo.com.br'),
(158, 'paulo ribeiro lins junior', 0, 3, 19, 3, 'paulo.ribeiro.lins.jr@gmail.com'),
(159, 'pedro paulo de andrade silva', 0, 3, 13, 2, 'pedropaulopb@yahoo.com.br'),
(160, 'petrônio carlos bezerra', 0, 3, 19, 3, 'petroniocg@ifpb.edu.br'),
(161, 'rachel freire torrez de souza', 0, 3, 13, 1, 'racheltorrez@yahoo.com.br'),
(162, 'ramide augusto sales dantas', 0, 3, 22, 1, 'ramide@gmail.com'),
(163, 'ranieri de araujo pereira', 0, 3, 17, 1, 'ranieri.engminas@gmail.com'),
(164, 'raphael alexander rosa romero', 0, 3, 17, 1, ''),
(165, 'rhavy maia guedes', 0, 3, 19, 3, 'rhavyron@gmail.com'),
(166, 'rodrigo rodrigues da silva', 0, 3, 13, 1, 'rrfisica@hotmail.com'),
(167, 'romulo alexandre silva', 0, 3, 23, 3, 'romulo_celia@hotmail.com'),
(168, 'romulo sousa torres', 0, 3, 22, 3, 'rstorresk@gmail.com'),
(169, 'ronaebson de carvalho ferreira', 0, 3, 13, 3, 'ronaebson@uol.com.br'),
(170, 'ronaldo araujo alves', 0, 3, 22, 3, 'araujo.ronaldo@gmail.com'),
(171, 'ronnie elder da cunha', 0, 3, 17, 2, 'ronnie.cunha@gmail.com'),
(172, 'ronnylson cesar de oliveira fonseca', 0, 3, 13, 2, 'ronnylsoncesar@gmail.com'),
(173, 'rosa lucia vieira souza', 0, 3, 12, 1, 'rosa.lucia@uol.com.br'),
(174, 'sabrina alves de freitas', 0, 3, 13, 3, 'sabrinaaf@gmail.com'),
(175, 'salomão pereira de almeida', 0, 3, 13, 3, 'salomaomatematica@yahoo.com.br'),
(176, 'samuel alves da silva', 0, 3, 19, 3, ''),
(177, 'saskia lavyne barbosa da silva', 0, 3, 13, 3, 'slavyne@yahoo.com.br'),
(178, 'sibria maria souto dos santos farias', 0, 3, 13, 3, 'siberia.santos@gmail.com'),
(179, 'stenio farias davila lins', 0, 3, 13, 1, 'steniopb@yahoo.com.br'),
(180, 'stephanie ingrid souza barboza', 0, 3, 13, 3, 'stephanieisb@gmail.com'),
(181, 'sulen silva figueiredo', 0, 3, 21, 3, 'suelensfigueiredo@gmail.com'),
(182, 'tarcisio oliveira de moraes júnior', 0, 3, 13, 3, 'tarcisiocz@gmail.com'),
(183, 'thalita cunha motta', 0, 3, 20, 3, 'thalitacmotta@gmail.com'),
(184, 'thassio nóbrega gomes', 0, 3, 18, 1, 'thassiong@gmail.com'),
(185, 'tulio cesar soares dos santos andre', 0, 3, 17, 4, 'tcssandre@hotmail.com'),
(186, 'uelpis luiz tenorio da silva', 0, 3, 13, 1, 'uelpisluiz@yahoo.com.br'),
(187, 'vanlex gomes galdino', 0, 3, 13, 2, 'vanlexgaldino@bol.com.br'),
(188, 'victor moiss de araújo medeiros', 0, 3, 13, 3, 'victor.medeiros@ifpb.edu.br'),
(189, 'wandenberg bismarck colaço lima', 0, 3, 17, 3, 'wandercg@oi.com.br'),
(190, 'yuri saladino souto maior nunes', 0, 3, 12, 3, 'yurisaladino@yahoo.com.br');
