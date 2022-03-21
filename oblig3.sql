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
  monthlySalary DECIMAL(10, 2)
  -- departmentId INTEGER
);

INSERT INTO employee
  (username, firstname, lastname, hiredDate, position, monthlySalary)
VALUES
  ('andf', 'Anders', 'Fimreite', NOW(), 'Developer', 100000.00),
  ('jorf', 'Jørgen', 'Fjølstad', NOW(), 'Developer', 100000.00),
  ('erig', 'Eric', 'Gjerstad', NOW(), 'Developer', 100000.00);


-- CREATE TABLE department (
--   id SERIAL PRIMARY KEY,
--   name VARCHAR(50) NOT NULL,
--   leaderId INTEGER,
--   CONSTRAINT fkLeader FOREIGN KEY (leaderId) REFERENCES employee(id) ON DELETE RESTRICT
-- );

-- ALTER TABLE employee
-- ADD CONSTRAINT fkDepartment FOREIGN KEY (departmentId) REFERENCES department(id) ON DELETE RESTRICT;

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
