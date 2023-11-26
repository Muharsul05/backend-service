# Что необходимо для запуска проекта на хосте:

* JDK 21, если нет то надо скачать и установить: [JDK 21](https://download.oracle.com/java/21/latest/jdk-21_windows-x64_bin.exe).
* PostgreSQL любая версия: [PostgreSQL](https://sbp.enterprisedb.com/getfile.jsp?fileid=1258792).

# Проверка установки:
    Для проверки что JDK установлена: java --version
    Для проверки что установлен PostgreSQL: В пуске должна быть программа PgAdmin

## Запуск проекта:
    git clone https://github.com/Muharsul05/backend-service
    cd backend-service
    ./mwnv spring-boot:run 

# Для запуска проекта в контейнере:
    Требуется Docker и терпение пока развернётся проект.
    Комманды для развёртки проекта:
    cd docker
    docker compose up -d

### На проекте есть документация доступная по url: [Документация](http://localhost:8080/microservice/api/docs-ui.html)