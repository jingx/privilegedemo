
CREATE TABLE "Privileges"(
  id INTEGER PRIMARY KEY,
  name VARCHAR2(50),
  path VARCHAR2(200)
);

CREATE SEQUENCE Seq_pid;

CREATE TABLE Roles(
  id INTEGER PRIMARY KEY,
  name VARCHAR2(50)
);

CREATE SEQUENCE Seq_rid;

CREATE TABLE Roles_Privileges(
  id INTEGER PRIMARY KEY,
  r_id INTEGER REFERENCES Roles(id),
  p_id INTEGER REFERENCES "Privileges"(id)
);

CREATE SEQUENCE Seq_rpid;

CREATE TABLE Accounts(
  id INTEGER PRIMARY KEY,
  name VARCHAR2(50),
  pass VARCHAR2(50),
  r_id INTEGER REFERENCES Roles(id)
);

CREATE SEQUENCE Seq_aid;


----------------------------

-- As an administrator, can add privileges, so that...

INSERT INTO "Privileges"(id, name) VALUES(Seq_pid.Nextval, 'Add');
INSERT INTO "Privileges"(id, name) VALUES(Seq_pid.Nextval, 'Del');
INSERT INTO "Privileges"(id, name) VALUES(Seq_pid.Nextval, 'Modify');
INSERT INTO "Privileges"(id, name) VALUES(Seq_pid.Nextval, 'Query');
INSERT INTO "Privileges"(id, name) VALUES(Seq_pid.Nextval, 'Login');
INSERT INTO "Privileges"(id, name) VALUES(Seq_pid.Nextval, 'Register');

-- As an administrator, can ....

SELECT * FROM "Privileges";

INSERT INTO Roles VALUES(Seq_rid.Nextval, 'Admin');
INSERT INTO Roles VALUES(Seq_rid.Nextval, 'User');
INSERT INTO Roles VALUES(Seq_rid.Nextval, 'Guest');

SELECT * FROM Roles;


-- As an administrator, can 授权 

INSERT INTO Roles_Privileges VALUES(Seq_rpid.Nextval,1,1);
INSERT INTO Roles_Privileges VALUES(Seq_rpid.Nextval,1,2);
INSERT INTO Roles_Privileges VALUES(Seq_rpid.Nextval,1,3);
INSERT INTO Roles_Privileges VALUES(Seq_rpid.Nextval,1,4);
INSERT INTO Roles_Privileges VALUES(Seq_rpid.Nextval,1,5);
INSERT INTO Roles_Privileges VALUES(Seq_rpid.Nextval,1,6);

INSERT INTO Roles_Privileges VALUES(Seq_rpid.Nextval,2,4);
INSERT INTO Roles_Privileges VALUES(Seq_rpid.Nextval,2,5);
INSERT INTO Roles_Privileges VALUES(Seq_rpid.Nextval,2,6);

INSERT INTO Roles_Privileges VALUES(Seq_rpid.Nextval,3,5);

SELECT * FROM Roles_Privileges;

-- 


SELECT name FROM "Privileges" WHERE id in (SELECT p_id FROM Roles_Privileges WHERE r_id=(SELECT id FROM Roles WHERE name='guest'));

--add a comment here by jingx @ 20090319




