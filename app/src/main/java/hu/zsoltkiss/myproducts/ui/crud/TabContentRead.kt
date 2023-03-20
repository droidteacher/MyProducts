package hu.zsoltkiss.myproducts.ui.crud

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import hu.zsoltkiss.myproducts.persistence.entity.Product

@Composable
fun TabContentRead(
    items: List<Product>,
    onEditClick: (Product) -> Unit,
    onDeleteClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {

    if (items.isEmpty()) {
        Text(
            "No product has been stored yet",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 20.sp
            ),
            modifier = modifier.fillMaxHeight()
        )
    } else {
        LazyColumn {
            productList(products = items, onEdit = onEditClick, onDelete = onDeleteClick)
        }
    }
}