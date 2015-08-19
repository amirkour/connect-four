--
-- schema.sql
--
-- defines the initial database schema for connect-four game implementation
--
create database if not exists connectfour;

use connectfour;
--
-- player_types
--
create table player_types(
    id tinyint not null auto_increment,
    name varchar(32) not null,

    primary key(id)
);

insert into player_types(name) values('pc');
insert into player_types(name) values('npc-left-to-right-agent');
insert into player_types(name) values('npc-configurable-minimax-agent');
insert into player_types(name) values('npc-offensive-minimax-agent');
insert into player_types(name) values('npc-defensive-minimax-agent');

--
-- player_colors
--
create table player_colors(
    id tinyint not null auto_increment,
    name varchar(16) not null,

    primary key(id)
);

insert into player_colors(name) values('red');
insert into player_colors(name) values('black');

--
-- users
--
create table users(
    id int not null auto_increment,
    email varchar(64) not null unique,
    fname varchar(64),
    lname varchar(64),

    primary key(id)
);

--
-- players
--
create table players(
    id int not null auto_increment,
    player_type_id tinyint not null,
    player_color_id tinyint not null,
    user_id int not null,

    primary key(id),
    index fk_players_player_type_id_player_types_id(player_type_id),
    index fk_players_player_color_id_player_colors_id(player_color_id),
    index fk_players_user_id_users_id(user_id),

    foreign key (player_type_id) references player_types(id),
    foreign key (player_color_id) references player_colors(id),
    foreign key (user_id) references users(id)
);

--
-- games
--
create table games(
    id int not null auto_increment,
    winning_player_id int null,
    outcome varchar(255) null,
    number_in_row_to_win tinyint not null,
    board_matrix_json varchar(255) not null,

    primary key(id),
    index fx_games_id_players_id(winning_player_id),

    foreign key (winning_player_id) references players(id)
);

--
-- game_players
--
create table game_players(
    game_id int not null,
    player_id int not null,
    list_index int not null, -- for hibernate to be able to resolve list ordering

    primary key(game_id,player_id),

    foreign key (game_id) references games(id),
    foreign key (player_id) references players(id)
);
