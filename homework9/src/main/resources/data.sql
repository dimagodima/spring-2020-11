insert into authors (author_id, author_name) values (1, 'Михаил Булгаков');
insert into authors (author_id, author_name) values (2, 'Лев Толстой');
insert into genres (genre_id, genre_name) values (1, 'роман');
insert into genres (genre_id, genre_name) values (2, 'фантастика');
insert into books (id, name, genre_id, author_id) values (1, 'Мастер и Маргарита',1,1);
insert into comments (comment_id, comment, id) values (1, 'Хорошая книга',1);
insert into comments (comment_id, comment, id) values (2, 'Прекрасная книга',1);
