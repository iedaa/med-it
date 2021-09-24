-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: meditfarmacia
-- ------------------------------------------------------
-- Server version	5.7.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `drug`
--

DROP TABLE IF EXISTS `drug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drug` (
  `drug_id` int(11) NOT NULL AUTO_INCREMENT,
  `dname` varchar(60) NOT NULL,
  `about` text,
  `price` double DEFAULT '0',
  `brand` varchar(30) DEFAULT NULL,
  `units` int(11) DEFAULT '1',
  `prescription` enum('Sim','Não') DEFAULT NULL,
  PRIMARY KEY (`drug_id`),
  UNIQUE KEY `dname` (`dname`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drug`
--

LOCK TABLES `drug` WRITE;
/*!40000 ALTER TABLE `drug` DISABLE KEYS */;
INSERT INTO `drug` VALUES (1,'Dipirona Sódica 500mg 10 Comprimidos','Este medicamento é indicado como analgésico (para dor) e antitérmico (para febre).',3.28,'Prati-Donaduzzi',52,'Não'),(2,'Analgésico Tylenol 500mg 20 Comprimidos','Analgésico Tylenol 500mg 20 Comprimidos é indicado para baixar a febre e aliviar temporariamente dores leves a moderadas, como as associadas à gripes e resfriados, dor de cabeça, dor no corpo, dores musculares, dor de dente, cólicas menstruais, dentre outras.',22.59,'Tylenol',21,'Não'),(3,'Clonazepam Gotas 2,5mg/mL com 20mL Teuto','Clonazepam é usado para prevenir e controlar convulsões. Este medicamento é conhecido como anticonvulsivante ou antiepiléptico. Também é usado para tratar ataques de pânico. O clonazepam atua acalmando o cérebro e os nervos. Pertence a uma classe de medicamentos chamados benzodiazepínicos.',9.9,'Teuto',5,'Sim'),(4,'Neolefrin Dia 20 Comprimidos','Este medicamento é indicado para o tratamento dos sintomas das gripes e resfriados, como dor, febre, congestão nasal e coriza.',3.28,'Neo Química',8,'Não'),(5,'Fluconazol 1 Cápsula Dura 150mg','O fluconazol é indicado para o tratamento de Candidíase vaginal (infecções da vagina causadas por fungos do gênero Candida) aguda e recorrente (de repetição), como profilaxia (prevenção) para reduzir a candidíase vaginal recorrente (três ou mais episódios por ano), balanite por Candida (infecção fúngica da região conhecida popularmente como “cabeça do pênis”) e Dermatomicoses (infecções fúngicas na pele e nos seus anexos, por exemplo, unha, conhecidas popularmente como micoses) como: Tinea pedis, Tinea corporis, Tinea cruris, Tinea unguium (onicomicoses) e infecções por fungos do gênero Candida.',6.05,'Eurofarma',34,'Não'),(6,'Finalop 1mg 30 Comprimidos','No couro cabeludo, a finasterida reduz especificamente os níveis de diidrotestosterona (DHT), a causa principal de queda de cabelo de padrão masculino. Desta maneira, a finasterida ajuda a reverter o processo da calvície, levando ao aumento do crescimento capilar e à prevenção de perdas adicionais de cabelo.',66.77,'Libbs',43,'Não'),(7,'Fixare 30 Comprimidos Revestidos','O Fixare é um complemento alimentar na manutenção dos ossos. Ele contém em sua formula vitaminas e minerais que ajudam no tratamento da osteoporose e da osteopenia.',81.59,'EMS',32,'Não'),(8,'Magnen B6 30 Comprimidos Revestidos 722,22mg/1mg','Magnen B6 atua como suplemento vitamínico-mineral nos casos de dietas restritivas e inadequadas; como auxiliar do sistema imunológico; em doenças crônicas ou convalescença e para idosos.',87.96,'Marjan',15,'Não'),(9,'Caldê K2 30 Comprimidos Revestidos','O Caldê K2 é um suplemento vitamínico-mineral que auxilia no tratamento de osteoporose e osteopenia. Tem em seu principio o Cálcio (cálcio citrato malato), Vitamina D, Vitamina K2.',101.99,'Marjan',2,'Não'),(10,'Materna 30 Comprimidos Revestidos','Materna é um suplemento vitamínico-mineral indicado para uso durante a gravidez e lactação, períodos de grande atividade fisiológica, com aumento das necessidades nutricionais diárias. Materna funciona suplementando, com vitaminas e minerais, a dieta de mulheres grávidas e que estão amamentando.',54.25,'Nestlé',25,'Não'),(11,'Composto Lácteo Ninho Forti Zero Lactose 700g','O Composto Lácteo Ninho Forti+ Zero Lactose é fortificado com Ferro, Zinco e Vitaminas A, C e D, essenciais para a nutrição das crianças.',42.99,'Nestlé',17,'Não');
/*!40000 ALTER TABLE `drug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(19) NOT NULL,
  `realname` varchar(30) DEFAULT '',
  `userpw` varchar(30) NOT NULL,
  `email` varchar(254) NOT NULL,
  `permission` enum('user','admin') DEFAULT 'user',
  `avatar` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','','admin','admin@gmail.com','admin','');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-24 18:30:25
