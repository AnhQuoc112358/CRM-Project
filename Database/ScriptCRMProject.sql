CREATE DATABASE crm_app;
USE crm_app;

CREATE TABLE IF NOT EXISTS roles (
    id INT  AUTO_INCREMENT,
    name VARCHAR(50) ,
    description VARCHAR(100),
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS users (
    id INT  AUTO_INCREMENT,
    username VARCHAR(100) ,
    firstname VARCHAR(100) ,
    lastname VARCHAR(100) ,
    email VARCHAR(100) ,
    password VARCHAR(100) ,
    phone_number VARCHAR(20) ,
    country VARCHAR(20) ,
    avatar VARCHAR(100),
    role_id INT ,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS status (
    id INT  AUTO_INCREMENT,
    name VARCHAR(50) ,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS projects (
    id INT  AUTO_INCREMENT,
    name VARCHAR(50) ,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tasks (
    id INT  AUTO_INCREMENT,
    name VARCHAR(50) ,
    start_date DATE,
    end_date DATE,
    user_id INT ,
    project_id INT,
    status_id INT,
    PRIMARY KEY (id)
);

ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (user_id) REFERENCES users (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (project_id) REFERENCES projects (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (status_id) REFERENCES status (id)  ON DELETE CASCADE;

INSERT INTO roles( name, description ) 
VALUES ("Member", "Nhân viên"),
("Manager", "Quản lý"),
("Admin", "Quản trị hệ thống");

INSERT INTO status (name) VALUES ('Not started'), ('In progress'), ('Completed');

INSERT INTO users (email,password,username,firstname,lastname,phone_number,country, role_id) 
VALUES  ('anhquoc11358@gmail.com', 'quoc123', 'anhquoc11358', 'Quoc', 'Nguyen Anh','08041897785', 'Vietnam', '3'),
('johnsmith@gmail.com', 'John123', 'John1995', 'John', 'Smith','0762813516','America','2'),
('jamestaylor@gmail.com', 'James123', 'James2000', 'James', 'Taylor', '0583164882', 'England','1'),
('john.doe@gmail.com', 'Pass123', 'johndoe123', 'John', 'Doe', '08041897785', 'USA', 1),
('jane.smith@gmail.com', 'Password456', 'janesmith456', 'Jane', 'Smith', '08041122334', 'Canada', 1),
('david.johnson@gmail.com', 'SecretPass', 'david123', 'David', 'Johnson', '08042998877', 'Australia', 1),
('sarah.brown@gmail.com', 'SecurePass', 'sarahb', 'Sarah', 'Brown', '08043999888', 'UK', 2),
('michael.lee@gmail.com', 'StrongPass123', 'michaellee', 'Michael', 'Lee', '08045551111', 'Germany', 1),
('emma.wong@gmail.com', 'TopSecret', 'emma123', 'Emma', 'Wong', '08044441111', 'France', 1),
('oliver.garcia@gmail.com', 'Pass12345', 'oliverg', 'Oliver', 'Garcia', '08048889999', 'Spain', 1),
('sophia.kim@gmail.com', 'SecurePassword', 'sophiakim', 'Sophia', 'Kim', '08042223333', 'Italy', 1),
('ethan.chen@gmail.com', 'Safe1234', 'ethanc', 'Ethan', 'Chen', '08049991111', 'Brazil', 1),
('isabella.lopez@gmail.com', 'SecretPasscode', 'isabellal', 'Isabella', 'Lopez', '08041114444', 'Mexico', 1);

INSERT INTO projects (name,start_date,end_date) 
VALUES ('Yuuchou Bank Web', '2023-01-01', '2023-07-20'),
('Datbike Website', '2023-04-01', NULL);

INSERT INTO tasks (name, start_date, end_date, user_id, project_id, status_id)
VALUES
('Gather Requirements for Yuuchou Bank Web', '2023-01-01', '2023-01-20', 1, 1, 3),
('Market Research for Banking Industry', '2023-01-21', '2023-02-10', 2, 1, 3),
('Plan Yuuchou Bank Web Structure', '2023-02-11', '2023-02-28', 3, 1, 3),
('Design User Interface for Yuuchou Bank Web', '2023-03-01', '2023-03-20', 4, 1, 3),
('Develop Yuuchou Bank Web', '2023-03-21', '2023-04-10', 5, 1, 3),
('Test Yuuchou Bank Web Functionality', '2023-04-11', '2023-04-30', 6, 1, 3),
('Optimize Performance of Yuuchou Bank Web', '2023-05-01', '2023-05-20', 3, 1, 3),
('Enhance Security for Yuuchou Bank Web', '2023-05-21', '2023-06-10', 4, 1, 3),
('Deploy and Manage Yuuchou Bank Web', '2023-06-11', '2023-07-11', 5, 1, 3),
('Test and Adjust Yuuchou Bank Web', '2023-07-12', '2023-07-20', 6, 1, 3);

INSERT INTO tasks (name, start_date, end_date, user_id, project_id, status_id)
VALUES
('Gather Requirements for Datbike Website', '2023-04-01', '2023-04-15', 7, 2, 3),
('Market Research for Electric Bikes', '2023-04-16', '2023-04-30', 8, 2, 3),
('Plan Datbike Website Structure', '2023-05-01', '2023-05-15', 9, 2, 3),
('Design User Interface for Datbike Website', '2023-05-16', null, 10, 2, 2),
('Develop Datbike Website', '2023-06-01', null, 11, 2, 2),
('Database Design for Datbike Website', '2023-06-16', '2023-06-30', 12, 2, 3),
('Test Datbike Website Functionality', '2023-07-01', '2023-07-15', 13, 2, 3),
('Optimize Performance of Datbike Website', '2023-07-16', null, 7, 2, 2),
('Enhance Security for Datbike Website', null, null, 8, 2, 1),
('Deploy and Manage Datbike Website', null, null, 9, 2, 1);
