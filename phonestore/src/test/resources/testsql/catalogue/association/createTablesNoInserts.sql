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
