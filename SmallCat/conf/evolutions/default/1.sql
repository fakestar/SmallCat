# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table m_items (
  item_id                   bigint auto_increment not null,
  item_name                 varchar(255) not null,
  create_date               datetime not null,
  update_date               datetime not null,
  constraint pk_m_items primary key (item_id))
;

create table t_players (
  player_id                 bigint auto_increment not null,
  player_name               varchar(255) not null,
  char_id                   bigint,
  create_date               datetime not null,
  update_date               datetime not null,
  constraint pk_t_players primary key (player_id))
;


create table c_player_friends (
  player_id                      bigint not null,
  friend_id                      bigint not null,
  constraint pk_c_player_friends primary key (player_id, friend_id))
;



alter table c_player_friends add constraint fk_c_player_friends_t_players_01 foreign key (player_id) references t_players (player_id) on delete restrict on update restrict;

alter table c_player_friends add constraint fk_c_player_friends_t_players_02 foreign key (friend_id) references t_players (player_id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table m_items;

drop table t_players;

drop table c_player_friends;

SET FOREIGN_KEY_CHECKS=1;

