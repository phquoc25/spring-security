CREATE TABLE CAR
(
    ID bigint NOT NULL,
    REGISTRATION_NUMBER VARCHAR(11) NOT NULL,
    COMPANY_ID bigint,
    CONSTRAINT CAR_COMPANY_ID_fk FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY (ID)
);
CREATE UNIQUE INDEX CAR_ID_uindex ON CAR (ID);
CREATE UNIQUE INDEX CAR_REGISTRATION_NUMBER_uindex ON CAR (REGISTRATION_NUMBER);
