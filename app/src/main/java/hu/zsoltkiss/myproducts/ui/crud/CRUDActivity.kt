package hu.zsoltkiss.myproducts.ui.crud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.ui.Modifier
import hu.zsoltkiss.myproducts.Greeting
import hu.zsoltkiss.myproducts.helper.debugPrint
import hu.zsoltkiss.myproducts.ui.theme.MyProductsTheme
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CRUDActivity: ComponentActivity()  {

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
                    debugPrint("viewModel: $viewModel", step = 7777)
                    Text("Hey!")
                }
            }
        }
    }

}