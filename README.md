(以下の日本語)
# StockMarketApp
* Tên: Ứng dụng kiểm tra thị trường chứng khoán
* HĐH: Android
* Ngôn ngữ: Kotlin
* Kiến trúc: Clean Architechture
* Chức năng: Tìm kiếm các công ty có trên thị trường chứng khoán, biết thông tin các công ty và biến động cổ phiểu.
* Thư viện sử dụng:
 // OpenCSV
    implementation 'com.opencsv:opencsv:5.5.2'

    // Compose dependencies
    implementation "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1"
    implementation "androidx.compose.material:material-icons-extended:$compose_version"
    implementation "androidx.activity:activity-compose:1.6.0-alpha01"
    implementation "com.google.accompanist:accompanist-swiperefresh:0.24.2-alpha"

    // Compose Nav Destinations
    implementation 'io.github.raamcosta.compose-destinations:core:1.1.2-beta'
    ksp 'io.github.raamcosta.compose-destinations:ksp:1.1.2-beta'

    //Dagger - Hilt
    implementation "com.google.dagger:hilt-android:2.40.5"
    kapt "com.google.dagger:hilt-android-compiler:2.40.5"
    implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    kapt "androidx.hilt:hilt-compiler:1.0.0"
    implementation 'androidx.hilt:hilt-navigation-compose:1.0.0'

    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-moshi:2.9.0'
    implementation "com.squareup.okhttp3:okhttp:5.0.0-alpha.3"
    implementation "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3"

    // Room
    implementation "androidx.room:room-runtime:2.4.2"
    kapt "androidx.room:room-compiler:2.4.2"

    // Kotlin Extensions and Coroutines support for Room
    implementation "androidx.room:room-ktx:2.4.2"
    
* Một số hình ảnh :
![image](https://user-images.githubusercontent.com/75416674/167454269-b58a374b-c116-401c-93dc-05e9ee2e77f1.png)
![image](https://user-images.githubusercontent.com/75416674/167454363-36747cdc-dbc2-43b5-b5bc-ab6d777d009c.png)
![image](https://user-images.githubusercontent.com/75416674/167454409-8b5ae41d-0800-43cd-b534-ca1ada5d4117.png)

*名前：株式市場チェッカーアプリ
* OS：Android
*言語：Kotlin
*アーキテクチャ：クリーンなアーキテクチャ
*機能：株式市場の企業を検索し、企業情報と株式変動を把握します。
*ライブラリの使用：
