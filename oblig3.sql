DROP SCHEMA IF EXISTS oblig3 CASCADE;

CREATE SCHEMA oblig3;
SET search_path TO oblig3;

CREATE TABLE employee (
  id SERIAL PRIMARY KEY,
  username VARCHAR(4) NOT NULL,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL,
  hiredDate DATE,
  position VARCHAR NOT NULL,
  monthlySalary DECIMAL(10, 2),
  departmentId INTEGER
);

CREATE TABLE department (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  leaderId INTEGER,
  CONSTRAINT fkLeader FOREIGN KEY (leaderId) REFERENCES employee(id) ON DELETE RESTRICT
);

ALTER TABLE employee
ADD CONSTRAINT fkDepartment FOREIGN KEY (departmentId) REFERENCES department(id) ON DELETE RESTRICT;

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

UPDATE department SET leaderId = 1 WHERE id = 1;
UPDATE department SET leaderId = 4 WHERE id = 2;
UPDATE department SET leaderId = 5 WHERE id = 3;

-- CREATE TABLE project (
--   id SERIAL PRIMARY KEY,
--   name VARCHAR(50) NOT NULL,
--   description TEXT NOT NULL
-- );

-- CREATE TABLE projectWork (
--   id SERIAL PRIMARY KEY,
--   employeeId INTEGER,
--   projectId INTEGER,
--   hoursWorked INTEGER,
--   CONSTRAINT fkEmployee FOREIGN KEY (employeeId) REFERENCES employee(id) ON DELETE RESTRICT,
--   CONSTRAINT fkProjectId FOREIGN KEY (projectId) REFERENCES project(id) ON DELETE RESTRICT
-- );
