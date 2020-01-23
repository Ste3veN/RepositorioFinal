
CREATE TABLE public.usuario
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    cedula character varying(10) COLLATE pg_catalog."default" NOT NULL,
    nombre character varying(100) COLLATE pg_catalog."default" NOT NULL,
    correo character varying(100) COLLATE pg_catalog."default" NOT NULL,
    contrasenia character varying(300) COLLATE pg_catalog."default" NOT NULL,
    foto bytea,
    CONSTRAINT usuario_pkey PRIMARY KEY (id),
    CONSTRAINT "uniq-cedula" UNIQUE (cedula)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.usuario
    OWNER to postgres;