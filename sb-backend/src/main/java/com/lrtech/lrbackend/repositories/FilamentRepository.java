package com.lrtech.lrbackend.repositories;

import com.lrtech.lrbackend.models.Filament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilamentRepository extends JpaRepository<Filament, UUID> {
}
