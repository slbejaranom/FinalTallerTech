CREATE OR REPLACE TRIGGER TR_LLAMADA 
BEFORE INSERT ON LLAMADA FOR EACH ROW
BEGIN
  :new.FUE_REPORTADA := '0';
END;

CREATE OR REPLACE TRIGGER TR_EMPRESA_TELEFONIA_FIJA_INSERT
BEFORE INSERT ON EMPRESA_TELEFONIA_FIJA FOR EACH ROW
BEGIN
 :new.ESTA_ACTIVO := '1';
END;

CREATE OR REPLACE TRIGGER TR_CLIENTE_EMPRESA_INSERT
BEFORE INSERT ON CLIENTE_EMPRESA FOR EACH ROW
BEGIN
    :new.ESTA_ACTIVO := '1';
END;