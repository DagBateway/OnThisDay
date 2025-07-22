# 📅 On This Day

**On This Day** is a modern Android app that brings historical events to your fingertips.  
By integrating with the Wikimedia API, the app fetches and displays notable events from today’s date
in history, alongside related articles and rich imagery.

Users can browse events, explore connected Wikipedia pages, and learn more about the past — all in
an elegant, offline-resilient interface.

[![Get it on Google Play](https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Google_Play_Store_badge_EN.svg/180px-Google_Play_Store_badge_EN.svg.png)](https://play.google.com/store/apps/details?id=com.albertocamillo.onthisday)

---

## 🧭 Features

- 📆 Daily historical events based on the current date
- 🌐 Wikipedia integration via Wikimedia’s public API
- 🖼️ Related page previews with title, summary, and thumbnail
- 📴 Offline mode for previously viewed data
- 🎨 Material 3 + Jetpack Compose UI
- 💬 Multi-language support (auto-detected from device locale)
- 🔍 Tap-through to full articles in browser
- 🧪 Modular architecture, testable and scalable

---

## 🌍 Languages Supported

The app detects the current device language and requests content accordingly.

- English (`en`)
- German (`de`)
- French (`fr`)
- Swedish (`sv`)
- Portuguese (`pt`)
- Russian (`ru`)
- Spanish (`es`)
- Arabic (`ar`)
- Bosnian (`bs`)

---

## 🛠 Tech Stack

| Layer         | Technologies                  |
|---------------|-------------------------------|
| Language      | Kotlin                        |
| UI            | Jetpack Compose, Material 3   |
| Architecture  | MVI (Model–View–Intent)       |
| DI            | Hilt                          |
| Network       | Retrofit, Moshi               |
| Database      | Room (with DAO and Entities)  |
| Async / State | Kotlin Coroutines + StateFlow |
| Image Loading | Coil                          |
| Logging       | Timber                        |
| Testing       | JUnit (not yet implemented)   |

---

## 🧱 Architecture

The app follows a **unidirectional data flow** using MVI principles:

1. **UI Layer** (Compose)
    - Displays state from the ViewModel
    - Dispatches user actions via lambdas

2. **ViewModel**
    - Collects flows from the repository
    - Emits `UiState` objects

3. **Repository**
    - Abstracts access to network and local sources
    - Provides `Flow<T>` objects to the ViewModel
    - Handles error cases and fallback to cache

4. **Data Layer**
    - **Network**: Retrofit + Moshi for API calls
    - **Persistence**: Room database for event and page storage

---

## 🔌 API Integration

This app uses
the [Wikimedia REST API](https://api.wikimedia.org/wiki/API_reference/Feed/On_this_day)  
to fetch "On This Day" events. Endpoints are locale-sensitive, and data is fetched based on the
device's language and date.

**Example endpoint:**

```
GET /feed/v1/wikipedia/{language}/onthisday/selected/{month}/{day}
```

No authentication is required, but
Wikimedia's [terms of use and rate limits](https://www.mediawiki.org/wiki/API:Etiquette) must be
respected.

---

## 🚀 Getting Started

### ✅ Prerequisites

- Android Studio Giraffe or newer
- JDK 17+
- Kotlin 1.9.x
- Gradle 8+

### 📦 Installation

```bash
git clone https://github.com/albertocamillo/on-this-day.git
cd on-this-day
./gradlew assembleDebug
```

Then run the app via Android Studio, or install on device with:

```bash
./gradlew installDebug
```

---

## 📂 Project Structure

```
com.albertocamillo.onthisday
│
├── ui/                  # Composables and Screens
│   ├── components/      # Shared UI elements
│   ├── selectedevents/  # Event list screen
│   └── details/         # Related page detail screen
│
├── domain/              # Clean domain models
├── repository/          # Network/database sync and business logic
├── network/model/       # Retrofit API models
├── database/            # Room DB setup (entities, DAOs)
├── di/                  # Hilt modules
└── utils/               # Utilities (e.g., ID generation)
```

---

## ❌ Known Limitations

- No automated tests implemented yet
- No bookmarking or search functionality
- No dark mode toggle (uses system default)
- Offline data limited to today's events only

---

## 🧪 Future Improvements

- Add unit tests for repositories and view models
- Introduce paging/lazy loading for past and future dates
- Implement bookmarking for favourite events
- Add UI tests using Jetpack Compose testing APIs
- Improve error handling and UX for empty states

---

## 👤 Author

**Alberto Camillo**  
Sydney, Australia

---
