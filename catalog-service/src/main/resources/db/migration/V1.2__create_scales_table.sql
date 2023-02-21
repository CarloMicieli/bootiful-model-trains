CREATE TABLE public.scales
(
    scale_id           varchar(50) PRIMARY KEY NOT NULL,
    name               varchar(50)             NOT NULL,
    ratio              numeric(19, 5)          NOT NULL,
    gauge_millimeters  numeric(19, 5),
    gauge_inches       numeric(19, 5),
    track_gauge        varchar(50)             NOT NULL,
    description_it     varchar(2500),
    description_en     varchar(2500),
    standards          varchar(100) array,
    created_date       timestamp               NOT NULL,
    last_modified_date timestamp               NOT NULL,
    version            INT                     NOT NULL
);
