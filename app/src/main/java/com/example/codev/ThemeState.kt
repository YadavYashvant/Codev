package com.example.codev

import android.content.res.Resources.Theme

data class ThemeState(var theme: com.example.codev.Theme) {
    fun toggleTheme() {
        theme = if(theme == com.example.codev.Theme.Light) com.example.codev.Theme.Dark else com.example.codev.Theme.Dark
    }
}

enum class Theme {
    Light, Dark
}
