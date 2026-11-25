# Лаборатори 4 — JUnit 5 нэгжийн тест

**Д.Бүрэнтөгс · B222270092**

## Хэрэгслийн хувилбар

```text
> java -version
openjdk version "17.0.21.1" 2026-11-17 LTS
OpenJDK Runtime Environment Microsoft-16059596 (build 17.0.21.1+2-LTS)
OpenJDK 64-Bit Server VM Microsoft-16059596 (build 17.0.21.1+2-LTS, mixed mode, sharing)

> mvn -version
Apache Maven 3.9.11 (3e54c93a704957b63ee3494413a2b544fd3d825b)
Maven home: <workspace>/work/java\apache-maven-3.9.11
Java version: 17.0.21.1, vendor: Microsoft, runtime: <workspace>/work/java\jdk-17.0.21.1+2
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
```

Локал замын эхний хэсгийг `<workspace>` гэж тэмдэглэв.

## Ажиллуулах

Java 17 буюу түүнээс дээш хувилбар болон Maven ашиглана.

```bash
mvn test
```

- [Хэвийн тестийн бүтэн гаралт](results/mvn-test.txt)
- [Зориудын мутацийн бүтэн гаралт](results/mvn-test-mutant.txt)

## Үр дүн

`GradeCalculator` нь нийт оноог үсгэн дүнд хөрвүүлж, ирц, лаб, хоёр сорил, шалгалтын оноог нийлүүлдэг. Нийт оноо болон бүрэлдэхүүн тус бүрийн хязгаарыг шалгаж, буруу утгад `IllegalArgumentException` шиддэг. Есөн тестийн методаас гурав нь параметртэй бөгөөд Maven нийт **26 тест** гүйцэтгэж, бүгд амжилттай болсон. Мутацийн үед A дүнгийн нөхцөлийг `score >= 90`-ээс `score > 90` болгон өөрчилсөн. `exactNinetyIsA` тест 90 оноонд A хүлээсэн ч B буцсан тул унасан. Энэ тест нь 95 оноо хэвээр A болж байсан ч яг 90 дээр гарсан алдааг илрүүлсэн. Нөхцөлийг сэргээсний дараа Maven тестүүдийг дахин ажиллуулж, `BUILD SUCCESS` гарсан.

