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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import io.github.carlomicieli.catalog.util.Slug;
import jakarta.validation.Valid;
import java.net.URI;
import java.time.Instant;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequestMapping("/brands")
@RestControllerAdvice
public class BrandsController {

    private final BrandsService brandsService;

    public BrandsController(BrandsService brandsService) {
        this.brandsService = brandsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> postBrands(@Valid @RequestBody BrandRequest request) {
        var brandSlug = brandsService.createBrand(request);
        return ResponseEntity.created(
                        linkTo(BrandsController.class).slash(brandSlug).toUri())
                .build();
    }

    @PutMapping("/{brandId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putBrands(@PathVariable Slug brandId, @Valid @RequestBody BrandRequest request) {
        brandsService.updateBrand(brandId, request);
    }

    @GetMapping("/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    BrandModel getBrandById(@PathVariable Slug brandId) {
        return brandsService
                .getBrandById(brandId)
                .map(BrandModel::new)
                .orElseThrow(() -> new BrandNotFoundException(brandId));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    CollectionModel<BrandModel> getBrands() {
        var brands = StreamSupport.stream(brandsService.getBrands().spliterator(), false)
                .map(BrandModel::new)
                .collect(Collectors.toList());
        return CollectionModel.of(brands);
    }

    @ExceptionHandler(BrandAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleAlreadyExistExceptions(BrandAlreadyExistsException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setInstance(
                linkTo(BrandsController.class).slash(ex.getBrandId()).toUri());
        problemDetail.setTitle(ex.getMessage());
        problemDetail.setType(URI.create("https://api.bookmarks.com/errors/conflict"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(BrandNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleNotFoundExceptions(BrandNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setInstance(
                linkTo(BrandsController.class).slash(ex.getBrandId()).toUri());
        problemDetail.setTitle(ex.getMessage());
        problemDetail.setType(URI.create("https://api.bookmarks.com/errors/not-found"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
