CREATE EXTENSION IF NOT EXISTS "uuid-ossp"; -- This is required to enable UUID extension
drop table if exists blog cascade;
drop table if exists post cascade;
drop table if exists comment cascade;
drop table if exists comment_like cascade;
drop table if exists file cascade;
drop table if exists post_files cascade;
drop table if exists "user" cascade;
-- since user is a keyword in postgresql, double quotes are used here
-- DROP statements end here
Create table if not exists blog
(
    id           serial primary key not null,
    guid         uuid default uuid_generate_v4(),
    name         varchar(150),
    about        text,
    published_at timestamp
);
create table if not exists post
(
    id      serial primary key not null,
    blog_id int,
    title   varchar(150),
    content text,
    "user"  int
);
create table if not exists comment
(
    id        serial primary key not null,
    parent_id int,
    content   text,
    post_id   int
);
create table if not exists "user"
(
    id           serial primary key not null,
    username     varchar(150),
    password     varchar(150),
    email        varchar(150),
    is_active    boolean,
    activated_at timestamp DEFAULT
                               (
                                   current_timestamp AT TIME ZONE 'UTC'
                                   )
);
create table comment_like
(
    id         bigserial not null,
    comment_id int8,
    post_id
               int8,
    user_id    int8,
    primary key (id)
);
create table file
(
    id   bigserial not null,
    name varchar(255),
    primary key (id)
);
create table post_files
(
    post_id int8 not null,
    file_id int8 not null,
    primary key (file_id, post_id)
);