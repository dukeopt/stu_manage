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

create table course_student (
  id                            bigint auto_increment not null,
  course_id                     bigint not null,
  student_id                    bigint not null,
  constraint pk_course_student primary key (id)
);

create table course_teacher (
  id                            bigint auto_increment not null,
  course_id                     bigint not null,
  teacher_id                    bigint not null,
  constraint pk_course_teacher primary key (id)
);

create table homework (
  id                            bigint auto_increment not null,
  course_tacher_id              bigint not null,
  name                          varchar(255),
  date                          datetime(6),
  constraint pk_homework primary key (id)
);

create table score (
  id                            bigint auto_increment not null,
  score                         double not null,
  student_id                    bigint not null,
  course_id                     bigint not null,
  constraint pk_score primary key (id)
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

drop table if exists course_student;

drop table if exists course_teacher;

drop table if exists homework;

drop table if exists score;

drop table if exists student;

drop table if exists teacher;

