package com.caixa.fluxoDeCaixa.enums.converters;

import com.caixa.fluxoDeCaixa.enums.Status;
import jakarta.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status type) {
        if (type == null) {
            return null;
        }
        return type.getValor();
    }

    @Override
    public Status convertToEntityAttribute(Integer value) {
        return Status.fromCode(value);
    }
}
