# Task Manager REST API

REST API приложение для управления задачами с JWT авторизацией.

## Стек

- Java 21
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL

## Запуск проекта

### 1. Клонировать репозиторий
git clone https://github.com/mksmks12345/task-manager-api.git
cd task-manager-api

### 2. Создать базу данных
```sql
CREATE DATABASE taskmanager;
```

### 3. Настроить подключение
Скопируй `src/main/resources/application.properties.example`  
в `src/main/resources/application.properties` и заполни свои данные.

### 4. Запуск проект
./mvnw spring-boot:run

## API Endpoints

### Авторизация
| Метод | URL | Описание |
|-------|-----|----------|
| POST | /api/auth/register | Регистрация |
| POST | /api/auth/login | Вход, возвращает JWT токен |

### Задачи (требуют токен)
| Метод | URL | Описание |
|-------|-----|----------|
| POST | /api/tasks | Создать задачу |
| GET | /api/tasks | Получить все задачи |
| GET | /api/tasks?status=TODO | Фильтр по статусу |
| PUT | /api/tasks/{id} | Обновить задачу |
| DELETE | /api/tasks/{id} | Удалить задачу |

## Статусы задач

- `TODO` — новая
- `IN_PROGRESS` — в работе
- `DONE` — завершена

## Авторизация в запросах

Добавление заголовка к каждому запросу:
```
Authorization: Bearer ваш_токен
```