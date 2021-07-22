alter session set "_ORACLE_SCRIPT"=true;

CREATE USER tallertech IDENTIFIED BY tallertechpass;

GRANT CREATE TABLE TO tallertech;
GRANT RESOURCE, DBA TO tallertech;
GRANT EXECUTE ON TALLERTECH.pro_reportar_llamadas_empresa TO tallertech;