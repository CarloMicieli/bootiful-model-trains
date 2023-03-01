CREATE TABLE public.catalog_items
(
    catalog_item_id     varchar(76)  NOT NULL,
    brand_id            varchar(50)  NOT NULL,
    item_number         varchar(25)  NOT NULL,
    scale_id            varchar(25)  NOT NULL,
    category            varchar(100) NOT NULL,
    description_en      varchar(2500),
    description_it      varchar(2500),
    details_en          varchar(2500),
    details_it          varchar(2500),
    power_method        varchar(10)  NOT NULL,
    delivery_date       varchar(10),
    availability_status varchar(100),
    count               integer      NOT NULL DEFAULT 1,
    created_at          timestamptz  NOT NULL,
    last_modified_at    timestamptz,
    version             integer      NOT NULL DEFAULT 1,
    CONSTRAINT "PK_catalog_items" PRIMARY KEY (catalog_item_id),
    CONSTRAINT "FK_catalog_items_brands" FOREIGN KEY (brand_id)
        REFERENCES public.brands (brand_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_catalog_items_scales" FOREIGN KEY (scale_id)
        REFERENCES public.scales (scale_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.rolling_stocks
(
    rolling_stock_id            uuid         NOT NULL,
    catalog_item_id             varchar(65)  NOT NULL,
    railway_id                  varchar(25)  NOT NULL,
    rolling_stock_category      varchar(100) NOT NULL,
    epoch                       varchar(10)  NOT NULL,
    livery                      varchar(50),
    length_over_buffers_mm      numeric(9, 2),
    length_over_buffers_in      numeric(9, 2),
    type_name                   varchar(25),
    road_number                 varchar(50),
    series                      varchar(50),
    depot                       varchar(100),
    dcc_interface               varchar(100),
    control                     varchar(100),
    electric_multiple_unit_type varchar(100),
    freight_car_type            varchar(100),
    locomotive_type             varchar(100),
    passenger_car_type          varchar(100),
    railcar_type                varchar(100),
    service_level               varchar(100),
    is_dummy                    boolean,
    minimum_radius              numeric(9, 2),
    coupling_socket             varchar(100),
    close_couplers              varchar(100),
    digital_shunting_coupling   varchar(100),
    flywheel_fitted             varchar(100),
    metal_body                  varchar(100),
    interior_lights             varchar(100),
    lights                      varchar(100),
    spring_buffers              varchar(100),
    CONSTRAINT "PK_rolling_stocks" PRIMARY KEY (rolling_stock_id),
    CONSTRAINT "FK_rolling_stocks_catalog_items" FOREIGN KEY (catalog_item_id)
        REFERENCES public.catalog_items (catalog_item_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_rolling_stocks_railways" FOREIGN KEY (railway_id)
        REFERENCES public.railways (railway_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
