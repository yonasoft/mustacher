package com.yonasoft.mustacher.presentation.components.bottom_nav_bar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.yonasoft.mustacher.R


@Composable
fun BottomNavBar(
    selectedItemIndex: Int,
    changeItemIndex: (Int) ->Unit
) {
    val items = listOf(
        BottomNavigationItem(
            title = "Record",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.baseline_fiber_manual_record_24),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.outline_fiber_manual_record_24),
        ),
        BottomNavigationItem(
            title = "Gallery",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.baseline_insert_photo_24),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.outline_insert_photo_24),
        )
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(selected = selectedItemIndex == index, onClick = {
                changeItemIndex(index)
            }, icon = {
                if (selectedItemIndex == index) {
                    Icon(
                        imageVector = item.selectedIcon,
                        contentDescription = item.title
                    )
                } else {
                    Icon(
                        imageVector = item.unselectedIcon,
                        contentDescription = item.title
                    )
                }
            }, label = {
                Text(text = item.title) },
            )
        }
    }
}