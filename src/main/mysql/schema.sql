CREATE DATABASE IF NOT EXISTS `netty_chat`;
use `netty_chat`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` VARCHAR(16) NOT NULL,
  `pwd` VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
