package com.springdemo.project.Service;

import com.springdemo.project.Entity.UserEntry;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {


    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(UserEntry.builder().userName("personc").password("personc").build()),
                Arguments.of(UserEntry.builder().userName("parsond").password("parsond").build()),
                Arguments.of(UserEntry.builder().userName("anirudh").password("anirudh").build()),
                Arguments.of(UserEntry.builder().userName("reddemma").password("reddemma").build())
        );
    }
}
