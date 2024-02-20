create table if not exists category(
id int PRIMARY KEY AUTO_INCREMENT,
name varchar(255),
description varchar(255)
);
create table if not exists product(
id int PRIMARY KEY AUTO_INCREMENT,
name varchar(255),
price double,
description varchar(255),
categoryid int,
FOREIGN KEY (categoryid) REFERENCES category(id)
);