package hu.zsoltkiss.myproducts.ui.crud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import hu.zsoltkiss.myproducts.Greeting
import hu.zsoltkiss.myproducts.data.TabItem
import hu.zsoltkiss.myproducts.helper.debugPrint
import hu.zsoltkiss.myproducts.ui.theme.MyProductsTheme
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CRUDActivity : ComponentActivity() {

    private val viewModel: CRUDViewModelImpl by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyProductsTheme {
                // A surface container using the 'background' color from the theme
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
                                    CreateContent(
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }

                                TabItem.Read -> {
                                    ReadContent(
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
    fun CreateContent(
        modifier: Modifier = Modifier
    ) {
        Text(
            text = "CREATE",
        )
    }

    @Composable
    fun ReadContent(
        modifier: Modifier = Modifier
    ) {
        Text(
            text = "READ",
        )
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