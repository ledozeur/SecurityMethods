create schema if not exists netology;

create table if not exists netology.person (
name varchar(100) not null,
surname varchar(100) not null,
age int  not null,
phone_number varchar(11) not null,
city_of_living varchar(100) not null,
PRIMARY KEY (name,surname,age)
);

insert into netology.person (name,surname,age,phone_number,city_of_living)
values
('Ivan','Ivanov',30,'89999999999','Moscow'),
('Petr','Petrov',25,'89999999998','Kazan'),
('Alexander','Sidorov',27,'89999999997','Saratov'),
('Olga','Sokolova',35,'89999999996','Saint Petersburg'),
('Maria','Kotova',33,'89999999995','Vladivostok'),
('Alexandr','Kuznetsov',30,'8999999994','Smolensk');

