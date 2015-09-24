-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Machine: 127.0.0.1
-- Gegenereerd op: 24 sep 2015 om 02:56
-- Serverversie: 5.6.17
-- PHP-versie: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Databank: `unwdmi`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `measurement`
--

CREATE TABLE IF NOT EXISTS `measurement` (
  `station_id` int(11) NOT NULL,
  `local_date` date NOT NULL,
  `local_time` time NOT NULL,
  `temperatuur` int(11) NOT NULL,
  `dauwpunt` int(11) NOT NULL,
  `luchtdruk` int(11) NOT NULL,
  `zichtbaarheid` int(11) NOT NULL,
  `neerslag` int(11) NOT NULL,
  `sneeuwdiepte` int(11) NOT NULL,
  `bewolking` int(11) NOT NULL,
  `windrichting` int(11) NOT NULL,
  `windsnelheid` int(11) NOT NULL,
  `gebeurtenissen` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
