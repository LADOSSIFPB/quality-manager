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

INSERT INTO `tb_tipo_pessoa` (`id_tipo_pessoa`, `nm_tipo`) VALUES
(1, 'COORDENADOR'),
(3, 'DISCENTE'),
(4, 'GESTOR'),
(2, 'ORIENTADOR');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_instituicao_bancaria`
--

INSERT INTO `tb_instituicao_bancaria` (`id_instituicao_bancaria`, `nm_banco`, `nr_cnpj`, `dt_registro`) VALUES
(1, 'Caixa Econômica', '360305000104', '2014-10-31 01:58:23'),
(2, 'Banco do Brasil SA', '00000000000191', '2014-10-31 02:22:31');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_pessoa`
--

INSERT INTO `tb_pessoa` (`id_pessoa`, `nm_pessoa`, `nr_cpf`, `nr_matricula`, `nm_endereco`, `nm_cep`, `nm_telefone`, `nm_email`, `nm_senha`, `tipo_pessoa_id`, `dt_registro`) VALUES
(1, 'Márcia Maria Costa Gomes', '12345678910', '12345678901', 'Rua Presidente Tancredo Neves. Bairro: Jardim Sorrilândia', '58015430', '8332083004', 'marcia.gomes@gmail.com', 'Mg123456%', 4, '2014-10-31 02:10:39'),
(2, 'Eri Jonhson Oliveira da Silva', '12345678921', '20111003145', 'Rua Muniz Barreto de Lima, 92', '58487564', '8399795879', 'erijonhson.os@gmail.com', 'Ej123456%', 3, '2014-10-31 02:32:57'),
(3, 'Rhavy Maia Guedes', '09876534523', '32456798', 'Rua Capitão Domingos Cariris', '58432562', '8396432156', 'rhavy.maia@gmail.com', 'Rg123456%', 2, '2014-10-31 02:36:52'),
(4, 'Felipe Nascimento Souza', '56781234910', '20111004531', 'Rua Argentina, 34', '58562432', '8396215643', 'felipe_nsousa@hotmail.com', 'Fn123456%', 3, '2014-10-31 02:41:29'),
(5, 'Elaine Cristina Juvino', '25018495023', '201420042112', 'Rua Holanda, 12', '58102822', '8396324015', 'elaine_cristina@hotmail.com', 'Ec123456%', 1, '2014-11-11 02:41:29'),
(6, 'Adriana Lemos', '45631901315', '201230012345', 'Rua Ivanildo Terceiro, 12', '58174245', '8391048332', 'adriana_lemos@gmail.com', 'Al123456%', 1, '2014-11-11 02:41:29'),
(7, 'Divanira Ferreira Maia', '84759204958', '201210032731', 'Rua Brasil, 13', '58324562', '8388341023', 'divanira_ferreira@gmail.com', 'Df123456%', 1, '2014-11-11 02:41:29');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_instituicao_financiadora`
--

INSERT INTO `tb_instituicao_financiadora` (`id_instituicao`, `nr_cnpj`, `nm_instituicao`, `nm_sigla`, `vl_orcamento`, `pessoa_id`, `dt_registro`) VALUES
(1, '10783898000175', 'Instituto Federal de Educação, Ciência e Tecnologia da Paraíba', 'IFPB', 1000000, 1, '2014-10-31 02:13:06');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_programa_institucional`
--

INSERT INTO `tb_programa_institucional` (`id_programa_institucional`, `nm_programa_institucional`, `nm_sigla`, `vl_orcamento`, `pessoa_id`, `instituicao_id`, `dt_registro`) VALUES
(1, 'Programa Institucional de Bolsas de Iniciação Científica para o Ensino Médio', 'PIBIC-EM', 10000, 1, 1, '2014-10-31 02:15:29'),
(2, 'Programa Institucional de Bolsas de Extensão', 'PROBEXT', 9000, 1, 1, '2014-10-31 02:25:16');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_edital`
--

INSERT INTO `tb_edital` (`id_edital`, `ar_edital`, `nr_edital`, `nr_ano`, `dt_inicio_inscricoes`, `dt_fim_inscricoes`, `dt_relatorio_parcial`, `dt_relatorio_final`, `nr_vagas`, `vl_bolsa_discente`, `vl_bolsa_docente`, `tp_edital`, `programa_institucional_id`, `pessoa_id`, `dt_registro`) VALUES
(1, '/home/slave4/arquivos', 11, 2014, '2014-10-01', '2014-11-30', '2015-03-11', '2015-09-30', 15, 100, 500, 'P', 1, 1, '2014-10-31 02:20:08');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_curso`
--

INSERT INTO `tb_curso` (`id_curso`, `nm_curso`, `pessoa_id`, `dt_registro`) VALUES
(1, 'Técnico em Informática Integrado ao Ensino Médio', 5, '2014-10-31 02:26:15'),
(2, 'Técnico em Mineração Integrado ao Ensino Médio', 6, '2014-10-31 02:28:20'),
(3, 'Técnico em Petróleo e Gás Integrado ao Ensino Médio', 7, '2014-10-31 02:29:57');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_turma`
--

INSERT INTO `tb_turma` (`id_turma`, `nr_periodo_letivo`, `nm_turno`, `dt_registro`, `curso_id`) VALUES
(1, 4, 'M', '2014-10-31 02:27:39', 1),
(2, 3, 'T', '2014-10-31 02:43:09', 2);

--
-- Fazendo dump de dados para tabela `tb_discente`
--

INSERT INTO `tb_discente` (`pessoa_id`, `turma_id`) VALUES
(2, 1),
(4, 1);

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_orientador`
--

INSERT INTO `tb_orientador` (`pessoa_id`, `nm_titulacao`, `nm_cargo`, `nm_local_trabalho`) VALUES
(3, 'Mestre', 'Professor', 'Laboratório de Sistemas Convergentes');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_dados_bancarios`
--

INSERT INTO `tb_dados_bancarios` (`pessoa_id`, `instituicao_bancaria_id`, `nr_operacao`, `nr_conta`, `dt_registro`) VALUES
(1, 1, '013', '123456789012345', '2014-10-31 02:11:41'),
(2, 1, '013', '123456789054321', '2014-10-31 02:34:18'),
(3, 2, NULL, '905432112345678', '2014-10-31 02:37:53'),
(4, 1, '013', '345126789054321', '2014-10-31 02:42:07');

-- --------------------------------------------------------

--
-- Fazendo dump de dados para tabela `tb_projeto`
--

INSERT INTO `tb_projeto` (`id_projeto`, `nm_projeto`, `dt_inicio_projeto`, `dt_fim_projeto`, `ar_projeto_submetido`, `ar_relatorio_parcial`, `ar_relatorio_final`, `nr_processo`, `tp_projeto`, `vl_orcamento`, `dt_registro`, `edital_id`) VALUES
(1, 'Uma ferramenta de apoio à gestão de projetos para coordenadores de pesquisa no IFPB', '2013-10-14', '2015-08-28', '/opt/slave4/arquivos', '/opt/slave4/arquivos/relatorios', '/opt/slave4/arquivos/relatorios', '1234567890123456780', 'P', 1500, '2014-10-31 02:48:15', 1);

-- --------------------------------------------------------


--
-- Fazendo dump de dados para tabela `tb_participacao`
--

INSERT INTO `tb_participacao` (`id_participacao`, `pessoa_id`, `projeto_id`, `dt_inicio`, `dt_fim`, `vl_bolsa`, `dt_registro`) VALUES
(1, 2, 1, '2013-10-28', '2015-08-28', 500, '2014-10-31 02:49:13'),
(2, 3, 1, '2013-11-05', NULL, 0, '2014-10-31 02:50:28'),
(3, 4, 1, '2013-11-18', NULL, 100, '2014-10-31 02:51:19');

-- --------------------------------------------------------

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
