package hu.zsoltkiss.myproducts.ui.crud

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hu.zsoltkiss.myproducts.persistence.entity.Product

fun LazyListScope.productList(
    products: List<Product>,
    onEdit: (Product) -> Unit,
    onDelete: (Product) -> Unit,
) {
    items(products) { product ->
        ProductCard(
            product,
            onEdit = onEdit,
            onDelete = onDelete
        )
    }
}

@Composable
fun ProductCard(
    someProduct: Product,
    onEdit: (Product) -> Unit,
    onDelete: (Product) -> Unit,
) {
    Card(
        elevation = 10.dp,
        modifier = Modifier.padding(10.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = someProduct.name,
                    style = TextStyle(
                        fontSize = 24.sp
                    ),
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = someProduct.description,
                    style = TextStyle(
                        fontSize = 16.sp
                    ),
                    color = Color.Gray
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.LightGray,
                    modifier = Modifier.clickable { onEdit(someProduct) }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text("${someProduct.quantity}", textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.LightGray,
                    modifier = Modifier.clickable { onDelete(someProduct) }
                )
            }
        }
    }
}