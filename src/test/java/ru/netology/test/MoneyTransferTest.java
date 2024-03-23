package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyFromCard1toCard2() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        var card1StartBalance = dashboardPage.getCard1Balance();
        var card2StartBalance = dashboardPage.getCard2Balance();
        var transferAmount = "300";
        dashboardPage.buttonCard1();
        var moneyTransferPage = new TransferPage();
        moneyTransferPage.transferVerify(transferAmount, DataHelper.getSecondCardInfo().getNumber());
        var card1EndBalance = dashboardPage.getCard1Balance();
        var card2EndBalance = dashboardPage.getCard2Balance();
        int transferAmountInt = Integer.parseInt(transferAmount);
        assertEquals(card1StartBalance + transferAmountInt, card1EndBalance);
        assertEquals(card2StartBalance - transferAmountInt, card2EndBalance);
    }

    @Test
    void shouldTransferMoneyFromCard2toCard1() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        var card1StartBalance = dashboardPage.getCard1Balance();
        var card2StartBalance = dashboardPage.getCard2Balance();
        var transferAmount = "300";
        dashboardPage.buttonCard2();
        var moneyTransferPage = new TransferPage();
        moneyTransferPage.transferVerify(transferAmount, DataHelper.getFirstCardInfo().getNumber());
        var card1EndBalance = dashboardPage.getCard1Balance();
        var card2EndBalance = dashboardPage.getCard2Balance();
        int transferAmountInt = Integer.parseInt(transferAmount);
        assertEquals(card2StartBalance + transferAmountInt, card2EndBalance);
        assertEquals(card1StartBalance - transferAmountInt, card1EndBalance);
    }

    @Test
    void shouldTransferMoneyOverLimit() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        var card1StartBalance = dashboardPage.getCard1Balance();
        var card2StartBalance = dashboardPage.getCard2Balance();
        dashboardPage.buttonCard2();
        var moneyTransferPage = new TransferPage();
        int transferAmount = card1StartBalance + 11000;
        moneyTransferPage.transferVerify(String.valueOf(transferAmount), DataHelper.getFirstCardInfo().getNumber());
        var card1EndBalance = dashboardPage.getCard1Balance();
        var card2EndBalance = dashboardPage.getCard2Balance();
        TransferPage.shouldErrorMessage();
        assertEquals(card1StartBalance, card1EndBalance);
        assertEquals(card2StartBalance, card2EndBalance);
    }

}