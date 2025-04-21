# 💰 Kotlin Bank App

A beginner-friendly **end-to-end Kotlin + Android MVVM** project to simulate a **basic banking system**. The app demonstrates core Android development skills and progresses toward Clean Architecture, Dependency Injection, and modern libraries.

---

## 🚀 Problem Statement

**Build a mobile banking app** that allows users to:

- 🔐 Login (no real auth initially)
- 💵 Add money to their account
- 💸 Withdraw funds
- 🔁 Transfer to another existing account
- 🧾 View transaction history

The app will start with **offline-only functionality** using **Room DB**, then extend to online sync, cloud auth, testing, and advanced architecture.

---

## 📱 Core Features

| Feature       | Description                                                        |
|---------------|---------------------------------------------------------------------|
| Login         | Simple username-based login (no real auth in v1)                   |
| Add Money     | Add a fixed or user-entered amount to account                      |
| Withdraw      | Deduct money if balance is sufficient                              |
| Transfer      | Transfer to another account in local DB                            |
| History       | View list of transactions (RecyclerView or Compose)                |
| Balance View  | Show current account balance at top of screen                      |

---

## 🛠 Tech Stack (v1)

- **Language:** Kotlin
- **UI:** XML + ViewBinding
- **Architecture:** MVVM (Model-View-ViewModel)
- **Persistence:** Room (SQLite)
- **Coroutines:** Kotlinx Coroutines + Flow
- **DI:** Manual constructor injection

---

## 📦 App Layers (MVVM)
UI (Activity/Fragment) ↔  ViewModel ↔  Repository ↔  Room DAO ↔ Entity ↔ Database

## 📅 Roadmap

| Week | Focus Area                 | What You Learn                                 | Milestone Achieved                     |
|------|----------------------------|------------------------------------------------|----------------------------------------|
| 1    | Kotlin + OOP               | Classes, Data Classes, Control Flow            | Account & Transaction models created   |
| 1    | Android Fundamentals       | Activities, ViewBinding, UI setup              | Home screen with login UI              |
| 1    | RecyclerView               | List of transactions                           | View static transaction history        |
| 1    | Room DB                    | Save/load balances and transactions            | Persist data and show balance          |
| 1    | MVVM Pattern               | ViewModel, Flow, manual DI                     | All logic inside ViewModel             |
| 1    | Navigation                 | Switch between login and dashboard             | Simple navigation flow added           |

---

## 🧩 Future Extensions (Advanced Topics)

| Topic                    | Tech                     | Feature Added                                          |
|--------------------------|--------------------------|--------------------------------------------------------|
| Dependency Injection   | Hilt                     | Inject ViewModel and Repository cleanly               |
| Networking            | Retrofit + Moshi         | Add currency exchange rate fetching                   |
| Clean Architecture    | Domain/Data separation   | UseCases, Repositories, Entities                      |
| Firebase Auth         | Firebase + Firestore     | Real user login and cloud-synced transactions         |
| Notifications         | WorkManager + Notification| Notify daily spend/earn summary                      |
| Testing               | JUnit + MockK + Espresso | Unit + UI tests with 80%+ coverage                    |
| Compose               | Jetpack Compose (optional)| Convert UI for modern declarative style               |

---

## 🧠 Learning Goals

- Master Kotlin syntax and idioms
- Understand real-world MVVM and app layering
- Build and structure a multi-screen Android app
- Prepare for Clean Architecture + DI
- Show working code to recruiters to demonstrate Kotlin + Android proficiency

---
