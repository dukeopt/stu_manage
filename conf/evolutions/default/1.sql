# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table admin (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  password                      varchar(255),
  constraint pk_admin primary key (id)
);

create table course (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_course primary key (id)
);

create table student (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  password                      varchar(255),
  constraint pk_student primary key (id)
);

create table teacher (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  password                      varchar(255),
  constraint pk_teacher primary key (id)
);


# --- !Downs

drop table if exists admin;

drop table if exists course;

drop table if exists student;

drop table if exists teacher;

