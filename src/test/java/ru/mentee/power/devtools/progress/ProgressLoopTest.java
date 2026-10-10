package ru.mentee.power.devtools.progress;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Тестирование ProgressTracker")
class ProgressLoopTest {

    @Test
    @DisplayName("Должен корректно вычислить суммарный прогресс когда передан массив mentee")
    void shouldProgressWhenMultipleMentees() {

        ProgressTracker tracker = new ProgressTracker();
        Mentee[] mentees = {
                new Mentee("Иван", "Москва", "Backend разработка", 5, 12),
                new Mentee("Мария", "Санкт-Петербург", "Fullstack", 8, 12),
                new Mentee("Пётр", "Казань", "Java Backend", 12, 12)
        };

        assertThat(mentees)
                .extracting(Mentee::name)
                .containsExactly("Иван", "Мария", "Пётр");

        String result = tracker.calculateTotalProgress(mentees);

        assertThat(result)
                .contains("пройдено 25 из 36 уроков")
                .contains("осталось 11 уроков");
    }

    @Test
    @DisplayName("Должен корректно обработать массив когда все mentee завершили курс")
    void shouldWhenAllMenteesCompleted() {

        ProgressTracker tracker = new ProgressTracker();
        Mentee[] mentees = {
                new Mentee("Иван", "Москва", "Backend", 12, 12),
                new Mentee("Мария", "СПБ", "Fullstack", 12, 12)
        };

        String result = tracker.calculateTotalProgress(mentees);
        assertThat(result)
                .contains("пройдено 24 из 24 уроков")
                .contains("осталось 0 уроков");
    }

    @Test
    @DisplayName("Должен корректно определить средний процент троих менти с разным прогрессом")
    void shouldCalculateAverageProgressWhenMenteesHaveDifferentProgress() {
        ProgressTracker progressTracker = new ProgressTracker();
        Mentee[] mentees = {
                new Mentee("Иван", "Москва", "Backend", 5, 12),
                new Mentee("Данил", "Москва", "Fullstack", 8, 12),
                new Mentee("Мария", "СПБ", "Backend", 12, 12)
        };

        double result = progressTracker.averageProgressPercent(mentees);

        assertThat(result)
                .isCloseTo(69.44, within(0.01));
    }

    @Test
    @DisplayName("Когда у всех менти 50%, круглый случай")
    void shouldCalculateAverageProgressWhenAllMenteesHave50Percent() {
        ProgressTracker progressTracker = new ProgressTracker();
        Mentee[] mentees = {
                new Mentee("Иван", "Москва", "Backend", 6, 12),
                new Mentee("Данил", "Москва", "Fullstack", 6, 12),
                new Mentee("Мария", "СПБ", "Backend", 6, 12)
        };
        double result = progressTracker.averageProgressPercent(mentees);
        assertThat(result).isEqualTo(50.0);
    }

    @Test
    @DisplayName("Выбрасывает исключение для пустого массива")
    void shouldThrowExceptionWhenMenteesArrayIsEmpty() {
        ProgressTracker progressTracker = new ProgressTracker();
        Mentee[] mentees =  {};

        assertThatThrownBy(() -> progressTracker.averageProgressPercent(mentees))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Пустой список mentee");
    }

    @Test
    @DisplayName("Средний процент находится в допустимых границах")
    void shouldReturnValidPercentageWhenMenteesHaveProgress() {
        ProgressTracker progressTracker = new ProgressTracker();
        Mentee[] mentees = {
                new Mentee("Иван", "Москва", "Backend", 5,12),
                new Mentee("Катя", "СПБ", "Fullstack",8, 12)
        };


        double result = progressTracker.averageProgressPercent(mentees);

        assertThat(result)
                .isGreaterThan(0)
                .isLessThanOrEqualTo(100);
    }
}