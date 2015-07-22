DROP TABLE sequences;
DROP TABLE user;

CREATE TABLE sequences (
	seq_name	VARCHAR(40)	PRIMARY KEY,
	seq_number	INTEGER NOT NULL
);
INSERT INTO sequences (seq_name, seq_number) values ('USER_ID', 0);

CREATE TABLE user (
	id			INTEGER PRIMARY KEY,
	name		TEXT NOT NULL,
	email		TEXT NOT NULL,
	password	TEXT NOT NULL
);