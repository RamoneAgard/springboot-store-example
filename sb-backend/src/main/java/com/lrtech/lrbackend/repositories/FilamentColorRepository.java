package com.lrtech.lrbackend.repositories;

import com.lrtech.lrbackend.models.FilamentColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilamentColorRepository extends JpaRepository<FilamentColor, UUID> {
}
