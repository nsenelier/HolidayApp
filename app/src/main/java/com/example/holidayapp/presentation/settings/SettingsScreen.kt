package com.example.holidayapp.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.holidayapp.core.components.TopAppBarContent

@Preview
@Composable
fun SettingsScreen(
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Surface(color = MaterialTheme.colors.background) {
            Column() {
                TopAppBarContent()
                SettingList()
            }

        }
    }
}

@Composable
fun SettingList(){
    val getAllData = SettingComponents().getAllData()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        item {
            Box(modifier = Modifier.fillMaxWidth().padding(start=60.dp, top=10.dp),

                ) {
                Text(text = "About the App")
            }
        }
        itemsIndexed(items = getAllData) { _, settingListModel ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(all = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = settingListModel.text
                )
            }
        }
    }
}