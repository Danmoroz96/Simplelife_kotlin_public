<img width="276" height="586" alt="image" src="https://github.com/user-attachments/assets/d8727aab-128e-49a4-bcc5-a426a72486d5" />
<img width="277" height="601" alt="image" src="https://github.com/user-attachments/assets/d73b5ef3-c191-45d8-96c4-e97ad343919f" />
<img width="277" height="613" alt="image" src="https://github.com/user-attachments/assets/fe34f9ff-c468-49aa-ac99-7e42d8ee5647" />
<img width="287" height="613" alt="image" src="https://github.com/user-attachments/assets/9fc3c46a-b87e-4e57-b071-4d986abbe3fa" />
<img width="277" height="591" alt="image" src="https://github.com/user-attachments/assets/38a36ecb-3aa4-4a0c-9357-668ad48c074e" />
<img width="308" height="617" alt="image" src="https://github.com/user-attachments/assets/87961b55-402c-4577-b6a3-800841f6848c" />
<img width="1522" height="788" alt="image" src="https://github.com/user-attachments/assets/a3315309-3877-42c9-bca1-3e93ed7d0227" />
<img width="300" height="607" alt="image" src="https://github.com/user-attachments/assets/3c3a8f28-6a0f-4d8d-b28f-61b421b97d44" />
<img width="241" height="532" alt="image" src="https://github.com/user-attachments/assets/21baa814-4051-4d45-a5b0-abaf145bb13c" />


SimpleLife helps users taking control of their everyday life by tracking daily expenses, habits, moods, and reflections. It provides a simple way to understand how you live, spend, and feel — all in one place. The goal is to help users notice and improve their habits. This app is for those who wants to track daily habits, emotions, and expenses, or who want a clearer view of where time and money and people looking to improve self-discipline and mental well-being. This app will have Expense Tracker, where you can add daily expenses with categories (Food, Transport, Entertainment, etc.) and view total spending by day, week, or month, Habit Tracker, in which you can Add custom habits (e.g., “Exercise”, “Read 30 mins”, “Drink water”) and mark habits done, Mood & Journal Entry, in which you can select how you feel each day (😊 😐 😞 etc.) and also write a short daily reflection, Dashboard and Data visualization. The app will be made on Kotlin language, will use Jetpack Compose for UI, Room database, MPAndroidChart, ViewModel,LiveData and Firebase cloud system. The design style is minimal, calm color palette (soft greens, whites, neutrals), it will focus on being clear. To make it clear for users, Icons for habits and moods weíll be used. Also users will enjoy smooth transitions between daily logs and charts.

# SimpleLife 🌱

SimpleLife is a modern Android application designed to help users take control of their everyday life by tracking expenses and habits. Built with **Kotlin** and **Jetpack Compose**, it demonstrates clean architecture and modern Android development practices.


## 🚀 Features

* **Expense Tracker:** Log daily spending with categories (Food, Transport, etc.).
* **Habit Tracker:** Add custom daily habits and mark them as complete.
* **Data Visualization:** View a dynamic Pie Chart breakdown of expenses using MPAndroidChart.
* **Local Storage:** All data is persisted locally using Room Database.

## 🛠 Tech Stack

* **Language:** Kotlin
* **UI:** Jetpack Compose (Material 3 Design)
* **Architecture:** MVVM (Model-View-ViewModel)
* **Database:** Room (SQLite) with Coroutines
* **Navigation:** Navigation Compose
* **Charts:** MPAndroidChart
* **Async:** Kotlin Coroutines & Flow
API used: Firebase Studio

## 📂 Project Structure

```text
com.example.simplelife
├── data/               # Room Database, Entities (Expense, Habit), DAO, Repository
├── ui/                 # Jetpack Compose UI
│   ├── screens/        # Dashboard, Stats, AddDialog
│   └── theme/          # Color, Type, Theme settings
├── viewmodel/          # SimpleLifeViewModel (State Management)
├── MainActivity.kt     # Navigation Host
└── SimpleLifeApplication.kt # App Entry Point




Installation
1. Clone the repository.
2. Open in Android Studio.
3. Sync Gradle dependencies.
4. Run on an Emulator or Physical Device (Min SDK 24).

## This application was created by Danila Morozov and Jiancai Hou. 
