# hichy

[![](https://jitpack.io/v/dev-weiqi/hichy.svg)](https://jitpack.io/#dev-weiqi/hichy)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Check Activity/Fragment hierarchy easier!

## Install

project/build.gradle

```groovy
allprojects {
  repositories {
   ...
   maven { url 'https://jitpack.io' }
  }
}
```

app/build.gradle

```groovy
dependencies {
    debugImplementation 'com.github.dev-weiqi:hichy:$latestVersion'
}
```

## Usage

1. Install
```kotlin
class MyApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        Hichy.register(this)
    }
    
}
```

2. Launch app and click notification

<img width="320" height="640" src="https://github.com/dev-weiqi/hichy/blob/main/screencapture1.png"/>

3. Enjoy it!

<img width="320" height="640" src="https://github.com/dev-weiqi/hichy/blob/main/screencapture2.png"/>

## **Contribution**

Contributions are always welcome. If you have any ideas or suggestions, you can create a Github issue. I will get to you as soon as possible.

## **License**

```
Copyright 2021 Wei-Qi Wang

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
