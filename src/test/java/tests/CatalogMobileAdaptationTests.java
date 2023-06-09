package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static constants.Constant.Url.ONLINER_START_PAGE;
import static enums.CatalogItem.*;
import static enums.SubCatalogItem.*;
import static enums.SubCatalogItem.NETWORK_EQUIPMENT;

public class CatalogMobileAdaptationTests extends BaseTestMobile{

    SoftAssert softly = new SoftAssert();

    @BeforeMethod
    public void openCatalogPage() {
        basePageMobile.open(ONLINER_START_PAGE);
        driverMobile.findElement(catalogHelperMobile.CATALOG_BUTTON).click();
    }

    @Test(priority = 3)
    public void shouldCheckCatalogItems() {
        //given
        List<String> items = new ArrayList<>();
        items.add(ELECTRONIC.getText());
        items.add(COMPUTERS.getText());
        items.add(HOUSEHOLD_APPLIANCE.getText());
        items.add(FOR_EVERY_DAY.getText());
        items.add(BUILDIND_AND_REPAIR.getText());
        items.add(HOME_AND_GARDEN.getText());
        items.add(AUTO_AND_MOTO.getText());
        items.add(BEAUTY_AND_SPORT.getText());
        items.add(CHILDREN_AND_MOTHERS.getText());
        //then
        softly.assertEquals(catalogHelperMobile.getCatalogItems(), items);
    }

    @Test(priority = 1)
    public void shouldCheckComputerCatalog() {
        //given
        int id= 2;
        List<String> items = new ArrayList<>();
        items.add(LAPTOPS_COMPUTERS_MONITORS.getText());
        items.add(ACCESSORIES.getText());
        items.add(DATA_STORAGE.getText());
        items.add(NETWORK_EQUIPMENT.getText());
        //when
        catalogHelperMobile.chooseCatalogItem(id);
        //then
        softly.assertTrue(driverMobile.findElement(catalogHelperMobile.COMPUTER_ITEMS).isDisplayed());
        softly.assertTrue(catalogHelperMobile.getItemsFromComputersBlock().containsAll(items));
        softly.assertAll();
    }

    @Test(priority = 2)
    public void shouldCheckComponentsSubdirectory() {
        //given
        int id= 2;
        //when
        catalogHelperMobile.chooseCatalogItem(id);
        catalogHelperMobile.chooseComputerSubdirectory(ACCESSORIES.getText());
        //then
        softly.assertTrue(driverMobile.findElements(catalogHelperMobile.COMPONENT_SUBDIRECTORY_ITEM_NAMES).stream().allMatch(WebElement :: isDisplayed));
        softly.assertTrue(driverMobile.findElements(catalogHelperMobile.COMPONENT_SUBDIRECTORY_ITEM_PRODUCTS).stream().allMatch(WebElement::isDisplayed));
        softly.assertTrue(driverMobile.findElements(catalogHelperMobile.COMPONENT_SUBDIRECTORY_ITEM_PRICE).stream().allMatch(WebElement::isDisplayed));
        softly.assertAll();
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        close();
    }
}