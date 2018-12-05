create table MARKETER
(
   EMAIL                VARCHAR(50) not null,
   NAME                 VARCHAR(200),
   NICK                 VARCHAR(50),
   ADDRESS              VARCHAR(250),
   PASSWD               VARCHAR(10),
   ST_VER               INT,
   primary key (EMAIL)
);
