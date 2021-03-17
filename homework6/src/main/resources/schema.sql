DROP TABLE IF EXISTS books, authors, genres, comments;
CREATE TABLE authors(author_id BIGINT SERIAL PRIMARY KEY, author_name VARCHAR(255));
CREATE TABLE genres(genre_id BIGINT SERIAL PRIMARY KEY, genre_name VARCHAR(255));
CREATE TABLE comments(comment_id BIGINT SERIAL PRIMARY KEY, comment VARCHAR(255));
CREATE TABLE books(id BIGINT SERIAL PRIMARY KEY , name VARCHAR(255), comment_id BIGINT, genre_id BIGINT, author_id BIGINT,
                   FOREIGN KEY (genre_id) REFERENCES genres(genre_id),
                   FOREIGN KEY(author_id) REFERENCES authors(author_id),
                   FOREIGN KEY(comment_id) REFERENCES comments(comment_id));