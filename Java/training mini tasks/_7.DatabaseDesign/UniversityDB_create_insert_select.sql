CREATE
DATABASE university;

-- Таблица "Преподаватели"
CREATE TABLE teachers
(
    TEACHER_ID   SERIAL PRIMARY KEY,
    FULL_NAME    VARCHAR(100) NOT NULL,
    PHONE_NUMBER VARCHAR(20),
    EMAIL        VARCHAR(50)
);

-- Таблица "Курсы"
CREATE TABLE courses
(
    COURSE_ID   SERIAL PRIMARY KEY,
    COURSE_NAME VARCHAR(100) NOT NULL,
    TEACHER_ID  INT UNIQUE   NOT NULL,
    DESCRIPTION TEXT,
    FOREIGN KEY (TEACHER_ID) REFERENCES teachers (TEACHER_ID)
);

-- Таблица "Студенты"
CREATE TABLE students
(
    STUDENT_ID   SERIAL PRIMARY KEY,
    FULL_NAME    VARCHAR(100) NOT NULL,
    PHONE_NUMBER VARCHAR(20),
    EMAIL        VARCHAR(50),
    COURSE_ID    INT          NOT NULL,
    FOREIGN KEY (COURSE_ID) REFERENCES courses (COURSE_ID)
);

-- Вставка преподавателей
INSERT INTO teachers (FULL_NAME, PHONE_NUMBER, EMAIL)
VALUES ('Анна Игоревна Ковалёва', '+7-900-123-45-67', 'anna.kovaleva@university.ru'),
       ('Игорь Николаевич Петров', '+7-900-987-65-43', 'igor.petrov@university.ru'),
       ('Мария Владимировна Сидорова', '+7-911-222-33-44', 'maria.sidorova@university.ru');

-- Вставка курсов
INSERT INTO courses (COURSE_NAME, TEACHER_ID, DESCRIPTION)
VALUES ('Введение в базы данных', 1, 'Основы проектирования и работы с базами данных'),
       ('Философия', 2, 'Углубленное изучение работы с базами данных'),
       ('Математический анализ', 3, 'Дифференциальное и интегральное исчисление');

-- Вставка студентов
INSERT INTO students (FULL_NAME, PHONE_NUMBER, EMAIL, COURSE_ID)
VALUES ('Мария Соколова', '+7-911-111-22-33', 'maria.sokolova@university.ru', 1),
       ('Алексей Ветров', '+7-922-333-44-55', 'alexey.vetrov@university.ru', 1),
       ('Елена Орлова', '+7-933-555-66-77', 'elena.orlova@university.ru', 2),
       ('Дмитрий Новиков', '+7-944-777-88-99', 'dmitry.novikov@university.ru', 3);

-- Посмотреть всех студентов с их курсами и преподавателями
SELECT
    s.FULL_NAME as "Студент",
    c.COURSE_NAME as "Курс",
    t.FULL_NAME as "Преподаватель"
FROM students s
         JOIN courses c ON s.COURSE_ID = c.COURSE_ID
         JOIN teachers t ON c.TEACHER_ID = t.TEACHER_ID
ORDER BY c.COURSE_NAME, s.FULL_NAME;