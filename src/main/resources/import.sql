EXEC init_jeu_essai;

insert into utilisateurs (username, password) values ('bob', '{bcrypt}$2a$10$ZBHkEf4ebi8TjBShnfzCKevUVMkssHTD4hjL8u9xy6As0E8HitOs2');
insert into roles(role_name) values('ROLE_ADMIN');
insert into roles(role_name) values('ROLE_USER');
INSERT INTO utilisateurs_roles (no_utilisateur, no_role) values(1,1);

