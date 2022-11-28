
package com.petfinder.util

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState

private class ScrollInfo(
    var index: Int = 0,
    var scrollOffset: Int = 0,
)

@Composable
fun LazyListState.isScrollingForwardsAsState(): State<Boolean> {
    val oldScrollInfo = remember(this) { ScrollInfo() }
    val scrollOffset by rememberUpdatedState(firstVisibleItemScrollOffset)
    val index by rememberUpdatedState(firstVisibleItemIndex)
    return remember(this) {
        derivedStateOf {
            val isForward = when {
                oldScrollInfo.index != index -> oldScrollInfo.index < index
                else -> oldScrollInfo.scrollOffset < scrollOffset
            }
            oldScrollInfo.scrollOffset = scrollOffset
            oldScrollInfo.index = index
            return@derivedStateOf isForward
        }
    }
}

@Composable
fun LazyListState.isLastItemVisibleAsState(): State<Boolean> {
    return remember {
        derivedStateOf {
            layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
        }
    }
}
