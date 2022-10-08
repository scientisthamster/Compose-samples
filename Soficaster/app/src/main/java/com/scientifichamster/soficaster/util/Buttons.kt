package com.scientifichamster.soficaster.util

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scientifichamster.soficaster.R
import com.scientifichamster.soficaster.ui.theme.SoficasterTheme

@Composable
fun ToggleFollowPodcastIconButton(
    isFollowed: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val clickLabel =
        stringResource(id = if (isFollowed) R.string.cd_unfollow else R.string.cd_follow)
    IconButton(
        onClick = onClick,
        modifier = modifier.semantics {
            onClick(label = clickLabel, action = null)
        }
    ) {
        Icon(
            imageVector = when {
                isFollowed -> Icons.Default.Check
                else -> Icons.Default.Add
            },
            contentDescription = when {
                isFollowed -> stringResource(id = R.string.cd_following)
                else -> stringResource(id = R.string.cd_not_following)
            },
            tint = animateColorAsState(
                when {
                    isFollowed -> LocalContentColor.current
                    else -> Color.Black.copy(alpha = ContentAlpha.high)
                }
            ).value,
            modifier = Modifier
                .shadow(
                    elevation = animateDpAsState(targetValue = if (isFollowed) 0.dp else 1.dp).value,
                    shape = MaterialTheme.shapes.small
                )
                .background(
                    color = animateColorAsState(
                        targetValue =
                        when {
                            isFollowed -> MaterialTheme.colors.surface.copy(0.38f)
                            else -> Color.White
                        }
                    ).value,
                    shape = MaterialTheme.shapes.small
                )
                .padding(4.dp)
        )
    }
}

@Preview
@Composable
fun ToggleFollowPodcastIconButtonPreview() {
    SoficasterTheme {
        ToggleFollowPodcastIconButton(false, {})
    }
}