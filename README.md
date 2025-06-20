# NSON – Nested Serialization Object Notation  
**Version: 1.0.0**

---

## 📌 Introduction

**NSON** is a custom-built JSON API for **Java** that provides functionality similar to Python's `json` module.  
It allows easy reading, writing, validating, and parsing of JSON data using plain Java — no third-party libraries required.

This library is lightweight, modular, and designed for developers who want control over JSON without external dependencies.

---

## 🔥 What’s With the Name?

- **NSON** stands for:  
  🧬 **Nested Serialization Object Notation** – highlighting its ability to parse complex nested structures  
  💻 **Nikhil’s Serialization Object Notation** – a personal touch from the creator, Nikhil 😄  

You get a clean, extensible, native Java JSON tool — handcrafted.

---

## 📸 Screenshots

> Sample UIs shown below are from tools that use this JSON API

# Screen Short
<p align="center">
  <img src="util/HomePage.png" width="30%" />
  <img src="util/SettingPage.png" width="30%" />
  <img src="util/loginPage.png" width="30%" />
  <img src="util/CreatingAccount.png" width="30%" />
  <img src="util/login_Success.png" width="30%" />
  <img src="util/asking_logout.png" width="30%" />
</p>
<br><br>

---

## ⚙️ How to Use

Just clone the repo and add it to your Java project.  
There’s no need for Maven or Gradle — everything works out of the box.

```bash
git clone https://github.com/karnikhil90/NSON.git
````

Then import and use:

```java
import src.NSON;
```

To explore usage, see the **example/** directory.

---

## 📁 Project Structure

```
NSON/
│   .gitignore
│   README.md
│   LICENSE
│   JSON.java
│   NSON.java
│
├───.github/
│   └───workflows/
│
├───.vscode/
│   └───setting.json
│
├───example/
│   ├───example.json
│   ├───ReadFromFile.java
│   └───Test.java
│
├───src/
│   ├───exception/
│   │   ├───JSONException.java
│   │   ├───JSONFileException.java
│   │   ├───JSONParseException.java
│   │   └───JSONValidationException.java
│   │
│   ├───utility/
│   │   ├───FileAccess.java
│   │   ├───NSONUtils.java
│   │   ├───RawJSONHandler.java
│   │   └───SymbolBalancer.java
│   │
│   ├───JSON.java
│   └───NSON.java
│
└───lib/
```

---

## 📜 License

This project is licensed under the **MIT License**.
See the [LICENSE](LICENSE) file for full details.

---

## About Me

Self-taught coder | Still Learning | Fluent in Java❤️ & Python | C/C++, Rust, & Basic Web Development | Passionate about Embedded Systems ❤️

### Connect with Me

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/karnikhil90/)
[![Twitter](https://img.shields.io/badge/Twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://x.com/karnikhil90)
[![Social Media](https://img.shields.io/badge/Social%20Media-000000?style=for-the-badge&logo=google&logoColor=white)](https://linktr.ee/karnikhil90)
