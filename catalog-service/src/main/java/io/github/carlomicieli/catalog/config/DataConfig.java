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
package io.github.carlomicieli.catalog.config;

import io.github.carlomicieli.catalog.brands.BrandsRepository;
import io.github.carlomicieli.catalog.railways.RailwaysRepository;
import io.github.carlomicieli.catalog.scales.ScalesRepository;
import io.github.carlomicieli.catalog.util.Slug;
import jakarta.annotation.Nonnull;
import java.net.URI;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJdbcRepositories(basePackageClasses = {BrandsRepository.class, RailwaysRepository.class, ScalesRepository.class})
@EnableJdbcAuditing
@EnableTransactionManagement
public class DataConfig extends AbstractJdbcConfiguration {
    @Override
    protected List<?> userConverters() {
        return List.of(
                new SlugReadingConverter(),
                new SlugWritingConverter(),
                new URIReadingConverter(),
                new URIWritingConverter());
    }

    @WritingConverter
    static class URIWritingConverter implements Converter<URI, String> {
        @Override
        public String convert(URI value) {
            return value.toString();
        }
    }

    @ReadingConverter
    static class URIReadingConverter implements Converter<String, URI> {
        @Override
        public URI convert(@Nonnull String value) {
            return URI.create(value);
        }
    }

    @WritingConverter
    static class SlugWritingConverter implements Converter<Slug, String> {
        @Override
        public String convert(Slug value) {
            return value.value();
        }
    }

    @ReadingConverter
    static class SlugReadingConverter implements Converter<String, Slug> {
        @Override
        public Slug convert(@Nonnull String value) {
            return Slug.of(value);
        }
    }
}
