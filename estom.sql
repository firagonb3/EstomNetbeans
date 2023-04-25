-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.21-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para estom
CREATE DATABASE IF NOT EXISTS `estom` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */;
USE `estom`;

-- Volcando estructura para tabla estom.fotouser
CREATE TABLE IF NOT EXISTS `fotouser` (
  `uuid` int(11) NOT NULL,
  `img` longblob NOT NULL,
  PRIMARY KEY (`uuid`),
  CONSTRAINT `FK__usuarios` FOREIGN KEY (`uuid`) REFERENCES `usuarios` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla estom.fotouser: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `fotouser` DISABLE KEYS */;
/*!40000 ALTER TABLE `fotouser` ENABLE KEYS */;

-- Volcando estructura para tabla estom.juegos
CREATE TABLE IF NOT EXISTS `juegos` (
  `estomid` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(500) COLLATE utf8_spanish_ci NOT NULL,
  `banner` varchar(2000) COLLATE utf8_spanish_ci NOT NULL,
  `logo` varchar(2000) COLLATE utf8_spanish_ci NOT NULL,
  `almacenamiento` double NOT NULL DEFAULT 0,
  PRIMARY KEY (`estomid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla estom.juegos: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `juegos` DISABLE KEYS */;
INSERT INTO `juegos` (`estomid`, `nombre`, `descripcion`, `banner`, `logo`, `almacenamiento`) VALUES
	(1, 'Portal', 'Portal™ es la nueva aventura para un solo jugador de Valve. Ambientado en los misteriosos laboratorios de Aperture Science, Portal ha sido calificado como uno de los juegos más innovadores de los últimos tiempos y ofrece incontables horas de rompecabezas ', 'https://cdn.cloudflare.steamstatic.com/steam/apps/400/library_hero.jpg', 'https://cdn.cloudflare.steamstatic.com/steam/apps/400/logo.png', 8),
	(2, 'Forza Horizon 5', '¡La aventura Horizon definitiva te espera! Explora los vibrantes paisajes de mundo abierto en constante evolución situado en México, y disfruta de una acción de conducción ilimitada y divertida con cientos de los mejores coches del mundo.', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1551360/library_hero.jpg', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1551360/logo.png', 110),
	(3, 'Battlefield™ 2042', 'Battlefield™ 2042 es un shooter en primera persona que marca el regreso a la emblemática guerra total de la franquicia. En un mundo en el futuro cercano transformado por el desorden, adáptate y sobrevive con la ayuda de tu patrulla y un arsenal de vanguardia en campos de batalla en constante.', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1517290/library_hero.jpg', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1517290/logo.png', 100),
	(4, 'Cyberpunk 2077', 'Cyberpunk 2077 es una historia de acción y aventura en mundo abierto ambientada en Night City, una megalópolis obsesionada con el poder, el glamur y la modificación corporal. Tu personaje es V, un mercenario que persigue un implante único que permite alcanzar la inmortalidad.', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1091500/library_hero.jpg', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1091500/logo.png', 70),
	(5, 'Five Nights at Freddy\'s: Security Breach', 'En Five Nights at Freddy\'s: Security Breach, juega como Gregory, un niño que ha estado atrapado durante la noche en el Mega Pizzaplex de Freddy Fazbear. Con la ayuda del propio Freddy, Gregory debe descubrir los secretos del Pizzaplex, conocer la verdad y sobrevivir hasta el amanecer.', 'https://cdn.cloudflare.steamstatic.com/steam/apps/747660/library_hero.jpg', 'https://cdn.cloudflare.steamstatic.com/steam/apps/747660/logo.png', 80),
	(6, 'Portal 2', '¡La "Iniciativa de Prueba Perpetua" ha sido ampliada, permitiéndote ahora diseñar puzles cooperativos para ti y tus amigos!', 'https://cdn.cloudflare.steamstatic.com/steam/apps/620/library_hero.jpg', 'https://cdn.cloudflare.steamstatic.com/steam/apps/620/logo.png', 11.97),
	(7, 'UNO', '¡UNO vuelve con características nuevas! Combina cartas por valor o color y juega cartas especiales para animar la partida. Compite contra otros para ver quién acaba antes sus cartas en el modo clásico o personaliza la partida con reglas de la casa.', 'https://cdn.cloudflare.steamstatic.com/steam/apps/470220/library_hero.jpg', 'https://cdn.cloudflare.steamstatic.com/steam/apps/470220/logo.png', 3),
	(8, 'Cookie Clicker', '¡Un juego incremental en el que hay que hacer galletas! Se publicó en 2013 en la web y se ha desarrollado activamente desde entonces. Esta es la versión oficial para Estom.', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1454400/library_hero.jpg', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1454400/logo.png', 0.35);
/*!40000 ALTER TABLE `juegos` ENABLE KEYS */;

-- Volcando estructura para tabla estom.juegoscomprados
CREATE TABLE IF NOT EXISTS `juegoscomprados` (
  `uuid` int(11) NOT NULL,
  `estomid` int(11) NOT NULL,
  PRIMARY KEY (`uuid`,`estomid`),
  KEY `FK_juegoscomprados_juegos` (`estomid`),
  CONSTRAINT `FK_juegoscomprados_juegos` FOREIGN KEY (`estomid`) REFERENCES `juegos` (`estomid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_juegoscomprados_usuarios` FOREIGN KEY (`uuid`) REFERENCES `usuarios` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla estom.juegoscomprados: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `juegoscomprados` DISABLE KEYS */;
INSERT INTO `juegoscomprados` (`uuid`, `estomid`) VALUES
	(20, 2),
	(20, 4),
	(20, 7),
	(21, 1),
	(21, 5),
	(21, 6),
	(21, 8);
/*!40000 ALTER TABLE `juegoscomprados` ENABLE KEYS */;

-- Volcando estructura para tabla estom.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `uuid` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `passwd` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`uuid`,`usuario`) USING BTREE,
  UNIQUE KEY `usuario` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla estom.usuarios: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`uuid`, `usuario`, `passwd`) VALUES
	(20, 'bemen3', '60cbc9cf3fda5c2a4beec6933b92aa9fa1ed5921ec3259c3dcff0ad17a090e15'),
	(21, 'marc', '6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
