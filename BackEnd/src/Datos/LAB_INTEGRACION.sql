--TABLE SPACE DATA 
CREATE TABLESPACE LAB_INTEGRACION_DATA DATAFILE 'C:\Oracle18c\oradata\XE\LAB_INTEGRACION_DATA.dbf' SIZE 52M   ONLINE;
-- TABLE SPACE TEMP
CREATE TEMPORARY TABLESPACE LAB_INTEGRACION_TEMP TEMPFILE 'C:\Oracle18c\oradata\XE\LAB_INTEGRACION_TEMP.dbf' SIZE 18M  AUTOEXTEND ON ;
--CREAR UN SCHEMA NUEVO 

alter session set "_ORACLE_SCRIPT" =true;  

CREATE USER LAB_INTEGRACION IDENTIFIED BY root
       DEFAULT TABLESPACE LAB_INTEGRACION_DATA
       TEMPORARY TABLESPACE  LAB_INTEGRACION_TEMP;
       
       
GRANT RESOURCE TO LAB_INTEGRACION; 
GRANT CONNECT TO LAB_INTEGRACION;
GRANT ALL PRIVILEGES TO LAB_INTEGRACION; 





CREATE TABLE CURSOS(
    ID NUMBER(10) NOT NULL,
    DESCRIPCION VARCHAR2(100),
    CREDITOS VARCHAR2(100),
    CONSTRAINT PK_COD_CURSOS PRIMARY KEY(ID)
);

CREATE TABLE ESTUDIANTE(
    ID NUMBER(10) NOT NULL,
    NOMBRE VARCHAR2(100),
    APELLIDO VARCHAR2(100),
    EDAD VARCHAR2(100),
    CONSTRAINT PK_COD_ESTUDIANTE PRIMARY KEY (ID)    
);

CREATE TABLE ESTUDIANTECURSO(
    CODESTUDAINTE NUMBER(10) NOT NULL,
    CODCURSOS NUMBER(10) NOT NULL
);
    
    ALTER TABLE ESTUDIANTECURSO ADD CONSTRAINT FK_COD_ESTUDIANTE FOREIGN  KEY(CODESTUDAINTE) REFERENCES ESTUDIANTE(ID);
ALTER TABLE ESTUDIANTECURSO ADD CONSTRAINT FK_COD_CURSOS FOREIGN  KEY(CODCURSOS) REFERENCES CURSOS(ID);

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "SP_INSERTAESTUDIANTE" (
    p_id   IN   NUMBER,
    p_nombre   IN   VARCHAR2,
    p_apellido   IN   VARCHAR2,
    p_edad   IN   VARCHAR2
) IS
BEGIN
    INSERT INTO ESTUDIANTE(
        id,
        nombre,
        apellido,
        edad
    ) VALUES (
        p_id,
        p_nombre,
        p_apellido,
        p_edad
    );

END SP_INSERTAESTUDIANTE;

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "SP_INSERTAESTUDIANTECURSO" (
    p_codestudiante   IN   NUMBER,
    p_codcursos    IN   NUMBER
) IS
BEGIN
    INSERT INTO ESTUDIANTECURSO (
        CODESTUDAINTE,
        codcursos
    ) VALUES (
        p_codestudiante,
        p_codcursos
    );

 END SP_INSERTAESTUDIANTECURSO;   
 
 
 CREATE OR REPLACE NONEDITIONABLE PROCEDURE "SP_UPDATEESTUDIANTE" (
    p_id   IN   ESTUDIANTE.id%TYPE,
    p_nombre   IN   ESTUDIANTE.nombre%TYPE,
    p_apellido   IN   ESTUDIANTE.apellido%TYPE,
    p_edad   IN   ESTUDIANTE.edad%TYPE
) IS
BEGIN
    UPDATE ESTUDIANTE
    SET
        nombre = p_nombre,
        apellido = p_apellido,
        edad = p_edad
    WHERE
        id = p_id;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END SP_UPDATEESTUDIANTE;
 
 
 CREATE OR REPLACE NONEDITIONABLE PROCEDURE "SP_DELETEESTUDIANTE" (
    p_id IN ESTUDIANTE.id%TYPE
) IS
BEGIN
    DELETE FROM ESTUDIANTE
    WHERE
        id = p_id;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END SP_DELETEESTUDIANTE;
 
 
 CREATE OR REPLACE PACKAGE TYPES

AS

TYPE REF_CURSOR IS REF CURSOR;

END;

 
CREATE OR REPLACE NONEDITIONABLE FUNCTION listar_estudiantes RETURN types.ref_cursor AS
    cursor_estudiante types.ref_cursor;
BEGIN
    OPEN cursor_estudiante FOR SELECT
                               id,
                               nombre,
                               apellido,
                               edad
                           FROM
                               ESTUDIANTE;

    RETURN cursor_estudiante;
END;

CREATE OR REPLACE NONEDITIONABLE FUNCTION buscar_estudiantes
(p_id IN ESTUDIANTE.id%TYPE)
RETURN types.ref_cursor AS
    cursor_estudiante types.ref_cursor;
BEGIN
    OPEN cursor_estudiante FOR SELECT
                               id,
                               nombre,
                               apellido,
                               edad
                           FROM
                               ESTUDIANTE
                            WHERE id = p_id;

    RETURN cursor_estudiante;
END;

CREATE OR REPLACE NONEDITIONABLE FUNCTION listar_cursos_x_estudiante
(p_id IN ESTUDIANTE.id%TYPE)
RETURN types.ref_cursor AS
    cursor_curso types.ref_cursor;
BEGIN
    OPEN cursor_curso FOR SELECT
                               ESTUDIANTECURSO.CODESTUDAINTE,
                               ESTUDIANTECURSO.CODCURSOS,
                               CURSOS.DESCRIPCION,
                               CURSOS.CREDITOS
                           FROM
                               ESTUDIANTECURSO   
                            INNER JOIN CURSOS ON ESTUDIANTECURSO.CODCURSOS = CURSOS.id
                            WHERE id = p_id;

    RETURN cursor_curso;
 END;
 
 
 
    sp_insertaestudiante (02,'Adrian','Chavvaria','23');
   sp_insertaestudiante (03,'Stiven','Villalobos','23');
BEGIN
   sp_insertaestudiante (04,'Antonio','Quesada','22');
   COMMIT;
END;

BEGIN
    sp_updateestudiante(4,'juan','morera','60');
    COMMIT;
END;  

BEGIN
    sp_deleteestudiante(4);
    COMMIT;
END;  


BEGIN
    listar_estudiantes;
END;  





