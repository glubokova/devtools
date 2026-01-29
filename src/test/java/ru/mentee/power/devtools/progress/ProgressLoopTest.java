
package ru.mentee.power.devtools.progress;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Тестирование ProgressTracker")
class ProgressLoopTest {

    @Test
    @DisplayName("Должен корректно вычислить суммарный прогресс когда передан массив mentee")
    void shouldProgresswhenMultipleMentees() {

        ProgressTracker tracker = new ProgressTracker();
        Mentee[] mentees = {
                new Mentee("Иван", "Москва", "Backend разработка", 5, 12),
                new Mentee("Мария", "Санкт-Петербург", "Fullstack", 8, 12),
                new Mentee("Пётр", "Казань", "Java Backend", 12, 12)
        };

        String result = tracker.calculateTotalProgress(mentees);

        assertThat(result)
                .contains("пройдено 25 из 36 уроков")
                .contains("осталось 11 уроков");
    }

    @Test
    @DisplayName("Должен корректно обработать массив когда все mentee завершили курс")
    void shouldwhenAllMenteesCompleted() {

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
}