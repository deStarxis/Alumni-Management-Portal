INSERT INTO address (city, street, zip, state, is_deleted)
VALUES ('FairField', '1000 N 4', 52557, 'Iowa', false),
       ('Burlington', '1000 N 4', 52554, 'Kansas', false),
       ('Kathmandu', '1000 Baneshor', 44800, 'California', false),
       ('Kaski', 'pokhara', 52414, 'Texas', false),
       ('Fairfield', '100Nth st', 85858, 'Iowa', false),
       ('Chicago', '100Nth st', 74747, 'Illinois', false);
INSERT INTO person (firstname, lastname, username, password, address_id, is_active) --password: asdf
VALUES ('Admin', 'User', 'admin@gmail.com', '$2a$10$npioSlnpFi43eZDYrGk.p.ax672jdzGXq9DA7sDMLtQ/jzPhlybsi', 1,
        true),
       ('Prabesh', 'KC', 'khssconfess@gmail.com', '$2a$10$npioSlnpFi43eZDYrGk.p.ax672jdzGXq9DA7sDMLtQ/jzPhlybsi', 1,
        true),
       ('Ram', 'bahadur', 'iamkarnashrestha@gmail.com', '$2a$10$npioSlnpFi43eZDYrGk.p.ax672jdzGXq9DA7sDMLtQ/jzPhlybsi',
        null, true),
       ('Faculty', 'bahadur', 'faculty@gmail.com', '$2a$10$npioSlnpFi43eZDYrGk.p.ax672jdzGXq9DA7sDMLtQ/jzPhlybsi', null,
        true),
       ('Student', 'bahadur', 'student@gmail.com', '$2a$10$npioSlnpFi43eZDYrGk.p.ax672jdzGXq9DA7sDMLtQ/jzPhlybsi', 3,
        true),
       ('Bidyarthi', 'bahadur', 'bidyarthi@gmail.com', '$2a$10$npioSlnpFi43eZDYrGk.p.ax672jdzGXq9DA7sDMLtQ/jzPhlybsi',
        4, true),
       ('Nishesh', 'bahadur', 'nishesh@gmail.com', '$2a$10$npioSlnpFi43eZDYrGk.p.ax672jdzGXq9DA7sDMLtQ/jzPhlybsi',
        4, true);

INSERT INTO student(id, graduated_year, major, university_id, gpa)
VALUES (2, 2022, 'CS', 6666, 3),
       (3, 2022, 'MBA', 9999, 3),
       (5, 2022, 'BBA', 1111, 2.5),
       (6, 2022, 'MBA', 2222, 3.5);

INSERT INTO faculty(id, major_subject)
VALUES (4, 'CS'),
       (7,'MBA');
INSERT INTO role (role, is_deleted)
VALUES ('Admin', false),
       ('Student', false),
       ('Faculty', false);


INSERT INTO person_roles(person_id, roles_id)
VALUES (1, 1),
       (2, 2),
       (3, 2),
       (4, 3),
       (5, 2),
       (6, 2),
       (7,3);

INSERT INTO company(name, address_id, is_deleted)
VALUES ('Meta', 5, false),
       ('Apple Inc.', 6, false);

INSERT INTO job(job_Type, job_Title, description, address_id, company_id, student_id, is_deleted)
VALUES ('Remote', 'software engineer', 'hello', 5, 1, 2, false),
       ('Contract', 'QA', 'desc', 6, 2, 2, false),
       ('Hybrid', 'Product Manager', 'desc', 6, 2, 2, false),
       ('Onsite', 'Product Manager', 'desc', 2, 2, 3, false);

INSERT INTO job_application(job_id, student_id, applied_date)
VALUES (1, 5, '2022/12/12 10:00:00'),
       (1, 6, '2022/12/13 11:00:00'),
       (1, 2, '2022/12/14 10:00:00'),
       (2, 3, '2022/12/15 13:00:00'),
       (2, 5, '2022/12/16 14:00:00'),
       (3, 6, '2022/12/16 15:00:00');

INSERT INTO tag (tag)
VALUES ('java'),
       ('javascript'),
       ('java spring'),
       ('java spring boot'),
       ('hibernate'),
       ('.net'),
       ('.net core'),
       ('.net framework'),
       ('php'),
       ('python'),
       ('remote'),
       ('ruby'),
       ('react'),
       ('redux'),
       ('react native'),
       ('onsite'),
       ('web'),
       ('backend'),
       ('front end');

INSERT INTO job_tags(job_id, tags_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (3, 1),
       (3, 3),
       (3, 4),
       (3, 5),
       (3, 6);
INSERT INTO comment(faculty_id,student_id,comment,is_deleted)
VALUES (4,2,'You are Good',false),
       (4,3,'You are bad',false);