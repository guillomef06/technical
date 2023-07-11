package com.example.technical.mappers;

import org.mapstruct.MapperConfig;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.WARN,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface UnmappedTargetPolicyConfig {
}
