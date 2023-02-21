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
package io.github.carlomicieli.catalog.util;

import java.text.Normalizer;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * It converts a string to a "slug".
 *
 * @see <a href="http://www.codecodex.com/wiki/Generate_a_url_slug#Java">Original implementation</a>
 */
public record Slug(String value) {
    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final String SEP = "-";

    public Slug(String value) {
        if (Objects.isNull(value)) {
            throw new InvalidSlugException();
        }

        this.value = toSeoFriendlyString(value);
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Checks whether the provided {@code slugValue} is not empty, otherwise the method will use the
     * {@code supplier} function to produce a value.
     *
     * @param slugValue the (possible empty or {@code null} slug value
     * @param supplier the slug supplier
     * @return the slug
     */
    public static String orElseGet(String slugValue, Supplier<String> supplier) {
        if (slugValue != null && !slugValue.isEmpty()) {
            return slugValue;
        }

        try {
            return supplier.get();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Encodes the provided String as {@code slug}.
     *
     * @param str the String to be encoded
     * @return the slug
     */
    public static Slug of(String str) {
        return new Slug(str);
    }

    public static Slug ofValues(String s1, String s2) {
        var v = String.format("%s%s%s", Slug.toSeoFriendlyString(s1), SEP, Slug.toSeoFriendlyString(s2));
        return new Slug(v);
    }

    /**
     * Joins the provided values and encode them as {@code slug}.
     *
     * @param values the values to be joined and then encoded
     * @return the slug
     */
    public static Slug ofValues(Object... values) {
        if (Objects.isNull(values)) {
            throw new InvalidSlugException();
        }

        Predicate<Object> valueIsNotNull = obj -> !Objects.isNull(obj);
        var str = Stream.of(values)
                .filter(valueIsNotNull)
                .map(Object::toString)
                .map(Slug::toSeoFriendlyString)
                .collect(Collectors.joining(SEP));
        return new Slug(str);
    }

    private static String toSeoFriendlyString(String str) {
        String noWhitespace = WHITESPACE.matcher(str).replaceAll(SEP);
        String normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD);
        return NON_LATIN.matcher(normalized).replaceAll("").toLowerCase(Locale.ENGLISH);
    }
}
