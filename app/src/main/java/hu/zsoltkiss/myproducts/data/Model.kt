package hu.zsoltkiss.myproducts.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

enum class TabItem(val label: String, val image: ImageVector, val description: String) {
    Create("Create", Icons.Filled.Create, "Create tab"),
    Read("Read", Icons.Filled.Info, "Read tab")
}