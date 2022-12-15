package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.CardChoosePage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.page.CardChoosePage.getTansferAmount;
import static ru.netology.web.page.CardChoosePage.getTansferAmountWhenDouble;

class MoneyTransferTest {


    @Test
    void shouldTransferMoneyToFirstCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        val transferPage = new TransferPage();
        String secondCardNumber = CardChoosePage.getSecondCardNumber();

        String amount = getTansferAmount(secondCardNumber);
        val cardInfo = DataHelper.secondCardInfo();
        CardChoosePage.chooseFirstCardForTransfer();
        transferPage.putTransferAmount(amount);
        transferPage.putMoneyCard(cardInfo);
    }

    @Test
    void shouldTransferMoneyToSecondCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        val transferPage = new TransferPage();
        String firstCardNumber = CardChoosePage.getFirstCardNumber();

        String amount = getTansferAmount(firstCardNumber);
        val cardInfo = DataHelper.firstCardInfo();
        CardChoosePage.chooseSecondCardForTransfer();
        transferPage.putTransferAmount(amount);
        transferPage.putMoneyCard(cardInfo);
    }

    @Test
    void checkFirstCardBalance() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        val transferPage = new TransferPage();
        String secondCardNumber = CardChoosePage.getSecondCardNumber();
        String amount = getTansferAmount(secondCardNumber);
        val cardInfo = DataHelper.secondCardInfo();

        String firstCardNumberBeforeTransfer = CardChoosePage.getFirstCardNumber();
        int expected = (CardChoosePage.getCardBalance(firstCardNumberBeforeTransfer)
                + Integer.parseInt(amount));

        CardChoosePage.chooseFirstCardForTransfer();
        transferPage.putTransferAmount(amount);
        transferPage.putMoneyCard(cardInfo);

        String firstCardNumberAfterTransfer = CardChoosePage.getFirstCardNumber();
        int actual = CardChoosePage.getCardBalance(firstCardNumberAfterTransfer);
        assertEquals(expected, actual);
    }

    @Test
    void checkSecondCardBalance() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        val transferPage = new TransferPage();
        val cardNumber = new CardChoosePage();
        String firstCardNumber = CardChoosePage.getFirstCardNumber();

        String amount = getTansferAmount(firstCardNumber);
        val cardInfo = DataHelper.firstCardInfo();
        String secondCardNumberBeforeTransfer = CardChoosePage.getSecondCardNumber();

        int expected = (CardChoosePage.getCardBalance(secondCardNumberBeforeTransfer)
                + Integer.parseInt(amount));
        CardChoosePage.chooseSecondCardForTransfer();

        transferPage.putTransferAmount(amount);
        transferPage.putMoneyCard(cardInfo);
        String secondCardNumberAfterTransfer = CardChoosePage.getSecondCardNumber();
        int actual = CardChoosePage.getCardBalance(secondCardNumberAfterTransfer);
        assertEquals(expected, actual);
    }

    @Test
    void shouldTransferMoneyToFirstCardIfAmountDouble() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        val transferPage = new TransferPage();
        String secondCardNumber = CardChoosePage.getSecondCardNumber();
        String amount = getTansferAmountWhenDouble(secondCardNumber);

        val cardInfo = DataHelper.secondCardInfo();
        CardChoosePage.chooseFirstCardForTransfer();
        transferPage.putTransferAmount(amount);
        transferPage.putMoneyCard(cardInfo);
    }

    @Test
    void shouldTransferMoneyToSecondCardIfAmountDouble() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        val transferPage = new TransferPage();
        String firstCardNumber = CardChoosePage.getFirstCardNumber();
        String amount = getTansferAmountWhenDouble(firstCardNumber);

        val cardInfo = DataHelper.firstCardInfo();
        CardChoosePage.chooseSecondCardForTransfer();
        transferPage.putTransferAmount(amount);
        transferPage.putMoneyCard(cardInfo);
    }

    @Test
    void checkFirstCardBalanceIfAmountDouble() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        val transferPage = new TransferPage();
        String secondCardNumber = CardChoosePage.getSecondCardNumber();

        String amount = getTansferAmountWhenDouble(secondCardNumber);
        val cardInfo = DataHelper.secondCardInfo();
        String firstCardNumberBeforeTransfer = CardChoosePage.getFirstCardNumber();

        double expected = (CardChoosePage.getCardBalance(firstCardNumberBeforeTransfer)
                + Double.parseDouble(amount));
        CardChoosePage.chooseFirstCardForTransfer();

        transferPage.putTransferAmount(amount);
        transferPage.putMoneyCard(cardInfo);

        String firstCardNumberAfterTransfer = CardChoosePage.getFirstCardNumber();
        double actual = CardChoosePage.getCardBalance(firstCardNumberAfterTransfer);
        assertEquals(expected, actual);
    }

    @Test
    void checkSecondCardBalanceIfAmountDouble() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        val transferPage = new TransferPage();
        String firstCardNumber = CardChoosePage.getFirstCardNumber();

        String amount = getTansferAmountWhenDouble(firstCardNumber);
        val cardInfo = DataHelper.firstCardInfo();
        String secondCardNumberBeforeTransfer = CardChoosePage.getSecondCardNumber();

        double expected = (CardChoosePage.getCardBalance(secondCardNumberBeforeTransfer)
                + Double.parseDouble(amount));
        CardChoosePage.chooseSecondCardForTransfer();

        transferPage.putTransferAmount(amount);
        transferPage.putMoneyCard(cardInfo);
        String secondCardNumberAfterTransfer = CardChoosePage.getSecondCardNumber();
        double actual = CardChoosePage.getCardBalance(secondCardNumberAfterTransfer);
        assertEquals(expected, actual);
    }
}