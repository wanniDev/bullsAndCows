package me.flab.bullsandcows

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class HealthCheckApiTest: AbstractTest() {

    @Test
    @DisplayName("현재 동작하고 있는 jvm의 system current milliseconds 를 출력한다.")
    fun currentJvmMilliseconds() {
        val result = mockMvc.perform(RestDocumentationRequestBuilders.get("/api/curr/mills"))

        result.andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                MockMvcRestDocumentation.document(
                    "{class-name}/{method-name}",
                    getDocumentRequest(),
                    Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                    PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("currentMilliseconds").type(JsonFieldType.NUMBER)
                            .description("JVM 시스템 현재 밀리초")
                    )
                )
            )
    }
}