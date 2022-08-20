drop schema public cascade;

create schema public;


create table logs(
	woeid bigint,
	results text
);