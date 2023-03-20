package hu.zsoltkiss.myproducts

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import hu.zsoltkiss.myproducts.persistence.entity.Product
import hu.zsoltkiss.myproducts.ui.crud.productList
import org.junit.Rule
import org.junit.Test

class ProductListTest {

    @get:Rule
    val testRule = createComposeRule()

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    private val testProducts = listOf(
        Product().also {
            it.name = "Chair"
            it.description = "Black office chair"
            it.quantity = 9
        },

        Product().also {
            it.name = "Desk"
            it.description = "Office desk with glass surface"
            it.quantity = 3
        },

        Product().also {
            it.name = "Armchair"
            it.description = "Purple, velvet covering"
            it.quantity = 1
        }
    )

    @Test
    fun hasProperDataOnScreen() {
        testRule.setContent {
            LazyColumn {
                productList(
                    products = testProducts,
                    onEdit = {},
                    onDelete = {}
                )
            }

        }

        testProducts.forEach {
            testRule.onNodeWithText(it.name).assertIsDisplayed()
            testRule.onNodeWithText(it.description).assertIsDisplayed()
            testRule.onNodeWithText(it.quantity.toString()).assertIsDisplayed()
        }

    }

}