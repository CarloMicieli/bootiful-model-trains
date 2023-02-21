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
package io.github.carlomicieli.catalog.brands;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.carlomicieli.catalog.CatalogServiceControllerAdvice;
import io.github.carlomicieli.catalog.config.WebConfig;
import io.github.carlomicieli.catalog.util.Slug;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("Brands controller")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@WebMvcTest({BrandsController.class, BrandsControllerAdvice.class, CatalogServiceControllerAdvice.class})
@Import({WebConfig.class})
class BrandsControllerTest {
    private static final Slug FIXED_BRAND_ID = Slug.of("ACME");

    @MockBean
    private BrandsService brandsService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("brands creation")
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class BrandsCreation {
        @Test
        void it_should_create_new_brands() throws Exception {
            var request = brandRequest();

            when(brandsService.createBrand(request)).thenReturn(FIXED_BRAND_ID);

            mockMvc.perform(post("/brands")
                            .content(asJsonString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(header().string("Location", "http://localhost/brands/" + FIXED_BRAND_ID));
        }

        @Test
        void it_should_validate_requests() throws Exception {
            var request = emptyBrandRequest();

            mockMvc.perform(post("/brands").content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                    .andExpect(jsonPath("$.instance", is("/brands")))
                    .andExpect(jsonPath("$.status", is(400)))
                    .andExpect(jsonPath("$.title", is("The request is not valid")))
                    .andExpect(jsonPath("$.type", is("https://api.bookmarks.com/errors/bad-request")))
                    .andExpect(jsonPath("$.errors.kind", is("must not be null")))
                    .andExpect(jsonPath("$.errors.name", is("must not be null")));
        }

        @Test
        void it_should_check_if_the_brand_already_exists() throws Exception {
            var request = brandRequest();

            when(brandsService.createBrand(request)).thenThrow(BrandAlreadyExistsException.class);

            mockMvc.perform(post("/brands").content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isConflict())
                    .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                    .andExpect(jsonPath("$.title", is("Conflict")))
                    .andExpect(jsonPath("$.type", is("https://api.bookmarks.com/errors/conflict")));
        }
    }

    @Nested
    @DisplayName("brands update")
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class BrandsUpdate {
        @Test
        void it_should_update_brands() throws Exception {
            var request = brandRequest();

            when(brandsService.createBrand(request)).thenReturn(FIXED_BRAND_ID);

            mockMvc.perform(put("/brands/{brandId}", "ACME")
                            .content(asJsonString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
        }

        @Test
        void it_should_validate_requests() throws Exception {
            var request = emptyBrandRequest();

            mockMvc.perform(put("/brands/{brandId}", "ACME")
                            .content(asJsonString(request))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                    .andExpect(jsonPath("$.instance", is("/brands/ACME")))
                    .andExpect(jsonPath("$.status", is(400)))
                    .andExpect(jsonPath("$.title", is("The request is not valid")))
                    .andExpect(jsonPath("$.type", is("https://api.bookmarks.com/errors/bad-request")))
                    .andExpect(jsonPath("$.errors.kind", is("must not be null")))
                    .andExpect(jsonPath("$.errors.name", is("must not be null")));
        }
    }

    @Nested
    @DisplayName("find brands by id")
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class FindBrandById {
        @Test
        void it_should_respond_with_NOT_FOUND_when_the_brand_is_not_found() throws Exception {
            mockMvc.perform(get("/brands/{brandId}", "acme")).andExpect(status().isNotFound());
        }

        @Test
        void it_should_find_brands() throws Exception {
            var brand = mock(Brand.class);
            when(brand.brandId()).thenReturn(FIXED_BRAND_ID);
            when(brandsService.getBrandById(FIXED_BRAND_ID)).thenReturn(Optional.of(brand));

            mockMvc.perform(get("/brands/{brandId}", FIXED_BRAND_ID))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/hal+json"))
                    .andExpect(jsonPath("$._links.self.href", is("http://localhost/brands/acme")));
        }
    }

    @Nested
    @DisplayName("find all brands")
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class FindAllBrands {
        @Test
        void it_should_respond_with_OK_when_there_are_no_brands() throws Exception {
            mockMvc.perform(get("/brands"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/hal+json"));
        }

        @Test
        void it_should_find_all_brands() throws Exception {
            var brand1 = mock(Brand.class);
            when(brand1.name()).thenReturn("Brand1");
            var brand2 = mock(Brand.class);
            when(brand2.name()).thenReturn("Brand2");
            when(brandsService.getBrands()).thenReturn(List.of(brand1, brand2));

            mockMvc.perform(get("/brands"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/hal+json"))
                    .andExpect(jsonPath("$._embedded.brand_model_list[0].name", is("Brand1")))
                    .andExpect(jsonPath("$._embedded.brand_model_list[1].name", is("Brand2")));
        }
    }

    String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    BrandRequest brandRequest() {
        return BrandRequestBuilder.builder()
                .name("ACME")
                .kind(BrandKind.INDUSTRIAL)
                .build();
    }

    BrandRequest emptyBrandRequest() {
        return BrandRequestBuilder.builder().build();
    }
}
