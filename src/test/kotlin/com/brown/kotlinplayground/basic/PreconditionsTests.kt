package com.brown.kotlinplayground.basic

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import kotlin.IllegalStateException

/**
 * Kotlin Preconditions 학습테스트
 */
class PreconditionsTests {

    inner class Member(
        val name: String,
        val age: Int,
        var skills : List<String>? = null
    )
    /**
     * value의 값이 false일 경우 [IllegalArgumentException]을 Throw 합니다
     */
    @Test
    fun requireBasicTest() {

        val member = Member("브라운", 35)

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
        val member = Member("브라운", 35)

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

    /**
     * value가 false라면 [IllegalStateException]을 반환한다
     */
    @Test
    fun checkTest(){
        val member = Member("브라운", 35)

        // 이름이 브라운이 아니면 예외를 던진다
        assertThrows<IllegalStateException>{
            check(member.name == "옐로우")
        }
    }

    @Test
    fun checkExceptionMessageTest(){
        val invalidNameMessage = "이름이상"
        val member = Member("브라운", 35)

        val ex = assertThrows<IllegalStateException> {
            check(member.name == "옐로우") { invalidNameMessage }
        }

        assertEquals(invalidNameMessage, ex.message)
    }

    @Test
    fun checkNotNullTest(){
        val noSkillMessage = "기술이 없습니다"
        val defaultMessage = "Required value was null."
        val member = Member("라이언", 10, null)

        val ex1 = assertThrows<IllegalStateException> {
            checkNotNull(member.skills)
        }
        assertEquals(defaultMessage, ex1.message)

        val ex2 = assertThrows<IllegalStateException> {
            checkNotNull(member.skills){noSkillMessage}
        }
        assertEquals(noSkillMessage, ex2.message)

        member.skills = listOf("JAVA", "SPRING", "JPA", "NODE.JS")
        assertNotNull(checkNotNull(member.skills))
    }

    /**
     * [IllegalStateException]예외를 Throw한다
     */
    @Test
    fun errorTest(){
        val errorMessage = "에러가 발생했습니다"
        val ex = assertThrows<IllegalStateException> {
            error(errorMessage)
        }

        assertEquals(errorMessage, ex.message)
    }
}