package com.kalculator.kalculator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@SpringBootApplication
class KAlculatorApplication

fun main(args: Array<String>) {
    runApplication<KAlculatorApplication>(*args)
}

@Controller
class CalculatorController(private val calculatorService: CalculatorService) {

	@GetMapping("/")
    fun calculadora(model: Model): String {
        model.addAttribute("resultado", null)
        return "index"
    }

    @PostMapping("/calcular")
    fun calcular(
        @RequestParam num1: Double,
        @RequestParam num2: Double,
        @RequestParam operacao: String,
        model: Model
    ): String {
        val resultado = when (operacao) {
            "add" -> calculatorService.add(num1, num2)
            "subtract" -> calculatorService.subtract(num1, num2)
            "multiply" -> calculatorService.multiply(num1, num2)
            "divide" -> calculatorService.divide(num1, num2)
            else -> throw IllegalArgumentException("Operação inválida")
        }

        model.addAttribute("resultado", resultado)
        return "index"
    }

 
}
