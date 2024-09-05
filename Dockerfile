# Используем официальный образ GraalVM как базовый
FROM ghcr.io/graalvm/graalvm-ce:latest AS build

# Устанавливаем необходимые пакеты
RUN gu install native-image

# Создаем рабочую директорию
WORKDIR /app

# Копируем файлы Gradle и зависимости
COPY gradlew .
COPY gradle/ ./gradle

# Копируем остальные файлы проекта
COPY . .

# Устанавливаем необходимые права для Gradle Wrapper
RUN chmod +x ./gradlew

# Сборка проекта
RUN ./gradlew build --no-daemon

# Собираем нативное изображение (опционально, если используете GraalVM Native Image)
RUN ./gradlew nativeCompile --no-daemon

# Строим минимальный образ
FROM eclipse-temurin:17-jre-alpine

# Создаем рабочую директорию
WORKDIR /app

# Копируем собранный jar файл из предыдущего этапа сборки
COPY --from=build /app/build/libs/your-application-name-0.1-all.jar .

# Указываем команду запуска
CMD ["java", "-jar", "your-application-name-0.1-all.jar"]
