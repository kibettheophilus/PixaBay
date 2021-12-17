# PixaBay

An android app built using Kotlin that consumes [Pixabay API](https://pixabay.com/) to display images.It has been built following Clean Architecture Principle, Repository Pattern, MVVM Architecture in the presentation layer as well as Jetpack components.

## Libraries.

- [Koin](https://github.com/google/hilt) - Dependency Injection library.
- [Jetpack](https://developer.android.com/jetpack)
    -   [Android KTX](https://developer.android.com/kotlin/ktx.html) - Provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
    - [AndroidX](https://developer.android.com/jetpack/androidx) - Major improvement to the original Android [Support Library](https://developer.android.com/topic/libraries/support-library/index), which is no longer maintained.
    -   [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
    -   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
    - [View Binding](https://developer.android.com/topic/libraries/view-binding/) - A feature that allows you to more easily write code that interacts with views.
    - [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)-Component that allows easier implementation of navigation from simple button clicks to more complex patterns.

- [Retrofit](https://square.github.io/retrofit/) - Type-safe http client
  and supports coroutines out of the box.
- [GSON](https://github.com/square/gson) - JSON Parser,used to parse
  requests on the data layer for Entities and understands Kotlin non-nullable
  and default parameters.
- [OkHttp-Logging-Interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - Logs HTTP request and response data.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines.
- [Flow](https://developer.android.com/kotlin/flow) - Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously.
- [Expresso](https://developer.android.com/training/testing/espresso) - Library used to write concise, beautiful, and reliable Android UI tests.
- [Truth](https://truth.dev/) - Assertions Library,provides readability as far as assertions are concerned.
- [Mockito](https://site.mockito.org/) - Mocking framework for tests.
- [Roboelectric](http://robolectric.org/) - Framework used to quickly and reliably run unit tests quick using the JVM.
- 
## CI/CD
- [GitHub Actions](https://github.com/kibettheophilus/PixaBay/blob/master/.github/workflows/firebase_distribute.yml) - GitHub actions is used in this project to run the continuous intergration and continuous deployment when a code is pushed to the master branch.
- [Fastlane](https://fastlane.tools/) - Fastlane is an open source platform aimed at simplifying Android and iOS deployment.
fastlane lets you automate every aspect of your development and release workflow. 
- [Firebase App Distribution](https://firebase.google.com/docs/app-distribution) - Makes distributing your apps to trusted testers painless.

## Screenshots
![List](https://user-images.githubusercontent.com/61080898/146204139-a8c62a04-8f20-4925-be9b-c77c07861c4e.png)
![Details](https://user-images.githubusercontent.com/61080898/146204189-5f4f1a72-a031-4d81-b179-6559913960e7.png)

https://user-images.githubusercontent.com/61080898/146205732-c519cf9b-0f81-4b69-a4a9-0db228e9f149.mp4

