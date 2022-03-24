INSERT INTO videojuegos(nombre) VALUES ('SNAKE');
INSERT INTO videojuegos(nombre) VALUES ('COD');
INSERT INTO videojuegos(nombre) VALUES ('FIFA');
INSERT INTO videojuegos(nombre) VALUES ('CSGO');


INSERT INTO clientes (nombre, apellido,email,videojuego_id) VALUES ('Patri','Jerez','pa@gmail.com',1);
INSERT INTO clientes (nombre, apellido,email,videojuego_id) VALUES ('Ana','García','ann@gmail.com',1);
INSERT INTO clientes (nombre, apellido,email,videojuego_id) VALUES ('Celia','García','cel@gmail.com',1);
INSERT INTO clientes (nombre, apellido,email,videojuego_id) VALUES ('Mati','Suárez','mat@gmail.com',2);
INSERT INTO clientes (nombre, apellido,email,videojuego_id) VALUES ('Juan','Fernández','jj@gmail.com',3);
INSERT INTO clientes (nombre, apellido,email,videojuego_id) VALUES ('Chube','Dieguez','chuwaca@gmail.com',4);
INSERT INTO clientes (nombre, apellido,email,videojuego_id) VALUES ('Carmen','Inguanzo','car@gmail.com',4);
INSERT INTO clientes (nombre, apellido,email,videojuego_id) VALUES ('Lola','Mento','ll@gmail.com',3);
INSERT INTO clientes (nombre, apellido,email,videojuego_id) VALUES ('Pedro','Fernández','pf@gmail.com',3);
INSERT INTO clientes (nombre, apellido,email,videojuego_id) VALUES ('Marta','Dieguez','ma@gmail.com',1);

INSERT INTO usuarios (username,password,enabled) VALUES ('mati','$2a$10$6XQSU8850B6TZOwRkaSH7OiWw2crpVrLboM78HxngI9YYCF.V4cCe',1);
INSERT INTO usuarios (username,password,enabled) VALUES ('benze','$2a$10$ab4slqHL0rsVJLkmcEZfduFV1AqQP1dzI6LapGKDNg8BtR.AKi6RW',1);

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (2,1);