package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class GUIPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public GUIPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ── LOCATORS ──────────────────────────────────────────────────────

    // Text fields
    private By nameField    = By.id("name");
    private By emailField   = By.id("email");
    private By phoneField   = By.id("phone");
    private By addressField = By.id("textarea");

    // Radio buttons
    private By maleRadio   = By.id("male");
    private By femaleRadio = By.id("female");

    // Dropdowns
    private By countryDrop = By.id("country");
    private By colorsDrop  = By.id("colors");

// ── WEB TABLES LOCATORS ───────────────────────────────────────────
    
    // Static Table
    private By staticBookTable = By.xpath("//table[@name='BookTable']");
    
    // Dynamic Table
    private By dynamicTaskTable = By.id("taskTable");
    private By dynamicHeaders = By.xpath("//table[@id='taskTable']//th");
    private By chromeCPUText = By.className("chrome-cpu");
    
    // Pagination Table
    private By paginationTable = By.id("productTable");
    private By paginationLinks = By.xpath("//ul[@id='pagination']//a");
    
    // Date pickers
    private By datePicker1 = By.id("datepicker");
    private By datePicker2 = By.id("txtDate");
    
 // Date Picker 3 — Date Range
    private By startDate = By.id("start-date");
    private By endDate   = By.id("end-date");
    private By submitBtn = By.cssSelector(".submit-btn");

    // File uploads — confirmed from your inspection
    private By singleFile   = By.id("singleFileInput");
    private By multipleFile = By.id("multipleFilesInput");
    
 // ── WIKIPEDIA SEARCH (TABS SECTION) ───────────────────────────────
    private By wikiSearchInput  = By.xpath("//input[@id='Wikipedia1_wikipedia-search-input']");
    private By wikiSearchBtn    = By.xpath("//input[@class='wikipedia-search-button']");
    // Added '//a' to click the actual link inside your results container
    private By wikiSearchResult = By.xpath("//div[@id='Wikipedia1_wikipedia-search-results']//a"); 

    // ── DYNAMIC BUTTON ────────────────────────────────────────────────
    private By startDynamicBtn  = By.xpath("//button[normalize-space()='START']");

    // Slider
   
    private By sliderHandle = By.cssSelector("#slider-range .ui-slider-handle");

    // Alert buttons
    private By simpleAlertBtn  = By.xpath("//button[text()='Simple Alert']");
    private By confirmAlertBtn = By.xpath("//button[text()='Confirmation Alert']");
    private By promptAlertBtn  = By.xpath("//button[text()='Prompt Alert']");

 // Windows & Tabs
    private By newTabBtn      = By.xpath("//button[normalize-space()='New Tab']");
    private By popupWindowBtn = By.xpath("//button[@id='PopUp']");
    // Mouse hover
    private By hoverButton = By.xpath("//button[text()='Point Me']");
    private By mobilesLink = By.xpath("//a[text()='Mobiles']");

    // Double click
    private By field1      = By.id("field1");
    private By field2      = By.id("field2");
    private By copyButton  = By.xpath("//button[text()='Copy Text']");

    // Drag and Drop
    private By draggable = By.id("draggable");
    private By droppable = By.id("droppable");
    
 // ── LABELS AND LINKS ──────────────────────────────────────────────
    // Grabs the entire container for mobile labels
    private By mobilesContainer = By.id("mobiles");
    
    // Uses the exact ID provided in the HTML
    private By appleLink = By.id("apple");
    
    // CSS Selector that finds every <a> tag specifically inside the broken-links div
    private By allBrokenLinks = By.cssSelector("#broken-links a.link");
    
 // ── LOCATORS: SHADOW DOM ──────────────────────────────────────────
    // Exactly matches the shadow host ID in your HTML
    private By shadowHostElement = By.cssSelector("#shadow_host");

    // ── TEXT FIELD METHODS ────────────────────────────────────────────

    public void enterName(String name) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        el.clear();
        el.sendKeys(name);
    }

    public void enterEmail(String email) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        el.clear();
        el.sendKeys(email);
    }

    public void enterPhone(String phone) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField));
        el.clear();
        el.sendKeys(phone);
    }

    public void enterAddress(String address) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(addressField));
        el.clear();
        el.sendKeys(address);
    }

    // ── RADIO BUTTON METHODS ──────────────────────────────────────────

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male"))
            driver.findElement(maleRadio).click();
        else
            driver.findElement(femaleRadio).click();
    }

    public boolean isGenderSelected(String gender) {
        if (gender.equalsIgnoreCase("Male"))
            return driver.findElement(maleRadio).isSelected();
        return driver.findElement(femaleRadio).isSelected();
    }

    // ── CHECKBOX METHODS ──────────────────────────────────────────────

    public void selectAllCheckboxes() {

        List<WebElement> checkboxes =
            driver.findElements(By.xpath("//input[@type='checkbox']"));

        for (WebElement chk : checkboxes) {
            if (!chk.isSelected()) {
                chk.click();
            }
        }
    }

    public boolean isCheckboxSelected(String day) {

        String id = day.toLowerCase();
        return driver.findElement(By.id(id)).isSelected();
    }

    // ── DROPDOWN METHODS ──────────────────────────────────────────────

    public void selectCountry(String country) {
        new Select(driver.findElement(countryDrop))
            .selectByVisibleText(country);
    }

    public void selectColor(String color) {
        new Select(driver.findElement(colorsDrop))
            .selectByVisibleText(color);
    }

    // Sorted List — 3rd select on the page
    public void selectFromSortedList(String value) {
        List<WebElement> selects = driver.findElements(By.tagName("select"));
        new Select(selects.get(2)).selectByVisibleText(value);
    }

    // Scrolling Dropdown (1–100) — 4th select on the page
 // ── 7. SCROLLING DROPDOWN METHOD ──────────────────────────────────────
    public void selectFromScrollingDropdown(String value) {
        
        // 1. Find the input box and click it to open the menu
        WebElement dropdownInput = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("comboBox"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownInput);
        dropdownInput.click();

        // 2. Build the correct XPath based on your HTML structure
        By optionLocator = By.xpath("//div[@class='option' and text()='Item " + value + "']");

        // 3. Wait for the element to merely exist in the DOM 
        WebElement option = wait.until(
            ExpectedConditions.presenceOfElementLocated(optionLocator)
        );

        // 4. Scroll down inside the dropdown list until the item is physically visible
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", option);

        // 5. Now that it is visible, wait for it to be clickable and click it
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }
    // ── DATE PICKER METHODS ───────────────────────────────────────────

    // Date Picker 1 — mm/dd/yyyy
    public void setDate1(String date) {
        WebElement dp = driver.findElement(datePicker1);
        dp.clear();
        dp.sendKeys(date);
        dp.sendKeys(Keys.TAB);
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    // Date Picker 2 — dd/mm/yyyy
 // ── DATE PICKER 2 METHOD ───────────────────────────────────────────
    public void setDate2(String date) {
        
        // 1. Wait for the element to exist in the DOM
        WebElement dp2 = wait.until(ExpectedConditions.presenceOfElementLocated(datePicker2));

        // 2. Scroll it into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dp2);

        // 3. THE FIX: Use JavaScript to bypass the "Read Only" / "Invalid State" block 
        // This injects the date string directly into the value attribute of the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1];", dp2, date);
        
        // 4. (Optional but recommended) Fire a 'change' event so the webpage recognizes the new data
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('change'));", dp2);
    }
    // Date Picker 3 — Date Range (from → to → Submit)
 // ── DATE RANGE PICKER METHOD ──────────────────────────────────────
    public void setDateRange(String from, String to) {
        
        WebElement startInput = wait.until(ExpectedConditions.elementToBeClickable(startDate));
        WebElement endInput = driver.findElement(endDate);
        WebElement btn = driver.findElement(submitBtn);

        // 1. Scroll the container into the center of the view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", startInput);

        // 2. Fill Start Date
        // Note: For native type="date", we don't always need .clear() if the field is empty, 
        // but if you do, keep it!
        startInput.sendKeys(from);

        // 3. Fill End Date
        endInput.sendKeys(to);

        // 4. THE FIX: Press TAB to move focus away from the input field.
        // This forces the Chrome native calendar popup to close immediately.
        endInput.sendKeys(Keys.TAB);
        
        // Brief pause to ensure the browser UI closes before clicking
        try { Thread.sleep(500); } catch (InterruptedException ex) {} 

        // 5. Force the click with JavaScript as a final safety measure
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }
    
    
    
 // ── 1. STATIC TABLE METHOD ────────────────────────────────────────
    public String getBookPriceFromStaticTable(String bookName) {
        // We use a dynamic XPath that finds the row with the specific book name, 
        // and then grabs the 4th column (Price) of that exact row.
        By priceLocator = By.xpath("//table[@name='BookTable']//tr[td[1][text()='" + bookName + "']]/td[4]");
        
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(priceLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", priceElement);
        
        return priceElement.getText();
    }

    // ── 2. DYNAMIC TABLE METHOD ───────────────────────────────────────
    public boolean verifyDynamicChromeCPU() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicTaskTable));
        
        // 1. Find which column index holds the "CPU (%)" header (since it shuffles)
        List<WebElement> headers = driver.findElements(dynamicHeaders);
        int cpuColumnIndex = -1;
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().equals("CPU (%)")) {
                cpuColumnIndex = i; // Store the index
                break;
            }
        }
        
        // 2. Find the row for 'Chrome' and get the specific cell using the index we just found
        // Note: XPath is 1-based, while Java Lists are 0-based, so we add 1 to the index for XPath
        int xpathIndex = cpuColumnIndex + 1;
        By chromeCpuCellLocator = By.xpath("//table[@id='taskTable']//tr[td[1][text()='Chrome']]/td[" + xpathIndex + "]");
        
        String tableCpuValue = driver.findElement(chromeCpuCellLocator).getText();
        
        // 3. Grab the displayed value below the table
        String displayedCpuValue = driver.findElement(chromeCPUText).getText();
        
        System.out.println("Table CPU: " + tableCpuValue + " | Displayed CPU: " + displayedCpuValue);
        
        // Return true if they match!
        return tableCpuValue.equals(displayedCpuValue);
    }

    // ── 3. PAGINATION TABLE METHOD ────────────────────────────────────
    public boolean findAndSelectProductInPagination(String targetProduct) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(paginationTable));
        
        List<WebElement> pages = driver.findElements(paginationLinks);
        
        // Loop through every page available
        for (int i = 1; i <= pages.size(); i++) {
            // Click the current page number
            driver.findElement(By.xpath("//ul[@id='pagination']//a[text()='" + i + "']")).click();
            
            // Check if our target product exists on this page
            List<WebElement> productRow = driver.findElements(By.xpath("//table[@id='productTable']//td[text()='" + targetProduct + "']"));
            
            // If the list size is greater than 0, it means we found the product!
            if (productRow.size() > 0) {
                // Find the checkbox in the same row and click it
                WebElement checkbox = driver.findElement(By.xpath("//table[@id='productTable']//tr[td[2][text()='" + targetProduct + "']]//input[@type='checkbox']"));
                checkbox.click();
                return true; // Successfully found and clicked
            }
        }
        
        return false; // Product was never found across any pages
    }
    // ── FILE UPLOAD METHODS ───────────────────────────────────────────

    public void uploadSingleFile(String filePath) {
        driver.findElement(singleFile).sendKeys(filePath);
    }

    public void uploadMultipleFiles(String filePath1, String filePath2) {
        driver.findElement(multipleFile).sendKeys(filePath1 + "\n" + filePath2);
    }
    
 // ── WIKIPEDIA SEARCH METHOD ───────────────────────────────────────
    public boolean searchWikipediaAndSwitch(String query) {
        
        // 1. Type the query and click the search button
        wait.until(ExpectedConditions.visibilityOfElementLocated(wikiSearchInput)).sendKeys(query);
        driver.findElement(wikiSearchBtn).click();

        // 2. Wait for the results to load and click the first link
        WebElement resultLink = wait.until(ExpectedConditions.elementToBeClickable(wikiSearchResult));
        resultLink.click();

        // 3. Switch to the newly opened Wikipedia tab
        String originalWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // 4. Verify we are on the new page, then close it and return
        boolean isSuccess = driver.getCurrentUrl().contains("wikipedia.org");
        driver.close();
        
        // 5. Switch focus back to the main test page
        driver.switchTo().window(originalWindow);

        return isSuccess;
    }

    // ── DYNAMIC BUTTON METHOD ─────────────────────────────────────────
    public void clickDynamicButton() {
        
        // Wait for the START button to be completely ready in the DOM
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(startDynamicBtn));
        
        // Scroll to it
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
        
        // Click it
        btn.click();
    }

    // ── SLIDER METHOD ─────────────────────────────────────────────────

    public void moveSlider(int offsetX) {
        // Find the first interactive handle belonging to the slider container
        WebElement handle = wait.until(
            ExpectedConditions.visibilityOfElementLocated(sliderHandle)
        );

        // Perform the drag and drop action by pixel offset
        new Actions(driver)
            .dragAndDropBy(handle, offsetX, 0)
            .perform();
    }
    // ── ALERT METHODS ─────────────────────────────────────────────────

    public void handleSimpleAlert() {
        driver.findElement(simpleAlertBtn).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void handleConfirmAlert() {
        driver.findElement(confirmAlertBtn).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void handlePromptAlert(String text) {
        driver.findElement(promptAlertBtn).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }
    
 // ── WINDOW & TAB HANDLING METHODS ─────────────────────────────────

    public String handleNewTab() {
        // 1. Store the original window ID so we can come back later
        String originalWindow = driver.getWindowHandle();

        // 2. Click the button that opens the new tab
        wait.until(ExpectedConditions.elementToBeClickable(newTabBtn)).click();

        // 3. Wait for the second window/tab to actually exist
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // 4. Loop through all open windows and switch to the new one
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // 5. Grab the title of the new tab to prove we switched successfully
        String newTabTitle = driver.getTitle();

        // 6. Close the new tab
        driver.close();

        // 7. Crucial Step: Switch Selenium's focus back to the original page!
        driver.switchTo().window(originalWindow);

        return newTabTitle;
    }

 // ── POPUP WINDOW METHOD ───────────────────────────────────────────
    public String handlePopupWindow() {
        
        // 1. Get the main window ID and the current total count of windows
        String originalWindow = driver.getWindowHandle();
        int initialWindowCount = driver.getWindowHandles().size();
        
        // 2. Use a JavaScript Click to prevent accidental UI double-clicking
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(popupWindowBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        // 3. THE FIX: Wait dynamically for the window count to be GREATER than what we started with
        wait.until(d -> d.getWindowHandles().size() > initialWindowCount);

        String popupUrl = "";

        // 4. THE FIX: Loop through ALL windows, closing any that aren't the main window
        for (String windowHandle : driver.getWindowHandles()) {
            
            // If this window is NOT the main window...
            if (!windowHandle.equals(originalWindow)) {
                
                // Switch to it
                driver.switchTo().window(windowHandle);
                
                // Grab the URL (if multiple popups opened, it will save the last one)
                popupUrl = driver.getCurrentUrl();
                
                // Close this specific popup window
                driver.close(); 
            }
        }

        // 5. Safely switch back to the main window
        driver.switchTo().window(originalWindow);

        return popupUrl;
    }

    // ── MOUSE HOVER METHOD ────────────────────────────────────────────

    public boolean performMouseHover() {
        WebElement btn = wait.until(
            ExpectedConditions.visibilityOfElementLocated(hoverButton));
        new Actions(driver).moveToElement(btn).perform();
        try {
            WebElement menu = wait.until(
                ExpectedConditions.visibilityOfElementLocated(mobilesLink));
            return menu.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ── DOUBLE CLICK METHOD ───────────────────────────────────────────

    public void performDoubleClick(String textToCopy) {
        WebElement f1 = wait.until(
            ExpectedConditions.visibilityOfElementLocated(field1));
        f1.clear();
        f1.sendKeys(textToCopy);
        WebElement btn = driver.findElement(copyButton);
        new Actions(driver).doubleClick(btn).perform();
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public String getCopiedFieldValue() {
        return driver.findElement(field2).getAttribute("value");
    }

    // ── DRAG AND DROP METHOD ──────────────────────────────────────────

    public void performDragAndDrop() {
        WebElement src = wait.until(
            ExpectedConditions.visibilityOfElementLocated(draggable));
        WebElement tgt = driver.findElement(droppable);
        new Actions(driver).dragAndDrop(src, tgt).perform();
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public String getDroppableText() {
        return driver.findElement(droppable).getText();
    }
    
 // ── 1. READ LABELS ────────────────────────────────────────────────
    public String getMobileLabelsText() {
        // Grabs the text of the entire "mobiles" div so the test can verify all of them at once
        return wait.until(ExpectedConditions.visibilityOfElementLocated(mobilesContainer)).getText();
    }

 // ── 2. CLICK VALID LAPTOP LINK (DYNAMIC) ──────────────────────────
    public String clickLaptopLinkAndReturn(String brandId) {
        
        // 1. Build the locator dynamically using the word we pass in (e.g., "apple", "lenovo")
        By dynamicLink = By.id(brandId);
        
        // 2. Click it
        wait.until(ExpectedConditions.elementToBeClickable(dynamicLink)).click();
        
        // 3. Wait for the new page to load
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
        
        // 4. Grab the URL and go back
        String newUrl = driver.getCurrentUrl();
        driver.navigate().back(); 
        
        return newUrl;
    }

    // ── 3. VERIFY BROKEN LINKS (THE API WAY) ──────────────────────────
    public int verifyBrokenLinks() {
        int brokenCount = 0;
        
        // Gets all 8 error links at once
        List<WebElement> links = driver.findElements(allBrokenLinks);
        
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            
            if (url == null || url.isEmpty()) {
                continue;
            }
            
            try {
                // Ping the server to check the status in the background
                java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) (new java.net.URL(url).openConnection());
                httpConn.setRequestMethod("HEAD");
                httpConn.connect();
                
                int respCode = httpConn.getResponseCode();
                
                // HTTP codes 400 and above mean the link is broken
                if (respCode >= 400) {
                    System.out.println("Verified Broken Link: " + url + " | Status Code: " + respCode);
                    brokenCount++;
                }
            } catch (Exception e) {
                System.out.println("Failed to connect to: " + url);
            }
        }
        return brokenCount;
    }
    
 // ── 1. DYNAMIC FORM SECTION (Optimized with exact HTML IDs) ───────
    public void fillAndSubmitFormSection(int sectionNumber, String textToEnter) {
        // Because your HTML uses id="input1", id="btn1", we can dynamically build the ID!
        By exactInputId = By.id("input" + sectionNumber);
        By exactButtonId = By.id("btn" + sectionNumber);
        
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(exactInputId));
        
        // Scroll to it
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);
        
        // Type and Click
        input.sendKeys(textToEnter);
        driver.findElement(exactButtonId).click();
    }

    // ── 2. FOOTER LINKS ───────────────────────────────────────────────
    public String clickFooterLinkAndReturn(String linkTextName) {
        // Looks specifically inside the Footer container for the link text
        By footerLink = By.xpath("//div[@id='PageList1']//a[contains(text(), '" + linkTextName + "')]");
        
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(footerLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        link.click();
        
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
        String url = driver.getCurrentUrl();
        driver.navigate().back();
        
        return url;
    }

    // ── 3. SHADOW DOM ─────────────────────────────────────────────────
    public void interactWithShadowDOM(String textToEnter) {
        
        // 1. Find the exact host from your HTML: <div id="shadow_host">
        WebElement shadowHost = wait.until(ExpectedConditions.presenceOfElementLocated(shadowHostElement));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        
        // 2. Pierce the root and find the first input (Text)
        WebElement shadowInput = shadowRoot.findElement(By.cssSelector("input[type='text']"));
        shadowInput.clear();
        shadowInput.sendKeys(textToEnter);
        
        // 3. Find the second input inside the shadow root (Checkbox)
        WebElement shadowCheckbox = shadowRoot.findElement(By.cssSelector("input[type='checkbox']"));
        if (!shadowCheckbox.isSelected()) {
            shadowCheckbox.click();
        }
    }
}

