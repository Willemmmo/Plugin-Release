object ProjectVersions {
    const val openosrsVersion = "4.9.10"
    const val apiVersion = "^1.0.0"
}

object Libraries {
    private object Versions {
        const val guice = "4.2.2"
        const val javax = "1.3.2"
        const val lombok = "1.18.10"
        const val pf4j = "3.2.0"
        const val okhttp3 = "4.2.2"
        const val slf4j = "1.7.30"
    }

    const val guice = "com.google.inject:guice:${Versions.guice}:no_aop"
    const val javax = "javax.annotation:javax.annotation-api:${Versions.javax}"
    const val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    const val lombok = "org.projectlombok:lombok:${Versions.lombok}"
    const val pf4j = "org.pf4j:pf4j:${Versions.pf4j}"
    const val slf4j = "org.slf4j:slf4j-api:${Versions.slf4j}"

}
