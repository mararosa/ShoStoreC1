package com.udacity.shoestore.shoedetail.domain.usecase

class ValidateInputValue {

    fun execute(input: String): ValidationResult {
        return if (input.isBlank()) {
            ValidationResult(
                success = false,
                errorMessage = "Field Required"
            )
        } else {
            ValidationResult(
                success = true
            )
        }
    }
}