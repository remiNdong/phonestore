delete from user_role;
delete from user;
delete from role;

insert into role values(1,"ADMIN");
insert into role values(2,"EMP");
insert into role values(3,"USER");


insert into user values(1,"gerant@hotmail.fr","Jean", "Legérant", "0601020304", "$2a$10$MeegwVkuyLpdRs7HmW3Tw.zo4vLrwEkRSVN4GqYRTdPq0jPXXMvTi");
insert into user values(2,"employe@hotmail.fr", "John", "Lebosseur", "0611121314", "$2a$10$qTL0g1GChNcGxVx9D/C3F.USubJ3h2q/F0TMZK3tqoJqPNwVa.acK");
insert into user values(3,"client@hotmail.fr", "Jack", "Leclient", "0621222324", "$2a$10$Xeas1YHf3GdJqwnNz4pliOy56/tylnUZ5nHHwufNREurVWZZyVTtW");
insert into user values(4,"client2@hotmail.fr", "Julie", "Lacliente", "0621222324", "$2a$10$Xeas1YHf3GdJqwnNz4pliOy56/tylnUZ5nHHwufNREurVWZZyVTtW");

insert into user_role values (1,1);
insert into user_role values (2,2);
insert into user_role values (3,3);


delete from modeletelephone;
delete from associationmodelereparation;
delete from reparation;
delete from marque;

insert into marque  values (1,"APPLE");
insert into marque  values (2,"SAMSUNG");

insert into modeletelephone values (1,6.1,128,"iphone_14.jpg",869,"iphone_14",1);
insert into modeletelephone values (2,6.1,128,"iphone_13_pro.jpg",1199,"iphone_13_pro",1);
insert into modeletelephone values (3,6.7,128,"iphone_13_pro_max.jpg",1259,"iphone_13_pro_max",1);

insert into modeletelephone values (4,6.8,256,"galaxy_s23.jpg",1199,"galaxy_s23",2);
insert into modeletelephone values (5,6.1,256,"galaxy_s22.jpg",649,"galaxy_s22",2);

insert into reparation values(1,"remplacement écran");
insert into reparation values(2,"remplacement batterie");
insert into reparation values(3,"remplacement caméra avant");
insert into reparation values(4,"remplacement caméra arrière");
insert into reparation values(5,"remplacement micro");
insert into reparation values(6,"remplacement haut parleur");


insert into associationmodelereparation values(1,1,2,130,1);
insert into associationmodelereparation values(2,1,1,328,1);

delete from prestation;

insert into prestation values(1,3,1,"APPLEIPHONE14",130,"CREE",'2023-07-31 12:45:56');
insert into prestation values(2,3,2,"APPLEIPHONE14XX",328,"CREE",'2023-08-11 12:45:56');


