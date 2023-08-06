CREATE TABLE authors
(
    id          integer primary key generated by default as identity,
    author_name varchar(100)
);

CREATE TABLE artists
(
    id          integer primary key generated by default as identity,
    artist_name varchar(100)
);

CREATE TABLE publishers
(
    id             integer primary key generated by default as identity,
    publisher_name varchar(100)
);

CREATE TABLE comics
(
    id           integer primary key generated by default as identity,
    comic_name   varchar(100),
    amount       integer,
    image        varchar(256)
        DEFAULT 'src/main/resources/images/1.jpg',
    publisher_id integer references publishers (id)
);

CREATE TABLE author_comic
(
    author_id integer references authors (id),
    comic_id  integer references comics (id)
);

CREATE TABLE artist_comic
(
    artist_id integer references artists (id),
    comic_id  integer references comics (id)
);
CREATE TABLE clients
(
    id        UUID         not null primary key ,
    client_name varchar(100) not null,
    email     varchar(100) not null,
    h_password  varchar(256) not null
);
CREATE TABLE artist_client
(
    id INTEGER primary key generated by default as identity,
    artist_id integer references artists(id),
    client_id uuid references clients(id)
);
CREATE TABLE author_client
(
    id integer primary key generated by default as identity,
    author_id integer references authors(id),
    client_id uuid references clients(id)
);
insert into authors(author_name)
values ('Martin');

insert into artists (artist_name)
values ('Sasha'),
       ('Masha');

insert into publishers(publisher_name)
values ('DC comics'),
       ('Marvel');

insert into comics (comic_name, amount, publisher_id)
values ('Batman returns', 20, 1);

insert into artist_comic(artist_id, comic_id)
VALUES (1, 1),
       (2, 1);
insert into author_comic(author_id, comic_id)
VALUES (1, 1);

