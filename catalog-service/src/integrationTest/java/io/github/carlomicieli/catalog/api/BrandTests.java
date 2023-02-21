/*
 *   Copyright (c) 2023 Carlo Micieli
 *
 *    Licensed to the Apache Software Foundation (ASF) under one
 *    or more contributor license agreements.  See the NOTICE file
 *    distributed with this work for additional information
 *    regarding copyright ownership.  The ASF licenses this file
 *    to you under the Apache License, Version 2.0 (the
 *    "License"); you may not use this file except in compliance
 *    with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing,
 *    software distributed under the License is distributed on an
 *    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *    KIND, either express or implied.  See the License for the
 *    specific language governing permissions and limitations
 *    under the License.
 */
package io.github.carlomicieli.catalog.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@DisplayName("brands api")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("seed")
class BrandTests {
    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    void it_should_create_new_brands() {
        // fmt:off
        given()
            .contentType(ContentType.JSON)
        .body(
            """
            {
                "name" : "aa182b9f-eaab-4bff-acd6-8fae5e5cc8f8",
                "registered_company_name" : "Registered Company Ltd",
                "organization_entity_type" : "LIMITED_COMPANY",
                "group_name": "UNKNOWN",
                "description" : {
                    "it" : "descrizione",
                    "en" : "description"
                },
                "address" : {
                    "street_address" : "Rue Morgue 22",
                    "extended_address" : null,
                    "postal_code" : "1H2 4BB",
                    "city" : "London",
                    "region" : null,
                    "country" : "GB"
                },
                "contact_info" : {
                    "email" : "mail@mail.com",
                    "phone" : "+14152370800",
                    "website_url" : "https://www.site.com"
                },
                "socials" : {
                    "facebook" : "facebook_handler",
                    "instagram" : "instagram_handler",
                    "linkedin" : "linkedin_handler",
                    "twitter" : "twitter_handler",
                    "youtube" : "youtube_handler"
                },
                "kind" : "INDUSTRIAL",
                "status" : "ACTIVE"
            }
            """)
        .when()
            .post("/brands")
        .then()
            .statusCode(201)
            .header("Location", "http://localhost:" + port + "/brands/aa182b9f-eaab-4bff-acd6-8fae5e5cc8f8");
        // fmt:on
    }

    @Test
    void it_should_update_existing_brands() {
        // fmt:off
        var brandName = "8669f9b3-7505-4ea6-955e-70d89eb9db60";
        given()
            .contentType(ContentType.JSON)
            .body(
                """
                {
                    "name" : "8669f9b3-7505-4ea6-955e-70d89eb9db60",
                    "registered_company_name" : "Registered Company Ltd",
                    "organization_entity_type" : "LIMITED_COMPANY",
                    "group_name": "UNKNOWN",
                    "description" : {
                        "it" : "descrizione",
                        "en" : "description"
                    },
                    "address" : {
                        "street_address" : "Rue Morgue 22",
                        "extended_address" : null,
                        "postal_code" : "1H2 4BB",
                        "city" : "London",
                        "region" : null,
                        "country" : "GB"
                    },
                    "contact_info" : {
                        "email" : "mail@mail.com",
                        "phone" : "+14152370800",
                        "website_url" : "https://www.site.com"
                    },
                    "socials" : {
                        "facebook" : "facebook_handler",
                        "instagram" : "instagram_handler",
                        "linkedin" : "linkedin_handler",
                        "twitter" : "twitter_handler",
                        "youtube" : "youtube_handler"
                    },
                    "kind" : "INDUSTRIAL",
                    "status" : "ACTIVE"
                }
                """)
            .when()
            .put("/brands/{brand}", brandName)
            .then()
            .statusCode(204);
        // fmt:on
    }

    @Test
    void it_should_find_brands_by_id() {
        // fmt:off
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/brands/{brandId}", "acme")
        .then()
            .statusCode(200)
            .contentType("application/hal+json")
            .body("_links.self.href", is("http://localhost:" + port + "/brands/acme"))
            .body("name", is("ACME"))
            .body("registered_company_name", is("Associazione Costruzioni Modellistiche Esatte"))
            .body("organization_entity_type", is("OTHER"))
            .body("description.it", is("descrizione"))
            .body("description.en", is("description"))
            .body("address.street_address", is("Viale Lombardia, 27"))
            .body("address.postal_code", is("20131"))
            .body("address.city", is("Milano"))
            .body("address.region", is("MI"))
            .body("address.country", is("IT"))
            .body("contact_info.email", is("mail@acmetreni.com"))
            .body("contact_info.phone", is("+14152370800"))
            .body("contact_info.website_url", is("http://www.acmetreni.com"))
            .body("socials.facebook", is("facebook_handler"))
            .body("socials.instagram", is("instagram_handler"))
            .body("socials.linkedin", is("linkedin_handler"))
            .body("socials.twitter", is("twitter_handler"))
            .body("socials.youtube", is("youtube_handler"))
            .body("kind", is("INDUSTRIAL"))
            .body("status", is("ACTIVE"));
        // fmt:on
    }
}
