package com.udacity.shoestore.shoedetail.domain.usecase


data class ValidationResult(val success: Boolean, val errorMessage: String? = null)
