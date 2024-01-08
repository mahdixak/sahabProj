
USE logdb;

CREATE TABLE Log (
    LogDate varchar(10),
    LogTime varchar(10),
    LogNumber int,
    thread varchar(255),
    info varchar(255),
    package varchar(255),
    argument varchar(255)
);