--
-- PostgreSQL database dump
--

-- Dumped from database version 12.19
-- Dumped by pg_dump version 12.6

-- Started on 2024-07-25 13:55:56 -03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3357 (class 1262 OID 16393)
-- Name: loja_virtual_spring; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE loja_virtual_spring WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';


ALTER DATABASE loja_virtual_spring OWNER TO postgres;

-- \connect loja_virtual_spring

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 206 (class 1259 OID 16408)
-- Name: acesso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.acesso (
    id bigint NOT NULL,
    descricao character varying(255)
);


ALTER TABLE public.acesso OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 16917)
-- Name: avaliacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.avaliacao (
    id bigint NOT NULL,
    descricao character varying(255) NOT NULL,
    nota integer NOT NULL,
    pessoa_id bigint NOT NULL,
    produto_id bigint NOT NULL
);


ALTER TABLE public.avaliacao OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16401)
-- Name: categoria_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria_produto (
    id bigint NOT NULL,
    nome_desc character varying(255) NOT NULL
);


ALTER TABLE public.categoria_produto OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16713)
-- Name: conta_a_pagar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.conta_a_pagar (
    id bigint NOT NULL,
    descricao character varying(255) NOT NULL,
    dt_pagamento date,
    dt_vencimento date NOT NULL,
    status character varying(255) NOT NULL,
    valor_desconto numeric(19,2),
    valor_total numeric(19,2) NOT NULL,
    fornecedor_id bigint NOT NULL,
    pessoa_id bigint NOT NULL
);


ALTER TABLE public.conta_a_pagar OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 16726)
-- Name: conta_receber; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.conta_receber (
    id bigint NOT NULL,
    descricao character varying(255) NOT NULL,
    dt_pagamento date,
    dt_vencimento date NOT NULL,
    status character varying(255) NOT NULL,
    valor_desconto numeric(19,2),
    valor_total numeric(19,2) NOT NULL,
    pessoa_id bigint NOT NULL
);


ALTER TABLE public.conta_receber OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16534)
-- Name: cupom_de_desconto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cupom_de_desconto (
    id bigint NOT NULL,
    porcentagem numeric(19,2),
    valor numeric(19,2),
    codigo bigint NOT NULL,
    dt_validade date NOT NULL
);


ALTER TABLE public.cupom_de_desconto OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16734)
-- Name: endereco; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.endereco (
    id bigint NOT NULL,
    bairro character varying(255) NOT NULL,
    cep character varying(255) NOT NULL,
    cidade character varying(255) NOT NULL,
    complemento character varying(255),
    numero character varying(255) NOT NULL,
    rua_logra character varying(255) NOT NULL,
    tipo_endereco character varying(255) NOT NULL,
    uf character varying(255) NOT NULL,
    pessoa_id bigint NOT NULL
);


ALTER TABLE public.endereco OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 16752)
-- Name: forma_de_pagamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.forma_de_pagamento (
    id bigint NOT NULL,
    descricao character varying(255) NOT NULL
);


ALTER TABLE public.forma_de_pagamento OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 16757)
-- Name: img_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.img_produto (
    id bigint NOT NULL,
    imagem_miniatura text NOT NULL,
    imagem_original text NOT NULL,
    produto_id bigint NOT NULL
);


ALTER TABLE public.img_produto OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16658)
-- Name: item_na_venda_compra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_na_venda_compra (
    id bigint NOT NULL,
    produto_id bigint NOT NULL,
    venda_id bigint NOT NULL,
    quantidade double precision NOT NULL
);


ALTER TABLE public.item_na_venda_compra OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16394)
-- Name: marca_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.marca_produto (
    id bigint NOT NULL,
    nome_desc character varying(255) NOT NULL
);


ALTER TABLE public.marca_produto OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 16775)
-- Name: nota_fiscal_compra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nota_fiscal_compra (
    id bigint NOT NULL,
    descricao character varying(255),
    dt_compra date NOT NULL,
    numero_nota character varying(255) NOT NULL,
    serie_nota character varying(255) NOT NULL,
    valor_desconto numeric(19,2),
    valor_icms numeric(19,2) NOT NULL,
    valor_total numeric(19,2) NOT NULL,
    conta_pagar_id bigint NOT NULL,
    pessoa_id bigint NOT NULL
);


ALTER TABLE public.nota_fiscal_compra OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 16793)
-- Name: nota_fiscal_venda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nota_fiscal_venda (
    id bigint NOT NULL,
    numero character varying(255) NOT NULL,
    pdf text NOT NULL,
    serie character varying(255) NOT NULL,
    tipo character varying(255) NOT NULL,
    xml text NOT NULL,
    venda_id bigint NOT NULL
);


ALTER TABLE public.nota_fiscal_venda OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16581)
-- Name: nota_item_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nota_item_produto (
    id bigint NOT NULL,
    quantidade double precision NOT NULL,
    nota_fiscal_compra_id bigint NOT NULL,
    produto_id bigint NOT NULL
);


ALTER TABLE public.nota_item_produto OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 16825)
-- Name: pessoa_fisica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pessoa_fisica (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    telefone character varying(255) NOT NULL,
    cpf character varying(255) NOT NULL,
    data_nascimento date
);


ALTER TABLE public.pessoa_fisica OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 16833)
-- Name: pessoa_juridica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pessoa_juridica (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    telefone character varying(255) NOT NULL,
    categoria character varying(255),
    cnpj character varying(255) NOT NULL,
    insc_estadual character varying(255) NOT NULL,
    insc_municipal character varying(255),
    nome_fantasia character varying(255) NOT NULL,
    razao_social character varying(255) NOT NULL
);


ALTER TABLE public.pessoa_juridica OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 16841)
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produto (
    id bigint NOT NULL,
    alerta_qtd_estoque boolean,
    altura double precision NOT NULL,
    ativo boolean NOT NULL,
    comprimento double precision NOT NULL,
    descricao text NOT NULL,
    largura double precision NOT NULL,
    link_youtube character varying(255),
    nome character varying(255) NOT NULL,
    peso double precision NOT NULL,
    qtd_alerta_estoque integer,
    qtd_click integer,
    qtd_estoque integer NOT NULL,
    tipo_unidade character varying(255) NOT NULL,
    valor_venda numeric(19,2) NOT NULL
);


ALTER TABLE public.produto OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16413)
-- Name: seq_acesso; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_acesso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_acesso OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16686)
-- Name: seq_avaliacao; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_avaliacao
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_avaliacao OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16406)
-- Name: seq_categoria_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_categoria_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_categoria_produto OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16532)
-- Name: seq_conta_a_pagar; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_conta_a_pagar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_conta_a_pagar OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16515)
-- Name: seq_conta_receber; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_conta_receber
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_conta_receber OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16539)
-- Name: seq_cupom_de_desconto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_cupom_de_desconto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_cupom_de_desconto OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16475)
-- Name: seq_endereco; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_endereco
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_endereco OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16522)
-- Name: seq_forma_de_pagamento; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_forma_de_pagamento
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_forma_de_pagamento OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16559)
-- Name: seq_img_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_img_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_img_produto OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16663)
-- Name: seq_item_na_venda_compra; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_item_na_venda_compra
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_item_na_venda_compra OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16399)
-- Name: seq_marca_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_marca_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_marca_produto OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16574)
-- Name: seq_nf_compra; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_nf_compra
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_nf_compra OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16616)
-- Name: seq_nota_fiscal_venda; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_nota_fiscal_venda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_nota_fiscal_venda OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16586)
-- Name: seq_nota_item_prod; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_nota_item_prod
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_nota_item_prod OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16436)
-- Name: seq_pessoa; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_pessoa
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_pessoa OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16549)
-- Name: seq_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_produto OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16606)
-- Name: seq_status_rastreio; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_status_rastreio
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_status_rastreio OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16492)
-- Name: seq_usuario; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_usuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_usuario OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16623)
-- Name: seq_vd_cp_lj_virt; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_vd_cp_lj_virt
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_vd_cp_lj_virt OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16598)
-- Name: status_rastreio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status_rastreio (
    id bigint NOT NULL,
    centro_distribuicao character varying(255),
    cidade character varying(255),
    estado character varying(255),
    status character varying(255),
    venda_id bigint NOT NULL
);


ALTER TABLE public.status_rastreio OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 16812)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id bigint NOT NULL,
    data_atual_senha date NOT NULL,
    login character varying(255) NOT NULL,
    senha character varying(255) NOT NULL,
    pessoa_id bigint NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16485)
-- Name: usuarios_acesso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios_acesso (
    usuario_id bigint NOT NULL,
    acesso_id bigint NOT NULL
);


ALTER TABLE public.usuarios_acesso OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 16869)
-- Name: vd_cp_lj_virt; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vd_cp_lj_virt (
    id bigint NOT NULL,
    data_entrega date NOT NULL,
    dias_entrega integer NOT NULL,
    dt_venda date NOT NULL,
    valor_desconto numeric(19,2),
    valor_frete numeric(19,2) NOT NULL,
    valor_total numeric(19,2) NOT NULL,
    pessoa_id bigint NOT NULL,
    cupom_de_desconto_id bigint,
    endereco_cobranca_id bigint NOT NULL,
    endereco_entrega_id bigint NOT NULL,
    forma_pagamento_id bigint NOT NULL,
    nota_fiscal_venda_id bigint NOT NULL
);


ALTER TABLE public.vd_cp_lj_virt OWNER TO postgres;

--
-- TOC entry 3316 (class 0 OID 16408)
-- Dependencies: 206
-- Data for Name: acesso; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3351 (class 0 OID 16917)
-- Dependencies: 241
-- Data for Name: avaliacao; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3314 (class 0 OID 16401)
-- Dependencies: 204
-- Data for Name: categoria_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3339 (class 0 OID 16713)
-- Dependencies: 229
-- Data for Name: conta_a_pagar; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3340 (class 0 OID 16726)
-- Dependencies: 230
-- Data for Name: conta_receber; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3325 (class 0 OID 16534)
-- Dependencies: 215
-- Data for Name: cupom_de_desconto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3341 (class 0 OID 16734)
-- Dependencies: 231
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3342 (class 0 OID 16752)
-- Dependencies: 232
-- Data for Name: forma_de_pagamento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3343 (class 0 OID 16757)
-- Dependencies: 233
-- Data for Name: img_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3336 (class 0 OID 16658)
-- Dependencies: 226
-- Data for Name: item_na_venda_compra; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3312 (class 0 OID 16394)
-- Dependencies: 202
-- Data for Name: marca_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3344 (class 0 OID 16775)
-- Dependencies: 234
-- Data for Name: nota_fiscal_compra; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3345 (class 0 OID 16793)
-- Dependencies: 235
-- Data for Name: nota_fiscal_venda; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3330 (class 0 OID 16581)
-- Dependencies: 220
-- Data for Name: nota_item_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3347 (class 0 OID 16825)
-- Dependencies: 237
-- Data for Name: pessoa_fisica; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3348 (class 0 OID 16833)
-- Dependencies: 238
-- Data for Name: pessoa_juridica; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3349 (class 0 OID 16841)
-- Dependencies: 239
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.produto (id, alerta_qtd_estoque, altura, ativo, comprimento, descricao, largura, link_youtube, nome, peso, qtd_alerta_estoque, qtd_click, qtd_estoque, tipo_unidade, valor_venda) VALUES (1, true, 20, true, 20, 'descricao text', 20, 'youtube.com', 'Produto Teste', 155, 2, 0, 1, 'UN', 200.00);


--
-- TOC entry 3332 (class 0 OID 16598)
-- Dependencies: 222
-- Data for Name: status_rastreio; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3346 (class 0 OID 16812)
-- Dependencies: 236
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3320 (class 0 OID 16485)
-- Dependencies: 210
-- Data for Name: usuarios_acesso; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3350 (class 0 OID 16869)
-- Dependencies: 240
-- Data for Name: vd_cp_lj_virt; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3358 (class 0 OID 0)
-- Dependencies: 207
-- Name: seq_acesso; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_acesso', 1, false);


--
-- TOC entry 3359 (class 0 OID 0)
-- Dependencies: 228
-- Name: seq_avaliacao; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_avaliacao', 1, false);


--
-- TOC entry 3360 (class 0 OID 0)
-- Dependencies: 205
-- Name: seq_categoria_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_categoria_produto', 1, false);


--
-- TOC entry 3361 (class 0 OID 0)
-- Dependencies: 214
-- Name: seq_conta_a_pagar; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_conta_a_pagar', 1, false);


--
-- TOC entry 3362 (class 0 OID 0)
-- Dependencies: 212
-- Name: seq_conta_receber; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_conta_receber', 1, false);


--
-- TOC entry 3363 (class 0 OID 0)
-- Dependencies: 216
-- Name: seq_cupom_de_desconto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_cupom_de_desconto', 1, false);


--
-- TOC entry 3364 (class 0 OID 0)
-- Dependencies: 209
-- Name: seq_endereco; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_endereco', 1, false);


--
-- TOC entry 3365 (class 0 OID 0)
-- Dependencies: 213
-- Name: seq_forma_de_pagamento; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_forma_de_pagamento', 1, false);


--
-- TOC entry 3366 (class 0 OID 0)
-- Dependencies: 218
-- Name: seq_img_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_img_produto', 1, false);


--
-- TOC entry 3367 (class 0 OID 0)
-- Dependencies: 227
-- Name: seq_item_na_venda_compra; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_item_na_venda_compra', 1, false);


--
-- TOC entry 3368 (class 0 OID 0)
-- Dependencies: 203
-- Name: seq_marca_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_marca_produto', 1, false);


--
-- TOC entry 3369 (class 0 OID 0)
-- Dependencies: 219
-- Name: seq_nf_compra; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_nf_compra', 1, false);


--
-- TOC entry 3370 (class 0 OID 0)
-- Dependencies: 224
-- Name: seq_nota_fiscal_venda; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_nota_fiscal_venda', 1, false);


--
-- TOC entry 3371 (class 0 OID 0)
-- Dependencies: 221
-- Name: seq_nota_item_prod; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_nota_item_prod', 1, false);


--
-- TOC entry 3372 (class 0 OID 0)
-- Dependencies: 208
-- Name: seq_pessoa; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_pessoa', 1, false);


--
-- TOC entry 3373 (class 0 OID 0)
-- Dependencies: 217
-- Name: seq_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_produto', 1, false);


--
-- TOC entry 3374 (class 0 OID 0)
-- Dependencies: 223
-- Name: seq_status_rastreio; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_status_rastreio', 1, false);


--
-- TOC entry 3375 (class 0 OID 0)
-- Dependencies: 211
-- Name: seq_usuario; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_usuario', 1, false);


--
-- TOC entry 3376 (class 0 OID 0)
-- Dependencies: 225
-- Name: seq_vd_cp_lj_virt; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_vd_cp_lj_virt', 1, false);


--
-- TOC entry 3117 (class 2606 OID 16412)
-- Name: acesso acesso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acesso
    ADD CONSTRAINT acesso_pkey PRIMARY KEY (id);


--
-- TOC entry 3155 (class 2606 OID 16921)
-- Name: avaliacao avaliacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avaliacao
    ADD CONSTRAINT avaliacao_pkey PRIMARY KEY (id);


--
-- TOC entry 3115 (class 2606 OID 16405)
-- Name: categoria_produto categoria_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria_produto
    ADD CONSTRAINT categoria_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3131 (class 2606 OID 16720)
-- Name: conta_a_pagar conta_a_pagar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conta_a_pagar
    ADD CONSTRAINT conta_a_pagar_pkey PRIMARY KEY (id);


--
-- TOC entry 3133 (class 2606 OID 16733)
-- Name: conta_receber conta_receber_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conta_receber
    ADD CONSTRAINT conta_receber_pkey PRIMARY KEY (id);


--
-- TOC entry 3123 (class 2606 OID 16538)
-- Name: cupom_de_desconto cupom_de_desconto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cupom_de_desconto
    ADD CONSTRAINT cupom_de_desconto_pkey PRIMARY KEY (id);


--
-- TOC entry 3135 (class 2606 OID 16741)
-- Name: endereco endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- TOC entry 3137 (class 2606 OID 16756)
-- Name: forma_de_pagamento forma_de_pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.forma_de_pagamento
    ADD CONSTRAINT forma_de_pagamento_pkey PRIMARY KEY (id);


--
-- TOC entry 3139 (class 2606 OID 16764)
-- Name: img_produto img_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.img_produto
    ADD CONSTRAINT img_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3129 (class 2606 OID 16662)
-- Name: item_na_venda_compra item_na_venda_compra_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_na_venda_compra
    ADD CONSTRAINT item_na_venda_compra_pkey PRIMARY KEY (id);


--
-- TOC entry 3113 (class 2606 OID 16398)
-- Name: marca_produto marca_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marca_produto
    ADD CONSTRAINT marca_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3141 (class 2606 OID 16782)
-- Name: nota_fiscal_compra nota_fiscal_compra_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_compra
    ADD CONSTRAINT nota_fiscal_compra_pkey PRIMARY KEY (id);


--
-- TOC entry 3143 (class 2606 OID 16800)
-- Name: nota_fiscal_venda nota_fiscal_venda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_venda
    ADD CONSTRAINT nota_fiscal_venda_pkey PRIMARY KEY (id);


--
-- TOC entry 3125 (class 2606 OID 16585)
-- Name: nota_item_produto nota_item_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT nota_item_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3147 (class 2606 OID 16832)
-- Name: pessoa_fisica pessoa_fisica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa_fisica
    ADD CONSTRAINT pessoa_fisica_pkey PRIMARY KEY (id);


--
-- TOC entry 3149 (class 2606 OID 16840)
-- Name: pessoa_juridica pessoa_juridica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa_juridica
    ADD CONSTRAINT pessoa_juridica_pkey PRIMARY KEY (id);


--
-- TOC entry 3151 (class 2606 OID 16848)
-- Name: produto produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3127 (class 2606 OID 16605)
-- Name: status_rastreio status_rastreio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status_rastreio
    ADD CONSTRAINT status_rastreio_pkey PRIMARY KEY (id);


--
-- TOC entry 3119 (class 2606 OID 16514)
-- Name: usuarios_acesso uk_8bak9jswon2id2jbunuqlfl9e; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT uk_8bak9jswon2id2jbunuqlfl9e UNIQUE (acesso_id);


--
-- TOC entry 3121 (class 2606 OID 16491)
-- Name: usuarios_acesso unique_acesso_user; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT unique_acesso_user UNIQUE (usuario_id, acesso_id);


--
-- TOC entry 3145 (class 2606 OID 16819)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 3153 (class 2606 OID 16873)
-- Name: vd_cp_lj_virt vd_cp_lj_virt_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_cp_lj_virt
    ADD CONSTRAINT vd_cp_lj_virt_pkey PRIMARY KEY (id);


-- FUNCTION: public.validachavepessoa()

-- DROP FUNCTION IF EXISTS public.validachavepessoa();

CREATE OR REPLACE FUNCTION public.validachavepessoa()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$

	declare existe integer;

	begin
		existe = (select count(1) from pessoa_fisica where id = NEW.pessoa_id);
		if (existe <= 0) then
			existe = (select count(1) from pessoa_juridica where id = NEW.pessoa_id);
		if (existe <= 0) then
			raise exception 'Não foi encontrado o Id ou PK da pessoa para realizar a associação';
			end if;
		end if;
		RETURN NEW;
	end;
	
$BODY$;

ALTER FUNCTION public.validachavepessoa()
    OWNER TO postgres;


-- FUNCTION: public.validachavepessoafornecedor()

-- DROP FUNCTION IF EXISTS public.validachavepessoafornecedor();

CREATE OR REPLACE FUNCTION public.validachavepessoafornecedor()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$

	declare existe integer;

	begin
		existe = (select count(1) from pessoa_fisica where id = NEW.fornecedor_id);
		if (existe <= 0) then
			existe = (select count(1) from pessoa_juridica where id = NEW.fornecedor_id);
		if (existe <= 0) then
			raise exception 'Não foi encontrado o Id ou PK da pessoa para realizar a associação';
			end if;
		end if;
		RETURN NEW;
	end;
	
$BODY$;

ALTER FUNCTION public.validachavepessoafornecedor()
    OWNER TO postgres;



--
-- TOC entry 3184 (class 2620 OID 16927)
-- Name: avaliacao validachavepessoaavaliacaoproduto; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoaavaliacaoprodutoupdate BEFORE UPDATE ON public.avaliacao FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3185 (class 2620 OID 16928)
-- Name: avaliacao validachavepessoaavaliacaoproduto2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoaavaliacaoprodutoinsert BEFORE INSERT ON public.avaliacao FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3173 (class 2620 OID 16933)
-- Name: conta_a_pagar validachavepessoacontapagarinsert; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontapagarinsert BEFORE INSERT ON public.conta_a_pagar FOR EACH ROW EXECUTE FUNCTION public.validachavepessoafornecedor();


--
-- TOC entry 3172 (class 2620 OID 16932)
-- Name: conta_a_pagar validachavepessoacontapagarupdate; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontapagarupdate BEFORE UPDATE ON public.conta_a_pagar FOR EACH ROW EXECUTE FUNCTION public.validachavepessoafornecedor();


--
-- TOC entry 3175 (class 2620 OID 16935)
-- Name: conta_receber validachavepessoainsert; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoainsert BEFORE INSERT ON public.conta_receber FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3177 (class 2620 OID 16937)
-- Name: endereco validachavepessoainsert; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoainsert BEFORE INSERT ON public.endereco FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3179 (class 2620 OID 16939)
-- Name: nota_fiscal_compra validachavepessoainsert; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoainsert BEFORE INSERT ON public.nota_fiscal_compra FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3181 (class 2620 OID 16943)
-- Name: usuario validachavepessoainsert; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoainsert BEFORE INSERT ON public.usuario FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3183 (class 2620 OID 16941)
-- Name: vd_cp_lj_virt validachavepessoainsert; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoainsert BEFORE INSERT ON public.vd_cp_lj_virt FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3174 (class 2620 OID 16934)
-- Name: conta_receber validachavepessoaupdate; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoaupdate BEFORE UPDATE ON public.conta_receber FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3176 (class 2620 OID 16936)
-- Name: endereco validachavepessoaupdate; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoaupdate BEFORE UPDATE ON public.endereco FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3178 (class 2620 OID 16938)
-- Name: nota_fiscal_compra validachavepessoaupdate; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoaupdate BEFORE UPDATE ON public.nota_fiscal_compra FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3180 (class 2620 OID 16942)
-- Name: usuario validachavepessoaupdate; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoaupdate BEFORE UPDATE ON public.usuario FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3182 (class 2620 OID 16940)
-- Name: vd_cp_lj_virt validachavepessoaupdate; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoaupdate BEFORE UPDATE ON public.vd_cp_lj_virt FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3156 (class 2606 OID 16494)
-- Name: usuarios_acesso acesso_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT acesso_fk FOREIGN KEY (acesso_id) REFERENCES public.acesso(id);


--
-- TOC entry 3164 (class 2606 OID 16783)
-- Name: nota_fiscal_compra conta_pagar_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_compra
    ADD CONSTRAINT conta_pagar_fk FOREIGN KEY (conta_pagar_id) REFERENCES public.conta_a_pagar(id);


--
-- TOC entry 3166 (class 2606 OID 16889)
-- Name: vd_cp_lj_virt cupom_de_desconto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_cp_lj_virt
    ADD CONSTRAINT cupom_de_desconto_fk FOREIGN KEY (cupom_de_desconto_id) REFERENCES public.cupom_de_desconto(id);


--
-- TOC entry 3167 (class 2606 OID 16894)
-- Name: vd_cp_lj_virt endereco_cobranca_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_cp_lj_virt
    ADD CONSTRAINT endereco_cobranca_fk FOREIGN KEY (endereco_cobranca_id) REFERENCES public.endereco(id);


--
-- TOC entry 3168 (class 2606 OID 16899)
-- Name: vd_cp_lj_virt endereco_entrega_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_cp_lj_virt
    ADD CONSTRAINT endereco_entrega_fk FOREIGN KEY (endereco_entrega_id) REFERENCES public.endereco(id);


--
-- TOC entry 3169 (class 2606 OID 16904)
-- Name: vd_cp_lj_virt forma_pagamento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_cp_lj_virt
    ADD CONSTRAINT forma_pagamento_fk FOREIGN KEY (forma_pagamento_id) REFERENCES public.forma_de_pagamento(id);


--
-- TOC entry 3158 (class 2606 OID 16788)
-- Name: nota_item_produto nota_fiscal_compra_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT nota_fiscal_compra_fk FOREIGN KEY (nota_fiscal_compra_id) REFERENCES public.nota_fiscal_compra(id);


--
-- TOC entry 3170 (class 2606 OID 16909)
-- Name: vd_cp_lj_virt nota_fiscal_venda_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_cp_lj_virt
    ADD CONSTRAINT nota_fiscal_venda_fk FOREIGN KEY (nota_fiscal_venda_id) REFERENCES public.nota_fiscal_venda(id);


--
-- TOC entry 3163 (class 2606 OID 16854)
-- Name: img_produto produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.img_produto
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 3161 (class 2606 OID 16859)
-- Name: item_na_venda_compra produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_na_venda_compra
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 3159 (class 2606 OID 16864)
-- Name: nota_item_produto produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 3171 (class 2606 OID 16922)
-- Name: avaliacao produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avaliacao
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 3157 (class 2606 OID 16820)
-- Name: usuarios_acesso usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT usuario_fk FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- TOC entry 3162 (class 2606 OID 16874)
-- Name: item_na_venda_compra venda_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_na_venda_compra
    ADD CONSTRAINT venda_fk FOREIGN KEY (venda_id) REFERENCES public.vd_cp_lj_virt(id);


--
-- TOC entry 3165 (class 2606 OID 16879)
-- Name: nota_fiscal_venda venda_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_venda
    ADD CONSTRAINT venda_fk FOREIGN KEY (venda_id) REFERENCES public.vd_cp_lj_virt(id);


--
-- TOC entry 3160 (class 2606 OID 16884)
-- Name: status_rastreio venda_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status_rastreio
    ADD CONSTRAINT venda_fk FOREIGN KEY (venda_id) REFERENCES public.vd_cp_lj_virt(id);


-- Completed on 2024-07-25 13:55:56 -03

--
-- PostgreSQL database dump complete
--

