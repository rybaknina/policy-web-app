-- Database: testdb
 --DROP DATABASE testdb;

CREATE DATABASE testdb
WITH
OWNER = postgres
ENCODING = 'UTF8'
TABLESPACE = pg_default
CONNECTION LIMIT = -1;

-- Table: public.policy_type

-- DROP TABLE public.policy_type;

CREATE TABLE public.policy_type
(
  id character(1) COLLATE pg_catalog."default" NOT NULL,
  type character varying(50) COLLATE pg_catalog."default",
  CONSTRAINT policy_type_pkey PRIMARY KEY (id)
)
  WITH (
  OIDS = FALSE
       )
  TABLESPACE pg_default;

ALTER TABLE public.policy_type
  OWNER to postgres;


INSERT INTO public.policy_type(
  id, type)
VALUES ('1', 'KASKO');
INSERT INTO public.policy_type(
  id, type)
VALUES ('2', 'National');


-- Table: public.policy

-- DROP TABLE public.policy;

CREATE TABLE public.policy
(
  id SERIAL PRIMARY KEY,
  id_type character(1) COLLATE pg_catalog."default",
  id_company character(3) COLLATE pg_catalog."default",
  id_car_type character(1) COLLATE pg_catalog."default",
  id_period character(1) COLLATE pg_catalog."default",
  price numeric(17,2),
  id_stock integer,
  is_active boolean,
  is_delete boolean
)
  WITH (
    OIDS = FALSE
  )
  TABLESPACE pg_default;

ALTER TABLE public.policy
  OWNER to postgres;

-- Table: public.role

-- DROP TABLE public.role;

CREATE TABLE public.role
(
  id SERIAL PRIMARY KEY,
  name character varying(45) COLLATE pg_catalog."default" DEFAULT NULL::character varying
)
  WITH (
  OIDS = FALSE
       )
  TABLESPACE pg_default;

ALTER TABLE public.role
  OWNER to postgres;


INSERT INTO public.role(
  name)
VALUES ('ROLE_USER');
INSERT INTO public.role(
  name)
VALUES ('ROLE_ADMIN');

-- DROP TABLE public.users;

CREATE TABLE public.users
(
  id SERIAL PRIMARY KEY,
  username character varying(255) COLLATE pg_catalog."default" NOT NULL,
  password character varying(255) COLLATE pg_catalog."default" NOT NULL,
  is_active boolean,
  is_delete boolean,
  CONSTRAINT id UNIQUE (id),
  CONSTRAINT users_username_key UNIQUE (username)
)
  WITH (
  OIDS = FALSE
       )
  TABLESPACE pg_default;

ALTER TABLE public.users
  OWNER to postgres;

-- Table: public.user_role

-- DROP TABLE public.user_role;

CREATE TABLE public.user_role
(
  user_id SERIAL,
  role_id SERIAL,
  CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id),
  CONSTRAINT fk_user_role_roleid FOREIGN KEY (role_id)
    REFERENCES public.role (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
)
  WITH (
  OIDS = FALSE
       )
  TABLESPACE pg_default;

ALTER TABLE public.user_role
  OWNER to postgres;

-- Index: fki_fk_user_role_userid

-- DROP INDEX public.fki_fk_user_role_userid;

CREATE INDEX fki_fk_user_role_userid
ON public.user_role USING btree
(user_id)
TABLESPACE pg_default;