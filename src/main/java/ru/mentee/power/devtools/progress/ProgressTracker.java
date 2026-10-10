package ru.mentee.power.devtools.progress;

public class ProgressTracker {
    public String calculateTotalProgress(Mentee[] mentees) {
        if (mentees == null || mentees.length == 0) {
            return null;
        }

        int totalCompleted = 0;
        int totalTotal = 0;

        for (Mentee mentee : mentees) {
            if (mentee!= null) {
                totalCompleted +=mentee.completedLessons();
                totalTotal += mentee.totalLessons();
            }
        }

        int totalRemaining = totalTotal - totalCompleted;

        return String.format(
                "Cуммарно: пройдено %d из %d уроков, осталось %d уроков",
                totalCompleted, totalTotal, totalRemaining
        );
    }

    public double averageProgressPercent(Mentee[] mentees) {
        if(mentees == null || mentees.length == 0) {
            throw new IllegalArgumentException("Пустой список mentee");
        }

        double totalPercent = 0;

        for(Mentee mentee : mentees) {
            double percent = mentee.completedLessons() * 100.0 / mentee.totalLessons();
            totalPercent += percent;
        }

        return totalPercent / mentees.length;
    }

    public static void main(String[] args) {
        ProgressTracker tracker = new ProgressTracker();

        Mentee[] mentees = {
                new Mentee("Иван", "Москва", "Backend разработка", 5, 12),
                new Mentee("Мария", "Санкт-Петербург", "Fullstack", 8, 12),
                new Mentee("Пётр", "Казань", "Java Backend", 12, 12)
        };

        String progress = tracker.calculateTotalProgress(mentees);
        System.out.println(progress);
    }


}