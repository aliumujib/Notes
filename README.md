# NoteApp

### Architecture
The app leverages uni-directional data flow the in building a predictable state machine for every screen. To achieve this, the ViewModel class of the Android Architecture Components and Kotlin Flow were used.

### Libraries ###
- Dagger Hilt - Dagger2 was used for dependency injection.
- Kotlin Flow - Flow was used for threading and data stream management.
- Room - Caching
- AndroidKtx - For cool extensions to Android classes.
- Architecture Components - For Lifecycle managment in the presentation layer, persistence.
- MockK - For mocking test dependencies.
- Truth - For Unit test assertions etc.
- Konveyor - For generating random data for tests.
- Espresso - For UI testing.
- Klint/Spotless - Lint checking and corrections

### How to build ###

- Run with Android Studio 4.2.1
- Run tests with ``./gradlew testDebug`
