CREATE TABLE public.brands
(
    brand_id                 varchar(50) PRIMARY KEY NOT NULL,
    name                     varchar(50)             NOT NULL,
    registered_company_name  varchar(100),
    organization_entity_type varchar(100),
    group_name               varchar(100),
    description_en           varchar(2500),
    description_it           varchar(2500),
    kind                     varchar(100)            NOT NULL,
    status                   varchar(100),
    contact_email            varchar(250),
    contact_website_url      varchar(100),
    contact_phone            varchar(20),
    address_street_address   varchar(255),
    address_extended_address varchar(255),
    address_city             varchar(50),
    address_region           varchar(50),
    address_postal_code      varchar(10),
    address_country          varchar(3),
    socials_facebook         varchar(100),
    socials_instagram        varchar(100),
    socials_linkedin         varchar(100),
    socials_twitter          varchar(100),
    socials_youtube          varchar(100),
    created_date             timestamp               NOT NULL,
    last_modified_date       timestamp               NOT NULL,
    version                  INT                     NOT NULL
);