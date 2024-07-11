package ru.clevertec.check.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.clevertec.check.config.DataSource;
import ru.clevertec.check.dto.request.PurchaseDto;
import ru.clevertec.check.exception.BadRequestException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.clevertec.check.testutil.TestConstants.TEST_ARG_DATASOURCE_PASSWORD;
import static ru.clevertec.check.testutil.TestConstants.TEST_ARG_DATASOURCE_URL;
import static ru.clevertec.check.testutil.TestConstants.TEST_ARG_DATASOURCE_USERNAME;
import static ru.clevertec.check.testutil.TestConstants.TEST_DATASOURCE_PASSWORD;
import static ru.clevertec.check.testutil.TestConstants.TEST_DATASOURCE_URL;
import static ru.clevertec.check.testutil.TestConstants.TEST_DATASOURCE_USERNAME;

class ArgsParserTest {

    @Test
    void checkConsoleArgsParseShouldReturnCorrectPurchaseDto() {
        DataSource.DATASOURCE_PASSWORD = null;
        DataSource.DATASOURCE_USERNAME = null;
        DataSource.DATASOURCE_URL = null;
        String[] testArgs = new String[]{
                "1-1", "1-1", "2-2", "discountCard=1111", "balanceDebitCard=100.01", "saveToFile=./result.csv",
                TEST_ARG_DATASOURCE_USERNAME, TEST_ARG_DATASOURCE_PASSWORD, TEST_ARG_DATASOURCE_URL};
        PurchaseDto expected = new PurchaseDto();
        expected.products = Map.of(1L,1, 2L, 2);
        expected.discountCard = 1111L;
        expected.balanceDebitCard= BigDecimal.valueOf(100.01);

        PurchaseDto actual = ArgsParser.consoleArgsParse(testArgs);

        assertAll(
                () -> assertEquals(expected.discountCard, actual.discountCard),
                () -> assertEquals(expected.products.size(), actual.products.size()),
                () -> assertEquals(expected.balanceDebitCard, actual.balanceDebitCard),
                () -> assertEquals(DataSource.DATASOURCE_PASSWORD, TEST_DATASOURCE_PASSWORD),
                () -> assertEquals(DataSource.DATASOURCE_URL, TEST_DATASOURCE_URL),
                () -> assertEquals(DataSource.DATASOURCE_USERNAME, TEST_DATASOURCE_USERNAME)
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsArgs")
    void checkConsoleArgsParseShouldThrowsBadRequestException(String[] input) {
        DataSource.DATASOURCE_PASSWORD = null;
        DataSource.DATASOURCE_USERNAME = null;
        DataSource.DATASOURCE_URL = null;
        Constants.SAVE_TO_FILE = null;

        assertThrows(BadRequestException.class, () -> ArgsParser.consoleArgsParse(input));
    }

    private static Stream<Arguments> provideStringsArgs() {
        return Stream.of(
                Arguments.of((Object) new String[] {"1-1", "2-2"}),
                Arguments.of((Object) new String[] {"1-1", "2-2", "saveToFile=./result.csv",
                        TEST_ARG_DATASOURCE_USERNAME, TEST_ARG_DATASOURCE_PASSWORD, TEST_ARG_DATASOURCE_URL}),
                Arguments.of((Object) new String[] {"1-1", "2-2a", "balanceDebitCard=100",
                        TEST_ARG_DATASOURCE_USERNAME, TEST_ARG_DATASOURCE_PASSWORD, TEST_ARG_DATASOURCE_URL}),
                Arguments.of((Object) new String[] {"1-1", "2-2a", "balanceDebitCard=100", "saveToFile=./result.csv",
                        TEST_ARG_DATASOURCE_USERNAME, TEST_ARG_DATASOURCE_PASSWORD, TEST_ARG_DATASOURCE_URL}),
                Arguments.of((Object) new String[] {"1-1", "2-2", "balanceDebitCard=100", "saveToFile=./result.csv",
                        TEST_ARG_DATASOURCE_USERNAME, TEST_ARG_DATASOURCE_PASSWORD}),
                Arguments.of((Object) new String[] {"1-1", "2-2", "balanceDebitCard=100", "saveToFile=./result.csv",
                        TEST_ARG_DATASOURCE_USERNAME, TEST_ARG_DATASOURCE_URL}),
                Arguments.of((Object) new String[] {"1-1", "2-2", "balanceDebitCard=100", "saveToFile=./result.csv",
                        TEST_ARG_DATASOURCE_PASSWORD, TEST_ARG_DATASOURCE_URL})
        );
    }
}
