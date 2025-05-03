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

# ✅ Kotlin Interview Preparation Checklist

## 🟢 Kotlin Language Fundamentals

- [x] Variables: `val`, `var`, immutability
- [x] Nullable Types: `?`, `!!`, safe calls (`?.`)
- [x] Elvis Operator: `?:`
- [ ] Smart Casts
- [ ] Functions & Lambdas
  - [ ] Higher-order functions
  - [ ] Inline functions
- [x] Control Flow: `if`, `when`, `for`, `while`
- [x] Data Classes
- [ ] Object Declarations and Singletons
- [ ] Companion Objects
- [x] Interfaces vs Abstract Classes
- [ ] Extension Functions
- [ ] Sealed Classes vs Enums
- [ ] Collections: `map`, `filter`, `reduce`, `flatMap`
- [ ] Generics: `T`, `in`, `out`, reified
- [ ] Scope Functions: `apply`, `also`, `run`, `let`, `with`

---

## 🟡 Coroutines & Concurrency

- [ ] Coroutine Basics: `launch`, `async`, `delay`, `suspend`
- [ ] Coroutine Scopes: `GlobalScope`, `viewModelScope`, etc.
- [ ] Dispatchers: `Main`, `IO`, `Default`, `Unconfined`
- [ ] Structured Concurrency
- [ ] Exception Handling in Coroutines
- [ ] Channels and Flows (optional)

---

## 🔵 Android & Jetpack (If Android Role)

- [ ] MVVM Architecture
- [ ] ViewModel + LiveData
- [ ] StateFlow vs LiveData
- [ ] Room DB
  - [ ] Entity, DAO, Queries
  - [ ] Migrations
- [ ] ViewBinding vs DataBinding
- [ ] Navigation Component
- [ ] SharedPreferences
- [ ] RecyclerView + Adapter
- [ ] Jetpack Compose (optional)
- [ ] WorkManager / Services
- [ ] Dependency Injection (Manual)
- [ ] Hilt / Dagger (optional)

---

## 🟣 Clean Code & Architecture

- [x] SOLID Principles
- [x] KISS, DRY, YAGNI
- [ ] Single Source of Truth
- [x] Repository Pattern
- [x] Clean Architecture Layers (UI, Domain, Data)
- [ ] Multi-module Project Structure

---

## 🔴 Testing & Debugging

- [ ] JUnit Testing
- [ ] Mocking with MockK / Mockito
- [ ] UI Testing (Espresso / Compose Testing)
- [ ] Debugging Coroutines
- [ ] Logcat Filters and Crash Logs

---

## 🧠 System Design & Patterns (Mid-Sr Roles)

- [ ] Designing scalable Android app
- [ ] Offline-first Architecture
- [ ] Modularization
- [ ] Design Patterns: Singleton, Factory, Observer

---

## 🧪 Practice & Problem Solving

- [ ] Solve 25+ LeetCode problems in Kotlin
- [ ] Build 1–2 full Kotlin Android Projects
- [ ] Build a feature using MVVM + Room + ViewModel
- [ ] Submit a test assignment or case study

---

## 📘 Soft Skills & Interviews

- [x] Explain MVVM and Clean Architecture
- [ ] Answer Kotlin vs Java differences
- [ ] Talk through code decisions and trade-offs
- [ ] Ask clarifying questions during interviews

