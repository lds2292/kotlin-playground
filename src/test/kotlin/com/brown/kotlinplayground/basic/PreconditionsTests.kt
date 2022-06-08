package com.brown.kotlinplayground.basic

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

/**
 * Kotlin Preconditions 학습테스트
 */
class PreconditionsTests {

    inner class Member(
        val name: String,
        val age: Int,
        var skills : List<String>? = null
    )

    lateinit var member: Member

    @BeforeEach
    internal fun setUp() {
        member = Member("브라운", 35)
    }

    /**
     * value의 값이 false일 경우 [IllegalArgumentException]을 Throw 합니다
     */
    @Test
    fun requireBasicTest() {
        // 이름이 "브라운"이어야 한다
        assertThrows<IllegalArgumentException> {
            require(member.name == "옐로우")
        }
        assertDoesNotThrow {
            require(member.name == "브라운")
        }
    }

    /**
     * value의 값이 false일 경우 [IllegalArgumentException]을 Throw 하며 메세지를 출력한다
     */
    @Test
    fun requireMessageTest(){

        val invalidNameMessage = "이름이상"

        // 이름이 "브라운"이어야 하며 아닐 경우 "이름상이"라는 Exception 메시지를 출력한다
        val ex1 : IllegalArgumentException = assertThrows {
            require(member.name == "옐로우") { invalidNameMessage }
        }
        assertEquals(invalidNameMessage, ex1.message)

    }

    /**
     * value가 null이라면 [IllegalArgumentException]을 Throw 한다.
     *
     * null이 아니면 null이 아닌 value를 리턴한다
     */
    @Test
    fun requireNotNullTest(){

        val noSkillMessage = "기술이 없습니다"
        val defaultMessage = "Required value was null."
        val member = Member("라이언", 10, null)

        val ex1 = assertThrows<IllegalArgumentException> {
            requireNotNull(member.skills)
        }
        assertEquals(defaultMessage, ex1.message)

        val ex2 = assertThrows<IllegalArgumentException> {
            requireNotNull(member.skills){noSkillMessage}
        }
        assertEquals(noSkillMessage, ex2.message)

        member.skills = listOf("JAVA", "SPRING", "JPA", "NODE.JS")
        assertNotNull(requireNotNull(member.skills))
    }
}