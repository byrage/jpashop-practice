package com.github.byrage.jpashop.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class LocalDateTimeUtilsTest {

    @BeforeEach
    void setUp() {
        LocalDateTimeUtils.restoreClock();
    }

    @Test
    void now() {
        LocalDateTime custom = LocalDateTimeUtils.now();
        LocalDateTime now = LocalDateTime.now();

        assertThat(LocalDateTimeUtils.now())
                .as("custom=%s, now=%s", custom, now)
                .isBeforeOrEqualTo(LocalDateTime.now());
    }

    @Test
    @DisplayName("반복실행으로 시간이 계속 증가되는지 검증")
    void repeatNow() throws InterruptedException {
        LocalDateTime before = LocalDateTimeUtils.now();
        for (int i = 0; i < 10; i++) {
            LocalDateTime after = LocalDateTimeUtils.now();
            assertThat(before)
                    .as("before=%s, after=%s", before, after)
                    .isBeforeOrEqualTo(after);
            before = after;

            Thread.sleep(100);
        }
    }

    @Test
    void fixClock() {
        LocalDateTime ldt = LocalDateTime.MIN;
        LocalDateTimeUtils.fixClockForTest(ldt);

        assertThat(LocalDateTimeUtils.now()).isEqualTo(ldt);
    }
}