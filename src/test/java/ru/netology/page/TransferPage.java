package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class TransferPage {
    private SelenideElement amount = $("[data-test-id='amount'] .input__control");
    private SelenideElement debitAccount = $("[data-test-id='from'] .input__control");
    private SelenideElement upButton = $("[data-test-id='action-transfer']");
    private static SelenideElement errorMessage = $(".notification__content");


    public DashboardPage transferVerify(String transferAmount, String number) {
        amount.setValue(transferAmount);
        debitAccount.setValue(number);
        upButton.click();
        return new DashboardPage();
    }

    public static void shouldErrorMessage() {
        errorMessage.shouldBe(visible);
    }
}
