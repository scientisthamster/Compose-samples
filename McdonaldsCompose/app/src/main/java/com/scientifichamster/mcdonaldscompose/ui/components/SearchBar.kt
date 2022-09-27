package com.scientifichamster.mcdonaldscompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scientifichamster.mcdonaldscompose.ui.theme.McdonaldsComposeTheme

@Composable
fun SearchBar(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = {})
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Rounded.Search)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Normal)
            )
        }
    }
}

@Preview("SearchBar")
@Composable
private fun SearchBarPreview() {
    McdonaldsComposeTheme {
        SearchBar(text = "Find what you want...")
    }
}

@Preview("SearchBar • DarkTheme")
@Composable
private fun SearchBarDarkTheme() {
    McdonaldsComposeTheme(darkTheme = true) {
        SearchBar(text = "Find what you want...")
    }
}