create table if not exists movie(
    db_id serial primary key,
    id varchar,
    title varchar,
    year varchar(10),
    imDbRating varchar(10),
    plot varchar
)