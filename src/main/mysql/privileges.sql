use mysql;
select host, user from user;
create user admin identified by '123456';
GRANT ALL ON netty_chat.* to admin@'%' identified by '123456' with grant OPTION;
flush privileges;
