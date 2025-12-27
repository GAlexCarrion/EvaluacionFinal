-- Insertar usuarios iniciales
INSERT INTO usuarios (username, password, rol) VALUES
                                                   ('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVwSgC', 'ADMIN'),
                                                   ('docente1', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVwSgC', 'DOCENTE'),
                                                   ('estudiante1', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVwSgC', 'ESTUDIANTE');

-- Insertar cursos iniciales
INSERT INTO cursos (nombre, cupo, descripcion, docente_id) VALUES
                                                               ('Programación Java', 30, 'Curso introductorio a Java', 2),
                                                               ('Base de Datos', 25, 'Fundamentos de bases de datos relacionales', 2),
                                                               ('Spring Framework', 20, 'Desarrollo web con Spring Boot', 2);