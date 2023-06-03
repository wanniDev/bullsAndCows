package me.flab.bullsandcows.presentation.game

import me.flab.bullsandcows.AbstractTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class GameApiTest: AbstractTest() {

    @Test
    @DisplayName("게임이 정상적으로 시작되면, 중복되지 않은 roomId를 메시지로 리턴합니다.")
    fun currentJvmMilliseconds() {
        val result = mockMvc.perform(RestDocumentationRequestBuilders.post("/game/start"))

        result.andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                MockMvcRestDocumentation.document(
                    "{class-name}/{method-name}",
                    getDocumentRequest(),
                    Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                    PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("success").type(JsonFieldType.BOOLEAN)
                            .description("api 성공여부"),
                        PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.OBJECT)
                            .description("api 페이로드 데이터"),
                        PayloadDocumentation.fieldWithPath("data.roomId").type(JsonFieldType.NUMBER)
                            .description("게임 방 번호")
                    )
                )
            )
    }
}