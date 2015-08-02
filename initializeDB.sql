DROP TABLE sequences;
DROP TABLE user;
DROP TABLE role;
DROP TABLE user_role;

CREATE TABLE sequences (
	seq_name	VARCHAR(40)	PRIMARY KEY,
	seq_number	INTEGER NOT NULL
);
INSERT INTO sequences (seq_name, seq_number) values ('USER_ID', 1000);

CREATE TABLE user (
	id			INTEGER PRIMARY KEY,
	login		TEXT NOT NULL,
	password	TEXT NOT NULL
);

CREATE TABLE role (
    id          INTEGER PRIMARY KEY,
    name        TEXT NOT NULL
);

CREATE TABLE user_role (
    user_id     INTEGER NOT NULL REFERENCES user(id) ON DELETE CASCADE,
    role_id     INTEGER NOT NULL REFERENCES role(id) ON DELETE CASCADE,
    CONSTRAINT pk_user_role PRIMARY KEY (user_id, role_id)
);

INSERT INTO user (id, login, password) values (1, 'admin', 'admin');
INSERT INTO role (id, name) values (2, 'admin');
INSERT INTO user_role (user_id, role_id) values (1, 2);