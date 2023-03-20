package hu.zsoltkiss.myproducts.ui.crud

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import hu.zsoltkiss.myproducts.data.TabItem

@Composable
fun BottomTabs(
    selectedTab: TabItem,
    onSelect: (TabItem) -> Unit,
    modifier: Modifier = Modifier
) {

    BottomAppBar(
        cutoutShape = CircleShape
    ) {

        listOf(
            TabItem.Create,
            TabItem.Read
        ).forEach { tabItem ->
            BottomNavigationItem(
                selected = (selectedTab == tabItem),
                onClick = {
                    onSelect(tabItem)
                },
                icon = {
                    Icon(
                        imageVector = tabItem.image,
                        contentDescription = tabItem.description,
                        tint = when (selectedTab == tabItem) {
                            true -> Color(255, 141, 0)
                            else -> Color.LightGray
                        }
                    )
                },
                label = {
                    Text(
                        text = tabItem.label,
                        color = when (selectedTab == tabItem) {
                            true -> Color(255, 141, 0)
                            else -> Color.LightGray
                        }
                    )
                },
                enabled = true
            )
        }
    }
}