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
package io.github.carlomicieli.catalog;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.carlomicieli.catalog.brands.BrandRequest;
import io.github.carlomicieli.catalog.brands.BrandsService;
import io.github.carlomicieli.catalog.util.Slug;
import java.io.InputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("seed")
public class CatalogSeeding implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(CatalogSeeding.class);
    private final BrandsService brandsService;

    public CatalogSeeding(BrandsService brandsService) {
        this.brandsService = brandsService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (brandsService.getBrandById(Slug.of("ACME")).isPresent()) {
            log.warn("Database contains data already. Skipping the catalog seeding");
            return;
        }

        var fileContent = readFileFromResources("data/brands.json");
        ObjectMapper mapper = new ObjectMapper();
        Brands brands = mapper.readValue(fileContent, Brands.class);

        for (var b : brands.items()) {
            brandsService.createBrand(b);
        }
    }

    private static InputStream readFileFromResources(String filename) {
        return CatalogSeeding.class.getClassLoader().getResourceAsStream(filename);
    }
}

record Brands(List<BrandRequest> items) {}
