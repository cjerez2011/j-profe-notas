create database colegio;

use colegio;

create table alumno(
	id int auto_increment, 
	rut varchar(12),
	nombre varchar(50),
	ape_pat varchar(50),
	ape_mat varchar(50),
	edad int,
	sexo varchar(1),
	primary key(id)
);

create table profesion(
	id int auto_increment,
	nombre varchar(50),
	primary key(id)
);

create table profesor(
	rut varchar(12),
	nombre varchar(50),
	ape_pat varchar(50),
	ape_mat varchar(50),
	edad int,
	sexo varchar(1),
	clave blob,
	primary key(rut)
);

create table profesion_profesor(
	id int auto_increment,
	profesor varchar(12),
	profesion int,
	primary key(id),
	foreign key(profesor) references profesor(rut),
	foreign key(profesion) references profesion(id)
);

create table curso(
	id int auto_increment,
	nombre varchar(200),
	profesor varchar(13), /*ojo*/
	primary key(id),
	foreign key(profesor) references profesor(rut)
);

create table curso_alumno(
	id int auto_increment,
	alumno int,
	curso int,
	primary key(id),
	foreign key(alumno) references alumno(id),
	foreign key(curso) references curso(id)
);

create table asistencia(
	id int auto_increment,
	alumno int,
	curso int,
	fecha datetime,
	asistio boolean,
	primary key(id),
	foreign key(alumno) references alumno(id),
	foreign key(curso) references curso(id)
);

create table notas(
	id int auto_increment,
	nota int,
	porcentaje_nota int,
    descripcion varchar(50),
	notaPonderada float,
	primary key(id),
	alumno int,
	curso int,
	foreign key(alumno) references alumno(id),
	foreign key(curso) references curso(id)
);





insert into alumno values(null, '17687328-0', 'Diego Andres', 'Soto', 'Banda' ,'23' ,'m');
insert into alumno values(null, '17828479-7', 'Manuel Ignacio', 'Ortega', 'Catalán' ,'23' ,'m');
insert into alumno values(null, '19018356-4', 'Fabian Ignacio', 'Droguett', 'Badilla' ,'19' ,'m');

insert into profesion values(null, 'Ingenieria en informática');
insert into profesion values(null, 'Analista Programador');
insert into profesion values(null, 'Ingenieria civil en informática');

insert into profesor values('16828943-k','Patricio Nicolás','Pérez','Pinto','26','m', AES_ENCRYPT('123456','llave'));
insert into profesor values('22222222-2','Chun','Hau','Lai','29','m', AES_ENCRYPT('123456','llave'));

insert into profesion_profesor values(null, '16828943-k','1');
insert into profesion_profesor values(null, '16828943-k','2');
insert into profesion_profesor values(null, '22222222-2','3');

insert into curso values(null, 'Matemáticas','16828943-k');
insert into curso values(null, 'Poo','22222222-2');

/*Alumnos en el curso del profe pato*/
insert into curso_alumno values(null, '1','1');
insert into curso_alumno values(null, '2','1');
insert into curso_alumno values(null, '3','1');

/*Alumnos en el curso del profe lai*/
insert into curso_alumno values(null, '1','2');
insert into curso_alumno values(null, '2','2');
insert into curso_alumno values(null, '3','2');

/*insert into asistencia values(null, '1', '1', now(), true);
insert into asistencia values(null, '2', '1', now(), false);
insert into asistencia values(null, '3', '1', now(), true);*/

insert into asistencia values(null, '1', '1', '2014-08-10', true);
insert into asistencia values(null, '2', '1', '2014-08-10', false);
insert into asistencia values(null, '3', '1', '2014-08-10', true);

insert into asistencia values(null, '1', '2', '2014-08-10', false);
insert into asistencia values(null, '2', '2', '2014-08-10', false);
insert into asistencia values(null, '3', '2', '2014-08-10', true);


insert into notas values(null,'60','10', 'Trabajo practico',  '6','1','1');
insert into notas values(null,'70','40'  ,'Prueba 1', '28' ,'2','2');
insert into notas values(null,'65','30','prueba 1','19.5','3','2');



select * from alumno ;
select * from profesion;
select * from profesor;
select * from profesion_profesor;
select * from curso;
select * from curso_alumno;
select * from asistencia;
select * from notas;



drop database colegio;

drop table asistencia;
drop table curso_alumno;
drop table curso;
drop table profesion_profesor;
drop table profesor;
drop table profesion;
drop table alumno;
drop table notas;

/*Consultas*/
/*1.- Cursos de un profesor*/
select curso.id, curso.nombre
from curso, profesor
where curso.profesor = profesor.rut and
profesor.rut = '16828943-k';

/*2.- Alumnos de un curso determinado*/
select a.id, a.rut, a.nombre, a.ape_pat, 
a.ape_mat, a.edad, a.sexo
from alumno a, curso c, curso_alumno ca
where c.id = ca.curso and ca.alumno = a.id 
and c.id = 1;

/*3.- Asistencia de un curso determinado en una
fecha determinada*/
select a.id, a.rut, a.nombre, a.ape_pat, 
a.ape_mat, a.edad, a.sexo, asis.fecha, asis.asistio
from curso c, asistencia asis, alumno a
where c.id = asis.curso and
asis.alumno = a.id and
c.id = '1' and
asis.fecha = '2014-08-10';

/*4.- Profesiones de un profesor determinado*/
select profesion.id, profesion.nombre
from profesor, profesion, profesion_profesor
where profesor.rut = profesion_profesor.profesor and
profesion.id = profesion_profesor.profesion and
profesor.rut = '22222222-2';

/*5.- Historial de asistencia de un alumno determinado*/
select a.id, a.rut, a.nombre, a.ape_pat, 
a.ape_mat, a.edad, a.sexo, asis.fecha, asis.asistio,
c.nombre as 'curso'
from curso c, asistencia asis, alumno a
where c.id = asis.curso and
asis.alumno = a.id and
a.rut = '19018356-4';

/*6.- Historial de asistencia de un alumno determinado y
de un curso determinado*/
select a.id, a.rut, a.nombre, a.ape_pat, 
a.ape_mat, a.edad, a.sexo, asis.fecha, asis.asistio,
c.nombre as 'curso'
from curso c, asistencia asis, alumno a
where c.id = asis.curso and
asis.alumno = a.id and
a.rut = '19018356-4' and
c.id = '2';


/*Pruebas*/
select a.id, a.rut, a.nombre, a.ape_pat, a.ape_mat, a.edad, 
a.sexo, asis.fecha, asis.asistio 
from curso c, asistencia asis, alumno a 
where c.id = asis.curso and asis.alumno = a.id 
and c.id = '4' and asis.fecha between '2014-10-08 00:00:00' 
and '2014-10-08 23:59:59';

/* prueba inicio sesion   */
select * from profesor
where clave = AES_ENCRYPT('123456','llave') and
rut = '16828943-k';



/*Select notas */

/* 1.- ver notas de un alumno por asignatura */


select   a.rut, a.nombre, a.ape_pat, a.ape_mat, b.nota, b.descripcion, c.nombre as 'Curso'  from alumno a, notas b, curso c
where b.alumno = a.id  and b.curso = c.id and c.id = 2;





/*2.- ver promedio de un curso   */


select   round(sum(nota)/count(nota))      from   notas 
 where  notas.curso = 2;

select sum(notaPonderada) from notas
where notas.alumno ='2' and notas.curso='2';

select sum(notaponderada) from notas
where notas.alumno ='1' and notas.curso='2';



/* select     a.nombre, b.nota     from */


/* 3.- ver todas las notas alumno */


select   a.rut, a.nombre, a.ape_pat, a.ape_mat, b.nota,b.descripcion, c.nombre as 'Curso'  from alumno a, notas b, curso c
where  b.curso = c.id and a.rut =  '17687328-0' ;

/* 4.- Ver información de alumnos bajo y alto rendimiento */

select 
    id
from
    alumno
where
    alumno.rut = 17687328 - 0;


 



select sum(notaponderada) from notas,alumno where alumno.rut ='1' and notas.curso='2'
