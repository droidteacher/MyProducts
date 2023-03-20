package hu.zsoltkiss.myproducts

import hu.zsoltkiss.myproducts.data.TabItem
import hu.zsoltkiss.myproducts.persistence.dao.ProductDao
import hu.zsoltkiss.myproducts.persistence.entity.Product
import hu.zsoltkiss.myproducts.ui.crud.CRUDViewModel
import hu.zsoltkiss.myproducts.ui.crud.CRUDViewModelImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import kotlin.test.assertEquals

class CRUDViewModelTest: KoinTest {

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

    private val daoMock = mockk<ProductDao> {
        every { fetchAll() } returns testProducts
    }

    private val testDriveModule = module {
        single { daoMock }
        factory<CRUDViewModel> { CRUDViewModelImpl(get()) }
    }

    private val createTab = TabItem.Create
    private val readTab = TabItem.Read

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.DEBUG)
        modules(listOf(testDriveModule))
    }

    private lateinit var sut: CRUDViewModel

    @Before
    fun setUp() {
        sut = get<CRUDViewModel>()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `GIVEN view model initialized THEN tab selection defaults to TabItem Read`() {
        assertEquals(TabItem.Read, sut.selectedTab.value)
    }

    @Test
    fun `GIVEN view model initialized THEN products list is empty`() {
        assertEquals(emptyList(), sut.products.value)
    }

    @Test
    fun `GIVEN view model initialized THEN shouldDisplayConfirmDialog defaults to false`() {
        assertEquals(false, sut.shouldDisplayConfirmDialog.value)
    }

    @Test
    fun `GIVEN view model initialized WHEN onSelectTab called THEN selectedTab changes for the proper value`() {
        sut.onSelectTab(createTab)
        assertEquals(createTab, sut.selectedTab.value)
    }

    @Test
    fun `GIVEN create tab is the selected one WHEN createProduct is called THEN it delegates to Dao and changes to read tab`() {
        sut.onSelectTab(createTab)
        sut.createProduct(testProducts[0].name, testProducts[0].description, testProducts[0].quantity)
        assertEquals(readTab, sut.selectedTab.value)
        verify(exactly = 1) {
            daoMock.insertProduct(testProducts[0])
            daoMock.fetchAll()
        }
    }

    @Test
    fun `GIVEN read tab is the selected one WHEN onEditRequest is called THEN it changes to create tab`() {
        sut.onSelectTab(readTab)
        sut.onEditRequest(testProducts[0])
        assertEquals(createTab, sut.selectedTab.value)
    }

    @Test
    fun `GIVEN create tab is the selected one WHEN confirmUpdate is called THEN it changes to read tab and calls proper Dao method`() {
        val newName = "aaa"
        val newDesc = "bbb"
        val newQuantity = 2
        sut.onSelectTab(readTab)
        sut.onEditRequest(testProducts[0])
        sut.confirmUpdate("aaa", "bbb", 2)
        val updatedProd = testProducts[0].also {
            it.name = newName
            it.description = newDesc
            it.quantity = newQuantity
        }
        assertEquals(readTab, sut.selectedTab.value)
        verify(exactly = 1) {
            daoMock.updateProduct(updatedProd)
            daoMock.fetchAll()
        }
    }
}