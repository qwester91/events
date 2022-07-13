CREATE DATABASE events
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Belarus.1251'
    LC_CTYPE = 'Russian_Belarus.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

    CREATE SCHEMA IF NOT EXISTS event
        AUTHORIZATION postgres;


        CREATE TABLE IF NOT EXISTS event.events
        (
            uuid uuid NOT NULL,
            dt_create bigint NOT NULL,
            dt_update bigint NOT NULL,
            title character varying COLLATE pg_catalog."default",
            description character varying COLLATE pg_catalog."default",
            dt_event timestamp without time zone[],
            dt_end_of_sale timestamp without time zone,
            type character varying COLLATE pg_catalog."default",
            status character varying COLLATE pg_catalog."default",
            CONSTRAINT events_pkey PRIMARY KEY (uuid)
        )

        TABLESPACE pg_default;

        ALTER TABLE IF EXISTS event.events
            OWNER to postgres;

        CREATE TABLE IF NOT EXISTS event.concert
        (
            uuid uuid NOT NULL,
            category uuid,
            CONSTRAINT concert_pkey PRIMARY KEY (uuid),
            CONSTRAINT uuid FOREIGN KEY (uuid)
                REFERENCES event.events (uuid) MATCH SIMPLE
                ON UPDATE NO ACTION
                ON DELETE NO ACTION
        )

        TABLESPACE pg_default;

        ALTER TABLE IF EXISTS event.concert
            OWNER to postgres;

        CREATE TABLE IF NOT EXISTS event.film
        (
            uuid uuid NOT NULL,
            country uuid,
            release_year integer,
            release_date character varying COLLATE pg_catalog."default",
            duration integer,
            CONSTRAINT film_pkey PRIMARY KEY (uuid),
            CONSTRAINT uuid FOREIGN KEY (uuid)
                REFERENCES event.events (uuid) MATCH SIMPLE
                ON UPDATE NO ACTION
                ON DELETE NO ACTION
        )

        TABLESPACE pg_default;

        ALTER TABLE IF EXISTS event.film
            OWNER to postgres;