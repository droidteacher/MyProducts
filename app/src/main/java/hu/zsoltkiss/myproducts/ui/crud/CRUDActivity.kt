package hu.zsoltkiss.myproducts.ui.crud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.zsoltkiss.myproducts.data.TabItem
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
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            when (viewModel.selectedTab.value) {
                                TabItem.Create -> {
                                    TabContentCreate(
                                        existingProduct = viewModel.beingEdited,
                                        onProductCreate = viewModel::createProduct,
                                        onProductEdit = viewModel::confirmUpdate,
                                        modifier = Modifier.align(Alignment.Center),
                                    )
                                }

                                TabItem.Read -> {
                                    TabContentRead(
                                        items = viewModel.products.value,
                                        onEditClick = viewModel::onEditRequest,
                                        onDeleteClick = viewModel::onDeleteRequest,
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

                    if (viewModel.shouldDisplayConfirmDialog.value) {
                        ConfirmDeleteDialog(
                            onConfirm = viewModel::confirmDelete,
                            onCancel = viewModel::cancelDelete,
                            onDismiss = viewModel::cancelDelete
                        )
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (viewModel.selectedTab.value == TabItem.Read) {
            viewModel.fetchAll()
        }
    }
}