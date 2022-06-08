package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.Registration.getRegUser;
import static ru.netology.data.DataGenerator.Registration.getUser;
import static ru.netology.data.DataGenerator.getRandLogin;
import static ru.netology.data.DataGenerator.getRandPassword;

public class TestMode {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldSuccessful() {
        var regUser = getRegUser("active");
        $("[data-test-id=\"login\"] input").setValue(regUser.getLogin());
        $("[data-test-id=\"password\"] input").setValue(regUser.getPassword());
        $("[data-test-id=\"action-login\"]").click();
        $(".heading").shouldBe(visible).shouldHave(Condition.text("Личный кабинет"));
    }

    @Test
    void shouldErrorNotRegUser() {
        var notRegUser = getUser("active");
        $("[data-test-id=\"login\"] input").setValue(notRegUser.getLogin());
        $("[data-test-id=\"password\"] input").setValue(notRegUser.getPassword());
        $("[data-test-id=\"action-login\"]").click();
        $("[data-test-id=\"error-notification\"]").shouldBe(visible).shouldHave(Condition.text("Неверно указан логин или пароль"));
    }

    @Test
    void shouldErrorBlockUser() {
        var blockUser = getRegUser("blocked");
        $("[data-test-id=\"login\"] input").setValue(blockUser.getLogin());
        $("[data-test-id=\"password\"] input").setValue(blockUser.getPassword());
        $("[data-test-id=\"action-login\"]").click();
        $("[data-test-id=\"error-notification\"]").shouldBe(visible).shouldHave(Condition.text("Пользователь заблокирован"));
    }

    @Test
    void shouldErrorRandLogin() {
        var regUser = getRegUser("active");
        var randLogin = getRandLogin();
        $("[data-test-id=\"login\"] input").setValue(randLogin);
        $("[data-test-id=\"password\"] input").setValue(regUser.getPassword());
        $("[data-test-id=\"action-login\"]").click();
        $("[data-test-id=\"error-notification\"]").shouldBe(visible).shouldHave(Condition.text("Неверно указан логин или пароль"));
    }

    @Test
    void shouldErrorRandPassword() {
        var regUser = getRegUser("active");
        var randPassword = getRandPassword();
        $("[data-test-id=\"login\"] input").setValue(regUser.getLogin());
        $("[data-test-id=\"password\"] input").setValue(randPassword);
        $("[data-test-id=\"action-login\"]").click();
        $("[data-test-id=\"error-notification\"]").shouldBe(visible).shouldHave(Condition.text("Неверно указан логин или пароль"));
    }

}
