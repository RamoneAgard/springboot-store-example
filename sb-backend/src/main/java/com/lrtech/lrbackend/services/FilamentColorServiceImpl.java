package com.lrtech.lrbackend.services;

import com.lrtech.lrbackend.models.FilamentColor;
import com.lrtech.lrbackend.repositories.FilamentColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FilamentColorServiceImpl implements FilamentColorService {

    private final FilamentColorRepository filamentColorRepository;

    @Override
    public Optional<FilamentColor> getById(UUID id) {
        return filamentColorRepository.findById(id);
    }
}
