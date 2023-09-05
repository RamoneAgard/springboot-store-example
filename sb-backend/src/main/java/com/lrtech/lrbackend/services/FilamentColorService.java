package com.lrtech.lrbackend.services;

import com.lrtech.lrbackend.models.FilamentColor;

import java.util.Optional;
import java.util.UUID;

public interface FilamentColorService {

    Optional<FilamentColor> getById(UUID id);
}
