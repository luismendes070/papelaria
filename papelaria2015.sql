-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 14-Jan-2015 às 21:11
-- Versão do servidor: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `papelaria2015`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE IF NOT EXISTS `clientes` (
  `cpf_cliente` varchar(15) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `endereco` varchar(50) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `loja_de_referencia1` varchar(15) NOT NULL,
  `loja_de_referencia2` varchar(15) NOT NULL,
  `situacao_cliente` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`cpf_cliente`, `nome`, `endereco`, `telefone`, `loja_de_referencia1`, `loja_de_referencia2`, `situacao_cliente`) VALUES
('05020868159', 'Luís Mendes Machado', 'Orizona GO', '(64)9600-1436', '(64)9600-1436', '(64)9600-1436', 1),
('185.302.491-00', 'ADELAIDE MARIA DA SILVA', 'Goiania GO', '(62)12345678', 'Casas Bahia', 'Ponto Frio', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedores`
--

CREATE TABLE IF NOT EXISTS `fornecedores` (
  `cnpj` varchar(20) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `endereco` varchar(50) NOT NULL,
  `telefone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `fornecedores`
--

INSERT INTO `fornecedores` (`cnpj`, `nome`, `endereco`, `telefone`) VALUES
('1', 'Tilibra', 'SP', '(11)1234-5678'),
('2', 'Multilazer', 'Brasil BR', '(11)1234-5678'),
('3', 'Faber Castell', 'SP', '(11)1234-5678'),
('4', 'Compactor', 'SP', '(11)1234-5678'),
('5', 'Bic', 'SP', '(11)1234-5678');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionarios`
--

CREATE TABLE IF NOT EXISTS `funcionarios` (
  `cpf_funcionario` varchar(15) NOT NULL,
  `nome_funcionario` varchar(50) NOT NULL,
  `endereco_funcionario` varchar(50) NOT NULL,
  `telefone_funcionario` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcionarios`
--

INSERT INTO `funcionarios` (`cpf_funcionario`, `nome_funcionario`, `endereco_funcionario`, `telefone_funcionario`) VALUES
('05020868159', 'Luís Mendes Machado', 'Orizona GO', '(64)1234-5678'),
('3', 'Mister 3', 'Alabasta', '3');

-- --------------------------------------------------------

--
-- Estrutura da tabela `itens_vendidos`
--

CREATE TABLE IF NOT EXISTS `itens_vendidos` (
`id_item_vendidos` int(11) NOT NULL,
  `id_venda` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=277 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `itens_vendidos`
--

INSERT INTO `itens_vendidos` (`id_item_vendidos`, `id_venda`, `id_produto`, `quantidade`) VALUES
(273, 199, 4, 4),
(274, 199, 5, 1),
(275, 199, 6, 1),
(276, 199, 5, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE IF NOT EXISTS `produtos` (
`id_produto` int(11) NOT NULL,
  `nome_produto` varchar(50) NOT NULL,
  `cnpj_fornecedor` varchar(20) NOT NULL,
  `preco` float NOT NULL,
  `estoque` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`id_produto`, `nome_produto`, `cnpj_fornecedor`, `preco`, `estoque`) VALUES
(4, 'Caderno 10 matérias', '1', 22, 47),
(5, 'Borracha', '3', 2, 63),
(6, 'Tesoura', '1', 3, 88);

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendas`
--

CREATE TABLE IF NOT EXISTS `vendas` (
`id_venda` int(11) NOT NULL,
  `cpf_funcionario` varchar(15) NOT NULL,
  `cpf_cliente` varchar(15) NOT NULL,
  `preco_total` float NOT NULL,
  `data_venda` varchar(15) NOT NULL,
  `hora_venda` varchar(15) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `vendas`
--

INSERT INTO `vendas` (`id_venda`, `cpf_funcionario`, `cpf_cliente`, `preco_total`, `data_venda`, `hora_venda`) VALUES
(199, '05020868159', '05020868159', 97, '14/01/2015', '06:10:33');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clientes`
--
ALTER TABLE `clientes`
 ADD PRIMARY KEY (`cpf_cliente`);

--
-- Indexes for table `fornecedores`
--
ALTER TABLE `fornecedores`
 ADD PRIMARY KEY (`cnpj`);

--
-- Indexes for table `funcionarios`
--
ALTER TABLE `funcionarios`
 ADD PRIMARY KEY (`cpf_funcionario`);

--
-- Indexes for table `itens_vendidos`
--
ALTER TABLE `itens_vendidos`
 ADD PRIMARY KEY (`id_item_vendidos`);

--
-- Indexes for table `produtos`
--
ALTER TABLE `produtos`
 ADD PRIMARY KEY (`id_produto`);

--
-- Indexes for table `vendas`
--
ALTER TABLE `vendas`
 ADD PRIMARY KEY (`id_venda`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `itens_vendidos`
--
ALTER TABLE `itens_vendidos`
MODIFY `id_item_vendidos` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=277;
--
-- AUTO_INCREMENT for table `produtos`
--
ALTER TABLE `produtos`
MODIFY `id_produto` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `vendas`
--
ALTER TABLE `vendas`
MODIFY `id_venda` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=200;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
