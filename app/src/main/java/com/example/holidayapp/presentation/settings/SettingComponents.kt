package com.example.holidayapp.presentation.settings

class SettingComponents {
    fun getAllData(): List<SettingListModel> {
        return listOf(
            SettingListModel(
                contentDescription = "About",
                text = "This App provides the listing of holidays by country"
            ),
            SettingListModel(
                contentDescription = "Version app",
                text = "Version 2.0"
            )
        )
    }
}

data class SettingListModel(
    val contentDescription: String,
    val text: String
)