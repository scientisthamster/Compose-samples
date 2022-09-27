package com.scientifichamster.mcdonaldscompose.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scientifichamster.mcdonaldscompose.ui.theme.McdonaldsComposeTheme

private enum class QuantityToggleState { Zero, NonZero }

@Composable
fun QuantityToggle(
    quantity: Int,
    onIncrementQuantity: () -> Unit,
    onDecrementQuantity: () -> Unit,
) {

    val transition =
        updateTransition(
            targetState = if (quantity == 0) QuantityToggleState.Zero else QuantityToggleState.NonZero,
            label = "transition"
        )

    val backgroundColor by transition.animateColor(label = "backgroundColor") { state ->
        when (state) {
            QuantityToggleState.Zero -> MaterialTheme.colors.background
            QuantityToggleState.NonZero -> MaterialTheme.colors.secondary
        }
    }

    val contentColor by transition.animateColor(label = "contentColor") { state ->
        when (state) {
            QuantityToggleState.Zero -> MaterialTheme.colors.secondary
            QuantityToggleState.NonZero -> LocalContentColor.current
        }
    }

    val iconSize by transition.animateDp(label = "iconSize") { state ->
        when (state) {
            QuantityToggleState.Zero -> 0.dp
            QuantityToggleState.NonZero -> 18.dp
        }
    }

    Surface(
        modifier = Modifier
            .width(90.dp)
            .aspectRatio(2.75f),
        shape = MaterialTheme.shapes.small,
        color = backgroundColor,
        contentColor = contentColor,
        border = BorderStroke(2.dp, MaterialTheme.colors.secondary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = if (quantity == 0) onIncrementQuantity else onDecrementQuantity),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.Done,
                modifier = Modifier
                    .size(iconSize)
            )
            Text(
                text = if (quantity == 0) "ADD" else "ADDED",
                style = MaterialTheme.typography.button,
                modifier = Modifier.animateContentSize()
            )
        }
    }
}

@Preview("QuantityToggle • Zero")
@Composable
private fun QuantityToggleZeroPreview() {
    McdonaldsComposeTheme {
        QuantityToggle(quantity = 0, onIncrementQuantity = {}) {}
    }
}

@Preview("QuantityToggle • Zero • DarkTheme")
@Composable
private fun QuantityToggleZeroDarkThemePreview() {
    McdonaldsComposeTheme(darkTheme = true) {
        QuantityToggle(quantity = 0, onIncrementQuantity = {}) {}
    }
}

@Preview("QuantityToggle • NonZero")
@Composable
private fun QuantityToggleNonZeroPreview() {
    McdonaldsComposeTheme {
        QuantityToggle(quantity = 1, onIncrementQuantity = {}) {}
    }
}

@Preview("QuantityToggle • NonZero • DarkTheme")
@Composable
private fun QuantityToggleNonZeroDarkThemePreview() {
    McdonaldsComposeTheme(darkTheme = true) {
        QuantityToggle(quantity = 1, onIncrementQuantity = {}) {}
    }
}

