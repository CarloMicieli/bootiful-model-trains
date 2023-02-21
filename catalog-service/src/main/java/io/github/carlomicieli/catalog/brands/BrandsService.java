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

import io.github.carlomicieli.catalog.util.Slug;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BrandsService {

    private final BrandRepository brandRepository;

    public BrandsService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Slug createBrand(BrandRequest request) {
        var newBrand = brandFromRequest(request, null);
        if (brandRepository.existsById(newBrand.brandId())) {
            throw new BrandAlreadyExistsException(newBrand.brandId());
        }
        brandRepository.save(newBrand);
        return newBrand.brandId();
    }

    public void updateBrand(Slug brandId, BrandRequest request) {
        var entity = brandRepository
                .findById(brandId)
                .map(existingBrand -> brandFromRequest(request, existingBrand))
                .orElseGet(() -> brandFromRequest(request, null));

        brandRepository.save(entity);
    }

    Brand brandFromRequest(BrandRequest request, Brand brand) {
        var existingBrand = Optional.ofNullable(brand);
        var id = existingBrand.map(Brand::brandId).orElseGet(() -> Slug.of(request.name()));

        return BrandBuilder.builder()
                .brandId(id)
                .address(request.address())
                .organizationEntityType(request.organizationEntityType())
                .contactInfo(request.contactInfo())
                .name(request.name())
                .groupName(request.groupName())
                .registeredCompanyName(request.registeredCompanyName())
                .description(request.description())
                .kind(request.kind())
                .socials(request.socials())
                .status(request.status())
                .createdDate(existingBrand.map(Brand::createdDate).orElse(null))
                .lastModifiedDate(existingBrand.map(Brand::lastModifiedDate).orElse(null))
                .version(existingBrand.map(Brand::version).orElse(null))
                .build();
    }

    public Optional<Brand> getBrandById(Slug brandId) {
        return brandRepository.findById(brandId);
    }

    public Iterable<Brand> getBrands() {
        return brandRepository.findAll();
    }
}
