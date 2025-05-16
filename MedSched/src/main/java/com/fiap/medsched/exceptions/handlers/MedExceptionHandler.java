package com.fiap.medsched.exceptions.handlers;

import com.fiap.medsched.exceptions.MedException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;
@Component
public class MedExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable exception, DataFetchingEnvironment environment) {
        if (exception instanceof MedException) {
            return GraphqlErrorBuilder.newError()
                    .message(exception.getMessage())
                    .location(environment.getField().getSourceLocation())
                    .build();
        }

        return GraphqlErrorBuilder.newError()
                .message("Erro interno inesperado")
                .location(environment.getField().getSourceLocation())
                .build();
    }
}

