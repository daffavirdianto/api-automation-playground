# API Automation Playground (Java Maven)

Repository ini berisi contoh dan latihan otomatisasi pengujian API menggunakan Java dengan Maven serta berbagai tools dan framework.

## Fitur

- Contoh pengujian API (GET, POST, PUT, DELETE)
- Struktur project yang mudah dipahami
- Contoh penggunaan environment dan data dinamis

## Prasyarat

- Java Development Kit (JDK) 8 atau lebih baru
- Maven
- Tools pengujian API (misal: Postman, atau framework Java seperti Rest Assured)

## Instalasi

```bash
git clone https://github.com/username/api-automation-playground.git
cd api-automation-playground
mvn clean install
```

## Menjalankan Tes

```bash
mvn test "-DsuiteXml=src/test/resources/cucumber_suite.xml"
```

## Kontribusi

Kontribusi sangat terbuka! Silakan buat pull request atau buka issue untuk diskusi.