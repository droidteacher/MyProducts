package hu.zsoltkiss.myproducts.ui.crud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hu.zsoltkiss.myproducts.data.TabItem
import hu.zsoltkiss.myproducts.persistence.entity.Product
import hu.zsoltkiss.myproducts.ui.theme.MyProductsTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class CRUDActivity : ComponentActivity() {

    private val viewModel: CRUDViewModelImpl by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyProductsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Column(Modifier.fillMaxSize()) {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)) {
                            when(viewModel.selectedTab.value) {
                                TabItem.Create -> {
                                    CreateTabContent(
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }

                                TabItem.Read -> {
                                    ReadTabContent(
                                        items = viewModel.products.value,
                                        onEditClick = viewModel::onEditProduct,
                                        onDeleteClick = viewModel::onDeleteProduct,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }

                        BottomTabs(
                            selectedTab = viewModel.selectedTab.value,
                            onSelect = viewModel::onSelectTab,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun CreateTabContent(
        modifier: Modifier = Modifier
    ) {

    }

    @Composable
    fun ReadTabContent(
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
}