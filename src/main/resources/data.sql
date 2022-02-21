INSERT INTO USUARIO(username, enable, password) VALUES('t', TRUE, 't');

INSERT INTO PERFIL (nome) VALUES ('ROLE_ADM');
INSERT INTO PERFIL (nome) VALUES ('ROLE_PACIENTE');

INSET INTO USUARIO_PERFIS(usuario_username, perfis_id) VALUES ('t', 1);