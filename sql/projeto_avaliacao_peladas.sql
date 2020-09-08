--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

-- Started on 2020-09-08 08:47:48

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE projeto_avaliacao_peladas;
--
-- TOC entry 2154 (class 1262 OID 18800)
-- Name: projeto_avaliacao_peladas; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE projeto_avaliacao_peladas WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


ALTER DATABASE projeto_avaliacao_peladas OWNER TO postgres;

\connect projeto_avaliacao_peladas

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2155 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2157 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 190 (class 1259 OID 18945)
-- Name: convite; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE convite (
    id integer NOT NULL,
    pelada_id integer,
    usuario_id integer
);


ALTER TABLE convite OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 18935)
-- Name: convites; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE convites (
    usuario_id integer,
    pelada_id integer,
    id integer NOT NULL
);


ALTER TABLE convites OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 18938)
-- Name: convites_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE convites_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE convites_id_seq OWNER TO postgres;

--
-- TOC entry 2158 (class 0 OID 0)
-- Dependencies: 189
-- Name: convites_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE convites_id_seq OWNED BY convites.id;


--
-- TOC entry 184 (class 1259 OID 18914)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 18924)
-- Name: inscricao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE inscricao (
    usuario_id integer,
    pelada_id integer,
    id integer NOT NULL
);


ALTER TABLE inscricao OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 18927)
-- Name: inscricao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE inscricao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE inscricao_id_seq OWNER TO postgres;

--
-- TOC entry 2159 (class 0 OID 0)
-- Dependencies: 187
-- Name: inscricao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE inscricao_id_seq OWNED BY inscricao.id;


--
-- TOC entry 181 (class 1259 OID 18804)
-- Name: pelada; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pelada (
    nome_evento character varying(100) DEFAULT ''::character varying NOT NULL,
    data date,
    hora time without time zone,
    local character varying(250) DEFAULT ''::character varying,
    id integer NOT NULL
);


ALTER TABLE pelada OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 18916)
-- Name: pelada_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pelada_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pelada_id_seq OWNER TO postgres;

--
-- TOC entry 2160 (class 0 OID 0)
-- Dependencies: 185
-- Name: pelada_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pelada_id_seq OWNED BY pelada.id;


--
-- TOC entry 182 (class 1259 OID 18878)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE usuario (
    apelido character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    senha character varying(255) NOT NULL,
    id integer NOT NULL
);


ALTER TABLE usuario OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 18894)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuario_id_seq OWNER TO postgres;

--
-- TOC entry 2161 (class 0 OID 0)
-- Dependencies: 183
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_id_seq OWNED BY usuario.id;


--
-- TOC entry 2011 (class 2604 OID 18940)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY convites ALTER COLUMN id SET DEFAULT nextval('convites_id_seq'::regclass);


--
-- TOC entry 2010 (class 2604 OID 18929)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inscricao ALTER COLUMN id SET DEFAULT nextval('inscricao_id_seq'::regclass);


--
-- TOC entry 2008 (class 2604 OID 18918)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pelada ALTER COLUMN id SET DEFAULT nextval('pelada_id_seq'::regclass);


--
-- TOC entry 2009 (class 2604 OID 18896)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN id SET DEFAULT nextval('usuario_id_seq'::regclass);


--
-- TOC entry 2149 (class 0 OID 18945)
-- Dependencies: 190
-- Data for Name: convite; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO convite (id, pelada_id, usuario_id) VALUES (12, 3, 0);
INSERT INTO convite (id, pelada_id, usuario_id) VALUES (13, 2, 0);


--
-- TOC entry 2147 (class 0 OID 18935)
-- Dependencies: 188
-- Data for Name: convites; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2162 (class 0 OID 0)
-- Dependencies: 189
-- Name: convites_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('convites_id_seq', 1, false);


--
-- TOC entry 2163 (class 0 OID 0)
-- Dependencies: 184
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 13, true);


--
-- TOC entry 2145 (class 0 OID 18924)
-- Dependencies: 186
-- Data for Name: inscricao; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO inscricao (usuario_id, pelada_id, id) VALUES (2, 5, 7);


--
-- TOC entry 2164 (class 0 OID 0)
-- Dependencies: 187
-- Name: inscricao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('inscricao_id_seq', 1, false);


--
-- TOC entry 2140 (class 0 OID 18804)
-- Dependencies: 181
-- Data for Name: pelada; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO pelada (nome_evento, data, hora, local, id) VALUES ('Copa', NULL, NULL, 'Salvador', 4);
INSERT INTO pelada (nome_evento, data, hora, local, id) VALUES ('Copa', '0020-09-07', NULL, 'Salvador', 5);
INSERT INTO pelada (nome_evento, data, hora, local, id) VALUES ('Copa', '0020-09-07', '02:32:00', 'Salvador', 6);
INSERT INTO pelada (nome_evento, data, hora, local, id) VALUES ('Copa', '0020-09-23', '02:32:00', 'Salvador', 8);
INSERT INTO pelada (nome_evento, data, hora, local, id) VALUES ('Copa', '0020-09-08', '02:32:00', 'Salvador', 9);
INSERT INTO pelada (nome_evento, data, hora, local, id) VALUES ('Copa', '0020-09-11', '02:32:00', 'Salvador', 10);
INSERT INTO pelada (nome_evento, data, hora, local, id) VALUES ('Copa', '2020-09-24', '07:36:00', 'Salvador', 11);


--
-- TOC entry 2165 (class 0 OID 0)
-- Dependencies: 185
-- Name: pelada_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pelada_id_seq', 1, false);


--
-- TOC entry 2141 (class 0 OID 18878)
-- Dependencies: 182
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario (apelido, email, nome, senha, id) VALUES ('adm', 'samuka192marins@gmail.com', 'Administrador', '1', 3);
INSERT INTO usuario (apelido, email, nome, senha, id) VALUES ('samuca', 'samuel_4186@hotmail.com', 'Samuel', '1', 2);


--
-- TOC entry 2166 (class 0 OID 0)
-- Dependencies: 183
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_id_seq', 2, true);


--
-- TOC entry 2025 (class 2606 OID 18949)
-- Name: convite_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY convite
    ADD CONSTRAINT convite_pkey PRIMARY KEY (id);


--
-- TOC entry 2023 (class 2606 OID 18934)
-- Name: inscricao_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inscricao
    ADD CONSTRAINT inscricao_id PRIMARY KEY (id);


--
-- TOC entry 2013 (class 2606 OID 18923)
-- Name: pelada_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pelada
    ADD CONSTRAINT pelada_id PRIMARY KEY (id);


--
-- TOC entry 2015 (class 2606 OID 18887)
-- Name: usuario_apelido_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_apelido_key UNIQUE (apelido);


--
-- TOC entry 2017 (class 2606 OID 18889)
-- Name: usuario_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_email_key UNIQUE (email);


--
-- TOC entry 2019 (class 2606 OID 18913)
-- Name: usuario_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_id PRIMARY KEY (id);


--
-- TOC entry 2021 (class 2606 OID 18891)
-- Name: usuario_nome_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_nome_key UNIQUE (nome);


--
-- TOC entry 2156 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2020-09-08 08:47:48

--
-- PostgreSQL database dump complete
--

