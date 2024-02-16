import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.ui.Model
import org.springframework.validation.support.BindingAwareModelMap
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CalculatorControllerTest {

    @Mock
    private lateinit var calculatorService: CalculatorService

    @InjectMocks
    private lateinit var calculatorController: CalculatorController

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testCalculadora() {
        val model = BindingAwareModelMap()

        val result = calculatorController.calculadora(model)

        assertEquals("index", result)
        assertNull(model["resultado"])
    }

    @Test
    fun testCalcular() {
        val model = BindingAwareModelMap()
        val num1 = 10.0
        val num2 = 5.0
        val operacao = "add"
        val expectedResult = 15.0

        // Mocking the behavior of calculatorService
        Mockito.`when`(calculatorService.add(num1, num2)).thenReturn(expectedResult)

        val result = calculatorController.calcular(num1, num2, operacao, model)

        assertEquals("index", result)
        assertEquals(expectedResult, model["resultado"])
    }
}
