package com.softarex.technical_proj.entities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<QuestionType, String> {
    @Override
    public String convertToDatabaseColumn(QuestionType questionType) {
        if (questionType == null)
            return null;
        return questionType.getVal();
    }

    @Override
    public QuestionType convertToEntityAttribute(String code) {
        if (code == null)
            return null;
        return Stream.of(QuestionType.values())
                .filter(c -> c.getVal().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
