create table jokes (
  id bigint not null,
  type varchar(255),
  setup varchar(255),
  punchline varchar(255),
  created timestamp,
  modified timestamp,
primary key (id)
);