package com.marek.model.sectors;

import lombok.Builder;

import java.util.List;

@Builder
public record Sector(long id, String name, List<Sector> children) {
}
