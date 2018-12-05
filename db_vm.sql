/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     03/12/2018 11:45:19                          */
/*==============================================================*/


drop table if exists CAMPAIGN;

drop table if exists CHANNEL;

drop table if exists CHANNEL_ATTR;

drop table if exists INFLUENCER;

drop table if exists INF_CHAN;

drop table if exists INF_CHAN_ATTR;

drop table if exists INF_SPEC;

drop table if exists MARKETER;

drop table if exists SPEC;

/*==============================================================*/
/* Table: CAMPAIGN                                              */
/*==============================================================*/
create table CAMPAIGN
(
   MKT_ID               VARCHAR(50),
   ID                   VARCHAR(40),
   TITLE                VARCHAR(150),
   DESCRIPTION          VARCHAR(250)
);

/*==============================================================*/
/* Table: CHANNEL                                               */
/*==============================================================*/
create table CHANNEL
(
   ID                   VARCHAR(10) not null,
   NAME                 VARCHAR(30),
   primary key (ID)
);

/*==============================================================*/
/* Table: CHANNEL_ATTR                                          */
/*==============================================================*/
create table CHANNEL_ATTR
(
   CH_ID                VARCHAR(10) not null,
   ID                   VARCHAR(15) not null,
   NAME                 VARCHAR(40),
   primary key (CH_ID, ID)
);

/*==============================================================*/
/* Table: INFLUENCER                                            */
/*==============================================================*/
create table INFLUENCER
(
   EMAIL                VARCHAR(50) not null,
   NAME                 VARCHAR(200),
   NICK                 VARCHAR(50),
   ADDRESS              VARCHAR(250),
   PASSWD               VARCHAR(10),
   ST_VER               INT,
   primary key (EMAIL)
);

/*==============================================================*/
/* Table: INF_CHAN                                              */
/*==============================================================*/
create table INF_CHAN
(
   CH_ID                VARCHAR(10) not null,
   INF_ID               VARCHAR(50) not null,
   primary key (CH_ID, INF_ID)
);

/*==============================================================*/
/* Table: INF_CHAN_ATTR                                         */
/*==============================================================*/
create table INF_CHAN_ATTR
(
   CH_ID                VARCHAR(10) not null,
   ATTR_ID              VARCHAR(15) not null,
   INF_ID               VARCHAR(50) not null,
   VAL                  VARCHAR(250),
   primary key (CH_ID, ATTR_ID, INF_ID)
);

/*==============================================================*/
/* Table: INF_SPEC                                              */
/*==============================================================*/
create table INF_SPEC
(
   INF_ID               VARCHAR(50) not null,
   SPEC_ID              VARCHAR(20) not null,
   primary key (INF_ID, SPEC_ID)
);

/*==============================================================*/
/* Table: MARKETER                                              */
/*==============================================================*/
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

/*==============================================================*/
/* Table: SPEC                                                  */
/*==============================================================*/
create table SPEC
(
   ID                   VARCHAR(20) not null,
   DESCR                VARCHAR(40),
   primary key (ID)
);

create table VERIFY_TOKEN(
    CONFIRM_KEY VARCHAR(40),
    CONFIRM_STATUS VARCHAR(1),
    EMAIL VARCHAR(30),

    CONSTRAINT PK_VERIFY_TOKEN_CONFIRM_KEY PRIMARY KEY (CONFIRM_KEY)
);

alter table CAMPAIGN add constraint FK_CMP_MKT foreign key (MKT_ID)
      references MARKETER (EMAIL) on delete restrict on update restrict;

alter table CHANNEL_ATTR add constraint FK_ATTR_CH foreign key (CH_ID)
      references CHANNEL (ID) on delete restrict on update restrict;

alter table INF_CHAN add constraint FK_INF_CH foreign key (CH_ID)
      references CHANNEL (ID) on delete restrict on update restrict;

alter table INF_CHAN add constraint FK_CH_INF foreign key (INF_ID)
      references INFLUENCER (EMAIL) on delete restrict on update restrict;

alter table INF_CHAN_ATTR add constraint FK_INF_CH_ATTR foreign key (CH_ID, ATTR_ID)
      references CHANNEL_ATTR (CH_ID, ID) on delete restrict on update restrict;

alter table INF_CHAN_ATTR add constraint FK_CH_ATTR_INF foreign key (INF_ID)
      references INFLUENCER (EMAIL) on delete restrict on update restrict;

alter table INF_SPEC add constraint FK_SPEC_INF foreign key (INF_ID)
      references INFLUENCER (EMAIL) on delete restrict on update restrict;

alter table INF_SPEC add constraint FK_INF_SPEC foreign key (SPEC_ID)
      references SPEC (ID) on delete restrict on update restrict;

