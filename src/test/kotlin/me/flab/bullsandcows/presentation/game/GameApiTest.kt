package me.flab.bullsandcows.presentation.game

import me.flab.bullsandcows.AbstractTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class GameApiTest: AbstractTest() {

    @Test
    @DisplayName("게임이 정상적으로 시작되면, 중복되지 않은 roomId를 메시지로 리턴합니다.")
    fun newGameTest() {
        val result = mockMvc.perform(
            RestDocumentationRequestBuilders.post("/game/start")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        result.andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("data").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("data.roomId").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("success").value(true))
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

    @Test
    @DisplayName("사용자가 정상적으로 번호를 전달하면, 정답 여부와 점수 현환을 확인할 수 있다.")
    fun proceedGameTest() {
        val guessNumber = "123"
        val result = mockMvc.perform(
            RestDocumentationRequestBuilders.post("/game/{guessNumber}/answer", guessNumber)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        result.andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("data").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("success").value(true))
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
                        PayloadDocumentation.fieldWithPath("data.correct").type(JsonFieldType.BOOLEAN)
                            .description("숫자 야구 정답 여부"),
                        PayloadDocumentation.fieldWithPath("data.remainingCount").type(JsonFieldType.NUMBER)
                            .description("숫자 야구 남은 시도 횟수"),
                        PayloadDocumentation.fieldWithPath("data.strike").type(JsonFieldType.NUMBER)
                            .description("숫자 야구 숫자와 위치 두 개를 전부 맞춘 횟수"),
                        PayloadDocumentation.fieldWithPath("data.ball").type(JsonFieldType.NUMBER)
                            .description("숫자 야구 숫자 맞췄지만, 위치를 틀린 횟수"),
                        PayloadDocumentation.fieldWithPath("data.out").type(JsonFieldType.NUMBER)
                            .description("숫자 야구 숫자와 위치 둘다 틀린 횟수")
                    )
                )
            )
    }
}