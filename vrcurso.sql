CREATE DATABASE vrcurso
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
	
CREATE TABLE professor
(
    id serial,
    nome character varying(150),
    rg character varying(20),
    cpf bigint,
    titulo integer,
    CONSTRAINT pk_professor PRIMARY KEY (id)
);

CREATE TABLE disciplina
(
    id serial,
    descricao character varying(150),
    ementa TEXT,
    limitevagas integer,
    id_professor integer,
	diasemana integer,
	cargahoraria integer,
    CONSTRAINT pk_disciplina PRIMARY KEY (id),
	CONSTRAINT fk_disciplina_professor foreign KEY (id_professor) REFERENCES professor (id)
);

CREATE TABLE aluno
(
    id serial,
	matricula bigint,
    nome character varying(150),
    rg character varying(20),
    cpf bigint,
    CONSTRAINT pk_aluno PRIMARY KEY (id)
);

CREATE TABLE curso
(
    id serial,
    descricao character varying(150),
    duracaomeses integer,
	periodo integer,
	qtdalunos integer,
	cargahoraria integer,
    CONSTRAINT pk_curso PRIMARY KEY (id)
);

CREATE TABLE cursodisciplina
(
    id serial,
    id_curso integer,
	id_disciplina integer,
    CONSTRAINT pk_cursodisciplina PRIMARY KEY (id),
    CONSTRAINT fk_curso foreign KEY (id_curso) REFERENCES curso (id),
    CONSTRAINT fk_disciplina foreign KEY (id_disciplina) REFERENCES disciplina (id)
);

CREATE TABLE matricula
(
    id serial,
    id_curso integer,
	id_aluno integer,
    CONSTRAINT pk_matricula PRIMARY KEY (id),
    CONSTRAINT fk_curso foreign KEY (id_curso) REFERENCES curso (id),
    CONSTRAINT fk_aluno foreign KEY (id_aluno) REFERENCES aluno (id)
);

CREATE TABLE matriculadisciplina
(
    id serial,
	id_matricula integer,
	id_disciplina integer,
    CONSTRAINT pk_matriculadisciplina PRIMARY KEY (id),
    CONSTRAINT fk_matricula foreign KEY (id_matricula) REFERENCES matricula (id),
    CONSTRAINT fk_disciplina foreign KEY (id_disciplina) REFERENCES disciplina (id)
);