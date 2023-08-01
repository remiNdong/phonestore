delete from user_role;
delete from user;
delete from role;

insert into role values(1,"ADMIN");
insert into role values(2,"EMP");
insert into role values(3,"USER");


insert into user values(1,"gerant@hotmail.fr","Jean", "Legérant", "0601020304", "$2a$10$MeegwVkuyLpdRs7HmW3Tw.zo4vLrwEkRSVN4GqYRTdPq0jPXXMvTi");
insert into user values(2,"employe@hotmail.fr", "John", "Lebosseur", "0611121314", "$2a$10$qTL0g1GChNcGxVx9D/C3F.USubJ3h2q/F0TMZK3tqoJqPNwVa.acK");
insert into user values(3,"client@hotmail.fr", "Jack", "Leclient", "0621222324", "$2a$10$Xeas1YHf3GdJqwnNz4pliOy56/tylnUZ5nHHwufNREurVWZZyVTtW");

insert into user_role values (1,1);
insert into user_role values (2,2);
insert into user_role values (3,3);
