package appjjang.fitpet.global.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI appjjangApi() {
        return new OpenAPI()
                .info(apiInfo())
                .components(authSetting())
                .addSecurityItem(securityRequirement());
    }

    private Info apiInfo() {
        return new Info().title("AppJjang API").description("AppJjang API 명세서").version("1.0.0");
    }

    private Components authSetting() {
        return new Components()
                .addSecuritySchemes(
                        "accessToken",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization"));
    }

    private SecurityRequirement securityRequirement() {
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList("accessToken");
        return securityRequirement;
    }

    @Bean
    public OperationCustomizer operationCustomizer() {
        return (operation, handlerMethod) -> {
            this.addResponseBodyWrapperSchemaExample(operation);
            return operation;
        };
    }

    private void addResponseBodyWrapperSchemaExample(Operation operation) {
        // 성공 응답 스키마 설정
        if (operation.getResponses().containsKey("200")) {
            final Content successContent = operation.getResponses().get("200").getContent();
            if (successContent != null) {
                successContent.forEach((mediaTypeKey, mediaType) -> {
                    Schema<?> originalSchema = mediaType.getSchema();
                    Schema<?> wrappedSchema = wrapSuccessSchema(originalSchema);
                    mediaType.setSchema(wrappedSchema);
                });
            }
        }

        // 실패 응답 스키마 설정 (예: 400 Bad Request)
        if (operation.getResponses().containsKey("400")) {
            final Content failContent = operation.getResponses().get("400").getContent();
            if (failContent != null) {
                failContent.forEach((mediaTypeKey, mediaType) -> {
                    Schema<?> originalSchema = mediaType.getSchema();
                    Schema<?> wrappedSchema = wrapFailSchema(originalSchema);
                    mediaType.setSchema(wrappedSchema);
                });
            }
        }
    }

    private Schema<?> wrapSuccessSchema(Schema<?> originalSchema) {
        final Schema<?> wrapperSchema = new Schema<>();

        wrapperSchema.addProperty("success", new Schema<>().type("boolean").example(true));
        wrapperSchema.addProperty("status", new Schema<>().type("integer").example(200));
        wrapperSchema.addProperty("data", originalSchema);
        wrapperSchema.addProperty("timestamp", new Schema<>().type("string").format("date-time").example(
                LocalDateTime.now().toString()));

        return wrapperSchema;
    }

    private Schema<?> wrapFailSchema(Schema<?> originalSchema) {
        final Schema<?> wrapperSchema = new Schema<>();

        wrapperSchema.addProperty("success", new Schema<>().type("boolean").example(false));
        wrapperSchema.addProperty("status", new Schema<>().type("integer").example(400));
        wrapperSchema.addProperty("data", originalSchema);
        wrapperSchema.addProperty("timestamp", new Schema<>().type("string").format("date-time").example(
                LocalDateTime.now().toString()));

        return wrapperSchema;
    }
}

