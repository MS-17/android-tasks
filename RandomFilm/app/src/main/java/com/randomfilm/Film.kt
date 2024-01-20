package com.randomfilm

import kotlinx.serialization.Serializable

@Serializable
data class Film (var title: String, var description: String,
                 var year: Int, var starring: List<String>, var rating: Float) {}