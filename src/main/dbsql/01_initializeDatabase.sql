
CREATE TABLE User (
    Id varchar(80) not null,
    Name varchar(80) null,
    Email varchar(80) null,
    CONSTRAINT pk_user PRIMARY KEY (Id)
);

CREATE TABLE Offer (
    Id varchar(80) not null,
    Title varchar(100) not null,
    Description text not null,
    Category varchar(80) not null,
    Owner varchar(80),
    CreationTime DATE not null,
    CONSTRAINT pk_offer PRIMARY KEY (Id)
);