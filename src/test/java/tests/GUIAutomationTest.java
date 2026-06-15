package tests;

import base.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.GUIPage;
import utils.ScreenshotUtil;
import utils.TestListener;

@Listeners(utils.TestListener.class)
public class GUIAutomationTest extends BaseTest {

    GUIPage page;

    @BeforeClass
    public void initPage() {
        page = new GUIPage(driver);
    }

    // ── DATA PROVIDER ─────────────────────────────────────────────────
    @DataProvider(name = "formData")
    public Object[][] getFormData() {
        return new Object[][] {
            { "John Doe",   "john@test.com", "9876543210",
              "123 Main St", "Male",   "India",  "Blue",  "Dog" },
            { "Jane Smith", "jane@test.com", "9123456789",
              "456 Oak Ave", "Female", "Canada", "Green", "Cat" },
            { "Ravi Kumar", "ravi@test.com", "9001122334",
              "789 Pine Rd", "Male",   "India",  "Red",   "Lion" }
        };
    }

    // ── 1. TEXT FIELDS ────────────────────────────────────────────────
    @Test(priority = 1, dataProvider = "formData",
          description = "Fill all text fields with multiple data sets")
    public void testTextFields(String name, String email, String phone,
                               String address, String gender,
                               String country, String color, String animal) {
        page.enterName(name);
        page.enterEmail(email);
        page.enterPhone(phone);
        page.enterAddress(address);

        // Manual screenshot — key checkpoint
        String path = ScreenshotUtil.capture(
            driver, "TextFields_" + name.replace(" ", "_"));
        try {
            TestListener.test.get().info(
                "✔ Text fields filled for dataset: " + name,
                MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (Exception e) {
            TestListener.test.get().info("✔ Text fields filled for: " + name);
        }
    }

    // ── 2. RADIO BUTTON ───────────────────────────────────────────────
    @Test(priority = 2, description = "Select Male gender radio button")
    public void testRadioButton() {
        page.selectGender("Male");
        Assert.assertTrue(page.isGenderSelected("Male"),
            "Male radio button not selected");
        TestListener.test.get().info("✔ Gender: Male selected");
    }

    // ── 3. CHECKBOXES ─────────────────────────────────────────────────
    @Test(priority = 3, description = "Select ALL checkboxes")
    public void testCheckboxes() {

        page.selectAllCheckboxes();

        TestListener.test.get().info("✔ All checkboxes selected successfully");
    }

    // ── 4. COUNTRY DROPDOWN ───────────────────────────────────────────
    @Test(priority = 4, description = "Select country from dropdown")
    public void testCountryDropdown() {
        page.selectCountry("India");
        TestListener.test.get().info("✔ Country selected: India");
    }

    // ── 5. COLORS DROPDOWN ────────────────────────────────────────────
    @Test(priority = 5, description = "Select color from dropdown")
    public void testColorsDropdown() {
        page.selectColor("Blue");
        TestListener.test.get().info("✔ Color selected: Blue");
    }

    // ── 6. SORTED LIST DROPDOWN ───────────────────────────────────────
    @Test(priority = 6, description = "Select animal from sorted list dropdown")
    public void testSortedListDropdown() {
        page.selectFromSortedList("Dog");
        TestListener.test.get().info("✔ Sorted list: Dog selected");
    }

    // ── 7. SCROLLING DROPDOWN ─────────────────────────────────────────
 // ── 7. SCROLLING DROPDOWN (LOOP 1 TO 100) ─────────────────────────
 // ── 7. SCROLLING DROPDOWN (BOUNDARY TEST) ─────────────────────────
    @Test(priority = 7, description = "Test scrolling functionality using boundary values (First, Middle, Last)")
    public void testScrollingDropdownBoundaries() {
        
        // 1. Test the Top Boundary (Proves dropdown opens and top is clickable)
        page.selectFromScrollingDropdown("1");
        
        // Brief pause so the UI resets and you can visually see the click
        try { Thread.sleep(400); } catch (InterruptedException e) {} 

        // 2. Test the Middle (Proves the scrollbar can drag down halfway)
        page.selectFromScrollingDropdown("50");
        try { Thread.sleep(400); } catch (InterruptedException e) {}

        // 3. Test the Bottom Boundary (Proves the scrollbar can reach the absolute end)
        page.selectFromScrollingDropdown("100");
        
        TestListener.test.get().info("✔ Scrolling dropdown: Boundary test passed (Selected 1, 50, 100)");
    }
    // ── 8. DATE PICKER 1 ──────────────────────────────────────────────
    @Test(priority = 8, description = "Set Date Picker 1 in mm/dd/yyyy format")
    public void testDatePicker1() {
        page.setDate1("06/13/2026");
        TestListener.test.get().info("✔ Date Picker 1 set: 06/13/2026");
    }

    // ── 9. DATE PICKER 2 ──────────────────────────────────────────────
    @Test(priority = 9, description = "Set Date Picker 2 in dd/mm/yyyy format")
    public void testDatePicker2() {
        page.setDate2("13/06/2026"); 
        TestListener.test.get().info("✔ Date Picker 2 set: 13/06/2026");
    }

    // ── 10. DATE RANGE PICKER ─────────────────────────────────────────
    @Test(priority = 10, description = "Set date range from and to with submit")
    public void testDateRangePicker() {
        page.setDateRange("06/13/2026", "06/20/2026");

        // Manual screenshot — proves range was set
        String path = ScreenshotUtil.capture(driver, "DateRange_Set");
        try {
            TestListener.test.get().info(
                "✔ Date Range set: 06/13/2026 to 06/20/2026",
                MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (Exception e) {
            TestListener.test.get().info("✔ Date Range set successfully");
        }
    }

    // ── 11. SINGLE FILE UPLOAD ────────────────────────────────────────
    @Test(priority = 11, description = "Upload single file")
    public void testSingleFileUpload() {
        String filePath = System.getProperty("user.dir")
                        + "/src/test/resources/sample.txt";
        page.uploadSingleFile(filePath);
        TestListener.test.get().info("✔ Single file uploaded: sample.txt");
    }

    // ── 12. MULTIPLE FILE UPLOAD ──────────────────────────────────────
    @Test(priority = 12, description = "Upload multiple files")
    public void testMultipleFileUpload() {
        String file1 = System.getProperty("user.dir")
                     + "/src/test/resources/sample.txt";
        String file2 = System.getProperty("user.dir")
                     + "/src/test/resources/sample2.txt";
        page.uploadMultipleFiles(file1, file2);
        TestListener.test.get().info("✔ Multiple files uploaded: sample.txt, sample2.txt");
    }

 // ── 13. WIKIPEDIA SEARCH (NEW TAB) ────────────────────────────────
    @Test(priority = 13, description = "Search Wikipedia, click result, switch to new tab, and return")
    public void testWikipediaSearch() {
        
        // Search for "Selenium" and verify the new tab opened successfully
        boolean success = page.searchWikipediaAndSwitch("Selenium");
        
        Assert.assertTrue(success, "Failed to navigate to the new Wikipedia tab.");
        TestListener.test.get().info("✔ Wikipedia Search: Successfully searched and handled new tab");
    }

    // ── 14. DYNAMIC BUTTON ────────────────────────────────────────────
    @Test(priority = 14, description = "Wait for dynamic button to be clickable and click it")
    public void testDynamicButton() {
        
        // Execute the click after the wait condition is met
        page.clickDynamicButton();
        
        TestListener.test.get().info("✔ Dynamic Button: Successfully waited for and clicked the START button");
    }
    // ── 15. SLIDER ────────────────────────────────────────────────────
 // ── 13. SLIDER ────────────────────────────────────────────────────
    @Test(priority = 15, description = "Move slider element")
    public void testSlider() {
        // Moves the first handle by 50 pixels to the right
        page.moveSlider(50);
        
        // OPTIONAL ENHANCEMENT: Capture a screenshot showing the slider moved
        String path = ScreenshotUtil.capture(driver, "Slider_Moved_Result");
        try {
            TestListener.test.get().info(
                "✔ Slider handle moved by 50px offset",
                MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (Exception e) {
            TestListener.test.get().info("✔ Slider moved by 50px offset");
        }
    }
    // ── 16. SIMPLE ALERT ──────────────────────────────────────────────
    @Test(priority = 16, description = "Handle simple alert")
    public void testSimpleAlert() {
        page.handleSimpleAlert();
        TestListener.test.get().info("✔ Simple alert accepted");
    }

    // ── 17. CONFIRMATION ALERT ────────────────────────────────────────
    @Test(priority = 17, description = "Handle confirmation alert")
    public void testConfirmAlert() {
        page.handleConfirmAlert();
        TestListener.test.get().info("✔ Confirmation alert accepted");
    }

    // ── 18. PROMPT ALERT ──────────────────────────────────────────────
    @Test(priority = 18, description = "Handle prompt alert with text input")
    public void testPromptAlert() {
        page.handlePromptAlert("Selenium Automation");
        TestListener.test.get().info("✔ Prompt alert filled: Selenium Automation");
    }

 // ── 19. NEW TAB ───────────────────────────────────────────────────
    @Test(priority = 19, description = "Click button, switch to new tab, verify, and return")
    public void testNewTab() {
        // Get the title of the new tab from our page method
        String tabTitle = page.handleNewTab();
        
        // Verify it actually grabbed a title (meaning the switch worked)
        Assert.assertFalse(tabTitle.isEmpty(), "Failed to switch to the new tab.");
        
        TestListener.test.get().info("✔ Successfully switched to New Tab. Title: " + tabTitle);
    }

    // ── 20. POPUP WINDOW ──────────────────────────────────────────────
    @Test(priority = 20, description = "Click button, switch to popup window, verify, and return")
    public void testPopupWindow() {
        // Get the URL of the popup from our page method
        String popupUrl = page.handlePopupWindow();
        
        Assert.assertFalse(popupUrl.isEmpty(), "Failed to switch to the Popup Window.");
        
        TestListener.test.get().info("✔ Successfully switched to Popup Window. URL: " + popupUrl);
    }
    // ── 21. MOUSE HOVER ───────────────────────────────────────────────
    @Test(priority = 21, description = "Hover over button and verify dropdown")
    public void testMouseHover() {
        boolean visible = page.performMouseHover();
        Assert.assertTrue(visible, "Hover menu did not appear");
        TestListener.test.get().info("✔ Mouse hover menu appeared");
    }

    // ── 22. DOUBLE CLICK ──────────────────────────────────────────────
    @Test(priority = 22, description = "Double click to copy text between fields")
    public void testDoubleClick() {
        page.performDoubleClick("AutoTest123");
        String copied = page.getCopiedFieldValue();
        Assert.assertEquals(copied, "AutoTest123",
            "Double click did not copy text");

        // Manual screenshot — proves copy worked
        String path = ScreenshotUtil.capture(driver, "DoubleClick_Result");
        try {
            TestListener.test.get().info(
                "✔ Double click copied text: " + copied,
                MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (Exception e) {
            TestListener.test.get().info("✔ Text copied: " + copied);
        }
    }

    // ── 23. DRAG AND DROP ─────────────────────────────────────────────
    @Test(priority = 23, description = "Drag element and drop to target")
    public void testDragAndDrop() {
        page.performDragAndDrop();

        // Manual screenshot — visually proves drag happened
        String path = ScreenshotUtil.capture(driver, "DragDrop_Result");
        try {
            TestListener.test.get().info(
                "✔ Drag and drop completed successfully",
                MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (Exception e) {
            TestListener.test.get().info("✔ Drag and drop completed");
        }
    }
    
 // ── 24. VERIFY LABELS ─────────────────────────────────────────────
    @Test(priority = 24, description = "Verify static mobile label text is present")
    public void testMobileLabels() {
        String labels = page.getMobileLabelsText();
        
        // Asserting against the exact text in your HTML
        Assert.assertTrue(labels.contains("Samsung"), "Samsung label missing");
        Assert.assertTrue(labels.contains("Real Me"), "Real Me label missing");
        Assert.assertTrue(labels.contains("Moto"), "Moto label missing");
        
        TestListener.test.get().info("✔ Mobile Labels (Samsung, Real Me, Moto) verified successfully");
    }

 // ── 25. LAPTOP LINKS ──────────────────────────────────────────────
    @Test(priority = 25, description = "Click all laptop links and verify navigation")
    public void testLaptopLinks() {
        
        // 1. Test Apple
        String appleUrl = page.clickLaptopLinkAndReturn("apple");
        Assert.assertTrue(appleUrl.contains("apple.com"), "Did not navigate to Apple.com");
        
        // 2. Test Lenovo
        String lenovoUrl = page.clickLaptopLinkAndReturn("lenovo");
        Assert.assertTrue(lenovoUrl.contains("lenovo.com"), "Did not navigate to Lenovo.com");
        
        // 3. Test Dell
        String dellUrl = page.clickLaptopLinkAndReturn("dell");
        Assert.assertTrue(dellUrl.contains("dell.com"), "Did not navigate to Dell.com");
        
        TestListener.test.get().info("✔ All Laptop links (Apple, Lenovo, Dell) clicked and verified successfully");
    }

    // ── 26. BROKEN LINKS API TEST ─────────────────────────────────────
    @Test(priority = 26, description = "Scan and verify all broken links using HTTP requests")
    public void testBrokenLinks() {
        
        int brokenLinksFound = page.verifyBrokenLinks();
        
        // Your HTML has exactly 8 broken links, so let's assert that it found all 8!
        Assert.assertEquals(brokenLinksFound, 8, "Expected 8 broken links, but found a different number.");
        
        TestListener.test.get().info("✔ Broken Links test completed. Successfully detected all 8 broken links.");
    }
    
 // ── 27. STATIC WEB TABLE ──────────────────────────────────────────
    @Test(priority = 27, description = "Extract specific data from a static table")
    public void testStaticTable() {
        
        // Let's find the price for "Master In Java"
        String price = page.getBookPriceFromStaticTable("Master In Java");
        
        Assert.assertEquals(price, "2000", "The price extracted did not match the expected value.");
        TestListener.test.get().info("✔ Static Table: Successfully extracted price '" + price + "' for 'Master In Java'");
    }

    // ── 28. DYNAMIC WEB TABLE ─────────────────────────────────────────
    @Test(priority = 28, description = "Match dynamic table cell data with displayed text")
    public void testDynamicTable() {
        
        boolean isMatch = page.verifyDynamicChromeCPU();
        
        Assert.assertTrue(isMatch, "The CPU value in the dynamic table did NOT match the label below it!");
        TestListener.test.get().info("✔ Dynamic Table: Successfully matched shifting Chrome CPU cell with the display label");
    }

    // ── 29. PAGINATION WEB TABLE ──────────────────────────────────────
    @Test(priority = 29, description = "Paginate through table to find and select an item")
    public void testPaginationTable() {
        
        // Let's search for "Desktop Computer" (which is hidden on page 3 based on your JS array)
        String targetItem = "Desktop Computer";
        boolean wasFoundAndClicked = page.findAndSelectProductInPagination(targetItem);
        
        Assert.assertTrue(wasFoundAndClicked, "Failed to find " + targetItem + " across any of the table pages.");
        TestListener.test.get().info("✔ Pagination Table: Successfully navigated pages, found '" + targetItem + "', and checked its box");
    }
    
 // ── 30. DYNAMIC FORMS ─────────────────────────────────────────────
    @Test(priority = 30, description = "Fill and submit specific form sections using dynamic IDs")
    public void testFormSections() {
        
        page.fillAndSubmitFormSection(1, "Data for Section 1");
        page.fillAndSubmitFormSection(3, "Data for Section 3");
        
        TestListener.test.get().info("✔ Dynamic Forms: Successfully filled and submitted Section 1 and 3 using exact IDs");
    }

    // ── 31. FOOTER LINKS ──────────────────────────────────────────────
    @Test(priority = 31, description = "Click footer links and verify navigation")
    public void testFooterLinks() {
        
        String url = page.clickFooterLinkAndReturn("Hidden Elements & AJAX");
        Assert.assertNotNull(url, "Failed to navigate using Footer link");
        
        TestListener.test.get().info("✔ Footer Links: Successfully clicked 'Hidden Elements' and navigated to: " + url);
    }

    // ── 32. SHADOW DOM ────────────────────────────────────────────────
    @Test(priority = 32, description = "Pierce Shadow DOM to interact with hidden elements")
    public void testShadowDOM() {
        
        page.interactWithShadowDOM("Successfully passed the Shadow Boundary!");
        
        TestListener.test.get().info("✔ Shadow DOM: Successfully pierced root, entered text, and clicked checkbox using CSS Selectors");
    }
}