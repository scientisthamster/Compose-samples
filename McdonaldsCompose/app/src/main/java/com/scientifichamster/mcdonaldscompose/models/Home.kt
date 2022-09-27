package com.scientifichamster.mcdonaldscompose.models

data class Home(
    val user: User,
    val categories: List<Category>,
    val popularMenuItems: List<MenuItem>,
    val recommendedMenuItems: List<MenuItem>,
)
