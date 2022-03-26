DROP SCHEMA IF EXISTS oblig3 CASCADE;

CREATE SCHEMA oblig3;
SET search_path TO oblig3;

CREATE TABLE employee (
  id SERIAL PRIMARY KEY,
  username VARCHAR(4) UNIQUE NOT NULL,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL,
  hiredDate DATE,
  position VARCHAR NOT NULL,
  monthlySalary DECIMAL(10, 2),
  departmentId INTEGER
);

CREATE TABLE department (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) UNIQUE NOT NULL,
  leaderId INTEGER,
  CONSTRAINT fkLeader FOREIGN KEY (leaderId) REFERENCES employee(id) ON DELETE RESTRICT
);

ALTER TABLE employee
ADD CONSTRAINT fkDepartment FOREIGN KEY (departmentId) REFERENCES department(id) ON DELETE RESTRICT;

CREATE TABLE project (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  description TEXT NOT NULL
);

CREATE TABLE projectParticipation (
  id SERIAL PRIMARY KEY,
  employeeId INTEGER,
  projectId INTEGER,
  hoursWorked INTEGER,
  role VARCHAR(50),
  CONSTRAINT fkEmployee FOREIGN KEY (employeeId) REFERENCES employee(id) ON DELETE RESTRICT,
  CONSTRAINT fkProjectId FOREIGN KEY (projectId) REFERENCES project(id) ON DELETE RESTRICT
);

INSERT INTO project (name, description) VALUES
  ('Website', 'Create a website for the company'),
  ('App', 'Create an app for the company');

INSERT INTO department (name) VALUES
  ('Software Development'),
  ('Accounting'),
  ('Customer Service');

INSERT INTO employee (username, firstname, lastname, hiredDate, position, monthlySalary, departmentId) VALUES
  ('andf', 'Anders', 'Fimreite', NOW(), 'Developer', 100000.00, 1),
  ('jorf', 'Jorgen', 'Fjolstad', NOW(), 'Developer', 100000.00, 1),
  ('erig', 'Eric', 'Gjerstad', NOW(), 'Developer', 100000.00, 1),
  ('test', 'Test', 'Testesen', NOW(), 'Accountant', 10000.00, 2),
  ('olan', 'Ola', 'Nordmann', NOW(), 'Scammer', 1000000.00, 3);

INSERT INTO projectParticipation (employeeId, projectId, hoursWorked, role) VALUES
  (1, 1, 1, 'Leader'),
  (2, 1, 2, 'Developer'),
  (3, 2, 1, 'Leader'),
  (1, 2, 2, 'Developer');

UPDATE department SET leaderId = 1 WHERE id = 1;
UPDATE department SET leaderId = 4 WHERE id = 2;
UPDATE department SET leaderId = 5 WHERE id = 3;
