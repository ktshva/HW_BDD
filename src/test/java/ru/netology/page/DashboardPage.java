package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement card1 = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]");
    private SelenideElement card2 = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]");
    private SelenideElement card1button = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"] .button__content");
    private SelenideElement card2button = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"] .button__content");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCard1Balance() {
        val text = card1.text();
        return extractBalance(text);
    }

    public int getCard2Balance() {
        val text = card2.text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage buttonCard1() {
        card1button.click();
        return new TransferPage();
    }

    public TransferPage buttonCard2() {
        card2button.click();
        return new TransferPage();
    }
}